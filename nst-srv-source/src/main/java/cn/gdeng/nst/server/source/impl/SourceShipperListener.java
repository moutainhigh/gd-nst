package cn.gdeng.nst.server.source.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.dto.source.RuleDTO;
import cn.gdeng.nst.api.dto.source.SourceShipperMqDto;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.RuleLogisticAssignEntity;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.NstRuleEnum;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.enums.SourceClientsEnum;
import cn.gdeng.nst.enums.SourceStatusEnum;
import cn.gdeng.nst.server.source.rule.AssignRule;
import cn.gdeng.nst.server.source.rule.StrategyContext;
import cn.gdeng.nst.util.server.InterfaceUtil;
import cn.gdeng.nst.util.server.JacksonUtil;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.GdProperties;
import cn.gdeng.nst.util.web.api.ObjectResult;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 货源分配消费者监听
 * 
 * @author xiaojun
 *
 */
@Service
public class SourceShipperListener implements MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 分配上线
	 */
	private static final int ASSIGNLIMT = 3;
	/**
	 * 平台分配上线
	 */
	private static final int PLATFORM_ASSIGNLIMT = 1;
	/**
	 * 货源分配校验失败常量
	 */
	private static final boolean VERIFY_FALSE = false;
	/**
	 * 货源分配校验成功常量
	 */
	private static final boolean VERIFY_TRUE = true;
	/**
	 * 平台配送辨识
	 */
	private static final int PLATFORM = 1;

	@Resource
	private BaseDao<?> baseDao;
	@Resource
	private ProducerBean taskProducer;
	@Resource
	private ProducerBean countProducer;
	@Autowired
	@Qualifier("msgPushProducer")
	private ProducerBean msgPushProducer;
	@Autowired
	private InterfaceUtil interfaceUtil;
	@Autowired
	private GdProperties gdProperties;

	/**
	 * 货源分配MQ消费者
	 */
	@Transactional
	public Action consume(Message message, ConsumeContext context) {
		RuleDTO dto = new RuleDTO();
		SourceShipperMqDto shipperMqDto = new SourceShipperMqDto();
		try {
			shipperMqDto = (SourceShipperMqDto) SerializeUtil.unserialize(message.getBody());
			dto.setSourceId(shipperMqDto.getId());
			// 货源分配前校验
			Boolean canAssign = assignBeforeVerify(dto);
			if (!canAssign) {
				if (logger.isDebugEnabled()) {
					logger.debug("货源分配前校验失败,货源id：" + dto.getSourceId());
				}
				return Action.CommitMessage;
			}
			// 验证通过获取分配所需货源信息
			RuleDTO ruleDto = querySourceToRule(dto);
			// 获取分配规则策略
			String ruleCode = getRuleCode(ruleDto);
			if (logger.isDebugEnabled()) {
				logger.debug("当前分配的货源id：" + ruleDto.getSourceId() + "执行分配,分配规则为:" + AssignRule.getAssignRule(ruleCode)
						+ "分配类型为:" + ruleDto.getSourceType());
			}
			// 执行分配规则策略
			Boolean isAssign = StrategyContext.getAssginRule(ruleCode).isAssign(ruleDto);
			// 规则策略内分配不成功,货源为直发
			if (!isAssign) {
				logger.warn("分配不成功,货源直发,货源id：" + ruleDto.getSourceId());
				baseDao.execute("Rule.assignToDriver", ruleDto);
			}
			return Action.CommitMessage;
		} catch (Exception e) {
			logger.error("message:", JSON.toJSONString(message));
			logger.error(JSON.toJSONString(context), e);
			insertMqError(dto, MqConstants.BIZ_TYPE_0, MqConstants.TOPIC_SOURCE_SHIPPER, e.getMessage(), "");
			return Action.CommitMessage;
		}
	}

	/**
	 * 货源分配前校验
	 * 
	 * @param dto
	 * @return
	 */
	private boolean assignBeforeVerify(RuleDTO dto) throws BizException {
		logger.info("货源插入前校验:==============货源id" + dto.getSourceId());
		// 判断货源id是否传入
		if (dto.getSourceId() == null) {
			return VERIFY_FALSE;
		}
		// 判断货源是否存在
		RuleDTO ruleDto = querySourceToRule(dto);
		if (ruleDto == null) {
			return VERIFY_FALSE;
		}
		// 判断货源是否处于分配中状态
		if (ruleDto.getNstRule().byteValue() != NstRuleEnum.DISTRIBUTING.getCode().byteValue()) {
			return VERIFY_FALSE;
		}
		// 判断货源是否达到分配货源次数限制
		if (ruleDto.getAssignCount().intValue() == ASSIGNLIMT) {
			// 超过分配次数货源直发 货源状态nstRule 已发布 货源类型 sourceGoodsType 为自有
			baseDao.execute("Rule.assignToDriver", ruleDto);
			return VERIFY_FALSE;
		}
		// 判断平台配送是否关闭
		if (ruleDto.getSourceStatus().byteValue() == SourceStatusEnum.CLOSED.getCode()) {
			logger.info("平台配送取消了,不进行分配货源id:" + ruleDto.getSourceId());
			return VERIFY_FALSE;
		}
		// 平台配送是否达到最大限制
		if (ruleDto.getAssignCount().intValue() == PLATFORM_ASSIGNLIMT
				&& ruleDto.getPlatform().intValue() == PLATFORM) {
			// 超过分配次数货源直发 货源状态 改为(已失效) 超时15分钟
			baseDao.execute("Rule.assignPlatformSourceClose", ruleDto);
			baseDao.execute("Rule.updateSourceAssignHisClose", ruleDto);
			// 调用农商友提供的接口 ，通知他们货源分配不成功
			notifyAssignFailure(ruleDto);
			// 友盟推送 物流公司 分配货源失败
			pushMessage(ruleDto);
			if (logger.isDebugEnabled()) {
				logger.debug("当前分配的货源id(平台配送)：" + ruleDto.getSourceId() + "分配失败");
			}
			return VERIFY_FALSE;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("当前分配的货源id：" + ruleDto.getSourceId() + "验证通过");
		}
		return VERIFY_TRUE;
	}

	/**
	 * 调用农商友提供的接口 ，通知他们货源分配 不成功
	 * 
	 * @param dto
	 * @throws BizException
	 */
	private void notifyAssignFailure(RuleDTO dto) throws BizException {
		Map<String, Object> param = new HashMap<>();
		// 所需参数
		param.put("memberAddressId", dto.getSourceId());
		// 加密参数
		Map<String, Object> des3ParamMap = des3Map(param);
		String resultStr = interfaceUtil.notifyAssignFailure(des3ParamMap);
		resultStr = Des3.decode(resultStr);
		// 谷登api返回结果类
		ObjectResult result = null;
		// 将解密后的结果 装换result
		try {
			result = JacksonUtil.str2Obj(resultStr, ObjectResult.class);
			if (result.getStatusCode() != 0) {
				insertMqError(result, MqConstants.BIZ_TYPE_2, MqConstants.TOPIC_SOURCE_SHIPPER, MsgCons.M_27006,
						gdProperties.getNotifyAssignFailureUrl());
			}
			logger.info("货源分配失败通知成功");
		} catch (Exception e) {
			logger.error("货源分配不成功，通知农商友失败" + e.toString());
		}

	}

	/**
	 * 对参数进行加密
	 * 
	 * @param map
	 * @return
	 */
	private Map<String, Object> des3Map(Map<String, Object> map) {
		String json = GSONUtils.getGson().toJson(map);
		Map<String, Object> temp = new HashMap<String, Object>();
		try {
			temp.put("param", Des3.encode(json));
		} catch (Exception e) {
			return map;
		}
		return temp;
	}

	/**
	 * 获取分配规则code
	 * 
	 * @param dto
	 * @return
	 */
	private String getRuleCode(RuleDTO ruleDto) {
		RuleLogisticAssignEntity entity = queryRuleLogisticAssignEntity(ruleDto.getShipperMemberId());
		if (entity != null && ruleDto.getAssignMemberId() == null) {
			//如果是平台配送，或者是农速通-货主 或者 是农速通-物流公司 才走指派规则
			if (ruleDto.getPlatform().intValue() == PLATFORM
					|| ruleDto.getClients().byteValue() == SourceClientsEnum.NST_AGENT.getCode()
							|| ruleDto.getClients().byteValue() == SourceClientsEnum.NST_CONSIGNOR.getCode()) {
				// 将分配memberId 设置为指派的memberid
				ruleDto.setAssignMemberId(entity.getMemberIdLogistic());
				ruleDto.setMemberType(entity.getMemberType());
				if (logger.isDebugEnabled()) {
					logger.debug(
							"当前分配的货源id：" + ruleDto.getSourceId() + "---通过货主指派的物流公司/车主为：" + ruleDto.getAssignMemberId());
				}
				return AssignRule.SHIPPERASSIGN.getCode();
			}
			return AssignRule.LOGISTICSASSIGN.getCode();
		} else {
			return AssignRule.LOGISTICSASSIGN.getCode();
		}
	}

	/**
	 * 查询货源到规则中组合dto
	 * 
	 * @param dto
	 * @return
	 */
	private RuleDTO querySourceToRule(RuleDTO dto) {
		RuleDTO ruleDto = baseDao.queryForObject("Rule.querySourceToRule", dto, RuleDTO.class);
		return ruleDto;
	}

	/**
	 * 查询货主绑定物流公司或者车主
	 * 
	 * @param memberId
	 * @return
	 */
	private RuleLogisticAssignEntity queryRuleLogisticAssignEntity(Integer shipperMemberId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipperMemberId", shipperMemberId);
		RuleLogisticAssignEntity entity = baseDao.queryForObject("Rule.queryShipperBindInfo", paramMap,
				RuleLogisticAssignEntity.class);
		return entity;
	}

	/**
	 * 友盟推送 物流公司 分配货源失败
	 * 
	 * @param dto
	 */
	private void pushMessage(RuleDTO dto) throws BizException {
		Map<String, Object> map = new HashMap<>();
		map.put("sourceId", dto.getSourceId());
		map.put("assignMemberId", dto.getAssignMemberId());
		// 获取货源的分配历史id
		Integer sourceAssignHisId = baseDao.queryForObject("Rule.querySourceAssignHisId", map, Integer.class);
		msgPushToAssignDetail(sourceAssignHisId, dto.getAssignMemberId(), MsgCons.M_28016);
	}

	/**
	 * 分配成功，给物流公司或车主推送
	 * 
	 * @param beforeId
	 * @param content
	 * @throws BizException
	 */
	private void msgPushToAssignDetail(Integer id, Integer memberId, String content) throws BizException {
		PushMsgDto dto = new PushMsgDto();
		dto.setBizId(id);
		dto.setContent(content);
		dto.setMemberId(memberId);
		dto.setMsgType(PushConstants.MSG_TYPE_4);
		Message msg = new Message(msgPushProducer.getProperties().getProperty(MqConstants.TOPIC), MqConstants.TAG,
				SerializeUtil.serialize(dto));
		try {
			logger.info("货源分配失败通知物流公司,分配历史id：" + id);
			logger.info("货源分配失败通知的物流公司memberId：" + memberId);
			msgPushProducer.send(msg);
		} catch (Exception e) {
			logger.error("货源分配失败通知物流公司推送MQ失败", e);
			// 异常数据存入表 mq_error
			this.insertMqError(dto, MqConstants.BIZ_TYPE_1, MqConstants.TOPIC_PUSH, e.getMessage(), "");
		}
	}

	/**
	 * 异常数据存入表 mq_error
	 * 
	 * @param t
	 * @param bizType
	 * @param topic
	 * @param remark
	 * @param httpUrl
	 */
	private <T> void insertMqError(T t, Integer bizType, Integer topic, String remark, String httpUrl) {
		MqError mqError = new MqError();
		mqError.setBizType(bizType);
		mqError.setTopic(topic);
		mqError.setContent(GSONUtils.toJson(t, false));
		mqError.setRemark(remark);
		mqError.setHttpUrl(httpUrl);
		baseDao.execute("MqError.insert", mqError);
	}
}

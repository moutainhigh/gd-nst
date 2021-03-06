package cn.gdeng.nst.server.source.rule;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import cn.gdeng.nst.api.dto.source.RuleDTO;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.api.dto.task.TaskDto;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.SourceAssignHisEntity;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.enums.SourceAssignHisStatusEnum;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 抽象货源分配规则
 * 
 * @author xiaojun
 */
public abstract class SourceAssignRule {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BaseDao<?> baseDao;
	@Autowired
	@Qualifier("msgPushProducer")
	private ProducerBean msgPushProducer;
	@Resource
	private ProducerBean taskProducer;

	/**
	 * 货源分配给物流公司/车主统一操作
	 */
	protected void assignOperation(RuleDTO dto) throws BizException {
		try {
			// 执行分配操作
			baseDao.execute("Rule.updateSourceAssign", dto);
			// 生成分配记录 并获取主键
			Integer sourceAssignHisId = insetSourceAssignHis(dto);
			// 获取推送消息所需要的Id
			Integer id = getPushNeedId(sourceAssignHisId, dto);
			// 获取推送内容
			String msg = getMsgConsByPlatform(dto.getPlatform());
			// 分配成功，给物流公司或车主推送
			msgPushToAssignDetail(id, dto.getAssignMemberId(), msg);
			// 推送30分钟
			assinCountdown(dto);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("货源分配操作失败：==============货源id" + dto.getSourceId());
			}
			logger.error("分配出错了", e);
			throw new BizException(MsgCons.C_27005, MsgCons.M_27005);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("货源分配成功：==============货源id:" + dto.getSourceId());
		}
	}

	/**
	 * 根据平台相关，得到推送内容
	 * 
	 * @param platform
	 * @return
	 */
	private String getMsgConsByPlatform(Integer platform) {
		if (platform == 1) {
			return MsgCons.M_28011;
		}
		return MsgCons.M_28010;
	}

	/**
	 * 根据分配给不同用户，传不同的id 物流公司分配历史id 车主 货源id
	 * 
	 * @param sourceAssignHisId
	 * @param sourceId
	 */
	private Integer getPushNeedId(Integer sourceAssignHisId, RuleDTO dto) {
		// memberType为2表示车主 3 物流公司
		if (dto.getMemberType() == 3) {
			return sourceAssignHisId;
		}
		return dto.getSourceId();
	}

	/**
	 * 插入分配历史表获取主键
	 * 
	 * @param dto
	 */
	private Integer insetSourceAssignHis(RuleDTO dto) {
		SourceAssignHisEntity entity = new SourceAssignHisEntity();
		entity.setSourceId(dto.getSourceId());
		entity.setAssignMemberId(dto.getAssignMemberId());
		entity.setMemberType(dto.getMemberType());
		entity.setRuleType(dto.getRuleType());
		entity.setRuleInfoId(dto.getRuleInfoId());
		entity.setStatus((byte) 1);
		entity.setCreateTime(new Date());
		entity.setIsView(0);
		Long id = baseDao.persist(entity, Long.class);
		Integer sourceAssignHisId = Integer.parseInt(id.toString());
		return sourceAssignHisId;
	}

	/**
	 * 分配成功，给物流公司或车主推送
	 * 
	 * @param beforeId
	 * @param content
	 * @throws BizException
	 */
	public void msgPushToAssignDetail(Integer id, Integer memberId, String content) throws BizException {
		PushMsgDto dto = new PushMsgDto();
		dto.setBizId(id);
		dto.setContent(content);
		dto.setMemberId(memberId);
		dto.setMsgType(PushConstants.MSG_TYPE_4);
		// 货源分配待接收状态
		dto.setBizMap(new HashMap<String, Object>());
		dto.getBizMap().put("status", SourceAssignHisStatusEnum.STAY_FOR_ACCEPTION.getCode());
		Message msg = new Message(msgPushProducer.getProperties().getProperty(MqConstants.TOPIC), MqConstants.TAG,
				SerializeUtil.serialize(dto));
		try {
			logger.info("分配货源成功:{},message:{}", new Object[] {
					DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME), JSON.toJSONString(msg) });
			msgPushProducer.send(msg);
		} catch (Exception e) {
			logger.error("分配货源推送MQ失败", e);
			// 异常数据存入表 mq_error
			this.insertMqError(dto, MqConstants.BIZ_TYPE_1, MqConstants.TOPIC_PUSH);
		}
	}

	/**
	 * 数据推送至队列15分钟倒计时
	 * 
	 * @throws Exception
	 */
	protected void assinCountdown(RuleDTO dto) {
		try {
			final TaskDto taskDto = new TaskDto();
			taskDto.setBizId(dto.getSourceId());
			taskDto.setTaskType(MqConstants.TASK_TYPE_1);
			Message msg = new Message(taskProducer.getProperties().getProperty(MqConstants.TOPIC), MqConstants.TAG,
					SerializeUtil.serialize(taskDto));
			msg.setKey(taskDto.getBizId().toString());
			msg.setStartDeliverTime(System.currentTimeMillis() + 15 * 60 * 1000);
			// 异步发送
			taskProducer.sendAsync(msg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					logger.info(MsgCons.M_27003 + GSONUtils.toJson(sendResult, false));
				}

				@Override
				public void onException(OnExceptionContext context) {
					logger.error(MsgCons.M_27003, context.getException());
					// 异常数据存入表 mq_error
					insertMqError(taskDto, context.getException().getMessage());
				}
			});
		} catch (Exception e) {
			logger.error("", e);
			insertMqError(dto, e.getMessage());
		}
	}

	/**
	 * 异常数据存入表 mq_error
	 * 
	 * @param dto
	 */
	protected void insertMqError(Object dto, String remark) {
		try {
			MqError mqError = new MqError();
			mqError.setBizType(MqConstants.BIZ_TYPE_0);
			mqError.setTopic(MqConstants.TOPIC_ORDER_INFO);
			mqError.setContent(GSONUtils.toJson(dto, false));
			mqError.setCreateUserId(0);
			mqError.setRemark(remark);
			baseDao.execute("MqError.insert", mqError);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 异常数据存入表 mq_error
	 * 
	 * @param dto
	 */
	private <T> void insertMqError(T t, Integer bizType, Integer topic) {
		MqError mqError = new MqError();
		mqError.setBizType(bizType);
		mqError.setTopic(topic);
		mqError.setContent(GSONUtils.toJson(t, false));
		baseDao.execute("MqError.insert", mqError);
	}
}

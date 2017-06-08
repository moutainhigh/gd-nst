package cn.gdeng.nst.server.source.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.dto.source.RuleDTO;
import cn.gdeng.nst.api.dto.source.RuleInfoDTO;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 物流分配规则
 * 
 * @author xiaojun
 */
@Service("logisticsAssignRule")
public class LogisticsAssignRule extends SourceAssignRule implements BaseRuleInterface {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BaseDao<?> baseDao;

	@Resource
	private ProducerBean countProducer;
	/**
	 * 规则分配失败常量
	 */
	private static final boolean RULE_FALSE = false;
	/**
	 * 规则分配成功常量
	 */
	private static final boolean RULE_TRUE = true;
	/**
	 * 车主标识
	 */
	private static final int DRIVERID = 2;
	/**
	 * 物流公司标识
	 */
	private static final int LOGISTICSID = 3;
	/**
	 * 干线标识
	 */
	private static final int TRUNKLINE = 1;
	/**
	 * 同城 标识
	 */
	private static final int SAMECITYID = 2;

	@Override
	public boolean isAssign(RuleDTO dto) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("物流分配开始==============货源id：" + dto.getSourceId());
		}
		// 查询出符合的规则 ,规则相同取最新启用的
		RuleInfoDTO ruleInfoDTO = baseDao.queryForObject("Rule.queryRuleInfo", dto, RuleInfoDTO.class);
		if (ruleInfoDTO == null) {
			if (dto.getSourceType() == 1 || (dto.getSourceType() == 2 && dto.getS_areaId() == null)) {
				logger.info("货源分配不成功,没有符合的规则");
				return RULE_FALSE;
			}
			// 同城货源规则区不匹配，则匹配市一级
			ruleInfoDTO = baseDao.queryForObject("Rule.querySameCityRuleInfo", dto, RuleInfoDTO.class);
			if (ruleInfoDTO == null) {
				logger.info("货源分配不成功,没有符合同城货源匹配市的规则");
				return RULE_FALSE;
			}
		}
		// 设置规则id到货源分配所需DTO中
		dto.setRuleInfoId(ruleInfoDTO.getRuleInfoId());
		// 查询可以分配的公司 如果没有直接返回false
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ruleInfoId", ruleInfoDTO.getRuleInfoId());
		List<Integer> ruleInfoList = baseDao.queryForList("Rule.queryCanAssignMembrIdList", paramMap, Integer.class);
		if (ruleInfoList == null || (ruleInfoList != null && ruleInfoList.size() == 0)) {
			logger.info("货源分配不成功,规则下没有符合的物流公司或车主");
			return RULE_FALSE;
		}
		// 查询出在匹配规则下分配的最后一条记录
		Integer assignMemberId = baseDao.queryForObject("Rule.queryAssignHisLastOne", paramMap, Integer.class);
		// 可以分配公司的最后一条记录
		/*
		 * 一、开始查询出分配历史表中最后一条记录 1.如果最后一条记录不在查询分配的公司集合(ruleInfoList)内,则重新开始轮询
		 * 2.如果最后一条记录在查询分配的公司集合(ruleInfoList)内,但是是集合的最后一条元素,则重新开始轮询
		 * 二、如果最后一条记录在查询分配的公司集合(ruleInfoList)内,但是不是集合的最后一条元素
		 * 1.分配这条记录的在集合内的下一个，到集合最后一条记录，没有分配成功，则分配记录前面的，如果，没有不进行分配(不包含这条记录)
		 */
		int ruleInfoListLastElement = ruleInfoList.get(ruleInfoList.size() - 1);
		if (!ruleInfoList.contains(assignMemberId) || assignMemberId == ruleInfoListLastElement) {
			for (int i = 0; i < ruleInfoList.size(); i++) {
				boolean isAssignSource = assignSource(dto, ruleInfoList, i);
				// 分配成功
				if (isAssignSource) {
					return isAssignSource;
				}
			}
		} else {
			return execute(dto, ruleInfoList, assignMemberId);
		}
		logger.info("货源分配不成功,轮询物流公司和车主不匹配");
		return RULE_FALSE;
	}

	/**
	 * 最后一条记录在查询分配集合的中间
	 * 
	 * @param dto
	 * @param ruleInfoList
	 * @param assignMemberId
	 * @return
	 * @throws BizException
	 */
	private boolean execute(RuleDTO dto, List<Integer> ruleInfoList, int assignMemberId) throws BizException {
		for (int i = ruleInfoList.indexOf(assignMemberId) + 1; i < ruleInfoList.size(); i++) {
			boolean isAssignSource = assignSource(dto, ruleInfoList, i);
			// 分配成功
			if (isAssignSource) {
				return isAssignSource;
			}
			// 如果分配在最后一个，则需要重新匹配前面的
			if (ruleInfoList.size() == (i + 1)) {
				for (int j = 0; j <= ruleInfoList.indexOf(assignMemberId); j++) {
					// 分配成功
					boolean isAssignSource2 = assignSource(dto, ruleInfoList, j);
					if (isAssignSource2) {
						return isAssignSource2;
					}
				}
			}
		}
		return RULE_FALSE;
	}

	/**
	 * 对遍历出来的物流公司或车主进行货源分配
	 * 
	 * @param dto
	 * @param ruleInfoList
	 * @param i
	 * @return
	 * @throws BizException
	 */
	private boolean assignSource(RuleDTO dto, List<Integer> ruleInfoList, int i) throws BizException {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", ruleInfoList.get(i));
		map.put("sourceId", dto.getSourceId());
		map.put("ruleInfoId", dto.getRuleInfoId());
		// 判断日分配额是否满，总分配额是否满，是否开启收货模式，检验车主还是物流公司(注:一个货源只能在一个物流/车主出现一次)
		Integer memberType = baseDao.queryForObject("Rule.queryMemberType", map, Integer.class);
		// 如果没有直接返回不分配
		if (memberType == null) {
			return RULE_FALSE;
		}
		// meberType会员类型2 车主 3 物流公司
		dto.setMemberType(memberType);
		// 如果为车主，则直接分配给车主
		if (memberType == DRIVERID) {
			return executeAssgin(dto, ruleInfoList, i);
		} else if (memberType == LOGISTICSID) {
			// 设置查询订阅线路memberId
			dto.setMemberId(ruleInfoList.get(i));
			// 如果分配发布货源为干线
			if (dto.getSourceType() == TRUNKLINE) {
				// 查询是否有满足的订阅线路
				Integer matchId = baseDao.queryForObject("Rule.querySubscriberLineForTrunkLine", dto, Integer.class);
				// 没有,跳过不分配
				if (matchId != null) {
					return executeAssgin(dto, ruleInfoList, i);
				}
				// 如果分配发布货源为同城
			} else if (dto.getSourceType() == SAMECITYID) {
				// 查询是否有满足的订阅线路
				Integer matchId = baseDao.queryForObject("Rule.querySubscriberLineForSameCity", dto, Integer.class);
				if (matchId != null) {
					return executeAssgin(dto, ruleInfoList, i);
				}
			}
		}
		logger.info("物流公司不匹配,当前分配公司或者车主id:" + ruleInfoList.get(i));
		return RULE_FALSE;
	}

	/**
	 * 执行分配
	 * 
	 * @param dto
	 * @param ruleInfoList
	 * @param i
	 * @return
	 * @throws BizException
	 */
	private boolean executeAssgin(RuleDTO dto, List<Integer> ruleInfoList, int i) throws BizException {
		dto.setAssignMemberId(ruleInfoList.get(i));
		dto.setRuleType(1);
		dto.setSourceGoodsType(2);
		this.assignOperation(dto);
		// 对物流公司/车主日配额和总配额增加
		baseDao.execute("Rule.addRuleLogistics", dto);
		// 推送会员统计
		countAdd(dto.getAssignMemberId());
		return RULE_TRUE;
	}

	/**
	 * 消息队列,推送计算会员总收益(货源分配总数)
	 */
	private void countAdd(Integer memberId) {
		MemberCountDTO mqVo = new MemberCountDTO();
		mqVo.setMemberId(memberId);
		mqVo.setAutoSourceCount(1);
		Message msg = new Message(countProducer.getProperties().getProperty(MqConstants.TOPIC), MqConstants.TAG,
				SerializeUtil.serialize(mqVo));
		msg.setKey(memberId + "'");
		try {
			countProducer.send(msg);
		} catch (Exception e) {
			insertMqError(mqVo, e.getMessage());
		}
	}

}

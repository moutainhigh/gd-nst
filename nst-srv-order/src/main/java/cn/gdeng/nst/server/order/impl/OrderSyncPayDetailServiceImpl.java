package cn.gdeng.nst.server.order.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
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
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.dto.order.OrderAgentDTO;
import cn.gdeng.nst.api.dto.order.OrderInfoDTO;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.api.server.order.OrderSyncPayDetailService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.OrderPaydetailEntity;
import cn.gdeng.nst.entity.nst.PayTradeEntity;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.server.Base64;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 订单同步支付详情服务实现类
 * 
 * @author xiaojun
 *
 */
@Service
public class OrderSyncPayDetailServiceImpl implements OrderSyncPayDetailService, MessageListener {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BaseDao<?> baseDao;
	@Autowired
	@Qualifier("countProducer")
	private ProducerBean countProducer;
	@Autowired
	@Qualifier("msgPushProducer")
	private ProducerBean msgPushProducer;

	@Override
	public Action consume(Message message, ConsumeContext context) {
		PayTradeEntity entity = new PayTradeEntity();
		try {
			String resultStr = SerializeUtil.unserialize(message.getBody()) + "";
			entity = getGson().fromJson(resultStr, PayTradeEntity.class);
			Map<String, String> map = getFromReParam(entity);
			OrderPaydetailEntity payDetailEntity = payToDetailObj(entity, map);
			// 1：信息订单交易详情 2：货源订单交易详情
			if (payDetailEntity.getOrderType() == 1) {
				insertOrderAgentSyncPayDetail(payDetailEntity);
			} else if (payDetailEntity.getOrderType() == 2) {
				// 同步更新运单表支付状态 ,并且计算司机总收益
				insertOrderSyncPayDetail(payDetailEntity, map);
			}
			logger.info("支付成功" + payDetailEntity.getOrderNo());
			return Action.CommitMessage;
		} catch (Exception e) {
			logger.error("message:", JSON.toJSONString(message));
			logger.error(JSON.toJSONString(context), e);
			insertMqError(entity, MqConstants.BIZ_TYPE_0, MqConstants.TOPIC_PAY_DETAIL);
			return Action.ReconsumeLater;
		}
	}

	/**
	 * 支付返回参数 同步到农速通订单支付详情中去
	 * 
	 * @param entity
	 * @return
	 */
	private OrderPaydetailEntity payToDetailObj(PayTradeEntity entity, Map<String, String> reParamMap)
			throws Exception {
		OrderPaydetailEntity paydetailEntity = new OrderPaydetailEntity();
		Map<String, Object> map = new HashMap<>();
		map.put("orderNo", entity.getOrderNo());
		map.put("payStatus", entity.getPayStatus());
		map.put("payType", entity.getPayType());
		map.put("payTime", entity.getPayTime());
		map.put("payMoney", entity.getPayAmt());
		// 订单类型
		map.put("orderType", reParamMap.get("orderType"));
		map.put("platformPayWater", entity.getPayCenterNumber());
		map.put("thirdPartyPayWater", entity.getThirdPayNumber());
		map.put("payAccountNo", entity.getPayerAccount());
		map.put("payName", entity.getPayerName());
		map.put("receiptNo", entity.getPayeeAccount());
		map.put("receiptName", entity.getPayeeName());
		map.put("createUserId", "admin");
		paydetailEntity = getGson().fromJson(GSONUtils.toJson(map, true),
				OrderPaydetailEntity.class);
		return paydetailEntity;
	}

	/**
	 * 在公共回传参数中封装到map 获取订单类型(1 信息 2 货运) 货运的时候获取driverMemberId
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getFromReParam(PayTradeEntity entity) throws Exception {
		String reParam = entity.getReParam();
		Object reParamObject = SerializeUtil.unserialize(Base64.decode(reParam));
		return GSONUtils.fromJsonToMapStr(reParamObject.toString());
	}

	@Override
	@Transactional
	public void insertOrderSyncPayDetail(OrderPaydetailEntity entity, Map<String, String> map) throws Exception {
		entity.setCreateTime(new Date());
		Long status = baseDao.persist(entity, Long.class);
		// 如果插入支付详情成功,则修改订单的支付转态
		if (status > 0) {
			logger.info("订单支付流水插入成功" + entity.getOrderNo());
			Map<String, Object> param = new HashMap<>();
			param.put("payStatus", entity.getPayStatus());
			param.put("orderNo", entity.getOrderNo());
			param.put("transportAmt", entity.getPayMoney());
			baseDao.execute("OrderPayDetail.updateOrderPayStatus", param);
		}
		logger.info("货运订单状态更改成功" + entity.getOrderNo());
		// 查询beforeId
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderNo", entity.getOrderNo());
		OrderInfoDTO orderInfo = baseDao.queryForObject("OrderInfo.getOrderBeforeIdByOrderNo", paramMap,
				OrderInfoDTO.class);
		// 推送支付成功消息
		if (orderInfo != null) {
			msgPushToPayOrderDetail(orderInfo.getOrderBeforeId(), orderInfo.getDriverMemberId(), MsgCons.M_28003);
		}
		// 处理司机总收益 MQ 生产者
		map.put("payAmt", entity.getPayMoney().toString());
		sendRevenueCount(map);
		recordSourceLog(map);
	}

	@Override
	@Transactional
	public void insertOrderAgentSyncPayDetail(OrderPaydetailEntity entity) throws Exception {
		entity.setCreateTime(new Date());
		Long status = baseDao.persist(entity, Long.class);
		// 如果插入支付详情成功,则修改订单的支付转态
		if (status > 0) {
			logger.info("订单支付流水插入成功" + entity.getOrderNo());
			Map<String, Object> param = new HashMap<>();
			param.put("payStatus", entity.getPayStatus());
			param.put("orderNo", entity.getOrderNo());
			param.put("infoAmt", entity.getPayMoney());
			baseDao.execute("OrderPayDetail.updateOrderAgentPayStatus", param);
		}
		logger.info("信息订单状态更改成功" + entity.getOrderNo());
		// 查询beforeId
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderNo", entity.getOrderNo());
		OrderAgentDTO orderAgent = baseDao.queryForObject("OrderAgent.getOrderBeforeIdByOrderNo", paramMap,
				OrderAgentDTO.class);
		// 推送支付成功消息
		if (orderAgent != null) {
			msgPushToPayOrderDetail(orderAgent.getOrderBeforeId(), orderAgent.getLogisticMemberId(), MsgCons.M_28004);
		}
	}

	/**
	 * 记录货源日志 货主已经支付运费
	 * 
	 * @param map
	 */
	private void recordSourceLog(Map<String, String> map) {
		map.put("description", "我已支付运费");
		map.put("createUserId", map.get("shipperMemberId"));
		baseDao.execute("SourceLog.insert", map);
		logger.info("货源日志记录成功");
	}

	/**
	 * 发送MQ 计算司机总收益
	 * 
	 * @param sourceId
	 */
	private void sendRevenueCount(Map<String, String> map) throws BizException {
		MemberCountDTO mqVo = new MemberCountDTO();
		mqVo.setMemberId(Integer.parseInt(map.get("driverMemberId")));
		mqVo.setDriverIcome(Double.parseDouble(map.get("payAmt")));
		Message msg = new Message(countProducer.getProperties().getProperty(MqConstants.TOPIC), MqConstants.TAG,
				SerializeUtil.serialize(mqVo));
		// msg.setKey(mqVo.getMemberId().toString());
		SendResult sendResult = null;
		try {
			sendResult = countProducer.send(msg);
			logger.info("计算司机总收益发送时间:{},message:{},context:{}",
					new Object[] { DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),
							JSON.toJSONString(msg), JSON.toJSONString(sendResult) });
		} catch (Exception e) {
			logger.error("计算司机总收益MQ error", e);
			// 异常数据存入表 mq_error
			insertMqError(mqVo, MqConstants.BIZ_TYPE_1, MqConstants.TOPIC_ORDER_INFO);
		}
	}

	/**
	 * 推送支付成功消息
	 * 
	 * @param beforeId
	 * @param content
	 * @throws BizException
	 */
	public void msgPushToPayOrderDetail(Integer beforeId, Integer memberId, String content) throws BizException {
		PushMsgDto dto = new PushMsgDto();
		dto.setBizId(beforeId);
		dto.setContent(content);
		dto.setMemberId(memberId);
		dto.setMsgType(PushConstants.MSG_TYPE_0);
		Message msg = new Message(msgPushProducer.getProperties().getProperty(MqConstants.TOPIC), MqConstants.TAG,
				SerializeUtil.serialize(dto));
		try {
			logger.info("支付费用成功:{},message:{}", new Object[] {
					DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME), JSON.toJSONString(msg) });
			msgPushProducer.send(msg);
		} catch (Exception e) {
			logger.error("支付费用推送MQ失败", e);
			// 异常数据存入表 mq_error
			this.insertMqError(dto, MqConstants.BIZ_TYPE_1, MqConstants.TOPIC_PUSH);
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
	/**
	 * 获取gson
	 * @return
	 */
	private Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		return gsonBuilder.create();
	}
}

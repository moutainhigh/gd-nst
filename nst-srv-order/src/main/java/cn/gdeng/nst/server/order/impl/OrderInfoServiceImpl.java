package cn.gdeng.nst.server.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.dto.order.OrderInfoDTO;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.api.server.order.OrderInfoService;
import cn.gdeng.nst.api.vo.order.OrderInfoDetailVo;
import cn.gdeng.nst.api.vo.order.OrderInfoPageVo;
import cn.gdeng.nst.api.vo.order.OrderOperateLogVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.OrderBeforeEntity;
import cn.gdeng.nst.entity.nst.OrderInfoEntity;
import cn.gdeng.nst.entity.nst.SourceLogEntity;
import cn.gdeng.nst.entity.nst.SourceShipperEntity;
import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.NstRuleEnum;
import cn.gdeng.nst.enums.OperateEnum;
import cn.gdeng.nst.enums.OrderAgentStatusEnum;
import cn.gdeng.nst.enums.OrderBeforeEnum;
import cn.gdeng.nst.enums.OrderBeforeSourceStatusEnum;
import cn.gdeng.nst.enums.OrderInfoStatusEnum;
import cn.gdeng.nst.enums.OrderInfoTransStatusEnum;
import cn.gdeng.nst.enums.PayStatusEnum;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.enums.SourceStatusEnum;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.server.AddrUtils;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;
import cn.gdeng.nst.util.web.api.SerializeUtil;

/**
 * 货主实现类ServiceImpl
 * @author huangjianhua  2017年1月10日  上午11:18:07
 * @version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	@Resource
	private ProducerBean countProducer;
	@Resource
	private ProducerBean msgPushProducer;
	
	@Override
	public ApiResult<ApiPage> queryOrderInfoPage(ApiPage page) throws BizException {
		ApiResult<ApiPage> apiResult=new ApiResult<>();
		int total=baseDao.queryForObject("OrderInfo.getTotal",page.getParaMap(), Integer.class);
		if(total==0){
			apiResult.setResult(new ApiPage().setRecordList(new ArrayList<Object>()));
			return apiResult;
		}
		List<OrderInfoPageVo> pageVoList=baseDao.queryForList("OrderInfo.queryByConditionPage", page.getParaMap(),OrderInfoPageVo.class);
		//生成全地址
		try {
			generalFullAddrAndSet(pageVoList);
		} catch (Exception e) {
			logger.error("查询订单,地址转换异常:{}", e);
			throw new BizException(MsgCons.C_20000, MsgCons.M_20000);
		}
		//将结果封装到分页对象
		page.setRecordList(pageVoList).setRecordCount(total);
		//将分页封装到返回结果
		apiResult.setResult(page);
		return apiResult;
	}
	
	/**生成详细的全地址，并set到list的元素中。<br/>
	 * NOTE:如果地址包含省份，去除该省份。
	 * @param list
	 * @throws Exception 
	 */
	private void generalFullAddrAndSet(List<OrderInfoPageVo> list) throws Exception {
		for (OrderInfoPageVo srcShi : list) {
			AddrUtils.generalFullAddrAndSet(srcShi);
		}
	}
	
	@Override
	@Transactional
	public ApiResult<Integer> confirmOrderInfo(OrderInfoDTO dto) throws BizException{
		ApiResult<Integer> apiResult=new ApiResult<>();
		Map<String, Object> orderInfoMap=new HashMap<>();
		orderInfoMap.put("sourceId",dto.getSourceId() );
		orderInfoMap.put("orderBeforeId",dto.getOrderBeforeId());
		//查询运单信息 是否已经确认收货
		OrderInfoEntity orderInfoEntity=baseDao.queryForObject("OrderInfo.queryOrderInfoBySourceIdOrOrderBeforeId", orderInfoMap, OrderInfoEntity.class);
		if (orderInfoEntity.getOrderStatus().compareTo(OrderInfoStatusEnum.GOODS_CONFIRM.getCode())==0) {
			throw new BizException(MsgCons.C_24027, MsgCons.M_24027);
		}
		//step1 更新运单状态"确认收货" 
		dto.setOrderStatus(OrderInfoStatusEnum.GOODS_CONFIRM.getCode());
		dto.setConfirmGoodsTime(DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME));
		//6+1 需求修改物流状态为  "车主已送达"
		dto.setTransStatus(OrderInfoTransStatusEnum.GOOD_ARRIVED.getCode());
		if (baseDao.execute("OrderInfo.update", dto)!=1) {
			throw new BizException(MsgCons.C_24028, MsgCons.M_24028);
		}
		//step2 更新预运单状态"已完成"
		this.updateOrderBeforeStatus(dto.getOrderBeforeId(), OrderBeforeEnum.COMPLETEORDER.getCode());
		//step3 记录日志   是否超时处理备注
		if(StringUtils.isNotBlank(dto.getIsHandleTimeout())){
			this.saveOperateLogger(dto.getSourceId(), OperateEnum.GOODSOWNERCONFIRMTIMEOUT.getName());
		}else{
			this.saveOperateLogger(dto.getSourceId(), OperateEnum.GOODSOWNERCONFIRM.getName());
		}
		//发送MQ统计司机接单数
		this.sendDriverOrderReceiveCount(orderInfoEntity.getDriverMemberId());
		//推送消息给车主
		msgPushToDriver(dto.getOrderBeforeId(), orderInfoEntity.getDriverMemberId(),MsgCons.M_28002);
		return apiResult;
	}
	@Override
	@Transactional
	public ApiResult<Integer> createOrderInfo(OrderInfoDTO dto) throws BizException{
		ApiResult<Integer> apiResult=new ApiResult<>();
		//step1 创建运单信息
		SourceShipperEntity entity=querySourceShipperById(dto.getSourceId());
		String orderNo=generateOrderNo(entity);
		dto.setOrderNo(orderNo);
		dto.setOrderStatus(OrderInfoStatusEnum.ORDER_CONFIRM.getCode());
		//查询预订单 判断是否为已接单状态
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id",dto.getOrderBeforeId());
		OrderBeforeEntity orderBeforeEntity=baseDao.queryForObject("OrderBefore.queryOrderBeforeById", paramMap, OrderBeforeEntity.class);
		if(orderBeforeEntity.getSourceStatus().compareTo(OrderBeforeSourceStatusEnum.RECEIVEORDER.getCode())!=0){
			throw new BizException(MsgCons.C_24035, MsgCons.M_24035);
		}
		dto.setDriverMemberId(orderBeforeEntity.getDriverMemberId());
		dto.setCarId(orderBeforeEntity.getCarId());
		dto.setShipperMemberId(orderBeforeEntity.getShipperMemberId());
		/**6+1项目  新增物流状态   已发货  */
		dto.setTransStatus(OrderInfoTransStatusEnum.SENT.getCode());
		if (entity.getNstRule().compareTo(NstRuleEnum.SELFLOGST.getCode())!=0) {
			//如果是物流公司直发的，不生产货运订单
			int temp=baseDao.execute("OrderInfo.insert", dto);
			if (temp!=1) {
				throw new BizException(MsgCons.C_23012, MsgCons.M_23012);
			}
		}
		if (entity.getNstRule().compareTo(NstRuleEnum.SELFLOGST.getCode())==0) {
			//step2    物流公司直发的  直接更改为已完成   更新预运单状态 "已完成"
			this.updateOrderBeforeStatus(dto.getOrderBeforeId(), OrderBeforeEnum.COMPLETEORDER.getCode());
		}else{
			//step2 更新预运单状态 "已生成订单"
			this.updateOrderBeforeStatus(dto.getOrderBeforeId(), OrderBeforeEnum.GENERATEORDER.getCode());
		}
		
		//step3 更新货源状态 "已接受"
		this.updateSourceShipperStatus(dto.getSourceId(),SourceStatusEnum.ACCEPTED.getCode());
		//step4 更新信息订单状态 "已确认"
		this.updateOrderAgentStatus(dto.getSourceId(),OrderAgentStatusEnum.CONFIRMED.getCode(), dto.getOrderBeforeId());
		
		//step5 记录接受日志
		String sourceLogDesc = null;
		if (entity.getAssignMemberId() == null && entity.getNstRule() == 1) {
			sourceLogDesc = OperateEnum.GOODSOWNERRECEIVE.getName();
		} else {
			sourceLogDesc = OperateEnum.LOGISTICSRECEIVE.getName();
		}
		this.saveOperateLogger(dto.getSourceId(), sourceLogDesc);
		
		String pushMsg=MsgCons.M_28009;
		//step6 mq推送
		if (entity.getNstRule().compareTo(NstRuleEnum.SELFLOGST.getCode())==0) {
			//物流公司直发    推送司机订单总数，推送物流公司总接单数
			this.sendOrderReceiveCount(entity.getMemberId());
			this.sendDriverOrderReceiveCount(dto.getDriverMemberId());
		}else if (entity.getNstRule().compareTo(NstRuleEnum.DISTRIBUTED.getCode())==0) {
			//代发
			this.sendOrderReceiveCount(entity.getAssignMemberId());
			msgPushToShipper(entity.getId(),entity.getMemberId(),MsgCons.M_28006);
		}
		//货主直发接受订单
		else if(entity.getNstRule().compareTo(NstRuleEnum.SELF.getCode())==0){
			pushMsg=MsgCons.M_28001;
		}
		//推送消息给车主
		msgPushToDriver(dto.getOrderBeforeId(),dto.getDriverMemberId(),pushMsg);
		return apiResult;
	}
	@Override
	@Transactional
	public ApiResult<Integer> refuseOrderInfo(OrderInfoDTO dto) throws BizException{
		ApiResult<Integer> apiResult=new ApiResult<>();
		//step1 更新预运单状态"已关闭"
		this.updateOrderBeforeStatus(dto.getOrderBeforeId(), OrderBeforeEnum.CLOSEORDER.getCode());
		//step2 更新货源状态"已发布"
		this.updateSourceShipperStatus(dto.getSourceId(),SourceStatusEnum.RELEASED.getCode());
		//step3 更新信息订单状态 "已关闭"
		boolean result= this.updateOrderAgentStatus(dto.getSourceId(),OrderAgentStatusEnum.COMPANY_CANCEL.getCode(),dto.getOrderBeforeId());
		//step4 记录拒绝日志
		if(result){
			this.saveOperateLogger(dto.getSourceId(), OperateEnum.LOGISTICSREFUSE.getName());
		}else{
			this.saveOperateLogger(dto.getSourceId(), OperateEnum.GOODSOWNERREFUSE.getName());
		}
		return apiResult;
	}
	@Override
	public ApiResult<List<OrderInfoPageVo>> queryOrderInfoList(Map<String, Object> map) throws BizException {
		ApiResult<List<OrderInfoPageVo>> apiResult=new ApiResult<>();
		List<OrderInfoPageVo> orderInfoPageVo=baseDao.queryForList("OrderInfo.queryOrderInfoList", map,OrderInfoPageVo.class);
		apiResult.setResult(orderInfoPageVo);
		return apiResult;
	}
	@Override
	public ApiResult<Integer> orderReceiveTimeOut(Map<String, Object> map) throws BizException {
		ApiResult<Integer> apiResult=new ApiResult<>();
		//step1 查询订单是否已经被接受
		OrderBeforeEntity entity=baseDao.queryForObject("OrderBefore.queryOrderBeforeById", map, OrderBeforeEntity.class);
		//只针对当前处于“接单”的状态，才进行超时处理
		if(entity.getSourceStatus() != OrderBeforeEnum.RECEIVEORDER.getCode()){
			return apiResult;
		}
		//step3 更新预运单状态"已超时"
		this.updateOrderBeforeStatus(entity.getId(), OrderBeforeEnum.TIMEOUT.getCode());
		//step4 更新货源状态"已发布"
		this.updateSourceShipperStatus(entity.getSourceId(),SourceStatusEnum.RELEASED.getCode());
		//step5 更新信息订单状态 "已关闭"
		boolean result= this.updateOrderAgentStatus(entity.getSourceId(),OrderAgentStatusEnum.COMPLETED.getCode(), entity.getId());
		//step5 记录超时日志
		if(result){
			this.saveOperateLogger(entity.getSourceId(), OperateEnum.LOGISTICSRECEIVETIMEOUT.getName());
		}else{
			this.saveOperateLogger(entity.getSourceId(), OperateEnum.GOODSOWNERRECEIVETIMEOUT.getName());
		}
		return apiResult;
	}
	/**
	 * 更新货源状态
	 * @param id
	 * @param status
	 * @throws BizException 
	 * @throws Exception
	 */
	private void updateSourceShipperStatus(Integer sourceId,Byte status) throws BizException {
		SourceShipperEntity entity=this.querySourceShipperById(sourceId);
		//step1 修改货源状态
		Map<String, Object> map=new HashMap<>();
		map.put("id",  sourceId);
		map.put("sourceStatus",  status);
		map.put("version",  entity.getVersion());
		int temp=baseDao.execute("OrderBefore.updateSourceShipperStatus", map);
		if (temp!=1) {
			throw new BizException(MsgCons.C_23011, MsgCons.M_23011);
		}
	}
	
	/**
	 * 修改预运单状态信息
	 * @param orderBeforeId 订单id
	 * @param status 状态
	 * @throws Exception
	 */
	private void updateOrderBeforeStatus(Integer orderBeforeId,Byte status) throws BizException{
		//step1 修改预订单状态
		Map<String, Object> map=new HashMap<>(2);
		map.put("id",  orderBeforeId);
		map.put("sourceStatus",  status);
		int record = baseDao.execute("OrderInfo.updateOrderBeforeStatus", map);
		if (record !=1) {
			throw new BizException(MsgCons.C_23008, MsgCons.M_23008);
		}
	}
	
	/**
	 * 更新信息订单状态
 	 * @param sourceId   货源id 
 	 * @param status 状态
	 * @param orderBeforeId 预订单id
	 * @throws BizException 
	 */
	private boolean updateOrderAgentStatus(Integer sourceId,Byte status, Integer orderBeforeId) throws BizException {
			SourceShipperEntity entity=this.querySourceShipperById(sourceId);
			if(entity.getNstRule().compareTo(NstRuleEnum.DISTRIBUTED.getCode())==0
					||entity.getNstRule().compareTo(NstRuleEnum.SELFLOGST.getCode())==0){
				//step1 更新信息订单状态
				Map<String, Object> mapMsg=new HashMap<>();
				mapMsg.put("sourceId",  sourceId);
				mapMsg.put("orderStatus",  status);
				mapMsg.put("orderBeforeId",  orderBeforeId);
				mapMsg.put("logisticTime", ParamProcessUtil.getNewDate());
			int temp=	baseDao.execute("OrderAgent.update", mapMsg);
			if (temp!=1) {
				throw new BizException(MsgCons.C_23011, MsgCons.M_23011);
			}
				return true;
			}
			return false;
	}
	
	/**
	 * 判断是否是物流公司
	 * @param sourceId
	 * @return
	 */
	private SourceShipperEntity querySourceShipperById(Integer sourceId)throws BizException{
		Map<String, Integer> map=new HashMap<>();
		map.put("id",  sourceId);
		SourceShipperEntity entity=baseDao.queryForObject("OrderBefore.querySourceShipper", map, SourceShipperEntity.class);
		if(entity==null){
			logger.info("查询货源异常:sourceId={}",sourceId);
			throw new BizException(MsgCons.C_24010, MsgCons.M_24010);
		}
		return entity;
	}
	
	/**
	 * 记录操作日志
	 * @param sourceId
	 * @param desc
	 */
	private void saveOperateLogger(Integer sourceId, String desc){
		SourceLogEntity entity = new SourceLogEntity();
		entity.setSourceId(sourceId);
		entity.setDescription(desc);
		entity.setCreateTime(new Date());
		baseDao.persist(entity, Integer.class);
	}
	
	/**
	 * 生成运单订单号
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	private String generateOrderNo(SourceShipperEntity entity) throws BizException{
		Integer sequence = baseDao.queryForObject("OrderInfo.getNstOrderNo", null, Integer.class);
		if(sequence == null){
			throw new BizException(MsgCons.C_23003, MsgCons.M_23003);
		}
		StringBuilder sb = new StringBuilder();
		//1.出发地省会Id
		sb.append(entity.getSProvinceId().toString().substring(0, 2)); 
		
		//2.年月日
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		sb.append(sf.format(new Date()));
		
		//3.目的地省会Id
		sb.append(entity.getEProvinceId().toString().substring(0, 2)); 
		
		//4.六位的自增序列
		String seq = String.valueOf(sequence);
		for(int i = 0; i < 6 - seq.length(); i++){
				sb.append("0");
		}
		sb.append(seq);
		return sb.toString();
	}

	/**
	 * 发送MQ 
	 * 货主确认收货或接受订单，推送消息给车主
	 * @param bizId
	 * @param memberId   
	 * @param content 信息内容
	 * @param logMsg  日志内容
	 * @throws BizException
	 */
	private void msgPushToDriver(Integer bizId, Integer memberId,String content)throws BizException {
		PushMsgDto dto=new PushMsgDto();
		dto.setBizId(bizId);
		dto.setContent(content);
		dto.setMemberId(memberId);
		dto.setMsgType(PushConstants.MSG_TYPE_0);
		
		Message msg = new Message(msgPushProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(dto));
		msg.setKey(dto.getMemberId().toString());
		try {
			logger.info(content+",推送消息给车主MQ发送时间:{},message:{}",new Object[]{DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),JSON.toJSONString(msg)});
			msgPushProducer.send(msg);
		} catch (Exception e) {
			logger.error(content+",推送消息给车主MQ error",e);
			//异常数据存入表 mq_error
			this.insertMqError(dto,memberId,MqConstants.TOPIC_PUSH);
		}
	}
	
	/**
	 * 发送MQ 
	 * 物流公司接受分配的货，推送消息给货主
	 * @param sourceId
	 */
	private void msgPushToShipper(Integer bizId, Integer memberId,String content)throws BizException {
		PushMsgDto dto=new PushMsgDto();
		dto.setBizId(bizId);
		dto.setContent(content);
		dto.setMemberId(memberId);
		dto.setMsgType(PushConstants.MSG_TYPE_0);
		
		Message msg = new Message(msgPushProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(dto));
		msg.setKey(dto.getMemberId().toString());
		try {
			logger.info("物流公司接受订单，推送消息给货主MQ发送时间:{},message:{},bizId:{},memberId:{}",new Object[]{DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),JSON.toJSONString(msg),bizId,memberId});
			msgPushProducer.send(msg);
		} catch (Exception e) {
			logger.error("物流公司接受订单，推送消息给货主MQ error",e);
			//异常数据存入表 mq_error
			this.insertMqError(dto,memberId, MqConstants.TOPIC_PUSH);
		}
	}
		
	/**
	 * 发送MQ 给物流公司统计接单次数
	 * @param sourceId
	 */
	private void sendOrderReceiveCount(Integer memberId)throws BizException {
		if(null == memberId){
			return;
		}
		MemberCountDTO mqVo=new MemberCountDTO();
		mqVo.setMemberId(memberId);
		mqVo.setConfirmSourceCount(1);
		Message msg = new Message(countProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(mqVo));
		msg.setKey(mqVo.getMemberId().toString());
		try {
			logger.info("接受订单物流公司统计次数MQ发送时间:{},memberId:{},message:{}",new Object[]{DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),memberId,JSON.toJSONString(msg)});
			countProducer.send(msg);
		} catch (Exception e) {
			logger.error("接受订单物流公司统计次数MQ error",e);
			//异常数据存入表 mq_error
			this.insertMqError(mqVo,memberId,MqConstants.TOPIC_ORDER_INFO);
		}
	}
	
	/**
	 * 发送MQ 车主统计接单数
	 * @param sourceId
	 */
	private void sendDriverOrderReceiveCount(Integer memberId)throws BizException {
		if(null == memberId){
			return;
		}
		MemberCountDTO mqVo=new MemberCountDTO();
		mqVo.setMemberId(memberId);
		mqVo.setDriverOrderCount(1);
		Message msg = new Message(countProducer.getProperties().getProperty(MqConstants.TOPIC),MqConstants.TAG,SerializeUtil.serialize(mqVo));
		msg.setKey(mqVo.getMemberId().toString());
		try {
			logger.info("接受订单车主统计次数MQ发送时间:{},message:{},memberId:{}",new Object[]{DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_DATETIME),JSON.toJSONString(msg),memberId});
			countProducer.send(msg);
		} catch (Exception e) {
			logger.error("接受订单车主统计次数MQ error",e);
			//异常数据存入表 mq_error
			this.insertMqError(mqVo,memberId,MqConstants.TOPIC_ORDER_INFO);
		}
	}
	
	/**
	 * 异常数据存入表 mq_error
	 * @param dto
	 */
	private void insertMqError(Object dto,Integer memberId,Integer topic){
		MqError mqError = new MqError();
		mqError.setBizType(MqConstants.BIZ_TYPE_1);
		mqError.setTopic(topic);
		mqError.setMemberId(memberId);
		mqError.setContent(GSONUtils.toJson(dto,false));
		mqError.setCreateUserId(1);
		baseDao.execute("MqError.insert", mqError);
	}

	@Override
	public ApiResult<OrderInfoDetailVo> queryOrderInfo(Map<String, Object> map) throws BizException {
		ApiResult<OrderInfoDetailVo> result = new ApiResult<OrderInfoDetailVo>();
		OrderInfoDetailVo vo = baseDao.queryForObject("SourceShipper.querySourceById",map,OrderInfoDetailVo.class);
		List<OrderOperateLogVo> operateLogVo= queryOrderOperateLog(map);
		vo.setOperateLogVo(operateLogVo);
		return result.setResult(convertDic(vo));
	}
	
	/**
	 * 订单操作日志信息
	 * @param map
	 */
	private List<OrderOperateLogVo> queryOrderOperateLog(Map<String, Object> map){
		List<OrderOperateLogVo> orderOperateLogVo=null;
		if(map.containsKey("needLog")){
			orderOperateLogVo = baseDao.queryForList("OrderBefore.queryOrderOperateLog", map,OrderOperateLogVo.class);
		}
		return orderOperateLogVo;
	}
	
	//字典数据中文转换
	private OrderInfoDetailVo convertDic(OrderInfoDetailVo vo) throws BizException {
		if(null != vo.getCarType()){
			vo.setCarTypeStr(CarTypeEnum.getNameByCode(vo.getCarType().byteValue()));
		}
		if(null != vo.getDriverCarType()){
			vo.setDriverCarTypeStr(CarTypeEnum.getNameByCode(vo.getDriverCarType().byteValue()));
		}
		if(null != vo.getDriverCerStatus()){
			vo.setDriverCerStatusStr(MemberCerStatusEnum.getNameByCode(vo.getDriverCerStatus().byteValue()));
		}
		if(null != vo.getGoodsType()){
			vo.setGoodsTypeStr(GoodsTypeEnum.getNameByCode(vo.getGoodsType().byteValue()));
		}
		if(null != vo.getOrderAgentPayStatus()){
			vo.setOrderAgentPayStatusStr(PayStatusEnum.getNameByCode(vo.getOrderAgentPayStatus().shortValue()));
		}
		if(null != vo.getOrderAgentStatus()){
			vo.setOrderAgentStatusStr(OrderAgentStatusEnum.getNameByCode(vo.getOrderAgentStatus().byteValue()));
		}
		if(null != vo.getOrderInfoPayStatus()){
			vo.setOrderInfoPayStatusStr(PayStatusEnum.getNameByCode(vo.getOrderInfoPayStatus().shortValue()));
		}
		if(null != vo.getOrderInfoStatus()){
			vo.setOrderInfoStatusStr(OrderInfoStatusEnum.getNameByCode(vo.getOrderInfoStatus().byteValue()));
		}  
		if(null != vo.getSendGoodsType()){
			vo.setSendGoodsTypeStr(SendGoodsTypeEnum.getNameByCode(vo.getSendGoodsType().byteValue()));
		}
		try {
			AddrUtils.generalFullAddrAndSet(vo);
		} catch (Exception e) {
			logger.error("", e);
			throw new BizException(MsgCons.C_20000, MsgCons.M_20000);
		}
		return vo;
	}
}

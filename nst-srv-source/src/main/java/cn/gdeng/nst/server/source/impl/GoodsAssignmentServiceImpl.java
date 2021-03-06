package cn.gdeng.nst.server.source.impl;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.dto.source.SourceAssignHisDetailDTO;
import cn.gdeng.nst.api.dto.source.SourceAssignHisListDTO;
import cn.gdeng.nst.api.server.source.GoodsAssignmentService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberCarEntity;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.entity.nst.MqError;
import cn.gdeng.nst.entity.nst.OrderBeforeEntity;
import cn.gdeng.nst.entity.nst.OrderInfoEntity;
import cn.gdeng.nst.entity.nst.OrderInfoTransEntity;
import cn.gdeng.nst.entity.nst.SourceLogEntity;
import cn.gdeng.nst.entity.nst.SourceShipperEntity;
import cn.gdeng.nst.enums.MqConstants;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.OperateEnum;
import cn.gdeng.nst.enums.OrderBeforeEnum;
import cn.gdeng.nst.enums.OrderInfoPrePayStatus;
import cn.gdeng.nst.enums.OrderInfoStatusEnum;
import cn.gdeng.nst.enums.OrderInfoTransStatusEnum;
import cn.gdeng.nst.enums.SourceAssignHisStatusEnum;
import cn.gdeng.nst.enums.SourceStatusEnum;
import cn.gdeng.nst.server.source.mq.GoodsProvidereMQServie;
import cn.gdeng.nst.util.server.AddrUtils;
import cn.gdeng.nst.util.server.InterfaceUtil;
import cn.gdeng.nst.util.server.InterfaceUtils;
import cn.gdeng.nst.util.server.ReflectionUtils;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Constant;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.GdProperties;

@Service
public class GoodsAssignmentServiceImpl implements GoodsAssignmentService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	@Resource
	private GoodsProvidereMQServie goodsProvidereMQServie;
	@Autowired
	private InterfaceUtil interfaceUtil;
	@Autowired
	private GdProperties gdProperties;

	@SuppressWarnings("unchecked")
	@Override
	public ApiResult<ApiPage> queryMyAssginmentGoodsByPage(ApiPage page) throws BizException {
		ApiResult<ApiPage> apiResult = new ApiResult<ApiPage>();
		// 获取结果集总数
		long total = baseDao.queryForObject("SourceAssignHis.countMyAssginmentGoods", page.getParaMap(), Long.class);
		// 获取结果集
		List<SourceAssignHisListDTO> list;
		if (total > 0) {
			list = baseDao.queryForList("SourceAssignHis.queryMyAssginmentGoodsByPage", page.getParaMap(),
					SourceAssignHisListDTO.class);

			try {
				// 生成详细的全地址
				generalFullAddrAndSet(list);
			} catch (Exception e) {
				logger.error("", e);
				throw new BizException(MsgCons.C_20000, MsgCons.M_20000);
			}

		} else {
			list = Collections.EMPTY_LIST;
		}
		// 将结果封装到分页对象中
		page.setRecordList(list).setRecordCount(total);
		logger.debug("queryMyAssginmentGoodsByPage查询成功！");
		return apiResult.setResult(page);
	}

	/**
	 * 生成详细的全地址，并set到list的元素中。<br/>
	 * NOTE:如果地址包含省份，去除该省份。
	 * 
	 * @param list
	 * @throws Exception
	 */
	private void generalFullAddrAndSet(List<SourceAssignHisListDTO> list) throws Exception {
		for (SourceAssignHisListDTO srcAssHis : list) {
			generalFullAddrAndSet(srcAssHis);
		}
	}

	/**
	 * 生成详细的全地址，并set到参数的元素中。<br/>
	 * NOTE:如果地址包含省份，去除该省份。
	 * 
	 * @param list
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private void generalFullAddrAndSet(Object obj) throws Exception {
		if (obj == null) {
			return;
		}
		// =====设置起始地=====
		String srcFullAddr = "";
		String sDetailVal = (String) ReflectionUtils.invokeMethod(obj, "getSourceSDetail");
		if (StringUtils.isNotBlank(sDetailVal)) {
			srcFullAddr = AddrUtils.ridProvinceAndSpliceAddr(sDetailVal, "/");

		}
		String sDetailAddrVal = (String) ReflectionUtils.invokeMethod(obj, "getSourceSDetailAddr");
		if (StringUtils.isNotBlank(sDetailAddrVal)) {
			srcFullAddr += sDetailAddrVal;
		}
		// set到对象属性中
		ReflectionUtils.invokeMethod(obj, "setSrcFullAddr", srcFullAddr);

		// =====设置目的地====
		String desFullAddr = "";
		String eDetailVal = (String) ReflectionUtils.invokeMethod(obj, "getSourceEDetail");
		if (StringUtils.isNotBlank(eDetailVal)) {
			desFullAddr = AddrUtils.ridProvinceAndSpliceAddr(eDetailVal, "/");
		}
		String eDetailAddrVal = (String) ReflectionUtils.invokeMethod(obj, "getSourceEDetailAddr");
		if (StringUtils.isNotBlank(eDetailAddrVal)) {
			desFullAddr += eDetailAddrVal;
		}
		// set到对象属性中
		ReflectionUtils.invokeMethod(obj, "setDesFullAddr", desFullAddr);
	}

	@Override
	@Transactional
	public ApiResult<Map<String, Object>> acceptAssigned(Map<String, Object> paramMap) throws BizException {
		// 设置货源为代发规则。
		paramMap.put("nstRule", 2);
		// 设置为接受分配
		paramMap.put("assignStatus", 2);

		// ===根据id查询货源===
		Integer sourceShipperId = Integer.valueOf(paramMap.get("sourceShipperId").toString());
		SourceShipperEntity paramEntity = new SourceShipperEntity();
		paramEntity.setId(sourceShipperId);
		SourceShipperEntity resultEntity = baseDao.find(SourceShipperEntity.class, paramEntity);
		if (resultEntity == null) {
			throw new BizException(MsgCons.C_24010, MsgCons.M_24010);
		}

		// ===更新货源和分配历史表状态===
		int ssRecord = baseDao.execute("SourceShipper.updateNstRuleById", paramMap);
		if (ssRecord == 0) {
			throw new BizException(MsgCons.C_24011, MsgCons.M_24011);
		}
		int sahRecord = baseDao.execute("SourceAssignHis.updateStatusById", paramMap);
		if (sahRecord == 0) {
			throw new BizException(MsgCons.C_24011, MsgCons.M_24011);
		}

		// ===保存货源日志=====
		String updateUserId = paramMap.get("updateUserId").toString();
		saveLog(sourceShipperId, OperateEnum.GOODSASSIGNED.getName(), updateUserId);

		ApiResult<Map<String, Object>> apiResult = new ApiResult<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("record", sahRecord);

		// ===推送消息给货主===
		goodsProvidereMQServie.msgPushToShipperForGoodsAssignmentAccepted(sourceShipperId, resultEntity.getMemberId());

		logger.debug("acceptAssigned执行成功！");
		return apiResult.setResult(resultMap);
	}

	@Override
	@Transactional
	public ApiResult<Map<String, Object>> acceptPlatformDistribution(Map<String, Object> paramMap) throws BizException {
		// ===根据id查询货源===
		Integer sourceShipperId = Integer.valueOf(paramMap.get("sourceShipperId").toString());
		SourceShipperEntity resultEntity = querySourceShipperById(sourceShipperId);
		// 判断货源是否已经取消
		if (resultEntity.getSourceStatus().compareTo((byte) 5) == 0) {
			throw new BizException(MsgCons.C_24035, MsgCons.M_24035);
		}
		// step1 判断物流公司是否绑定司机
		int LogisticBindingCar = baseDao.queryForObject("SourceShipper.queryMemberLogisticDriverCar", paramMap,
				Integer.class);
		if (LogisticBindingCar == 0) {
			throw new BizException(MsgCons.C_23016, MsgCons.M_23016);
		}
		List<MemberCarEntity> listDriverCar = baseDao.queryForList("SourceShipper.querydriverCar", paramMap,
				MemberCarEntity.class);
		// step2 司机最少包含一辆车 ，获取司机最新添加的车辆
		if (CollectionUtils.isEmpty(listDriverCar) || listDriverCar.size() == 0) {
			throw new BizException(MsgCons.C_23015, MsgCons.M_23015);
		}
		// step3 查询货源分配规则
		SourceAssignHisListDTO assignHisListDTO = baseDao.queryForObject("SourceAssignHis.queryAssignRule", paramMap,
				SourceAssignHisListDTO.class);
		if (assignHisListDTO == null) {
			logger.info("根据sourceShipperId:{}和sourceAssignHisId:{}查询分配规则错误!",
					new Object[] { paramMap.get("sourceShipperId"), paramMap.get("sourceAssignHisId") });
			throw new BizException(MsgCons.C_22102, MsgCons.M_22102);
		}
		// 设置货源为对应的分配规则。
		paramMap.put("nstRule", assignHisListDTO.getNstRule());
		// 设置为接受分配
		paramMap.put("assignStatus", SourceAssignHisStatusEnum.ACCEPTED.getCode());
		// 设置平台配送
		paramMap.put("platform", Constant.PlatFormMark.YES.getValue());
		// 货源已被接受
		paramMap.put("sourceStatus", SourceStatusEnum.ACCEPTED.getCode());
		// ===更新货源和分配历史表状态===
		int ssRecord = baseDao.execute("SourceShipper.updateNstRuleById", paramMap);
		if (ssRecord == 0) {
			throw new BizException(MsgCons.C_24011, MsgCons.M_24011);
		}
		int sahRecord = baseDao.execute("SourceAssignHis.updateStatusById", paramMap);
		if (sahRecord == 0) {
			throw new BizException(MsgCons.C_24011, MsgCons.M_24011);
		}

		// ===保存货源日志=====
		String updateUserId = paramMap.get("updateUserId").toString();
		saveLog(sourceShipperId, OperateEnum.GOODSASSIGNED.getName(), updateUserId);

		// 新增订单
		resultEntity.setUpdateUserId(updateUserId);
		Integer orderBeforeId = this.createOrder(resultEntity, listDriverCar.get(0));

		String driverMemberId = paramMap.get("driverMemberId").toString();
		// 推送消息给司机
		goodsProvidereMQServie.msgPushToDriverForPlatformAssignmentAccepted(orderBeforeId,
				Integer.valueOf(driverMemberId));

		ApiResult<Map<String, Object>> apiResult = new ApiResult<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("record", sahRecord);
		return apiResult.setResult(resultMap);
	}

	/**
	 * 创建订单相关信息 新增预订单 运单记录
	 * 
	 * @param resultEntity
	 * @param paramMap
	 * @throws BizException
	 */
	private Integer createOrder(SourceShipperEntity shipperResultEntity, MemberCarEntity carEntity)
			throws BizException {
		// step1 查询司机相关信息
		MemberInfoEntity memberInfoEntity = new MemberInfoEntity();
		memberInfoEntity.setId(carEntity.getMemberId());
		MemberInfoEntity driverInfoEntity = baseDao.find(MemberInfoEntity.class, memberInfoEntity);

		Date createDate = new Date();
		// step2 新增预订单记录
		OrderBeforeEntity beforeEntity = new OrderBeforeEntity();
		beforeEntity.setSourceStatus(OrderBeforeEnum.GENERATEORDER.getCode());
		beforeEntity.setSourceId(shipperResultEntity.getId());
		beforeEntity.setCarId(carEntity.getId());
		beforeEntity.setShipperMemberId(shipperResultEntity.getMemberId());
		beforeEntity.setShipperName(shipperResultEntity.getShipperName());
		beforeEntity.setShipperMobile(shipperResultEntity.getShipperMobile());
		beforeEntity.setDriverMemberId(driverInfoEntity.getId());
		beforeEntity.setDriverMobile(driverInfoEntity.getMobile());
		beforeEntity.setDriverName(driverInfoEntity.getUserName());
		beforeEntity.setCreateUserId(shipperResultEntity.getUpdateUserId());
		beforeEntity.setCreateTime(createDate);
		beforeEntity.setUpdateUserId(shipperResultEntity.getUpdateUserId());
		beforeEntity.setUpdateTime(createDate);
		beforeEntity.setShipper_isDeleted(Constant.TABLE_NOT_DELETE);
		beforeEntity.setDriver_isDeleted(Constant.TABLE_NOT_DELETE);
		Long beforeEntityId = baseDao.persist(beforeEntity, Long.class);
		if (beforeEntityId == null) {
			throw new BizException(MsgCons.C_23014, MsgCons.M_23014);
		}

		// step3 新增运单记录
		OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
		orderInfoEntity.setOrderNo(generateOrderNo(shipperResultEntity));
		orderInfoEntity.setSourceId(shipperResultEntity.getId());
		orderInfoEntity.setPrePayStatus(OrderInfoPrePayStatus.PENDING_PAY.getCode());
		orderInfoEntity.setOrderStatus(OrderInfoStatusEnum.ORDER_CONFIRM.getCode());
		orderInfoEntity.setCarId(carEntity.getId());
		orderInfoEntity.setOrderBeforeId(beforeEntityId.intValue());
		orderInfoEntity.setShipperMemberId(shipperResultEntity.getMemberId());
		orderInfoEntity.setDriverMemberId(driverInfoEntity.getId());
		orderInfoEntity.setConfirmOrderTime(createDate);
		orderInfoEntity.setTransStatus(OrderInfoTransStatusEnum.WAIT_EXAMINED_GOODS.getCode());
		orderInfoEntity.setCreateUserId(shipperResultEntity.getUpdateUserId());
		orderInfoEntity.setCreateTime(createDate);
		orderInfoEntity.setUpdateUserId(shipperResultEntity.getUpdateUserId());
		orderInfoEntity.setUpdateTime(createDate);
		orderInfoEntity.setShipper_isDeleted(Constant.TABLE_NOT_DELETE);
		orderInfoEntity.setDriver_isDeleted(Constant.TABLE_NOT_DELETE);
		Long orderInfoEntityId = baseDao.persist(orderInfoEntity, Long.class);
		if (orderInfoEntityId == null) {
			throw new BizException(MsgCons.C_23014, MsgCons.M_23014);
		}
		// step4 新增物流状态
		OrderInfoTransEntity transEntity = new OrderInfoTransEntity();
		transEntity.setOrderInfoId(orderInfoEntityId.intValue());
		transEntity.setOrderNo(orderInfoEntity.getOrderNo());
		transEntity.setSourceId(orderInfoEntity.getSourceId());
		transEntity.setTransStatus(OrderInfoTransStatusEnum.WAIT_EXAMINED_GOODS.getCode());
		transEntity.setOperateTime(createDate);
		transEntity.setCreateTime(createDate);
		transEntity.setCreateUserId(orderInfoEntity.getCreateUserId());
		baseDao.persist(transEntity, Long.class);
		// step5 发送通知
		this.sendNotice(orderInfoEntity);
		return beforeEntityId.intValue();
	}

	/**
	 * 通知谷登平台
	 */
	private void sendNotice(OrderInfoEntity orderInfoEntity) {
		// 通知谷登平台 物流公司已接受了平台配送的货源
		Map<String, Object> sendParam = new HashMap<>();
		sendParam.put("orderNo", orderInfoEntity.getSourceId());
		sendParam.put("type", 7);
		sendParam.put("userId", "0");
		sendParam.put("nstOrderNo", orderInfoEntity.getOrderNo());
		sendParam.put("companyId", orderInfoEntity.getUpdateUserId());
		sendParam.put("driverId", orderInfoEntity.getDriverMemberId());
		logger.info("平台配送物流公司:{}已接受此货源:{},通知谷登平台!",
				new Object[] { orderInfoEntity.getUpdateUserId(), orderInfoEntity.getSourceId() });
		String msg = null;
		try {
			String r = interfaceUtil.sendCallbackOrder(InterfaceUtils.des3Map(sendParam));
			Integer reCode = InterfaceUtils.pubMethod(r).getCode();
			msg = InterfaceUtils.pubMethod(r).getMsg();
			if (reCode == MsgCons.C_10000) {
				logger.info("物流公司接受平台配送货源,通知谷登平台成功!");
			} else {
				logger.info("物流公司接受平台配送货源,通知谷登平台失败!");
				this.insertMqError(sendParam, "物流公司接受平台配送货源,通知谷登平台失败,接口返回:" + msg, gdProperties.getCallbackOrderUrl(),
						Integer.parseInt(orderInfoEntity.getUpdateUserId()));
			}
		} catch (Exception e) {
			logger.info("物流公司接受平台配送货源,通知谷登平台异常：{}", e);
			this.insertMqError(sendParam, "物流公司接受平台配送货源,通知谷登平台异常,接口返回:" + msg, gdProperties.getCallbackOrderUrl(),
					Integer.parseInt(orderInfoEntity.getUpdateUserId()));
		}
	}

	/**
	 * 根据货源ID查询货源信息
	 * 
	 * @param sourceShipperId
	 * @return
	 * @throws BizException
	 */
	private SourceShipperEntity querySourceShipperById(Integer sourceShipperId) throws BizException {
		SourceShipperEntity paramEntity = new SourceShipperEntity();
		paramEntity.setId(sourceShipperId);
		SourceShipperEntity resultEntity = baseDao.find(SourceShipperEntity.class, paramEntity);
		if (resultEntity == null) {
			throw new BizException(MsgCons.C_24010, MsgCons.M_24010);
		}
		return resultEntity;
	}

	/**
	 * 保存货源日志。
	 * 
	 * @param sourceId
	 * @param desc
	 * @param createUserId
	 * @return
	 */
	private int saveLog(Integer sourceId, String desc, String createUserId) {
		SourceLogEntity entity = new SourceLogEntity();
		entity.setSourceId(sourceId);
		entity.setDescription(desc);
		entity.setCreateUserId(createUserId);
		Long id = baseDao.persist(entity, Long.class);
		logger.debug("保存货源日志成功，日志内容：" + desc);
		return id.intValue();
	}

	@Override
	@Transactional
	public ApiResult<Map<String, Object>> rejectAssigned(Map<String, Object> paramMap) throws BizException {
		// ===根据id查询货源===
		Integer sourceShipperId = Integer.valueOf(paramMap.get("sourceShipperId").toString());
		SourceShipperEntity resultEntity = querySourceShipperById(sourceShipperId);
		// 判断是否平台配送的货源
		if (resultEntity.getPlatform() == Constant.PlatFormMark.YES.getValue()) {
			// 判断货源是否已经取消
			if (resultEntity.getSourceStatus().compareTo((byte) 5) == 0) {
				throw new BizException(MsgCons.C_24035, MsgCons.M_24035);
			}
			// 关闭货源 修改状态已关闭(平台配送)
			baseDao.execute("SourceShipper.platformGoodsClose", paramMap);
			// 通知农商友、农批商
			Map<String, Object> sendParam = new HashMap<>();
			sendParam.put("memberAddressId", sourceShipperId);
			logger.info("平台配送物流公司:{}拒绝接受此货源:{},通知谷登平台!",
					new Object[] { paramMap.get("updateUserId"), sourceShipperId });
			String msg = null;
			try {
				String r = interfaceUtil.notifyAssignFailure(InterfaceUtils.des3Map(sendParam));
				Integer reCode = InterfaceUtils.pubMethod(r).getCode();
				msg = InterfaceUtils.pubMethod(r).getMsg();
				if (reCode == MsgCons.C_10000) {
					logger.info("物流公司拒绝平台配送货源,通知谷登平台成功");
				} else {
					logger.info("物流公司拒绝平台配送货源,通知谷登平台失败");
					this.insertMqError(sendParam, "物流公司拒绝平台配送货源,通知谷登平台失败,接口返回数据:" + msg,
							gdProperties.getNotifyAssignFailureUrl(),
							Integer.parseInt(paramMap.get("updateUserId").toString()));
				}
			} catch (Exception e) {
				logger.info("物流公司拒绝平台配送货源,通知谷登平台异常：{}", e);
				this.insertMqError(sendParam, "物流公司拒绝平台配送货源,通知谷登平台异常,接口返回数据:" + msg,
						gdProperties.getNotifyAssignFailureUrl(),
						Integer.parseInt(paramMap.get("updateUserId").toString()));
			}
		}
		// 设置为拒绝分配
		paramMap.put("assignStatus", 3);
		int sahRecord = baseDao.execute("SourceAssignHis.updateStatusById", paramMap);
		if (sahRecord == 0) {
			throw new BizException(MsgCons.C_24012, MsgCons.M_24012);
		}
		// ===保存货源日志=====
		/*
		 * 暂时不保存物流公司拒绝的日志。 Integer sourceShipperId =
		 * Integer.valueOf(paramMap.get("sourceShipperId").toString()); String
		 * updateUserId = paramMap.get("updateUserId").toString();
		 * saveLog(sourceShipperId, OperateEnum.GOODSREJECTED.getName(),
		 * updateUserId);
		 */

		// =====推送至mq进行重新分配=====
		goodsProvidereMQServie.goodsAssignmentMQ(sourceShipperId);

		ApiResult<Map<String, Object>> apiResult = new ApiResult<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("record", sahRecord);
		logger.debug("rejectAssigned执行成功！");
		return apiResult.setResult(resultMap);
	}

	@Override
	public ApiResult<SourceAssignHisDetailDTO> queryAssginmentGoodsDetail(Map<String, Object> paramMap)
			throws BizException {
		SourceAssignHisDetailDTO dto = baseDao.queryForObject("SourceAssignHis.queryAssginmentGoodsDetail", paramMap,
				SourceAssignHisDetailDTO.class);

		// 生成详细的全地址
		try {
			generalFullAddrAndSet(dto);
		} catch (Exception e) {
			logger.error("", e);
			throw new BizException(MsgCons.C_20000, MsgCons.M_20000);
		}
		logger.debug("queryAssginmentGoodsDetail执行成功！");
		ApiResult<SourceAssignHisDetailDTO> apiResult = new ApiResult<SourceAssignHisDetailDTO>();
		return apiResult.setResult(dto);
	}

	/**
	 * 生成订单号
	 * 
	 * @param entity
	 * @return
	 * @throws BizException
	 */
	private String generateOrderNo(SourceShipperEntity entity) throws BizException {
		Integer sequence = baseDao.queryForObject("SourceShipper.getNstOrderNo", null, Integer.class);
		Map<String, Integer> map = new HashMap<>();
		map.put("id", entity.getId());
		entity = baseDao.queryForObject("SourceShipper.querySourceProvinceById", map, SourceShipperEntity.class);
		if (sequence == null) {
			throw new BizException(MsgCons.C_23003, MsgCons.M_23003);
		}
		StringBuilder sb = new StringBuilder();
		// 1.出发地省会Id
		sb.append(entity.getSProvinceId().toString().substring(0, 2));
		// 2.年月日
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		sb.append(sf.format(new Date()));
		// 3.目的地省会Id
		sb.append(entity.getEProvinceId().toString().substring(0, 2));
		// 4.六位的自增序列
		String seq = String.valueOf(sequence);
		for (int i = 0; i < 6 - seq.length(); i++) {
			sb.append("0");
		}
		sb.append(seq);
		return sb.toString();
	}

	public void insertMqError(Map<String, Object> sendMap, String remark, String url, Integer memberId) {
		MqError mqError = new MqError();
		mqError.setBizType(MqConstants.BIZ_TYPE_2);
		mqError.setTopic(1);
		mqError.setMemberId(memberId);
		mqError.setHttpUrl(url);
		mqError.setContent(GSONUtils.toJson(sendMap, false));
		mqError.setRemark(remark);
		baseDao.execute("MqError.insert", mqError);
	}

	@Override
	public ApiResult<Integer> queryAssiGoodsIsView(Map<String, Object> paramMap) throws BizException {
		ApiResult<Integer> result = new ApiResult<>();
		Integer isView = baseDao.queryForObject("SourceAssignHis.queryAssiGoodsIsView", paramMap, Integer.class);
		result.setResult(isView);
		return result;
	}

	@Override
	public ApiResult<Integer> assignGoodsView(Map<String, Object> paramMap) throws BizException {
		ApiResult<Integer> result = new ApiResult<>();
		baseDao.execute("SourceAssignHis.assignGoodsView", paramMap);
		return result;
	}
}

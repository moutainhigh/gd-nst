package cn.gdeng.nst.web.controller.source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.api.server.member.MemberInfoApiService;
import cn.gdeng.nst.api.server.source.GoodsProvidereDubboMQServie;
import cn.gdeng.nst.api.server.source.GoodsService;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.entity.nst.SourceMerchantEntity;
import cn.gdeng.nst.entity.nst.SourceShipperEntity;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.NstRuleEnum;
import cn.gdeng.nst.enums.SourceGoodsTypeEnum;
import cn.gdeng.nst.enums.SourcePlatformEnum;
import cn.gdeng.nst.pub.service.AreaService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.DistanceCalculationUtil;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

/**
 * 货源更新控制类（包括增删改操作）
 * 
 * @author wjguo datetime 2016年8月3日 上午10:42:31
 *
 */
@Controller
@RequestMapping("v1/goodsUpdate")
public class GoodsUpdateController extends BaseController {
	/**
	 * 货源服务类
	 */
	@Reference
	private GoodsService goodsService;
	/**
	 * 区域服务类
	 */
	@Reference
	private AreaService areaService;
	/**
	 * 会员信息服务类
	 */
	@Reference
	private MemberInfoApiService memberInfoApiService;

	/**
	 * 货源mq生产者服务
	 * 
	 */
	@Reference
	private GoodsProvidereDubboMQServie goodsProvidereDubboMQServie;

	/**
	 * 货主发布货源
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("insertForShipper")
	@ResponseBody
	public ApiResult<?> insertForShipper(HttpServletRequest request) throws Exception {
		// 货主发货后马上进入分配
		return insert(NstRuleEnum.DISTRIBUTING, request);
	}

	/**
	 * 货源发布
	 * 
	 * @param nstRule
	 *            分配规则
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ApiResult<Map<String, Object>> insert(NstRuleEnum nstRule, HttpServletRequest request) throws Exception {
		SourceShipperEntity srcShipParam = getDecodeDto(request, SourceShipperEntity.class);
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		// 1.数据处理前的校验
		checkBeforeProcessOnInsert(srcShipParam);
		// 2.数据处理
		processBeforeInsert(srcShipParam, reqParamMap);
		// 3.数据处理后的校验
		checkAfterProcessOnInsert(srcShipParam);
		// 4.set分配规则
		srcShipParam.setNstRule(nstRule.getCode());
		// 5.填充默认值。
		fullGoodsDefaultData(srcShipParam);
		if (nstRule.equals(NstRuleEnum.SELFLOGST)) {
			// 物流公司直发，货源类型为自有。
			srcShipParam.setSourceGoodsType(SourceGoodsTypeEnum.SELF.getCode());
		}

		// 6.保存至数据库
		ApiResult<Map<String, Object>> apiResult = null;
		if (SourcePlatformEnum.PLAT_DIST.getCode().equals(srcShipParam.getPlatform())) {
			// 平台配置，需要额外保存平台商家信息
			SourceMerchantEntity merchant = constructSourceMerchantEntity(reqParamMap);
			apiResult = goodsService.save(srcShipParam, merchant);
		} else {
			apiResult = goodsService.save(srcShipParam);
		}

		// 7.货源分配
		if (nstRule.equals(NstRuleEnum.DISTRIBUTING) && apiResult.isSuccess()) {
			Long id = (Long) apiResult.getResult().get("id");
			goodsProvidereDubboMQServie.goodsAssignmentMQ(id.intValue());
		}
		return apiResult;
	}

	/**
	 * 在插入数据时，数据处理前的校验
	 * 
	 * @param entity
	 * @throws BizException
	 *             校验不通过，抛出此异常。
	 */
	private void checkBeforeProcessOnInsert(SourceShipperEntity entity) throws BizException {
		// 用户
		DataCheckUtils.assertIsNonNull("关联会员id不能为空", entity.getMemberId());
		// 地方
		DataCheckUtils.assertIsNonNull("出发地的省市区拼接的名称不能为空", entity.getSDetail());
		DataCheckUtils.assertIsNonNull("目的地的省市区拼接的名称不能为空", entity.getEDetail());
		DataCheckUtils.assertIsNonNull("出发地的经纬度不能为空", entity.getSLat(), entity.getSLng());
		DataCheckUtils.assertIsNonNull("目的地经纬度不能为空", entity.getELat(), entity.getELng());
		// 货物参数
		DataCheckUtils.assertIsNonNull(MsgCons.C_24000, MsgCons.M_24000, entity.getGoodsType());
		DataCheckUtils.assertExistNonNull(MsgCons.C_24013, MsgCons.M_24013, entity.getTotalWeight(),
				entity.getTotalSize());
		DataCheckUtils.assertLooseNumLimit(MsgCons.C_24001, MsgCons.M_24001, entity.getTotalWeight(), 0.01, 999.99);
		DataCheckUtils.assertLooseNumLimit(MsgCons.C_24002, MsgCons.M_24002, entity.getTotalSize(), 1, 999);
		DataCheckUtils.assertNumLimit(MsgCons.C_24003, MsgCons.M_24003, entity.getFreight(), 0, 99999);
		DataCheckUtils.assertIsNonNull(MsgCons.C_24004, MsgCons.M_24004, entity.getSendGoodsType());
		DataCheckUtils.assertIsNonNull(MsgCons.C_24015, MsgCons.M_24015, entity.getCarLength());
		DataCheckUtils.assertIsNonNull(MsgCons.C_24016, MsgCons.M_24016, entity.getCarType());
		// 其他
		DataCheckUtils.assertStrLimit(MsgCons.C_24005, MsgCons.M_24005, entity.getGoodsName(), 50);
		DataCheckUtils.assertStrLimit(MsgCons.C_24006, MsgCons.M_24006, entity.getRemark(), 50);
		DataCheckUtils.assertIsNonNull("客户端来源不能为空", entity.getClients());
		//如果有装货日期不为空，则需要判断装货日期不能小于当前日期
		if (entity.getSendDate()!=null) {
			checkSendDate(entity);
		}
	}

	/**
	 * 在插入前的数据加工处理
	 * 
	 * @param entity
	 *            封装的请求参数实体
	 * @param reqParamMap
	 *            map格式的所有请求参数
	 * @throws Exception
	 */
	private void processBeforeInsert(SourceShipperEntity entity, Map<String, String> reqParamMap) throws Exception {
		// 省市区(县)的地址映射为id
		mapAddrToId(entity, reqParamMap);
		// 如有需要，对起始地和目的地的省/市/区(县)的拼接进行修复。
		repairAppendAddrIfNecessary(entity);
		// 计算里程
		calculateMileage(entity);
		// 辨别同城与干线
		identifySourceType(entity);
		// 如有需要，set当前货主的基本信息
		setShipperBaseInfoIfNecessary(entity);
		// 如果没有指定创建人，则默认就是关联的会员id。
		if (StringUtils.isBlank(entity.getCreateUserId())) {
			entity.setCreateUserId(entity.getMemberId().toString());
		}
	}

	/**
	 * 省市区(县)的地址映射为id
	 * 
	 * @param entity
	 * @param reqParamMap
	 * @throws Exception
	 */
	private void mapAddrToId(SourceShipperEntity entity, Map<String, String> reqParamMap) throws Exception {
		// 起始地映射
		mapSrcAddrToId(entity, reqParamMap);
		// 目的地映射
		mapDescAddrToId(entity, reqParamMap);
	}

	/**
	 * 起始地的省市区(县)的地址映射为id
	 * 
	 * @param entity
	 * @param reqParamMap
	 * @throws Exception
	 */
	private void mapSrcAddrToId(SourceShipperEntity entity, Map<String, String> reqParamMap) throws Exception {
		// 出发地城市
		if (entity.getSCityId() == null && StringUtils.isNotBlank(reqParamMap.get("sCityName"))) {
			entity.setSCityId(getAreaIdByName(reqParamMap.get("sCityName"), false));
		}

		// 出发地省份
		if (entity.getSProvinceId() == null) {
			/*
			 * //有省份中文名 if
			 * (StringUtils.isNotBlank(reqParamMap.get("sProvinceName"))) {
			 * entity.setSProvinceId(getAreaIdByName(reqParamMap.get(
			 * "sProvinceName"), true)); } else
			 */
			if (entity.getSCityId() != null) {
				// 没省份中文名，则通过市来获取。
				entity.setSProvinceId(preferFatherAreaIdIfNecessary(entity.getSCityId()));
			}
		}

		// 出发地区（县）
		if (entity.getSAreaId() == null && StringUtils.isNotBlank(reqParamMap.get("sAreaName"))) {
			entity.setSAreaId(getAreaIdByFatherIdAndName(entity.getSCityId(), reqParamMap.get("sAreaName")));
		}
	}

	/**
	 * 目的地的省市区(县)的地址映射为id
	 * 
	 * @param entity
	 * @param reqParamMap
	 * @throws Exception
	 */
	private void mapDescAddrToId(SourceShipperEntity entity, Map<String, String> reqParamMap) throws Exception {
		// 目的地城市
		if (entity.getECityId() == null && StringUtils.isNotBlank(reqParamMap.get("eCityName"))) {
			entity.setECityId(getAreaIdByName(reqParamMap.get("eCityName"), false));
		}

		// 目的地省份
		if (entity.getEProvinceId() == null) {
			// 有省份中文名
			/*
			 * if (StringUtils.isNotBlank(reqParamMap.get("eProvinceName"))) {
			 * entity.setEProvinceId(getAreaIdByName(reqParamMap.get(
			 * "eProvinceName"), true)); } else
			 */
			if (entity.getECityId() != null) {
				// 没省份中文名，则通过市来获取。
				entity.setEProvinceId(preferFatherAreaIdIfNecessary(entity.getECityId()));
			}
		}

		// 目的地区（县）
		if (entity.getEAreaId() == null && StringUtils.isNotBlank(reqParamMap.get("eAreaName"))) {
			entity.setEAreaId(getAreaIdByFatherIdAndName(entity.getECityId(), reqParamMap.get("eAreaName")));
		}
	}

	/**
	 * 根据名称获取地区id。<br/>
	 * ps:只适合省或市的级别使用。
	 * 
	 * @param name
	 *            名称
	 * @param isProvince
	 *            是否省级地区
	 * @return
	 * @throws Exception
	 *             如果名称不正确，抛出BizException异常
	 */
	private Integer getAreaIdByName(Object name, boolean isProvince) throws Exception {
		ApiResult<AreaDTO> apiResult = areaService.getAreaByName(name.toString(), isProvince);
		AreaDTO area = apiResult.getResult();
		if (area != null) {
			return Integer.valueOf(area.getAreaID());
		}

		if (isProvince) {
			throw new BizException(MsgCons.C_24024, MsgCons.M_24024);
		} else {
			throw new BizException(MsgCons.C_24025, MsgCons.M_24025);
		}
	}

	/**
	 * 根据区域id获取地区的父id，如果父id不存在，则返回当前区域的id<br/>
	 * ps:只适合省或市的级别使用。
	 * 
	 * @param name
	 *            名称
	 * @return
	 * @throws Exception
	 */
	private Integer preferFatherAreaIdIfNecessary(Integer areaId) throws Exception {
		ApiResult<AreaDTO> apiResult = areaService.getAreaById(areaId.toString());
		AreaDTO area = apiResult.getResult();
		if (area != null) {
			// 如果是直辖市，是没有father的，则省份直接保存直辖市id
			return Integer.valueOf(area.getFather() != null ? area.getFather() : area.getAreaID());
		}
		return areaId;
	}

	/**
	 * 根据父id和地区名称获取地区id。<br/>
	 * ps：在获取区或县时需要使用此方法，因为区或县存在不同市之间存在相同的名称，需要通过市来进一步区分。
	 * 
	 * @param fatherId
	 *            父id
	 * @param areaName
	 *            地区名称
	 * @return
	 * @throws Exception
	 *             如果获取不到对应的地区id，抛出BizException异常。
	 */
	private Integer getAreaIdByFatherIdAndName(Integer fatherId, Object areaName) throws Exception {
		ApiResult<List<AreaDTO>> apiResult = areaService.listChildArea(fatherId.toString());
		List<AreaDTO> areas = apiResult.getResult();
		for (AreaDTO area : areas) {
			if (area.getArea().equals(areaName)) {
				return Integer.valueOf(area.getAreaID());
			}
		}

		throw new BizException(MsgCons.C_24026, MsgCons.M_24026);
	}

	/**
	 * 如有需要，修复拼接的地址（省/市/区（县））
	 * 
	 * @param entity
	 * @throws Exception
	 */
	private void repairAppendAddrIfNecessary(SourceShipperEntity entity) throws Exception {
		String sDetail = entity.getSDetail();
		// 如果直接用/开头，则说明没有对应的省名
		if (sDetail.startsWith("/")) {
			Integer id = entity.getSProvinceId();
			ApiResult<AreaDTO> apiResult = areaService.getAreaById(id.toString());
			sDetail = apiResult.getResult().getArea() + sDetail;
			entity.setSDetail(sDetail);
		}

		String eDetail = entity.getEDetail();
		if (eDetail.startsWith("/")) {
			Integer id = entity.getEProvinceId();
			ApiResult<AreaDTO> apiResult = areaService.getAreaById(id.toString());
			eDetail = apiResult.getResult().getArea() + eDetail;
			entity.setEDetail(eDetail);

		}
	}

	/**
	 * 里程计算
	 * 
	 * @param entity
	 */
	private void calculateMileage(SourceShipperEntity entity) {
		double distance = DistanceCalculationUtil.GetDistance(entity.getELat(), entity.getELng(), entity.getSLat(),
				entity.getSLng());
		entity.setMileage(distance);
	}

	/**
	 * 鉴别货源是同城还是干线
	 * 
	 * @param srcShipParam
	 */
	private void identifySourceType(SourceShipperEntity entity) {
		if (entity.getSProvinceId().equals(entity.getEProvinceId())
				&& entity.getSCityId().equals(entity.getECityId())) {
			// 同城
			entity.setSourceType(2);
		} else {
			// 干线
			entity.setSourceType(1);
		}
	}

	/**
	 * 如有需要，set当前货主的基本信息
	 * 
	 * @param entity
	 */
	private void setShipperBaseInfoIfNecessary(SourceShipperEntity entity) throws BizException {
		// 如果信息部全，则根据id查询并重新赋值
		if (entity.getShipperName() == null || entity.getShipperMobile() == null) {
			ApiResult<MemberInfoEntity> apiResult = memberInfoApiService.getById(entity.getMemberId());
			MemberInfoEntity memberInfo = apiResult.getResult();
			if (memberInfo != null) {
				entity.setShipperName(memberInfo.getUserName());
				entity.setShipperMobile(memberInfo.getMobile());
			}
		}
	}

	/**
	 * 在插入数据时，数据处理后的校验
	 * 
	 * @param entity
	 * @throws BizException
	 *             校验不通过，抛出此异常。
	 */
	private void checkAfterProcessOnInsert(SourceShipperEntity entity) throws BizException {
		// 地方
		DataCheckUtils.assertIsNonNull("出发地的省会ID不能为空", entity.getSProvinceId());
		DataCheckUtils.assertIsNonNull("出发地的城市ID不能为空", entity.getSCityId());

		DataCheckUtils.assertIsNonNull("目的地的省会ID不能为空", entity.getEProvinceId());
		DataCheckUtils.assertIsNonNull("目的地的城市ID不能为空", entity.getECityId());

		// 注释说明：发货无需传区域
		// 如果是同城货源，必须要求区域（县）的地址id存在
		/*
		 * if (entity.getSourceType() == 2) {
		 * DataCheckUtils.assertIsNonNull("出发地的区(县)不能为空", entity.getSAreaId());
		 * DataCheckUtils.assertIsNonNull("目的地的区(县)不能为空", entity.getEAreaId());
		 * }
		 */

		// 货主基本信息
		DataCheckUtils.assertIsNonNull("货主基本信息不全", entity.getShipperName(), entity.getShipperMobile());
	}

	/**
	 * 发布货源前填充默认的数据。
	 * 
	 * @param srcShipParam
	 */
	private void fullGoodsDefaultData(SourceShipperEntity srcShipParam) {
		if (srcShipParam.getCreateTime() == null) {
			srcShipParam.setCreateTime(new Date());
		}
		if (srcShipParam.getUpdateTime() == null) {
			srcShipParam.setUpdateTime(new Date());
		}
		if (srcShipParam.getNstRule() == null) {
			srcShipParam.setNstRule(NstRuleEnum.SELF.getCode());
		}
		// 默认版本号是从0开始。
		if (srcShipParam.getVersion() == null) {
			srcShipParam.setVersion(0);
		}
		if (srcShipParam.getPlatform() == null) {
			srcShipParam.setPlatform(SourcePlatformEnum.NOT_PLAT_DIST.getCode());
		}
	}

	/**
	 * 构造6+1货源对应商家信息实体
	 * 
	 * @param reqParamMap
	 *            请求数据集合
	 * @return 构造后的entity对象。
	 */
	private SourceMerchantEntity constructSourceMerchantEntity(Map<String, String> reqParamMap) {
		SourceMerchantEntity merchant = new SourceMerchantEntity();
		String memberId = reqParamMap.get("merchantMemberId");
		String name = reqParamMap.get("merchantName");
		String mobile = reqParamMap.get("merchantMobile");
		String title = reqParamMap.get("merchantTitle");
		String address = reqParamMap.get("merchantAddress");

		if (StringUtils.isNotBlank(memberId)) {
			merchant.setCreateUserId(memberId);
			merchant.setMemberId(Integer.valueOf(memberId));
		}

		merchant.setName(name);
		merchant.setMobile(mobile);
		merchant.setTitle(title);
		merchant.setAddress(address);
		merchant.setCreateTime(new Date());
		merchant.setUpdateTime(new Date());
		merchant.setIsDeleted((byte) 0);
		return merchant;
	}

	/**
	 * 物流公司发布货源
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("insertForLogistics")
	@ResponseBody
	public ApiResult<?> insertForLogistics(HttpServletRequest request) throws Exception {
		// 物流公司发货前需要经过认证
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		String memberId = reqParamMap.get("memberId");
		DataCheckUtils.assertIsNonNull("关联会员id不能为空", memberId);
		if (!isAuthed(Integer.valueOf(memberId))) {
			throw new BizException(MsgCons.C_21017, MsgCons.M_21017);
		}
		// 物流公司发货，如果updateId不为空，则需要删除货源 货源id=updateId
		String updateId = reqParamMap.get("updateId");
		if (StringUtils.isNotEmpty(updateId)) {
			goodsService.deleteGoodsById(Integer.parseInt(updateId));
		}
		// 发布货源
		return insert(NstRuleEnum.SELFLOGST, request);
	}

	/**
	 * 根据memberId判断用户是否经过认证
	 * 
	 * @param memberId
	 * @return
	 */
	private boolean isAuthed(Integer memberId) throws BizException {
		ApiResult<MemberInfoEntity> apiResult = memberInfoApiService.getById(Integer.valueOf(memberId));
		MemberInfoEntity memberInfoEntity = apiResult.getResult();
		int passCerCode = MemberCerStatusEnum.PASS_CER.getCode().intValue();
		return memberInfoEntity.getCerCompanyStatus() == passCerCode
				|| memberInfoEntity.getCerPersonalStatus() == passCerCode;
	}

	/**
	 * 通过id进行删除
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public ApiResult<?> deleteById(HttpServletRequest request) throws Exception {
		// 请求参数map。ps:转为泛型为String类型，是为了防止整数转换为小数的问题。
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		// 调用服务的参数map
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		// 数据校验
		checkDataInBaseUpdate(serParamMap);
		return goodsService.deleteSafelyById(serParamMap);
	}

	/**
	 * 更新数据前的基本数据检测
	 * 
	 * @param paraMap
	 * @throws BizException
	 *             校验不通过，抛出此异常。
	 */
	private void checkDataInBaseUpdate(Map<String, Object> paraMap) throws BizException {
		DataCheckUtils.assertIsNonNull("数据版本号不能为空", paraMap.get("version"));
		DataCheckUtils.assertIsNonNull("货源id不能为空", paraMap.get("id"));
		DataCheckUtils.assertIsNonNull("修改人员id不能为空", paraMap.get("updateUserId"));
	}

	/**
	 * 刷新货源创建时间
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refreshGoodsCreateTime")
	@ResponseBody
	public ApiResult<?> refreshGoodsCreateTime(HttpServletRequest request) throws Exception {
		// 请求参数map。ps:转为泛型为String类型，是为了防止整数转换为小数的问题。
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		// 调用服务的参数map
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		serParamMap.put("createTime", new Date());
		// 数据校验
		checkDataBeforeRefreshGoodsCreateTime(serParamMap);
		return goodsService.refreshGoodsCreateTimeAndTransToPub(serParamMap);
	}

	/**
	 * 刷新货源创建时间前的数据检测
	 * 
	 * @param paraMap
	 * @throws BizException
	 *             校验不通过，抛出此异常。
	 */
	private void checkDataBeforeRefreshGoodsCreateTime(Map<String, Object> paraMap) throws BizException {
		checkDataInBaseUpdate(paraMap);
		DataCheckUtils.assertIsNonNull("创建时间不能为空", paraMap.get("createTime"));
	}

	/**
	 * 根据id更新装货数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateSendDate")
	@ResponseBody
	public ApiResult<?> updateSendDate(HttpServletRequest request) throws Exception {
		// 请求参数map。ps:转为泛型为String类型，是为了防止整数转换为小数的问题。
		Map<String, String> reqParamMap = getDecodeMapStr(request);
		// 调用服务的参数map
		Map<String, Object> serParamMap = new HashMap<String, Object>(reqParamMap);
		// 更新货源的创建时间为服务器当前时间
		serParamMap.put("createTime", new Date());
		// 数据校验
		checkDataBeforeupdateSendDate(serParamMap);
		return goodsService.changeSendDateAndTransToPub(serParamMap);
	}

	/**
	 * 更新货源装货时间前的数据检测
	 * 
	 * @param paraMap
	 * @throws BizException
	 *             校验不通过，抛出此异常。
	 */
	private void checkDataBeforeupdateSendDate(Map<String, Object> paraMap) throws BizException {
		checkDataInBaseUpdate(paraMap);
		DataCheckUtils.assertIsNonNull("装货时间不能为空", paraMap.get("sendDate"));
		// 装货时间的比较
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date sendDate = null;
		try {
			sendDate = format.parse(paraMap.get("sendDate").toString());
		} catch (ParseException e) {
			new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		DataCheckUtils.assertPassedCurDate(MsgCons.C_24007, MsgCons.M_24007, sendDate);
	}

	/**
	 * 刷新货源同时删除这条货源
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refreshGoods")
	@ResponseBody
	public Object refreshGoods(HttpServletRequest request) throws Exception {
		// 获取参数
		Map<String, Object> paramMap = getDecodeMap(request);
		// 数据校验
		checkParam(paramMap);
		return goodsService.refreshGoods(paramMap);
	}

	/**
	 * 数据校验
	 * 
	 * @param paramMap
	 * @throws BizException
	 */
	private void checkParam(Map<String, Object> paramMap) throws BizException {
		DataCheckUtils.assertIsNonNull("货源id不能为空", paramMap.get("id"));
	}

	/**
	 * 装货时间和当前时间比较
	 * 
	 * @param resultEntity
	 * @throws BizException
	 */
	private void checkSendDate(SourceShipperEntity entity) throws BizException {
		// 装货时间
		long sendTimeMillis = entity.getSendDate().getTime();
		// 当前时间
		long currentTimeMillis = System.currentTimeMillis();
		// 装货时间小于当前刷新时间，返回24007
		if (sendTimeMillis < currentTimeMillis) {
			throw new BizException(MsgCons.C_24007, MsgCons.M_24007);
		}
	}
}

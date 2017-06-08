package cn.gdeng.nst.web.controller.source;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.api.dto.source.CarDriverDetailDto;
import cn.gdeng.nst.api.dto.source.FindCarLineDto;
import cn.gdeng.nst.api.server.source.CarService;
import cn.gdeng.nst.api.vo.order.OrderDetailBaseVo;
import cn.gdeng.nst.entity.nst.FindCarLineEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.pub.service.AreaService;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.Constant;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

/**
 * @author DJB
 * @version 创建时间：2016年8月9日 上午10:11:47 车源控制层
 */

@Controller
@RequestMapping("v1/car")
public class CarController extends BaseController {

	/**
	 * 车辆服务类
	 */
	@Reference
	private CarService	carService;

	/**
	 * 区域服务类
	 */
	@Reference
	private AreaService	areaService;

	/**
	 * 
	 * @Description: 添加线路 并查询出此线路的车辆
	 * @param request
	 *        FindCarLineEntity 实体
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("saveLine")
	@ResponseBody
	public ApiResult<FindCarLineDto> saveLine(HttpServletRequest request) throws Exception {

		Map<String, Object> param = getDecodeMap(request);
		Integer memberId = Integer.parseInt(param.get("memberId").toString());
		DataCheckUtils.assertIsNonNull("关联会员id不能为空", memberId);
		carService.memberCer(MsgCons.C_21017, MsgCons.M_21017, memberId);
		Integer s_provinceId = DataCheckUtils.mapValueToInteger(param, "s_provinceId"), e_provinceId = DataCheckUtils.mapValueToInteger(param, "e_provinceId"), s_cityId = DataCheckUtils.mapValueToInteger(param, "s_cityId"), e_cityId = DataCheckUtils.mapValueToInteger(param, "e_cityId"),
					s_areaId = DataCheckUtils.mapValueToInteger(param, "s_areaId"), e_areaId = DataCheckUtils.mapValueToInteger(param, "e_areaId");
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "s_detail");
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "e_detail");
		if ( s_areaId == null && s_cityId == null && s_provinceId == null && param.get("s_detail") != null && !param.get("s_detail").equals("全国")) {
			param = mapSrcAddrToId(param);
		}
		if ( e_areaId == null && e_cityId == null && e_provinceId == null && param.get("e_detail") != null && !param.get("e_detail").equals("全国")) {
			param = mapSrcAddrToIdDestination(param);
		}
		FindCarLineEntity findCarLineEntity = new FindCarLineEntity();
		findCarLineEntity.setS_provinceId(DataCheckUtils.mapValueToInteger(param, "s_provinceId"));
		findCarLineEntity.setE_provinceId(DataCheckUtils.mapValueToInteger(param, "e_provinceId"));
		findCarLineEntity.setS_cityId(DataCheckUtils.mapValueToInteger(param, "s_cityId"));
		findCarLineEntity.setE_cityId(DataCheckUtils.mapValueToInteger(param, "e_cityId"));
		findCarLineEntity.setS_areaId(DataCheckUtils.mapValueToInteger(param, "s_areaId"));
		findCarLineEntity.setE_areaId(DataCheckUtils.mapValueToInteger(param, "e_areaId"));
		findCarLineEntity.setS_detail(param.get("s_detail").toString().trim());
		findCarLineEntity.setE_detail(param.get("e_detail").toString().trim());
		// 之后 memberId 此参数从token取
		findCarLineEntity.setMemberId(memberId);
		findCarLineEntity.setCreateUserId(memberId.toString());
		findCarLineEntity.setCreateTime(new Date());
		findCarLineEntity.setUpdateUserId(memberId.toString());
		findCarLineEntity.setUpdateTime(new Date());
		findCarLineEntity.setIsSelect(Constant.TABLE_SELECT);
		findCarLineEntity.setIsDeleted(Constant.TABLE_NOT_DELETE);
		return carService.saveFindCarLine(findCarLineEntity);

	}

	/**
	 * 
	 * @Description: 删除线路
	 * @param request
	 *        id:线路ID memberID 会员ID
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("deleteLine")
	@ResponseBody
	public ApiResult<List<FindCarLineDto>> deleteLine(HttpServletRequest request) throws Exception {
		Map<String, Object> param = getDecodeMap(request);
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "id");
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "memberId");
		// 之后 memberId 此参数从token取
		return carService.deleteFindCarLineDtos(param);
	}

	/**
	 * 
	 * @Description: 查询司机所有线路
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryCarPage")
	@ResponseBody
	public ApiResult<List<FindCarLineDto>> queryCarPage(HttpServletRequest request) throws Exception {
		Map<String, Object> param = getDecodeMap(request);
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "memberId");
		Integer memberId = Integer.parseInt(param.get("memberId").toString());
		carService.memberCer(MsgCons.C_21017, MsgCons.M_21017, memberId);
		return carService.queryFindCarLineDtos(memberId);
	}

	/**
	 * 
	 * @Description:根据线路分页查询司机
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryLine")
	@ResponseBody
	public ApiResult<ApiPage> queryLine(HttpServletRequest request) throws Exception {
		// 获取分页对象
		ApiPage page = getPageInfoEncript(request);
		Map<String, Object> param = page.getParaMap();
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "memberId");
		Integer memberId = Integer.parseInt(param.get("memberId").toString());
		carService.memberCer(MsgCons.C_21017, MsgCons.M_21017, memberId);

		DataCheckUtils.assertIsNonNull("线路ID" + MsgCons.M_20001, MsgCons.C_20001, param.get("id"));
		return carService.queryFindCarLinePage(page);
	}

	/**
	 * 
	 * @Description: 查询司机详情
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryCarDriver")
	@ResponseBody
	public ApiResult<CarDriverDetailDto> queryCarDriver(HttpServletRequest request) throws Exception {
		Map<String, Object> param = getDecodeMap(request);
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "memberId");
		String memberId = param.get("memberId").toString();
		return carService.queryCarDetail(new Integer(memberId));
	}

	/**
	 * 
	 * @Description: 第一次进入，查询推荐货源 使用GPS查询，需要传出发地的省市区
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryRecommendGoods")
	@ResponseBody
	public ApiResult<ApiPage> queryRecommendGoods(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		Map<String, Object> param = page.getParaMap();
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "memberId");
		// 处理GPS定位
		param = mapSrcAddrToId(param);
		return carService.queryRecommendGoods(page);
	}

	/**
	 * 
	 * @Description: 车主找货源--分页查询货源
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryGoodsAll")
	@ResponseBody
	public ApiResult<ApiPage> queryGoodsAll(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		Map<String, Object> param = page.getParaMap();
		if ( param.get("recommend") != null && param.get("recommend").toString().equals("1")) {
			DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "memberId");
			// 处理GPS定位
			param = mapSrcAddrToId(param);
			page.setParaMap(param);
			return carService.queryRecommendGoods(page);
		}
		if ( param.get("recommend") != null && param.get("recommend").toString().equals("2")) {
			Integer s_provinceId = DataCheckUtils.mapValueToInteger(param, "s_provinceId"), s_cityId = DataCheckUtils.mapValueToInteger(param, "s_cityId"), s_areaId = DataCheckUtils.mapValueToInteger(param, "s_areaId");
			String sCityName = DataCheckUtils.mapValueToString(param, "sCityName");

			if ( s_areaId == null && s_cityId == null && s_provinceId == null && sCityName != null) {
				param = mapSrcAddrToId(param);
				page.setParaMap(param);
			}

			return carService.queryGoodsPage(page);
		}

		return carService.queryGoodsPage(page);
	}

	/**
	 * 
	 * @Description: 物流公司找货源--分页查货源总数统计
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryGoodsAllTotal")
	@ResponseBody
	public ApiResult<Object> queryGoodsAllTotal(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		return carService.queryGoodsAllTotal(page);
	}

	/**
	 * 查询货源详情 ()
	 */
	@RequestMapping("queryGoodsDetail")
	@ResponseBody
	public ApiResult<OrderDetailBaseVo> queryGoodsDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> param = getDecodeMap(request);
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001, MsgCons.C_20001, param, "sourceId");
		return carService.queryGoodsDetail(param);
	}

	/**
	 * 起始地的省市区(县)的地址映射为id
	 * 
	 * @param entity
	 * @param reqParamMap
	 * @throws Exception
	 */
	private Map<String, Object> mapSrcAddrToId(Map<String, Object> reqParamMap) throws Exception {
		// 出发地城市
		if ( reqParamMap.get("s_cityId") == null && reqParamMap.get("sCityName") != null) {
			reqParamMap.put("s_cityId", getAreaIdByName(reqParamMap.get("sCityName")));
		}
		// 出发地省份
		if ( reqParamMap.get("s_provinceId") == null) {
			// 有省份中文名
			if ( reqParamMap.get("sProvinceName") != null) {
				reqParamMap.put("s_provinceId", getAreaIdByName(reqParamMap.get("sProvinceName")));
			} else if ( reqParamMap.get("s_cityId") != null) {
				// 没省份中文名，则通过市来获取，如果是直辖市，是没有father的，则省份直接保存直辖市id
				reqParamMap.put("s_provinceId", preferFatherAreaIdIfNecessary((Integer) reqParamMap.get("s_cityId")));
			}
		}

		// 出发地区（县）
		if ( reqParamMap.get("s_areaId") == null && reqParamMap.get("sAreaName") != null) {
			reqParamMap.put("s_areaId", getAreaIdByFatherIdAndName((Integer) reqParamMap.get("s_cityId"), reqParamMap.get("sAreaName")));
		}
		return reqParamMap;
	}

	/**
	 * 目的地地的省市区(县)的地址映射为id
	 * 
	 * @param entity
	 * @param reqParamMap
	 * @throws Exception
	 */
	private Map<String, Object> mapSrcAddrToIdDestination(Map<String, Object> reqParamMap) throws Exception {
		// 出发地城市
		if ( reqParamMap.get("e_cityId") == null && reqParamMap.get("eCityName") != null) {
			reqParamMap.put("e_cityId", getAreaIdByName(reqParamMap.get("eCityName")));
		}
		// 出发地省份
		if ( reqParamMap.get("e_provinceId") == null) {
			// 有省份中文名
			if ( reqParamMap.get("eProvinceName") != null) {
				reqParamMap.put("e_provinceId", getAreaIdByName(reqParamMap.get("eProvinceName")));
			} else if ( reqParamMap.get("e_cityId") != null) {
				// 没省份中文名，则通过市来获取，如果是直辖市，是没有father的，则省份直接保存直辖市id
				reqParamMap.put("e_provinceId", preferFatherAreaIdIfNecessary((Integer) reqParamMap.get("e_cityId")));
			}
		}

		// 出发地区（县）
		if ( reqParamMap.get("e_areaId") == null && reqParamMap.get("eAreaName") != null) {
			reqParamMap.put("e_areaId", getAreaIdByFatherIdAndName((Integer) reqParamMap.get("e_cityId"), reqParamMap.get("eAreaName")));
		}
		return reqParamMap;
	}

	/**
	 * 根据名称获取地区id.<br/>
	 * ps:只适合省或市的级别使用。
	 * 
	 * @param name
	 *        名称
	 * @return
	 * @throws Exception
	 */
	private Integer getAreaIdByName(Object name) throws Exception {
		ApiResult<AreaDTO> apiResult = areaService.getAreaByName(name.toString());
		AreaDTO area = apiResult.getResult();
		if ( area != null) {
			return Integer.valueOf(area.getAreaID());
		}
		return null;
	}

	/**
	 * 根据区域id获取地区的父id，如果父id不存在，则返回当前区域的id<br/>
	 * ps:只适合省或市的级别使用。
	 * 
	 * @param name
	 *        名称
	 * @return
	 * @throws Exception
	 */
	private Integer preferFatherAreaIdIfNecessary(Integer areaId) throws Exception {
		ApiResult<AreaDTO> apiResult = areaService.getAreaById(areaId.toString());
		AreaDTO area = apiResult.getResult();
		if ( area != null) {
			// 如果是直辖市，是没有father的，则省份直接保存直辖市id
			return Integer.valueOf(area.getFather() != null ? area.getFather() : area.getArea());
		}
		return areaId;
	}

	/**
	 * 根据父id和地区名称获取地区id。<br/>
	 * ps：在获取区或县时需要使用此方法，因为区或县存在不同市之间存在相同的名称，需要通过市来进一步区分。
	 * 
	 * @param fatherId
	 *        父id
	 * @param areaName
	 *        地区名称
	 * @return
	 * @throws Exception
	 */
	private Integer getAreaIdByFatherIdAndName(Integer fatherId, Object areaName) throws Exception {
		ApiResult<List<AreaDTO>> apiResult = areaService.listChildArea(fatherId.toString());
		List<AreaDTO> areas = apiResult.getResult();
		for (AreaDTO area : areas) {
			if ( area.getArea().equals(areaName)) {
				return Integer.valueOf(area.getAreaID());
			}
		}

		return null;
	}

}

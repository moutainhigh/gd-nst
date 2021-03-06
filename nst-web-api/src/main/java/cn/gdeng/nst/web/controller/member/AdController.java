package cn.gdeng.nst.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.api.server.ad.AdService;
import cn.gdeng.nst.api.server.ad.CallstatisticsService;
import cn.gdeng.nst.entity.nst.CallstatisticsEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.pub.service.AreaService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
* @author DJB
* @version 创建时间：2016年8月3日 下午3:48:27
* 广告
*/
@Controller
@RequestMapping("v1/ad")
public class AdController extends BaseController  {
	@Reference
	private AdService adService;
	/**
	 * 区域服务类
	 */
	@Reference
	private AreaService areaService;
	

	/**
	 * 电话统计服务类
	 */
	@Reference
	private CallstatisticsService callstatisticsService;
	
	/**
	 * 
	 * @Description: 查询所有广告  包括跑马灯和横幅广告 
	 * @param request
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping("queryNewAd")
	@ResponseBody
	public Object queryNewAd(HttpServletRequest request) throws Exception {
		Map<String,Object> paraMap = getDecodeMap(request);
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001,MsgCons.C_20001,paraMap,"channel");
		DataCheckUtils.assertIsNoNull(MsgCons.M_20001,MsgCons.C_20001,paraMap,"cityName");
		paraMap=mapSrcAddrToId(paraMap);
		return adService.queryAdAllNew(paraMap);
	}
	
	
	/**起始地的省市区(县)的地址映射为id
	 * @param entity
	 * @param reqParamMap
	 * @throws Exception
	 */
	private Map<String, Object> mapSrcAddrToId(Map<String, Object> reqParamMap) throws Exception {
		//出发地城市
		if (reqParamMap.get("cityId") ==  null && reqParamMap.get("cityName") != null) {
			try {
				reqParamMap.put("cityId", getAreaIdByName(reqParamMap.get("cityName")));
			} catch (Exception e) {
				reqParamMap.put("cityId",null);
			}
		}
		//出发地省份
		if (reqParamMap.get("provinceId")==  null) {
			//有省份中文名
			if (reqParamMap.get("provinceName") != null) {
				try {
					reqParamMap.put("provinceId",getAreaIdByName(reqParamMap.get("provinceName")));	
				} catch (Exception e) {
					reqParamMap.put("provinceId",null);	
				}
			} else if (reqParamMap.get("cityId")!= null) {
				//没省份中文名，则通过市来获取，如果是直辖市，是没有father的，则省份直接保存直辖市id
				try {
					reqParamMap.put("provinceId",preferFatherAreaIdIfNecessary((Integer)reqParamMap.get("cityId")));
				} catch (Exception e) {
					reqParamMap.put("provinceId",null);	
				}
			}
		}
		
		//出发地区（县）
		if (reqParamMap.get("areaId")==  null && reqParamMap.get("areaName") != null) {
			try {
				reqParamMap.put("areaId",getAreaIdByFatherIdAndName((Integer)reqParamMap.get("cityId"), reqParamMap.get("areaName")));
			} catch (Exception e) {
				reqParamMap.put("areaId",null);
			}
		}
		return reqParamMap;
	}
	

	
	
	/**根据名称获取地区id.<br/>
	 * ps:只适合省或市的级别使用。
	 * @param name 名称
	 * @return
	 * @throws Exception
	 */
	private Integer getAreaIdByName(Object name) throws Exception {
		ApiResult<AreaDTO> apiResult = areaService.getAreaByName(name.toString());
		AreaDTO area = apiResult.getResult();
		if (area != null) {
			return Integer.valueOf(area.getAreaID());
		}
		return null;
	}
	
	/**根据区域id获取地区的父id，如果父id不存在，则返回当前区域的id<br/>
	 * ps:只适合省或市的级别使用。
	 * @param name 名称
	 * @return
	 * @throws Exception
	 */
	private Integer preferFatherAreaIdIfNecessary(Integer areaId) throws Exception {
		ApiResult<AreaDTO> apiResult = areaService.getAreaById(areaId.toString());
		AreaDTO area = apiResult.getResult();
		if (area != null) {
			//如果是直辖市，是没有father的，则省份直接保存直辖市id
			return Integer.valueOf(area.getFather() != null ? area.getFather() : area.getArea());
		}
		return areaId;
	}
	
	
	
	/**根据父id和地区名称获取地区id。<br/>
	 * ps：在获取区或县时需要使用此方法，因为区或县存在不同市之间存在相同的名称，需要通过市来进一步区分。
	 * @param fatherId 父id
	 * @param areaName 地区名称
	 * @return
	 * @throws Exception
	 */
	private Integer getAreaIdByFatherIdAndName(Integer fatherId, Object areaName) throws Exception {
		ApiResult<List<AreaDTO>> apiResult = areaService.listChildArea(fatherId.toString());
		List<AreaDTO> areas = apiResult.getResult();
		for (AreaDTO area : areas) {
			if (area.getArea().equals(areaName)) {
				return Integer.valueOf(area.getAreaID());
			}
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @Description: 存储电话拨打记录
	 * @param request
	 * @return  对象类型
	 * @throws Exception
	 *
	 */
	@RequestMapping("saveCall")
	@ResponseBody
	public Object saveCall(HttpServletRequest request) throws Exception {
		CallstatisticsEntity callstatisticsEntity = getDecodeDto(request, CallstatisticsEntity.class);
		
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"memberId",MsgCons.C_20001,callstatisticsEntity.getMemberid());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"S_Mobile",MsgCons.C_20001,callstatisticsEntity.getS_Mobile());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"CallRole",MsgCons.C_20001,callstatisticsEntity.getCallRole());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"Source",MsgCons.C_20001,callstatisticsEntity.getSource());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"FromCode",MsgCons.C_20001,callstatisticsEntity.getFromCode());
		return callstatisticsService.saveCall(callstatisticsEntity);
	}
	
	
	
	/**
	 * 
	 * @Description: 存储电话拨打记录
	 * @param request
	 * @return Map 类型
	 * @throws Exception
	 *
	 */
	@RequestMapping("saveCallResultMap")
	@ResponseBody
	public Object saveCallResultMap(HttpServletRequest request) throws Exception {
		CallstatisticsEntity callstatisticsEntity = getDecodeDto(request, CallstatisticsEntity.class);
		
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"memberId",MsgCons.C_20001,callstatisticsEntity.getMemberid());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"S_Mobile",MsgCons.C_20001,callstatisticsEntity.getS_Mobile());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"CallRole",MsgCons.C_20001,callstatisticsEntity.getCallRole());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"Source",MsgCons.C_20001,callstatisticsEntity.getSource());
		DataCheckUtils.assertIsNonNull(MsgCons.M_20001+"FromCode",MsgCons.C_20001,callstatisticsEntity.getFromCode());
		return callstatisticsService.saveCallResultMap(callstatisticsEntity);
	}
	

}

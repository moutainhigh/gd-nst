package cn.gdeng.nst.server.pub.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.pub.service.AreaService;
import cn.gdeng.nst.util.server.bo.CacheBo;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 区域表实现
 * 
 * @author xiaojun
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private BaseDao<?> baseDao;
	@Autowired
	private CacheBo cacheBo;

	@Override
	public ApiResult<Map<String, List<AreaDTO>>> listTopArea() throws Exception {
		Map<String, List<AreaDTO>> map = new HashMap<>();
		map.put("directlyCity", listTopDirectlyCity());
		map.put("region", listTopRegion());
		map.put("province", listTopProvince());
		return new ApiResult<Map<String, List<AreaDTO>>>(map, MsgCons.C_10000, MsgCons.M_10000);
	}

	@Override
	public ApiResult<AreaDTO> getAreaById(String areaID) throws Exception {
		AreaDTO areaDto = cacheBo.getAreaById(areaID, baseDao);
		ApiResult<AreaDTO> result = new ApiResult<AreaDTO>(areaDto, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	@Override
	public ApiResult<AreaDTO> getAreaByName(String area) throws Exception {
		AreaDTO areaDto = cacheBo.getAreaByName(area, baseDao);
		ApiResult<AreaDTO> result = new ApiResult<AreaDTO>(areaDto, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	@Override
	public ApiResult<AreaDTO> getAreaByName(String area, Boolean isProvince) throws Exception {
		AreaDTO areaDto = cacheBo.getAreaByName(area, isProvince, baseDao);
		ApiResult<AreaDTO> result = new ApiResult<AreaDTO>(areaDto, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	@Override
	public ApiResult<List<AreaDTO>> listChildArea(String id) throws Exception {
		List<AreaDTO> list = cacheBo.listChildArea(id, baseDao);
		ApiResult<List<AreaDTO>> result = new ApiResult<List<AreaDTO>>(list, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	@Override
	public ApiResult<List<AreaDTO>> getAreaChildTree(String father) throws Exception {
		List<AreaDTO> list = cacheBo.getAreaChildTree(father, baseDao);
		ApiResult<List<AreaDTO>> result = new ApiResult<List<AreaDTO>>(list, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	@Override
	public ApiResult<List<AreaDTO>> getAllProvinceCity() throws Exception {
		List<AreaDTO> list = cacheBo.getAllProvinceCity(baseDao);
		ApiResult<List<AreaDTO>> result = new ApiResult<List<AreaDTO>>(list, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	@Override
	public List<AreaDTO> listTopDirectlyCity() throws Exception {
		return cacheBo.listTopDirectlyCity(baseDao);
	}

	@Override
	public List<AreaDTO> listTopRegion() throws Exception {
		return cacheBo.listTopRegion(baseDao);
	}

	@Override
	public List<AreaDTO> listTopProvince() throws Exception {
		return cacheBo.listTopProvince(baseDao);
	}

	/**
	 * 
	 * @Description: 根据定位中文地址获取 特定省市区ID   市名称不能为空 
	 * @param reqParamMap
	 *            需要转换的map
	 * @param provinceId
	 *            map中 省ID 的key名称
	 * @param provinceName
	 *            map中 省中文 的key名称
	 * @param cityId
	 *            map中市ID 的key名称
	 * @param cityName
	 *            map中市中文 的key名称
	 * @param areaId
	 *            map中区ID 的key名称
	 * @param areaName
	 *            map中区中文ID 的key名称
	 * @return
	 * @throws Exception
	 *
	 */
	@Override
	public Map<String, Object> mapSrcAddrToId(Map<String, Object> reqParamMap, String provinceId,
			String provinceName, String cityId, String cityName, String areaId, String areaName) throws Exception {
		// 城市
		if (reqParamMap.get("cityId") == null && reqParamMap.get("cityName") != null) {
			reqParamMap.put("cityId", getAreaIdByName(reqParamMap.get("cityName")));
		}
		// 省份
		if (reqParamMap.get("provinceId") == null) {
			// 有省份中文名
			if (reqParamMap.get("provinceName") != null) {
				reqParamMap.put("provinceId", getAreaIdByName(reqParamMap.get("provinceName")));
			} else if (reqParamMap.get("cityId") != null) {
				// 没省份中文名，则通过市来获取，如果是直辖市，是没有father的，则省份直接保存直辖市id
				reqParamMap.put("provinceId", preferFatherAreaIdIfNecessary((Integer) reqParamMap.get("cityId")));
			}
		}
		// 出发地区（县）
		if (reqParamMap.get("areaId") == null && reqParamMap.get("areaName") != null) {
			reqParamMap.put("areaId",
					getAreaIdByFatherIdAndName((Integer) reqParamMap.get("cityId"), reqParamMap.get("areaName")));
		}
		return reqParamMap;
	}

	/**
	 * 根据名称获取地区id.<br/>
	 * ps:只适合省或市的级别使用。
	 * 
	 * @param name
	 *            名称
	 * @return
	 * @throws Exception
	 */
	private Integer getAreaIdByName(Object name) throws Exception {
		ApiResult<AreaDTO> apiResult = this.getAreaByName(name.toString());
		AreaDTO area = apiResult.getResult();
		if (area != null) {
			return Integer.valueOf(area.getAreaID());
		}
		return null;
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
		ApiResult<AreaDTO> apiResult = this.getAreaById(areaId.toString());
		AreaDTO area = apiResult.getResult();
		if (area != null) {
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
	 *            父id
	 * @param areaName
	 *            地区名称
	 * @return
	 * @throws Exception
	 */
	private Integer getAreaIdByFatherIdAndName(Integer fatherId, Object areaName) throws Exception {
		ApiResult<List<AreaDTO>> apiResult = this.listChildArea(fatherId.toString());
		List<AreaDTO> areas = apiResult.getResult();
		for (AreaDTO area : areas) {
			if (area.getArea().equals(areaName)) {
				return Integer.valueOf(area.getAreaID());
			}
		}

		return null;
	}

}

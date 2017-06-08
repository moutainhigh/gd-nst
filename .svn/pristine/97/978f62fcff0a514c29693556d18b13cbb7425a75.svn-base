package cn.gdeng.nst.pub.service;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 区域服务
 * 
 * @author xiaojun
 */
public interface AreaService {
	/**
	 * 取一级地区
	 * 
	 * @return
	 */
	public ApiResult<Map<String, List<AreaDTO>>> listTopArea() throws Exception;
	
	/**
	 *  取一级地区直辖市
	 * 
	 * @return
	 */
	public List<AreaDTO> listTopDirectlyCity() throws Exception;
	
	
	/**
	 * 取一级地区自治区
	 * 
	 * @return
	 */
	public List<AreaDTO> listTopRegion() throws Exception;
	
	/**
	 * 取一级地区省份(不包括港澳)
	 * 
	 * @return
	 */
	public List<AreaDTO> listTopProvince() throws Exception;

	/**
	 * 根据Id查询地区
	 * 
	 * @param areaID
	 * @return
	 */
	public ApiResult<AreaDTO> getAreaById(String areaID) throws Exception;

	/**
	 * 根据地区名查询地区
	 * 
	 * @param area
	 * @return
	 */
	public ApiResult<AreaDTO> getAreaByName(String area) throws Exception;
	
	/**
	 * 根据地区名查询地区 
	 * @param area
	 * @param isProvince 是否省级地区，是为了处理直辖市的查询问题。
	 * @return
	 */
	public ApiResult<AreaDTO> getAreaByName(String area, Boolean isProvince) throws Exception;

	/**
	 * 根据id查下级类
	 * 
	 * @param id
	 * @return
	 */
	public ApiResult<List<AreaDTO>> listChildArea(String id) throws Exception;

	/**
	 * 根据id查询下级树
	 * 
	 * @param father
	 * @return
	 */
	public ApiResult<List<AreaDTO>> getAreaChildTree(String father) throws Exception;

	/**
	 * 获取所有省份和城市
	 * 
	 * @return
	 */
	public ApiResult<List<AreaDTO>> getAllProvinceCity() throws Exception;
	
	/**
	 * 
	 * @Description: 根据定位中文地址获取 特定省市区ID   市名称不能为空 
	 *  example : param =areaService.mapSrcAddrToId(param, "s_provinceId", "sProvinceName", "s_cityId", "sCityName", "s_areaId", "sAreaName");
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
	public Map<String, Object> mapSrcAddrToId(Map<String, Object> reqParamMap
			,String provinceId,String provinceName
			,String cityId,String cityName
			,String areaId,String areaName) throws Exception;
	
}

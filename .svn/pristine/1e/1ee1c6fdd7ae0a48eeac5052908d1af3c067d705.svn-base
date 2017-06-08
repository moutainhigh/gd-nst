package cn.gdeng.nst.util.server.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.api.dto.redis.MemberBaseinfoDTO;
import cn.gdeng.nst.dao.BaseDao;

public class CacheBo {
	/***********************地区分类缓存**********************/
	
	/**
	 * 取一级地区
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> listTopArea(BaseDao baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		return baseDao.queryForList("Area.listTopArea", params ,AreaDTO.class);
	}
	
	/**
	 * 取一级地区直辖市
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> listTopDirectlyCity(BaseDao baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		return baseDao.queryForList("Area.listTopDirectlyCity", params ,AreaDTO.class);
	}
	
	/**
	 * 取一级地区自治区
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> listTopRegion(BaseDao baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		return baseDao.queryForList("Area.listTopRegion", params ,AreaDTO.class);
	}
	
	/**
	 * 取一级地区省份(不包括港澳)
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> listTopProvince(BaseDao baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		return baseDao.queryForList("Area.listTopProvince", params ,AreaDTO.class);
	}
	
	/**
	 * 根据Id查询地区 
	 * @param areaID
	 * @param baseDao
	 * @return
	 */
	public AreaDTO getAreaById(String areaID,BaseDao baseDao){
		Map <String, String> p = new HashMap<String, String>();
		p.put("areaID", areaID);
		return (AreaDTO)baseDao.queryForObject("Area.getArea", p, AreaDTO.class);
	}
	
	/**
	 * 根据地区名查询地区 
	 * @param area
	 * @param baseDao
	 * @return
	 */
	public AreaDTO getAreaByName(String area,BaseDao<?> baseDao) {
		Map <String, String> p = new HashMap<String, String>();
		p.put("area", area);
		return (AreaDTO)baseDao.queryForObject("Area.getAreaByName", p ,AreaDTO.class);
	}
	
	/**
	 * 根据地区名查询地区 
	 * @param area
	 * @param isProvince 是否省级地区，是为了处理直辖市的查询问题。
	 * @param baseDao
	 * @return
	 */
	public AreaDTO getAreaByName(String area, Boolean isProvince, BaseDao<?> baseDao) {
		Map <String, Object> p = new HashMap<String, Object>();
		p.put("area", area);
		p.put("isProvince", isProvince);
		return (AreaDTO)baseDao.queryForObject("Area.getAreaByName", p ,AreaDTO.class);
	}
	

	/**
	 * 根据id查下级类
	 * @param id
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> listChildArea(String id,BaseDao baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("father", id);
		return baseDao.queryForList("Area.listChildArea", params ,AreaDTO.class);
	}
	
	/**
	 * 根据id查询下级树
	 * @param father
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> getAreaChildTree(String father,BaseDao baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("father", father);
		return baseDao.queryForList("Area.getChildTree", params ,AreaDTO.class);
	}

	/**
	 * 删除地区缓存
	 * @param id
	 */
	public void deleteAreaCacheById(String id){
		
	}
	
	/**
	 * 获取所有省份和城市
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> getAllProvinceCity(BaseDao<?> baseDao) {
		Map<String,Object> params = new HashMap<String,Object>();
		return baseDao.queryForList("Area.getAllProvinceCity", params, AreaDTO.class);
	}
	
	/**
	 * 一级地区（附加isParent字段）
	 * @param baseDao
	 * @return
	 */
	public List<AreaDTO> getParentTree(BaseDao<?> baseDao){
		Map<String,Object> params = new HashMap<String,Object>();
		return baseDao.queryForList("Area.getParentTree", params, AreaDTO.class);
	}
	
	/***********************地区分类缓存End**********************/
	
	/**********************会员缓存Start************************/
	/**
	 * 根据ID查询会员
	 * @param memberId
	 * @param baseDao
	 * @return
	 * @throws Exception
	 */
	public MemberBaseinfoDTO getMemberById(String memberId,BaseDao baseDao) throws Exception {
		Map map=new HashMap();
		map.put("memberId", memberId);
		return (MemberBaseinfoDTO) baseDao.queryForObject("MemberBaseinfo.getByMemberId", map, MemberBaseinfoDTO.class);
	}
	
	/**
	 * 根据mobile查询会员
	 * @param mobile
	 * @param baseDao
	 * @return
	 * @throws Exception
	 */
	public MemberBaseinfoDTO getMemberByMobile(String mobile,BaseDao baseDao) throws Exception {
		Map map=new HashMap();
		map.put("mobile", mobile);
		return (MemberBaseinfoDTO) baseDao.queryForObject("MemberBaseinfo.getByMobile", map, MemberBaseinfoDTO.class);
	}
	
	/**
	 * 根据账号查询会员
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public MemberBaseinfoDTO getMemberByAccount(String account,BaseDao baseDao) throws Exception {
		Map map=new HashMap();
		map.put("account", account);
		return  (MemberBaseinfoDTO)baseDao.queryForObject("MemberBaseinfo.getByAccount", map, MemberBaseinfoDTO.class);
	}
	
	/**
	 * 删除会员缓存
	 * @param id
	 */
	public void deleteMemberCacheById(String id){
		
	}
	
	/**********************会员缓存End**************************/
	
}

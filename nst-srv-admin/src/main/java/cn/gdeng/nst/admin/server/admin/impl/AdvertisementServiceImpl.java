package cn.gdeng.nst.admin.server.admin.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdvertisementDTO;
import cn.gdeng.nst.admin.dto.admin.TreeNode;
import cn.gdeng.nst.admin.service.admin.AdvertisementService;
import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.AdvertisementEntity;
import cn.gdeng.nst.enums.AdStatusEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.server.bo.CacheBo;
import cn.gdeng.nst.util.web.api.ApiResult;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private BaseDao<?> baseDao;
	
	@Autowired
	private CacheBo cacheBo;

	@Override
	public ApiResult<Long> add(AdvertisementEntity entity) {
		// 广告名称唯一判断
		boolean exists = exists(entity.getName(), entity.getChannel(), entity.getCityId());
		if(exists){
			return new ApiResult<Long>().withError(MsgCons.C_29002, MsgCons.M_29002);
		}
		
		// 排序唯一判断
		boolean sortExists = sortExists(entity.getSort(), entity.getChannel(), entity.getCityId());
		if(sortExists){
			return new ApiResult<Long>().withError(MsgCons.C_29003, MsgCons.M_29003);
		}
		
		Long result = baseDao.persist(entity, Long.class);
		return new ApiResult<Long>().setResult(result);
	}

	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> map) {
		int totalCount = baseDao.queryForObject("Advertisement.countTotal", map, Integer.class);
		List<AdvertisementDTO> list = null;
		if(totalCount > 0){
			list = baseDao.queryForList("Advertisement.queryListByPage", map, AdvertisementDTO.class);
		}else{
			list = Collections.emptyList();
		}
		
		// 广告状态是否已过期处理
		for(AdvertisementDTO dto : list){
			Date endTime = dto.getEndTime();
			if(new Date().after(endTime)){
				dto.setStatus(AdStatusEnum.EXPIRED.getCode());
			}
		}
		
		AdminPageDTO pageDTO = new AdminPageDTO(totalCount, list);
		return new ApiResult<AdminPageDTO>().setResult(pageDTO);
	}

	@Override
	public ApiResult<Integer> updateStatus(AdvertisementDTO advertiseDTO) {
		// 上架
		if(AdStatusEnum.ON.getCode().equals(advertiseDTO.getStatus())){
			AdvertisementDTO dto = queryById(advertiseDTO.getId());
			
			// 当前时间小于广告开始时间，不能上架
			Date nowDate = new Date();
			if(nowDate.before(dto.getStartTime())){
				return new ApiResult<Integer>().withError(MsgCons.C_29004, MsgCons.M_29004);
			}
			
			// 判断当前上架广告记录数
			int validCount = countValid(dto);
			if(validCount >= 8){
				return new ApiResult<Integer>().withError(MsgCons.C_29001, MsgCons.M_29001);
			}
			
			// 排序唯一判断
			boolean sortExists = sortExists(dto.getSort(), dto.getChannel(), dto.getCityId());
			if(sortExists){
				return new ApiResult<Integer>().withError(MsgCons.C_29003, MsgCons.M_29003);
			}
		}
		int result = baseDao.execute("Advertisement.updateStatus", advertiseDTO);
		return new ApiResult<Integer>().setResult(result);
	}

	@Override
	public ApiResult<AdvertisementDTO> getById(Integer id) {
		AdvertisementDTO dto = queryById(id);
		if(dto != null){
			Date endTime = dto.getEndTime();
			if(new Date().after(endTime)){
				dto.setStatus(AdStatusEnum.EXPIRED.getCode());
			}
		}
		return new ApiResult<AdvertisementDTO>().setResult(dto);
	}

	@Override
	public ApiResult<Integer> update(AdvertisementDTO advertiseDTO) {
		// 广告名称唯一判断
		AdvertisementDTO dto = queryById(advertiseDTO.getId()); //修改前广告信息
		if(dto != null && !dto.getName().equals(advertiseDTO.getName())){
			boolean nameExists = exists(advertiseDTO.getName(), advertiseDTO.getChannel(), dto.getCityId());
			if(nameExists){
				return new ApiResult<Integer>().withError(MsgCons.C_29002, MsgCons.M_29002);
			}
		}
		
		// 排序唯一判断
		boolean sortExists = sortExists(advertiseDTO.getSort(), advertiseDTO.getChannel(), dto.getCityId());
		if(sortExists){
			return new ApiResult<Integer>().withError(MsgCons.C_29003, MsgCons.M_29003);
		}
		
		int result = baseDao.execute("Advertisement.update", advertiseDTO);
		return new ApiResult<Integer>().setResult(result);
	}
	
	/**
	 * 根据id获取广告信息详情
	 * @param id
	 * @return
	 */
	private AdvertisementDTO queryById(Integer id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		AdvertisementDTO dto = baseDao.queryForObject("Advertisement.getById", paramMap, AdvertisementDTO.class);
		return dto;
	}
	
	/**
	 * 查询当前城市状态为上架的广告数
	 * @param advertiseDTO
	 * @return
	 */
	private int countValid(AdvertisementDTO advertiseDTO){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String nowDate = DateUtil.toString(new Date(), "yyyy-MM-dd HH:mm:ss");
		paramMap.put("cityId", advertiseDTO.getCityId());
		paramMap.put("nowDate", nowDate);
		paramMap.put("channel", advertiseDTO.getChannel());
		return baseDao.queryForObject("Advertisement.countValid", paramMap, Integer.class);
	}

	/**
	 * 判断广告是否已存在
	 * 广告名称在同一广告位广告中，数值唯一
	 * @param advertiseDTO
	 * @return
	 */
	private boolean exists(String name, Byte channel, Integer cityId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("cityId", cityId);
		map.put("channel", channel);
		int result = baseDao.queryForObject("Advertisement.exists", map, Integer.class);
		return result > 0;
	}
	
	/**
	 * 判断排序数字是否存在
	 * @param entity
	 * @return
	 */
	private boolean sortExists(Integer sort, Byte channel, Integer cityId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", sort);
		map.put("cityId", cityId);
		map.put("channel", channel);
		String nowTime = DateUtil.toString(new Date(), "yyyy-MM-dd HH:mm:ss");
		map.put("nowTime", nowTime);
		int result = baseDao.queryForObject("Advertisement.sortExists", map, Integer.class);
		return result > 0;
	}
	
	@Override
	public ApiResult<List<TreeNode>> queryForInitTree() {
		// 有广告的省份id列表
		List<String> provinceIdList = baseDao.queryForList("Advertisement.queryProvinceIdList", null, String.class);
		
		List<AreaDTO> provinceList = cacheBo.getParentTree(baseDao);
		
		AreaDTO defaultArea = new AreaDTO();
		defaultArea.setAreaID("0");
		defaultArea.setArea("默认");
		if(provinceList == null){
			provinceList = new ArrayList<AreaDTO>();
		}
		provinceList.add(0, defaultArea);
		
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		for(AreaDTO dto : provinceList){
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("level", 1); //省
			if(CollectionUtils.isNotEmpty(provinceIdList) && provinceIdList.contains(dto.getAreaID())){
				attributes.put("hasAd", true);//节点下面是否有广告
			}
			
			TreeNode treeNode = new TreeNode(dto.getAreaID(), dto.getArea());
			if(dto.getIsParent()){
				treeNode.setState("closed");
			}
			treeNode.setAttributes(attributes);
			treeNodeList.add(treeNode);
		}
		
		TreeNode topNode = new TreeNode("", "农速通广告管理系统");
		topNode.setChildren(treeNodeList);
		
		List<TreeNode> topTreeNodeList = new ArrayList<TreeNode>();
		topTreeNodeList.add(topNode);
		return new ApiResult<List<TreeNode>>().setResult(topTreeNodeList);
	}

	@Override
	public ApiResult<List<TreeNode>> queryAreaNodeByPid(String pid) {
		List<AreaDTO> list = cacheBo.listChildArea(pid, baseDao);
		
		// 有广告的城市id列表
		List<String> cityIdList = baseDao.queryForList("Advertisement.queryCityIdList", null, String.class);
		
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		for(AreaDTO dto : list){
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("level", 2); //市
			if(CollectionUtils.isNotEmpty(cityIdList) && cityIdList.contains(dto.getAreaID())){
				attributes.put("hasAd", true);//节点下面是否有广告
			}
			
			TreeNode treeNode = new TreeNode(dto.getAreaID(), dto.getArea());
			treeNode.setAttributes(attributes);
			
			treeNodeList.add(treeNode);
		}
		return new ApiResult<List<TreeNode>>().setResult(treeNodeList);
	}

	@Override
	public ApiResult<List<AdvertisementDTO>> listByConditions(Map<String, Object> map) {
		List<AdvertisementDTO> list = baseDao.queryForList("Advertisement.listByConditions", map, AdvertisementDTO.class);
		return new ApiResult<List<AdvertisementDTO>>().setResult(list);
	}

	@Override
	@Transactional
	public ApiResult<String> syncAd(Integer cityId, Integer[] adIds, String loginUserId) {
		if (cityId == null) {
			return new ApiResult<String>().withError(MsgCons.C_29007, MsgCons.M_29007);
		}
		
		ApiResult<String> result = new ApiResult<String>();
		if (adIds == null) {
			return result;
		}
		
		// 获取城市
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("areaID", cityId);
		AreaDTO cityDTO = baseDao.queryForObject("Area.getByAreaId", paramMap, AreaDTO.class);
		if (cityDTO == null) {
			return new ApiResult<String>().withError(MsgCons.C_29007, MsgCons.M_29007);
		}
		
		// 同步广告信息
		for (Integer adId : adIds) {
			AdvertisementDTO dto = queryById(adId);
			if (dto != null) {
				AdvertisementEntity entity = new AdvertisementEntity();
				
				if (StringUtils.isNotBlank(cityDTO.getFather())) {
					entity.setProvinceId(Integer.parseInt(cityDTO.getFather()));
				}
			
				if (StringUtils.isNotBlank(cityDTO.getAreaID())) {
					entity.setCityId(Integer.parseInt(cityDTO.getAreaID()));
				}
				
				entity.setName(dto.getName());
				entity.setImgUrl(dto.getImgUrl());
				entity.setChannel(dto.getChannel());
				entity.setSort(dto.getSort());
				entity.setStatus(AdStatusEnum.OFF.getCode());
				entity.setStartTime(dto.getStartTime());
				entity.setEndTime(dto.getEndTime());
				entity.setCreateTime(new Date());
				entity.setCreateUserId(loginUserId);
				baseDao.persist(entity, Long.class);
			}
		}
		return result;
	}
	
}

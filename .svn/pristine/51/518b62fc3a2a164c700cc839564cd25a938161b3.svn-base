package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminAdBannerDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.AdBannerService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.AdBannerEntity;
import cn.gdeng.nst.enums.AdBannerStateEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

@Service
public class AdBannerServiceImpl implements AdBannerService{

	@Autowired
	private BaseDao<?> baseDao;

	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("AdBanner.countTotal", paramMap, Integer.class);
		
		List<AdminAdBannerDTO> list = null;
		if(total > 0){
			list = baseDao.queryForList("AdBanner.queryListByPage", paramMap, AdminAdBannerDTO.class);
		}else{
			list = Collections.emptyList();
		}
		AdminPageDTO page = new AdminPageDTO(total, list);
		return new ApiResult<AdminPageDTO>().setResult(page);
	}

	@Override
	public ApiResult<Long> persist(AdBannerEntity entity) {
		Long id = baseDao.persist(entity, Long.class);	
		return new ApiResult<Long>().setResult(id);
	}

	@Override
	@Transactional
	public ApiResult<int[]> batchDelete(String[] ids, String loginUserId) {
		@SuppressWarnings("unchecked")
		Map<String, Object>[] batchValues = new HashMap[ids.length];
		for(int i = 0, len = batchValues.length; i < len; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", Integer.parseInt(ids[i]));
			map.put("updateUserId", loginUserId);
			map.put("state", AdBannerStateEnum.DELETE.getCode());
			batchValues[i] = map;
		}
		int[] result = baseDao.batchUpdate("AdBanner.updateState", batchValues);
		return new ApiResult<int[]>().setResult(result);
	}

	@Override
	public ApiResult<Integer> updateState(Map<String, Object> paramMap) {
		int result = baseDao.execute("AdBanner.updateState", paramMap);
		return new ApiResult<Integer>(result, MsgCons.C_10000, MsgCons.M_10000);
	}

	@Override
	public ApiResult<AdminAdBannerDTO> getById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		AdminAdBannerDTO bannerDTO = baseDao.queryForObject("AdBanner.getById", paramMap, AdminAdBannerDTO.class);
		return new ApiResult<AdminAdBannerDTO>().setResult(bannerDTO);
	}

	@Override
	public ApiResult<Integer> update(AdminAdBannerDTO adBannerDTO) {
		int result = baseDao.execute("AdBanner.update", adBannerDTO);
		return new ApiResult<Integer>().setResult(result);
	}
}

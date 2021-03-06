package cn.gdeng.nst.admin.service.admin;

import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminAdBannerDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.entity.nst.AdBannerEntity;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 广告轮播图（废弃）
 * @author jfdeng
 *
 */
public interface AdBannerService {

	ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap);
	
	ApiResult<Long> persist(AdBannerEntity entity);
	
	ApiResult<int[]> batchDelete(String[] ids, String loginUserId);
	
	ApiResult<Integer> updateState(Map<String, Object> paramMap);
	
	ApiResult<AdminAdBannerDTO> getById(Integer id);

	ApiResult<Integer> update(AdminAdBannerDTO adBannerDTO);
}

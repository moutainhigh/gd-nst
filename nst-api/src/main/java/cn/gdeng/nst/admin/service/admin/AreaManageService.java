package cn.gdeng.nst.admin.service.admin;

import java.util.List;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

public interface AreaManageService {

	
	public ApiResult<AdminPageDTO> queryTopArea();
	public ApiResult<AdminPageDTO> listChildArea(String map);
	
	ApiResult<List<AreaDTO>> queryTopList();
	
	ApiResult<List<AreaDTO>> queryChildList(String parentId);
}

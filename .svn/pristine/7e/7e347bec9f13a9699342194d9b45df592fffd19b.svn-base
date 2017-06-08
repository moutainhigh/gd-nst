package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberCarDTO;
import cn.gdeng.nst.api.dto.member.MemberCarApiDTO;
import cn.gdeng.nst.entity.nst.MemberCarEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface MemberCarManageService {
	/**
	 * 根据条件查询list(分页查询)
	 * 
	 * @param map
	 * @return List<CarsDTO>
	 */
	public List<MemberCarEntity> getByConditionPage(Map<String,Object> map)throws Exception;
	
	public int  getByConditionCount(Map<String, Object> map)throws Exception;
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> map);
	public ApiResult<MemberCarDTO>  getMemberCarDetail(Map<String, Object> map);
	
	public ApiResult<Integer>  updateMemberCarById(Map<String, Object> map);
	public ApiResult<Integer> queryMemberCarNumber(Map<String, Object> map);
	public ApiResult<Integer> queryMemberCarCount(Map<String, Object> map);
	
	/**
	 * 
	 * @Description: 根据ID删除车辆
	 * @param map
	 * @return
	 *
	 */
	public ApiResult<Integer>  deleteMemberCarById(Map<String, Object> map);
	

	
}

package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberInfoDTO;
import cn.gdeng.nst.admin.dto.admin.RuleOnoffDTO;
import cn.gdeng.nst.admin.dto.admin.SourceAssignHisDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

public interface MemberInfoManageService {
	/**
	 * 根据条件查询list(分页查询)
	 * 
	 * @param map
	 * @return List<CarsDTO>
	 */
	public List<MemberInfoDTO> getByConditionPage(Map<String,Object> map)throws Exception;
	
	public int  getByConditionCount(Map<String, Object> map)throws Exception;
	
	public ApiResult<MemberInfoDTO>  getMemberInfoDetail(Map<String, Object> map);
	
	public List<SourceAssignHisDTO>  getSourceAssignHisPage(Map<String, Object> map)throws Exception;
	
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> map);

	public   ApiResult<Integer> updateUserNameAndMobile(Map<String, Object> paramMap);
	
	public   ApiResult<Integer> resetStatusById(Map<String, Object> paramMap);
	
	public   ApiResult<Integer> resetPwdById(Map<String, Object> paramMap);
	
	
	public ApiResult<RuleOnoffDTO>  getMemberRuleOnoffDTODetail(Map<String, Object> map);
	
	public ApiResult<Integer> updateNstMemberInfoAndRuleOnoffAndAssign(Map<String, Object> map, String province, String city, String area) throws Exception;
	
	public ApiResult<Integer> queryMemberInfoCount(Map<String, Object> map);
} 

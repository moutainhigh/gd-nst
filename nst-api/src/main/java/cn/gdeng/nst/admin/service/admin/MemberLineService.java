package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberLineDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 会员长跑线路接口
 * @author wujiang
 *
 */
public interface MemberLineService {
	
	/**
	 * 分页查询线路
	 */
	ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap);
	
	ApiResult<MemberLineDTO> showDetail(Map<String, Object> map);
	
	ApiResult<Integer> countTotal(Map<String, Object> paramMap);
	
	ApiResult<List<MemberLineDTO>> queryList(Map<String, Object> paramMap);
	
}

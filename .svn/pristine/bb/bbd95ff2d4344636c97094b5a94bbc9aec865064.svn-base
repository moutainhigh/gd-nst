package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdminSourceAssignHisDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 分配记录service
 * @author wind
 *
 */
public interface SourceAssignHisService {

	/**
	 * 查询列表（不分页）
	 * @param paramMap
	 * @return
	 */
	ApiResult<List<AdminSourceAssignHisDTO>> queryList(Map<String, Object> paramMap);
	
	/**
	 * 分页查询：包含总记录数，列表分页数据
	 * @param paramMap
	 * @return
	 */
	ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap);
	
	/**
	 * 获取总记录数
	 * @param paramMap
	 * @return
	 */
	ApiResult<Integer> countTotal(Map<String, Object> paramMap);

	/**
	 * 获取分页列表
	 * @param paramMap
	 * @return
	 */
	ApiResult<List<AdminSourceAssignHisDTO>> queryListByPage(Map<String, Object> paramMap);
}

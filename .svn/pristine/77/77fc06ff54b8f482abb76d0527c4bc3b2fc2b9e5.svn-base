package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminOrderBeforeDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 接单记录service
 * @author jfdeng
 *
 */
public interface OrderBeforeService {

	/**
	 * 查询接单记录列表（不分页）
	 * @param paramMap
	 * @return
	 */
	ApiResult<List<AdminOrderBeforeDTO>> queryList(Map<String, Object> paramMap);
	
	/**
	 * 分页查询（包含总记录数，分页列表数据）
	 * @param paramMap
	 * @return
	 */
	ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap);
	
	/**
	 * 查询总记录数
	 * @param paramMap
	 * @return
	 */
	ApiResult<Integer> countTotal(Map<String, Object> paramMap);
	
	/**
	 * 分页数据列表查询
	 * @param paramMap
	 * @return
	 */
	ApiResult<List<AdminOrderBeforeDTO>> queryListByPage(Map<String, Object> paramMap);
}

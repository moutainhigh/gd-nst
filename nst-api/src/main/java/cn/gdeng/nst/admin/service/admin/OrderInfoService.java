package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminOrderDTO;
import cn.gdeng.nst.admin.dto.admin.AdminOrderInfoDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 货运订单service
 * @author jfdeng
 *
 */
public interface OrderInfoService {

	/**
	 * 分页查询：包含总记录数，分页列表数据
	 * @param paramMap
	 * @return
	 */
	ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap);
	
	/**
	 * 根据sourceId查询货源订单
	 * @param sourceId
	 * @return
	 */
	ApiResult<List<AdminOrderDTO>> getAdminOrderDTOBySourceId(Integer sourceId);
	
	/**
	 * 根据id获取详情
	 * @param id
	 * @return
	 */
	ApiResult<AdminOrderInfoDTO> getById(Integer id);

	/**
	 * 列表分页查询
	 * @param paramMap
	 * @return
	 */
	ApiResult<List<AdminOrderInfoDTO>> queryListByPage(Map<String, Object> paramMap);

	/**
	 * 查询总记录数
	 * @param paramMap
	 * @return
	 */
	ApiResult<Integer> countTotal(Map<String, Object> paramMap);
}

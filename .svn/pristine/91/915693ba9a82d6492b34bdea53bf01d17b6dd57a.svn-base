package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.CallstatisticsDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 *功能描述：拨打电话统计
 */
public interface CallstatisticsService{
	
	/**
	 * 分页查询拨打电话记录
	 * @param map
	 * @return
	 * @throws Exception
	 */
	ApiResult<AdminPageDTO> pageQuery(Map<String, Object> map);
	
	ApiResult<Integer> getTotal(Map<String, Object> map);
	
	ApiResult<List<CallstatisticsDTO>> queryList(Map<String, Object> map);

}

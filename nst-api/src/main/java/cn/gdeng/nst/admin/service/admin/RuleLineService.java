package cn.gdeng.nst.admin.service.admin;

import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 物流公司订阅线路Service接口
 * @author huangjianhua
 * @Date:2017年1月4日下午3:01:13
 */	
public interface RuleLineService {
	
	/**
	 * 分页查询线路
	 */
	ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap);
	
	/**
	 * 查询总记录数
	 * @param paramMap
	 * @return
	 */
	ApiResult<Integer> countTotal(Map<String, Object> paramMap);
	
}

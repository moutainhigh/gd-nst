package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.RuleInfoDTO;
import cn.gdeng.nst.entity.nst.RuleInfoEntity;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 分配规则接口
 * @author wujiang
 *
 */
public interface RuleInfoService {
	/**
	 * 分配规则信息表
	 * @param map
	 * @return
	 */
	ApiResult<AdminPageDTO> pageQuery(Map<String, Object> map);
	
	/**
	 * 新增分配规则
	 * @param map
	 * @return
	 */
	ApiResult<Long> saveAdd(RuleInfoEntity entity);
	
	ApiResult<RuleInfoDTO> getById(Integer id);
	
	ApiResult<Integer> update(Map<String, Object> map);
	
	ApiResult<Integer> countTotal(Map<String, Object> map);
	
	ApiResult<List<RuleInfoDTO>> queryList(Map<String, Object> map);
	
	ApiResult<Integer> validateRuleName(Map<String, Object> map);
}

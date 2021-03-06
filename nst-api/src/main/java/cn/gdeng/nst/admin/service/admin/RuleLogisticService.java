package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.RuleLogisticDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

public interface RuleLogisticService {
	ApiResult<List<RuleLogisticDTO>> getMemberInfoByRuleId(Integer id);
	ApiResult<String> insertBatch(String[] ids, String ruleInfoId, String createUserId, String[] levels,String[] memberTypeArray,String[] totalLimtArray,String[] dayLimtArray,String[] startTimeArray,String[] endTimeArray,Integer onOff);
	ApiResult<String> updateBatch(String[] ids, String ruleInfoId, String updateUserId, String[] levels,String[] memberTypeArray,String[] totalLimtArray,String[] dayLimtArray,String[] startTimeArray,String[] endTimeArray);
	ApiResult<int[]> batchDelete(String[] ids, String ruleInfoId);
	ApiResult<AdminPageDTO> companyQueryPage(Map<String, Object> map);
	ApiResult<Integer> updateOnOffBatch(Map<String, Object> map);
	ApiResult<Integer> queryRuleLogisticByRuleIdTotal(Map<String, Object> map);
	ApiResult<List<RuleLogisticDTO>> getMemberInfoByRuleIdPage(Map<String, Object> map);
}

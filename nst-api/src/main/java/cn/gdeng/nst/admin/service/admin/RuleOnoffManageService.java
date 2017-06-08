package cn.gdeng.nst.admin.service.admin;

import java.util.Map;

import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface RuleOnoffManageService {
	public ApiResult<Integer>  updateRuleOnoffById(Map<String, Object> map) throws BizException;
}

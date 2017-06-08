package cn.gdeng.nst.api.server.member;

import java.util.List;

import cn.gdeng.nst.api.dto.member.RuleLineApiDTO;
import cn.gdeng.nst.api.vo.member.RuleLineVo;
import cn.gdeng.nst.entity.nst.RuleLineEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

public interface RuleLineApiService {

	public ApiResult<List<RuleLineVo>>  ruleLineList(RuleLineApiDTO ruleLineApiDTO) throws BizException;
	
	public ApiResult<Integer>  deleteRuleLineById(RuleLineApiDTO ruleLineApiDTO) throws BizException;

	public ApiResult<Long>  saveRuleLine(RuleLineEntity ruleLineEntity) throws BizException;
}

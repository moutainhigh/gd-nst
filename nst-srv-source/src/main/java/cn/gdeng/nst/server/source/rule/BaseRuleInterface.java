package cn.gdeng.nst.server.source.rule;

import cn.gdeng.nst.api.dto.source.RuleDTO;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 分配规则基础接口
 * @author xiaojun
 */
public interface BaseRuleInterface {
	/**
	 * 货源分配
	 * @param dto
	 * @return
	 * @throws BizException
	 */
	boolean isAssign(RuleDTO dto) throws BizException;
}

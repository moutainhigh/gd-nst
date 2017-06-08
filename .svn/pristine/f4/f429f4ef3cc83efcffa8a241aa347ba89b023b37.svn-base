package cn.gdeng.nst.server.source.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.gdeng.nst.api.dto.source.RuleDTO;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 货主指派规则
 * 
 * @author xiaojun
 */
@Service("shipperAssignRule")
public class ShipperAssignRule extends SourceAssignRule implements BaseRuleInterface {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 规则分配成功常量
	 */
	private static final boolean RULE_TRUE = true;

	@Override
	public boolean isAssign(RuleDTO dto) throws BizException {
		if (logger.isDebugEnabled()) {
			logger.debug("货主指派货源分配开始==============货源id：" + dto.getSourceId());
		}
		dto.setRuleType(0);
		dto.setSourceGoodsType(3);
		this.assignOperation(dto);
		if (logger.isDebugEnabled()) {
			logger.debug("货主指派货源分配成功结束==============货源id：" + dto.getSourceId());
		}
		return RULE_TRUE;
	}

}

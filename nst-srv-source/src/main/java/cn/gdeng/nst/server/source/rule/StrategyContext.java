package cn.gdeng.nst.server.source.rule;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 分配规则上下文
 * 
 * @author xiaojun
 *
 */
@Component
public class StrategyContext implements ApplicationContextAware {

	private static BaseRuleInterface baseRuleInterface;
	/**
	 * Spring应用上下文环境
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 获取相应分配规则
	 * 
	 * @param ruleCode
	 * @return
	 */
	public static BaseRuleInterface getAssginRule(String ruleCode) {
		baseRuleInterface = (BaseRuleInterface) applicationContext.getBean(AssignRule.getAssignRule(ruleCode));
		return baseRuleInterface;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		StrategyContext.applicationContext = applicationContext;
	}

}

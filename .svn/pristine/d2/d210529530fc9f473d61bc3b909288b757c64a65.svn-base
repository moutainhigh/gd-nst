package cn.gdeng.nst.server.source.rule;

/**
 * 规则枚举类
 * @author xiaojun
 *
 */
public enum AssignRule {
	/**
	 * 无分配规则
	 */
	NORULEASSIGN("-1",""),
	/**
	 * 货主指派
	 */
	SHIPPERASSIGN("1","shipperAssignRule"),
	/**
	 * 物流规则
	 */
	LOGISTICSASSIGN("2","logisticsAssignRule");
	
	private String code;
	private String rule;
	
	private AssignRule(String code,String rule) {
		this.code=code;
		this.rule=rule;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	public static String getAssignRule(String code){
		AssignRule[] values = AssignRule.values();
		for(AssignRule val : values){
			if(val.getCode().equals(code)){
				return val.getRule();
			}
		}
		return null;
	}
	
	
}

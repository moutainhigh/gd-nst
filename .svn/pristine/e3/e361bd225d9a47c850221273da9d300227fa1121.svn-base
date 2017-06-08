package cn.gdeng.nst.enums;

public enum RuleInfoEnableEnum {
	
	NO((byte)0, "未启用"),
	ENABLED((byte)1, "已启用"),
	DISABLED((byte)2, "已禁用");

	private Byte code;
	
	private String name;

	private RuleInfoEnableEnum(Byte code, String name) {
		this.code = code;
		this.name = name;
	}

	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(Byte code){
		RuleInfoEnableEnum[] values = RuleInfoEnableEnum.values();
		for(RuleInfoEnableEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

package cn.gdeng.nst.enums;

public enum NstRuleOnoffEnum {
	
	on((byte)2, "开"),
	off((byte)1, "关");

	private Byte code;
	
	private String name;

	private NstRuleOnoffEnum(Byte code, String name) {
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
		NstRuleOnoffEnum[] values = NstRuleOnoffEnum.values();
		for(NstRuleOnoffEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

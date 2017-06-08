package cn.gdeng.nst.enums;

public enum CallServiceTypeEnum {
	GXYW((byte)1, "干线业务"),
	TCYW((byte)2, "同城业务");
	
	private Byte code;
	
	private String name;
	
	CallServiceTypeEnum(Byte code, String name){
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
		CallServiceTypeEnum[] values = CallServiceTypeEnum.values();
		for(CallServiceTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

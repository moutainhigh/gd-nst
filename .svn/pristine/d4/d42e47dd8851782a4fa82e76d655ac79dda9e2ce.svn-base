package cn.gdeng.nst.enums;

public enum CallRoleEnum {
	
	NSTHZ((byte)0, "货主"),
	NSTSJ((byte)1, "车主"),
	NSTWLGS((byte)2, "物流公司"),
	NSYNPS((byte)3, "农商友-农批商"),
	GYS((byte)4, "供应商"),
	NSY((byte)5, "农商友");

	private Byte code;
	
	private String name;
	
	CallRoleEnum(Byte code, String name){
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
		CallRoleEnum[] values = CallRoleEnum.values();
		for(CallRoleEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

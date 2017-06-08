package cn.gdeng.nst.enums;

public enum AppTypeEnum {

	APPHZ((byte)2, "货主APP"),
	APPWL((byte)3, "物流公司APP"),
	APPCZ((byte)1, "车主APP");
	private Byte code;
	
	private String name;

	private AppTypeEnum(Byte code, String name) {
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
		AppTypeEnum[] values = AppTypeEnum.values();
		for(AppTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

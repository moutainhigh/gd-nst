package cn.gdeng.nst.enums;

public enum AdStatusEnum {

	ON((byte)1, "上架"),
	OFF((byte)2, "下架"),
	EXPIRED((byte)3, "过期");
	
	private Byte code;
	
	private String name;

	private AdStatusEnum(Byte code, String name) {
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
		AdStatusEnum[] values = AdStatusEnum.values();
		for(AdStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

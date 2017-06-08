package cn.gdeng.nst.enums;

public enum AdChannelEnum {

	NST_DRIVER((byte)1, "车主APP"),
	NST_CONSIGNOR((byte)2, "货主APP"),
	NST_AGENT((byte)3, "物流公司APP");
	
	private Byte code;
	
	private String name;
	
	private AdChannelEnum(Byte code, String name){
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
		AdChannelEnum[] values = AdChannelEnum.values();
		for(AdChannelEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

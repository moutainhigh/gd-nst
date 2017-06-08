package cn.gdeng.nst.enums;

public enum SourceClientsEnum {

	NST_CONSIGNOR((byte)2, "农速通-货主"),
	NST_AGENT((byte)3, "农速通-物流公司"),
	NSY((byte)4, "农商友"),
	NSY_NPS((byte)5, "农商友-农批商"),
	NSY_GYS((byte)6, "农商友-供应商");
	
	private Byte code;
	
	private String name;
	
	private SourceClientsEnum(Byte code, String name) {
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
		if(code == null) {
			return null;
		}
		SourceClientsEnum[] values = SourceClientsEnum.values();
		for(SourceClientsEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

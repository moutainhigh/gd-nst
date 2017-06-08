package cn.gdeng.nst.enums;

public enum MemberInfoStatusEnum {

	DISABLE((byte)0, "未启用"),
	ENABLE((byte)1, "启用");
	
	private Byte code;
	
	private String name;

	private MemberInfoStatusEnum(Byte code, String name) {
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
		MemberInfoStatusEnum[] values = MemberInfoStatusEnum.values();
		for(MemberInfoStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

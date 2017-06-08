package cn.gdeng.nst.enums;

public enum SourceStatusClientEnum {
	/**	已发布	*/
	RELEASED((byte)1, "待确认"),
	/**待确认		*/
	STAY_FOR_CONFIRM((byte)2, "已确认"),
	/**已接受		*/
	ACCEPTED((byte)3, "已完成"),
	/**	已过期	*/
	EXPIRE((byte)4, "已关闭"),
	/**	平台配送关闭	*/
	PLATFORM_CLOSE((byte)5, "平台配送关闭");

	private Byte code;
	
	private String name;

	private SourceStatusClientEnum(Byte code, String name) {
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
		SourceStatusClientEnum[] values = SourceStatusClientEnum.values();
		for(SourceStatusClientEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

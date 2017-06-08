package cn.gdeng.nst.enums;

public enum NstNoticeOnOffEnum {

	DFB((byte)0, "待发布"),
	JY((byte)2, "启用"),
	QY((byte)1, "禁用");
	private Byte code;
	
	private String name;

	private NstNoticeOnOffEnum(Byte code, String name) {
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
		NstNoticeOnOffEnum[] values = NstNoticeOnOffEnum.values();
		for(NstNoticeOnOffEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

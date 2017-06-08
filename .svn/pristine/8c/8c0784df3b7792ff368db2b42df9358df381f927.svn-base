package cn.gdeng.nst.enums;

/**
 * 验货状态
 *
 */
public enum SourceExamineStatusEnum {
	
	PASS((byte)1, "通过"),
	NOT_PASS((byte)2, "不通过");

	private Byte code;
	
	private String name;

	private SourceExamineStatusEnum(Byte code, String name) {
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
		SourceExamineStatusEnum[] values = SourceExamineStatusEnum.values();
		for(SourceExamineStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return "";
	}
}

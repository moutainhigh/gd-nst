package cn.gdeng.nst.enums;

public enum SourceTypeEnum {
	
	TRUNK(1, "干线"),
	CITY_WIDE(2, "同城");

	private Integer code;
	
	private String name;
	
	private SourceTypeEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(Integer code){
		SourceTypeEnum[] values = SourceTypeEnum.values();
		for(SourceTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

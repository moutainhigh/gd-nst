package cn.gdeng.nst.enums;

public enum AdBannerStateEnum {
	
	ENABLED(1, "启用"),
	DISABLED(2, "禁用"),
	DELETE(3, "删除");

	private Integer code;
	
	private String name;
	
	AdBannerStateEnum(Integer code, String name){
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
		AdBannerStateEnum[] values = AdBannerStateEnum.values();
		for(AdBannerStateEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

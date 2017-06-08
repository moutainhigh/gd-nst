package cn.gdeng.nst.enums;

public enum AdBannerTypeEnum {

	BANNER(1, "轮播图");
	
	private Integer code;
	
	private String name;
	
	AdBannerTypeEnum(Integer code, String name){
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
		AdBannerTypeEnum[] values = AdBannerTypeEnum.values();
		for(AdBannerTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

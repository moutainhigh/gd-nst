package cn.gdeng.nst.enums;

public enum OrderInfoTypeEnum {
	
	TRUNK(1, "干线订单"),
	CITY_WIDE(2, "同城订单");

	private Integer code;
	
	private String name;
	
	private OrderInfoTypeEnum(Integer code, String name){
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
		OrderInfoTypeEnum[] values = OrderInfoTypeEnum.values();
		for(OrderInfoTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

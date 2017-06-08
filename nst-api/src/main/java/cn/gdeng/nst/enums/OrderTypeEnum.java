package cn.gdeng.nst.enums;

public enum OrderTypeEnum {
	
	ORDER_INFO(1, "货运订单"),
	ORDER_AGENT(2, "信息订单");

	private Integer code;
	
	private String name;
	
	private OrderTypeEnum(Integer code, String name) {
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
		OrderTypeEnum[] values = OrderTypeEnum.values();
		for(OrderTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

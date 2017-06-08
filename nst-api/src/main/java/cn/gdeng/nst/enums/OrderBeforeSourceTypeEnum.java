package cn.gdeng.nst.enums;

public enum OrderBeforeSourceTypeEnum {
	
	TRUNK((byte)1, "干线订单"),
	CITY_WIDE((byte)2, "同城订单");

	private Byte code;
	
	private String name;
	
	private OrderBeforeSourceTypeEnum(Byte code, String name){
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
		OrderBeforeSourceTypeEnum[] values = OrderBeforeSourceTypeEnum.values();
		for(OrderBeforeSourceTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

package cn.gdeng.nst.enums;

public enum PayDetailOrderTypeEnum {
	
	ORDER_AGNET((short)1, "信息订单交易详情"),
	ORDER_INFO((short)2, "货源订单交易详情");

	private Short code;
	
	private String name;

	private PayDetailOrderTypeEnum(Short code, String name) {
		this.code = code;
		this.name = name;
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

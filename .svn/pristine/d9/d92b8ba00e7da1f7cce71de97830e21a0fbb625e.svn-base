package cn.gdeng.nst.enums;

public enum OrderInfoStatusEnum {
	/**订单确认*/
	ORDER_CONFIRM((byte)1, "待收货"),
	/**确认收货(货主确认、7天自动确认、平台配置已支付尾款)*/
	GOODS_CONFIRM((byte)2, "已完成"),
	/**已关闭*/
	CLOSED((byte)3, "已关闭");

	private Byte code;
	
	private String name;

	private OrderInfoStatusEnum(Byte code, String name) {
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
		OrderInfoStatusEnum[] values = OrderInfoStatusEnum.values();
		for(OrderInfoStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

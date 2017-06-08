package cn.gdeng.nst.enums;
/**
* @author DJB
* @version 创建时间：2016年12月12日 下午3:43:09
* 类说明
*/

public enum OrderInfoOrderStatusEnum {
	/**订单确认		*/
	ORDER_CONFIRM((byte)1, "已确认"),
	/**确认收货		*/
	GOODS_CONFIRM((byte)2, "已完成"),
	/**已关闭		*/
	CLOSED((byte)3, "已关闭");

	private Byte code;
	
	private String name;

	private OrderInfoOrderStatusEnum(Byte code, String name) {
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
		OrderInfoOrderStatusEnum[] values = OrderInfoOrderStatusEnum.values();
		for(OrderInfoOrderStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}

}

package cn.gdeng.nst.enums;

/** 支付预付款状态枚举
 * 
 * @author wjguo
 *
 * datetime:2016年12月13日 下午3:25:38
 */
public enum OrderInfoPrePayStatus {
	/**未支付
	 * 
	 */
	PENDING_PAY((byte)0, "未支付"),
	/**已支付
	 * 
	 */
	PAID((byte)1, "已支付");

	private Byte code;
	
	private String name;

	private OrderInfoPrePayStatus(Byte code, String name) {
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
		OrderInfoPrePayStatus[] values = OrderInfoPrePayStatus.values();
		for(OrderInfoPrePayStatus val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

package cn.gdeng.nst.enums;

public enum OrderAgentStatusEnum2 {
	/**待确认		*/
	WAITCONFIRM((byte)1,"待确认"),
	/**已确认		*/
	CONFIRMED((byte)2, "已完成"),
	/**车主取消		*/
	COMPLETED((byte)3, "已关闭"),
	/**物流公司拒绝*/
	COMPANY_CANCEL((byte)4, "已关闭");
	
	private Byte code;
	
	private String name;

	private OrderAgentStatusEnum2(Byte code, String name) {
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
		OrderAgentStatusEnum2[] values = OrderAgentStatusEnum2.values();
		for(OrderAgentStatusEnum2 val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}

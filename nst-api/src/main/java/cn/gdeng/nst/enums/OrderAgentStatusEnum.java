package cn.gdeng.nst.enums;

public enum OrderAgentStatusEnum {
	/**待确认		*/
	WAITCONFIRM((byte)1,"待确认"),
	/**已确认		*/
	CONFIRMED((byte)2, "已完成"),
	/**已关闭(车主取消)		*/
	COMPLETED((byte)3, "已关闭"),
	/**已关闭(物流公司拒绝)		*/
	COMPANY_CANCEL((byte)4, "已关闭");
	
	private Byte code;
	
	private String name;

	private OrderAgentStatusEnum(Byte code, String name) {
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
		OrderAgentStatusEnum[] values = OrderAgentStatusEnum.values();
		for(OrderAgentStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}

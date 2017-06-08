package cn.gdeng.nst.enums;

public enum PayStatusEnum {
	NOT_PAY((short)1, "待支付"),
	PAID((short)2, "支付成功"),
	/**已关闭*/
	CLOSED((short)3, "待支付"),
	REFUND((short)4, "已退款");

	private Short code;
	
	private String name;

	private PayStatusEnum(Short code, String name) {
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
	
	public static String getNameByCode(Short code){
		if(code==null){
			return PayStatusEnum.NOT_PAY.getName();
		}
		PayStatusEnum[] values = PayStatusEnum.values();
		for(PayStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}

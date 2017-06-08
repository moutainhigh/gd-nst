package cn.gdeng.nst.enums;

public enum PayTypeEnum {
	
	WEI_XIN("WEIXIN_APP", "微信"),
	ZHI_FU_BAO("ALIPAY_H5", "支付宝"),
	PING_AN("PINAN", "平安");

	private String code;
	
	private String name;

	private PayTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(String code){
		PayTypeEnum[] values = PayTypeEnum.values();
		for(PayTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

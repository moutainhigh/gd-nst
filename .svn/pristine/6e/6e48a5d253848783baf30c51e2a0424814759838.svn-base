package cn.gdeng.nst.enums;

public enum CallSourceEnum {

	WLGSZC((byte)1, "物流公司找车"),
	WLGSFGWDH((byte)2, "物流公司分给我的货"),
	WLGSDD((byte)3, "物流公司订单"),
	CZZH((byte)4, "车主找货"),
	CZZD((byte)5, "车主订单"),
	HZWFDH((byte)6, "货主我发的货"),
	HZYD((byte)7, "货主运单"),
	NSYWLGL((byte)8, "农商友物流管理"),
	NSYDDXQ((byte)9, "农商友订单详情"),
	NPSWLGL((byte)10, "农批商物流管理"),
	NPSDDXQ((byte)11, "农批商订单详情"),
	GYSWLGL((byte)12, "供应商物流管理");
	
	private Byte code;
	
	private String name;
	
	CallSourceEnum(Byte code, String name){
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
		CallSourceEnum[] values = CallSourceEnum.values();
		for(CallSourceEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

package cn.gdeng.nst.enums;

public enum SendGoodsTypeEnum {
	UNLIMITED((byte)1, "不限"),
	WHOLE_TRUCK_LOAD((byte)2, "整车"),
	LESS_THAN_TRUCK_LOAD((byte)3, "零担");
	
	private Byte code;
	
	private String name;

	private SendGoodsTypeEnum(Byte code, String name) {
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
		if(code == null) {
			return null;
		}
		SendGoodsTypeEnum[] values = SendGoodsTypeEnum.values();
		for(SendGoodsTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}

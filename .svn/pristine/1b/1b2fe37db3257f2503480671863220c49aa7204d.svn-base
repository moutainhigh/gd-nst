package cn.gdeng.nst.enums;

public enum MemberInfoRegetypeEnum {

	GLHT((byte)0, "管理后台"),
	GDNPW((byte)1, "谷登农批"),
	NSY((byte)3, "农商友"),
	NSYNPS((byte)4, "农商友-农批商"),
	NPY((byte)5, "农批友"),
	NSYGYS((byte)6, "农商友-供应商"),
	POSSK((byte)7, "POS刷卡"),
	WXSQ((byte)8, "微信授权"),
	NSTHZ((byte)9, "农速通-货主"),
	NSTSJ((byte)10, "农速通-车主"),
	NSTWLGS((byte)11, "农速通-物流公司");
	
	
	

	
	private Byte code;
	
	private String name;

	private MemberInfoRegetypeEnum(Byte code, String name) {
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
		MemberInfoRegetypeEnum[] values = MemberInfoRegetypeEnum.values();
		for(MemberInfoRegetypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

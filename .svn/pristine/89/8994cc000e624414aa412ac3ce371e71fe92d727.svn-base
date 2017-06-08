package cn.gdeng.nst.enums;

/**
 * 货源类型
 * @author dengjianfeng
 *
 */
public enum SourceGoodsTypeEnum {
	
	SELF((byte)1, "自有"),
	ASSIGN((byte)2, "分配"),
	APPOINT((byte)3, "指派");
	
	private Byte code;
	
	private String name;
	
	private SourceGoodsTypeEnum(Byte code, String name){
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
		SourceGoodsTypeEnum[] values = SourceGoodsTypeEnum.values();
		for(SourceGoodsTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

package cn.gdeng.nst.enums;
/**
* @author DJB
* @version 创建时间：2016年8月4日 下午2:26:07
* 是否选中枚举
*/

public enum IsSelectEnum {
	
	NOT_SELECT((byte)0, "未选中"),
	SELECT((byte)1, "已选中");
	
	private IsSelectEnum(Byte code, String name) {
		this.code = code;
		this.name = name;
	}

	private Byte code;
	
	private String name;

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
		IsSelectEnum[] values = IsSelectEnum.values();
		for(IsSelectEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}

}

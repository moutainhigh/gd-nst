package cn.gdeng.nst.enums;
/**
* @author DJB
* @version 创建时间：2016年8月8日 下午2:51:33
* 农速通企业与个人 认证状态枚举
*/

public enum MemberCerStatusEnum {
	NO_CER((byte)0, "待认证"),
	IN_CER((byte)1, "认证中"),
	PASS_CER((byte)2, "已认证"),
	FAIL_CER((byte)3, "已驳回");
	
	
	
	
	private Byte code;
	
	private String name;
	
	
	private MemberCerStatusEnum(Byte code, String name) {
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
		MemberCerStatusEnum[] values = MemberCerStatusEnum.values();
		for(MemberCerStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	

}

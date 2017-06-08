package cn.gdeng.nst.enums;

public enum NsyAssignEnum {
	
	ASSIGNED(1, "已分配物流公司"),
	NOASSIGN(0, "未分配物流公司");

	private Integer code;
	
	private String name;
	
	NsyAssignEnum(Integer code, String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(Integer code){
		NsyAssignEnum[] values = NsyAssignEnum.values();
		for(NsyAssignEnum val : values){
			if(val.getCode().equals(code)){
				return val.name;
			}
		}
		return null;
	}
}

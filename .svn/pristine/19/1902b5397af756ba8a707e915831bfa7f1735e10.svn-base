package cn.gdeng.nst.enums;

/**货源分配历史表状态枚举
 * @author wjguo
 * datetime 2016年8月10日 下午6:58:41  
 *
 */
public enum SourceAssignHisStatusEnum {
	STAY_FOR_ACCEPTION((byte)1, "待接受"),
	
	ACCEPTED((byte)2, "接受"),
	
	REJECTED((byte)3, "拒绝"),
	
	OVERDUE((byte)4, "超时"),
	
	CANCEL((byte)5, "取消");
	
	private Byte code;
	
	private String name;

	private SourceAssignHisStatusEnum(Byte code, String name) {
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
	
	/**根据code码获取对应的名称
	 * @param code
	 * @return
	 */
	public static String getNameByCode(Byte code){
		if(code == null) {
			return null;
		}
		SourceAssignHisStatusEnum[] values = SourceAssignHisStatusEnum.values();
		for(SourceAssignHisStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}

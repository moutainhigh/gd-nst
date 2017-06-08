package cn.gdeng.nst.enums;

/**货源分配历史表状态枚举
 * @author wjguo
 * datetime 2016年8月10日 下午6:58:41  
 *
 */
public enum AdminSourceAssignHisStatusEnum {
	
	/**待接受*/
	STAY_FOR_ACCEPTION((byte)1, ""),
	
	/**已接受 */
	ACCEPTED((byte)2, "接受"),
	
	/**已拒绝 */
	REJECTED((byte)3, "拒绝"),
	
	/**已失效 */
	OVERDUE((byte)4, "超时");
	
	private Byte code;
	
	private String name;

	private AdminSourceAssignHisStatusEnum(Byte code, String name) {
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
		AdminSourceAssignHisStatusEnum[] values = AdminSourceAssignHisStatusEnum.values();
		for(AdminSourceAssignHisStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
}

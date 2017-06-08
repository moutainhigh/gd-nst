package cn.gdeng.nst.enums;

/**农速通分配标识，此枚举用作展示。
 * @author wjguo
 * datetime 2016年8月26日 下午4:08:26  
 *
 */
public enum NstRuleEnum2 {
	
	/**货主直发*/
	SELF((byte)1, "自有"),
	/**分配*/
	DISTRIBUTED((byte)2, "分配"),
	/**分配中*/
	DISTRIBUTING((byte)3, "分配"),
	/**物流公司直发*/
	SELFLOGST((byte)4, "自有"),
	/**分配规则_车主*/
	DISTRIBUTED_DRIVER((byte)5, "分配"),
	/**指派_物流公司*/
	ASSIGN_LOGISTIC((byte)6, "指派"),
	/**指派_车主*/
	ASSIGN_DRIVER((byte)7, "指派");
	

	private Byte code;
	
	private String name;

	private NstRuleEnum2(Byte code, String name) {
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
		NstRuleEnum2[] values = NstRuleEnum2.values();
		for(NstRuleEnum2 val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

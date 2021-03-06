package cn.gdeng.nst.enums;

/**对应数据库source_shipper表nstRule字段
 * @author wjguo
 * datetime 2016年8月26日 下午4:07:21  
 *
 */
public enum NstRuleEnum {
	
	/** 货主直发**/
	SELF((byte)1, "货主直发"),
	/** 代发**/
	DISTRIBUTED((byte)2, "代发"),
	/** 分配中**/
	DISTRIBUTING((byte)3, "分配中"),
	/** 物流公司直发**/
	SELFLOGST((byte)4, "物流公司直发"),
	/** 分配规则_车主*/
	DISTRIBUTED_DRIVER((byte)5, "分配规则_车主"),
	/** 指派_物流公司*/
	ASSIGN_LOGISTIC((byte)6, "指派_物流公司"),
	/** 指派_车主*/
	ASSIGN_DRIVER((byte)7, "指派_车主");

	private Byte code;
	
	private String name;

	private NstRuleEnum(Byte code, String name) {
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
		NstRuleEnum[] values = NstRuleEnum.values();
		for(NstRuleEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

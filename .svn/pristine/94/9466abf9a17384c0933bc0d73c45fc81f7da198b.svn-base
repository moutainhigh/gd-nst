package cn.gdeng.nst.enums;


/**
 *  物流状态
 * @author DJB
 *
 */
public enum TransStatusEnum {
	
	
	/**待验货  */
	NOT_CHECK((byte)1, "待验货"),
	/**已发货 */
	SNEDED((byte)2, "已发货"),
	/**已收货  */
	RECEPTED((byte)3, "已送达"),
	/**验货不通过  */
	NOT_PASS((byte)4,"验货不通过"),
	/**已拒收  */
	REJECTED((byte)5, "已拒收");
	

	private Byte code;
	
	private String name;

	private TransStatusEnum(Byte code, String name) {
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
		TransStatusEnum[] values = TransStatusEnum.values();
		for(TransStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return "";
	}
}


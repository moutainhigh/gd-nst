package cn.gdeng.nst.enums;

/**运单-物流信息 物流状态被关闭的原因枚举
 * 
 * @author wjguo
 *
 * datetime:2016年12月5日 下午4:43:03
 */
public enum OrderInfoTransCloseReasonEnum {

	NSY_CANCEL((byte)1, "农商友取消采购单"),
	EXAMINE_GOODS_NOT_PASS((byte)2, "验货不通过"),
	EXAMINE_GOODS_OVERDUE((byte)3, "验货超时(3天)"),
	PRE_PAYMENT_OVERDUE((byte)4, "预付款支付超时"),
	REPEAL_PRE_PAYMTEN((byte)5, "退预付款(拒绝收货)");
	
	private Byte code;
	
	private String name;

	private OrderInfoTransCloseReasonEnum(Byte code, String name) {
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
	
	/**根据code码获取name属性
	 * @param code
	 * @return 如果找不到对应的枚举，返回null
	 */
	public static String getNameByCode(Byte code){
		OrderInfoTransCloseReasonEnum en = getByCode(code);
		return en != null ? en.getName() : null;
	}
	
	/**根据code码获取枚举
	 * @param code
	 * @return 如果找不到对应的枚举，返回null
	 */
	public static OrderInfoTransCloseReasonEnum getByCode(Byte code){
		OrderInfoTransCloseReasonEnum[] values = OrderInfoTransCloseReasonEnum.values();
		for(OrderInfoTransCloseReasonEnum val : values){
			if(val.getCode().equals(code)){
				return val;
			}
		}
		return null;
	}
}

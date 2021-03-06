package cn.gdeng.nst.enums;
/**
* @author DJB
* @version 创建时间：2016年12月16日 上午11:43:37
* 类说明
*/

public enum OperationStatusEnum {
	/**  物流公司待确认  */
	OPERATION_1((byte)1,"待确认","您已接单，等待物流公司确认"),
	
	/** 货主待确认   */
	OPERATION_2((byte)2,"待确认","您已接单，等待货主确认"),
	
	
	/** 成功接单，未付款   */
	OPERATION_3((byte)3,"成功接单，未付款","您已成功接单"),
	
	/** 成功接单，已付款   */
	OPERATION_4((byte)4,"成功接单，已付款","您已成功接单"),
	
	/** 物流公司拒绝你的接单   */
	OPERATION_5((byte)5,"物流公司拒绝你的接单","物流公司不同意您的接单，接单失败"),
	
	/**货主拒绝你的接单    */
	OPERATION_6((byte)6,"货主拒绝你的接单","货主不同意您的接单，接单失败"),
	
	/** 接单超时未处理   */
	OPERATION_7((byte)7,"接单超时未处理","接单超时未处理，接单失败"),
	
	/** 取消接单   */
	OPERATION_8((byte)8,"取消接单","您已取消接单"),
	
	/** 指派未验货   */
	OPERATION_9((byte)9,"指派未验货","货主已支付预付款,等待您验货"),
	
	/** 指派验货失败   */
	OPERATION_10((byte)10,"指派验货失败","验货不通过,订单关闭"),
	
	/** 指派验货成功   */
	OPERATION_11((byte)11,"指派验货成功","验货通过,等待车主送货"),
	
	/** 指派验货超时   */
	OPERATION_12((byte)12,"指派验货超时","您超时未验货,订单关闭"),
	
	/** 指派已送达   */
	OPERATION_13((byte)13,"指派已送达","货物已送达,等待货主确认收货"),
	
	/** 指派已收货   */
	OPERATION_14((byte)14,"指派已收货","货主已确认收货,交易完成"),
	
	/**货源过期    */
	OPERATION_15((byte)15,"货源过期",null),
	
	/** 指派未支付预付款   */
	OPERATION_16((byte)16,"指派未支付预付款","等待货主支付预付款"),
	
	/** 运单关闭原因 ,预付款支付超时   */
	OPERATION_17((byte)17,"运单关闭原因 ,预付款支付超时","货主超时未支付预付款,订单关闭"),
	
	/**  运单关闭原因 ,退预付款  */
	OPERATION_18((byte)18,"运单关闭原因 ,退预付款","货主申请退款,订单关闭"),
	
	/**  已完成   */
	OPERATION_19((byte)19,"订单关闭","农商友已取消订单"),
	/**  已完成   */
	OPERATION_20((byte)20,"订单已完成","您已成功接单"),
	
	
	/** 未知   */
	OPERATION_0((byte)0,"未知",null);
	
	
	
	/***  操作状态 **/
	private  Byte operationStatus;
	/**** 操作状态中文解释 ****/
	private String operationStr;
	/**** 操作状态提示语 ****/
	private String markedWords;
	
	
	
	
	private OperationStatusEnum(Byte operationStatus, String operationStr, String markedWords) {
		this.operationStatus = operationStatus;
		this.operationStr = operationStr;
		this.markedWords = markedWords;
	}
	
	public Byte getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(Byte operationStatus) {
		this.operationStatus = operationStatus;
	}
	public String getOperationStr() {
		return operationStr;
	}
	public void setOperationStr(String operationStr) {
		this.operationStr = operationStr;
	}
	public String getMarkedWords() {
		return markedWords;
	}
	public void setMarkedWords(String markedWords) {
		this.markedWords = markedWords;
	}
	
	
	public static String getOperationStrByStatus(Byte operationStatus){
		OperationStatusEnum[] values = OperationStatusEnum.values();
		for(OperationStatusEnum val : values){
			if(val.getOperationStatus().equals(operationStatus)){
				return val.getOperationStr();
			}
		}
		return null;
	}
	
	public static String getMarkedWordsByStatus(Byte operationStatus){
		OperationStatusEnum[] values = OperationStatusEnum.values();
		for(OperationStatusEnum val : values){
			if(val.getOperationStatus().equals(operationStatus)){
				return val.getMarkedWords();
			}
		}
		return null;
	}
	

}

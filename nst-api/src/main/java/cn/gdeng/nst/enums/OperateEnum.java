package cn.gdeng.nst.enums;
/**
* @author huangjianhua
* @version 创建时间：2016年8月10日 下午2:26:07
* 操作Enum 
*/

public enum OperateEnum {
	/**接受		*/
	RECEIVE("receive", "接受"),
	/**拒绝	*/
	REFUSE("refuse", "拒绝"),
	/**查询	  */
	QUERY("query","查询"),
	/**超时		*/
	TIMEOUT("Y","超时"),
	
	GOODSPUBLISHED("goodsPublished", "货源发布成功"),
	
	APPLYFREIGHTGOODS("applyfreightgoods", "车主已接单"),
	
	CANCELFREIGHTGOODS("cancelfreightgoods", "车主已取消接单"),
	
	GOODSOVERDUE("GoodsOverdue", "货源已过期"),
	
	GOODSASSIGNED("GoodsAssigned", "已分配至物流公司"),
	
	GOODSREJECTED("GoodsRejected", "物流公司拒绝分配的货源"),
	
	/** 物流公司已接受车主接单 */
	LOGISTICSRECEIVE("logisticsReceive", "物流公司已接受车主接单"),
	/** 物流公司已拒绝车主接单 */
	LOGISTICSREFUSE("logisticsRefuse", "物流公司已拒绝车主接单"),
	/** 物流公司接受车主接单已超时 */
	//LOGISTICSRECEIVETIMEOUT("logisticsReceiveTimeOut", "物流公司接受车主接单已超时"),
	//物流公司接受车主接单超时，修改为拒绝，防止对用户影响不佳。
	LOGISTICSRECEIVETIMEOUT("logisticsReceiveTimeOut", "物流公司已拒绝车主接单"),
	
	/** 货主已接受车主接单 */
	GOODSOWNERRECEIVE("goodsOwnerReceive", "我已接受车主接单"),
	/** 货主已拒绝车主接单 */
	GOODSOWNERREFUSE("goodsOwnerRefuse", "我已拒绝车主接单"),
	/** 货主接受车主接单已超时 */
	GOODSOWNERRECEIVETIMEOUT("goodsOwnerReceiveTimeOut", "我接受车主接单已超时"),
	
	/** 货主确认收货 */
	GOODSOWNERCONFIRM("goodsOwnerConfirm", "我已确认收货"),
	/** 货主确认收货超时 */
	GOODSOWNERCONFIRMTIMEOUT("goodsOwnerConfirmTimeOut", "我已超时收货"),
	/** 货主已支付运费 */
	GOODSOWNERPAID("goodsOwnerPaid", "我已支付运费");
	
	private OperateEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;
	
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

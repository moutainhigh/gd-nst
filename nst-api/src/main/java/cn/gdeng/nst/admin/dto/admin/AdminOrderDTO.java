package cn.gdeng.nst.admin.dto.admin;

import java.io.Serializable;

import cn.gdeng.nst.enums.OrderAgentStatusEnum2;
import cn.gdeng.nst.enums.OrderInfoStatusEnum;
import cn.gdeng.nst.enums.OrderTypeEnum;
import cn.gdeng.nst.enums.PayStatusEnum;

/**
 * 货源订单、信息订单共用DTO
 * @author dengjianfeng
 *
 */
public class AdminOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer orderType;
	
	private String orderNo;
	
	private Byte orderStatus;
	
	private String buyerName;
	
	private String buyerMobile;
	
	private String sellerName;
	
	private String sellerMobile;
	
	private Short payStatus;
	
	private Double payMoney;
	
	/**货源：0 非平台配送  1 平台配送*/
	private Integer platform;

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

	public Short getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Short payStatus) {
		this.payStatus = payStatus;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getOrderTypeStr(){
		return OrderTypeEnum.getNameByCode(getOrderType());
	}
	
	public String getOrderStatusStr(){
		if(OrderTypeEnum.ORDER_AGENT.getCode().equals(getOrderType())){
			return OrderAgentStatusEnum2.getNameByCode(getOrderStatus());
		}
		else if(OrderTypeEnum.ORDER_INFO.getCode().equals(getOrderType())){
			return OrderInfoStatusEnum.getNameByCode(getOrderStatus());
		}
		return null;
	}
	
	public String getPayStatusStr(){
		return PayStatusEnum.getNameByCode(getPayStatus());
	}
}

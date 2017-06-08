package cn.gdeng.nst.admin.dto.admin;

import java.io.Serializable;
import java.util.Date;

import cn.gdeng.nst.enums.PayStatusEnum;
import cn.gdeng.nst.enums.PayTypeEnum;

public class AdminOrderPayDetailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048880114474476392L;
	
	/**
    *主键ID
    */
    private Integer id;

    /**
    *订单号
    */
    private String orderNo;

    /**
    *1：已支付 2：支付失败
    */
    private Short payStatus;

    /**
    *1：微信 2：支付宝 3：平安银行
    */
    private String payType;

    /**
    *付款时间
    */
    private Date payTime;

    /**
    *1：信息订单交易详情 2：货源订单交易详情
    */
    private Short orderType;

    /**
    *平台支付流水
    */
    private String platformPayWater;

    /**
    *第三方支付流水
    */
    private String thirdPartyPayWater;

    /**
    *付款账号
    */
    private String payAccountNo;

    /**
    *付款方姓名
    */
    private String payName;

    /**
    *收款账号
    */
    private String receiptNo;

    /**
    *收款方姓名
    */
    private String receiptName;
    
    
    private Double payMoney;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Short getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Short payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public String getPlatformPayWater() {
		return platformPayWater;
	}

	public void setPlatformPayWater(String platformPayWater) {
		this.platformPayWater = platformPayWater;
	}

	public String getThirdPartyPayWater() {
		return thirdPartyPayWater;
	}

	public void setThirdPartyPayWater(String thirdPartyPayWater) {
		this.thirdPartyPayWater = thirdPartyPayWater;
	}

	public String getPayAccountNo() {
		return payAccountNo;
	}

	public void setPayAccountNo(String payAccountNo) {
		this.payAccountNo = payAccountNo;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}
	
	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayStatusStr(){
		return PayStatusEnum.getNameByCode(getPayStatus());
	}
	
	public String getPayTypeStr(){
		return PayTypeEnum.getNameByCode(getPayType());
	}
}

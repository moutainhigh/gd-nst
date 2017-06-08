package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "order_paydetail")
public class OrderPaydetailEntity  implements java.io.Serializable{
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
     * 支付金额
     */
    private Double payMoney;

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

    /**
    *创建人员ID
    */
    private String createUserId;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *修改人员ID
    */
    private String updateUserId;

    /**
    *修改时间
    */
    private Date updateTime;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "orderNo")
    public String getOrderNo(){

        return this.orderNo;
    }
    public void setOrderNo(String orderNo){

        this.orderNo = orderNo;
    }
    @Column(name = "payStatus")
    public Short getPayStatus(){

        return this.payStatus;
    }
    public void setPayStatus(Short payStatus){

        this.payStatus = payStatus;
    }
    @Column(name = "payType")
    public String getPayType(){

        return this.payType;
    }
    public void setPayType(String payType){

        this.payType = payType;
    }
    @Column(name = "payTime")
    public Date getPayTime(){

        return this.payTime;
    }
    @Column(name = "payMoney")
    public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public void setPayTime(Date payTime){

        this.payTime = payTime;
    }
    @Column(name = "orderType")
    public Short getOrderType(){

        return this.orderType;
    }
    public void setOrderType(Short orderType){

        this.orderType = orderType;
    }
    @Column(name = "platformPayWater")
    public String getPlatformPayWater(){

        return this.platformPayWater;
    }
    public void setPlatformPayWater(String platformPayWater){

        this.platformPayWater = platformPayWater;
    }
    @Column(name = "thirdPartyPayWater")
    public String getThirdPartyPayWater(){

        return this.thirdPartyPayWater;
    }
    public void setThirdPartyPayWater(String thirdPartyPayWater){

        this.thirdPartyPayWater = thirdPartyPayWater;
    }
    @Column(name = "payAccountNo")
    public String getPayAccountNo(){

        return this.payAccountNo;
    }
    public void setPayAccountNo(String payAccountNo){

        this.payAccountNo = payAccountNo;
    }
    @Column(name = "payName")
    public String getPayName(){

        return this.payName;
    }
    public void setPayName(String payName){

        this.payName = payName;
    }
    @Column(name = "receiptNo")
    public String getReceiptNo(){

        return this.receiptNo;
    }
    public void setReceiptNo(String receiptNo){

        this.receiptNo = receiptNo;
    }
    @Column(name = "receiptName")
    public String getReceiptName(){

        return this.receiptName;
    }
    public void setReceiptName(String receiptName){

        this.receiptName = receiptName;
    }
    @Column(name = "createUserId")
    public String getCreateUserId(){

        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){

        this.createUserId = createUserId;
    }
    @Column(name = "createTime")
    public Date getCreateTime(){

        return this.createTime;
    }
    public void setCreateTime(Date createTime){

        this.createTime = createTime;
    }
    @Column(name = "updateUserId")
    public String getUpdateUserId(){

        return this.updateUserId;
    }
    public void setUpdateUserId(String updateUserId){

        this.updateUserId = updateUserId;
    }
    @Column(name = "updateTime")
    public Date getUpdateTime(){

        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){

        this.updateTime = updateTime;
    }
}

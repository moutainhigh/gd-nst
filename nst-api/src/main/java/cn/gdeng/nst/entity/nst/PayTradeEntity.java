package cn.gdeng.nst.entity.nst;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pay_trade")
public class PayTradeEntity  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 返回版本
	 */
    private String version;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 主题
     */
    private String title;
    /**
     * 支付方式 ALIPAY_H5：支付宝 WEIXIN_APP：微信 PINAN：平安银行
     */
    private String payType;
    /**
     * 支付中心支付流水号
     */
    private String payCenterNumber;
    /**
     * 第三方支付流水
     */
    private String thirdPayNumber;
    /**
     * 超时时间 小时用h表示，例如1h
     */
    private String timeOut;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 关闭时间
     */
    private Date closeTime;
    /**
     * 支付状态 1待付款 2已付款 3已关闭 4已退款
     */
    private String payStatus;
    /**
     * 接入系统来源   nst:农速通 nps：农批商 nsy：农商友 gys：供应商
     */
    private String appKey;
    /**
     * 通知状态  1已通知 0未通知 2通知中
     */
    private String notifyStatus;
    /**
     * 公用回传参数
     */
    private String reParam;
    /**
     * 回调地址
     */
    private String returnUrl;
    /**
     * 业务系统后台异步通知
     */
    private String notifyUrl;
    /**
     * 订单金额
     */
    private Double totalAmt;
    /**
     * 交易金额
     */
    private Double payAmt;
    /**
     * 实收金额
     */
    private Double receiptAmt;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 关闭人ID
     */
    private String closeUserId;
    /**
     * 第三方付款方账号
     */
    private String thirdPayerAccount;
    /**
     * 付款方用户ID
     */
    private String payerUserId;
    /**
     * 付款方账号
     */
    private String payerAccount;
    /**
     * 付款方姓名
     */
    private String payerName;
    /**
     * 收款方用户ID
     */
    private String payeeUserId;
    /**
     * 收款方账号
     */
    private String payeeAccount;
    /**
     * 收款方姓名
     */
    private String payeeName;
    /**
     * 第三方收款方账号
     */
    private String thirdPayeeAccount;
    /**
     * 费率
     */
    private String rate;
    /**
     * 服务费金额
     */
    private Double feeAmt;
    /**
     * 退款人ID
     */
    private String refundUserId;
    /**
     * 退款时间
     */
    private Date refundTime;
    /**
     * 业务信息扩展
     */
    private String extendJson;
    /**
     * 请求IP
     */
    private String requestIp;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "version")
    public String getVersion(){

        return this.version;
    }
    public void setVersion(String version){

        this.version = version;
    }
    @Column(name = "orderNo")
    public String getOrderNo(){

        return this.orderNo;
    }
    public void setOrderNo(String orderNo){

        this.orderNo = orderNo;
    }
    @Column(name = "title")
    public String getTitle(){

        return this.title;
    }
    public void setTitle(String title){

        this.title = title;
    }
    @Column(name = "payType")
    public String getPayType(){

        return this.payType;
    }
    public void setPayType(String payType){

        this.payType = payType;
    }
    @Column(name = "payCenterNumber")
    public String getPayCenterNumber(){

        return this.payCenterNumber;
    }
    public void setPayCenterNumber(String payCenterNumber){

        this.payCenterNumber = payCenterNumber;
    }
    @Column(name = "thirdPayNumber")
    public String getThirdPayNumber(){

        return this.thirdPayNumber;
    }
    public void setThirdPayNumber(String thirdPayNumber){

        this.thirdPayNumber = thirdPayNumber;
    }
    @Column(name = "timeOut")
    public String getTimeOut(){

        return this.timeOut;
    }
    public void setTimeOut(String timeOut){

        this.timeOut = timeOut;
    }
    @Column(name = "payTime")
    public Date getPayTime(){

        return this.payTime;
    }
    public void setPayTime(Date payTime){

        this.payTime = payTime;
    }
    @Column(name = "closeTime")
    public Date getCloseTime(){

        return this.closeTime;
    }
    public void setCloseTime(Date closeTime){

        this.closeTime = closeTime;
    }
    @Column(name = "payStatus")
    public String getPayStatus(){

        return this.payStatus;
    }
    public void setPayStatus(String payStatus){

        this.payStatus = payStatus;
    }
    @Column(name = "appKey")
    public String getAppKey(){

        return this.appKey;
    }
    public void setAppKey(String appKey){

        this.appKey = appKey;
    }
    @Column(name = "notifyStatus")
    public String getNotifyStatus(){

        return this.notifyStatus;
    }
    public void setNotifyStatus(String notifyStatus){

        this.notifyStatus = notifyStatus;
    }
    @Column(name = "reParam")
    public String getReParam(){

        return this.reParam;
    }
    public void setReParam(String reParam){

        this.reParam = reParam;
    }
    @Column(name = "returnUrl")
    public String getReturnUrl(){

        return this.returnUrl;
    }
    public void setReturnUrl(String returnUrl){

        this.returnUrl = returnUrl;
    }
    @Column(name = "notifyUrl")
    public String getNotifyUrl(){

        return this.notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl){

        this.notifyUrl = notifyUrl;
    }
    @Column(name = "totalAmt")
    public Double getTotalAmt(){

        return this.totalAmt;
    }
    public void setTotalAmt(Double totalAmt){

        this.totalAmt = totalAmt;
    }
    @Column(name = "payAmt")
    public Double getPayAmt(){

        return this.payAmt;
    }
    public void setPayAmt(Double payAmt){

        this.payAmt = payAmt;
    }
    @Column(name = "receiptAmt")
    public Double getReceiptAmt(){

        return this.receiptAmt;
    }
    public void setReceiptAmt(Double receiptAmt){

        this.receiptAmt = receiptAmt;
    }
    @Column(name = "orderTime")
    public Date getOrderTime(){

        return this.orderTime;
    }
    public void setOrderTime(Date orderTime){

        this.orderTime = orderTime;
    }
    @Column(name = "closeUserId")
    public String getCloseUserId(){

        return this.closeUserId;
    }
    public void setCloseUserId(String closeUserId){

        this.closeUserId = closeUserId;
    }
    @Column(name = "thirdPayerAccount")
    public String getThirdPayerAccount(){

        return this.thirdPayerAccount;
    }
    public void setThirdPayerAccount(String thirdPayerAccount){

        this.thirdPayerAccount = thirdPayerAccount;
    }
    @Column(name = "payerUserId")
    public String getPayerUserId(){

        return this.payerUserId;
    }
    public void setPayerUserId(String payerUserId){

        this.payerUserId = payerUserId;
    }
    @Column(name = "payerAccount")
    public String getPayerAccount(){

        return this.payerAccount;
    }
    public void setPayerAccount(String payerAccount){

        this.payerAccount = payerAccount;
    }
    @Column(name = "payerName")
    public String getPayerName(){

        return this.payerName;
    }
    public void setPayerName(String payerName){

        this.payerName = payerName;
    }
    @Column(name = "payeeUserId")
    public String getPayeeUserId(){

        return this.payeeUserId;
    }
    public void setPayeeUserId(String payeeUserId){

        this.payeeUserId = payeeUserId;
    }
    @Column(name = "payeeAccount")
    public String getPayeeAccount(){

        return this.payeeAccount;
    }
    public void setPayeeAccount(String payeeAccount){

        this.payeeAccount = payeeAccount;
    }
    @Column(name = "payeeName")
    public String getPayeeName(){

        return this.payeeName;
    }
    public void setPayeeName(String payeeName){

        this.payeeName = payeeName;
    }
    @Column(name = "thirdPayeeAccount")
    public String getThirdPayeeAccount(){

        return this.thirdPayeeAccount;
    }
    public void setThirdPayeeAccount(String thirdPayeeAccount){

        this.thirdPayeeAccount = thirdPayeeAccount;
    }
    @Column(name = "rate")
    public String getRate(){

        return this.rate;
    }
    public void setRate(String rate){

        this.rate = rate;
    }
    @Column(name = "feeAmt")
    public Double getFeeAmt(){

        return this.feeAmt;
    }
    public void setFeeAmt(Double feeAmt){

        this.feeAmt = feeAmt;
    }
    @Column(name = "refundUserId")
    public String getRefundUserId(){

        return this.refundUserId;
    }
    public void setRefundUserId(String refundUserId){

        this.refundUserId = refundUserId;
    }
    @Column(name = "refundTime")
    public Date getRefundTime(){

        return this.refundTime;
    }
    public void setRefundTime(Date refundTime){

        this.refundTime = refundTime;
    }
    @Column(name = "extendJson")
    public String getExtendJson(){

        return this.extendJson;
    }
    public void setExtendJson(String extendJson){

        this.extendJson = extendJson;
    }
    @Column(name = "requestIp")
    public String getRequestIp(){

        return this.requestIp;
    }
    public void setRequestIp(String requestIp){

        this.requestIp = requestIp;
    }
    @Column(name = "createTime")
    public Date getCreateTime(){

        return this.createTime;
    }
    public void setCreateTime(Date createTime){

        this.createTime = createTime;
    }
    @Column(name = "createUserId")
    public String getCreateUserId(){

        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){

        this.createUserId = createUserId;
    }
    @Column(name = "updateTime")
    public Date getUpdateTime(){

        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){

        this.updateTime = updateTime;
    }
    @Column(name = "updateUserId")
    public String getUpdateUserId(){

        return this.updateUserId;
    }
    public void setUpdateUserId(String updateUserId){

        this.updateUserId = updateUserId;
    }
}


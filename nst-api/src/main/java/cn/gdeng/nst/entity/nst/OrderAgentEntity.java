package cn.gdeng.nst.entity.nst;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 信息订单Entity
 * 
 * @author huangjianhua  2016年7月30日  上午9:32:41
 * @version 1.0
 */
@Entity(name = "order_agent")
public class OrderAgentEntity  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3447614431077161798L;
	
	private Integer id;
    /**  信息费订单号     */    private String orderNo;
    /** 货源id      */    private Integer sourceId;
    /** 关联的预运单ID      */
    private Integer orderBeforeId;
    
    /** 物流公司memberId      */    private Integer logisticMemberId;
    /** 司机memberId      */    private Integer driverMemberId;
    /** 接单时间      */    private Date confirmTime;
    /** 物流公司确认时间      */    private Date logisticTime;
    /** 1 已确认 2 已完成      */    private Byte orderStatus;
    /** 1：已支付 2：支付失败*/
    private Short payStatus;
    
    private Double infoAmt;
        private String createUserId;
    private Date createTime;
    private String updateUserId;
    private Date updateTime;
    
    private String logisticName;
    private String logisticMobile;
    
    private String driverName;
    
    private String driverMobile;
    
    
    
    

    @Id    @Column(name = "id")
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
    
    @Column(name = "orderBeforeId")
    public Integer getOrderBeforeId() {
		return orderBeforeId;
	}
	public void setOrderBeforeId(Integer orderBeforeId) {
		this.orderBeforeId = orderBeforeId;
	}

	public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }
    @Column(name = "sourceId")
    public Integer getSourceId(){
        return this.sourceId;
    }
    public void setSourceId(Integer sourceId){
        this.sourceId = sourceId;
    }
    @Column(name = "logisticMemberId")
    public Integer getLogisticMemberId(){
        return this.logisticMemberId;
    }
    public void setLogisticMemberId(Integer logisticMemberId){
        this.logisticMemberId = logisticMemberId;
    }
    @Column(name = "driverMemberId")
    public Integer getDriverMemberId(){
        return this.driverMemberId;
    }
    public void setDriverMemberId(Integer driverMemberId){
        this.driverMemberId = driverMemberId;
    }
    @Column(name = "confirmTime")
    public Date getConfirmTime(){
        return this.confirmTime;
    }
    public void setConfirmTime(Date confirmTime){
        this.confirmTime = confirmTime;
    }
    @Column(name = "logisticTime")
    public Date getLogisticTime(){
        return this.logisticTime;
    }
    public void setLogisticTime(Date logisticTime){
        this.logisticTime = logisticTime;
    }
    @Column(name = "orderStatus")
    public Byte getOrderStatus(){
        return this.orderStatus;
    }
    public void setOrderStatus(Byte orderStatus){
        this.orderStatus = orderStatus;
    }
    @Column(name = "payStatus")
    public Short getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Short payStatus) {
		this.payStatus = payStatus;
	}
	@Column(name = "InfoAmt")
	public Double getInfoAmt() {
		return infoAmt;
	}
	public void setInfoAmt(Double infoAmt) {
		this.infoAmt = infoAmt;
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
    
    
    @Column(name = "logisticName")
	public String getLogisticName() {
		return logisticName;
	}
	public void setLogisticName(String logisticName) {
		this.logisticName = logisticName;
	}
	
	@Column(name = "logisticMobile")
	public String getLogisticMobile() {
		return logisticMobile;
	}
	public void setLogisticMobile(String logisticMobile) {
		this.logisticMobile = logisticMobile;
	}
	
	@Column(name = "driverName")
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	@Column(name = "driverMobile")
	public String getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
    
    
    
    
    
}

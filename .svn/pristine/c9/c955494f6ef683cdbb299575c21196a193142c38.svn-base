package cn.gdeng.nst.api.dto.order;
import java.io.Serializable;
import java.util.Date;

/**
 * 预运单表DTO
 * 
 * @author huangjianhua  2016年7月30日  上午10:31:23
 * @version 1.0
 */
public class OrderBeforeDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6454479309007007538L;
	private Integer id;
	 /**关联货源id   */
    private Integer sourceId;
	/**接单车辆ID			*/
	private Integer carId;
	/**物流公司ID			*/
	private Integer logisticMemberId;
    /**货主memberId     */
    private Integer shipperMemberId;
    /**司机memberId     */
    private Integer driverMemberId;
    /** 1 已接单 2 已取消(司机) 3 已拒绝(货主) 4 已生成订单    */
    private Integer sourceStatus;
 
    private Byte isDeleted;

    private String createUserId;

    private Date createTime;
    
    private Byte sourceType; //1 干线 2 同城 

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getShipperMemberId() {
		return shipperMemberId;
	}

	public void setShipperMemberId(Integer shipperMemberId) {
		this.shipperMemberId = shipperMemberId;
	}

	public Integer getDriverMemberId() {
		return driverMemberId;
	}

	public void setDriverMemberId(Integer driverMemberId) {
		this.driverMemberId = driverMemberId;
	}

	public Integer getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(Integer sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

	public Byte getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLogisticMemberId() {
		return logisticMemberId;
	}

	public void setLogisticMemberId(Integer logisticMemberId) {
		this.logisticMemberId = logisticMemberId;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	
	
	
}

package cn.gdeng.nst.api.dto.order;

import java.io.Serializable;

/**
* @author DJB
* @version 创建时间：2016年12月7日 下午3:13:42
* 订单基本信息DTO
*/

public class OrderBasicDTO implements Serializable {

	/**
	 * cn.gdeng.nst.api.dto.order
	 */
	private static final long serialVersionUID = -8638310359027389192L;
	
    /** 订单ID */
	private Integer orderInfoId;
	/** 货源ID */
	private Integer sourceId;
	/** 预定单ID */	
	private Integer orderBeforeId;
	/** 订单编号 */	
	private String orderInfoNo;
	/** 当前货主ID */	
	private Integer shipperMemberId;
	/** 物流公司ID */	
	private Integer companyMemberId;
	/** 货源版本 */
	private Integer version;
	/** 司机ID */	
	private Integer driverMemberId;

	public Integer getDriverMemberId() {
		return driverMemberId;
	}

	public void setDriverMemberId(Integer driverMemberId) {
		this.driverMemberId = driverMemberId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getOrderBeforeId() {
		return orderBeforeId;
	}

	public void setOrderBeforeId(Integer orderBeforeId) {
		this.orderBeforeId = orderBeforeId;
	}

	public String getOrderInfoNo() {
		return orderInfoNo;
	}

	public void setOrderInfoNo(String orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}

	public Integer getShipperMemberId() {
		return shipperMemberId;
	}

	public void setShipperMemberId(Integer shipperMemberId) {
		this.shipperMemberId = shipperMemberId;
	}

	public Integer getCompanyMemberId() {
		return companyMemberId;
	}

	public void setCompanyMemberId(Integer companyMemberId) {
		this.companyMemberId = companyMemberId;
	}
	}

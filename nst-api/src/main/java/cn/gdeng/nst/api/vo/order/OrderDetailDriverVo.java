package cn.gdeng.nst.api.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author DJB
* @version 创建时间：2016年8月17日 下午5:43:14
* 货源详情 包含司机车辆信息
* 
* 
* 这个VO请不要再使用，后续会删除
* 
*/

public class OrderDetailDriverVo extends OrderDetailBaseVo {
	
	  /**接单司机车牌		*/
    private String driverCarNumber;
    /**接单司机车类型		*/
    private Byte driverCarType;
    /**接单司机车载重		*/
    private BigDecimal driverCarLoad;
    /**接单司机车长		*/
    private BigDecimal driverCarLength;
    
    private Integer carId;
    private Byte orderBeforeStatus;
    private String orderBeforeStatusStr;
	public String getDriverCarNumber() {
		return driverCarNumber;
	}
	public void setDriverCarNumber(String driverCarNumber) {
		this.driverCarNumber = driverCarNumber;
	}
	public Byte getDriverCarType() {
		return driverCarType;
	}
	public void setDriverCarType(Byte driverCarType) {
		this.driverCarType = driverCarType;
	}
	public BigDecimal getDriverCarLoad() {
		return driverCarLoad;
	}
	public void setDriverCarLoad(BigDecimal driverCarLoad) {
		this.driverCarLoad = driverCarLoad;
	}
	public BigDecimal getDriverCarLength() {
		return driverCarLength;
	}
	public void setDriverCarLength(BigDecimal driverCarLength) {
		this.driverCarLength = driverCarLength;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Byte getOrderBeforeStatus() {
		return orderBeforeStatus;
	}
	public void setOrderBeforeStatus(Byte orderBeforeStatus) {
		this.orderBeforeStatus = orderBeforeStatus;
	}
	public String getOrderBeforeStatusStr() {
		return orderBeforeStatusStr;
	}
	public void setOrderBeforeStatusStr(String orderBeforeStatusStr) {
		this.orderBeforeStatusStr = orderBeforeStatusStr;
	}
    
    
    
    
	

}

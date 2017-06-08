package cn.gdeng.nst.api.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.OrderAgentStatusEnum;
import cn.gdeng.nst.enums.OrderBeforeEnum;
import cn.gdeng.nst.enums.OrderInfoStatusEnum;
import cn.gdeng.nst.enums.PayStatusEnum;

/**
 * 预运单详情VO
 * 
 * @author huangjianhua 2016年8月3日 下午4:42:51
 * @version 1.0
 */
public class OrderBeforeVo extends OrderDetailBaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885467405334623216L;

	private Integer carId;// 车辆id
	private Byte orderBeforeStatus; // 预运单状态
	private String orderBeforeStatusStr;// 预运单状态中文解释

	private Integer orderBeforeId;
	/** 接单司机车牌 */
	private String driverCarNumber;
	/** 接单司机车类型 */
	private Integer driverCarType;
	private String driverCarTypeStr;
	/** 接单司机车载重 */
	private BigDecimal driverCarLoad;
	/** 接单司机车长 */
	private BigDecimal driverCarLength;
	/** 司机真实姓名 */
	private String driverRealName;
	/** 司机认证状态 */
	private Integer driverCerStatus;
	private String driverCerStatusStr;
	/** 司机电话 */
	private String driverMobile;
	/** 司机头像 */
	private String driverIconUrl;
	/** 物流公司名称 */
	private String companyName;
	/** 信息订单号 */
	private String orderAgentNo;
	/** 信息订单状态 */
	private Integer orderAgentStatus;
	private String orderAgentStatusStr;
	/** 信息生成时间 */
	private Date agentConfirmTime;
	/** 信息确认订单时间 */
	private Date agentLogisticTime;
	/** 信息-支付金额 */
	private BigDecimal orderAgentPayMoney;
	/** 信息-付款状态 */
	private Integer orderAgentPayStatus;
	private String orderAgentPayStatusStr;
	/** 信息-支付时间 */
	private Date orderAgentPayTime;
	/** 信息-平台支付流水 */
	private String orderAgentPlatformPayWater;
	/** 信息-付款方 */
	private String orderAgentPayName;

	/** 信息订单id */
	private Integer orderAgentId;

	/** 货运订单号 */
	private String orderInfoNo;
	/** 订单状态 */
	private Integer orderInfoStatus;
	private String orderInfoStatusStr;
	/** 货运生成时间 */
	private Date confirmOrderInfoTime;
	/** 货运确认订单时间 */
	private Date confirmGoodsInfoTime;

	/** 货运-信息费 */
	private BigDecimal orderInfoPayMoney;
	/** 货运-付款状态 */
	private Integer orderInfoPayStatus;
	private String orderInfoPayStatusStr;
	/** 货运-支付时间 */
	private Date orderInfoPayTime;
	/** 货运-平台支付流水 */
	private String orderInfoPlatformPayWater;
	/** 货运-付款方 */
	private String orderInfoPayName;

	/** 货主真实姓名 */
	private String shipperRealName;
	/** 货主状态 */
	private Integer shipperCerStatus;
	/** 货主电话 */
	private String shipperMobile;
	/** 货主头像 */
	private String shipperIconUrl;
	/** 订单日志集合 */
	private List<OrderOperateLogVo> operateLogVo;

	private Integer payeeUserId;// 收款方用户ID
	private String payeeMobile;// 收款方账号
	private String payeeName;// 收款方姓名

	public Integer getOrderBeforeId() {
		return orderBeforeId;
	}

	public void setOrderBeforeId(Integer orderBeforeId) {
		this.orderBeforeId = orderBeforeId;
	}

	public String getDriverCarNumber() {
		return driverCarNumber;
	}

	public void setDriverCarNumber(String driverCarNumber) {
		this.driverCarNumber = driverCarNumber;
	}

	public Integer getDriverCarType() {
		return driverCarType;
	}

	public void setDriverCarType(Integer driverCarType) {
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrderAgentNo() {
		return orderAgentNo;
	}

	public void setOrderAgentNo(String orderAgentNo) {
		this.orderAgentNo = orderAgentNo;
	}

	public Integer getOrderAgentStatus() {
		return orderAgentStatus;
	}

	public void setOrderAgentStatus(Integer orderAgentStatus) {
		this.orderAgentStatus = orderAgentStatus;
	}

	public Date getAgentConfirmTime() {
		return agentConfirmTime;
	}

	public void setAgentConfirmTime(Date agentConfirmTime) {
		this.agentConfirmTime = agentConfirmTime;
	}

	public Date getAgentLogisticTime() {
		return agentLogisticTime;
	}

	public void setAgentLogisticTime(Date agentLogisticTime) {
		this.agentLogisticTime = agentLogisticTime;
	}

	public String getOrderInfoNo() {
		return orderInfoNo;
	}

	public void setOrderInfoNo(String orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}

	public Integer getOrderInfoStatus() {
		return orderInfoStatus;
	}

	public void setOrderInfoStatus(Integer orderInfoStatus) {
		this.orderInfoStatus = orderInfoStatus;
	}

	public Date getConfirmOrderInfoTime() {
		return confirmOrderInfoTime;
	}

	public void setConfirmOrderInfoTime(Date confirmOrderInfoTime) {
		this.confirmOrderInfoTime = confirmOrderInfoTime;
	}

	public Date getConfirmGoodsInfoTime() {
		return confirmGoodsInfoTime;
	}

	public void setConfirmGoodsInfoTime(Date confirmGoodsInfoTime) {
		this.confirmGoodsInfoTime = confirmGoodsInfoTime;
	}

	public BigDecimal getOrderAgentPayMoney() {
		return orderAgentPayMoney;
	}

	public void setOrderAgentPayMoney(BigDecimal orderAgentPayMoney) {
		this.orderAgentPayMoney = orderAgentPayMoney;
	}

	public Integer getOrderAgentPayStatus() {
		return orderAgentPayStatus;
	}

	public void setOrderAgentPayStatus(Integer orderAgentPayStatus) {
		this.orderAgentPayStatus = orderAgentPayStatus;
	}

	public Date getOrderAgentPayTime() {
		return orderAgentPayTime;
	}

	public void setOrderAgentPayTime(Date orderAgentPayTime) {
		this.orderAgentPayTime = orderAgentPayTime;
	}

	public String getOrderAgentPlatformPayWater() {
		return orderAgentPlatformPayWater;
	}

	public void setOrderAgentPlatformPayWater(String orderAgentPlatformPayWater) {
		this.orderAgentPlatformPayWater = orderAgentPlatformPayWater;
	}

	public String getOrderAgentPayName() {
		return orderAgentPayName;
	}

	public void setOrderAgentPayName(String orderAgentPayName) {
		this.orderAgentPayName = orderAgentPayName;
	}

	public BigDecimal getOrderInfoPayMoney() {
		return orderInfoPayMoney;
	}

	public void setOrderInfoPayMoney(BigDecimal orderInfoPayMoney) {
		this.orderInfoPayMoney = orderInfoPayMoney;
	}

	public Integer getOrderInfoPayStatus() {
		return orderInfoPayStatus;
	}

	public void setOrderInfoPayStatus(Integer orderInfoPayStatus) {
		this.orderInfoPayStatus = orderInfoPayStatus;
	}

	public Date getOrderInfoPayTime() {
		return orderInfoPayTime;
	}

	public void setOrderInfoPayTime(Date orderInfoPayTime) {
		this.orderInfoPayTime = orderInfoPayTime;
	}

	public String getOrderInfoPlatformPayWater() {
		return orderInfoPlatformPayWater;
	}

	public void setOrderInfoPlatformPayWater(String orderInfoPlatformPayWater) {
		this.orderInfoPlatformPayWater = orderInfoPlatformPayWater;
	}

	public String getOrderInfoPayName() {
		return orderInfoPayName;
	}

	public void setOrderInfoPayName(String orderInfoPayName) {
		this.orderInfoPayName = orderInfoPayName;
	}

	public String getDriverRealName() {
		return driverRealName;
	}

	public void setDriverRealName(String driverRealName) {
		this.driverRealName = driverRealName;
	}

	public Integer getDriverCerStatus() {
		return driverCerStatus;
	}

	public void setDriverCerStatus(Integer driverCerStatus) {
		this.driverCerStatus = driverCerStatus;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getDriverIconUrl() {
		return driverIconUrl;
	}

	public void setDriverIconUrl(String driverIconUrl) {
		this.driverIconUrl = driverIconUrl;
	}

	public String getShipperRealName() {
		return shipperRealName;
	}

	public void setShipperRealName(String shipperRealName) {
		this.shipperRealName = shipperRealName;
	}

	public Integer getShipperCerStatus() {
		return shipperCerStatus;
	}

	public void setShipperCerStatus(Integer shipperCerStatus) {
		this.shipperCerStatus = shipperCerStatus;
	}

	public String getShipperMobile() {
		return shipperMobile;
	}

	public void setShipperMobile(String shipperMobile) {
		this.shipperMobile = shipperMobile;
	}

	public String getShipperIconUrl() {
		return shipperIconUrl;
	}

	public void setShipperIconUrl(String shipperIconUrl) {
		this.shipperIconUrl = shipperIconUrl;
	}

	public List<OrderOperateLogVo> getOperateLogVo() {
		return operateLogVo;
	}

	public void setOperateLogVo(List<OrderOperateLogVo> operateLogVo) {
		this.operateLogVo = operateLogVo;
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

	/*
	 * public String getOrderBeforeStatusStr() { return
	 * OrderBeforeEnum.getNameByCode(getOrderBeforeStatus()); }
	 * 
	 * public String getDriverCerStatusStr() { return
	 * MemberCerStatusEnum.getNameByCode((byte)getDriverCerStatus().intValue());
	 * }
	 * 
	 * public String getOrderAgentStatusStr() { return
	 * OrderAgentStatusEnum.getNameByCode
	 * ((byte)getOrderAgentStatus().intValue()); }
	 * 
	 * public String getOrderAgentPayStatusStr() { return
	 * PayStatusEnum.getNameByCode((short)getOrderAgentPayStatus().intValue());
	 * }
	 * 
	 * public String getOrderInfoStatusStr() { return
	 * OrderAgentStatusEnum.getNameByCode
	 * ((byte)getOrderAgentStatus().intValue()); }
	 * 
	 * public String getOrderInfoPayStatusStr() { return
	 * PayStatusEnum.getNameByCode((short)getOrderInfoPayStatus().intValue()); }
	 * 
	 * public String getDriverCarTypeStr() { return
	 * CarTypeEnum.getNameByCode((byte)getDriverCarType().intValue()); }
	 */

	public String getOrderBeforeStatusStr() {
		if (StringUtils.isBlank(orderBeforeStatusStr)
				&& getOrderBeforeStatus() != null) {
			return OrderBeforeEnum.getNameByCode(getOrderBeforeStatus());
		} else {
			return orderBeforeStatusStr;
		}

	}

	public void setOrderBeforeStatusStr(String orderBeforeStatusStr) {
		this.orderBeforeStatusStr = orderBeforeStatusStr;

	}

	public String getDriverCarTypeStr() {
		if (StringUtils.isBlank(driverCarTypeStr) && getDriverCarType() != null) {
			return CarTypeEnum.getNameByCode((byte) getDriverCarType()
					.intValue());
		} else {
			return driverCarTypeStr;
		}

	}

	public void setDriverCarTypeStr(String driverCarTypeStr) {
		this.driverCarTypeStr = driverCarTypeStr;
	}

	public String getDriverCerStatusStr() {
		if (StringUtils.isBlank(driverCerStatusStr)
				&& getDriverCerStatus() != null) {
			return MemberCerStatusEnum
					.getNameByCode((byte) getDriverCerStatus().intValue());
		} else {
			return driverCerStatusStr;
		}

	}

	public void setDriverCerStatusStr(String driverCerStatusStr) {

		this.driverCerStatusStr = driverCerStatusStr;

	}

	public String getOrderAgentStatusStr() {
		if (StringUtils.isBlank(orderAgentStatusStr)
				&& getOrderAgentStatus() != null) {
			return OrderAgentStatusEnum
					.getNameByCode((byte) getOrderAgentStatus().intValue());
		} else {
			return orderAgentStatusStr;
		}

	}

	public void setOrderAgentStatusStr(String orderAgentStatusStr) {
		this.orderAgentStatusStr = orderAgentStatusStr;
	}

	public String getOrderAgentPayStatusStr() {
		if (StringUtils.isBlank(orderAgentPayStatusStr)
				&& getOrderAgentPayStatus() != null) {
			return PayStatusEnum.getNameByCode((short) getOrderAgentPayStatus()
					.intValue());
		} else {
			return orderAgentPayStatusStr;
		}

	}

	public void setOrderAgentPayStatusStr(String orderAgentPayStatusStr) {
		this.orderAgentPayStatusStr = orderAgentPayStatusStr;
	}

	public String getOrderInfoStatusStr() {
		if (StringUtils.isBlank(orderInfoStatusStr)
				&& getOrderInfoStatus() != null) {
			return OrderInfoStatusEnum
					.getNameByCode((byte) getOrderInfoStatus().intValue());
		} else {
			return orderInfoStatusStr;
		}

	}

	public void setOrderInfoStatusStr(String orderInfoStatusStr) {
		this.orderInfoStatusStr = orderInfoStatusStr;
	}

	public String getOrderInfoPayStatusStr() {
		if (StringUtils.isBlank(orderInfoPayStatusStr)
				&& getOrderInfoPayStatus() != null) {
			return PayStatusEnum.getNameByCode((short) getOrderInfoPayStatus()
					.intValue());
		} else {
			return orderInfoPayStatusStr;
		}

	}

	public void setOrderInfoPayStatusStr(String orderInfoPayStatusStr) {
		this.orderInfoPayStatusStr = orderInfoPayStatusStr;
	}

	public Integer getPayeeUserId() {
		return payeeUserId;
	}

	public void setPayeeUserId(Integer payeeUserId) {
		this.payeeUserId = payeeUserId;
	}

	public String getPayeeMobile() {
		return payeeMobile;
	}

	public void setPayeeMobile(String payeeMobile) {
		this.payeeMobile = payeeMobile;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Integer getOrderAgentId() {
		return orderAgentId;
	}

	public void setOrderAgentId(Integer orderAgentId) {
		this.orderAgentId = orderAgentId;
	}

}

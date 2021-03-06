package cn.gdeng.nst.api.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.OrderBeforeEnum;
import cn.gdeng.nst.enums.OrderInfoOrderStatusEnum;
import cn.gdeng.nst.enums.OrderInfoTransCloseReasonEnum;
import cn.gdeng.nst.enums.PayStatusEnum;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.enums.SourceStatusEnum;
import cn.gdeng.nst.enums.TransStatusEnum;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * @author DJB
 * @version 创建时间：2016年12月6日 下午12:15:47 订单管理--订单列表 专用 请勿套用
 */

public class OrderBeforeBasicDTO implements Serializable {

	/**
	 * cn.gdeng.nst.api.dto.source
	 */
	private static final long serialVersionUID = 8935333115995029052L;
	/** 货源ID */
	private Integer sourceId;
	/** 线路类型 1 干线 2 同城 */
	private Byte sourceType;
	/** 出发地详细地址 */
	private String s_detail;
	/** 目的地详细地址 */
	private String e_detail;

	/** 装货时间 */
	private String sendDate;
	/** 货物名称*/
	private String goodsName;
	/** 重量 */
	private Double totalWeight;
	/** 体积 */
	private Integer totalSize;
	/** 货源所需车长*/
	private Double carLength;
	/** 里程 */
	private Double mileage;
	/** 货物类型 */
	private Byte goodsType;
	/**  货源所需车型 */
	private Byte carType;
	/*** 发货方式 */
	private Byte sendGoodsType;
	/** 当前货主下单时姓名 */
	private String shipperName;
	/** 当前货主下单时手机 */
	private String shipperMobile;
	/** 意向运费 */
	private BigDecimal freight;
	/** 车辆id */
	private Integer carId;
	/** 物流状态（冗余）: 1待验货 2已发货 3车主已送达 4验货不通过 5 拒绝收货   */
	private Byte transStatus;
	/*** 货源关闭原因 */
	private Byte closeReason;
	/** 货源创建时间 */
	private Date createTime;
	/** 货源显示时间  时  天   */
	private String timeStr;
	/** 1 货主直发 2 代发 3分配中 4物流公司直发 **/
	private Byte nstRule;
	/** 0 非平台配送 1 平台配送 **/
	private Byte platform;

	/** 货源状态 **/
	private Byte sourceStatus;

	/** 预运单ID **/
	private Integer orderBeforeId;
	/** 预运单状态 **/
	private Byte orderBeforeStatus;
	/** 支付状态 **/
	private Byte orderAgentPayStatus;
	/** 订单ID **/
	private Integer orderInfoId;
	/** 订单状态 **/
	private Byte orderInfoStatus;
	/** 订单号 **/
	private String orderInfoNo;
	/** 订单确认时间 **/
	private Date orderTime; //old 订单确认时间 为了统一， 此字段以后别用了 使用 confirmOrderInfoTime替换

	/** 预付款支付状态(6+1) 0 未支付 1 已支付 **/
	private Byte prePayStatus;

	/** 信息订单号 **/
	private String orderAgentNo;
	/** 信息订单ID **/
	private Integer orderAgentId;

	/** 收款方用户ID **/
	private Integer payeeUserId;
	/** 收款方账号 **/
	private String payeeMobile;
	/** 收款方姓名 **/
	private String payeeName;
	/** 订单确认时间 **/
	private Date confirmOrderInfoTime;
	/** 信息部确认时间 **/
	private Date agentLogisticTime;

	// 当前货主名片--显示名片
	/** 当前货主头像 */
	private String iconUrl;
	/** 当前货主 姓名*/
	private String userName;
	/** 当前货主ID */
	private Integer memberId;
	/** 当前货主手机号 */
	private String mobile;
	/** 当前货主个人认证状态 */
	private Byte cerPersonalStatus;

	// 原始货主信息
	/*** 货主id */
	private Integer oriShipperMemberId;
	/*** 货主名称 */
	private String oriShipperName;
	/*** 货主电话 */
	private String oriShipperMobile;

	/** 货主状态 */
	private Byte oriShipperCerStatus;
	/** 货主头像 */
	private String oriShipperIconUrl;

	// 商家信息
	/*** 商家会员id */
	private Integer merchantMemberId;
	/*** 商家名称 */
	private String merchantName;
	/*** 商家电话 */
	private String merchantMobile;
	/** 商铺名称 */
	private String merchantTitle;
	/** 商铺地址 */
	private String merchantAddress;
	/** 商铺 商标 */
	private String merchantLogoUrl;

	/*** 操作状态 **/
	private Byte operationStatus;
	/**** 操作状态中文解释 ****/
	private String operationStr;
	/**** 操作状态提示语 ****/
	private String markedWords;

	/** 兼容老版本 2016-12-19 */
	private Byte payStatus;
	private String orderNo;

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayStatusStr() {
		if ( getPayStatus() != null) {
			return PayStatusEnum.getNameByCode((short) getPayStatus());
		} else {
			return null;
		}

	}

	public Byte getOperationStatus() {
		return operationStatus;
	}

	public String getOperationStr() {
		return operationStr;
	}

	public String getMarkedWords() {
		return markedWords;
	}

	public Byte getPrePayStatus() {
		return prePayStatus;
	}

	public void setPrePayStatus(Byte prePayStatus) {
		this.prePayStatus = prePayStatus;
	}

	public String getUserName() {
		return (String) ParamProcessUtil.resultFieldByNstRole(this.userName, getOriShipperName(), getShipperName(), getNstRule());
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMemberId() {
		return (Integer) ParamProcessUtil.resultFieldByNstRole(this.memberId, getOriShipperMemberId(), null, getNstRule());

	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return (String) ParamProcessUtil.resultFieldByNstRole(this.mobile, getOriShipperMobile(), getShipperMobile(), getNstRule());
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getCerPersonalStatus() {
		return (Byte) ParamProcessUtil.resultFieldByNstRole(this.cerPersonalStatus, getOriShipperCerStatus(), null, getNstRule());
	}

	public void setCerPersonalStatus(Byte cerPersonalStatus) {
		this.cerPersonalStatus = cerPersonalStatus;
	}

	public String getCerPersonal() {
		return MemberCerStatusEnum.getNameByCode(getCerPersonalStatus());
	}

	public String getIconUrl() {
		return (String) ParamProcessUtil.resultFieldByNstRole(this.iconUrl, getOriShipperIconUrl(), null, getNstRule());
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipperMobile() {
		return shipperMobile;
	}

	public void setShipperMobile(String shipperMobile) {
		this.shipperMobile = shipperMobile;
	}

	public Integer getMerchantMemberId() {
		return merchantMemberId;
	}

	public void setMerchantMemberId(Integer merchantMemberId) {
		this.merchantMemberId = merchantMemberId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public String getMerchantTitle() {
		return merchantTitle;
	}

	public void setMerchantTitle(String merchantTitle) {
		this.merchantTitle = merchantTitle;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getMerchantLogoUrl() {
		return merchantLogoUrl;
	}

	public void setMerchantLogoUrl(String merchantLogoUrl) {
		this.merchantLogoUrl = merchantLogoUrl;
	}

	public Byte getNstRule() {
		return nstRule;
	}

	public void setNstRule(Byte nstRule) {
		this.nstRule = nstRule;
	}

	public Byte getPlatform() {
		return platform;
	}

	public void setPlatform(Byte platform) {
		this.platform = platform;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}

	public String getS_detail() {
		return s_detail;
	}

	public void setS_detail(String s_detail) {
		if ( StringUtils.isNotBlank(s_detail)) {
			this.s_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(s_detail, "/");
		} else {
			this.s_detail = s_detail;
		}
	}

	public String getE_detail() {
		return e_detail;
	}

	public void setE_detail(String e_detail) {
		if ( StringUtils.isNotBlank(e_detail)) {
			this.e_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(e_detail, "/");
		} else {
			this.e_detail = e_detail;
		}
	}

	public String getSendDate() {
		return ParamProcessUtil.stringDatePattern(sendDate, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm");
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Double getCarLength() {
		return carLength;
	}

	public void setCarLength(Double carLength) {
		this.carLength = carLength;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Byte getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Byte goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsTypeStr() {
		return GoodsTypeEnum.getNameByCode(getGoodsType());
	}

	public Byte getCarType() {
		return carType;
	}

	public void setCarType(Byte carType) {
		this.carType = carType;
	}

	public String getCarTypeStr() {
		return CarTypeEnum.getNameByCode(getCarType());
	}

	public Byte getSendGoodsType() {
		return sendGoodsType;
	}

	public void setSendGoodsType(Byte sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
	}

	public String getSendGoodsTypeStr() {
		return SendGoodsTypeEnum.getNameByCode(getSendGoodsType());
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public Byte getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(Byte sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

	public String getSourceStatusStr() {
		return SourceStatusEnum.getNameByCode(getSourceStatus());
	}

	public Integer getOrderBeforeId() {
		return orderBeforeId;
	}

	public void setOrderBeforeId(Integer orderBeforeId) {
		this.orderBeforeId = orderBeforeId;
	}

	public Byte getOrderBeforeStatus() {
		return orderBeforeStatus;
	}

	public void setOrderBeforeStatus(Byte orderBeforeStatus) {
		this.orderBeforeStatus = orderBeforeStatus;
	}

	public String getOrderBeforeStatusStr() {
		return OrderBeforeEnum.getNameByCode(getOrderBeforeStatus());
	}

	public Byte getOrderAgentPayStatus() {
		return orderAgentPayStatus;
	}

	public void setOrderAgentPayStatus(Byte orderAgentPayStatus) {
		this.orderAgentPayStatus = orderAgentPayStatus;
	}

	public String getOrderAgentPayStatusStr() {
		if ( getOrderAgentPayStatus() == null) {
			return null;
		}
		return PayStatusEnum.getNameByCode((short) getOrderAgentPayStatus());
	}

	public Integer getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public Byte getOrderInfoStatus() {
		return orderInfoStatus;
	}

	public void setOrderInfoStatus(Byte orderInfoStatus) {
		this.orderInfoStatus = orderInfoStatus;
	}

	public String getOrderInfoStatusStr() {
		return OrderInfoOrderStatusEnum.getNameByCode(getOrderInfoStatus());
	}

	public String getOrderInfoNo() {
		return orderInfoNo;
	}

	public void setOrderInfoNo(String orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderAgentNo() {
		return orderAgentNo;
	}

	public void setOrderAgentNo(String orderAgentNo) {
		this.orderAgentNo = orderAgentNo;
	}

	public Integer getOrderAgentId() {
		return orderAgentId;
	}

	public void setOrderAgentId(Integer orderAgentId) {
		this.orderAgentId = orderAgentId;
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

	public Date getConfirmOrderInfoTime() {
		return confirmOrderInfoTime;
	}

	public void setConfirmOrderInfoTime(Date confirmOrderInfoTime) {
		this.confirmOrderInfoTime = confirmOrderInfoTime;
	}

	public Date getAgentLogisticTime() {
		return agentLogisticTime;
	}

	public void setAgentLogisticTime(Date agentLogisticTime) {
		this.agentLogisticTime = agentLogisticTime;
	}

	public Integer getOriShipperMemberId() {
		return oriShipperMemberId;
	}

	public void setOriShipperMemberId(Integer oriShipperMemberId) {
		this.oriShipperMemberId = oriShipperMemberId;
	}

	public String getOriShipperName() {
		return oriShipperName;
	}

	public void setOriShipperName(String oriShipperName) {
		this.oriShipperName = oriShipperName;
	}

	public String getOriShipperMobile() {
		return oriShipperMobile;
	}

	public void setOriShipperMobile(String oriShipperMobile) {
		this.oriShipperMobile = oriShipperMobile;
	}

	public Byte getOriShipperCerStatus() {
		return oriShipperCerStatus;
	}

	public void setOriShipperCerStatus(Byte oriShipperCerStatus) {
		this.oriShipperCerStatus = oriShipperCerStatus;
	}

	public String getOriShipperIconUrl() {
		return oriShipperIconUrl;
	}

	public void setOriShipperIconUrl(String oriShipperIconUrl) {
		this.oriShipperIconUrl = oriShipperIconUrl;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Byte getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(Byte transStatus) {
		this.transStatus = transStatus;
	}

	public String getTransStatusStr() {
		return TransStatusEnum.getNameByCode(getTransStatus());
	}

	public Byte getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(Byte closeReason) {
		this.closeReason = closeReason;
	}

	public String getCloseReasonStr() {
		return OrderInfoTransCloseReasonEnum.getNameByCode(getCloseReason());
	}

	public Boolean getInitialOperation() {
		Map<String, Object> resutlMap = ParamProcessUtil.getInitialOperation(nstRule, platform, sourceStatus, orderBeforeStatus, orderAgentPayStatus, orderInfoStatus, transStatus, prePayStatus, closeReason);
		this.operationStatus = (Byte) resutlMap.get("operationStatus");
		this.operationStr = (String) resutlMap.get("operationStr");
		this.markedWords = (String) resutlMap.get("markedWords");
		return true;

	}

}
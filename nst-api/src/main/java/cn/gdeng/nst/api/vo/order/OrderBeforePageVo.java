package cn.gdeng.nst.api.vo.order;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.OrderBeforeEnum;
import cn.gdeng.nst.enums.OrderInfoStatusEnum;
import cn.gdeng.nst.enums.PayStatusEnum;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.enums.SourceStatusEnum;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * 
 * 预运单PageVo
 * 
 * @author huangjianhua 2016年8月3日 下午4:27:46
 * @version 1.0
 * 
 *  此VO 不再使用，后续将删除
 */
public class OrderBeforePageVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1634553417960799133L;

	private Integer sourceId;

	private Byte sourceType;// 1 干线 2 同城

	private String s_detail;

	private String e_detail;

	private String userName;// 用户姓名

	private Integer memberId;// 用户ID

	private String mobile;

	private Byte cerPersonalStatus;

	private Byte cerCompanyStatus;

	private String cerPersonal;

	private String cerCompany;

	/**
	 * 头像
	 */
	private String iconUrl;

	/**
	 * 装货时间
	 */
	private String sendDate;
	/**
	 * 货物名称
	 */
	private String goodsName;
	/**
	 * 重量
	 * 
	 */
	private Double totalWeight;
	/**
	 * 体积
	 * 
	 */
	private Integer totalSize;
	/**
	 * 货源所需车长
	 * 
	 */
	private Double carLength;

	/**
	 * 里程
	 * 
	 */
	private Double mileage;

	/**
	 * 货物类型
	 */
	private Byte goodsType;

	private String goodsTypeStr;
	/**
	 * 货源所需车型
	 * 
	 */
	private Byte carType;

	private String carTypeStr;

	private Integer nstRule;

	/**
	 * 发货方式
	 * 
	 */
	private Byte sendGoodsType;
	private String sendGoodsTypeStr;

	private Byte sourceStatus;

	private String sourceStatusStr;

	private Date createTime;

	private Integer orderBeforeId;

	private Byte orderBeforeStatus;

	private String orderBeforeStatusStr;

	private Byte payStatus;
	private String payStatusStr;

	private Integer orderInfoId;

	private Byte orderStatus;
	private String orderStatusStr;

	private String orderNo;

	private Date orderTime; // old 订单确认时间 为了统一， 此字段以后别用了 使用 confirmOrderInfoTime
							// 替换

	private String orderAgentNo;

	private Integer orderAgentId;

	private Integer payeeUserId;// 收款方用户ID
	private String payeeMobile;// 收款方账号
	private String payeeName;// 收款方姓名
	private Date confirmOrderInfoTime;// 订单确认时间 (不用)
	private Date agentLogisticTime;// 信息部确认时间

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
		if (StringUtils.isNotBlank(s_detail)) {
			this.s_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(s_detail,
					"/");
		} else {
			this.s_detail = s_detail;
		}
	}

	public Integer getNstRule() {
		return nstRule;
	}

	public void setNstRule(Integer nstRule) {
		this.nstRule = nstRule;
	}

	public String getE_detail() {
		return e_detail;
	}

	public void setE_detail(String e_detail) {
		if (StringUtils.isNotBlank(e_detail)) {
			this.e_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(e_detail,
					"/");
		} else {
			this.e_detail = e_detail;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getCerPersonalStatus() {
		return cerPersonalStatus;
	}

	public void setCerPersonalStatus(Byte cerPersonalStatus) {
		this.cerPersonalStatus = cerPersonalStatus;
	}

	public Byte getCerCompanyStatus() {
		return cerCompanyStatus;
	}

	public void setCerCompanyStatus(Byte cerCompanyStatus) {
		this.cerCompanyStatus = cerCompanyStatus;
	}

	public String getCerPersonal() {
		return MemberCerStatusEnum.getNameByCode(getCerPersonalStatus());
	}

	public String getCerCompany() {
		return MemberCerStatusEnum.getNameByCode(getCerCompanyStatus());
	}

	public String getSendDate() {
		return ParamProcessUtil.stringDatePattern(sendDate,
				"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm");
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

	public Byte getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(Byte sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

	public String getSourceStatusStr() {
		return SourceStatusEnum.getNameByCode(getSourceStatus());
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayStatusStr() {
		if (getPayStatus() != null) {
			return PayStatusEnum.getNameByCode((short) getPayStatus());
		} else {
			return payStatusStr;
		}

	}

	public Integer getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public Byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusStr() {
		return OrderInfoStatusEnum.getNameByCode(getOrderStatus());
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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

	public String getOrderAgentNo() {
		return orderAgentNo;
	}

	public void setOrderAgentNo(String orderAgentNo) {
		this.orderAgentNo = orderAgentNo;
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

}

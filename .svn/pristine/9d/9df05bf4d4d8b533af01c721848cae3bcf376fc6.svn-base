package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * @author DJB
 * @version 创建时间：2016年8月11日 下午5:38:13 司机找货--- 货源基本信息
 */

public class GoodsSourceBasicDto implements Serializable {

	/**
	 * cn.gdeng.nst.api.dto.source
	 */
	private static final long serialVersionUID = 8935333115995029052L;
	
	/**货源ID  */
	private Integer sourceId;
	
	/** 线路类型 1 干线 2 同城  */
	private Byte sourceType;
	
	/** 出发地省会Id */
	private Integer s_provinceId;
	
	/** 出发地城市Id */
	private Integer s_cityId;
	
	/** 出发地区域Id */
	private Integer s_areaId;
	
	/** 出发地中文地址 */
	private String s_detail;
	
	/** 目的地省会Id */
	private Integer e_provinceId;
	
	/** 目的地城市Id */
	private Integer e_cityId;
	
	/** 目的地区域Id */
	private Integer e_areaId;
	
	/**  目的地 详细地址 */
	private String e_detail;
	
	/** 用户头像地址  */
	private String iconUrl;
	
	/** 用户姓名 */
	private String userName;
	
	/**  用户ID */
	private Integer memberId; 
	
	/**  用户手机  */
	private String mobile;
	
	/** 用户个人认证状态  */
	private Byte cerPersonalStatus;
	
	/** 用户企业认证状态 */
	private Byte cerCompanyStatus;
	
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
	 */
	private Double totalWeight;
	
	/**
	 * 体积
	 */
	private Integer totalSize;
	
	/**
	 * 货源所需车长
	 */
	private Double carLength;

	/**
	 * 里程
	 */
	private Double mileage;

	/**
	 * 货物类型
	 */
	private Byte goodsType;

	/**
	 * 货源所需车型
	 */
	private Byte carType;


	/**
	 * 发货方式
	 */
	private Byte sendGoodsType;
	
	/**  货源创建时间 */
	private Date createTime;
	
	/** 货源发布时间  时 天 */
	private String timeStr;
	
	/**  1 货主直发 2 分配规则_物流公司 3分配中 4物流公司直发 5分配规则_车主 6 指派_物流公司 7 指派_车主  */
	private Integer nstRule;

	public Integer getNstRule() {
		return nstRule;
	}

	public void setNstRule(Integer nstRule) {
		this.nstRule = nstRule;
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

	public Integer getS_provinceId() {
		return s_provinceId;
	}

	public void setS_provinceId(Integer s_provinceId) {
		this.s_provinceId = s_provinceId;
	}

	public Integer getS_cityId() {
		return s_cityId;
	}

	public void setS_cityId(Integer s_cityId) {
		this.s_cityId = s_cityId;
	}

	public Integer getS_areaId() {
		return s_areaId;
	}

	public void setS_areaId(Integer s_areaId) {
		this.s_areaId = s_areaId;
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

	public Integer getE_provinceId() {
		return e_provinceId;
	}

	public void setE_provinceId(Integer e_provinceId) {
		this.e_provinceId = e_provinceId;
	}

	public Integer getE_cityId() {
		return e_cityId;
	}

	public void setE_cityId(Integer e_cityId) {
		this.e_cityId = e_cityId;
	}

	public Integer getE_areaId() {
		return e_areaId;
	}

	public void setE_areaId(Integer e_areaId) {
		this.e_areaId = e_areaId;
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

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}

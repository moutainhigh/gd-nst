package cn.gdeng.nst.api.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;

import cn.gdeng.nst.enums.NstRuleEnum;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.enums.SourceStatusEnum;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * 订单详情Base
 * 
 * @author huangjianhua  2016年8月15日  上午10:49:24
 * @version 1.0
 */
public class OrderDetailBaseVo extends BusinessCardVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7524924170124706236L;
	
	/**
	 * 货源ID
	 */
	private Integer sourceId;
	/** 出发地   */
	private String s_detail;
	/** 发货地详细地址   */
	private String s_detailed_address;
	/**目的地    */
	private String e_detail;
	/** 收货地详细地址   */
	private String e_detailed_address;
	
	private Byte sourceType;
	 /**里程 	*/
    private BigDecimal mileage;
	 /** 装车时间            */
    private String sendDate;
    /** 车长       */
    private BigDecimal carLength;
    /** 车辆类型       */
    private Integer carType;
    private String carTypeStr;
    /** 货物类型     */
    private Integer goodsType;
    private String goodsTypeStr;
    /** 发货方式     */
    private Integer sendGoodsType;
    private String sendGoodsTypeStr;
    /** 货物名称       */
    private String goodsName;
    
    private BigDecimal totalWeight;
    
    private BigDecimal totalSize;
    /**货主留言     */
    private String remark;
    /**	意向运费	*/
    private BigDecimal freight;
    /*** 货源来源    */
    private String goodsSource;
    private Byte sourceStatus;//货源状态
    private String sourceStatusStr; //货源状态中文
    
    private Integer nstRule;
    private String nstRuleStr;
    
    /*** 当前货主名称 */
    private String shipperName;
    /*** 当前货主电话  */
    private String shipperMobile;
    /**
     * 出发地的全地址，包括省市区和详细地
     */
    private String srcFullAddr;
    
    /**
     * 目的地的全地址，包括省市区和详细地
     */
    private String desFullAddr;
    /**发货时间**/
    private Date createTime;
    
    
	public Byte getSourceType() {
		return sourceType;
	}
	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	
	public String getS_detail() {
		return s_detail;
	}
	public void setS_detail(String s_detail) {
		if (StringUtils.isNotBlank(s_detail)) {
			this.s_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(s_detail, "/");
		
		}else {
			this.s_detail = s_detail;
		}
		
	}
	public String getS_detailed_address() {
		return s_detailed_address;
	}
	public void setS_detailed_address(String s_detailed_address) {
		this.s_detailed_address = s_detailed_address;
	}
	public String getE_detail() {
		return e_detail;
	}
	public void setE_detail(String e_detail) {
		if (StringUtils.isNotBlank(e_detail)) {
			this.e_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(e_detail, "/");
		
		}else {
			this.e_detail = e_detail;
		}
		
	}
	public String getE_detailed_address() {
		return e_detailed_address;
	}
	public void setE_detailed_address(String e_detailed_address) {
		this.e_detailed_address = e_detailed_address;
	}
	public BigDecimal getMileage() {
		return mileage;
	}
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	public String getSendDate() {
		return ParamProcessUtil.stringDatePattern(sendDate,"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm");
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getSendGoodsType() {
		return sendGoodsType;
	}
	public void setSendGoodsType(Integer sendGoodsType) {
		this.sendGoodsType = sendGoodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}
	public BigDecimal getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(BigDecimal totalSize) {
		this.totalSize = totalSize;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getNstRule() {
		return nstRule;
	}
	public void setNstRule(Integer nstRule) {
		this.nstRule = nstRule;
	}
	public BigDecimal getCarLength() {
		return carLength;
	}
	public void setCarLength(BigDecimal carLength) {
		this.carLength = carLength;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public String getGoodsSource() {
		return goodsSource;
	}
	public void setGoodsSource(String goodsSource) {
		this.goodsSource = goodsSource;
	}
	public Byte getSourceStatus() {
		return sourceStatus;
	}
	public void setSourceStatus(Byte sourceStatus) {
		this.sourceStatus = sourceStatus;
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
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	/*public String getGoodsTypeStr() {
		return  GoodsTypeEnum.getNameByCode((byte)getGoodsType().intValue());
	}

	public String getSendGoodsTypeStr() {
		return  SendGoodsTypeEnum.getNameByCode((byte)getSendGoodsType().intValue());
	}
	
	public String getNstRuleStr() {
		return  NstRuleEnum.getNameByCode((byte)getNstRule().intValue());
	}

	public String getCarTypeStr() {
		return  CarTypeEnum.getNameByCode((byte)getCarType().intValue());
	}*/
	
	public String getSrcFullAddr() {
		return srcFullAddr;
	}
	public void setSrcFullAddr(String srcFullAddr) {
		this.srcFullAddr = srcFullAddr;
	}
	public String getDesFullAddr() {
		return desFullAddr;
	}
	public void setDesFullAddr(String desFullAddr) {
		this.desFullAddr = desFullAddr;
	}
	public String getCarTypeStr() {
		if (StringUtils.isBlank(carTypeStr)&&getCarType()!=null) {
			return CarTypeEnum.getNameByCode((byte)getCarType().intValue());
		} else {
			return carTypeStr;
		}
		
	}
	public void setCarTypeStr(String carTypeStr) {
			this.carTypeStr = carTypeStr;
	}
	public String getGoodsTypeStr() {
		if (StringUtils.isBlank(goodsTypeStr)&&getGoodsType()!=null) {
			return  GoodsTypeEnum.getNameByCode((byte)getGoodsType().intValue());
		}else {
			return  goodsTypeStr;
		}
		
	}
	public void setGoodsTypeStr(String goodsTypeStr) {
			this.goodsTypeStr = goodsTypeStr;
		
	}
	public String getSendGoodsTypeStr() {
		if (StringUtils.isBlank(sendGoodsTypeStr)&&getSendGoodsType()!=null) {
			return SendGoodsTypeEnum.getNameByCode((byte)getSendGoodsType().intValue());
		}else {
			return  sendGoodsTypeStr;
		}
		
	}
	public void setSendGoodsTypeStr(String sendGoodsTypeStr) {
			this.sendGoodsTypeStr = sendGoodsTypeStr;
		
	}
	public String getNstRuleStr() {
		if (StringUtils.isBlank(nstRuleStr)&&getNstRule()!=null) {
			return NstRuleEnum.getNameByCode((byte)getNstRule().intValue());
		}else {
			return nstRuleStr;
		}
		
	}
	public void setNstRuleStr(String nstRuleStr) {
		this.nstRuleStr = nstRuleStr;
		
	}
	
	public String getSourceStatusStr() {
		if (StringUtils.isBlank(sourceStatusStr)&&getSourceStatus()!=null) {
			return SourceStatusEnum.getNameByCode(getSourceStatus());
		} else {
			return  this.sourceStatusStr;
		}
		
	}
	public void setSourceStatusStr(String sourceStatusStr) {
			this.sourceStatusStr = sourceStatusStr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}

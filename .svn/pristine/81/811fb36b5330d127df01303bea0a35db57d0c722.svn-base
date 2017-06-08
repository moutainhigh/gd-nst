package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.util.web.api.NumUtils;

/** 分配的货源详情DTO
 * @author wjguo
 * datetime 2016年8月11日 下午7:57:05  
 *
 */
public class SourceAssignHisDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1622928819779622121L;
	/**
	 * 数据库id
	 */
	private Integer id;
	 /**
	 * 货源id
	 */
	private Integer sourceId;
	 /**
	 * 分配的物流公司会员id
	 */
	private Integer assignMemberId;

	 /**
	 * 分配状态
	 */
	private Byte status;
	/**
	 * 分配创建时间
	 */
	private Date createTime;
	
	/**货源起始地
	 * 
	 */
	private String sourceSDetail;
	
	/**货源起始地详细地址(补充详情地址)
	 * 
	 */
	private String sourceSDetailAddr;
	
	/**货源目的地
	 * 
	 */
	private String sourceEDetail;
	
	/**货源目的地详细地址(补充详情地址)
	 * 
	 */
	private String sourceEDetailAddr;
	
	/**
	 * 货源装货时间
	 */
	private Date sourceSendDate;
	
	/**货源数据版本号
	 * 
	 */
	private Integer sourceVersion;
	
	/**
	 * 货主电话
	 */
	private String shipperMobile;
	
    /**
     * 出发地的全地址，包括市、区和详细地
     */
    private String srcFullAddr;
    
    /**
     * 目的地的全地址，包括市、区和详细地
     */
    private String desFullAddr;
    
    /**分配状态名称
     * 
     */
    private String assginStatusName;
    
    /**货主留言
     * 
     */
    private String sourceRemark;
    
    /**货源里程
     * 
     */
    private Double sourceMileage;
    
    /**
     * 货物类型
     */
    private Byte sourceGoodsType;
    
    /**货源总重量
     * 
     */
    private Double sourceTotalWeight;

    /**货源总体积
     * 
     */
    private Integer sourceTtalSize;
    
    /**货物名称
     * 
     */
    private String sourceGoodsName;
    
    /**货源所需车辆类型
     * 
     */
    private Byte sourceCarType;
    
    /**货源所需车长
     * 
     */
    private Double sourceCarLength;
    
    /**
     * 货源发货方式
     */
    private Byte sourceSendGoodsType;
    
    /**货源意向运费
     * 
     */
    private Double sourceFreight;
    
    /**货主名称
     * 
     */
    private String shipperUserName;
    
    /**货主头像url
     * 
     */
    private String shipperIconUrl;
    
    /**
     * 货主是否通过认证
     */
    private Boolean shipperAuth;
    
    /**分配信息来源名称。如：平台分配已过期、平台分配已拒绝 等
     * 
     */
    private String assginInfoFromName;
    /**服务器当前时间
     * 
     */
    private Date currentDate;
    /**
     * 货源线路类型。1 干线 2 同城
     */
    private Integer sourceType;
    
    /**
     * 平台配送
     */
    private Integer platform;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getAssignMemberId() {
		return assignMemberId;
	}

	public void setAssignMemberId(Integer assignMemberId) {
		this.assignMemberId = assignMemberId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSourceSDetail() {
		return sourceSDetail;
	}

	public void setSourceSDetail(String sourceSDetail) {
		this.sourceSDetail = sourceSDetail;
	}

	public String getSourceSDetailAddr() {
		return sourceSDetailAddr;
	}

	public void setSourceSDetailAddr(String sourceSDetailAddr) {
		this.sourceSDetailAddr = sourceSDetailAddr;
	}

	public String getSourceEDetail() {
		return sourceEDetail;
	}

	public void setSourceEDetail(String sourceEDetail) {
		this.sourceEDetail = sourceEDetail;
	}

	public String getSourceEDetailAddr() {
		return sourceEDetailAddr;
	}

	public void setSourceEDetailAddr(String sourceEDetailAddr) {
		this.sourceEDetailAddr = sourceEDetailAddr;
	}

	public Date getSourceSendDate() {
		return sourceSendDate;
	}

	public void setSourceSendDate(Date sourceSendDate) {
		this.sourceSendDate = sourceSendDate;
	}

	public Integer getSourceVersion() {
		return sourceVersion;
	}

	public void setSourceVersion(Integer sourceVersion) {
		this.sourceVersion = sourceVersion;
	}

	public String getShipperMobile() {
		return shipperMobile;
	}

	public void setShipperMobile(String shipperMobile) {
		this.shipperMobile = shipperMobile;
	}

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

	public String getAssginStatusName() {
		return assginStatusName;
	}

	public void setAssginStatusName(String assginStatusName) {
		this.assginStatusName = assginStatusName;
	}

	public String getSourceRemark() {
		return sourceRemark;
	}

	public void setSourceRemark(String sourceRemark) {
		this.sourceRemark = sourceRemark;
	}

	public Double getSourceMileage() {
		return sourceMileage;
	}

	public void setSourceMileage(Double sourceMileage) {
		this.sourceMileage = sourceMileage;
	}

	public Byte getSourceGoodsType() {
		return sourceGoodsType;
	}

	public void setSourceGoodsType(Byte sourceGoodsType) {
		this.sourceGoodsType = sourceGoodsType;
	}

	public Double getSourceTotalWeight() {
		return sourceTotalWeight;
	}

	public void setSourceTotalWeight(Double sourceTotalWeight) {
		this.sourceTotalWeight = sourceTotalWeight;
	}

	public Integer getSourceTtalSize() {
		return sourceTtalSize;
	}

	public void setSourceTtalSize(Integer sourceTtalSize) {
		this.sourceTtalSize = sourceTtalSize;
	}

	public String getSourceGoodsName() {
		//产品给出优化方案：货品名称显示10个字符，超过部分用省略号代替
		if(StringUtils.isNotBlank(this.sourceGoodsName)&&(this.sourceGoodsName.length()>10)){
			StringBuffer sb=new StringBuffer();
			sb.append(this.sourceGoodsName.substring(0, 11));
			sb.append("...");
			return sb.toString();
		}
		return sourceGoodsName;
	}

	public void setSourceGoodsName(String sourceGoodsName) {
		this.sourceGoodsName = sourceGoodsName;
	}

	public Byte getSourceCarType() {
		return sourceCarType;
	}

	public void setSourceCarType(Byte sourceCarType) {
		this.sourceCarType = sourceCarType;
	}

	public Double getSourceCarLength() {
		return sourceCarLength;
	}

	public void setSourceCarLength(Double sourceCarLength) {
		this.sourceCarLength = sourceCarLength;
	}

	public Byte getSourceSendGoodsType() {
		return sourceSendGoodsType;
	}

	public void setSourceSendGoodsType(Byte sourceSendGoodsType) {
		this.sourceSendGoodsType = sourceSendGoodsType;
	}

	public Double getSourceFreight() {
		return sourceFreight;
	}

	public void setSourceFreight(Double sourceFreight) {
		this.sourceFreight = sourceFreight;
	}


	public String getShipperUserName() {
		return shipperUserName;
	}

	public void setShipperUserName(String shipperUserName) {
		this.shipperUserName = shipperUserName;
	}

	public String getShipperIconUrl() {
		return shipperIconUrl;
	}

	public void setShipperIconUrl(String shipperIconUrl) {
		this.shipperIconUrl = shipperIconUrl;
	}

	public Boolean getShipperAuth() {
		return shipperAuth;
	}

	public void setShipperAuth(Boolean shipperAuth) {
		this.shipperAuth = shipperAuth;
	}
	
	public String getAssginInfoFromName() {
		return assginInfoFromName;
	}

	public void setAssginInfoFromName(String assginInfoFromName) {
		this.assginInfoFromName = assginInfoFromName;
	}
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	/**获取货源所需车型名称
	 * @return
	 */
	public String getSourceCarTypeName() {
		return CarTypeEnum.getNameByCode(sourceCarType);
	}
	
	/**获取货源所需车长名称
	 * @return
	 */
	public String getSourceCarLengthName() {
		if (sourceCarLength == null) {
			return null;
		}
		return sourceCarLength <= 0 ? "不限" :  NumUtils.format(sourceCarLength)+ "米";
	}
	
	/**获取货源意向运费名称
	 * @return
	 */
	public String getSourceFreightName() {
		if (sourceFreight == null) {
			return null;
		}
		return sourceFreight <= 0 ? "面议" :  NumUtils.formatAndDotIdent(sourceFreight) + "元";
	}
	
	/**获取货源发货方式名称
	 * @return
	 */
	public String getSourceSendGoodsTypeName() {
		return SendGoodsTypeEnum.getNameByCode(sourceSendGoodsType);
	}
	
	
	/**获取货源类型名称
	 * @return
	 */
	public String getSourceGoodsTypeName() {
		return GoodsTypeEnum.getNameByCode(sourceGoodsType);
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
	
}

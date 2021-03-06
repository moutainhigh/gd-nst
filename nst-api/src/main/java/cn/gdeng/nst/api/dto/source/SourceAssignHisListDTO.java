package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;
import java.util.Date;

/** 货源分配历史的列表DTO
 * @author wjguo
 * datetime 2016年8月11日 上午9:38:51  
 *
 */
public class SourceAssignHisListDTO implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -3121703150712711518L;
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
    
    /**服务器当前时间
     * 
     */
    private Date currentDate;
    
    /**
     * 平台配送标示
     */
    private Integer platform;
    
    private Integer nstRule;

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

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getNstRule() {
		return nstRule;
	}

	public void setNstRule(Integer nstRule) {
		this.nstRule = nstRule;
	}
	
}

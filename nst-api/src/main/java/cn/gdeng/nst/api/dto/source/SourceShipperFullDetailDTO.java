package cn.gdeng.nst.api.dto.source;

import java.util.Date;
import java.util.List;

import cn.gdeng.nst.enums.CarTypeEnum;

/**货源完整的详细DTO
 * @author wjguo
 * datetime 2016年8月8日 下午2:06:03  
 *
 */
public class SourceShipperFullDetailDTO extends SourceShipperSimpleDetailDTO  implements java.io.Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8438362150917615541L;
	/**司机手机号码
     * 
     */
    private String driverMobile;
    /**
     * 司机接单时间
     */
    private Date driverAcceptDate;
    /**司机姓名
     * 
     */
    private String driverName;
    /**
     * 司机是否已经过认证
     */
    private Boolean driverAuth;
    /**司机头像url
     * 
     */
    private String driverIconUrl;
    /**司机接单的车牌号码
     * 
     */
    private String driverCarNumber;
    /**
     * 司机接单的车辆类型
     */
    private Byte driverCarType; 
    /**司机接单的车辆载重
     * 
     */
    private Double driverLoad;
    /**
     * 司机接单的车辆长度
     */
    private Double driverCarLength;
    
    /**当前货源是否可处理，例如接受和拒绝司机的接单,删除货源等。
     * 
     */
    private Boolean isCanDispose;
    
    /**
     * 分配的物流公司手机号码
     */
    private String assignMemberMobile;
    /**分配的物流公司姓名
     * 
     */
    private String assignMemberName;
    /**
     * 分配的物流公司是否已经过认证
     */
    private Boolean assignMemberAuth;
    /**分配的物流公司头像url
     * 
     */
    private String assignMemberIconUrl;
    
    /** 货源日志
     * 
     */
    private List<SourceLogSimpleDetailDTO> sourceLogs;
    
	public String getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	public Date getDriverAcceptDate() {
		return driverAcceptDate;
	}
	public void setDriverAcceptDate(Date driverAcceptDate) {
		this.driverAcceptDate = driverAcceptDate;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Boolean getDriverAuth() {
		return driverAuth;
	}
	public void setDriverAuth(Boolean driverAuth) {
		this.driverAuth = driverAuth;
	}
	public String getDriverIconUrl() {
		return driverIconUrl;
	}
	public void setDriverIconUrl(String driverIconUrl) {
		this.driverIconUrl = driverIconUrl;
	}
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
	public Double getDriverLoad() {
		return driverLoad;
	}
	public void setDriverLoad(Double driverLoad) {
		this.driverLoad = driverLoad;
	}
	public Double getDriverCarLength() {
		return driverCarLength;
	}
	public void setDriverCarLength(Double driverCarLength) {
		this.driverCarLength = driverCarLength;
	}
	public Boolean getIsCanDispose() {
		return isCanDispose;
	}
	public void setIsCanDispose(Boolean isCanDispose) {
		this.isCanDispose = isCanDispose;
	}
	public String getAssignMemberMobile() {
		return assignMemberMobile;
	}
	public void setAssignMemberMobile(String assignMemberMobile) {
		this.assignMemberMobile = assignMemberMobile;
	}
	public String getAssignMemberName() {
		return assignMemberName;
	}
	public void setAssignMemberName(String assignMemberName) {
		this.assignMemberName = assignMemberName;
	}
	public Boolean getAssignMemberAuth() {
		return assignMemberAuth;
	}
	public void setAssignMemberAuth(Boolean assignMemberAuth) {
		this.assignMemberAuth = assignMemberAuth;
	}
	public String getAssignMemberIconUrl() {
		return assignMemberIconUrl;
	}
	public void setAssignMemberIconUrl(String assignMemberIconUrl) {
		this.assignMemberIconUrl = assignMemberIconUrl;
	}
	public List<SourceLogSimpleDetailDTO> getSourceLogs() {
		return sourceLogs;
	}
	public void setSourceLogs(List<SourceLogSimpleDetailDTO> sourceLogs) {
		this.sourceLogs = sourceLogs;
	}
	
	
	/**司机接单车辆类型名称
	 * @return
	 */
	public String getDriverCarTypeName() {
		return CarTypeEnum.getNameByCode(driverCarType);
	}

}

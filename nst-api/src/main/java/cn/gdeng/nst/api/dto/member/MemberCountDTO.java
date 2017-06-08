package cn.gdeng.nst.api.dto.member;

import java.util.Date;

public class MemberCountDTO  implements java.io.Serializable{
    
    private static final long serialVersionUID = -7290098147420186948L;
    /**
    *会员id
    */
    private Integer memberId;

    /**
    *司机成功接单数
    */
    private Integer driverOrderCount;

    /**
    *司机总收益
    */
    private Double driverIcome;

    /**
    *物流公司总接单数
    */
    private Integer confirmSourceCount;

    /**
    *货源分配总数
    */
    private Integer AutoSourceCount;

    /**
    *创建人员ID
    */
    private String createUserId;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *修改人员ID
    */
    private String updateUserId;

    /**
    *修改时间
    */
    private Date updateTime;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getDriverOrderCount() {
		return driverOrderCount;
	}

	public void setDriverOrderCount(Integer driverOrderCount) {
		this.driverOrderCount = driverOrderCount;
	}

	public Double getDriverIcome() {
		return driverIcome;
	}

	public void setDriverIcome(Double driverIcome) {
		this.driverIcome = driverIcome;
	}

	public Integer getConfirmSourceCount() {
		return confirmSourceCount;
	}

	public void setConfirmSourceCount(Integer confirmSourceCount) {
		this.confirmSourceCount = confirmSourceCount;
	}

	public Integer getAutoSourceCount() {
		return AutoSourceCount;
	}

	public void setAutoSourceCount(Integer autoSourceCount) {
		AutoSourceCount = autoSourceCount;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
    
}

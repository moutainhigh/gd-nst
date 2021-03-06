package cn.gdeng.nst.admin.dto.admin;

import java.util.Date;

public class MemberCerAuditDTO implements java.io.Serializable {

	private static final long serialVersionUID = 9159762976453145892L;

	private Integer id;

	private Integer memberId;
	private Integer cerId;

	private Integer userType;
	private String auditOpinion;
	private Byte cerStatus;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;

	private Integer carId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getCerId() {
		return cerId;
	}

	public void setCerId(Integer cerId) {
		this.cerId = cerId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public Byte getCerStatus() {
		return cerStatus;
	}

	public void setCerStatus(Byte cerStatus) {
		this.cerStatus = cerStatus;
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

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

}

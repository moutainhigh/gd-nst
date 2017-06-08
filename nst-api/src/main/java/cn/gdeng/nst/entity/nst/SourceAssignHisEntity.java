package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "source_assign_his")
public class SourceAssignHisEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209281788525534857L;

	private Integer id;

	private Integer sourceId;

	private Integer assignMemberId;
	/**
	 * 会员类型：2车主 3物流公司
	 */
	private Integer memberType;
	/**
	 * 规则类型：0 货主指派 1 物流规则
	 */
	private Integer ruleType;
	/**
	 * 关联规则Id
	 */
	private Integer ruleInfoId;
	/**
	 * 分配货源是否查看
	 */
	private Integer isView;

	private Byte status;

	private String createUserId;

	private Date createTime;

	private String updateUserId;

	private Date updateTime;

	@Id
	@Column(name = "id")
	public Integer getId() {

		return this.id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	@Column(name = "sourceId")
	public Integer getSourceId() {

		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {

		this.sourceId = sourceId;
	}

	@Column(name = "assignMemberId")
	public Integer getAssignMemberId() {

		return this.assignMemberId;
	}

	public void setAssignMemberId(Integer assignMemberId) {

		this.assignMemberId = assignMemberId;
	}

	@Column(name = "status")
	public Byte getStatus() {

		return this.status;
	}

	public void setStatus(Byte status) {

		this.status = status;
	}

	@Column(name = "createUserId")
	public String getCreateUserId() {

		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {

		this.createUserId = createUserId;
	}

	@Column(name = "createTime")
	public Date getCreateTime() {

		return this.createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	@Column(name = "updateUserId")
	public String getUpdateUserId() {

		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {

		this.updateUserId = updateUserId;
	}

	@Column(name = "updateTime")
	public Date getUpdateTime() {

		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

	@Column(name = "memberType")
	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	@Column(name = "ruleType")
	public Integer getRuleType() {
		return ruleType;
	}

	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}

	@Column(name = "ruleInfoId")
	public Integer getRuleInfoId() {
		return ruleInfoId;
	}

	public void setRuleInfoId(Integer ruleInfoId) {
		this.ruleInfoId = ruleInfoId;
	}

	@Column(name = "isView")
	public Integer getIsView() {
		return isView;
	}

	public void setIsView(Integer isView) {
		this.isView = isView;
	}
}

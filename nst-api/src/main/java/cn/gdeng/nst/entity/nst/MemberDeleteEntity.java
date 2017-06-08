package cn.gdeng.nst.entity.nst;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "member_delete")
public class MemberDeleteEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9012042981203182249L;

	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 关联会员id
	 */
	private Integer memberId;

	/**
	 * 业务主表id
	 */
	private Integer businessId;

	/**
	 * 当前需删除app类型（1 货主 2 车主 3 物流公司）
	 */
	private Integer appType;

	/**
	 * 业务模块类型（1 分配的货 2 订单 3 运单 ）
	 */
	private Integer deleteType;

	/**
	 * 是否删除(0:未删除;1:已删除)
	 */
	private Integer isDeleted;

	/**
	 * 创建人员ID
	 */
	private String createUserId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改人员ID
	 */
	private String updateUserId;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	@Id
	@Column(name = "id")
	public Integer getId() {

		return this.id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	@Column(name = "memberId")
	public Integer getMemberId() {

		return this.memberId;
	}

	public void setMemberId(Integer memberId) {

		this.memberId = memberId;
	}

	@Column(name = "businessId")
	public Integer getBusinessId() {

		return this.businessId;
	}

	public void setBusinessId(Integer businessId) {

		this.businessId = businessId;
	}

	@Column(name = "appType")
	public Integer getAppType() {

		return this.appType;
	}

	public void setAppType(Integer appType) {

		this.appType = appType;
	}

	@Column(name = "deleteType")
	public Integer getDeleteType() {

		return this.deleteType;
	}

	public void setDeleteType(Integer deleteType) {

		this.deleteType = deleteType;
	}

	@Column(name = "isDeleted")
	public Integer getIsDeleted() {

		return this.isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {

		this.isDeleted = isDeleted;
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
}

package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;
import java.util.Date;

import cn.gdeng.nst.enums.MemberCerStatusEnum;

/**
 * @author DJB
 * @version 创建时间：2016年8月8日 下午2:34:43 物流公司找车模块---司机基本信息
 */

public class CarDriverBasicDto implements Serializable {

	/**
	 * cn.gdeng.nst.api.dto.source
	 */
	private static final long serialVersionUID = -2794224935458879297L;
	/** 司机ID */
	private Integer memberId;
	/** 手机号 */
	private String mobile;
	/** 司机姓名 */
	private String userName;
	/** 司机头像地址 */
	private String iconUrl;
	/**  司机接单总数 */
	private Integer orderQuantity;
	/**  创建时间 */
	private Date createTime;
	/**  个人认证状态 */
	private Byte cerPersonalStatus;
	/**  企业认证状态  */
	private Byte cerCompanyStatus;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getOrderQuantity() {
		if ( this.orderQuantity == null) {
			return 0;
		} else {
			return orderQuantity;
		}

	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}

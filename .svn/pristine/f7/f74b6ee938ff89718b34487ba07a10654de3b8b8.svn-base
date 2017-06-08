package cn.gdeng.nst.api.vo.order;

import java.io.Serializable;

import cn.gdeng.nst.enums.MemberCerStatusEnum;

/**
* @author DJB
* @version 创建时间：2016年8月17日 下午2:51:27
* 物流公司、货主名片 类
*/

public class BusinessCardVo implements Serializable {
	/**
	 * cn.gdeng.nst.api.vo.order
	 */
	private static final long serialVersionUID = 3975275953245235203L;
	/**
     * 会员ID
     */
    private Integer memberId;
    /**
     * 联系人
     */
    private String userName;
    
    /**
     * 头像
     */
    private String iconUrl;
    /**
     * 账号
     */
    private String mobile;
    
    /**
     * 认证状态:0:未认证 1:认证中 2:认证通过 3:认证未通过
     */
    private Byte cerPersonalStatus;
    
    private String cerPersonal;
    
  /*  private Byte  appType;*/

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	public String getCerPersonal() {
		return MemberCerStatusEnum.getNameByCode(getCerPersonalStatus());
	}

/*	public Byte getAppType() {
		return appType;
	}

	public void setAppType(Byte appType) {
		this.appType = appType;
	}
*/
	
    
    
    

}

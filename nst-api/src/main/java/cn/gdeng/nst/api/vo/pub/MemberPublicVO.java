package cn.gdeng.nst.api.vo.pub;

import java.io.Serializable;
/**
 * 公共会员VO
 * @author xiaojun
 *
 */
public class MemberPublicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4946550230452157983L;
	/**
	 * 会员的认证的状态  2认证通过 3认证不通过
	 */
	private String cerStatus;
	/**
	 * 业务类型 1 干线业务 2 同城业务 0表示没有选择
	 */
	private String serviceType;
	/**
	 * 联系人
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realName;
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCerStatus() {
		return cerStatus;
	}
	public void setCerStatus(String cerStatus) {
		this.cerStatus = cerStatus;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
}

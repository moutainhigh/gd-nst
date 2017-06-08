package cn.gdeng.nst.admin.dto.admin;

import cn.gdeng.nst.entity.nst.MemberCarEntity;
import cn.gdeng.nst.enums.CarTypeEnum;


public class MemberCarDTO extends MemberCarEntity implements java.io.Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -5642989194434990897L;
private String userName;
private String mobile;
private String serviceType;

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getCarTypeStr(){

    return CarTypeEnum.getNameByCode(getCarType());
}
public String getServiceType() {
	return serviceType;
}
public void setServiceType(String serviceType) {
	this.serviceType = serviceType;
}



}

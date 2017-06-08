package cn.gdeng.nst.admin.dto.admin;

import cn.gdeng.nst.entity.nst.CallstatisticsEntity;
import cn.gdeng.nst.enums.CallServiceTypeEnum;
import cn.gdeng.nst.enums.CallSourceEnum;
import cn.gdeng.nst.enums.CallRoleEnum;

public class CallstatisticsDTO extends CallstatisticsEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6957800687813273753L;
	
	//主叫业务范围
	private Byte serviceType;
	
	public Byte getServiceType() {
		return serviceType;
	}

	public void setServiceType(Byte serviceType) {
		this.serviceType = serviceType;
	}

	public String getSourceStr(){
		return CallSourceEnum.getNameByCode(getSource());
	}
	
	public String getCallRoleStr(){
		return CallRoleEnum.getNameByCode(getCallRole());
	}
	
	public String getCallServiceType(){
		return CallServiceTypeEnum.getNameByCode(getServiceType());
	}

}

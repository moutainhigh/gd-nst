package cn.gdeng.nst.admin.dto.admin;

import java.text.SimpleDateFormat;

import cn.gdeng.nst.entity.nst.AdBannerEntity;
import cn.gdeng.nst.enums.AdChannelEnum;
import cn.gdeng.nst.enums.AdBannerStateEnum;
import cn.gdeng.nst.enums.AdBannerTypeEnum;

public class AdminAdBannerDTO extends AdBannerEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285513825813716889L;

	private String createUserName;
	
	private String updateUserName;
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getTypeStr() {
		return AdBannerTypeEnum.getNameByCode(getType());
	}

	public String getStateStr() {
		return AdBannerStateEnum.getNameByCode(getState());
	}

	public String getChannelStr() {
		return AdChannelEnum.getNameByCode(getChannel());
	}

	public String getStartTimeStr() {
		if(getStartTime() == null){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartTime());
	}
	
	public String getEndTimeStr() {
		if(getEndTime() == null){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getEndTime());
	}
}

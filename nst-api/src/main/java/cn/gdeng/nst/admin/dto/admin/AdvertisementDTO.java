package cn.gdeng.nst.admin.dto.admin;

import java.text.SimpleDateFormat;

import cn.gdeng.nst.entity.nst.AdvertisementEntity;
import cn.gdeng.nst.enums.AdChannelEnum;
import cn.gdeng.nst.enums.AdStatusEnum;

public class AdvertisementDTO extends AdvertisementEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4857616876628370425L;
	
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getChannelStr(){
		return AdChannelEnum.getNameByCode(getChannel());
	}
	
	public String getStatusStr(){
		return AdStatusEnum.getNameByCode(getStatus());
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

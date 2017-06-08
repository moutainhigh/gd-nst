package cn.gdeng.nst.admin.dto.admin;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.entity.nst.RuleInfoEntity;
import cn.gdeng.nst.enums.RuleInfoEnableEnum;
import cn.gdeng.nst.enums.SourceTypeEnum;

public class RuleInfoDTO extends RuleInfoEntity{

	private static final long serialVersionUID = 6807392704709899589L;
	/**
	 * 创建人员
	 */
    private String createUser;
    
    /**
     * 规则内使用的物流公司
     */
    private String count;
    
    private String provinceIdStr;
    
    private String cityIdStr;
    
    private String areaStr;
    
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public String getProvinceIdStr() {
		return provinceIdStr;
	}
	
	public void setProvinceIdStr(String provinceIdStr) {
		this.provinceIdStr = provinceIdStr;
	}
	
	public String getCityIdStr() {
		return cityIdStr;
	}
	
	public void setCityIdStr(String cityIdStr) {
		this.cityIdStr = cityIdStr;
	}
	
	public String getAreaStr() {
		return areaStr;
	}
	
	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}
	
	public String getOnOffStr(){
		return RuleInfoEnableEnum.getNameByCode(getOnOff());
	}
	
	public String getOnTimeStr(){
		if(getOnTime() == null){
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(getOnTime());
	}

	public String getOffTimeStr(){
		if(getOffTime() == null){
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(getOffTime());
	}
	
	public String getCreateTimeStr(){
		if(getCreateTime() == null){
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(getCreateTime());
	}

	public String getSourceTypeStr() {
		return SourceTypeEnum.getNameByCode(getSourceType());
	}

	public String getSourceAddress() {
		StringBuffer buffer=new StringBuffer();
		if(StringUtils.isNotBlank(getProvinceIdStr())&&(StringUtils.isNotBlank(getCityIdStr()))){
			buffer.append(getProvinceIdStr());
			buffer.append(" ");
			buffer.append(getCityIdStr());
			if(StringUtils.isNotBlank(getAreaStr())){
				buffer.append(" ");
				buffer.append(getAreaStr());
			}
		}
		return buffer.toString();
	}
}

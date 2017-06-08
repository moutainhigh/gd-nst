package cn.gdeng.nst.admin.dto.admin;

import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.enums.MemberCerStatusEnum;
import cn.gdeng.nst.enums.MemberInfoRegetypeEnum;
import cn.gdeng.nst.enums.MemberInfoStatusEnum;

public class MemberInfoDTO extends MemberInfoEntity{

	private static final long serialVersionUID = -696039299791831161L;
	//物流公司规则表中的level
	private String level;
	//省
	private Integer provinceId;
    //市
	private Integer cityId;
    //区
	private Integer areaId;
	// 省/市/区
	private String detail;
	//绑定物流公司的MEMBERID
	private Integer memberIdLogistic;
	//真实姓名	
	private String  realName;
	//绑定物流公司的公司名
	private String memberIdLogisticName;
	//已认证物流公司的名称
	private String companyName;
	
	private Integer memberType;
	
	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public Integer getProvinceId() {
		return provinceId;
	}


	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getStatusStr(){
		if(getStatus() != null){
			return MemberInfoStatusEnum.getNameByCode(getStatus());		
		}
		return "";
	}
	public String getCerPersonalStatusStr() {
		if(getCerPersonalStatus() != null){
			return MemberCerStatusEnum.getNameByCode(getCerPersonalStatus().byteValue());
		}
		return "";
	}
	public String getCerCompanyStatusStr() {
		if(getCerCompanyStatus() != null){
			return MemberCerStatusEnum.getNameByCode(getCerCompanyStatus().byteValue());
		}
		return "";
	}
	
	public String getRegetypeStr(){
		if(getRegetype() != null){
			return MemberInfoRegetypeEnum.getNameByCode(getRegetype().byteValue());
		}
		return "";
    }
	
	
	public Integer getMemberIdLogistic() {
		return memberIdLogistic;
	}


	public void setMemberIdLogistic(Integer memberIdLogistic) {
		this.memberIdLogistic = memberIdLogistic;
	}


	public String getMemberIdLogisticName() {
		return memberIdLogisticName;
	}


	public void setMemberIdLogisticName(String memberIdLogisticName) {
		this.memberIdLogisticName = memberIdLogisticName;
	}


	public String getAddressStr(){
		String address = getDetail();
		if(address != null){
			String[] detailArray = address.split("/");
			StringBuilder sb = new StringBuilder();
			for(String val : detailArray){
				sb.append(val).append(" ");
			}
			return sb.toString();
		}
		return "";
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public Integer getMemberType() {
		return memberType;
	}


	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

} 

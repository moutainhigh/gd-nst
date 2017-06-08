package cn.gdeng.nst.admin.dto.admin;

import java.text.SimpleDateFormat;

import cn.gdeng.nst.entity.nst.MemberLineEntity;
import cn.gdeng.nst.enums.IsDeletedEnum;
import cn.gdeng.nst.enums.SourceTypeEnum;

public class MemberLineDTO extends MemberLineEntity{

	/**
	 * 常跑线路DTO
	 */
	private static final long serialVersionUID = 6623389461872077543L;
	
	/**
	 * 线路发布人
	 */
	private String publisher;
	
	/**
	 * 线路发布人手机
	 */
	private String phone;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSourceTypeStr(){
		return SourceTypeEnum.getNameByCode(getLineType());
	}
	
	public String getSDetailStr(){
		String sDetail = getS_detail();
		if(sDetail != null){
			String[] detailArray = sDetail.split("/");
			StringBuilder sb = new StringBuilder();
			for(String val : detailArray){
				sb.append(val).append(" ");
			}
			return sb.toString();
		}
		return null;
	}
	
	public String getEDetailStr(){
		String eDetail = getE_detail();
		if(eDetail != null){
			String[] detailArray = eDetail.split("/");
			StringBuilder sb = new StringBuilder();
			for(String val : detailArray){
				sb.append(val).append(" ");
			}
			return sb.toString();
		}
		return null;
	}
	
	public String getIsDeletedStr(){
		return IsDeletedEnum.getNameByCode(getIsDeleted());
	}
	
	public String getCreateTimeStr(){
		if(getCreateTime() == null){
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(getCreateTime());
	}
}

package cn.gdeng.nst.admin.dto.admin;

import cn.gdeng.nst.entity.nst.SourceShipperEntity;
import cn.gdeng.nst.enums.CarTypeEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.NstRuleEnum2;
import cn.gdeng.nst.enums.SendGoodsTypeEnum;
import cn.gdeng.nst.enums.SourceClientsEnum;
import cn.gdeng.nst.enums.SourceGoodsTypeEnum;
import cn.gdeng.nst.enums.SourceStatusEnum2;
import cn.gdeng.nst.enums.SourceTypeEnum;
import cn.gdeng.nst.util.admin.web.DateUtil;

public class AdminSourceShipperDTO extends SourceShipperEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7859861630629689083L;

	private String memberName;
	
	private String memberMobile;
	
	private String logisticName;
	
	private String logisticMobile;
	
	private Integer orderBeforeCount;
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public String getLogisticName() {
		return logisticName;
	}

	public void setLogisticName(String logisticName) {
		this.logisticName = logisticName;
	}

	public String getLogisticMobile() {
		return logisticMobile;
	}

	public void setLogisticMobile(String logisticMobile) {
		this.logisticMobile = logisticMobile;
	}

	public Integer getOrderBeforeCount() {
		return orderBeforeCount;
	}

	public void setOrderBeforeCount(Integer orderBeforeCount) {
		this.orderBeforeCount = orderBeforeCount;
	}

	public String getSourceTypeStr(){
		return SourceTypeEnum.getNameByCode(getSourceType());
	}
	
	public String getSendGoodsTypeStr(){
		return SendGoodsTypeEnum.getNameByCode(getSendGoodsType());
	}
	
	public String getCarTypeStr(){
		return CarTypeEnum.getNameByCode(getCarType());
	}
	
	public String getGoodsTypeStr(){
		return GoodsTypeEnum.getNameByCode(getGoodsType());
	}
	
	public String getClientsStr(){
		return SourceClientsEnum.getNameByCode(getClients());
	}
	
	public String getSourceStatusStr(){
		return SourceStatusEnum2.getNameByCode(getSourceStatus());
	}
	
	public String getNstRuleStr(){
		return NstRuleEnum2.getNameByCode(getNstRule());
	}
	
	public String getSourceGoodsTypeStr() {
		return SourceGoodsTypeEnum.getNameByCode(getSourceGoodsType());
	}
	
	public String getSDetailStr(){
		String sDetail = getSDetail();
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
		String eDetail = getEDetail();
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
	
	public String getSendDateStr() {
		if(getSendDate() == null){
			return null;
		}
		return DateUtil.toString(getSendDate(), DateUtil.DATE_FORMAT_DATETIME);
	}
}

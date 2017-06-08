package cn.gdeng.nst.admin.dto.admin;

import java.util.Date;

import cn.gdeng.nst.entity.nst.SourceAssignHisEntity;
import cn.gdeng.nst.enums.AdminSourceAssignHisStatusEnum;
import cn.gdeng.nst.enums.GoodsTypeEnum;
import cn.gdeng.nst.enums.NstRuleEnum2;
import cn.gdeng.nst.enums.SourceClientsEnum;
import cn.gdeng.nst.enums.SourceTypeEnum;

public class AdminSourceAssignHisDTO extends SourceAssignHisEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4171304020194558826L;
	
	/**发布人*/
	private String sourceMemberName;
	
	/**发布人手机*/
	private String sourceMemberMobile;
	
	/**货源发布时间*/
	private Date sourceCreateTime;
	
	/**分配公司/车主*/
	private String assignName;
	
	/**分配公司/车主手机*/
	private String assignMobile;
	
	private Byte goodsType;
	
	private Byte nstRule;
	
	private Double totalWeight;
	
	/**线路类型*/
	private Integer sourceType;
	
	private String sDetail;
	
	private String eDetail;
	
	/**发布来源*/
	private Byte clients;

	public String getSourceMemberName() {
		return sourceMemberName;
	}

	public void setSourceMemberName(String sourceMemberName) {
		this.sourceMemberName = sourceMemberName;
	}

	public String getSourceMemberMobile() {
		return sourceMemberMobile;
	}

	public void setSourceMemberMobile(String sourceMemberMobile) {
		this.sourceMemberMobile = sourceMemberMobile;
	}

	public Date getSourceCreateTime() {
		return sourceCreateTime;
	}

	public void setSourceCreateTime(Date sourceCreateTime) {
		this.sourceCreateTime = sourceCreateTime;
	}

	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	public String getAssignMobile() {
		return assignMobile;
	}

	public void setAssignMobile(String assignMobile) {
		this.assignMobile = assignMobile;
	}
	
	public Byte getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Byte goodsType) {
		this.goodsType = goodsType;
	}

	public Byte getNstRule() {
		return nstRule;
	}

	public void setNstRule(Byte nstRule) {
		this.nstRule = nstRule;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getSDetail() {
		return sDetail;
	}

	public void setSDetail(String sDetail) {
		this.sDetail = sDetail;
	}

	public String getEDetail() {
		return eDetail;
	}

	public void setEDetail(String eDetail) {
		this.eDetail = eDetail;
	}

	public Byte getClients() {
		return clients;
	}

	public void setClients(Byte clients) {
		this.clients = clients;
	}
	
	public String getStatusStr(){
		return AdminSourceAssignHisStatusEnum.getNameByCode(getStatus());
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
	
	public String getGoodsTypeStr(){
		return GoodsTypeEnum.getNameByCode(getGoodsType());
	}
	
	public String getSourceTypeStr(){
		return SourceTypeEnum.getNameByCode(getSourceType());
	}
	
	public String getNstRuleStr(){
		return NstRuleEnum2.getNameByCode(getNstRule());
	}
	
	public String getClientsStr(){
		return SourceClientsEnum.getNameByCode(getClients());
	}
}

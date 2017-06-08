package cn.gdeng.nst.admin.dto.admin;

import java.util.Date;

import cn.gdeng.nst.entity.nst.OrderBeforeEntity;
import cn.gdeng.nst.enums.MemberInfoRegetypeEnum;
import cn.gdeng.nst.enums.OrderAgentStatusEnum;
import cn.gdeng.nst.enums.OrderBeforeSourceStatusEnum;
import cn.gdeng.nst.enums.SourceTypeEnum;

public class AdminOrderBeforeDTO extends OrderBeforeEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9009222247393861523L;

	private Integer nstRule;
	
	/**线路类型：1 干线 2 同城*/
	private Integer sourceType;
	
	private String sDetail;
	
	private String eDetail;
	
	/**货物重量*/
	private Double totalWeight;
	
	/**货源发布时间*/
	private Date releaseTime;
	
	/**发布人注册来源*/
	private Byte regeType;
	
	/**信息订单号*/
	private String orderAgentNo;
	
	/**信息订单状态*/
	private Byte orderAgentStatus;
	
	/**货运订单号*/
	private String orderInfoNo;
	
	/**接单处理时间*/
	private Date handleTime;

	public Integer getNstRule() {
		return nstRule;
	}

	public void setNstRule(Integer nstRule) {
		this.nstRule = nstRule;
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

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Byte getRegeType() {
		return regeType;
	}

	public void setRegeType(Byte regeType) {
		this.regeType = regeType;
	}

	public String getOrderAgentNo() {
		return orderAgentNo;
	}

	public void setOrderAgentNo(String orderAgentNo) {
		this.orderAgentNo = orderAgentNo;
	}

	public Byte getOrderAgentStatus() {
		return orderAgentStatus;
	}

	public void setOrderAgentStatus(Byte orderAgentStatus) {
		this.orderAgentStatus = orderAgentStatus;
	}

	public String getOrderInfoNo() {
		return orderInfoNo;
	}

	public void setOrderInfoNo(String orderInfoNo) {
		this.orderInfoNo = orderInfoNo;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
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
	
	public String getSourceTypeStr(){
		return SourceTypeEnum.getNameByCode(getSourceType());
	}
	
	public String getSourceStatusStr(){
		return OrderBeforeSourceStatusEnum.getNameByCode(getSourceStatus());
	}
	
	public String getRegeTypeStr(){
		return MemberInfoRegetypeEnum.getNameByCode(getRegeType());
	}
	
	public String getOrderAgentStatusStr() {
		return OrderAgentStatusEnum.getNameByCode(getOrderAgentStatus());
	}
}

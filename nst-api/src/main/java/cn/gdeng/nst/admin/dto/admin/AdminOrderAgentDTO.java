package cn.gdeng.nst.admin.dto.admin;

import java.text.SimpleDateFormat;

import cn.gdeng.nst.entity.nst.OrderAgentEntity;
import cn.gdeng.nst.enums.OrderAgentStatusEnum2;
import cn.gdeng.nst.enums.OrderBeforeSourceTypeEnum;
import cn.gdeng.nst.enums.PayStatusEnum;

public class AdminOrderAgentDTO extends OrderAgentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3462521573545772572L;
	
	/** 发布人姓名*/
	private String sourceMemberName;
	
	/** 发布人手机*/
	private String sourceMemberMobile;
	
	private AdminOrderPayDetailDTO orderPayDetail;
	
	private Double payMoney;
	
	/** 物流公司名称*/
	private String logisticCompanyName;
	
	private Integer s_provinceId;
	
	private Integer s_cityId;
	
	private Integer s_areaId;
	
	private String s_detail;
	
	private Integer e_provinceId;
	
	private Integer e_cityId;
	
	private Integer e_areaId;
	
	private String e_detail;
	
	/** 货物重量*/
	private Double totalWeight;
	
	/** 订单类型：1 干线 2 同城*/
	private Byte sourceType;

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
	
	public String getOrderStatusStr(){
		return OrderAgentStatusEnum2.getNameByCode(getOrderStatus());
	}

	public String getConfirmTimeStr() {
		if(getConfirmTime() != null){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.format(getConfirmTime());
		}
		return "";
	}
	
	public String getLogisticTimeStr(){
		if(getLogisticTime() != null){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sf.format(getLogisticTime());
		}
		return "";
	}

	public String getPayStatusStr(){
		return PayStatusEnum.getNameByCode(getPayStatus());
	}
	
	public AdminOrderPayDetailDTO getOrderPayDetail() {
		return orderPayDetail;
	}

	public void setOrderPayDetail(AdminOrderPayDetailDTO orderPayDetail) {
		this.orderPayDetail = orderPayDetail;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getLogisticCompanyName() {
		return logisticCompanyName;
	}

	public void setLogisticCompanyName(String logisticCompanyName) {
		this.logisticCompanyName = logisticCompanyName;
	}
	
	public Integer getS_provinceId() {
		return s_provinceId;
	}

	public void setS_provinceId(Integer s_provinceId) {
		this.s_provinceId = s_provinceId;
	}

	public Integer getS_cityId() {
		return s_cityId;
	}

	public void setS_cityId(Integer s_cityId) {
		this.s_cityId = s_cityId;
	}

	public Integer getS_areaId() {
		return s_areaId;
	}

	public void setS_areaId(Integer s_areaId) {
		this.s_areaId = s_areaId;
	}

	public String getS_detail() {
		return s_detail;
	}

	public void setS_detail(String s_detail) {
		this.s_detail = s_detail;
	}

	public Integer getE_provinceId() {
		return e_provinceId;
	}

	public void setE_provinceId(Integer e_provinceId) {
		this.e_provinceId = e_provinceId;
	}

	public Integer getE_cityId() {
		return e_cityId;
	}

	public void setE_cityId(Integer e_cityId) {
		this.e_cityId = e_cityId;
	}

	public Integer getE_areaId() {
		return e_areaId;
	}

	public void setE_areaId(Integer e_areaId) {
		this.e_areaId = e_areaId;
	}

	public String getE_detail() {
		return e_detail;
	}

	public void setE_detail(String e_detail) {
		this.e_detail = e_detail;
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

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Byte getSourceType() {
		return sourceType;
	}

	public void setSourceType(Byte sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getSourceTypeStr() {
		return OrderBeforeSourceTypeEnum.getNameByCode(getSourceType());
	}
}

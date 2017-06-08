package cn.gdeng.nst.api.dto.redis;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.gdeng.nst.entity.nst.MemberBaseinfoEntity;



public class MemberBaseinfoDTO extends MemberBaseinfoEntity  implements Serializable, Comparator<Object>{

  
	private static final long serialVersionUID = 2263074342823401073L;

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	//是否已经认证，0 未认证，1已认证  （通过关联查询 member_certifi 表得到）
	public String isAuth;
	
	/**
	 * 是否已关注分类
	 */
	private Integer hasFocusCategory;
	
	/**
	 * 用户关注的分类
	 */
	private List<?> focusCategoryList;
	
	/**
	 * 用户所在市场集合
	 */
	private List<?> reMarketList;
	
	/**
	 * 用户查看过的市场id
	 */
	private Long marketId;
	
	/**
	 * 用户查看过的市场名字
	 */
	private String marketName;
	
	/**
	 * 用户所在城市
	 */
	private String cityName;
	
	/**
	 * 显示YYYY-MM-DD的时间sent Time格式
	 * @return
	 */
	private String sendDateString;
	
	//农速通注册,公司名称
	private String companyName;
	
	private String companyMobile;
	
	private String createUserName;
	
//	private String memberId_ed;
	
	private String ActityNo;
	
	
	private String area;
	
	private String certificationType;
	
	private String nstStatus;
	
	private Long busiMemberId; // 商家ID（供应商或批发商）

	private String idCard;

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	/**
	 * 评价类型 总数
	 */
	private int evaluateTypeCount;
	
	/**
	 * 各个评论数量
	 */
	private Map<String, Long> commentCount;
	
	/**
	 * 农速通星级评分
	 */
	private Integer nstEvaluate;
	
	/**
	 * orderCount  订单总数,不包含4,5状态
	 * @return
	 */
	private String orderCount;
	//是否有行驶证
	private String ifvehiclePhotoUrl;
	//是否有驾驶证
	private String ifdriverPhotoUrl;
	//是否有身份证
	private String ifcardPhotoUrl;
	//是否有营业执照
	private String ifbzlPhotoUrl;
	//货物线路总数
	private String memberCarlineCount;
	private String nst_cardPhotoUrl;
	private String nst_bzlPhotoUrl;
	private String isCertify;
	private String nstCreateTimeFlag;
	private Date nstCreateTime;
	private String nst_CompanyAddress;
	
	// 推荐人手机号
	private String refereeMobile;

	//推荐人姓名 
	private String refereeRealName;
	
	
	
    
	public String getNst_CompanyAddress() {
		return nst_CompanyAddress;
	}
	public void setNst_CompanyAddress(String nst_CompanyAddress) {
		this.nst_CompanyAddress = nst_CompanyAddress;
	}
	public String getNstCreateTimeFlag() {
		return nstCreateTimeFlag;
	}
	public void setNstCreateTimeFlag(String nstCreateTimeFlag) {
		this.nstCreateTimeFlag = nstCreateTimeFlag;
	}
	public Date getNstCreateTime() {
		return nstCreateTime;
	}
	public void setNstCreateTime(Date nstCreateTime) {
		this.nstCreateTime = nstCreateTime;
	}
	public String getIsCertify() {
		return isCertify;
	}
	public void setIsCertify(String isCertify) {
		this.isCertify = isCertify;
	}
	public String getNst_cardPhotoUrl() {
		return nst_cardPhotoUrl;
	}
	public void setNst_cardPhotoUrl(String nst_cardPhotoUrl) {
		this.nst_cardPhotoUrl = nst_cardPhotoUrl;
	}
	public String getNst_bzlPhotoUrl() {
		return nst_bzlPhotoUrl;
	}
	public void setNst_bzlPhotoUrl(String nst_bzlPhotoUrl) {
		this.nst_bzlPhotoUrl = nst_bzlPhotoUrl;
	}
	public String getMemberCarlineCount() {
		return memberCarlineCount;
	}
	public void setMemberCarlineCount(String memberCarlineCount) {
		this.memberCarlineCount = memberCarlineCount;
	}
	public String getIfcardPhotoUrl() {
		return ifcardPhotoUrl;
	}
	public void setIfcardPhotoUrl(String ifcardPhotoUrl) {
		this.ifcardPhotoUrl = ifcardPhotoUrl;
	}
	public String getIfbzlPhotoUrl() {
		return ifbzlPhotoUrl;
	}
	public void setIfbzlPhotoUrl(String ifbzlPhotoUrl) {
		this.ifbzlPhotoUrl = ifbzlPhotoUrl;
	}
	public String getIfvehiclePhotoUrl() {
		return ifvehiclePhotoUrl;
	}
	public void setIfvehiclePhotoUrl(String ifvehiclePhotoUrl) {
		this.ifvehiclePhotoUrl = ifvehiclePhotoUrl;
	}
	public String getIfdriverPhotoUrl() {
		return ifdriverPhotoUrl;
	}
	public void setIfdriverPhotoUrl(String ifdriverPhotoUrl) {
		this.ifdriverPhotoUrl = ifdriverPhotoUrl;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getNstEvaluate() {
		return nstEvaluate;
	}
	public void setNstEvaluate(Integer nstEvaluate) {
		this.nstEvaluate = nstEvaluate;
	}
	public Map<String, Long> getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Map<String, Long> commentCount) {
		this.commentCount = commentCount;
	}
	public int getEvaluateTypeCount() {
		return evaluateTypeCount;
	}
	public void setEvaluateTypeCount(int evaluateTypeCount) {
		this.evaluateTypeCount = evaluateTypeCount;
	}
	public String getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}
	public String getCcityIds() {
		return ccityIds;
	}
	public void setCcityIds(String ccityIds) {
		this.ccityIds = ccityIds;
	}
	private Integer startRow=0;
	private Integer endRow=10;
	private String userTypes;
	private String ccityIds;
	private Long ccityId;
	//农速通的个人中心,增加被驳回的原因
	private String nstRjReason;
	
    public String getNstRjReason() {
		return nstRjReason;
	}
	public void setNstRjReason(String nstRjReason) {
		this.nstRjReason = nstRjReason;
	}
	public Long getCcityId() {
		return ccityId;
	}
	public void setCcityId(Long ccityId) {
		this.ccityId = ccityId;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	
//登录设备
	private Integer device;



    public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
	}
	public String getNstStatus() {
		return nstStatus;
	}
	public void setNstStatus(String nstStatus) {
		this.nstStatus = nstStatus;
	}
	
	public String getCertificationType() {
		return certificationType;
	}
	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getActityNo() {
		return ActityNo;
	}

	public void setActityNo(String actityNo) {
		ActityNo = actityNo;
	}

//	public String getMemberId_ed() {
//		return memberId_ed;
//	}
//
//	public void setMemberId_ed(String memberId_ed) {
//		this.memberId_ed = memberId_ed;
//	}

	public String getCompanyMobile() {
		return companyMobile;
	}

	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}
	private String companyContact;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}
	private String app;
	
	

	public String getSendDateString() {
		return sendDateString;
	}

	public void setSendDateString(String sendDateString) {
		this.sendDateString = sendDateString;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	//增加birthday的String类型，用于 mybaties操作数据库Date类型的insert和更新
	public String birthday_string;

    public String getBirthday_string() {
		return birthday_string;
	}

	public void setBirthday_string(String birthday_string) {
		this.birthday_string = birthday_string;
	}

	public String getUpdateTime_string() {
		return updateTime_string;
	}

	public void setUpdateTime_string(String updateTime_string) {
		this.updateTime_string = updateTime_string;
	}

	public String getCreateTime_string() {
		return createTime_string;
	}

	public void setCreateTime_string(String createTime_string) {
		this.createTime_string = createTime_string;
	}
	
	//增加updateTime的String类型，用于 mybaties操作数据库Date类型的insert和更新

	public Integer getHasFocusCategory() {
		return hasFocusCategory;
	}

	public void setHasFocusCategory(Integer hasFocusCategory) {
		this.hasFocusCategory = hasFocusCategory;
	}
	public List<?> getFocusCategoryList() {
		return focusCategoryList;
	}

	public void setFocusCategoryList(List<?> focusCategoryList) {
		this.focusCategoryList = focusCategoryList;
	}
	public List<?> getReMarketList() {
		return reMarketList;
	}

	public void setReMarketList(List<?> reMarketList) {
		this.reMarketList = reMarketList;
	}
	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}
	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Long getBusiMemberId() {
		return busiMemberId;
	}
	public void setBusiMemberId(Long busiMemberId) {
		this.busiMemberId = busiMemberId;
	}
	public String updateTime_string;
	
	//增加createTime的String类型，用于 mybaties操作数据库Date类型的insert和更新

	public String createTime_string;

	public String getRefereeMobile() {
		return refereeMobile;
	}
	public void setRefereeMobile(String refereeMobile) {
		this.refereeMobile = refereeMobile;
	}
	public String getRefereeRealName() {
		return refereeRealName;
	}
	public void setRefereeRealName(String refereeRealName) {
		this.refereeRealName = refereeRealName;
	}
	
	
	
}

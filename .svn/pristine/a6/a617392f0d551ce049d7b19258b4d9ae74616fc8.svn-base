package cn.gdeng.nst.entity.nst;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rule_logistic")
public class RuleLogisticEntity  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3978013463393337809L;

	/**
    *主键ID
    */
    private Integer id;
    /**
    *关联规则Id
    */
    private Integer ruleInfoId;
    /**
    *物流公司memberId
    */
    private Integer memberId;
    /**
    *1~9 数字越大，级别越大
    */
    private Integer level;
    /**
    *每天分配数量
    */
    private Integer dayCount;
    
    /**
     * 日分配上限
     */
    private Integer dayLimt;
    /**
    *当前日期(如果当前日期和这个日期不同,则需要将dayCount进行清空)
    */
    private Date currentDay;
    /**
    *已使用总配额
    */
    private Integer totalCount;
    
    /**
     * 分配总上限(货源分配总数)
     */
    private Integer totalLimt;
    
    /**
     * 分配开始时间
     */
    private Date startTime;
    
    /**
     * 分配结束时间
     */
    private Date endTime;
    
    /**
     * 会员类型：2 车主 3 物流公司
     */
    private Integer memberType;
    
    /**
    *是否删除(0:未删除;1:已删除)
    */
    private Byte isDeleted;
    /**
    *创建人员ID
    */
    private String createUserId;
    /**
    *创建时间
    */
    private Date createTime;
    /**
    *修改人员ID
    */
    private String updateUserId;
    /**
    *修改时间
    */
    private Date updateTime;
    @Id
    @Column(name = "id")
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    
    @Column(name = "ruleInfoId")
    public Integer getRuleInfoId(){
        return this.ruleInfoId;
    }
    public void setRuleInfoId(Integer ruleInfoId){
        this.ruleInfoId = ruleInfoId;
    }
    
    @Column(name = "memberId")
    public Integer getMemberId(){
        return this.memberId;
    }
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }
    
    @Column(name = "level")
    public Integer getLevel(){
        return this.level;
    }
    public void setLevel(Integer level){
        this.level = level;
    }
    
    @Column(name = "dayCount")
    public Integer getDayCount(){
        return this.dayCount;
    }
    public void setDayCount(Integer dayCount){
        this.dayCount = dayCount;
    }
    
    @Column(name = "currentDay")
    public Date getCurrentDay(){
        return this.currentDay;
    }
    public void setCurrentDay(Date currentDay){
        this.currentDay = currentDay;
    }
    
    @Column(name = "totalCount")
    public Integer getTotalCount(){
        return this.totalCount;
    }
    public void setTotalCount(Integer totalCount){
        this.totalCount = totalCount;
    }
    
    @Column(name = "isDeleted")
    public Byte getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(Byte isDeleted){
        this.isDeleted = isDeleted;
    }
    
    @Column(name = "createUserId")
    public String getCreateUserId(){
        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){
        this.createUserId = createUserId;
    }
    
    @Column(name = "createTime")
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    
    @Column(name = "updateUserId")
    public String getUpdateUserId(){
        return this.updateUserId;
    }
    public void setUpdateUserId(String updateUserId){
        this.updateUserId = updateUserId;
    }
    
    @Column(name = "updateTime")
    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    @Column(name = "dayLimt")
	public Integer getDayLimt() {
		return dayLimt;
	}
	public void setDayLimt(Integer dayLimt) {
		this.dayLimt = dayLimt;
	}
	
	@Column(name = "totalLimt")
	public Integer getTotalLimt() {
		return totalLimt;
	}
	public void setTotalLimt(Integer totalLimt) {
		this.totalLimt = totalLimt;
	}
	
	@Column(name = "memberType")
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	
	@Column(name = "startTime")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "endTime")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}

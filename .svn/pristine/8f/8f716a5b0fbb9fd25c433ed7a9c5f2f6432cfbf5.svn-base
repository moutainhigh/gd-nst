package cn.gdeng.nst.entity.nst;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "member_count")
public class MemberCountEntity  implements java.io.Serializable{
    
    private static final long serialVersionUID = -7290098147420186948L;
    private Integer id;
    /**
    *会员id
    */
    private Integer memberId;
    /**
    *司机成功接单数
    */
    private Integer driverOrderCount=0;
    /**
    *司机总收益
    */
    private Double driverIcome=0.0;
    /**
    *物流公司总接单数
    */
    private Integer confirmSourceCount=0;
    /**
    *货源分配总数
    */
    private Integer AutoSourceCount=0;
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
    /**
     * 用户注册时间
     */
    private Date regTime;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
        @Column(name = "memberId")
    public Integer getMemberId(){
        return this.memberId;
    }
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }
    @Column(name = "driverOrderCount")
    public Integer getDriverOrderCount(){
        return this.driverOrderCount;
    }
    public void setDriverOrderCount(Integer driverOrderCount){
        this.driverOrderCount = driverOrderCount;
    }
    @Column(name = "driverIcome")
    public Double getDriverIcome(){
        return this.driverIcome;
    }
    public void setDriverIcome(Double driverIcome){
        this.driverIcome = driverIcome;
    }
    @Column(name = "confirmSourceCount")
    public Integer getConfirmSourceCount(){
        return this.confirmSourceCount;
    }
    public void setConfirmSourceCount(Integer confirmSourceCount){
        this.confirmSourceCount = confirmSourceCount;
    }
    @Column(name = "AutoSourceCount")
    public Integer getAutoSourceCount(){
        return this.AutoSourceCount;
    }
    public void setAutoSourceCount(Integer AutoSourceCount){
        this.AutoSourceCount = AutoSourceCount;
    }
    @Column(name = "createUserId")
    public String getCreateUserId(){
        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){
        this.createUserId = createUserId;
    }
 
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
    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    @Column(name = "regTime")
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
}

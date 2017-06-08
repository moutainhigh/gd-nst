
package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rule_logistic_assign")
public class RuleLogisticAssignEntity  implements java.io.Serializable{
    /**
    *主键ID
    */
    private Integer id;

    /**
    *货主memberId
    */
    private Integer memberIdShipper;

    /**
    *物流公司memberId
    */
    private Integer memberIdLogistic;

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
    
    /**
     * 会员类型
     */
    private Integer  memberType;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "memberIdShipper")
    public Integer getMemberIdShipper(){

        return this.memberIdShipper;
    }
    public void setMemberIdShipper(Integer memberIdShipper){

        this.memberIdShipper = memberIdShipper;
    }
    @Column(name = "memberIdLogistic")
    public Integer getMemberIdLogistic(){

        return this.memberIdLogistic;
    }
    public void setMemberIdLogistic(Integer memberIdLogistic){

        this.memberIdLogistic = memberIdLogistic;
    }
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
    @Column(name = "memberType")
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
   
    
}

package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "member_cer_audit")
public class MemberCerAuditEntity  implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 9159762976453145892L;

    /**
    *主键ID
    */
    private Integer id;
    /**
    *认证表ID
    */
    private Integer cerId;
    /**
    *审核意见
    */
    private String auditOpinion;
    /**
    *农速通认证状态(1:认证通过 2:认证未通过)
    */
    private Byte cerStatus;
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
    @Column(name = "cerId")
    public Integer getCerId(){
        return this.cerId;
    }
    public void setCerId(Integer cerId){
        this.cerId = cerId;
    }
    @Column(name = "auditOpinion")
    public String getAuditOpinion(){
        return this.auditOpinion;
    }
    public void setAuditOpinion(String auditOpinion){
        this.auditOpinion = auditOpinion;
    }
    @Column(name = "cerStatus")
    public Byte getCerStatus(){
        return this.cerStatus;
    }
    public void setCerStatus(Byte cerStatus){
        this.cerStatus = cerStatus;
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
}

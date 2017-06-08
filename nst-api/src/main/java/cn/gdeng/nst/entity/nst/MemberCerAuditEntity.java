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

    *认证表ID
    */
    private Integer cerId;

    *审核意见
    */
    private String auditOpinion;

    *农速通认证状态(1:认证通过 2:认证未通过)
    */
    private Byte cerStatus;

    *创建人员ID
    */
    private String createUserId;

    *创建时间
    */
    private Date createTime;

    *修改人员ID
    */
    private String updateUserId;

    *修改时间
    */
    private Date updateTime;

    @Column(name = "id")
    public Integer getId(){

    }
    public void setId(Integer id){

    }
    @Column(name = "cerId")
    public Integer getCerId(){

    }
    public void setCerId(Integer cerId){

    }
    @Column(name = "auditOpinion")
    public String getAuditOpinion(){

    }
    public void setAuditOpinion(String auditOpinion){

    }
    @Column(name = "cerStatus")
    public Byte getCerStatus(){

    }
    public void setCerStatus(Byte cerStatus){

    }
    @Column(name = "createUserId")
    public String getCreateUserId(){

    }
    public void setCreateUserId(String createUserId){

    }
    @Column(name = "createTime")
    public Date getCreateTime(){

    }
    public void setCreateTime(Date createTime){

    }
    @Column(name = "updateUserId")
    public String getUpdateUserId(){

    }
    public void setUpdateUserId(String updateUserId){

    }
    @Column(name = "updateTime")
    public Date getUpdateTime(){

    }
    public void setUpdateTime(Date updateTime){

    }
}
package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "source_log")
public class SourceLogEntity  implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7812176879273384567L;

	/**
    *主键id
    */
    private Integer id;

    /**
    *关联货源id
    */
    private Integer sourceId;

    /**
    *物流描述
    */
    private String description;

    /**
    *创建人员ID
    */
    private String createUserId;

    /**
    *创建时间
    */
    private Date createTime = new Date();

    /**
    *修改人员ID
    */
    private String updateUserId;

    /**
    *修改时间
    */
    private Date updateTime = new Date();

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "sourceId")
    public Integer getSourceId(){

        return this.sourceId;
    }
    public void setSourceId(Integer sourceId){

        this.sourceId = sourceId;
    }
    @Column(name = "description")
    public String getDescription(){

        return this.description;
    }
    public void setDescription(String description){

        this.description = description;
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
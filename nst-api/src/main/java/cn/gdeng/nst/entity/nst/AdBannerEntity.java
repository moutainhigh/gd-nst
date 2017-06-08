package cn.gdeng.nst.entity.nst;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ad_banner")
public class AdBannerEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6790541578564671862L;

    private Integer id;

    private String name;
    
    private Integer type;

    private String imgUrl;

    private Byte channel;

    private Date startTime;

    private Date endTime;

    private Integer state;

    private Integer sort;

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "name")
    public String getName(){

        return this.name;
    }
    public void setName(String name){

        this.name = name;
    }
	@Column(name = "type")
    public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "imgUrl")
    public String getImgUrl(){

        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl){

        this.imgUrl = imgUrl;
    }
    @Column(name = "channel")
    public Byte getChannel(){

        return this.channel;
    }
    public void setChannel(Byte channel){

        this.channel = channel;
    }
    @Column(name = "startTime")
    public Date getStartTime(){

        return this.startTime;
    }
    public void setStartTime(Date startTime){

        this.startTime = startTime;
    }
    @Column(name = "endTime")
    public Date getEndTime(){

        return this.endTime;
    }
    public void setEndTime(Date endTime){

        this.endTime = endTime;
    }
    @Column(name = "state")
    public Integer getState(){

        return this.state;
    }
    public void setState(Integer state){

        this.state = state;
    }
    @Column(name = "sort")
    public Integer getSort(){

        return this.sort;
    }
    public void setSort(Integer sort){

        this.sort = sort;
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

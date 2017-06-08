package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "advertisement")
public class AdvertisementEntity  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892981037322027561L;

	/**
    *
    */
    private Integer id;

    /**
    *广告名称
    */
    private String name;

    /**
    *渠道（1车主APP；2货主APP；3物流公司APP）
    */
    private Byte channel;

    /**
    *开始时间
    */
    private Date startTime;

    /**
    *结束时间
    */
    private Date endTime;

    /**
    *图片url
    */
    private String imgUrl;

    /**
    *状态（1上架，2下架，3过期，4删除）
    */
    private Byte status;

    /**
    *省份
    */
    private Integer provinceId;

    /**
    *城市
    */
    private Integer cityId;
    
    /**
     * 排序
     */
    private Integer sort;

    /**
    *创建用户id
    */
    private String createUserId;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *修改人id
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
    @Column(name = "name")
    public String getName(){

        return this.name;
    }
    public void setName(String name){

        this.name = name;
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
    @Column(name = "imgUrl")
    public String getImgUrl(){

        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl){

        this.imgUrl = imgUrl;
    }
    @Column(name = "status")
    public Byte getStatus(){

        return this.status;
    }
    public void setStatus(Byte status){

        this.status = status;
    }
    @Column(name = "provinceId")
    public Integer getProvinceId(){

        return this.provinceId;
    }
    public void setProvinceId(Integer provinceId){

        this.provinceId = provinceId;
    }
    @Column(name = "cityId")
    public Integer getCityId(){

        return this.cityId;
    }
    public void setCityId(Integer cityId){

        this.cityId = cityId;
    }
    @Column(name = "sort")
    public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
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


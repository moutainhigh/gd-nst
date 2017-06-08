package cn.gdeng.nst.admin.dto.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import cn.gdeng.nst.enums.AppTypeEnum;
import cn.gdeng.nst.enums.NstNoticeOnOffEnum;


/**
 * 农速通公告DTO
 * @author 小俊
 */
@SuppressWarnings("serial")
public class NstNoticeEntityDTO implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
    *
    */
    private Integer id;

    /**
    *公告名称
    */
    private String title;

    /**
    *所属渠道: 0全部 1车主 2货主 3物流公司
    */
    private Byte channel;

    /**
    *出发地省会Id
    */
    private Integer provinceId;

    /**
    *出发地城市Id
    */
    private Integer cityId;

    /**
    *省份
    */
    private String province;

    /**
    *城市
    */
    private String city;

    /**
    *公告内容
    */
    private String content;

    /**
    *启用禁用: 0待发 1关闭 2开启
    */
    private Byte onOff;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *创建用户id
    */
    private String createUserId;

    /**
    *修改时间
    */
    private Date updateTime;

    /**
    *修改用户id
    */
    private String updateUserId;
    
    private String  userName;

    @Id
    @Column(name = "id")
    public Integer getId(){

        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    @Column(name = "title")
    public String getTitle(){

        return this.title;
    }
    public void setTitle(String title){

        this.title = title;
    }
    @Column(name = "channel")
    public Byte getChannel(){

        return this.channel;
    }
   
   public String  getChannelStr(){
	   if(getChannel()!=null){
		   return  AppTypeEnum.getNameByCode(getChannel());   
	   }
	   return null;
   }
    
    public void setChannel(Byte channel){

        this.channel = channel;
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
    @Column(name = "province")
    public String getProvince(){

        return this.province;
    }
    public void setProvince(String province){

        this.province = province;
    }
    @Column(name = "city")
    public String getCity(){

        return this.city;
    }
    public void setCity(String city){

        this.city = city;
    }
    @Column(name = "content")
    public String getContent(){

        return this.content;
    }
    public void setContent(String content){

        this.content = content;
    }
    @Column(name = "onOff")
    public Byte getOnOff(){

        return this.onOff;
    }
    public String  getOnOffStr(){
 	   if(getOnOff()!=null){
 		 return  NstNoticeOnOffEnum.getNameByCode(getOnOff());   
 	   }
 	   return null;
    }
    public void setOnOff(Byte onOff){

        this.onOff = onOff;
    }
    @Column(name = "createTime")
    public Date getCreateTime(){

        return this.createTime;
    }
    public void setCreateTime(Date createTime){

        this.createTime = createTime;
    }
    @Column(name = "createUserId")
    public String getCreateUserId(){

        return this.createUserId;
    }
    public void setCreateUserId(String createUserId){

        this.createUserId = createUserId;
    }
    @Column(name = "updateTime")
    public Date getUpdateTime(){

        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){

        this.updateTime = updateTime;
    }
    @Column(name = "updateUserId")
    public String getUpdateUserId(){

        return this.updateUserId;
    }
    public void setUpdateUserId(String updateUserId){

        this.updateUserId = updateUserId;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}

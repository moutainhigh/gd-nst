package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rule_onoff")
public class RuleOnoffEntity  implements java.io.Serializable{
    /**
    *主键ID
    */
    private Integer id;
    /**
    *物流公司memberId
    */
    private Integer memberId;
    /**
    *是否开启(1:关闭;2:开启)
    */
    private Byte onOff=1;
    /**
    *省会Id
    */
    private Integer provinceId;
    /**
    *城市Id
    */
    private Integer cityId;
    /**
    *区域Id
    */
    private Integer areaId;
    /**
    *省份+城市+区域（/  作为分隔符）
    */
    private String detail;
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
    @Column(name = "memberId")
    public Integer getMemberId(){
        return this.memberId;
    }
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }
    @Column(name = "onOff")
    public Byte getOnOff(){
        return this.onOff;
    }
    public void setOnOff(Byte onOff){
        this.onOff = onOff;
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
    @Column(name = "areaId")
    public Integer getAreaId(){
        return this.areaId;
    }
    public void setAreaId(Integer areaId){
        this.areaId = areaId;
    }
    @Column(name = "detail")
    public String getDetail(){
        return this.detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
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
}

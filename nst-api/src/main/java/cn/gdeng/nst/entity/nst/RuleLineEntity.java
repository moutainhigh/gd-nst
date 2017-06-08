package cn.gdeng.nst.entity.nst;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rule_line")
public class RuleLineEntity  implements java.io.Serializable{
    /**
    *主键ID
    */
    private Integer id;
    /**
    *物流公司memberId
    */
    private Integer memberId;
    /**
    *1 干线 2 同城
    */
    private Integer lineType;
    /**
    *出发地省会Id
    */
    private Integer s_provinceId;
    /**
    *出发地城市Id
    */
    private Integer s_cityId;
    /**
    *出发地区域Id
    */
    private Integer s_areaId;
    /**
    *出发地 省份+城市+区域（/  作为分隔符）
    */
    private String s_detail;
    /**
    *目的地省会Id
    */
    private Integer e_provinceId;
    /**
    *目的地城市Id
    */
    private Integer e_cityId;
    /**
    *目的地区域Id
    */
    private Integer e_areaId;
    /**
    *目的地 省份+城市+区域（/  作为分隔符）
    */
    private String e_detail;
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
    @Column(name = "lineType")
    public Integer getLineType(){
        return this.lineType;
    }
    public void setLineType(Integer lineType){
        this.lineType = lineType;
    }
    @Column(name = "s_provinceId")
    public Integer getS_provinceId(){
        return this.s_provinceId;
    }
    public void setS_provinceId(Integer s_provinceId){
        this.s_provinceId = s_provinceId;
    }
    @Column(name = "s_cityId")
    public Integer getS_cityId(){
        return this.s_cityId;
    }
    public void setS_cityId(Integer s_cityId){
        this.s_cityId = s_cityId;
    }
    @Column(name = "s_areaId")
    public Integer getS_areaId(){
        return this.s_areaId;
    }
    public void setS_areaId(Integer s_areaId){
        this.s_areaId = s_areaId;
    }
    @Column(name = "s_detail")
    public String getS_detail(){
        return this.s_detail;
    }
    public void setS_detail(String s_detail){
        this.s_detail = s_detail;
    }
    @Column(name = "e_provinceId")
    public Integer getE_provinceId(){
        return this.e_provinceId;
    }
    public void setE_provinceId(Integer e_provinceId){
        this.e_provinceId = e_provinceId;
    }
    @Column(name = "e_cityId")
    public Integer getE_cityId(){
        return this.e_cityId;
    }
    public void setE_cityId(Integer e_cityId){
        this.e_cityId = e_cityId;
    }
    @Column(name = "e_areaId")
    public Integer getE_areaId(){
        return this.e_areaId;
    }
    public void setE_areaId(Integer e_areaId){
        this.e_areaId = e_areaId;
    }
    @Column(name = "e_detail")
    public String getE_detail(){
        return this.e_detail;
    }
    public void setE_detail(String e_detail){
        this.e_detail = e_detail;
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

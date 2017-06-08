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

    *物流公司memberId
    */
    private Integer memberId;

    *是否开启(1:关闭;2:开启)
    */
    private Byte onOff=1;

    *省会Id
    */
    private Integer provinceId;

    *城市Id
    */
    private Integer cityId;

    *区域Id
    */
    private Integer areaId;

    *省份+城市+区域（/  作为分隔符）
    */
    private String detail;

    *是否删除(0:未删除;1:已删除)
    */
    private Byte isDeleted;

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
    @Column(name = "memberId")
    public Integer getMemberId(){

    }
    public void setMemberId(Integer memberId){

    }
    @Column(name = "onOff")
    public Byte getOnOff(){

    }
    public void setOnOff(Byte onOff){

    }
    @Column(name = "provinceId")
    public Integer getProvinceId(){

    }
    public void setProvinceId(Integer provinceId){

    }
    @Column(name = "cityId")
    public Integer getCityId(){

    }
    public void setCityId(Integer cityId){

    }
    @Column(name = "areaId")
    public Integer getAreaId(){

    }
    public void setAreaId(Integer areaId){

    }
    @Column(name = "detail")
    public String getDetail(){

    }
    public void setDetail(String detail){

    }
    public Byte getIsDeleted(){

    }
    public void setIsDeleted(Byte isDeleted){

    }
    @Column(name = "createUserId")
    public String getCreateUserId(){

    }
    public void setCreateUserId(String createUserId){

    }
    public Date getCreateTime(){

    }
    public void setCreateTime(Date createTime){

    }
    @Column(name = "updateUserId")
    public String getUpdateUserId(){

    }
    public void setUpdateUserId(String updateUserId){

    }
    public Date getUpdateTime(){

    }
    public void setUpdateTime(Date updateTime){

    }
}
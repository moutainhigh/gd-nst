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

    *物流公司memberId
    */
    private Integer memberId;

    *1 干线 2 同城
    */
    private Integer lineType;

    *出发地省会Id
    */
    private Integer s_provinceId;

    *出发地城市Id
    */
    private Integer s_cityId;

    *出发地区域Id
    */
    private Integer s_areaId;

    *出发地 省份+城市+区域（/  作为分隔符）
    */
    private String s_detail;

    *目的地省会Id
    */
    private Integer e_provinceId;

    *目的地城市Id
    */
    private Integer e_cityId;

    *目的地区域Id
    */
    private Integer e_areaId;

    *目的地 省份+城市+区域（/  作为分隔符）
    */
    private String e_detail;

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
    @Column(name = "lineType")
    public Integer getLineType(){

    }
    public void setLineType(Integer lineType){

    }
    @Column(name = "s_provinceId")
    public Integer getS_provinceId(){

    }
    public void setS_provinceId(Integer s_provinceId){

    }
    @Column(name = "s_cityId")
    public Integer getS_cityId(){

    }
    public void setS_cityId(Integer s_cityId){

    }
    @Column(name = "s_areaId")
    public Integer getS_areaId(){

    }
    public void setS_areaId(Integer s_areaId){

    }
    @Column(name = "s_detail")
    public String getS_detail(){

    }
    public void setS_detail(String s_detail){

    }
    @Column(name = "e_provinceId")
    public Integer getE_provinceId(){

    }
    public void setE_provinceId(Integer e_provinceId){

    }
    @Column(name = "e_cityId")
    public Integer getE_cityId(){

    }
    public void setE_cityId(Integer e_cityId){

    }
    @Column(name = "e_areaId")
    public Integer getE_areaId(){

    }
    public void setE_areaId(Integer e_areaId){

    }
    @Column(name = "e_detail")
    public String getE_detail(){

    }
    public void setE_detail(String e_detail){

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
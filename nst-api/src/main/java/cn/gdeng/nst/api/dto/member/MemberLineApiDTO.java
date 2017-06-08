package cn.gdeng.nst.api.dto.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 常跑线路DTO
 *  @author WANGKAI 2016 08-11
 */
public class MemberLineApiDTO  implements java.io.Serializable {
	
	private static final long serialVersionUID = 6623389461872077543L;

	/**
	 * 认证ID
	 */
	private Integer id;
	/**
	 * 关联会员ID
	 */
    private Integer memberId;
	/**
	 * 线路类型
	 */
    private Integer lineType;
	/**
	 * 出发地省会Id
	 */
    private Integer sProvinceId;
	/**
	 * 出发地城市Id
	 */
    private Integer sCityId;
	/**
	 * 出发地区域Id
	 */
    private Integer sAreaId;
	/**
	 * 出发地 省份+城市+区域（/  作为分隔符）
	 */
    private String sDetail;
	/**
	 * 目的地省会Id
	 */
    private Integer eProvinceId;
	/**
	 * 目的地城市Id
	 */
    private Integer eCityId;

	/**
	 * 目的地区域Id
	 */
    private Integer eAreaId;

	/**
	 * 目的地 省份+城市+区域 （/  作为分隔符）
	 */
    private String eDetail;

	/**
	 * 是否删除(0:未删除;1:已删除)
	 */
    private Byte isDeleted;

	/**
	 * 创建人员ID
	 */
    private String createUserId;

	/**
	 * 创建时间
	 */
    private Date createTime;

	/**
	 * 修改人员ID
	 */
    private String updateUserId;

	/**
	 * 修改时间
	 */
    private Date updateTime;

	/**
	 * 线路发布人
	 */
	private String publisher;
	
	/**
	 * 线路发布人手机
	 */
	private String phone;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){

        this.id = id;
    }
    public Integer getMemberId(){

        return this.memberId;
    }
    public void setMemberId(Integer memberId){

        this.memberId = memberId;
    }
    public Integer getLineType() {
		return lineType;
	}
	public void setLineType(Integer lineType) {
		this.lineType = lineType;
	}
    public Integer getsProvinceId(){

        return this.sProvinceId;
    }
    public void setsProvinceId(Integer sProvinceId){

        this.sProvinceId = sProvinceId;
    }
    public Integer getsCityId(){

        return this.sCityId;
    }
    public void setsCityId(Integer sCityId){

        this.sCityId = sCityId;
    }
    public Integer getsAreaId(){

        return this.sAreaId;
    }
    public void setsAreaId(Integer sAreaId){

        this.sAreaId = sAreaId;
    }
    public String getsDetail(){

        return this.sDetail;
    }
    public void setsDetail(String sDetail){

        this.sDetail = sDetail;
    }
    public Integer geteProvinceId() {
    	
		return eProvinceId;
	}
	public void seteProvinceId(Integer eProvinceId) {
		
		this.eProvinceId = eProvinceId;
	}
    public Integer geteCityId(){
    	
        return this.eCityId;
    }

	public void seteCityId(Integer eCityId){
		
        this.eCityId = eCityId;
    }
    public Integer geteAreaId(){

        return this.eAreaId;
    }
    public void seteAreaId(Integer eAreaId){

        this.eAreaId = eAreaId;
    }
    public String geteDetail(){

        return this.eDetail;
    }
    public void seteDetail(String eDetail){

        this.eDetail = eDetail;
    }
    public Byte getIsDeleted(){

        return this.isDeleted;
    }
    public void setIsDeleted(Byte isDeleted){

    }
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

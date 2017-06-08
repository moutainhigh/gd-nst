package cn.gdeng.nst.entity.nst;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "area_setting")
public class AreaSetting implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4055156046768477276L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	//private String areaID;

	@Column(name = "areaName")
	private String areaName;

	/*  会员ID  */
	@Column(name = "memberId")
    private Long memberId;
    
    //  手机
	@Column(name = "mobile")
    private String mobile;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}

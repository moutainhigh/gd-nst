package cn.gdeng.nst.api.vo.member;

import java.io.Serializable;
/**
 * 订阅开关详情
 * @author wangkai 2016-08-11
 *
 */
public class RuleOnoffDetailVo implements Serializable {
	
	private static final long serialVersionUID = -1L;
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
	     * 
	     * 有没有对应的物流规则标识   0：没有 ，1：有
	     */
	    private Integer flag; 
	    
	    
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		public Byte getOnOff() {
			return onOff;
		}

		public void setOnOff(Byte onOff) {
			this.onOff = onOff;
		}

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}

		public Integer getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(Integer provinceId) {
			this.provinceId = provinceId;
		}

		public Integer getCityId() {
			return cityId;
		}

		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}

		public Integer getAreaId() {
			return areaId;
		}

		public void setAreaId(Integer areaId) {
			this.areaId = areaId;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public Integer getFlag() {
			return flag;
		}

		public void setFlag(Integer flag) {
			this.flag = flag;
		}
	    
	    
}

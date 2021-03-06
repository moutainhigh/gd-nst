package cn.gdeng.nst.api.dto.member;
/**
 * 收货模式开关DTO
 * @author WANGKAI 2016 08-11
 *
 */
public class RuleOnoffDTO implements java.io.Serializable {

	private static final long serialVersionUID = 6623389461872077543L;

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
	    private Byte onOff;

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
	    *省会name
	    */
	    private String provinceName;
	    /**
	    *城市name
	    */
	    private String cityName;
	    /**
	    *区域name
	    */
	    private String areaName;
	    
	    /**
	     * 区分app
	     */
	    private String deviceApp;
	    
		public String getProvinceName() {
			return provinceName;
		}

		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}

		public Byte getOnOff() {
			return onOff;
		}

		public void setOnOff(Byte onOff) {
			this.onOff = onOff;
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

		public String getDeviceApp() {
			return deviceApp;
		}

		public void setDeviceApp(String deviceApp) {
			this.deviceApp = deviceApp;
		}
	    
	    
}

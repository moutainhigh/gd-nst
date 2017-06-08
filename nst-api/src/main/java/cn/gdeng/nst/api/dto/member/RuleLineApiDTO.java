package cn.gdeng.nst.api.dto.member;

import java.util.Date;
/**
 * 订阅线路DTO
 * @author WANGKAI 2016 08-11
 */
public class RuleLineApiDTO implements java.io.Serializable {
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

		public Integer getLineType() {
			return lineType;
		}

		public void setLineType(Integer lineType) {
			this.lineType = lineType;
		}

		public Integer getS_provinceId() {
			return s_provinceId;
		}

		public void setS_provinceId(Integer s_provinceId) {
			this.s_provinceId = s_provinceId;
		}

		public Integer getS_cityId() {
			return s_cityId;
		}

		public void setS_cityId(Integer s_cityId) {
			this.s_cityId = s_cityId;
		}

		public Integer getS_areaId() {
			return s_areaId;
		}

		public void setS_areaId(Integer s_areaId) {
			this.s_areaId = s_areaId;
		}

		public String getS_detail() {
			return s_detail;
		}

		public void setS_detail(String s_detail) {
			this.s_detail = s_detail;
		}

		public Integer getE_provinceId() {
			return e_provinceId;
		}

		public void setE_provinceId(Integer e_provinceId) {
			this.e_provinceId = e_provinceId;
		}

		public Integer getE_cityId() {
			return e_cityId;
		}

		public void setE_cityId(Integer e_cityId) {
			this.e_cityId = e_cityId;
		}

		public Integer getE_areaId() {
			return e_areaId;
		}

		public void setE_areaId(Integer e_areaId) {
			this.e_areaId = e_areaId;
		}

		public String getE_detail() {
			return e_detail;
		}

		public void setE_detail(String e_detail) {
			this.e_detail = e_detail;
		}

		public Byte getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Byte isDeleted) {
			this.isDeleted = isDeleted;
		}


}

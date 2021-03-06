package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.gdeng.nst.entity.nst.FindCarLineEntity;
import cn.gdeng.nst.util.web.api.ParamProcessUtil;

/**
 * @author DJB
 * @version 创建时间：2016年8月4日 下午2:44:59 物流公司找车线路DTO
 */
public class FindCarLineDto implements Serializable {

	/**
	 * cn.gdeng.nst.api.dto.source
	 */
	private static final long serialVersionUID = -6953139412221645182L;
	/** 线路编号 */
	private Integer id;
	/** 会员ID */
	private Integer memberId;
	/** 出发地省ID */
	private Integer s_provinceId;
	/** 出发地市ID */
	private Integer s_cityId;
	/** 出发地区ID */
	private Integer s_areaId;
	/** 出发地中文详细地址 （去掉斜杠） */
	private String s_detail;
	/** 目的地省ID */
	private Integer e_provinceId;
	/** 目的地市ID */
	private Integer e_cityId;
	/** 目的地区ID */
	private Integer e_areaId;
	/** 出发地中文详细地址 （ 去掉斜杠） */
	private String e_detail;
	/** 删除标识 */
	private Byte isSelect;
	/** 更新时间 */
	private Date updateTime;

	public FindCarLineDto() {
		super();
	}

	public FindCarLineDto(FindCarLineEntity findCarLineEntity) {
		this.id = findCarLineEntity.getId();
		this.memberId = findCarLineEntity.getMemberId();
		this.s_provinceId = findCarLineEntity.getS_provinceId();
		this.s_cityId = findCarLineEntity.getS_cityId();
		this.s_areaId = findCarLineEntity.getS_areaId();

		if ( StringUtils.isNotBlank(s_detail)) {
			this.s_detail = ParamProcessUtil.ridAndSpliceAddr(s_detail, "/");

		} else {
			this.s_detail = findCarLineEntity.getS_detail();
		}

		this.e_provinceId = findCarLineEntity.getE_provinceId();
		this.e_cityId = findCarLineEntity.getE_cityId();
		this.e_areaId = findCarLineEntity.getE_areaId();
		if ( StringUtils.isNotBlank(findCarLineEntity.getE_detail())) {
			this.e_detail = ParamProcessUtil.ridAndSpliceAddr(findCarLineEntity.getE_detail(), "/");

		} else {
			this.e_detail = findCarLineEntity.getE_detail();
		}

		this.isSelect = findCarLineEntity.getIsSelect();
		this.updateTime = findCarLineEntity.getUpdateTime();
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

		if ( StringUtils.isNotBlank(s_detail)) {
			this.s_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(s_detail, "/");

		} else {
			this.s_detail = s_detail;
		}

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
		if ( StringUtils.isNotBlank(e_detail)) {
			this.e_detail = ParamProcessUtil.ridProvinceAndSpliceAddr(e_detail, "/");
		} else {
			this.e_detail = e_detail;
		}
	}

	public Byte getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Byte isSelect) {
		this.isSelect = isSelect;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}

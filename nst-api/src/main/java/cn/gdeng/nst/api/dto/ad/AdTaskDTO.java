package cn.gdeng.nst.api.dto.ad;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告定时任务DTO
 * 
 * @author dengjianfeng
 *
 */
public class AdTaskDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4795851401329862252L;

	/**
	*
	*/
	private Integer id;

	/**
	 * 广告名称
	 */
	private String name;

	/**
	 * 渠道（1车主APP；2货主APP；3物流公司APP）
	 */
	private Byte channel;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 图片url
	 */
	private String imgUrl;

	/**
	 * 状态（1上架，2下架，3过期，4删除）
	 */
	private Byte status;

	/**
	 * 省份
	 */
	private Integer provinceId;

	/**
	 * 城市
	 */
	private Integer cityId;

	/**
	 * 排序
	 */
	private Integer sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getChannel() {
		return channel;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}

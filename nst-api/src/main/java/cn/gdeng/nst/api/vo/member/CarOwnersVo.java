package cn.gdeng.nst.api.vo.member;

import java.io.Serializable;
import java.util.List;

public class CarOwnersVo implements Serializable {

	private static final long serialVersionUID = 5157010743864355832L;
    /**
     * 会员姓名
     */
	private String userName;
	/**
	 * 头像
	 */
	private String iconUrl;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 个人认证状态
	 */
	private Integer cerPersonalStatus;
	/**
	 * 司机成功接单数
	 */
	private Long driverOrderCount;
	/**
	 * 车辆信息
	 */
	private List<?> carList;
	/**
	 * 线路信息
	 */
	private List<?> lineList;
		
	public CarOwnersVo(){}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCerPersonalStatus() {
		return cerPersonalStatus;
	}

	public void setCerPersonalStatus(Integer cerPersonalStatus) {
		this.cerPersonalStatus = cerPersonalStatus;
	}

	public Long getDriverOrderCount() {
		return driverOrderCount;
	}

	public void setDriverOrderCount(Long driverOrderCount) {
		this.driverOrderCount = driverOrderCount;
	}

	public List<?> getCarList() {
		return carList;
	}

	public void setCarList(List<?> carList) {
		this.carList = carList;
	}

	public List<?> getLineList() {
		return lineList;
	}

	public void setLineList(List<?> lineList) {
		this.lineList = lineList;
	}
}

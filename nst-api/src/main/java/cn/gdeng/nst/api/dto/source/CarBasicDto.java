package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;

import cn.gdeng.nst.enums.CarTypeEnum;

/**
* @author DJB
* @version 创建时间：2016年8月9日 下午4:26:28
*  物流找车模块-- 车辆基本信息
*/

public class CarBasicDto implements Serializable {
	/**
	 * cn.gdeng.nst.api.dto.source
	 */
	private static final long serialVersionUID = -8800367241137225226L;
	
	private Integer id;
	
	private Integer memberId;//司机ID
	
	private String carNumber;//车牌号
	
	private int carType;//车辆类型
	
	private String carTypeStr;
	
	private Double load;//载重
	
	private Double carLength;//车长
	
	

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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public String getCarTypeStr() {
		return CarTypeEnum.getNameByCode((byte)getCarType());
	}


	public Double getLoad() {
		return load;
	}

	public void setLoad(Double load) {
		this.load = load;
	}


	public Double getCarLength() {
		return carLength;
	}

	public void setCarLength(Double carLength) {
		this.carLength = carLength;
	}


	
	
	/**
	 * 使用java正则表达式去掉多余的.与0
	 * @param s
	 * @return 
	 */
	public static String subZeroAndDot(String s){
	    if(s.indexOf(".") > 0){
	        s = s.replaceAll("0+?$", "");//去掉多余的0
	        s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
	    }
	    return s;
	}
	

}

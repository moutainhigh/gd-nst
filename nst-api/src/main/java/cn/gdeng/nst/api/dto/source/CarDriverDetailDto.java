package cn.gdeng.nst.api.dto.source;

import java.io.Serializable;
import java.util.List;

/**
 * @author DJB
 * @version 创建时间：2016年8月9日 下午4:24:43 物流找车模块-- 司机详细信息
 */

public class CarDriverDetailDto implements Serializable {
	/**
	 * cn.gdeng.nst.api.dto.source
	 */
	private static final long serialVersionUID = 317046258067697438L;

	/** 司机基本信息 */
	private CarDriverBasicDto carDriverBasicDto;

	/** 车辆信息链表 */
	private List<CarBasicDto> carBasicDtoList;

	/** 线路信息链表 */
	private List<FindCarLineDto> findCarLineDtoList;

	public CarDriverDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarDriverDetailDto(CarDriverBasicDto carDriverBasicDto, List<CarBasicDto> carBasicDtoList, List<FindCarLineDto> findCarLineDtoList) {
		super();
		this.carDriverBasicDto = carDriverBasicDto;
		this.carBasicDtoList = carBasicDtoList;
		this.findCarLineDtoList = findCarLineDtoList;
	}

	public CarDriverBasicDto getCarDriverBasicDto() {
		return carDriverBasicDto;
	}

	public void setCarDriverBasicDto(CarDriverBasicDto carDriverBasicDto) {
		this.carDriverBasicDto = carDriverBasicDto;
	}

	public List<CarBasicDto> getCarBasicDtoList() {
		return carBasicDtoList;
	}

	public void setCarBasicDtoList(List<CarBasicDto> carBasicDtoList) {
		this.carBasicDtoList = carBasicDtoList;
	}

	public List<FindCarLineDto> getFindCarLineDtoList() {
		return findCarLineDtoList;
	}

	public void setFindCarLineDtoList(List<FindCarLineDto> findCarLineDtoList) {
		this.findCarLineDtoList = findCarLineDtoList;
	}

}

package cn.gdeng.nst.entity.nst;

import java.io.Serializable;

public class Area implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3150199724033229807L;

	private int id;

	private String areaID;

	private String area;

	private String father;
	
	private Double lng;
	private Double lat;
	
	//2016-03-14
	private String areaType;
	
	
	

	public int getId() {

		return this.id;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getAreaID() {

		return this.areaID;
	}

	public void setAreaID(String areaID) {

		this.areaID = areaID;
	}

	public String getArea() {

		return this.area;
	}

	public void setArea(String area) {

		this.area = area;
	}

	public String getFather() {

		return this.father;
	}

	public void setFather(String father) {

		this.father = father;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	
}

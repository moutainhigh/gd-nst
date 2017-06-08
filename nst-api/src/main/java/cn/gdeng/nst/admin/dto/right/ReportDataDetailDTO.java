package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;
import java.util.List;

public class ReportDataDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5387242706784429354L;

	private List<Number> data;
	
	private String type;
	
	private String typeY;
	
	private String color;
	
	private String parms;

	public List<Number> getData() {
		return data;
	}

	public void setData(List<Number> data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeY() {
		return typeY;
	}

	public void setTypeY(String typeY) {
		this.typeY = typeY;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getParms() {
		return parms;
	}

	public void setParms(String parms) {
		this.parms = parms;
	}

	
}

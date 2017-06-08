package cn.gdeng.nst.enums;

import java.util.ArrayList;
import java.util.List;

public enum CarLengthEnum {
	
//	NO_LIMITED(-1d, "不限"),
	M_1_7(1.7, "1.7"),
	M_2(2.0, "2"),
	M_2_7(2.7, "2.7"),
	M_4_2(4.2, "4.2"),
	M_4_5(4.5, "4.5"),
	M_5(5.0, "5"),
	M_6_2(6.2, "6.2"),
	M_6_8(6.8, "6.8"),
	M_7_2(7.2, "7.2"),
	M_7_7(7.7, "7.7"),
	M_7_8(7.8, "7.8"),
	M_8_2(8.2, "8.2"),
	M_8_6(8.6, "8.6"),
	M_9_6(9.6, "9.6"),
	M_11_7(11.7, "11.7"),
	M_12_5(12.5, "12.5"),
	M_13(13.0, "13"),
	M_13_5(13.5, "13.5"),
	M_14(14.0, "14"),
	M_15(15.0, "15"),
	M_16(16.0, "16"),
	M_17(17.0, "17"),
	M_17_5(17.5, "17.5"),
	M_18(18.0, "18"),
	M_20(20.0, "20"),
	OTHER(-2.0, "其他");
	
	private Double code;
	
	private String name;

	private CarLengthEnum(Double code, String name) {
		this.code = code;
		this.name = name;
	}

	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getNameByCode(Double code){
		CarLengthEnum[] values = CarLengthEnum.values();
		for(CarLengthEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
	
	public static List<Double> getCodeList(){
		List<Double> list = new ArrayList<Double>();
		CarLengthEnum[] values = CarLengthEnum.values();
		for(CarLengthEnum val : values){
			list.add(val.getCode());
		}
		return list;
	}
}

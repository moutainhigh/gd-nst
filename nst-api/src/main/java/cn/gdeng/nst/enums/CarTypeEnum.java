package cn.gdeng.nst.enums;

public enum CarTypeEnum {

	UNLIMITED((byte)-1, "车型不限"),
	MINIVAN((byte)1, "面包车"),
	GOLDEN((byte)2, "金杯车"),
	MINI_FLATCAR((byte)3, "小型平板"),
	MEDIUM_FLATCAR((byte)4, "中型平板"),
	MINI_BOXVAN((byte)5, "小型厢货"),
	LARGE_BOXVAN((byte)6, "大型厢货"),
	GONDOLA((byte)7,"敞车"),
	BOXVAN((byte)8, "厢式货车"),
	HIGH_SIDED_TRUCK((byte)9, "高栏车"),
	FLATCAR((byte)10, "平板车"),
	CONTAINER((byte)11, "集装箱"),
	THERMAL_CAR((byte)12, "保温车"),
	REFRIGERATED_TRUCK((byte)13, "冷藏车"),
	FRESH_WATER_CAR((byte)14, "活鲜水车"),
	OTHER_CAR((byte)15, "其他"),
	IEVCO((byte)16, "依维柯");
	 
	private Byte code;
	
	private String name;

	private CarTypeEnum(Byte code, String name) {
		this.code = code;
		this.name = name;
	}

	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(Byte code){
		if(code == null) {
			return null;
		}
		CarTypeEnum[] values = CarTypeEnum.values();
		for(CarTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

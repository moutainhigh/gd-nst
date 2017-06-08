package cn.gdeng.nst.enums;

public enum GoodsTypeEnum {
	
	VEGETABLE_FRUIT((byte)101, "蔬菜水果"),
	GRAIN_OIL((byte)102, "干调粮油"),
	AQUATIC((byte)103, "活鲜水产"),
	FOOD_DRINK((byte)104, "食品饮料"),
	ICE_GOODS((byte)105, "冷冻商品"),
	FERTILIZER_SEED((byte)106, "化肥种子"),
	FARM_TOOLS((byte)107, "农资农具"),
	COMMODITY((byte)108, "日用品"),
	BUILDING_MATERIALS((byte)109, "建材设备"),
	OTHER((byte)110, "其他商品");
	
	private Byte code;
	
	private String name;
	
	private GoodsTypeEnum(Byte code, String name){
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
		GoodsTypeEnum[] values = GoodsTypeEnum.values();
		for(GoodsTypeEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

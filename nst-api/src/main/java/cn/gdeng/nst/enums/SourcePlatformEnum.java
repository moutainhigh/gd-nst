package cn.gdeng.nst.enums;


/**
 * 货源平台配送枚举值
 * @author wjguo
 *
 * datetime:2016年12月3日 下午3:48:48
 */
public enum SourcePlatformEnum {
	/**  非平台配送**/
	NOT_PLAT_DIST((byte)0, "非平台配送"),
	/**  平台配送**/
	PLAT_DIST((byte)1, "平台配送");

	private Byte code;
	
	private String name;

	private SourcePlatformEnum(Byte code, String name) {
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
		SourcePlatformEnum[] values = SourcePlatformEnum.values();
		for(SourcePlatformEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return null;
	}
}

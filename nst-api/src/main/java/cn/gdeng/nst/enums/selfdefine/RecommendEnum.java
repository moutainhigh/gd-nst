package cn.gdeng.nst.enums.selfdefine;

import cn.gdeng.nst.enums.TransStatusEnum;

/**
* @author DJB
* @version 创建时间：2017年2月21日 下午5:15:41
* 找货源  recommend  自定义枚举
*/

public enum RecommendEnum {
	
	/**系统推荐 */
	SYS_RECOMMEND("1", "系统推荐"),
	/**空车配货 */
	ALLOCATE_CARGO("2", "空车配货");
	

	private String code;
	
	private String name;

	private RecommendEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getNameByCode(String code){
		TransStatusEnum[] values = TransStatusEnum.values();
		for(TransStatusEnum val : values){
			if(val.getCode().equals(code)){
				return val.getName();
			}
		}
		return "";
	}
	

}

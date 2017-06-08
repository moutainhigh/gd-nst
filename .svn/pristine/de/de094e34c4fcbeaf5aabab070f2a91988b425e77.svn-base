package cn.gdeng.nst.enums.selfdefine;

import cn.gdeng.nst.enums.TransStatusEnum;

/**
* @author DJB
* @version 创建时间：2017年2月21日 下午5:21:51
* 找货源  访问来源  自定义枚举
*/

public enum VisitSourceEnum {
	

	/**车主 */
	SOURCE_DRIVER("2", "车主"),
	/**物流公司 */
	SOURCE_COMPANY("3", "物流公司");
	

	private String code;
	
	private String name;

	private VisitSourceEnum(String code, String name) {
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

package cn.gdeng.nst.util.server.bo;

public class RedisConstant {
	/**
	 * redis key
	 */
	public static enum RedisKey{
		//单个地区
		AREA_AREAID("AREA_"),
		//一级所有地区
		AREA_TOP_ALL("AREA_TOP_ALL"),
		//取一级地区直辖市
		AREA_TOP_DIRECTLYCITY("AREA_TOP_DIRECTLYCITY"),
		//取一级地区自治区
		AREA_TOP_REGION("AREA_TOP_REGION"),
		// 取一级地区省份(不包括港澳)
		AREA_TOP_PROVINCE("AREA_TOP_PROVINCE"),
		//地区子集
		AREA_CHILD_AREAID("AREA_CHILD_"),
		//地区子集树
		AREA_TREE_AREAID("AREA_TREE_"),
		//所有省份和城市
		AREA_ALL_PROVINCE_CITY("AREA_ALL_PROVINCE_CITY"),
		//一级地区（附加isParent字段）
		AREA_PARENT_TREEID("AREA_PARENT_TREE"),
		
		//会员id
		MEMBER_ID("MEMBER_"),
		//会员手机号
		MEMBER_MOBILE("MEMBER_MOBILE_"),
		//会员账号
		MEMBER_ACCOUNT("MEMBER_ACCOUNT_");
		
		public String value;

		RedisKey(String value) {
			this.value = value;
		}
	
	}
	
}

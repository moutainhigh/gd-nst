package cn.gdeng.nst.admin.service.admin;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.entity.nst.AppVersion;

/**
 * App信息接口
 * @author wenwuji
 *
 */
public interface AppVersionService {

	/**
	 * 功能描述:保存App信息
	 * @param appRecord
	 * @return AppRecord对象
	 */
	//public AppRecord persistAppRecord(AppRecord appRecord);
	
	/**
	 * 功能描述:分页查询App信息
	 * @param appRecord
	 * @param page Hn封装分页对象:可以些设置order pagesize等属性
	 * @return AppRecordResult
	 */
	//public AppRecordResult queryAppRecordPage(AppRecord appRecord,QueryParamExt page) throws IllegalArgumentException, IllegalAccessException ;
	
	/**
	 * 功能描述:分类统计App信息(下载用户、活跃用户在Android/IOS数量)
	 * @param servicetype :统计类型:1:商家app 2:会员app
	 * @return AppRecordDTO对象
	 */
	//public AppRecordDTO countAppRecord(String servicetype);
	
	/**
	 * 功能描述:保存AppVersion信息
	 * @param appVersion
	 * @return AppVersion对象
	 */
	public AppVersion persistAppVersion(AppVersion appVersion);
	
	/**
	 * 功能描述:删除AppVersion信息
	 * @param id
	 * @return AppVersion对象
	 */
	public boolean deleteAppVersion(String id);
	
	/**
	 * 功能描述:app产品是否存在该版本号
	 * @param type app类型
	 * @param num 版本号
	 * @return true/false
	 */
	public boolean isExistNum(String platform,String type,String num);
	
	/**
	 * 功能描述:分页AppVersion信息
	 * @param page Hn封装分页对象:可以些设置order pagesize等属性
	 * @return AppVersionResult
	 */
//	public AppVersionResult queryAppVersionPage(QueryParam<Map<String, Object>> page);
	
	/**
	 * 功能描述:取最新的app版本信息
	 * @param type
	 * @return AppVersion对象
	 */
	public AppVersion getLastAppVersion(String type,String platform);
	/**
	 * 修改版本信息，只能修改功能升级描述
	 * @author Administrator 2015年1月27日 上午11:05:58
	 * <p>Title: updateAppVersion </p>
	 * <p>Description: </p>
	 * @param appVersion
	 */
	public void updateAppVersion(AppVersion appVersion);
	/**
	 * 根据ID获得版本信息
	 * @author Administrator 2015年1月27日 上午11:19:17
	 * <p>Title: getAppVersionById </p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public AppVersion getAppVersionById(String id);
	
	public Integer getAppVersionCount(Map<String,Object> map);
	
	public List<AppVersion> getAppVersionList(Map<String,Object> map);
}

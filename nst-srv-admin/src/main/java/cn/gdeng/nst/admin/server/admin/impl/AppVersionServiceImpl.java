package cn.gdeng.nst.admin.server.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.admin.service.admin.AppVersionService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.AppVersion;
import cn.gdeng.nst.util.server.IdGenerateUtil;

/**
 * APP信息统计接口实现类
 *
 */
@Service
public class AppVersionServiceImpl implements AppVersionService{
	@Autowired
	private BaseDao baseDao;
	@Override
	public AppVersion persistAppVersion(AppVersion appVersion) {
		appVersion.setId(IdGenerateUtil.create32UUID());
		if(this.baseDao.execute("AppRecord.persistAppVersion", appVersion) >= 1){
			return appVersion;
		}else{
			return null;
		}
	}

	@Override
	public AppVersion getLastAppVersion(String type,String platform) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", type);
		params.put("platform", platform);
		return (AppVersion) this.baseDao.queryForObject("AppRecord.getLastAppVersion", params, AppVersion.class);
	}

	@Override
	public boolean deleteAppVersion(String id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		if(this.baseDao.execute("AppRecord.deleteAppVersion", params) >= 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isExistNum(String platform,String type,String num) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("num", num);
		params.put("type", type);
		params.put("platform", platform);
		AppVersion appVersion = (AppVersion) baseDao.queryForObject("AppRecord.isExistNum", params, AppVersion.class);
		if(null != appVersion){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void updateAppVersion(AppVersion appVersion) {
		
		baseDao.dynamicMerge(appVersion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AppVersion getAppVersionById(String id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return (AppVersion) this.baseDao.queryForObject("AppRecord.getAppVersion", params, AppVersion.class);
	
	}

	@Override
	public Integer getAppVersionCount(Map<String, Object> map) {
		return (Integer)this.baseDao.queryForObject("AppRecord.queryAppVersionPageCount", map, Integer.class);
	}

	@Override
	public List<AppVersion> getAppVersionList(Map<String, Object> map) {
		return this.baseDao.queryForList("AppRecord.queryAppVersionPage", map);
	}
}

package cn.gdeng.nst.dao.route.support;

import java.util.Map;

import javax.sql.DataSource;

import com.gudeng.framework.dba.route.DatabaseRouteConfig;

/** 简单参数配置数据源的路由。
 * @author wjguo
 * data 2016年7月25日 上午11:57:49 
 *
 */
public class SimpleParamDatabaseRouteConfig implements DatabaseRouteConfig{
	/**默认的参数key
	 * 
	 */
	public static final String DEFAULT_PARAMKEY = "dbResource";
	/**引用的数据源集合
	 * 
	 */
	private Map<String, DataSource> referDataSources;
	/**默认数据源
	 *  
	 */
	private DataSource defaultDataSource;
	
	/**数据库名 */
	public static final String DB_GUDENG = "gudeng";
	public static final String DB_NST = "nst";
	
	@Override
	public DataSource route(Object paramObject) {
		if (this.referDataSources.size() > 0  && paramObject != null ) {
			if (paramObject.getClass().isArray() ||  paramObject instanceof Map) {
				if( paramObject.getClass().isArray()) {
					//数组
					for (Object obj : (Object[])paramObject) {
						if (obj instanceof Map) {
							DataSource dataSource = getDataSource((Map<?, ?>)obj);
							if (dataSource != null) {
								return dataSource;
							}
						}
					}
				} else {
					//集合
					DataSource dataSource = getDataSource((Map<?, ?>)paramObject);
					if (dataSource != null) {
						return dataSource;
					}
				}
				
				
			}
		}
		return defaultDataSource;
	}
	
	/**根据map获取数据源
	 * @param paramMap  参数集合
	 * @return
	 */
	private DataSource getDataSource(Map<?, ?> paramMap) {
		Object dbSrouceFlag =  paramMap.get(DEFAULT_PARAMKEY);
		if (dbSrouceFlag != null) {
			return referDataSources.get(dbSrouceFlag.toString());
		}
		
		return null;
	}

	public void setReferDataSources(Map<String, DataSource> referDataSources) {
		this.referDataSources = referDataSources;
	}

	public void setDefaultDataSource(DataSource defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}
}

package cn.gdeng.nst.api.server.source;

import java.util.Map;

import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 平台配送相关Service
 * @author xiaojun
 */
public interface PlatformService {
	/**
	 * 平台配送关闭货源
	 * @param sourceId
	 */
	public ApiResult<?> goodsClose(Map<String, Object> map);
}

package cn.gdeng.nst.pub.service;

import java.util.Map;

import cn.gdeng.nst.api.vo.pub.AppVersionVO;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * app端版本控制service
 * @author xiaojun
 *
 */
public interface AppVersionService {
	/**
	 * 检查版本号
	 * @param map
	 * @return
	 */
	ApiResult<AppVersionVO> checkAppVesion(Map<String,Object> map) throws BizException;
}

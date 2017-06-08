package cn.gdeng.nst.server.pub.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;

import cn.gdeng.nst.api.vo.pub.AppVersionVO;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.AppVersion;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.pub.service.AppVersionService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * app版本控制实现类
 * 
 * @author xiaojun
 *
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {
	/**
	 * 定义记录日志信息
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BaseDao<?> baseDao;

	@Override
	public ApiResult<AppVersionVO> checkAppVesion(Map<String, Object> paramMap) throws BizException {
		ApiResult<AppVersionVO> result = new ApiResult<>();
		// 获取最后一次app版本号
		AppVersion av = baseDao.queryForObject("AppRecord.getLastAppVersion", paramMap, AppVersion.class);
		String appNum = paramMap.get("num").toString();
		// 对比app端传参数与查出最后一次版本做对比
		if (av != null && !appNum.equals(av.getNum())) {
			logger.info("最新版本:" + JSON.toJSONString(av));
			result = handleReturnVO(result, av);
			return result;
		}
		result = new ApiResult<AppVersionVO>(null, MsgCons.C_30001, MsgCons.M_30001);
		return result;
	}

	/**
	 * 处理版本，返回最新版本信息给app端
	 * 
	 * @param result
	 * @param av
	 * @return
	 */
	private ApiResult<AppVersionVO> handleReturnVO(ApiResult<AppVersionVO> result, AppVersion av) {
		AppVersionVO avvo = new AppVersionVO();
		avvo.setNum(av.getNum());
		avvo.setApkAddress(av.getApkAddress());
		avvo.setNeedEnforce(av.getNeedEnforce());
		avvo.setPublishTime(av.getPublishTime());
		avvo.setRemark(av.getRemark());
		result = new ApiResult<AppVersionVO>(avvo, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}
}

package cn.gdeng.nst.api.server.source;

import cn.gdeng.nst.util.web.api.BizException;

/**货源分配接口
 * @author zhangnf
 * datetime 2016年8月20日 下午3:38:22  
 *
 */
public interface RuleLogisticsService {
	/**
	 * 每天凌晨重置物流公司当天已分配货源数
	 */
	void updateDayCount() throws BizException;

}

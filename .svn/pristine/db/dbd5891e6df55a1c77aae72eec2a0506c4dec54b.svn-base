package cn.gdeng.nst.api.server.ad;

import java.util.Map;

import cn.gdeng.nst.entity.nst.CallstatisticsEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
* @author DJB
* @version 创建时间：2016年8月30日 下午3:51:29
* 类说明
*/


public interface CallstatisticsService {
	
	/**
	 * 
	 * @Description: 添加拨打电话记录
	 * @param callstatisticsEntity
	 * @return
	 * @throws BizException
	 *
	 */
	ApiResult<Boolean> saveCall(CallstatisticsEntity callstatisticsEntity) throws BizException;
	
	
	/**
	 * 
	 * @Description: 添加拨打电话记录  此类专门给 农批商、农商友  因为对方系统只能接收  Map类型的数据
	 * @param callstatisticsEntity
	 * @return
	 * @throws BizException
	 *
	 */
	ApiResult<Map<String, String>> saveCallResultMap(CallstatisticsEntity callstatisticsEntity) throws BizException;

}

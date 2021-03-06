package cn.gdeng.nst.api.server.ad;

import java.util.Map;

import cn.gdeng.nst.api.dto.ad.AdAllDto;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
* @author DJB
* @version 创建时间：2016年8月1日 上午10:49:12
* 广告服务层
*/

public interface AdService {
	

	
	/**
	 * 
	 * @Description: 查询新广告广告，包括跑马灯和横幅广告  （横幅广告默认5条）
	 * @param paraMap
	 * @return
	 *
	 */
	ApiResult<AdAllDto> queryAdAllNew(Map<String,Object> paraMap) throws BizException;
	
	/**
	 * 定时任务：广告自动上架
	 * @throws BizException 
	 */
	void putOnAdvertisement() throws BizException;
}

package cn.gdeng.nst.api.server.source;

import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
* @author DJB
* @version 创建时间：2017年2月15日 上午11:42:02
* 搜索货源服务类接口
*/

public interface SearchGoodsSourceService {
	
	//推荐货源
	ApiResult<ApiPage> queryRecommendGoods(ApiPage page) throws BizException;
	
	//条件搜索货源
	ApiResult<ApiPage> queryGoodsPage(ApiPage page) throws BizException;

}

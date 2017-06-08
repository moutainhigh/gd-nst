package cn.gdeng.nst.api.server.order;

import cn.gdeng.nst.api.dto.order.OrderAgentDTO;
import cn.gdeng.nst.api.vo.order.OrderAgentVo;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 信息(物流代理)订单service
 * 
 * @author huangjianhua  2016年8月1日  上午11:19:31
 * @version 1.0
 */
public interface OrderAgentService {

	/**
	 * 分页查询信息订单
	 * @param page
	 * @return
	 */
	ApiResult<ApiPage> queryOrderAgentPage(ApiPage page)throws Exception;
	
	/**
	 * 查询信息订单详情
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	ApiResult<OrderAgentVo>  queryOrderAgent(OrderAgentDTO dto)throws BizException;
	
}

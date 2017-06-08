package cn.gdeng.nst.api.server.order;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.api.dto.order.OrderInfoDTO;
import cn.gdeng.nst.api.vo.order.OrderInfoDetailVo;
import cn.gdeng.nst.api.vo.order.OrderInfoPageVo;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 运单信息Service
 * 
 * @author huangjianhua  2016年8月1日  上午11:21:00
 * @version 1.0
 */
public interface OrderInfoService {
	
	/**
	 * 分页查询运单信息
	 * @param page
	 * @return
	 */
	ApiResult<ApiPage> queryOrderInfoPage(ApiPage page)throws BizException;
	/**
	 * 运单详情查询
	 * @param page
	 * @return
	 */
	ApiResult<OrderInfoDetailVo> queryOrderInfo(Map<String, Object> map)throws BizException;
	
	/**
	 * 货主确认收货
	 * @param dto
	 * @return
	 */
	ApiResult<Integer> confirmOrderInfo(OrderInfoDTO dto)throws BizException;
	
	/**
	 * 创建运单信息(货主或物流公司接受货源)
	 * @param dto
	 * @return
	 */
	ApiResult<Integer> createOrderInfo(OrderInfoDTO dto)throws BizException;
	
	/**
	 * 拒绝(货主或物流公司拒绝接受货源)
	 * @param dto
	 * @return
	 */
	ApiResult<Integer> refuseOrderInfo(OrderInfoDTO dto) throws BizException;
	
	/**
	 * 查询运单集合
	 * @param map
	 * @return
	 */
	ApiResult<List<OrderInfoPageVo>> queryOrderInfoList(Map<String, Object> map)throws BizException;
	
	/**
	 * 接受订单超时(10分钟)
	 * @return
	 */
	ApiResult<Integer> orderReceiveTimeOut(Map<String, Object> map)throws BizException;
}

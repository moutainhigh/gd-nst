package cn.gdeng.nst.api.server.order;

import java.util.List;
import java.util.Map;

import cn.gdeng.nst.api.vo.order.OrderInfoTransAndSourceExamine;
import cn.gdeng.nst.api.vo.order.OrderInfoTransVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 *  运单-物流信息接口
 * @author wjguo
 *
 * datetime:2016年12月5日 上午11:52:16
 */
public interface OrderInfoTransService {

	/** 预付款支付成功后的保存。
	 * @param orderNo 订单编号
	 * @return 结果为map集合。
	 */
	ApiResult<Map<String, Object>> saveForPrePaymentSucc(String orderNo) throws BizException;
	/** 退掉预付款成功后的操作。
	 * @param orderNo  订单编号
	 * @return 结果为map集合。
	 */
	ApiResult<Map<String, Object>> repealPrePaymentSucc(String orderNo) throws BizException;
	/** 尾款支付成功后的处理。
	 * @param orderNo 订单编号
	 * @return 结果为map集合。
	 */
	ApiResult<Map<String, Object>> payFinalPaymentSuccHandler(String orderNo) throws BizException;
	
	
	/**
	 * 物流信息 加验货信息
	 * @param orderNo
	 * @throws BizException 
	 */
	ApiResult<OrderInfoTransAndSourceExamine> queryOrderInfoTransAndSourceExamine(Map<String, Object>  map)throws BizException;
	
	/**
	 * 物流信息 
	 * @param orderNo
	 * @throws BizException 
	 */
	ApiResult<List<OrderInfoTransVo>>  queryOrderInfoTrans(Map<String, Object>  map)throws BizException;


	
	/** 扫描支付预付款过期的数据并进行处理。
	 * @return 返回map对象，结果包含过期的记录数和订单编号
	 * @throws BizException
	 */
	public ApiResult<Map<String, Object>> scanPrePaymtenOverdueAndHandler() throws BizException;
	
	/** 支付预付款超时处理。
	 * @param orderNos 超时的订单编号id
	 * 
	 * @return 返回map对象，结果包含影响的记录数。
	 * @throws BizException
	 */
	public ApiResult<Map<String, Object>> payPrePaymtenOverdue(String[] orderNos) throws BizException;
}

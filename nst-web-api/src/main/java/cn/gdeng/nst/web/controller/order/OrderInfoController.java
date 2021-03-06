package cn.gdeng.nst.web.controller.order;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.order.OrderInfoDTO;
import cn.gdeng.nst.api.server.order.OrderInfoService;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

/**
 * 货主Controller
 * @author huangjianhua  2017年1月10日  上午11:10:57
 * @version 1.0
 */
@Controller
@RequestMapping("v1/orderInfo")
public class OrderInfoController extends BaseController {
	
	@Reference
	private OrderInfoService orderInfoService;
	
	/**
	 * (接受)新增运单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createOrderInfo")
	@ResponseBody
	public Object createOrderInfo(HttpServletRequest request) throws Exception {
		OrderInfoDTO dto = getDecodeDto(request, OrderInfoDTO.class);
		checkCreateOrderInfoData(dto);
		return orderInfoService.createOrderInfo(dto);
	}
	/**
	 * 拒绝订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refuseOrderInfo")
	@ResponseBody
	public Object refuseOrderInfo(HttpServletRequest request) throws Exception {
		OrderInfoDTO dto = getDecodeDto(request, OrderInfoDTO.class);
		checkCreateOrderInfoData(dto);
		return orderInfoService.refuseOrderInfo(dto);
	}
	
	
	/**
	 *	确认收货
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("confirmOrderInfo")
	@ResponseBody
	public Object confirmOrderInfo(HttpServletRequest request) throws Exception {
		OrderInfoDTO dto = getDecodeDto(request, OrderInfoDTO.class);
		checkCreateOrderInfoData(dto);
		return orderInfoService.confirmOrderInfo(dto);
	}
	
	/**
	 * 查询运单详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOrderInfo")
	@ResponseBody
	public Object queryOrderInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> map= getDecodeMap(request);
		DataCheckUtils.assertIsNonNull("货源ID不能为null", map.get("sourceId"));
		return orderInfoService.queryOrderInfo(map);
	}
	
	/**
	 * 分页查询运单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOrderInfoPage")
	@ResponseBody
	public Object queryOrderInfoPage(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		DataCheckUtils.assertIsNonNull("货主ID不能为null", page.getParaMap().get("shipperMemberId"));
		return orderInfoService.queryOrderInfoPage(page);
	}
	
	/**
	 * 检查数据
	 * @param dto
	 * @throws BizException
	 */
	private void checkCreateOrderInfoData(OrderInfoDTO dto)throws BizException{
		DataCheckUtils.assertIsNonNull("货源ID不能为null", dto.getSourceId());
		DataCheckUtils.assertIsNonNull("预运单ID不能为null", dto.getOrderBeforeId());
	}
}

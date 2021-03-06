package cn.gdeng.nst.web.controller.order;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.server.order.OrderBeforeService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;
/**
 * 
 * @author DJB
 * V2半  车主订单 controller
 *
 */
@Controller
@RequestMapping("v2/orderBefore")
public class OrderBeforeControllerV2 extends BaseController {
	
	@Reference
	private OrderBeforeService orderBeforeService;
	
	
	/**
	 * 分页查询预运单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOrderBeforePage")
	@ResponseBody
	public Object queryOrderBeforePage(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		DataCheckUtils.assertIsNonNull(MsgCons.C_20001,MsgCons.M_20001, page.getParaMap().get("sourceStatus"),page.getParaMap().get("driverMemberId"));
		orderBeforeService.checkoutMember(page.getParaMap().get("driverMemberId").toString());
		return orderBeforeService.queryOrderBeforePageV2(page);
	}

	
	/**
	 * 确认送达
	 */
	
	@RequestMapping("deliveryConfirmation")
	@ResponseBody
	public Object deliveryConfirmation(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);
		DataCheckUtils.assertIsNonNull(MsgCons.C_20001,MsgCons.M_20001, paramMap.get("driverMemberId"), paramMap.get("orderBeforeId"));
		return orderBeforeService.deliveryConfirmation(paramMap);
	}

	

	
	
}

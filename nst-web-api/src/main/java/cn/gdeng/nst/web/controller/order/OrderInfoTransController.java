package cn.gdeng.nst.web.controller.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.server.order.OrderInfoTransService;
import cn.gdeng.nst.api.vo.order.OrderInfoTransAndSourceExamine;
import cn.gdeng.nst.api.vo.order.OrderInfoTransVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;
/**
 * 物流信息Controller
 * 
 * @author Kwang
 */
@Controller
@RequestMapping("OrderInfoTrans")
public class OrderInfoTransController extends BaseController {
	@Reference
	private  OrderInfoTransService orderInfoTransService;
	/**
	 * 物流信息和验证信息
	 * 
	 * @param request 
	 */
	@ResponseBody
	@RequestMapping("queryOrderInfoTransAndSourceExamineByOrderNo")
	public Object queryOrderInfoTransAndSourceExamineByOrderNo(HttpServletRequest request) throws Exception {
		Map<String, Object> map = getDecodeMap(request);  
		ApiResult<OrderInfoTransAndSourceExamine> result= orderInfoTransService.queryOrderInfoTransAndSourceExamine(map);       
        return result; 
	 }
	
	/**
	 * 物流信息
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("queryOrderInfoTransByOrderId")
	public Object queryOrderInfoTrans(HttpServletRequest request) throws Exception {
		Map map = getDecodeMap(request);  
		ApiResult<List<OrderInfoTransVo>> result= orderInfoTransService.queryOrderInfoTrans(map); 
        return result; 
	 }
	
	

}

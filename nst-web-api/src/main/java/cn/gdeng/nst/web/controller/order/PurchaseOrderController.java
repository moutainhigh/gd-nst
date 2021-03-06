package cn.gdeng.nst.web.controller.order;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.server.order.PurchaseOrderService;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;
/**
 * 采购信息
 * @author Kwang
 *2016-12-08
 */
@Controller
@RequestMapping("purchaseOrder")
public class PurchaseOrderController extends BaseController{
	
	@Reference
	private PurchaseOrderService purchaseOrderService;
	/**
	 * 根据货源ID查询采购信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
   	@RequestMapping("queryPurchaseOrderBySourceId")
	@ResponseBody
	public Object queryPurchaseOrderBySourceId(HttpServletRequest request) throws Exception {
   		Map<String, Object> map = getDecodeMap(request);  
        return purchaseOrderService.queryPurchaseOrderBySourceId((String)map.get("sourceId"));
   	}
}

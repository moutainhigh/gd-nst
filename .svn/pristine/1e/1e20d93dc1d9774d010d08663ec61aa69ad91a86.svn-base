package cn.gdeng.nst.server.order.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.order.OrderInfoDTO;
import cn.gdeng.nst.api.server.order.OrderInfoService;
import cn.gdeng.nst.api.vo.order.OrderInfoPageVo;
import cn.gdeng.nst.enums.OperateEnum;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 
 * 货主 7天不操作 自动 完成订单
 * @author huangjianhua  2016年8月12日  下午3:49:03
 * @version 1.0
 */
public class OrderConfirmHandleTaskImpl {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Reference
	private OrderInfoService orderInfoService;
	
	public void execute() throws BizException{  
		logger.info("确认收货OrderConfirmHandleTaskImpl begin");
		Long beginDate = System.currentTimeMillis();
		// 未处理的状态
		Map<String, Object> map = new HashMap<>();
		map.put("orderStatus", 1);
		ApiResult<List<OrderInfoPageVo>> apiResult = orderInfoService.queryOrderInfoList(map);
		if (CollectionUtils.isEmpty(apiResult.getResult())) {
			return;
		}
		for (OrderInfoPageVo vo : apiResult.getResult()) {
			try {
				OrderInfoDTO dto = new OrderInfoDTO();
				dto.setSourceId(vo.getSourceId());
				dto.setId(vo.getId());
				dto.setIsHandleTimeout(OperateEnum.TIMEOUT.getCode());
				dto.setOrderBeforeId(vo.getOrderBeforeId());
				orderInfoService.confirmOrderInfo(dto);
			} catch (Exception e) {
				logger.error("确认收货OrderConfirmHandleTaskImpl error:", e);
			}
		}
		Long endDate = System.currentTimeMillis() - beginDate;
		logger.info("确认收货OrderConfirmHandleTaskImpl end,time consume:{}ms", endDate);
	}
}

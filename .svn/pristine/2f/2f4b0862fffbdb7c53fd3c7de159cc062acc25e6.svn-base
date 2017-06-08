package cn.gdeng.nst.server.order.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.order.OrderBeforeService;

/**
* @author DJB
* @version 创建时间：2016年12月7日 上午10:29:36
* 车主验货超时  3天
*/

public class DriverExamineCargoTimeOutTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Reference
	private OrderBeforeService orderBeforeService;
	/**
	 * 定时
	 */
	public void execute(){  
		logger.info("-----------DriverExamineCargoTimeOutTask begin---------");
		Long beginTime=System.currentTimeMillis();
		try {
			orderBeforeService.examineCargoTimeOut();
		} catch (Exception e) {
			logger.error("DriverExamineCargoTimeOutTask execute failure", e);
		}
		
        Long consumedTime=System.currentTimeMillis() - beginTime;
        logger.info("-----------DriverExamineCargoTimeOutTask end, time consume:{}ms ---------", consumedTime);
    }  
}

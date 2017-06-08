package cn.gdeng.nst.server.source.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.source.RuleLogisticsService;


/** 每天凌晨重置物流公司当天已分配货源数
 * @author zhangnf
 * datetime 2016年8月20日 上午10:12:23  
 *
 */
public class RuleLogisticTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Reference
	private RuleLogisticsService ruleLogisticsService;
	/**
	 * 定时
	 */
	public void execute(){  
		logger.info("-----------RuleLogisticTask begin---------");
		Long beginTime=System.currentTimeMillis();
		
		try {
			ruleLogisticsService.updateDayCount();
		} catch (Exception e) {
			logger.error("RuleLogisticTask execute failure", e);
		}
		
        Long consumedTime=System.currentTimeMillis() - beginTime;
        logger.info("-----------RuleLogisticTask end, time consume:{}ms ---------", consumedTime);
    }  
}

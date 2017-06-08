package cn.gdeng.nst.server.source.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.source.GoodsService;


/** 货源5天后过期的task。
 * @author wjguo
 * datetime 2016年8月17日 上午10:12:23  
 *
 */
public class GoodsOverdueTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Reference
	private GoodsService goodsService;
	/**
	 * 定时
	 */
	public void execute(){  
		logger.info("-----------GoodsOverdueTask begin---------");
		Long beginTime=System.currentTimeMillis();
		
		try {
			goodsService.scanOverdueAndUpdate();
		} catch (Exception e) {
			logger.error("GoodsOverdueTask execute failure", e);
		}
		
        Long consumedTime=System.currentTimeMillis() - beginTime;
        logger.info("-----------GoodsOverdueTask end, time consume:{}ms ---------", consumedTime);
    }  
}

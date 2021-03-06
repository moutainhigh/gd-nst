package cn.gdeng.nst.server.advertisement.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.server.ad.AdService;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 广告自动上架定时任务
 * @author dengjianfeng
 *
 */
public class AdPutOnTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Reference
	private AdService adService;
	
	public void execute() {
		try {
			adService.putOnAdvertisement();
		} catch (BizException e) {
			logger.error("广告自动上架定时任务异常", e);
		}
	}
}

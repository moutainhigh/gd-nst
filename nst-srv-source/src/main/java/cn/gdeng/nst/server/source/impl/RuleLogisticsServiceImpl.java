package cn.gdeng.nst.server.source.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.server.source.RuleLogisticsService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.util.web.api.BizException;

@Service
public class RuleLogisticsServiceImpl implements RuleLogisticsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	
	@Override
	@Transactional
	public void updateDayCount() throws BizException {
		logger.info("-----------RuleLogisticTask begin---------");
		Long beginTime=System.currentTimeMillis();
		int rows = 0;
		try {
			rows = baseDao.execute("SourceAssignHis.updateDayCount", null);
			updateMemberAssginLevel();
		} catch (Exception e) {
			logger.error("RuleLogisticTask execute failure", e);
		}
        Long consumedTime=System.currentTimeMillis() - beginTime;
        logger.info("-----------RuleLogisticTask end, affected rows:{}ms ---------", rows);
        logger.info("-----------RuleLogisticTask end, time consume:{}ms ---------", consumedTime);
	}
	/**
	 * 每天凌晨更新物流公司/车主的分配优先级
	 */
	private void updateMemberAssginLevel(){
		baseDao.execute("Rule.updateMemberAssginLevel", null);
	}
}

package cn.gdeng.nst.server.source.mq.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.api.server.source.GoodsProvidereDubboMQServie;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.server.source.mq.GoodsProvidereMQServie;
import cn.gdeng.nst.util.web.api.BizException;

@Service
public class GoodsProvidereDubboServieMQImpl implements GoodsProvidereDubboMQServie {
	@Resource
	private BaseDao<?> baseDao;
	@Resource
	private GoodsProvidereMQServie goodsProvidereMQServie;
	
	@Override
	public void goodsAssignmentMQ(Integer sourceShipperID) throws BizException {
		//物流公司接单超时
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("sourceShipperID", sourceShipperID);
		baseDao.execute("SourceAssignHis.updateExpire", paramMap);
		//推送至货源分配队列
		goodsProvidereMQServie.goodsAssignmentMQ(sourceShipperID);
	}
	
}

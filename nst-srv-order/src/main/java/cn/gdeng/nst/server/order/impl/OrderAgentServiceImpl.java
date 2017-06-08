package cn.gdeng.nst.server.order.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.api.dto.order.OrderAgentDTO;
import cn.gdeng.nst.api.server.order.OrderAgentService;
import cn.gdeng.nst.api.vo.order.OrderAgentPageVo;
import cn.gdeng.nst.api.vo.order.OrderAgentVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 物流公司实现类ServiceImpl
 * @author huangjianhua  2017年1月10日  上午11:16:53
 * @version 1.0
 */
@Service
public class OrderAgentServiceImpl implements OrderAgentService {
	@Resource
	private BaseDao<?> baseDao;
	@Override
	public ApiResult<ApiPage> queryOrderAgentPage(ApiPage page) throws Exception {
		ApiResult<ApiPage> apiResult=new ApiResult<>();
		int total=baseDao.queryForObject("OrderAgent.getTotal",page.getParaMap(), Integer.class);
		if(total==0){
			return apiResult;
		}
		List<OrderAgentPageVo> pageVoList=baseDao.queryForList("OrderAgent.queryByConditionPage", page.getParaMap(),OrderAgentPageVo.class);
		//将结果封装到分页对象
		page.setRecordList(pageVoList).setRecordCount(total);
		//将分页封装到返回结果
		apiResult.setResult(page);
		return apiResult;
	}
	@Override
	public ApiResult<OrderAgentVo> queryOrderAgent(OrderAgentDTO dto) throws BizException {
		ApiResult<OrderAgentVo> apiResult=new ApiResult<>();
		OrderAgentVo agentVo=baseDao.queryForObject("OrderAgent.queryOrderAgentById", dto,OrderAgentVo.class);
		if(agentVo==null){
			throw new BizException(MsgCons.C_23004, MsgCons.M_23004);
		}
		apiResult.setResult(agentVo);
		return apiResult;
	}

}

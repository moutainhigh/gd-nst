package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.admin.service.admin.RuleOnoffManageService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
/**
 * 线路订阅开关
 * @author Administrator
 *
 */
@Service
public class RuleOnoffManageServiceImpl implements RuleOnoffManageService {

	
	@Resource
	private BaseDao<?> baseDao;
	
	@Override
	public ApiResult<Integer> updateRuleOnoffById(Map<String, Object> map)
			throws BizException {
		ApiResult<Integer> result = new ApiResult<Integer>();
		try{
			Integer re=(Integer)baseDao.execute("ruleOnoff.updateRuleOnoffAddr", map);
			result.setResult(re);
			return result;
		}catch(Exception e){	
			System.out.println(e.getMessage());
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

}

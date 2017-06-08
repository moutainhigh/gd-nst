package cn.gdeng.nst.admin.server.admin.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.RuleLineDTO;
import cn.gdeng.nst.admin.service.admin.RuleLineService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 物流公司订阅线路ServerImpl 
 * @author huangjianhua
 * @Date:2017年1月4日下午3:02:58
 */
@Service
public class RuleLineServiceImpl implements RuleLineService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BaseDao<?> baseDao;
	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>();
		try{
			int total = baseDao.queryForObject("RuleLine.queryByConditionPageCount", paramMap, Integer.class);
			List<RuleLineDTO> list = baseDao.queryForList("RuleLine.queryByConditionPage", paramMap, RuleLineDTO.class);
			AdminPageDTO page = new AdminPageDTO(total,list);
			apiResult.setResult(page);
			return apiResult;
		}catch(Exception e){
			logger.error("物流公司查询订阅线路异常:{}", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		ApiResult<Integer> apiResult = new ApiResult<Integer>();
		int total = baseDao.queryForObject("RuleLine.queryByConditionPageCount", paramMap, Integer.class);
		return apiResult.setResult(total);
	}

}

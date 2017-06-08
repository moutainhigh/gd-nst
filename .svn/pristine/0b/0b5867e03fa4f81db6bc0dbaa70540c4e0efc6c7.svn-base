package cn.gdeng.nst.admin.server.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.RuleInfoDTO;
import cn.gdeng.nst.admin.service.admin.RuleInfoService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.RuleInfoEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 分配规则信息接口实现
 * @author Administrator
 *
 */
@Service
public class RuleInfoServiceImpl implements RuleInfoService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BaseDao<?> baseDao;

	@Override
	public ApiResult<AdminPageDTO> pageQuery(Map<String, Object> map) {
		ApiResult<AdminPageDTO> result = new ApiResult<AdminPageDTO>();
		try{
			int total = baseDao.queryForObject("ruleInfo.queryListByPageCount", map, Integer.class);
			List<RuleInfoDTO> list = baseDao.queryForList("ruleInfo.queryListByPage", map, RuleInfoDTO.class);
			AdminPageDTO page = new AdminPageDTO(total,list);
			result.setResult(page);
			return result;
		}catch(Exception e){
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Long> saveAdd(RuleInfoEntity entity) {
		entity.setIsDeleted((byte)0);
		Long id = baseDao.persist(entity, Long.class);
		ApiResult<Long> apiResult = new ApiResult<Long>(id, MsgCons.C_10000, MsgCons.M_10000);
		return apiResult;
	}

	@Override
	public ApiResult<RuleInfoDTO> getById(Integer id) {
		ApiResult<RuleInfoDTO> result = new ApiResult<RuleInfoDTO>();
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			RuleInfoDTO dto = baseDao.queryForObject("ruleInfo.getById", paramMap, RuleInfoDTO.class);
			return result.setResult(dto);
		}catch(Exception e){
			logger.error("新增规则信息异常", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> update(Map<String, Object> paramMap) {
		int result = baseDao.execute("ruleInfo.update", paramMap);
		return new ApiResult<Integer>(result, MsgCons.C_10000, MsgCons.M_10000);
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> map) {
		int total = baseDao.queryForObject("ruleInfo.queryListByPageCount", map, Integer.class);
		return new ApiResult<Integer>().setResult(total);
	}

	@Override
	public ApiResult<List<RuleInfoDTO>> queryList(Map<String, Object> map) {
		List<RuleInfoDTO> list = baseDao.queryForList("ruleInfo.queryListByPage", map, RuleInfoDTO.class);
		return new ApiResult<List<RuleInfoDTO>>().setResult(list);
	}

	@Override
	public ApiResult<Integer> validateRuleName(Map<String, Object> map) {
		int total = baseDao.queryForObject("ruleInfo.queryListByRuleName", map, Integer.class);
		return new ApiResult<Integer>().setResult(total);
	}

}

package cn.gdeng.nst.admin.server.admin.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberLineDTO;
import cn.gdeng.nst.admin.service.admin.MemberLineService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 会员长跑线路接口实现
 * @author wujiang
 *
 */
@Service
public class MemberLineServiceImpl implements MemberLineService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BaseDao<?> baseDao;

	/**
	 * 分页查询线路
	 */
	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>();
		try{
			int total = baseDao.queryForObject("MemberLine.queryByConditionPageCount", paramMap, Integer.class);
			List<MemberLineDTO> list = baseDao.queryForList("MemberLine.queryByConditionPage", paramMap, MemberLineDTO.class);
			AdminPageDTO page = new AdminPageDTO(total,list);
			apiResult.setResult(page);
			return apiResult;
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<MemberLineDTO> showDetail(Map<String, Object> map) {
		ApiResult<MemberLineDTO> result = new ApiResult<MemberLineDTO>();
		try{
			result.setResult(baseDao.queryForObject("MemberLine.showDetail", map, MemberLineDTO.class));
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		ApiResult<Integer> apiResult = new ApiResult<Integer>();
		int total = baseDao.queryForObject("MemberLine.queryByConditionPageCount", paramMap, Integer.class);
		return apiResult.setResult(total);
	}

	@Override
	public ApiResult<List<MemberLineDTO>> queryList(Map<String, Object> paramMap) {
		List<MemberLineDTO> list = baseDao.queryForList("MemberLine.queryByConditionPage", paramMap, MemberLineDTO.class);
		return new ApiResult<List<MemberLineDTO>>().setResult(list);
	}

}

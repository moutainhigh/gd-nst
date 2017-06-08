package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.CallstatisticsDTO;
import cn.gdeng.nst.admin.service.admin.CallstatisticsService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;



/**
 *功能描述：拨打电话统计
 */
@Service
public class CallstatisticsImpl implements CallstatisticsService{
	
	@Autowired
	private BaseDao<?>  baseDao;


	@Override
	public ApiResult<AdminPageDTO> pageQuery(Map<String, Object> map){
		int total = baseDao.queryForObject("call.getTotal", map, Integer.class);
		List<CallstatisticsDTO> list = null;
		if(total > 0){
			list = baseDao.queryForList("call.pageQuery", map, CallstatisticsDTO.class);
		}else{
			list = Collections.emptyList();
		}
		AdminPageDTO page = new AdminPageDTO(total, list);
		return new ApiResult<AdminPageDTO>().setResult(page);
	}


	@Override
	public ApiResult<Integer> getTotal(Map<String, Object> map) {
		ApiResult<Integer> result = new ApiResult<Integer>();
		int total = baseDao.queryForObject("call.getTotal", map, Integer.class);
		return result.setResult(total);
	}


	@Override
	public ApiResult<List<CallstatisticsDTO>> queryList(Map<String, Object> map) {
		ApiResult<List<CallstatisticsDTO>> result = new ApiResult<List<CallstatisticsDTO>>();
		List<CallstatisticsDTO> list = baseDao.queryForList("call.pageQuery", map, CallstatisticsDTO.class);
		return result.setResult(list);
	}
	
}

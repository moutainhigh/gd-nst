package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdminSourceAssignHisDTO;
import cn.gdeng.nst.admin.service.admin.SourceAssignHisService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.util.web.api.ApiResult;

@Service
public class SourceAssignHisServiceImpl implements SourceAssignHisService{
	
	@Autowired
	private BaseDao<?> baseDao;
	
	@Override
	public ApiResult<List<AdminSourceAssignHisDTO>> queryList(Map<String, Object> paramMap) {
		List<AdminSourceAssignHisDTO> list = baseDao.queryForList("SourceAssignHis.queryList", paramMap, AdminSourceAssignHisDTO.class);
		return new ApiResult<List<AdminSourceAssignHisDTO>>().setResult(list);
	}

	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("SourceAssignHis.countTotal", paramMap, Integer.class);
		
		List<AdminSourceAssignHisDTO> list = null;
		if (total > 0) {
			list = baseDao.queryForList("SourceAssignHis.queryListByPage", paramMap, AdminSourceAssignHisDTO.class);
		} else {
			list = Collections.emptyList();
		}
		
		AdminPageDTO pageDTO = new AdminPageDTO(total, list);
		return new ApiResult<AdminPageDTO>(pageDTO);
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("SourceAssignHis.countTotal", paramMap, Integer.class);
		return new ApiResult<Integer>(total);
	}

	@Override
	public ApiResult<List<AdminSourceAssignHisDTO>> queryListByPage(Map<String, Object> paramMap) {
		List<AdminSourceAssignHisDTO> list = baseDao.queryForList("SourceAssignHis.queryListByPage", paramMap, AdminSourceAssignHisDTO.class);
		return new ApiResult<List<AdminSourceAssignHisDTO>>(list);
	}

}

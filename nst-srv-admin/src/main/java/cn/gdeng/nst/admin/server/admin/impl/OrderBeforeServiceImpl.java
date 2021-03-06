package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminOrderBeforeDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.OrderBeforeService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OrderBeforeServiceImpl implements OrderBeforeService{
	
	@Autowired
	private BaseDao<?> baseDao;
	
	@Override
	public ApiResult<List<AdminOrderBeforeDTO>> queryList(Map<String, Object> paramMap) {
		List<AdminOrderBeforeDTO> list = baseDao.queryForList("OrderBefore.queryList", paramMap, AdminOrderBeforeDTO.class);
		return new ApiResult<List<AdminOrderBeforeDTO>>().setResult(list);
	}

	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("OrderBefore.countTotal", paramMap, Integer.class);
		
		List<AdminOrderBeforeDTO> list = null;
		if(total > 0){
			list = baseDao.queryForList("OrderBefore.queryPage", paramMap, AdminOrderBeforeDTO.class);
		}else{
			list = Collections.emptyList();
		}
		
		AdminPageDTO page = new AdminPageDTO(total, list);
		return new ApiResult<AdminPageDTO>(page, MsgCons.C_10000, MsgCons.M_10000);
	}

	@Override
	public ApiResult<Integer> countTotal(Map<String, Object> paramMap) {
		int total = baseDao.queryForObject("OrderBefore.countTotal", paramMap, Integer.class);
		return new ApiResult<Integer>(total, MsgCons.C_10000, MsgCons.M_10000);
	}

	@Override
	public ApiResult<List<AdminOrderBeforeDTO>> queryListByPage(Map<String, Object> paramMap) {
		List<AdminOrderBeforeDTO> list = baseDao.queryForList("OrderBefore.queryPage", paramMap, AdminOrderBeforeDTO.class);
		return new ApiResult<List<AdminOrderBeforeDTO>>().setResult(list);
	}

}

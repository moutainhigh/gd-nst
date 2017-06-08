package cn.gdeng.nst.admin.server.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.AreaManageService;
import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.bo.CacheBo;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;
@Service
public class AreaManageServiceImpl implements AreaManageService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	
	@Resource
	private CacheBo cacheBo;
	
	@Override
	public ApiResult<AdminPageDTO> queryTopArea() {
		ApiResult<AdminPageDTO> result = new ApiResult<AdminPageDTO>();
		try{
			List<AreaDTO> list = baseDao.queryForList("Area.listTopArea",null,AreaDTO.class);
			AdminPageDTO page = new AdminPageDTO(99999999,list);
			result.setResult(page);
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	
	@Override
	public ApiResult<AdminPageDTO> listChildArea(String id) {
		ApiResult<AdminPageDTO> result = new ApiResult<AdminPageDTO>();
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("father", id);
			List<AreaDTO> list = baseDao.queryForList("Area.listChildArea",map,AreaDTO.class);
			AdminPageDTO page = new AdminPageDTO(99999999,list);
			result.setResult(page);
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<List<AreaDTO>> queryTopList() {
		List<AreaDTO> list = cacheBo.listTopArea(baseDao);
		return new ApiResult<List<AreaDTO>>().setResult(list);
	}

	@Override
	public ApiResult<List<AreaDTO>> queryChildList(String parentId) {
		List<AreaDTO> list = cacheBo.listChildArea(parentId, baseDao);
		return new ApiResult<List<AreaDTO>>().setResult(list);
	}


}

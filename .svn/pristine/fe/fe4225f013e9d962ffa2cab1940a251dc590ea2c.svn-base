package cn.gdeng.nst.server.source.impl;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.api.server.source.GoodsLogService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.SourceLogEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

@Service
public class GoodsLogServiceImpl implements GoodsLogService {
	@Resource
	private BaseDao<?> baseDao;
	
	@Override
	@Transactional
	public ApiResult<Integer> save(SourceLogEntity entity) throws BizException {
		Integer id = baseDao.persist(entity, Integer.class);
		return new ApiResult<Integer>().setResult(id);
	}

	@Override
	public ApiResult<Integer> simpleSave(Integer sourceId, String desc, String createUserId) throws BizException {
		SourceLogEntity entity = new SourceLogEntity();
		entity.setSourceId(sourceId);
		entity.setDescription(desc);
		entity.setCreateUserId(createUserId);
		return save(entity);
	}



}

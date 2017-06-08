package cn.gdeng.nst.server.pub.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberDeleteEntity;
import cn.gdeng.nst.pub.dto.MemberDeleteDTO;
import cn.gdeng.nst.pub.service.MemberDeleteService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
 * 会员删除汇总实现
 * 
 * @author xiaojun
 *
 */
@Service
public class MemberDeleteServiceImpl implements MemberDeleteService {

	@Autowired
	private BaseDao<?> baseDao;

	@Override
	public ApiResult<Integer> delete(MemberDeleteDTO dto) throws BizException {
		ApiResult<Integer> result = new ApiResult<>();
		// 组装 entity
		MemberDeleteEntity entity = handelEntity(dto);
		// 插入
		baseDao.persist(entity, Long.class);
		return result;
	}

	/**
	 * 根据dto组装entity
	 * 
	 * @param dto
	 * @return
	 */
	private MemberDeleteEntity handelEntity(MemberDeleteDTO dto) {
		MemberDeleteEntity entity = new MemberDeleteEntity();
		entity.setMemberId(dto.getMemberId());
		entity.setBusinessId(dto.getBusinessId());
		entity.setAppType(dto.getAppType());
		entity.setDeleteType(dto.getDeleteType());
		entity.setIsDeleted(1);
		entity.setCreateUserId(dto.getMemberId().toString());
		entity.setCreateTime(new Date());
		return entity;
	}

}

package cn.gdeng.nst.server.member.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.gdeng.nst.api.server.member.MemberLogisticDriverApiService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

/**
 * 物流公司->个人中心->司机管理api Service 实现类
 * @author Administrator
 *
 */
@Service
public class MemberLogisticDriverApiServiceImpl implements MemberLogisticDriverApiService {
	@Resource
    private BaseDao<?>   baseDao;

	@Override
	public ApiResult<ApiPage> queryPage(ApiPage apiPage) throws BizException{
        long total = baseDao.queryForObject("MemberLogisticDriverApi.countByLogisticMemberId",apiPage.getParaMap() ,Long.class);
        if(total > 0){
	          List<Map<String,Object>> list = baseDao.queryForList("MemberLogisticDriverApi.findByLogisticMemberId", apiPage.getParaMap());
	          apiPage.setRecordList(list).setRecordCount(total);
        }
        ApiResult<ApiPage> apiResult = new ApiResult<ApiPage>(apiPage,MsgCons.C_10000,MsgCons.M_10000);
		return apiResult;
	}

	@Override
	@Transactional
	public ApiResult<Integer> save(Map<String, Object> map) throws BizException{
	    Integer total =	baseDao.execute("MemberLogisticDriverApi.saveDriver", map);
	    ApiResult<Integer> apiResult = new ApiResult<Integer>(total,MsgCons.C_10000,MsgCons.M_10000);
		return apiResult;
	}

	@Override
	@Transactional
	public ApiResult<Integer> update(Map<String, Object> map) throws BizException{
		Integer total =	baseDao.execute("MemberLogisticDriverApi.updateDriver", map);
		ApiResult<Integer> apiResult = new ApiResult<Integer>(total,MsgCons.C_10000,MsgCons.M_10000);
		return apiResult;
	}

	@Override
	public ApiResult<ApiPage> queryPageByCar(ApiPage apiPage) throws BizException{
		long total = baseDao.queryForObject("MemberLogisticDriverApi.countByLogisticMemberId",apiPage.getParaMap(),Long.class);
        if(total > 0){
	          List<Map<String,Object>> list = baseDao.queryForList("MemberLogisticDriverApi.findByCar", apiPage.getParaMap());
	          apiPage.setRecordList(list).setRecordCount(total);
        }
        ApiResult<ApiPage> apiResult = new ApiResult<ApiPage>(apiPage,MsgCons.C_10000,MsgCons.M_10000);
		return apiResult;
	}

	@Override
	public ApiResult<Integer> checkDriverOrder(Map<String, Object> map) throws BizException{
		Integer total =	baseDao.queryForObject("MemberLogisticDriverApi.checkDriverOrder", map,Integer.class);
		ApiResult<Integer> apiResult = new ApiResult<Integer>(total,MsgCons.C_10000,MsgCons.M_10000);
		return apiResult;
	}

	@Override
	public ApiResult<Integer> checkDriverExist(Map<String, Object> map) throws BizException{
		Integer total =	baseDao.queryForObject("MemberLogisticDriverApi.checkDriverExist", map,Integer.class);
		ApiResult<Integer> apiResult = new ApiResult<Integer>(total,MsgCons.C_10000,MsgCons.M_10000);
		return apiResult;
	}	

}

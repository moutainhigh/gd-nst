package cn.gdeng.nst.server.member.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.gdeng.nst.api.dto.member.MemberLineApiDTO;
import cn.gdeng.nst.api.server.member.MemberLineApiService;
import cn.gdeng.nst.api.vo.member.MemberLinePageVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberLineEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;
/**
 * 用户常跑线路
 * @author kwang
 *
 */
@Service
public class MemberLineApiServiceImpl implements MemberLineApiService {
	
	@Resource
	private BaseDao<?> baseDao;
	
	/**
	 * 查询常跑线路列表
	 * @param ApiPage
	 */
	@Override
	public ApiResult<ApiPage>  queryPage(ApiPage page)
			throws BizException {
		ApiResult<ApiPage> apiResult=new ApiResult<>();
		List<MemberLinePageVo> pageVoList=new ArrayList<MemberLinePageVo>();
		int total=baseDao.queryForObject("MemberLineApi.getTotal",page.getParaMap(), Integer.class);
		if(total==0){
			page.setRecordList(pageVoList).setRecordCount(total);
			apiResult.setResult(page);
			return apiResult;
		}
	    pageVoList=baseDao.queryForList("MemberLineApi.queryPage", page.getParaMap(),MemberLinePageVo.class);
		page.setRecordList(pageVoList).setRecordCount(total);
		apiResult.setResult(page);
		return apiResult;
	}

    /**
     * 删除线路（逻辑删除）
     * @param  id
     * @return 影响条数
     */
	@Override
    @Transactional
	public ApiResult<Integer> deleteMemberLineById(MemberLineApiDTO memberLineApiDTO) throws BizException {
		if(null==memberLineApiDTO.getId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		if(null==memberLineApiDTO.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		int re=baseDao.execute("MemberLineApi.deleteMemberLineById",memberLineApiDTO);
		 ApiResult<Integer> apiResult = new ApiResult<Integer>();
		 apiResult.setResult(re);
		 return apiResult;
	}
	/**
	 * 添加常跑线路
	 * @param MemberLineEntity
	 */
	@Override
    @Transactional
	public ApiResult<Long> saveMemberLine(MemberLineEntity memberLineEntity)
			throws BizException {
		if(null==memberLineEntity.getE_cityId()||null==memberLineEntity.getS_cityId()
				||null==memberLineEntity.getE_provinceId()||null==memberLineEntity.getS_provinceId()
				||null==memberLineEntity.getS_detail()||null==memberLineEntity.getE_detail()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		int carTotal=baseDao.queryForObject("MemberCarApi.getTotal",memberLineEntity, Integer.class);
		if(carTotal<1){
			throw new BizException(MsgCons.C_22024, MsgCons.M_22024);
		}
		int total=baseDao.queryForObject("MemberLineApi.getTotal",memberLineEntity, Integer.class);
		if(total>=10){
			throw new BizException(MsgCons.C_22023, MsgCons.M_22023);
		}
		int lineTotal=baseDao.queryForObject("MemberLineApi.getLineTotal",memberLineEntity, Integer.class);
		if(lineTotal>0){
			throw new BizException(MsgCons.C_22027, MsgCons.M_22027);	
		}
		if(null==memberLineEntity.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		// LineType  1 干线 2 同城
		if(memberLineEntity.getE_cityId().equals(memberLineEntity.getS_cityId())){
			memberLineEntity.setLineType(2);	
		}else {
			memberLineEntity.setLineType(1);	
		}
		long re=baseDao.persist(memberLineEntity, Long.class);
		ApiResult<Long> apiResult = new ApiResult<Long>();
		apiResult.setResult(re);
	    return apiResult;
	}
	
	
	

}

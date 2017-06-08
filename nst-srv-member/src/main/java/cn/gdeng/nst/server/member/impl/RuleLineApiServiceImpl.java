package cn.gdeng.nst.server.member.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.gdeng.nst.api.dto.member.RuleLineApiDTO;
import cn.gdeng.nst.api.server.member.RuleLineApiService;
import cn.gdeng.nst.api.vo.member.RuleLineVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.RuleLineEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 订阅线路
 * @author kwang
 *
 */
@Service
public class RuleLineApiServiceImpl implements RuleLineApiService {

	@Resource
    private BaseDao<?> baseDao;
	/**
	 * 线路列表
	 * @param RuleLineApiDTO
	 */
	@Override
	public ApiResult<List<RuleLineVo>> ruleLineList(RuleLineApiDTO ruleLineApiDTO) throws BizException {
		ApiResult<List<RuleLineVo>> apiResult=new ApiResult<>();
		if(null==ruleLineApiDTO.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		List<RuleLineVo> list=baseDao.queryForList("RuleLineApi.ruleLineList", ruleLineApiDTO, RuleLineVo.class);
		apiResult.setResult(list);
		return apiResult;
	}
    /**
     * 逻辑删除
     * @return 影响条数
     */
	@Override
	public ApiResult<Integer> deleteRuleLineById(RuleLineApiDTO ruleLineApiDTO)
			throws BizException {
		ApiResult<Integer> apiResult=new ApiResult<Integer>();
		if(null==ruleLineApiDTO.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		if(null==ruleLineApiDTO.getId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		Integer re=baseDao.execute("RuleLineApi.deleteRuleLineById", ruleLineApiDTO);
		apiResult.setResult(re);
		return apiResult;
	}
	/**
	 * 添加订阅线路
	 * @param RuleLineEntity
	 */
	@Override
	public ApiResult<Long> saveRuleLine(RuleLineEntity ruleLineEntity)
			throws BizException {
		if(null==ruleLineEntity.getMemberId()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
/*		if(null==ruleLineEntity.getE_cityId()||null==ruleLineEntity.getS_cityId()
				||null==ruleLineEntity.getE_provinceId()||null==ruleLineEntity.getS_provinceId()
				||null==ruleLineEntity.getS_detail()||null==ruleLineEntity.getE_detail()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}*/
		if(null==ruleLineEntity.getS_cityId() || (ruleLineEntity.getS_cityId() != null && ruleLineEntity.getS_cityId()==0)
				|| null==ruleLineEntity.getS_provinceId() || (ruleLineEntity.getS_provinceId() != null && ruleLineEntity.getS_provinceId()==0)
				|| null==ruleLineEntity.getS_detail()){
			throw new BizException(MsgCons.C_20001, MsgCons.M_20001);
		}
		ApiResult<Long> apiResult=null;
		int lineTotal=baseDao.queryForObject("RuleLineApi.getLineTotal",ruleLineEntity, Integer.class);
		//相同的线路是否存在
		if(lineTotal>0){
			throw new BizException(MsgCons.C_22027, MsgCons.M_22027);	
		}
		//用户已有线路
		int total=baseDao.queryForObject("RuleLineApi.ruleLineListCount",ruleLineEntity, Integer.class);
		if(total>=5){
			throw new BizException(MsgCons.C_22021, MsgCons.M_22021);
		}else{
			if(ruleLineEntity.getE_cityId() != null &&ruleLineEntity.getE_cityId().equals(ruleLineEntity.getS_cityId())){
				ruleLineEntity.setLineType(2);	
			}else {
				ruleLineEntity.setLineType(1);	
			}
		Long id=baseDao.persist(ruleLineEntity, Long.class);
		apiResult=	new ApiResult<Long>();
		apiResult.setResult(id);
		return apiResult;
	}
	}
}

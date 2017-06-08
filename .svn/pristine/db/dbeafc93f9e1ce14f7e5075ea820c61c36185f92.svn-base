package cn.gdeng.nst.admin.server.admin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberInfoDTO;
import cn.gdeng.nst.admin.dto.admin.RuleOnoffDTO;
import cn.gdeng.nst.admin.dto.admin.SourceAssignHisDTO;
import cn.gdeng.nst.admin.service.admin.MemberInfoManageService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.RuleLogisticAssignEntity;
import cn.gdeng.nst.entity.nst.RuleOnoffEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.InterfaceUtil;
import cn.gdeng.nst.util.server.JacksonUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.ObjectResult;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

@Service
public class MemberInfoManageServiceImpl implements MemberInfoManageService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	@Autowired
	private InterfaceUtil interfaceUtil;
	
	@Override
	public List<MemberInfoDTO> getByConditionPage(Map<String, Object> map)
			throws Exception {
		return baseDao.queryForList("MemberInfo.queryListByPage",map,MemberInfoDTO.class);
	}

	@Override
	public int getByConditionCount(Map<String, Object> map) throws Exception {
		return (int)baseDao.queryForObject("MemberInfo.countTotal",map,Integer.class);
	}

	@Override
	public ApiResult<MemberInfoDTO> getMemberInfoDetail(Map<String, Object> map){
		ApiResult<MemberInfoDTO> result = new ApiResult<MemberInfoDTO>();
		try{
			result.setResult(baseDao.queryForObject("MemberInfo.queryMemberInfoDetail", map, MemberInfoDTO.class));
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public List<SourceAssignHisDTO> getSourceAssignHisPage(
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> map) {
		ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>();
		try{
			int total = baseDao.queryForObject("MemberInfo.countTotal",map,Integer.class);
			List<MemberInfoDTO> list = baseDao.queryForList("MemberInfo.queryListByPage",map,MemberInfoDTO.class);
			AdminPageDTO page = new AdminPageDTO(total,list);
			apiResult.setResult(page);
			return apiResult;
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> updateUserNameAndMobile(Map<String, Object> paramMap)
			{
		ApiResult<Integer> apiResult = new ApiResult<Integer>();
		try{
		    String r =interfaceUtil.updateMemberInfo(des3Map(paramMap));
		    Integer reCode=pubMethod(r).getCode();
		if(reCode==MsgCons.C_10000){
			return apiResult.setResult(0);
		}else{
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);	
			}
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	@Override
	public ApiResult<Integer> resetStatusById(Map<String, Object> paramMap) {
		ApiResult<Integer> apiResult = new ApiResult<Integer>();
		try{
		String r =interfaceUtil.updateStatus(des3Map(paramMap));
		Integer reCode=pubMethod(r).getCode();
		if(reCode==MsgCons.C_10000){
			return apiResult.setResult(0);
		}else{
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);	
			}
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> resetPwdById(Map<String, Object> paramMap) {
		ApiResult<Integer> apiResult = new ApiResult<Integer>();
		try{
		String r =interfaceUtil.updatePassword(des3Map(paramMap));
		Integer reCode=pubMethod(r).getCode();
		if(reCode==MsgCons.C_10000){
			return apiResult.setResult(0);
		}else{
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);	
			}
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	

	@Override
	public ApiResult<RuleOnoffDTO> getMemberRuleOnoffDTODetail(
			Map<String, Object> map) {
		ApiResult<RuleOnoffDTO> result = new ApiResult<RuleOnoffDTO>();
		try{
			result.setResult(baseDao.queryForObject("ruleOnoff.queryRuleOnoffStatusById", map, RuleOnoffDTO.class));
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	
	
	/**
	 * 公共处理方法 参数值 传入调用接口 返回字符串
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private ApiResult<?> pubMethod(String str) throws Exception {
		// 农速通api返回结果类
		ApiResult<?> apiResult = new ApiResult<>();
		// 谷登api返回结果类
		ObjectResult result = null;
		// 将返回结果解密
		String resultStr = Des3.decode(str);
		// 将解密后的结果 装换result
		result = JacksonUtil.str2Obj(resultStr, ObjectResult.class);
		// 处理返回结果
		if (result.getStatusCode() == 0) {
			apiResult.setCodeMsg(MsgCons.C_10000, result.getMsg());
		} else {
			apiResult.withError(MsgCons.C_20000, result.getMsg());
		}
		return apiResult;
	}

	
   public  Map<String, Object> des3Map(Map<String, Object> map){
	   map.put("memberapi_type", "admin");
		   String json = GSONUtils.getGson().toJson(map);
			Map<String, Object> temp = new HashMap<String, Object>();
			try {
				temp.put("param", Des3.encode(json));
			} catch (Exception e) {
				return map;
			}
	   return temp;
   }

@Override
@Transactional
public ApiResult<Integer> updateNstMemberInfoAndRuleOnoffAndAssign (
		Map<String, Object> paraMap, String province, String city, String area)throws Exception{
	ApiResult<Integer> apiResult = new ApiResult<Integer>();
	
	//不验证区域
	if(null!=province&&!"".equals(province)&&null!=city&&!"".equals(city)){
	paraMap.put("detail", province+"/"+city+"/"+area);
	Integer CountRe=(Integer)baseDao.queryForObject("ruleOnoff.ruleOnoffCount", paraMap,Integer.class);
	if(CountRe>0){
      baseDao.execute("ruleOnoff.updateRuleOnoffAddr", paraMap);
	}else{
		RuleOnoffEntity  rEntity=new RuleOnoffEntity();
		rEntity.setMemberId(Integer.parseInt((String)paraMap.get("memberId")));
		rEntity.setProvinceId(Integer.parseInt((String)paraMap.get("provinceId")));
		rEntity.setCityId(Integer.parseInt((String)paraMap.get("cityId")));
		rEntity.setAreaId(Integer.parseInt((String)paraMap.get("areaId")));
		rEntity.setDetail((String)paraMap.get("detail"));
		rEntity.setCreateTime(new Date());
		baseDao.persist(rEntity, Long.class);
	}
	}
	//&&"1".equals((String)paraMap.get("appType"))
	if(null!=paraMap.get("appoint")){
    baseDao.execute("MemberInfo.updateAppoint", paraMap);
	Integer CountRla=(Integer)baseDao.queryForObject("ruleLogisticAssign.ruleLogisticAssignCount", paraMap,Integer.class);
	String appointStr=(String)paraMap.get("appoint");
	int appoint=Integer.parseInt(appointStr);
	if(appoint==0&&CountRla==0){//绑定  是
			RuleLogisticAssignEntity rlaEntity=new RuleLogisticAssignEntity();
			rlaEntity.setMemberIdLogistic(Integer.parseInt((String)paraMap.get("memberIdLogistic")));
			rlaEntity.setMemberIdShipper(Integer.parseInt((String)paraMap.get("memberId")));
			rlaEntity.setMemberType(Integer.parseInt((String)paraMap.get("assignUserType")));
			baseDao.persist(rlaEntity, Long.class);
	}
	if(appoint==1&&CountRla==1){//绑定  否
		baseDao.execute("ruleLogisticAssign.deleteRuleLogisticAssign", paraMap);
		baseDao.execute("MemberInfo.updateAppoint", paraMap);
	}}
	return apiResult.setResult(1);
}

@Override
public ApiResult<Integer> queryMemberInfoCount(Map<String, Object> map) {
	ApiResult<Integer> result = new ApiResult<Integer>();
	int total = baseDao.queryForObject("MemberInfo.countTotal",map,Integer.class);
	result.setResult(total);
	return result;
}


 
}

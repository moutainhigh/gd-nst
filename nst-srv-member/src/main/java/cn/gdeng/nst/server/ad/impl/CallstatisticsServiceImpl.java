package cn.gdeng.nst.server.ad.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.api.server.ad.CallstatisticsService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.CallstatisticsEntity;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

/**
* @author DJB
* @version 创建时间：2016年8月30日 下午3:52:38
* 类说明
*/

@Service
public class CallstatisticsServiceImpl implements CallstatisticsService {

	
	@Resource
	private BaseDao<?> baseDao;
	/**
	 * 
	 * @Description:  添加打电话记录
	 * @param callstatisticsEntity
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<Boolean> saveCall(CallstatisticsEntity callstatisticsEntity) throws BizException {
		ApiResult<Boolean> result = new ApiResult<Boolean>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberId", callstatisticsEntity.getMemberid());
		//查询主叫用户信息
		MemberInfoEntity memberInfoEntity=baseDao.queryForObject("MemberInfo.findMemberByMobileOrId", map, MemberInfoEntity.class);
		
		Map<String, Object> sMap=new HashMap<String, Object>();
		sMap.put("mobile", callstatisticsEntity.getS_Mobile());
		//查询被叫用户信息
		MemberInfoEntity sMemberInfoEntity=baseDao.queryForObject("MemberInfo.findMemberByMobileOrId", sMap, MemberInfoEntity.class);
		
		if (memberInfoEntity!=null) {
		callstatisticsEntity.setE_Mobile(memberInfoEntity.getMobile());
		callstatisticsEntity.setE_Name(memberInfoEntity.getUserName());
		}
		
		if (sMemberInfoEntity!=null) {
		callstatisticsEntity.setB_memberid(sMemberInfoEntity.getId());
		callstatisticsEntity.setS_Name(sMemberInfoEntity.getUserName());
		}
		
		callstatisticsEntity.setCreateTime(new Date());
		Long  id=baseDao.persist(callstatisticsEntity, Long.class);
		if (id!=null) {
			return result.setResult(true);
		}
		return result.setResult(false);	
	}

	/**
	 * 
	 * @Description: 添加拨打电话记录  此类专门给 农批商、农商友  因为对方系统只能接收  Map类型的数据
	 * @param callstatisticsEntity
	 * @return
	 * @throws BizException
	 *
	 */
	@Override
	public ApiResult<Map<String, String>> saveCallResultMap(CallstatisticsEntity callstatisticsEntity) throws BizException {
		ApiResult<Map<String, String>> result = new ApiResult<Map<String, String>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberId", callstatisticsEntity.getMemberid());
		//查询主叫用户信息
		MemberInfoEntity memberInfoEntity=baseDao.queryForObject("MemberInfo.findMemberByMobileOrId", map, MemberInfoEntity.class);
		
		Map<String, Object> sMap=new HashMap<String, Object>();
		sMap.put("mobile", callstatisticsEntity.getS_Mobile());
		//查询被叫用户信息
		MemberInfoEntity sMemberInfoEntity=baseDao.queryForObject("MemberInfo.findMemberByMobileOrId", sMap, MemberInfoEntity.class);
		
		if (memberInfoEntity!=null) {
		callstatisticsEntity.setE_Mobile(memberInfoEntity.getMobile());
		callstatisticsEntity.setE_Name(memberInfoEntity.getUserName());
		}
		
		if (sMemberInfoEntity!=null) {
		callstatisticsEntity.setB_memberid(sMemberInfoEntity.getId());
		callstatisticsEntity.setS_Name(sMemberInfoEntity.getUserName());
		}
		
		callstatisticsEntity.setCreateTime(new Date());
		baseDao.persist(callstatisticsEntity, Long.class);
		Map<String, String> resultMap=new HashMap<String, String>();
		resultMap.put("msg", "成功");
		return result.setResult(resultMap);
	}

}

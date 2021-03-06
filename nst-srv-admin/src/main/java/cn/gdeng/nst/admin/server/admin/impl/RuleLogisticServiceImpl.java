package cn.gdeng.nst.admin.server.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberInfoDTO;
import cn.gdeng.nst.admin.dto.admin.RuleLogisticDTO;
import cn.gdeng.nst.admin.service.admin.RuleLogisticService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.CommonConstant;

/**
 * 规则分配-物流公司表对应service
 * @author Administrator
 *
 */
@Service
public class RuleLogisticServiceImpl implements RuleLogisticService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private BaseDao<?> baseDao;
	
	/**
	 * 根据规则查询物流公司信息
	 */
	@Override
	public ApiResult<List<RuleLogisticDTO>> getMemberInfoByRuleId(Integer id) {
		ApiResult<List<RuleLogisticDTO>> result = new ApiResult<List<RuleLogisticDTO>>();
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			List<RuleLogisticDTO> list = baseDao.queryForList("ruleLogistic.getMemberInfoByRuleId", map, RuleLogisticDTO.class);
			return result.setResult(list);
		}catch(Exception e){
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	
	/**
	 * 插入物流公司-规则表
	 */
	@Override
	public ApiResult<String> insertBatch(String[] ids, String ruleInfoId, String createUserId,
			String[] levels,String[] memberTypeArray,String[] totalLimtArray,String[] dayLimtArray,String[] startTimeArray,String[] endTimeArray,Integer onOff) {
		@SuppressWarnings("unchecked")
		Map<String, Object>[] batchValues = new HashMap[ids.length];
		//step1 获取最大的assginLevel 
		Integer assginLevel= baseDao.queryForObject("ruleLogistic.getMaxAssginLevel", null,Integer.class);
		for(int i = 0, len = batchValues.length; i < len; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ruleInfoId", ruleInfoId);
			map.put("memberId", Integer.parseInt(ids[i]));
			map.put("createUserId", createUserId);
			map.put("level", Integer.parseInt(levels[i]));
			map.put("memberType", Integer.parseInt(memberTypeArray[i]));
			map.put("totalLimt", Integer.parseInt(totalLimtArray[i]));
			map.put("dayLimt", Integer.parseInt(dayLimtArray[i]));
			map.put("startTime", DateUtil.formateDate(startTimeArray[i], "yyyy-MM-dd"));
			map.put("endTime", DateUtil.formateDate(endTimeArray[i], "yyyy-MM-dd"));
			map.put("assginLevel", (assginLevel+1+i));
			map.put("onOff", onOff);
			batchValues[i] = map;
		}
		String result = baseDao.batchUpdate("ruleLogistic.insertBatch", batchValues).length > 0 ? CommonConstant.COMMON_AJAX_SUCCESS : CommonConstant.COMMON_AJAX_ERROR;;
		return new ApiResult<String>(result, MsgCons.C_10000, MsgCons.M_10000);
	}
	
	/**
	 * 删除物流公司-规则表
	 */
	@Override
	@Transactional
	public ApiResult<int[]> batchDelete(String[] ids, String ruleInfoId) {
		@SuppressWarnings("unchecked")
		Map<String, Object>[] batchValues = new HashMap[ids.length];
		for(int i = 0, len = batchValues.length; i < len; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", Integer.parseInt(ids[i]));
			map.put("ruleInfoId", ruleInfoId);
			batchValues[i] = map;
		}
		int[] result = baseDao.batchUpdate("ruleLogistic.deleteBatch", batchValues);
		return new ApiResult<int[]>().setResult(result);
	}

	/**
	 * 更新物流公司-规则表
	 */
	@Override
	public ApiResult<String> updateBatch(String[] ids, String ruleInfoId, String updateUserId,
			String[] levels,String[] memberTypeArray,String[] totalLimtArray,String[] dayLimtArray,String[] startTimeArray,String[] endTimeArray) {
		@SuppressWarnings("unchecked")
		Map<String, Object>[] batchValues = new HashMap[ids.length];
		for(int i = 0, len = batchValues.length; i < len; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ruleInfoId", ruleInfoId);
			map.put("memberId", Integer.parseInt(ids[i]));
			map.put("updateUserId", updateUserId);
			map.put("level", Integer.parseInt(levels[i]));
			map.put("memberType", Integer.parseInt(memberTypeArray[i]));
			map.put("totalLimt", Integer.parseInt(totalLimtArray[i]));
			map.put("dayLimt", Integer.parseInt(dayLimtArray[i]));
			map.put("startTime", DateUtil.formateDate(startTimeArray[i], "yyyy-MM-dd"));
			map.put("endTime", DateUtil.formateDate(endTimeArray[i], "yyyy-MM-dd"));
			batchValues[i] = map;
		}
		String result = baseDao.batchUpdate("ruleLogistic.updateBatch", batchValues).length > 0 ? CommonConstant.COMMON_AJAX_SUCCESS : CommonConstant.COMMON_AJAX_ERROR;;
		return new ApiResult<String>(result, MsgCons.C_10000, MsgCons.M_10000);
	}
	
	/**
	 * 分页查询物流公司
	 */
	@Override
	public ApiResult<AdminPageDTO> companyQueryPage(Map<String, Object> map) {
		ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>();
		try{
			int total = baseDao.queryForObject("ruleLogistic.getCompanyTotal",map,Integer.class);
			List<MemberInfoDTO> list = baseDao.queryForList("ruleLogistic.getCompanyByPage",map,MemberInfoDTO.class);
			AdminPageDTO page = new AdminPageDTO(total,list);
			apiResult.setResult(page);
			return apiResult;
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	/**
	 * 批量同步启用禁用状态
	 */
	@Override
	public ApiResult<Integer> updateOnOffBatch(Map<String, Object> map) {
		Integer result = baseDao.execute("ruleLogistic.updateOnOffBatch", map); 
		return new ApiResult<Integer>(result, MsgCons.C_10000, MsgCons.M_10000);
	}


	/**
	 * 根据物流规则ID分页查询物流公司/车主
	 */
	@Override
	public ApiResult<List<RuleLogisticDTO>> getMemberInfoByRuleIdPage(Map<String, Object> map) {
		ApiResult<List<RuleLogisticDTO>> result = new ApiResult<List<RuleLogisticDTO>>();
		try{
			List<RuleLogisticDTO> list = baseDao.queryForList("ruleLogistic.getMemberInfoByRuleIdPage", map, RuleLogisticDTO.class);
			return result.setResult(list);
		}catch(Exception e){
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	
	/**
	 * 根据规则ID查询物流公司/车主 总数
	 */
	@Override
	public ApiResult<Integer> queryRuleLogisticByRuleIdTotal(Map<String, Object> map) {
		ApiResult<Integer> result = new ApiResult<Integer>();
		try{
			Integer count = baseDao.queryForObject("ruleLogistic.queryRuleLogisticByRuleIdTotal", map, Integer.class);
			return result.setResult(count);
		}catch(Exception e){
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
}

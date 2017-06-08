package cn.gdeng.nst.admin.server.admin.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberCarDTO;
import cn.gdeng.nst.admin.service.admin.MemberCarManageService;
import cn.gdeng.nst.admin.service.admin.mq.provider.MemberCerMqProviderService;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberCarEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

@Service
public class MemberCarManageServiceImpl implements MemberCarManageService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BaseDao<?> baseDao;
	@Resource
	private MemberCerMqProviderService providerService;

	@Override
	public List<MemberCarEntity> getByConditionPage(Map<String, Object> map)
			throws Exception {
		return baseDao.queryForList("MemberCar.getByCondition", map, MemberCarEntity.class);
	}
	
	@Override
	public int  getByConditionCount(Map<String, Object> map)
			throws Exception {
		return (int)baseDao.queryForObject("MemberCar.getByCondition", map,  Integer.class);
	}
	
	
	@Override
	public ApiResult<AdminPageDTO> queryPage(Map<String, Object> map) {
		ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>();
		try{
			int total = baseDao.queryForObject("MemberCar.countTotal",map,Integer.class);
			List<MemberCarDTO> list = baseDao.queryForList("MemberCar.queryListByPage",map,MemberCarDTO.class);
			AdminPageDTO page = new AdminPageDTO(total,list);
			apiResult.setResult(page);
			return apiResult;
		}catch(Exception e){
			logger.error("", e);
			return apiResult.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}
	@Override
	public ApiResult<MemberCarDTO> getMemberCarDetail(Map<String, Object> map){
		ApiResult<MemberCarDTO> result = new ApiResult<MemberCarDTO>();
		try{
			result.setResult(baseDao.queryForObject("MemberCar.queryMemberCarDetail", map, MemberCarDTO.class));
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> updateMemberCarById(Map<String, Object> map) {
		ApiResult<Integer> result = new ApiResult<Integer>();
		try{
			result.setResult((Integer)baseDao.execute("MemberCar.updateMemberCarById", map));
			return result;
		}catch(Exception e){	
			logger.error("", e);
			return result.withError(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	@Override
	public ApiResult<Integer> queryMemberCarNumber(Map<String, Object> map) {
		ApiResult<Integer> result = new ApiResult<Integer>();
		int total = baseDao.queryForObject("MemberCar.carNumberCountTotal",map,Integer.class);
		result.setResult(total);
		return result;
	}

	@Override
	public ApiResult<Integer> queryMemberCarCount(Map<String, Object> map) {
		ApiResult<Integer> result = new ApiResult<Integer>();
		int total = baseDao.queryForObject("MemberCar.countTotal",map,Integer.class);
		result.setResult(total);
		return result;
	}

	/**
	 * 
	 * @Description: 删除车辆
	 * @param map
	 * @return
	 *
	 */
	@Override
	@Transactional
	public ApiResult<Integer> deleteMemberCarById(Map<String, Object> map) {
		ApiResult<Integer> result = new ApiResult<Integer>();
		MemberCarEntity memberCarEntity = baseDao.queryForObject("MemberCar.queryMemberCarDetail", map, MemberCarEntity.class);
		result.setIsSuccess(false);
		if (null == memberCarEntity) {
			result.setMsg("车辆不存在");
		} else if (memberCarEntity.getIsDeleted().compareTo((byte) 1) == 0) {
			result.setMsg("车辆已经删除过了");
		} else {
			Integer re= baseDao.execute("MemberCar.updateMemberCarById", map);
			if (re > 0) {
				// 发消息
				String content = String.format(MsgCons.M_28024, memberCarEntity.getCarNumber());
				PushMsgDto msgDto = new PushMsgDto();
				msgDto.setBizId(re);
				msgDto.setMemberId(memberCarEntity.getMemberId());
				msgDto.setContent(content);
				msgDto.setMsgType(PushConstants.MSG_TYPE_9);
				providerService.sendMemberCerAppMsg(re.toString(), msgDto);

				result.setIsSuccess(true);
				result.setMsg("删除成功");
			}

		}
		return result;
	}

}

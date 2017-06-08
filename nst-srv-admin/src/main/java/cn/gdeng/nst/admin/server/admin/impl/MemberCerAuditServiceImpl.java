package cn.gdeng.nst.admin.server.admin.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberCerAuditDTO;
import cn.gdeng.nst.admin.service.admin.mq.provider.MemberCerMqProviderService;
import cn.gdeng.nst.admin.service.member.MemberCerAuditService;
import cn.gdeng.nst.admin.service.member.MemberCerService;
import cn.gdeng.nst.api.dto.task.PushMsgDto;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.entity.nst.MemberCerAuditEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.enums.PushConstants;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

@Service
public class MemberCerAuditServiceImpl implements MemberCerAuditService {
    
    @Resource
    private BaseDao<?> baseDao;    
    @Resource
    private MemberCerService memberCerService;
    @Resource
    private MemberCerMqProviderService providerService;
    

    @Override
    @Transactional
    public ApiResult<Long> save(MemberCerAuditDTO dto) throws BizException{
        String msg = "";
        Integer memberId = dto.getMemberId();
        PushMsgDto msgDto = null;
        MemberCerAuditEntity entity = new MemberCerAuditEntity();
        entity.setCerId(dto.getCerId());
        entity.setAuditOpinion(dto.getAuditOpinion());
        entity.setCerStatus(dto.getCerStatus());
        entity.setCreateUserId(dto.getCreateUserId());
        entity.setCreateTime(dto.getCreateTime());
        Long number = baseDao.persist(entity,Long.class); 
        if(number > 0){
            Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
            paramMap.put("cerId", dto.getCerId());
            paramMap.put("cerStatus", dto.getCerStatus());
            paramMap.put("userType", dto.getUserType());
            paramMap.put("auditor", dto.getCreateUserId());
            paramMap.put("auditTime", dto.getCreateTime());
            paramMap.put("auditOpinion", dto.getAuditOpinion());
            if(dto.getCerStatus()==1){
                msg = MsgCons.M_28008;
                msgDto = new PushMsgDto();
                msgDto.setBizId(number.intValue());
                msgDto.setMemberId(memberId);
                msgDto.setContent(msg);
                msgDto.setMsgType(PushConstants.MSG_TYPE_2);
                providerService.sendMemberCerAppMsg(number.toString(), msgDto);
                paramMap.put("typeStatus", 2);
                
				// 认证通过, 修改车辆为承载车辆
                Map<String, Object> carMap = new HashMap<String, Object>();
                carMap.put("carId", dto.getCarId());
                baseDao.execute("MemberCar.updateIsCarriage", carMap);
                
            }else if(dto.getCerStatus()==2){
                msg = MsgCons.M_28007;
                msgDto = new PushMsgDto();
                msgDto.setBizId(number.intValue());
                msgDto.setMemberId(memberId);
                msgDto.setContent(msg);
                msgDto.setMsgType(PushConstants.MSG_TYPE_1);
                providerService.sendMemberCerAppMsg(number.toString(), msgDto);
                paramMap.put("typeStatus", 3);
            } 
            number =  (long) baseDao.execute("Membercer.updateByConditions", paramMap);
        }      
        ApiResult<Long> result = new ApiResult<Long>(number,MsgCons.C_10000, MsgCons.M_10000);
        return result;
    }

    @Override
    public ApiResult<AdminPageDTO> findByConditions(Map<String, Object> paramMap) throws BizException {
        int total = baseDao.queryForObject("MemberCerAudit.count", paramMap, Integer.class);
        List<MemberCerAuditDTO> list = baseDao.queryForList("MemberCerAudit.findByPage", paramMap,MemberCerAuditDTO.class);
        AdminPageDTO page = new AdminPageDTO(total, list);
        ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>(page, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }

    @Override
    public ApiResult<Integer> countTotal(Map<String, Object> paramMap) throws BizException{
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ApiResult<?> findAll(Map<String, Object> paramMap) throws BizException{
        // TODO Auto-generated method stub
        return null;
    }
}

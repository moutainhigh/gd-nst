package cn.gdeng.nst.admin.server.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberCerDTO;
import cn.gdeng.nst.admin.service.member.MemberCerService;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;

@Service
public class MemberCerServiceImpl implements MemberCerService {
    
    @Resource
    private BaseDao<?> baseDao;

    @Override
    public ApiResult<AdminPageDTO> findByConditions(Map<String, Object> paramMap) throws BizException {
        int total = baseDao.queryForObject("Membercer.countByConditions", paramMap, Integer.class);
        List<Map<String,Object>> list = baseDao.queryForList("Membercer.findByConditions", paramMap);
        AdminPageDTO page = new AdminPageDTO(total, list);
        ApiResult<AdminPageDTO> apiResult = new ApiResult<AdminPageDTO>(page, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }

    @Override
    public ApiResult<MemberCerDTO> findOne(Map<String, Object> paramMap) throws BizException {
        MemberCerDTO dto =  (MemberCerDTO) baseDao.queryForObject("Membercer.findOne", paramMap,MemberCerDTO.class);
       
        //查询车辆是否已被注册验证
        if(dto != null && dto.getCarId() != null) {
        	Map<String, Object> carMap = new HashMap<String, Object>();
        	carMap.put("mcId", dto.getId());
        	carMap.put("memberCarNumber", dto.getCarNumber());
        	int carCount = baseDao.queryForObject("Membercer.countAuthedCar", carMap, Integer.class);
        	if(carCount > 0) {
        		// 已被注册验证
        		dto.setCarAuth(1);
        	} else {
        		// 未被注册验证
        		dto.setCarAuth(0);
        	}
        }
        ApiResult<MemberCerDTO> apiResult =  new ApiResult<MemberCerDTO>(dto, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }
    
    @Override
    public ApiResult<Integer> countTotal(Map<String, Object> paramMap) throws BizException{
        int total = baseDao.queryForObject("Membercer.countByConditions", paramMap, Integer.class);
        ApiResult<Integer> apiResult = new ApiResult<Integer>(total, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }

    @Override
    public ApiResult<?> findAll(Map<String, Object> paramMap) throws BizException {
        List<?> list = baseDao.queryForList("Membercer.findAll", paramMap);
        ApiResult<List<?>> apiResult = new ApiResult<List<?>>(list, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }
}

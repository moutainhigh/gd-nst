package cn.gdeng.nst.server.member.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.api.dto.member.MemberCarApiDTO;
import cn.gdeng.nst.api.dto.member.MemberCerApiDTO;
import cn.gdeng.nst.api.server.member.MemberCarApiService;
import cn.gdeng.nst.api.server.member.MemberCerApiService;
import cn.gdeng.nst.api.vo.member.MemberCerVo;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GdProperties;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudeng.framework.dba.transaction.annotation.Transactional;

/**
 * 身份认证Service的实现类
 * @author yyj
 *
 */
@Service
public class MemberCerApiServiceImpl implements MemberCerApiService {

    @Autowired
    private GdProperties gdProperties;
    
    @Autowired
    private MemberCarApiService carApiService;

    @Resource
    private BaseDao<?>   baseDao;

    @Override
    public ApiResult<Integer> countByMemberId(Map<String, Object> paramMap) throws BizException{
        Integer total = baseDao.queryForObject("MembercerApi.countByMemberId", paramMap, Integer.class);
        ApiResult<Integer> apiResult = new ApiResult<Integer>(total, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }

    @Override
    public ApiResult<MemberCerVo> findOneByMemberId(Map<String, Object> paramMap) throws BizException{
        MemberCerVo vo = baseDao.queryForObject("MembercerApi.findByMemberId", paramMap, MemberCerVo.class);
        ApiResult<MemberCerVo> apiResult = new ApiResult<MemberCerVo>(vo, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }

    @Override
    @Transactional
    public ApiResult<Integer> save(MemberCerApiDTO dto) throws BizException{
    	//控制层做过同样的校验
    	/*if(StringUtils.isEmpty(dto.getRealName())){
            throw new BizException(MsgCons.C_21009, MsgCons.M_21009);
        }else if(StringUtils.isEmpty(dto.getIdCard())){
            throw new BizException(MsgCons.C_21010, MsgCons.M_21010);
        }else if(!dto.getIdCard().trim().matches(ValidatorUtil.REGEX_ID_CARD)){
            throw new BizException(MsgCons.C_21015, MsgCons.M_21015);
        }else if(StringUtils.isEmpty(dto.getIdCardUrl_z())){
            throw new BizException(MsgCons.C_21011, MsgCons.M_21011);
        }else if(StringUtils.isEmpty(dto.getIdCardUrl_f())){
            throw new BizException(MsgCons.C_21012, MsgCons.M_21012);
        }else if(dto.getFlag() != null && dto.getFlag()==1 && StringUtils.isEmpty(dto.getCompanyName())){
            throw new BizException(MsgCons.C_21013, MsgCons.M_21013);
        }else if(StringUtils.isNotEmpty(dto.getCompanyName()) && dto.getCompanyName().length() > 20){
            throw new BizException(MsgCons.C_21014, MsgCons.M_21014);
        }*/
    	
    	//操作数据库影响的记录数
        Integer number = 0;
        //用户认证类型:1:个人认证(默认传1),2:公司认证
        Integer userType = 0;
        dto.setCerStatus(0);
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("memberId", dto.getMemberId());
        paramMap.put("userType", 1);
        Integer total = countByMemberId(paramMap).getResult();
        if (total == 0) {
            baseDao.execute("MembercerApi.save", dto);
            if (StringUtils.isNotEmpty(dto.getBzlUrl())) {  
                paramMap.put("userType", 2);
                //此处的total是查询企业是否已经被认证过了，如果=0则从新生成一条企业认证记录，如果>0则更新原有的
                total = countByMemberId(paramMap).getResult();
                //此处上面已经判断营业执照是否为空,这里再做判断显得多余(暂时不修改业务代码,只做备注说明)
                if (total == 0 && StringUtils.isNotEmpty(dto.getBzlUrl())) {
                    dto.setUserType(2);
                    number = baseDao.execute("MembercerApi.save", dto);
                    if(number > 0){
                        userType = 2;
                    }
                }
                if(total > 0){
                    dto.setUserType(2);
                    baseDao.execute("MembercerApi.update", dto);
                    userType = 2;
                }
            }
        } else {
            number = baseDao.execute("MembercerApi.update", dto);
            paramMap.put("userType", 2);
            //此处的total是查询企业是否已经被认证过了，如果=0则从新生成一条企业认证记录，如果>0则更新原有的
            total = countByMemberId(paramMap).getResult();
            if (total == 0 && StringUtils.isNotEmpty(dto.getBzlUrl())) {
                dto.setUserType(2);
                number = baseDao.execute("MembercerApi.save", dto);
                if(number > 0){
                    userType = 2;
                }
            }
            if(total > 0){
                userType = 2;
            }
        }
        paramMap.put("realName", dto.getRealName().trim());
        paramMap.put("cerPersonalStatus", 1);
        paramMap.put("iconUrl", dto.getIconUrl());
        if(userType == 2){
            paramMap.put("cerCompanyStatus", 1);         
        }
        baseDao.execute("MemberInfo.updateByMap", paramMap);
        if(dto.getMemberCarId() != null){
            MemberCarApiDTO carApiDTO = new MemberCarApiDTO();
            carApiDTO.setId(dto.getMemberCarId());
            carApiDTO.setMemberId(dto.getMemberId());
            carApiDTO.setVehicleUrl(dto.getVehicleUrl());
            carApiDTO.setCarHeadUrl(dto.getCarHeadUrl());
            carApiDTO.setCarRearUrl(dto.getCarRearUrl());
            carApiService.updateMemberCar(carApiDTO);
        }      
        ApiResult<Integer> apiResult = new ApiResult<Integer>(number, MsgCons.C_10000, MsgCons.M_10000);
        return apiResult;
    }
}

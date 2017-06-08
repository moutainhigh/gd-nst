package cn.gdeng.nst.web.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.member.MemberCerApiDTO;
import cn.gdeng.nst.api.server.member.MemberCerApiService;
import cn.gdeng.nst.api.vo.member.MemberCerVo;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.ValidatorUtil;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 身份认证的controller
 * @author yjj
 *
 */
@Controller
@RequestMapping("v1/memberCerApi")
public class MemberCerApiController extends BaseController{  
    @Reference
    MemberCerApiService memberCerApiService;
    
    /**
     * 根据memberId查询认证信息
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("memberCerInfo")
    public Object memberCerInfo(HttpServletRequest request) throws Exception {   
       Map<String,Object> paramMap = getDecodeMap(request);      
       ApiResult<MemberCerVo> result = memberCerApiService.findOneByMemberId(paramMap);
       return result;
    }
    
    /**
     * 保存认证信息到信息表
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("saveCerInfo")
    public Object saveCerInfo(HttpServletRequest request) throws Exception {
         MemberCerApiDTO dto = getDecodeDto(request,MemberCerApiDTO.class); 
         checkParam(dto);
         ApiResult<Integer> result = memberCerApiService.save(dto);
         return result;
    }
    
    /**
     * 检查参数
     * @param dto
     * @throws BizException
     */
	public void checkParam(MemberCerApiDTO dto) throws BizException{
	   	 DataCheckUtils.assertIsNonNull(MsgCons.C_21009, MsgCons.M_21009,dto.getRealName());
	     DataCheckUtils.assertIsNonNull(MsgCons.C_21010, MsgCons.M_21010,dto.getIdCard());
	     DataCheckUtils.assertIsNonNull(MsgCons.C_21011, MsgCons.M_21011,dto.getIdCardUrl_z());
	     DataCheckUtils.assertIsNonNull(MsgCons.C_21012, MsgCons.M_21012,dto.getIdCardUrl_f());
	     //DataCheckUtils.assertIsNonNull(MsgCons.C_21022, MsgCons.M_21022,dto.getCertificFrom());
         if(!dto.getIdCard().trim().matches(ValidatorUtil.REGEX_ID_CARD)){
             throw new BizException(MsgCons.C_21015, MsgCons.M_21015);
         }else if(dto.getFlag() != null && dto.getFlag()==1 && StringUtils.isEmpty(dto.getCompanyName())){
             throw new BizException(MsgCons.C_21013, MsgCons.M_21013);
         }else if(StringUtils.isNotEmpty(dto.getCompanyName()) && dto.getCompanyName().length() > 20){
             throw new BizException(MsgCons.C_21014, MsgCons.M_21014);
         }
	}
}

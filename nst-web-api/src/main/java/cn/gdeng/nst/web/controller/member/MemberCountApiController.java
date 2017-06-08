package cn.gdeng.nst.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.member.MemberCountDTO;
import cn.gdeng.nst.api.server.member.MemberCountApiService;
import cn.gdeng.nst.api.vo.member.MemberCountCompanyVo;
import cn.gdeng.nst.api.vo.member.MemberCountDriverVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;
/**
 * 用户统计controller
 * @author kwang
 *
 */
@Controller
@RequestMapping("memberCountApi")
public class MemberCountApiController  extends BaseController {

	@Reference
	private MemberCountApiService memberCountApiService;
	
	/**
	 * 物流公司用户统计
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findByCompanyMemberId")
	public Object findByCompanyMemberId(HttpServletRequest request) throws Exception {
		MemberCountDTO memberCountDTO = getDecodeDto(request,MemberCountDTO.class);        
        ApiResult<MemberCountCompanyVo> result = memberCountApiService.findByCompanyMemberId(memberCountDTO);
        return result; 
	 }
	
	/**
	 * 司机用户统计
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findByDriverMemberId")
	public Object findByDriverMemberId(HttpServletRequest request) throws Exception {
		MemberCountDTO memberCountDTO = getDecodeDto(request,MemberCountDTO.class);        
        ApiResult<MemberCountDriverVo> result = memberCountApiService.findByDriverMemberId(memberCountDTO);
        return result; 
	 }
	
	
	
	
}

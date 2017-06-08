package cn.gdeng.nst.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.member.MemberLineApiDTO;
import cn.gdeng.nst.api.server.member.MemberLineApiService;
import cn.gdeng.nst.entity.nst.MemberLineEntity;
import cn.gdeng.nst.util.web.api.ApiPage;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;
/**
 * 常跑线路controller
 * @author kwang
 *
 */
@Controller
@RequestMapping("memberLineApi")
public class MemberLineApiController  extends BaseController {

	@Reference
	private MemberLineApiService memberLineApiService;
	
	/**
	 * 分页查询常用线路
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryMemberLinePage")
	@ResponseBody
	public Object queryMemberLinePage(HttpServletRequest request) throws Exception {
		ApiPage page = getPageInfoEncript(request);
		return memberLineApiService.queryPage(page);
	}
	/**
	 * 保存常用线路
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveMemberLine")
	public Object saveMemberLine(HttpServletRequest request) throws Exception {
		MemberLineEntity memberLineEntity = getDecodeDto(request,MemberLineEntity.class);        
        ApiResult<Long> result = memberLineApiService.saveMemberLine(memberLineEntity);
        return result; 
	 }
	
	/**
	 * 删除常用线路
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteMemberLineById")
	public Object deleteMemberLineById(HttpServletRequest request) throws Exception {
		MemberLineApiDTO memberLineApiDTO = getDecodeDto(request,MemberLineApiDTO.class);        
        ApiResult<Integer> result = memberLineApiService.deleteMemberLineById(memberLineApiDTO);
        return result; 
	 }
	
}

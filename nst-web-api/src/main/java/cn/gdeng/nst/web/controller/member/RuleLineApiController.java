package cn.gdeng.nst.web.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.member.RuleLineApiDTO;
import cn.gdeng.nst.api.server.member.RuleLineApiService;
import cn.gdeng.nst.entity.nst.RuleLineEntity;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;
/**
 *订阅线路controller
 * @author kwang
 *
 */
@Controller
@RequestMapping("ruleLineApi")
public class RuleLineApiController extends BaseController {
	
	@Reference
	private RuleLineApiService ruleLineApiService;
	

	/**
	 * 订阅线路列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ruleLineList")
	@ResponseBody
	public Object ruleLineList(HttpServletRequest request) throws Exception {
		RuleLineApiDTO ruleLineApiDTO = getDecodeDto(request,RuleLineApiDTO.class);
		return ruleLineApiService.ruleLineList(ruleLineApiDTO);
	}
	/**
	 * 保存订阅线路
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveRuleLine")
	public Object saveRuleLine(HttpServletRequest request) throws Exception {
		RuleLineEntity ruleLineEntity = getDecodeDto(request,RuleLineEntity.class);        
        ApiResult<Long> result = ruleLineApiService.saveRuleLine(ruleLineEntity);
        return result; 
	 }
	
	/**
	 * 删除订阅线路
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteRuleLineById")
	public Object deleteRuleLineById(HttpServletRequest request) throws Exception {
		RuleLineApiDTO ruleLineApiDTO = getDecodeDto(request,RuleLineApiDTO.class);        
        ApiResult<Integer> result = ruleLineApiService.deleteRuleLineById(ruleLineApiDTO);
        return result; 
	 }
	

}

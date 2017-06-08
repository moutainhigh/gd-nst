package cn.gdeng.nst.web.controller.member;

import javax.servlet.http.HttpServletRequest;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.dto.member.RuleOnoffDTO;
import cn.gdeng.nst.api.server.member.RuleOnoffApiService;
import cn.gdeng.nst.api.vo.member.RuleOnoffDetailVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 订阅开关
 *@author kwang
 */
@Controller
@RequestMapping("ruleOnoffApi")
public class RuleOnoffApiController  extends BaseController {

	@Reference
	private RuleOnoffApiService ruleOnoffApiApiService;
	/**
	 * 修改开关（开关）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updateRuleOnoffById")
	public Object updateRuleOnoffById(HttpServletRequest request) throws Exception {
		RuleOnoffDTO ruleOnoffDTO = getDecodeDto(request,RuleOnoffDTO.class);        
		//区分车主  还是物流公司开启收货模式
		ruleOnoffDTO.setDeviceApp(getAppInfo(request).getDeviceApp());
		ApiResult<Integer> result = ruleOnoffApiApiService.updateRuleOnoffById(ruleOnoffDTO);
        return result; 
	 }
	/**
	 * 查询用户开关设置
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("queryRuleOnoffById")
	public Object queryRuleOnoffById(HttpServletRequest request) throws Exception {
		RuleOnoffDTO ruleOnoffDTO = getDecodeDto(request,RuleOnoffDTO.class);        
        ApiResult<RuleOnoffDetailVo> result = ruleOnoffApiApiService.queryRuleOnoffById(ruleOnoffDTO);
        return result; 
	 }
	/**
	 * 修改订阅开关地址
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updateRuleOnoffAddressById")
	public Object updateRuleOnoffAddressById(HttpServletRequest request) throws Exception {
		RuleOnoffDTO ruleOnoffDTO = getDecodeDto(request,RuleOnoffDTO.class);        
        ApiResult<Integer> result = ruleOnoffApiApiService.updateRuleOnoffAddressById(ruleOnoffDTO);
        return result; 
	 }
	
	
}

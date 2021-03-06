package cn.gdeng.nst.web.controller.pub;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.vo.pub.AssignVO;
import cn.gdeng.nst.pub.service.RuleLogisticAssignService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 是否指派物流公司Controller
 * @author wj
 *
 */
@Controller
@RequestMapping("v1/assign")
public class RuleLogisticAssignController extends BaseController {
	
	@Reference
	private RuleLogisticAssignService ruleLogisticAssignService;

	/**
	 * 判断农商友是否已选择指派物流公司
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("validateAssign")
	@ResponseBody
	public Object validateAssign(HttpServletRequest request) throws Exception{
		Map<String, Object> paramMap=getDecodeMap(request);
		checkParam(paramMap);
		ApiResult<AssignVO> apiResult = ruleLogisticAssignService.validateAssign(paramMap);
		return apiResult;
	}
	
	/**
	 * 检查参数
	 * @param paramMap
	 * @throws BizException
	 */
	public void checkParam(Map<String, Object> paramMap) throws BizException{
		//货主memberId不能为空  
		DataCheckUtils.assertIsNonNull("货主memberId不能为空", paramMap.get("memberIdShipper"));
	}
}

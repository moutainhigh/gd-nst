package cn.gdeng.nst.admin.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.MemberCarManageService;
import cn.gdeng.nst.admin.service.admin.SourceShipperService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;

@RequestMapping("demo")
@Controller
public class DemoController extends AdminBaseController{
	@Reference
	private SourceShipperService sourceShipperService;
	@Reference
	private MemberCarManageService memberCarManageService;

	@RequestMapping("index")
	public String index(){
		return "demo/demo";
	}
	
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		ApiResult<AdminPageDTO> apiResult = memberCarManageService.queryPage(paramMap);
		return JSONObject.toJSONString(apiResult.getResult(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	@RequestMapping("test1")
	@ResponseBody
	public String test1(HttpServletRequest request){
		return JSONObject.toJSONString(new ApiResult<>());
	}
	
	@RequestMapping("test2")
	@ResponseBody
	public String test2(HttpServletRequest request){
		return JSONObject.toJSONString(new ApiResult<>().withError(MsgCons.C_21011, MsgCons.M_21011));
	}
	
	@RequestMapping("test3")
	@ResponseBody
	public String test3(HttpServletRequest request){
		int a = 1/0;
		return JSONObject.toJSONString(new ApiResult<>().withError(MsgCons.C_21011, MsgCons.M_21011));
	}
	
}

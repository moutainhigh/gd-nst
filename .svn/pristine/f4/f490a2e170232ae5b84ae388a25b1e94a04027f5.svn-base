package cn.gdeng.nst.admin.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.service.admin.AreaManageService;
import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("area")
public class AreaController extends AdminBaseController{

	@Reference
	private AreaManageService areaManageService;
	
	@RequestMapping("queryTopList")
	@ResponseBody
	public String queryTopList(){
		ApiResult<List<AreaDTO>> apiResult = areaManageService.queryTopList();
		return JSONObject.toJSONString(apiResult);
	}
	
	@RequestMapping("queryChildList/{parentId}")
	@ResponseBody
	public String queryChildList(@PathVariable("parentId")String parentId){
		ApiResult<List<AreaDTO>> apiResult = areaManageService.queryChildList(parentId);
		return JSONObject.toJSONString(apiResult);
	}
}

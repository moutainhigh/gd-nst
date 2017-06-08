package cn.gdeng.nst.web.controller.pub;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.redis.AreaDTO;
import cn.gdeng.nst.pub.service.AreaService;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.web.controller.base.BaseController;

/**
 * 区域Controller
 * @author xiaojun
 *
 */
@Controller
@RequestMapping("v1/area")
public class AreaController extends BaseController {
	
	@Reference
	private AreaService areaService;
	/**
	 * 获取顶级区域
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listTopArea")
	@ResponseBody
	public Object listTopArea(HttpServletRequest request) throws Exception{
		return areaService.listTopArea();
	}
	/**
	 * 获取区域下一级区域
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listChildArea")
	@ResponseBody
	public Object listChildArea(HttpServletRequest request) throws Exception{
		String jsonStr=getParamDecodeStr(request);
		String parentId=GSONUtils.getJsonValueStr(jsonStr, "parentId");
		ApiResult<List<AreaDTO>> apiResult = areaService.listChildArea(parentId);
		return apiResult;
	}
}

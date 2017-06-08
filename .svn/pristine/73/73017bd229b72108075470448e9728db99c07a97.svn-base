package cn.gdeng.nst.admin.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminAdBannerDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.AdBannerService;
import cn.gdeng.nst.entity.nst.AdBannerEntity;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.enums.AdBannerStateEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.CommonUtil;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 广告轮播图（废弃）
 * @author dengjianfeng
 *
 */
@Controller
@RequestMapping("adBanner")
public class AdBannerController extends AdminBaseController{

	@Reference
	private AdBannerService adBannerService;
	
	@RequestMapping("index")
	public String index(){
		return "adBanner/list";
	}
	
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if(StringUtils.isNotBlank(startDate)){
			String startTime = CommonUtil.getStartOfDay(DateUtil.formateDate(startDate));
			paramMap.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endDate)){
			String endTime = CommonUtil.getEndOfDay(DateUtil.formateDate(endDate));
			paramMap.put("endTime", endTime);
		}
		
		ApiResult<AdminPageDTO> apiResult = adBannerService.queryPage(paramMap);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	@RequestMapping("add")
	public String add(){
		return "adBanner/add";
	}
	
	@RequestMapping(value="saveAdd",produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveAdd(HttpServletRequest request, AdBannerEntity adBannerEntity){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		
		adBannerEntity.setState(AdBannerStateEnum.ENABLED.getCode());
		adBannerEntity.setCreateTime(new Date());
		adBannerEntity.setCreateUserId(getUser(request).getUserID());
		ApiResult<Long> apiResult = adBannerService.persist(adBannerEntity);
		return JSONObject.toJSONString(apiResult);
	}
	
	@RequestMapping(value="delete",produces="application/json;charset=utf-8")
	@ResponseBody
	public String delete(HttpServletRequest request){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		
		String idsStr = request.getParameter("ids");
		String[] ids = idsStr.split(",");
		ApiResult<int[]> apiResult = adBannerService.batchDelete(ids, getUser(request).getUserID());
		return JSONObject.toJSONString(apiResult);
	}
	
	@RequestMapping(value="updateState",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateState(HttpServletRequest request, Integer id, Integer state){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("state", state);
		paramMap.put("updateUserId", getUser(request).getUserID());
		ApiResult<Integer> apiResult = adBannerService.updateState(paramMap);
		return JSONObject.toJSONString(apiResult);
	}

	@RequestMapping("detail/{id}")
	public String detail(@PathVariable("id")Integer id){
		ApiResult<AdminAdBannerDTO> apiResult = adBannerService.getById(id);
		if(apiResult != null){
			putModel("dto", apiResult.getResult());
		}
		return "adBanner/detail";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id")Integer id){
		ApiResult<AdminAdBannerDTO> apiResult = adBannerService.getById(id);
		if(apiResult != null){
			putModel("dto", apiResult.getResult());
		}
		return "adBanner/edit";
	}
	
	@RequestMapping(value="saveEdit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveEdit(HttpServletRequest request, AdminAdBannerDTO adBannerDTO){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		adBannerDTO.setUpdateUserId(getUser(request).getUserID());
		ApiResult<Integer> apiResult = adBannerService.update(adBannerDTO);
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 验证登录信息是否存在
	 * @param request
	 * @return
	 */
	private String validateLogin(HttpServletRequest request){
		SysRegisterUser registerUser = getUser(request);
		if(registerUser == null){
			ApiResult<String> apiResult = new ApiResult<String>();
			return JSONObject.toJSONString(apiResult.withError(MsgCons.C_30000,  MsgCons.M_30000)); 
		}
		return null;
	}
}

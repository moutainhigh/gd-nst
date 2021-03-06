package cn.gdeng.nst.admin.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdvertisementDTO;
import cn.gdeng.nst.admin.dto.admin.TreeNode;
import cn.gdeng.nst.admin.service.admin.AdvertisementService;
import cn.gdeng.nst.entity.nst.AdvertisementEntity;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.enums.AdStatusEnum;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.CommonUtil;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

/***
 * 广告管理
 * @author jfdeng
 *
 */
@Controller
@RequestMapping("advertisement")
public class AdvertisementController extends AdminBaseController{

	@Reference
	private AdvertisementService advertisementService;
	
	/**
	 * 进入广告管理列表主页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "advertisement/index";
	}
	
	/**
	 * 广告管理页面左边树形列表（省份数据列表）数据初始化
	 * @return
	 */
	@RequestMapping("queryTreeNodes")
	@ResponseBody
	public String queryTreeNodes(){
		ApiResult<List<TreeNode>> apiResult = advertisementService.queryForInitTree();
		return JSONObject.toJSONString(apiResult.getResult());
	}
	
	/**
	 * 广告管理页面左边树形列表：根据省份id获取对应的城市列表数据
	 * @param pid
	 * @return
	 */
	@RequestMapping("queryTreeNodes/{pid}")
	@ResponseBody
	public String queryTreeNodes(@PathVariable("pid")String pid){
		ApiResult<List<TreeNode>> apiResult = advertisementService.queryAreaNodeByPid(pid);
		return JSONObject.toJSONString(apiResult.getResult());
	}
	
	/**
	 * 广告管理页面右边分页数据（广告管理列表）查询
	 * @param request
	 * @return
	 */
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> map = getParametersMap(request);
		setCommParameters(request, map);
		map.put("nowDate", DateUtil.toString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if(StringUtils.isNotBlank(startDate)){
			String startTime = CommonUtil.getStartOfDay(DateUtil.formateDate(startDate));
			map.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endDate)){
			String endTime = CommonUtil.getEndOfDay(DateUtil.formateDate(endDate));
			map.put("endTime", endTime);
		}
		
		ApiResult<AdminPageDTO> apiResult = advertisementService.queryPage(map);
		return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 进入新增广告页面
	 * @param provinceId
	 * @param cityId
	 * @return
	 */
	@RequestMapping("add")
	public String add(String provinceId, String cityId){
		putModel("provinceId", provinceId);
		putModel("cityId", cityId);
		return "advertisement/add";
	}
	
	/**
	 * 保存新增广告信息
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping("saveAdd")
	@ResponseBody
	public String saveAdd(AdvertisementEntity entity, HttpServletRequest request){
		String validateLoginResult = validateLogin(request);
		if(validateLoginResult != null){
			return validateLoginResult;
		}
		entity.setStatus(AdStatusEnum.OFF.getCode());
		entity.setCreateTime(new Date());
		entity.setCreateUserId(getUser(request).getUserID());
		ApiResult<Long> apiResult = advertisementService.add(entity);
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 广告上下架操作
	 * @param advertiseDTO
	 * @return
	 */
	@RequestMapping("updateStatus")
	@ResponseBody
	public String updateStatus(AdvertisementDTO advertiseDTO){
		String validateLoginResult = validateLogin(request);
		if(validateLoginResult != null){
			return validateLoginResult;
		}
		advertiseDTO.setUpdateUserId(getUser(request).getUserID());
		ApiResult<Integer> apiResult = advertisementService.updateStatus(advertiseDTO);
		return JSONObject.toJSONString(apiResult);
	}
	
	/***
	 * 查看广告详情
	 * @param id
	 * @return
	 */
	@RequestMapping("detail/{id}")
	public String detail(@PathVariable("id")Integer id){
		ApiResult<AdvertisementDTO> apiResult = advertisementService.getById(id);
		if(apiResult != null){
			putModel("dto", apiResult.getResult());
		}
		return "advertisement/detail";
	}
	
	/**
	 * 进入编辑广告内容页面
	 * @param id
	 * @return
	 */
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id")Integer id){
		ApiResult<AdvertisementDTO> apiResult = advertisementService.getById(id);
		if(apiResult != null){
			putModel("dto", apiResult.getResult());
		}
		return "advertisement/edit";
	}
	
	/**
	 * 保存修改广告内容
	 */
	@RequestMapping("saveEdit")
	@ResponseBody
	public String saveEdit(AdvertisementDTO advertiseDTO){
		String validateLoginResult = validateLogin(request);
		if(validateLoginResult != null){
			return validateLoginResult;
		}
		advertiseDTO.setUpdateUserId(getUser(request).getUserID());
		ApiResult<Integer> apiResult = advertisementService.update(advertiseDTO);
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 保存广告内容并进行上架操作
	 * @param advertiseDTO
	 * @return
	 */
	@RequestMapping("saveEditAndPutOn")
	@ResponseBody
	public String saveEditAndPutOn(AdvertisementDTO advertiseDTO){
		String validateLoginResult = validateLogin(request);
		if(validateLoginResult != null){
			return validateLoginResult;
		}
		advertiseDTO.setUpdateUserId(getUser(request).getUserID());
		ApiResult<Integer> editApiResult = advertisementService.update(advertiseDTO);
		//修改成功
		if(editApiResult != null && editApiResult.getResult() != null && editApiResult.getResult() > 0){
			advertiseDTO.setStatus(AdStatusEnum.ON.getCode());
			ApiResult<Integer> updateStatusResult = advertisementService.updateStatus(advertiseDTO);
			return JSONObject.toJSONString(updateStatusResult);
		}
		return JSONObject.toJSONString(editApiResult);
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
	
	/**
	 * 跳到同步默认广告页面
	 * @return
	 */
	@RequestMapping("sync/{syncCityId}")
	public String sync(@PathVariable("syncCityId")Integer syncCityId) {
		putModel("syncCityId", syncCityId);
		return "advertisement/sync";
	}
	
	/**
	 * 获取‘默认’上架广告列表
	 * @param request
	 * @return
	 */
	@RequestMapping("listDefaultAdByChannel/{channel}")
	@ResponseBody
	public String listByConditions(@PathVariable("channel")Integer channel) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cityId", 0); 
		paramMap.put("status", AdStatusEnum.ON.getCode());
		paramMap.put("channel", channel);
		ApiResult<List<AdvertisementDTO>> result = advertisementService.listByConditions(paramMap);
		if (result == null) {
			result = new ApiResult<List<AdvertisementDTO>>().withError(MsgCons.C_20000, MsgCons.M_20000);
		}
		return JSONObject.toJSONString(result);
	}
	
	@RequestMapping("saveSync")
	@ResponseBody
	public String saveSync(@RequestParam("cityId") Integer cityId, @RequestParam("adId") Integer[] adIds) {
		// 登录用户信息
		SysRegisterUser loginUser = getUser(request);
		String loginUserId = loginUser == null ? null : loginUser.getUserID();
		
		ApiResult<String> result = advertisementService.syncAd(cityId, adIds, loginUserId);
		if (result == null) {
			result = new ApiResult<String>().withError(MsgCons.C_20000, MsgCons.M_20000);
		}
		return JSONObject.toJSONString(result);
	}
	
}

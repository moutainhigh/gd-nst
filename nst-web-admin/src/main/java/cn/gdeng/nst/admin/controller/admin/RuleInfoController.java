package cn.gdeng.nst.admin.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.RuleInfoDTO;
import cn.gdeng.nst.admin.dto.admin.RuleLogisticDTO;
import cn.gdeng.nst.admin.service.admin.RuleInfoService;
import cn.gdeng.nst.admin.service.admin.RuleLogisticService;
import cn.gdeng.nst.entity.nst.RuleInfoEntity;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 物流公司-分配规则
 * @author wj
 *
 */
@Controller
@RequestMapping("rule")
public class RuleInfoController extends AdminBaseController{
	
	@Reference
	private RuleInfoService ruleInfoService;
	
	@Reference
	private RuleLogisticService ruleLogisticService;
	
	@RequestMapping("index")
	public String index(){
		return "ruleInfo/list";
	}
	
	/**
	 * 物流规则分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping("pageQuery")
	@ResponseBody
	public String pageQuery(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		ApiResult<AdminPageDTO> apiResult = ruleInfoService.pageQuery(paramMap);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * 新增按钮跳转
	 * @return
	 */
	@RequestMapping("add")
	public String add(){
		return "ruleInfo/add";
	}
	
	/**
	 * 添加物流公司跳转
	 * @return
	 */
	@RequestMapping("addCompany")
	public String addCompany(){
		return "ruleInfo/addCompany";
	}
	
	/**
	 * 物流公司分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping("companyPageQuery")
	@ResponseBody
	public String companyPageQuery(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		ApiResult<AdminPageDTO> apiResult = ruleLogisticService.companyQueryPage(paramMap);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * 保存物流规则
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="saveAdd", produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveAdd(HttpServletRequest request, RuleInfoEntity entity){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		entity.setCreateTime(new Date());
		entity.setCreateUserId(getUser(request).getUserID());
		entity.setOnOff((byte)0);
        ApiResult<Long> result = ruleInfoService.saveAdd(entity);
		return JSONObject.toJSONString(result);
	}
	
	
	/**
	 * 物流规则编辑跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable String id, Model model){
		RuleInfoDTO entity = ruleInfoService.getById(Integer.parseInt(id)).getResult();
		model.addAttribute("entity", entity);
		return "ruleInfo/edit";
	}
	
	/**
	 * 物流规则编辑跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("getMemberInfoByRuleId")
	@ResponseBody
	public String getMemberInfoByRuleId(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		ApiResult<List<RuleLogisticDTO>> result = ruleLogisticService.getMemberInfoByRuleId(Integer.parseInt(id));
		return JSONObject.toJSONString(result.getResult(), SerializerFeature.WriteDateUseDateFormat);
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
	 * 编辑保存物流规则
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="saveEdit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveEdit(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		paramMap.put("updateUserId", getUser(request).getUserID());
		ApiResult<Integer> apiResult = ruleInfoService.update(paramMap);
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 保存物流规则-物流公司
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="insertRuleLogistic", produces="application/json;charset=utf-8")
	@ResponseBody
	public String insertRuleLogistic(HttpServletRequest request){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		String ruleInfoId = request.getParameter("ruleInfoId");
		String onOff = request.getParameter("onOff");
		
		String ids = request.getParameter("ids");
		String[] idArray = ids.split(",");
		String createUserId = getUser(request).getUserID();
		String levels = request.getParameter("levels");
		String[] levelArray = levels.split(",");
		
		String memberType = request.getParameter("memberType");
		String[] memberTypeArray = memberType.split(",");
		
		String totalLimt = request.getParameter("totalLimt");
		String[] totalLimtArray = totalLimt.split(",");
		
		String dayLimt = request.getParameter("dayLimt");
		String[] dayLimtArray = dayLimt.split(",");
		
		String startTime = request.getParameter("startTime");
		String[] startTimeArray = startTime.split(",");
		
		String endTime = request.getParameter("endTime");
		String[] endTimeArray = endTime.split(",");
		
        ApiResult<String> result = ruleLogisticService.insertBatch(idArray, ruleInfoId,
        		createUserId, levelArray,memberTypeArray,totalLimtArray,dayLimtArray,startTimeArray,endTimeArray,Integer.parseInt(onOff));
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 更新物流规则-物流公司
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="updateRuleLogistic", produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateRuleLogistic(HttpServletRequest request){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		String ruleInfoId = request.getParameter("ruleInfoId");
		String ids = request.getParameter("ids");
		String[] idArray = ids.split(",");
		String updateUserId = getUser(request).getUserID();
		String levels = request.getParameter("levels");
		String[] levelArray = levels.split(",");
		
		String memberType = request.getParameter("memberType");
		String[] memberTypeArray = memberType.split(",");
		String totalLimt = request.getParameter("totalLimt");
		String[] totalLimtArray = totalLimt.split(",");
		String dayLimt = request.getParameter("dayLimt");
		String[] dayLimtArray = dayLimt.split(",");
		String startTime = request.getParameter("startTime");
		String[] startTimeArray = startTime.split(",");
		String endTime = request.getParameter("endTime");
		String[] endTimeArray = endTime.split(",");
		
        ApiResult<String> result = ruleLogisticService.updateBatch(idArray, ruleInfoId, updateUserId, levelArray,memberTypeArray,totalLimtArray,dayLimtArray,startTimeArray,endTimeArray);
		return JSONObject.toJSONString(result);
	}
	
	@RequestMapping(value="delete",produces="application/json;charset=utf-8")
	@ResponseBody
	public String delete(HttpServletRequest request){
		String idsStr = request.getParameter("ids");
		String[] ids = idsStr.split(",");
		String ruleInfoId = request.getParameter("ruleInfoId");
		ApiResult<int[]> apiResult = ruleLogisticService.batchDelete(ids, ruleInfoId);
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 导出规则列表前的检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportCheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public String exportCheck(HttpServletRequest request){
		ApiResult<String> apiResult = new ApiResult<String>();
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		
		ApiResult<Integer> remoteApiResult = ruleInfoService.countTotal(paramMap);
		if(remoteApiResult == null || remoteApiResult.getResult() == null){
			apiResult.setCodeMsg(MsgCons.C_50000, MsgCons.M_50000);
			apiResult.setResult("服务器出错！");
			return JSONObject.toJSONString(apiResult);
		}
		
		//总记录数验证
		int total = remoteApiResult.getResult();
		if(total <= 0){
			return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_29005, MsgCons.M_29005));
		}
		else if(total > EXPORT_MAX_SIZE){
			return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_29006, MsgCons.M_29006));
		}
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 导出规则列表
	 */
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response){
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		
		OutputStream ouputStream = null;
		try{
			String fileName = "货源规则管理"+DateUtil.toString(new Date(), "yyyy-MM-dd_HH:mm:ss");
			 // 设置输出响应头信息，
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls");
            ouputStream = response.getOutputStream();
            
            WritableWorkbook wwb = Workbook.createWorkbook(ouputStream);
            if(wwb == null){
            	return;
            }
            
            // 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
            WritableSheet sheet = wwb.createSheet("货源规则管理", 0);
            
            String[] headers = {"规则名称","货源所在地","线路类别", "规则状态", "创建人", "规则启用时间", "规则禁用时间", "规则创建时间"};
            for(int i = 0, len = headers.length; i < len; i++){
            	 sheet.addCell(new Label(i, 0, headers[i]));
            }
            
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = ruleInfoService.countTotal(paramMap);
            int totalCount = 0;
            if(countApiResult != null){
            	totalCount = countApiResult.getResult();
            }
            // 计算分几次查询导出数据
            int exportCount = totalCount / EXPORT_PAGE_SIZE;
            if((totalCount % EXPORT_PAGE_SIZE) != 0){
            	exportCount++;
            }
            int startRow = 0;
    		for(int i = 0; i < exportCount; i++){
            	// 查询分页数据
            	paramMap.put("startRow", startRow);
            	paramMap.put("endRow", EXPORT_PAGE_SIZE);
            	ApiResult<List<RuleInfoDTO>> apiResult = ruleInfoService.queryList(paramMap);
            	List<RuleInfoDTO> list = null;
            	if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
            	for(int j = 0, len = list.size(); j < len; j++){
                	RuleInfoDTO dto = list.get(j);
                	sheet.addCell(new Label(0, j+1+startRow, dto.getName().toString()));
                	sheet.addCell(new Label(1, j+1+startRow, dto.getSourceAddress()));
                	sheet.addCell(new Label(2, j+1+startRow, dto.getSourceTypeStr()));
                    sheet.addCell(new Label(3, j+1+startRow, dto.getOnOffStr()));
                    sheet.addCell(new Label(4, j+1+startRow, dto.getCreateUser()));
                    sheet.addCell(new Label(5, j+1+startRow, dto.getOnTimeStr()));
                    sheet.addCell(new Label(6, j+1+startRow, dto.getOffTimeStr()));
                    sheet.addCell(new Label(7, j+1+startRow, dto.getCreateTimeStr()));
                }
            	startRow += EXPORT_PAGE_SIZE;
    		}
            wwb.write();
            wwb.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
            try {
                ouputStream.flush();
                ouputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 导出单个规则的详细信息前的检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportDetailCheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public String exportDetailCheck(HttpServletRequest request){
		ApiResult<String> apiResult = new ApiResult<String>();
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		
		ApiResult<Integer> remoteApiResult = ruleLogisticService.queryRuleLogisticByRuleIdTotal(paramMap);
		if(remoteApiResult == null || remoteApiResult.getResult() == null){
			apiResult.setCodeMsg(MsgCons.C_50000, MsgCons.M_50000);
			apiResult.setResult("服务器出错！");
			return JSONObject.toJSONString(apiResult);
		}
		
		//总记录数验证
		int total = remoteApiResult.getResult();
		if(total <= 0){
			return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_29005, MsgCons.M_29005));
		}
		else if(total > EXPORT_MAX_SIZE){
			return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_29006, MsgCons.M_29006));
		}
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 导出单个规则的详细信息
	 */
	@RequestMapping("exportDetail")
	public void exportDetail(HttpServletRequest request, HttpServletResponse response){
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		
		RuleInfoDTO rule = ruleInfoService.getById(Integer.parseInt(paramMap.get("id").toString())).getResult();
		
		OutputStream ouputStream = null;
		try{
			String fileName = rule.getName()+"_"+DateUtil.toString(new Date(), "yyyy-MM-dd_HH:mm:ss");
			 // 设置输出响应头信息，
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls");
            ouputStream = response.getOutputStream();
            
            WritableWorkbook wwb = Workbook.createWorkbook(ouputStream);
            if(wwb == null){
            	return;
            }
            
            // 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
            WritableSheet sheet = wwb.createSheet("货源规则管理", 0);
            
            String[] headers = {"规则名称","所在城市","货源类别", "公司/车主", "公司/车主手机", "用户类型", "优先级", "总分配上限", "日分配上限", "分配开始时间" , "分配结束时间", "已使用日配额", "已使用总配额"};
            for(int i = 0, len = headers.length; i < len; i++){
            	 sheet.addCell(new Label(i, 0, headers[i]));
            }
            
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = ruleLogisticService.queryRuleLogisticByRuleIdTotal(paramMap);
            int totalCount = 0;
            if(countApiResult != null){
            	totalCount = countApiResult.getResult();
            }
            // 计算分几次查询导出数据
            int exportCount = totalCount / EXPORT_PAGE_SIZE;
            if((totalCount % EXPORT_PAGE_SIZE) != 0){
            	exportCount++;
            }
            int startRow = 0;
    		for(int i = 0; i < exportCount; i++){
            	// 查询分页数据
            	paramMap.put("startRow", startRow);
            	paramMap.put("endRow", EXPORT_PAGE_SIZE);
            	ApiResult<List<RuleLogisticDTO>> apiResult = ruleLogisticService.getMemberInfoByRuleIdPage(paramMap);
            	List<RuleLogisticDTO> list = null;
            	if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
            	for(int j = 0, len = list.size(); j < len; j++){
            		RuleLogisticDTO dto = list.get(j);
                	sheet.addCell(new Label(0, j+1+startRow, rule.getName().toString()));
                	sheet.addCell(new Label(1, j+1+startRow, rule.getSourceAddress()));
                	sheet.addCell(new Label(2, j+1+startRow, rule.getSourceTypeStr()));
                    sheet.addCell(new Label(3, j+1+startRow, dto.getUserName()));
                    sheet.addCell(new Label(4, j+1+startRow, dto.getMobile()));
                    sheet.addCell(new Label(5, j+1+startRow, dto.getMemberTypeStr()));
                    sheet.addCell(new Label(6, j+1+startRow, dto.getLevel()+""));
                    sheet.addCell(new Label(7, j+1+startRow, dto.getTotalLimt()+""));
                    sheet.addCell(new Label(8, j+1+startRow, dto.getDayLimt()+""));
                    sheet.addCell(new Label(9, j+1+startRow, dto.getStartTimeStr()));
                    sheet.addCell(new Label(10, j+1+startRow, dto.getEndTimeStr()));
                    sheet.addCell(new Label(11, j+1+startRow, dto.getDayCount()+""));
                    sheet.addCell(new Label(12, j+1+startRow, dto.getTotalCount()+""));
                }
            	startRow += EXPORT_PAGE_SIZE;
    		}
            wwb.write();
            wwb.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
            try {
                ouputStream.flush();
                ouputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 启用禁用规则
	 */
	@RequestMapping(value="resetOnOffById")
	@ResponseBody
	public String resetOnOffById(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		if("1".equals(paramMap.get("onOff"))){
			paramMap.put("onTime", DateUtil.getSysDateTimeString());
		}
		if("2".equals(paramMap.get("onOff"))){
			paramMap.put("offTime", DateUtil.getSysDateTimeString());
		}
		String updateUserId = getUser(request).getUserID();
		paramMap.put("updateUserId", updateUserId);
		ApiResult<Integer> result = ruleInfoService.update(paramMap);
		if(result.getCode() == 10000){
			ruleLogisticService.updateOnOffBatch(paramMap);
		}
		return JSONObject.toJSONString(result);
	}
	
	
	@RequestMapping(value="validayRuleName",produces="application/json;charset=utf-8")
	@ResponseBody
	public String validayRuleName(HttpServletRequest request){
		ApiResult<String> apiResult = new ApiResult<String>();
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
				
		ApiResult<Integer> remoteApiResult = ruleInfoService.validateRuleName(paramMap);
		apiResult.setResult(remoteApiResult.getResult().toString());
		return JSONObject.toJSONString(apiResult);
	}
}
 
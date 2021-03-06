package cn.gdeng.nst.admin.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminOrderBeforeDTO;
import cn.gdeng.nst.admin.dto.admin.AdminOrderDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdminSourceAssignHisDTO;
import cn.gdeng.nst.admin.dto.admin.AdminSourceShipperDTO;
import cn.gdeng.nst.admin.service.admin.OrderAgentService;
import cn.gdeng.nst.admin.service.admin.OrderBeforeService;
import cn.gdeng.nst.admin.service.admin.OrderInfoService;
import cn.gdeng.nst.admin.service.admin.SourceAssignHisService;
import cn.gdeng.nst.admin.service.admin.SourceShipperService;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.CommonUtil;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 货源管理
 * @author dengjianfeng
 *
 */
@Controller
@RequestMapping("sourceShipper")
public class SourceShipperController extends AdminBaseController{

	@Reference
	private SourceShipperService sourceShipperService;
	
	@Reference
	private SourceAssignHisService sourceAssignService;
	
	@Reference
	private OrderBeforeService orderBeforeService;
	
	@Reference
	private OrderInfoService orderInfoService;
	
	@Reference
	private OrderAgentService orderAgentService;

	@RequestMapping("index")
	public String index(){
		return "sourceShipper/list";
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		formatParamTime(paramMap);
		
		ApiResult<AdminPageDTO> apiResult = sourceShipperService.queryPage(paramMap);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * 进入编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer id){
		ApiResult<AdminSourceShipperDTO> apiResult = sourceShipperService.getById(id);
		if(apiResult != null){
			putModel("sourceShipperDTO", apiResult.getResult());
		}
		putModel("sourceId", id);
		return "sourceShipper/detail";
	}
	
	/**
	 * 保存编辑信息
	 * @param sourceShipperDTO
	 * @param request
	 * @return
	 */
	@RequestMapping("saveEdit")
	@ResponseBody
	public String saveEdit(AdminSourceShipperDTO sourceShipperDTO, HttpServletRequest request){
		//是否登录验证
		String validateLogin = validateLogin(request);
		if(validateLogin != null){
			return validateLogin;
		}
		
		sourceShipperDTO.setUpdateUserId(getUser(request).getUserID());
		ApiResult<Integer> apiResult = sourceShipperService.update(sourceShipperDTO);
		if(apiResult == null){
			apiResult = new ApiResult<Integer>(0, MsgCons.C_50000, MsgCons.M_50000);
		}
		return JSONObject.toJSONString(apiResult);
	}

	/**
	 * 进入详情页面
	 * @param sourceId
	 * @return
	 */
	@RequestMapping("detail/{sourceId}")
	public String detail(@PathVariable("sourceId") Integer sourceId){
		ApiResult<AdminSourceShipperDTO> apiResult = sourceShipperService.getById(sourceId);
		if(apiResult != null){
			putModel("sourceShipperDTO", apiResult.getResult());
		}
		putModel("sourceId", sourceId);
		return "sourceShipper/detail";
	}
	
	/**
	 * 删除（批量删除）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="delete",produces="application/json;charset=utf-8")
	@ResponseBody
	public String delete(HttpServletRequest request){
		String validateResult = validateLogin(request);
		if(validateResult != null){
			return validateResult;
		}
		
		String idsStr = request.getParameter("ids");
		String[] ids = idsStr.split(",");
		ApiResult<int[]> apiResult = sourceShipperService.batchDelete(ids, getUser(request).getUserID());
		return JSONObject.toJSONString(apiResult);
	}
	
	/**
	 * 货源分配记录
	 * @param sourceId
	 * @return
	 */
	@RequestMapping("assignHistoryList/{sourceId}")
	@ResponseBody
	public String assignHistoryList(@PathVariable("sourceId") Integer sourceId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sourceId", sourceId);
		ApiResult<List<AdminSourceAssignHisDTO>> apiResult = sourceAssignService.queryList(paramMap);
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(apiResult != null){
			resultMap.put("rows", apiResult.getResult());
		}else{
			resultMap.put("rows", Collections.EMPTY_LIST);
		}
		return JSONObject.toJSONString(resultMap, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 货源接单记录
	 * @param sourceId
	 * @return
	 */
	@RequestMapping("orderBeforeList/{sourceId}")
	@ResponseBody
	public String orderBeforeList(@PathVariable("sourceId") Integer sourceId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sourceId", sourceId);
		ApiResult<List<AdminOrderBeforeDTO>> apiResult = orderBeforeService.queryList(paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(apiResult != null){
			resultMap.put("rows", apiResult.getResult());
		}else{
			resultMap.put("rows", Collections.EMPTY_LIST);
		}
		return JSONObject.toJSONString(resultMap, SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 订单信息
	 * @param sourceId
	 * @return
	 */
	@RequestMapping("orderList/{sourceId}")
	@ResponseBody
	public String orderList(@PathVariable("sourceId") Integer sourceId){
		List<AdminOrderDTO> list = new ArrayList<AdminOrderDTO>(2);
		//货运订单
		ApiResult<List<AdminOrderDTO>> orderInfoApiResult = orderInfoService.getAdminOrderDTOBySourceId(sourceId);
		if(orderInfoApiResult != null && orderInfoApiResult.getResult() != null){
			list.addAll(orderInfoApiResult.getResult());
		}
		//信息订单
		ApiResult<List<AdminOrderDTO>> orderAgentApiResult = orderAgentService.getAdminOrderDTOBySourceId(sourceId);
		if(orderAgentApiResult != null && orderAgentApiResult.getResult() != null){
			list.addAll(orderAgentApiResult.getResult());
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", list);
		return JSONObject.toJSONString(resultMap, SerializerFeature.WriteDateUseDateFormat);
		
	}
	
	/**
	 * 验证登录信息是否存在
	 * @param request
	 * @return
	 */
	private String validateLogin(HttpServletRequest request){
		SysRegisterUser registerUser = getUser(request);
		if(registerUser == null){
			ApiResult<String> apiResult = new ApiResult<String>("登录超时,请重新登录", MsgCons.C_30000,  MsgCons.M_30000);
			return JSONObject.toJSONString(apiResult); 
		}
		return null;
	}
	
	/**
	 * 导出前检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportCheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public String exportCheck(HttpServletRequest request){
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		formatParamTime(paramMap);
		ApiResult<Integer> apiResult = sourceShipperService.countTotal(paramMap);
		if(apiResult != null && apiResult.getResult() != null){
			int total = apiResult.getResult();
			if(total <= 0){
				return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_29005, MsgCons.M_29005));
			}
			else if(total > EXPORT_MAX_SIZE){
				return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_29006, MsgCons.M_29006));
			}
		} else {
			return JSONObject.toJSONString(new ApiResult<String>().withError(MsgCons.C_50000, MsgCons.M_50000));
		}
		return JSONObject.toJSONString(new ApiResult<String>());
	}
	
	/**
	 * 导出数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response){
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		formatParamTime(paramMap);
		paramMap.put(END_ROW, EXPORT_PAGE_SIZE);
		
		OutputStream ouputStream = null;
		try{
			String fileName = "货源列表" + DateUtil.toString(new Date(), "yyyy-MM-dd_HHmmss");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xlsx");
			ouputStream = response.getOutputStream();
			
			SXSSFWorkbook workbook = new SXSSFWorkbook();
            String[] headers = {"货源ID", "发布人姓名", "发布人手机", "发布时间", "分配公司/车主名称", 
            		"分配公司/车主手机", "货源类型", "线路类型", "发货地", "目的地", "货物类型", "货物重量",
            		"发货方式", "发布来源", "司机接单次数", "货源状态", "货源删除状态"};
            
			Sheet sheet = createSheet(workbook, headers);
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = sourceShipperService.countTotal(paramMap);
            int totalCount = 0;
            if(countApiResult != null){
            	totalCount = countApiResult.getResult();
            }
            // 计算分几次查询导出数据
            int exportCount = (totalCount / EXPORT_PAGE_SIZE) + 1;
            
            int rowNum = 1;
            for(int i = 0; i < exportCount; i++){
            	// 查询分页数据
            	paramMap.put(START_ROW, (i * EXPORT_PAGE_SIZE));
	            ApiResult<List<AdminSourceShipperDTO>> apiResult = sourceShipperService.queryListByPage(paramMap);
	    		List<AdminSourceShipperDTO> list = null;
	    		if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
	            for(int j = 0, len = list.size(); j < len; j++){
                   	AdminSourceShipperDTO dto = list.get(j);
                   	writeRowData(sheet, rowNum, dto);
                    rowNum++;
               	}
            }
            workbook.write(ouputStream);
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
	 * 创建一个sheet，并在第一行写入表头
	 * @param workbook
	 * @param sheetNum
	 * @param headers
	 * @return
	 */
	private Sheet createSheet(SXSSFWorkbook workbook, String[] headers){
		String sheetName = "货源数据";
		Sheet sheet = workbook.createSheet(sheetName);
		Row row = sheet.createRow(0);
        for(int i = 0, len = headers.length; i < len; i++){
        	row.createCell(i).setCellValue(headers[i]);
        }
        return sheet;
	}
	
	/**
	 * 填充dto字段到row
	 * @param sheet
	 * @param rowNum
	 * @param dto
	 */
	private void writeRowData(Sheet sheet, int rowNum, AdminSourceShipperDTO dto){
		Row row = sheet.createRow(rowNum);
		
		String sourceId = dto.getId()== null ? "" : dto.getId().toString();
		String memberName = dto.getMemberName() == null ? "" : dto.getMemberName();
		String memberMobile = dto.getMemberMobile() == null ? "" : dto.getMemberMobile();
		String createTime = dto.getCreateTime() == null ? "" : DateUtil.toString(dto.getCreateTime(), DateUtil.DATE_FORMAT_DATETIME);
		String assignName = dto.getLogisticName() == null ? "" : dto.getLogisticName();
		String assignMobile = dto.getLogisticMobile() == null ? "" : dto.getLogisticMobile();
		String sourceGoodsType = dto.getSourceGoodsTypeStr() == null ? "" : dto.getSourceGoodsTypeStr();
		String sourceType = dto.getSourceTypeStr() == null ? "" : dto.getSourceTypeStr();
		String sDetail = dto.getSDetailStr() == null ? "" : dto.getSDetailStr();
		String eDetail = dto.getEDetailStr() == null ? "" : dto.getEDetailStr();
		String goodsType = dto.getGoodsTypeStr() == null ? "" : dto.getGoodsTypeStr();
		String totalWeight = dto.getTotalWeight() == null ? "" : (dto.getTotalWeight().toString() + "吨");
		String sendGoodsType = dto.getSendGoodsTypeStr() == null ? "" : dto.getSendGoodsTypeStr();
		String client = dto.getClientsStr() == null ? "" : dto.getClientsStr();
		String orderBeforeCount = dto.getOrderBeforeCount() == null ? "0" : dto.getOrderBeforeCount().toString();
		String sourceStatus = dto.getSourceStatusStr() == null ? "" : dto.getSourceStatusStr();
		String isDeleted = "";
		if (dto.getIsDeleted() != null) {
			if (dto.getIsDeleted().byteValue() == 0) {
				isDeleted = "未删除";
			} else if (dto.getIsDeleted().byteValue() == 1) {
				isDeleted = "已删除";
			}
		}
		
		String[] data = {sourceId, memberName, memberMobile, createTime, assignName, assignMobile,
				sourceGoodsType, sourceType, sDetail, eDetail, goodsType, sendGoodsType, totalWeight, client, 
				orderBeforeCount, sourceStatus, isDeleted};
		for (int i = 0, len = data.length; i < len; i++) {
			row.createCell(i).setCellValue(data[i]);
		}
	}
	
	/**
	 * 查询时间参数格式化：开始时间为yyyy-MM-dd 00:00:00;结束时间为yyyy-MM-dd 23:59:59
	 * @param paramMap
	 */
	private void formatParamTime(Map<String, Object> paramMap) {
		String startDate = (String) paramMap.get("startDate");
		String endDate = (String) paramMap.get("endDate");
		if(StringUtils.isNotBlank(startDate)){
			String startTime = CommonUtil.getStartOfDay(DateUtil.formateDate(startDate));
			paramMap.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endDate)){
			String endTime = CommonUtil.getEndOfDay(DateUtil.formateDate(endDate));
			paramMap.put("endTime", endTime);
		}
	}
}

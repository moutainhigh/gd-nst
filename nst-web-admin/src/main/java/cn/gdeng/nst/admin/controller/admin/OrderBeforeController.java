package cn.gdeng.nst.admin.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminOrderBeforeDTO;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.service.admin.OrderBeforeService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.CommonUtil;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 接单记录
 * @author dengjianfeng
 *
 */
@Controller
@RequestMapping("orderBefore")
public class OrderBeforeController extends AdminBaseController{

	@Reference
	private OrderBeforeService orderBeforeService;
	
	/**
	 * 进入接单记录列表页
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "orderBefore/list";
	}
	
	/**
	 * 接单记录分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request); 
		// 分页参数处理
		setCommParameters(request, paramMap);
		// 查询时间处理
		formatDateParams(request, paramMap);
	
		ApiResult<AdminPageDTO> apiResult = orderBeforeService.queryPage(paramMap);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * 数据导出检测
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportCheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public String exportCheck(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		// 查询时间处理
		formatDateParams(request, paramMap);
		
		ApiResult<Integer> apiResult = orderBeforeService.countTotal(paramMap);
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
	 * 数据导出
	 * @param request
	 * @param response
	 */
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response){
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		// 查询时间处理
		formatDateParams(request, paramMap);	
		paramMap.put(END_ROW, EXPORT_PAGE_SIZE);
		
		OutputStream ouputStream = null;
		try{
			String fileName = "接单列表"+DateUtil.toString(new Date(), "yyyy-MM-dd_HHmmss");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xlsx");
            ouputStream = response.getOutputStream();
            
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			String[] headers = {"货源ID", "发布人姓名", "发布人手机","发布人注册来源", "发货地", "目的地", 
					"线路类型", "重量", "发布时间", "接单时间", "车主姓名", "车主手机", "信息订单号", "接单处理状态",
					"信息订单状态", "货运订单号"};
            
			Sheet sheet = createSheet(workbook, headers);
            
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = orderBeforeService.countTotal(paramMap);
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
            	ApiResult<List<AdminOrderBeforeDTO>> apiResult = orderBeforeService.queryListByPage(paramMap);
            	List<AdminOrderBeforeDTO> list = null;
            	if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
	            for(int j = 0, len = list.size(); j < len; j++){
                   	AdminOrderBeforeDTO dto = list.get(j);
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
		String sheetName = "接单记录统计数据";
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
	private void writeRowData(Sheet sheet, int rowNum, AdminOrderBeforeDTO dto){
		Row row = sheet.createRow(rowNum);
		
		String sourceId = dto.getSourceId() == null ? "" : dto.getSourceId().toString();
		String shipperName = dto.getShipperName() == null ? "" : dto.getShipperName();
		String shipperMobile = dto.getShipperMobile() == null ? "" : dto.getShipperMobile();
		String regeType = dto.getRegeTypeStr() == null ? "" : dto.getRegeTypeStr();
		String sDetail = dto.getSDetailStr() == null ? "" : dto.getSDetailStr();
		String eDetail = dto.getEDetailStr() == null ? "" : dto.getEDetailStr();
		String sourceType = dto.getSourceTypeStr() == null ? "" : dto.getSourceTypeStr();
		String totalWeight = dto.getTotalWeight() == null ? "" : (dto.getTotalWeight().toString() + "吨");
		String releaseTime = dto.getReleaseTime() == null ? "" : DateUtil.toString(dto.getReleaseTime(), DateUtil.DATE_FORMAT_DATETIME);
		String createTime = dto.getCreateTime() == null ? "" : DateUtil.toString(dto.getCreateTime(),  DateUtil.DATE_FORMAT_DATETIME);
		String driverName = dto.getDriverName() == null ? "" : dto.getDriverName();
		String driverMobile = dto.getDriverMobile() == null ? "" : dto.getDriverMobile();
		String orderAgentNo = dto.getOrderAgentNo() == null ? "" : dto.getOrderAgentNo();
		String sourceStatus = dto.getSourceStatusStr() == null ? "" : dto.getSourceStatusStr();
		String orderAgentStatus = dto.getOrderAgentStatusStr() == null ? "" : dto.getOrderAgentStatusStr();
		String orderInfoNo = dto.getOrderInfoNo() == null ? "" : dto.getOrderInfoNo();
		
		String[] data = {sourceId, shipperName, shipperMobile, regeType, sDetail, eDetail, sourceType,
				totalWeight, releaseTime, createTime, driverName, driverMobile, orderAgentNo, sourceStatus,
				orderAgentStatus, orderInfoNo};
		for (int i =0, len = data.length; i < len; i++) {
			row.createCell(i).setCellValue(data[i]);
		}
	}
	
	/**
	 * 查询参数时间格式化：开始时间增加00:00:00， 接受时间增加23:59:59
	 * @param request
	 * @param paramMap
	 */
	private void formatDateParams(HttpServletRequest request, Map<String, Object> paramMap){
		// 发布时间
		String releaseStartDate = request.getParameter("releaseStartDate");
		String releaseEndDate = request.getParameter("releaseEndDate");
		if(StringUtils.isNotBlank(releaseStartDate)){
			paramMap.put("releaseStartTime", CommonUtil.getStartOfDay(DateUtil.formateDate(releaseStartDate)));
		}
		if(StringUtils.isNotBlank(releaseEndDate)){
			paramMap.put("releaseEndTime", CommonUtil.getEndOfDay(DateUtil.formateDate(releaseEndDate)));
		}
		// 接受时间
		String acceptStartDate = request.getParameter("acceptStartDate");
		String acceptEndDate = request.getParameter("acceptEndDate");
		if(StringUtils.isNotBlank(acceptStartDate)){
			paramMap.put("acceptStartTime", CommonUtil.getStartOfDay(DateUtil.formateDate(acceptStartDate)));
		}
		if(StringUtils.isNotBlank(acceptEndDate)){
			paramMap.put("acceptEndTime", CommonUtil.getEndOfDay(DateUtil.formateDate(acceptEndDate)));
		}
		// 确认时间
		String confirmStartDate = request.getParameter("confirmStartDate");
		String confirmEndDate = request.getParameter("confirmEndDate");
		if(StringUtils.isNotBlank(confirmStartDate)){
			paramMap.put("confirmStartTime", CommonUtil.getStartOfDay(DateUtil.formateDate(confirmStartDate)));
		}
		if(StringUtils.isNotBlank(confirmEndDate)){
			paramMap.put("confirmEndTime", CommonUtil.getEndOfDay(DateUtil.formateDate(confirmEndDate)));
		}
	}
}

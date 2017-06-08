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

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.AdminSourceAssignHisDTO;
import cn.gdeng.nst.admin.service.admin.SourceAssignHisService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.CommonUtil;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

/**
 * 分配记录
 * @author dengjianfeng
 */
@Controller
@RequestMapping("sourceAssignHis")
public class SourceAssignHisController extends AdminBaseController{

	@Reference
	private SourceAssignHisService sourceAssignHisService;
	
	@RequestMapping("index")
	public String index(){
		return "sourceAssignHis/list";
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
		formatDateParams(paramMap);
		
		ApiResult<AdminPageDTO> apiResult = sourceAssignHisService.queryPage(paramMap);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(), SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * 导出检测
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportCheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public String exportCheck(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		formatDateParams(paramMap);
		
		ApiResult<Integer> apiResult = sourceAssignHisService.countTotal(paramMap);
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
		formatDateParams(paramMap);	
		paramMap.put(END_ROW, EXPORT_PAGE_SIZE);
		
		OutputStream ouputStream = null;
		try{
			String fileName = "分配记录列表"+DateUtil.toString(new Date(), "yyyy-MM-dd_HHmmss");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xlsx");
            ouputStream = response.getOutputStream();
            
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			String[] headers = {"货源ID", "发布人姓名", "发布人手机", "发布时间", "货源类型", "分配公司/车主", "分配公司/车主手机", 
					"货物类型", "货物重量", "线路类型",  "发货地", "目的地", "发布来源", "分配时间", "分配处理状态"};
            
			Sheet sheet = createSheet(workbook, headers);
            
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = sourceAssignHisService.countTotal(paramMap);
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
            	ApiResult<List<AdminSourceAssignHisDTO>> apiResult = sourceAssignHisService.queryListByPage(paramMap);
            	List<AdminSourceAssignHisDTO> list = null;
            	if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
	            for(int j = 0, len = list.size(); j < len; j++){
	            	AdminSourceAssignHisDTO dto = list.get(j);
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
		String sheetName = "分配记录";
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
	private void writeRowData(Sheet sheet, int rowNum, AdminSourceAssignHisDTO dto){
		Row row = sheet.createRow(rowNum);
		
		String sourceId = dto.getSourceId() == null ? "" : dto.getSourceId().toString();
		String sourceMemberName = dto.getSourceMemberName() == null ? "" : dto.getSourceMemberName();
		String sourceMemberMobile = dto.getSourceMemberMobile() == null ? "" : dto.getSourceMemberMobile();
		String sourceCreateTime = dto.getSourceCreateTime() == null ? "" : DateUtil.toString(dto.getSourceCreateTime(), DateUtil.DATE_FORMAT_DATETIME);
		String nstRule = dto.getNstRuleStr() == null ? "" : dto.getNstRuleStr();
		String assignName = dto.getAssignName() == null ? "" : dto.getAssignName();
		String assignMobile = dto.getAssignMobile() == null ? "" : dto.getAssignMobile();
		String goodsType = dto.getGoodsTypeStr() == null ? "" : dto.getGoodsTypeStr();
		String totalWeight = dto.getTotalWeight() == null ? "" : (dto.getTotalWeight()+"吨");
		String sourceType = dto.getSourceTypeStr() == null ? "" : dto.getSourceTypeStr();
		String sDetail = dto.getSDetail() == null ? "" : dto.getSDetail();
		String eDetail = dto.getEDetail() == null ? "" : dto.getEDetail();
		String clients = dto.getClientsStr() == null ? "" : dto.getClientsStr();
		String createTime = dto.getCreateTime() == null ? "" : DateUtil.toString(dto.getCreateTime(), DateUtil.DATE_FORMAT_DATETIME);
		String status = dto.getStatusStr() == null ? "" : dto.getStatusStr();
		
		String[] data = {sourceId, sourceMemberName, sourceMemberMobile, sourceCreateTime, nstRule, assignName, assignMobile, goodsType,
				totalWeight, sourceType, sDetail, eDetail, clients, createTime, status};
		for (int i = 0, len = data.length; i < len; i++) {
			row.createCell(i).setCellValue(data[i]);
		}
	}
	
	/**
	 * 查询参数时间格式化：开始时间增加00:00:00， 接受时间增加23:59:59
	 * @param request
	 * @param paramMap
	 */
	private void formatDateParams(Map<String, Object> paramMap) {
		String sourceStartDate = (String) paramMap.get("sourceStartDate");
		String sourceStartEnd = (String) paramMap.get("sourceStartEnd");
		String assignStartDate = (String) paramMap.get("assignStartDate");
		String assignEndDate = (String) paramMap.get("assignEndDate");
		
		if(StringUtils.isNotBlank(sourceStartDate)){
			paramMap.put("sourceStartTime", CommonUtil.getStartOfDay(DateUtil.formateDate(sourceStartDate)));
		}
		if(StringUtils.isNotBlank(sourceStartEnd)){
			paramMap.put("sourceEndTime", CommonUtil.getEndOfDay(DateUtil.formateDate(sourceStartEnd)));
		}
		
		if(StringUtils.isNotBlank(assignStartDate)){
			paramMap.put("assignStartTime", CommonUtil.getStartOfDay(DateUtil.formateDate(assignStartDate)));
		}
		if(StringUtils.isNotBlank(assignEndDate)){
			paramMap.put("assignEndTime", CommonUtil.getEndOfDay(DateUtil.formateDate(assignEndDate)));
		}
	}
}

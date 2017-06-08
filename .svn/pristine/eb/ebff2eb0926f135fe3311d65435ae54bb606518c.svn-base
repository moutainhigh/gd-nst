package cn.gdeng.nst.admin.controller.admin;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.CallstatisticsDTO;
import cn.gdeng.nst.admin.service.admin.CallstatisticsService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.CommonUtil;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 拨打电话统计
 * @author wj
 *
 */
@Controller
@RequestMapping("call")
public class CallstatisticsController extends AdminBaseController{
	
	@Reference
	public CallstatisticsService callstatisticsService;

	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		return "callstatistics/list";
	}
	
	
	/**
	 * 默认查询和id条件查询结合
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("pageQuery")
	@ResponseBody
	public String pageQuery(HttpServletRequest request){
		Map<String, Object> map = getParametersMap(request);
		//设定分页,排序
		setCommParameters(request, map);
		String createTimeStart = request.getParameter("createTimeStart");
		String createTimeEnd = request.getParameter("createTimeEnd");
		if(StringUtils.isNotBlank(createTimeStart)){
			String startTime = CommonUtil.getStartOfDay(DateUtil.formateDate(createTimeStart));
			map.put("createTimeStart", startTime);
		}
		if(StringUtils.isNotBlank(createTimeEnd)){
			String endTime = CommonUtil.getEndOfDay(DateUtil.formateDate(createTimeEnd));
			map.put("createTimeEnd", endTime);
		}
		ApiResult<AdminPageDTO> result = callstatisticsService.pageQuery(map);
		return JSONObject.toJSONString(result.getResult(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	

	@RequestMapping(value="checkExportParams", produces="application/json;charset=utf-8")
	@ResponseBody
	public String checkExportParams(HttpServletRequest request){
		ApiResult<String> result = new ApiResult<String>();
		// 设置查询参数
		Map<String, Object> map = getParametersMap(request);
		String createTimeStart = request.getParameter("createTimeStart");
		String createTimeEnd = request.getParameter("createTimeEnd");
		if(StringUtils.isNotBlank(createTimeStart)){
			String startTime = CommonUtil.getStartOfDay(DateUtil.formateDate(createTimeStart));
			map.put("createTimeStart", startTime);
		}
		if(StringUtils.isNotBlank(createTimeEnd)){
			String endTime = CommonUtil.getEndOfDay(DateUtil.formateDate(createTimeEnd));
			map.put("createTimeEnd", endTime);
		}
		ApiResult<Integer>  remoteApiResult = callstatisticsService.getTotal(map);
		if(remoteApiResult == null || remoteApiResult.getResult() == null){
			result.setCodeMsg(MsgCons.C_50000, MsgCons.M_50000);
			result.setResult("服务器出错！");
			return JSONObject.toJSONString(result);
		}
		int total = remoteApiResult.getResult();
		if(total <= 0){
			return JSONObject.toJSONString(result.withError(MsgCons.C_29005, MsgCons.M_29005));
		}
		else if(total > EXPORT_MAX_SIZE){
			return JSONObject.toJSONString(result.withError(MsgCons.C_29006, MsgCons.M_29006));
		}
		return JSONObject.toJSONString(result);
	}

	
	@RequestMapping(value = "exportData")
	public void exportData(HttpServletRequest request) {
		Map<String, Object> map = getParametersMap(request);
		String createTimeStart = request.getParameter("createTimeStart");
		String createTimeEnd = request.getParameter("createTimeEnd");
		if(StringUtils.isNotBlank(createTimeStart)){
			String startTime = CommonUtil.getStartOfDay(DateUtil.formateDate(createTimeStart));
			map.put("createTimeStart", startTime);
		}
		if(StringUtils.isNotBlank(createTimeEnd)){
			String endTime = CommonUtil.getEndOfDay(DateUtil.formateDate(createTimeEnd));
			map.put("createTimeEnd", endTime);
		}

		OutputStream ouputStream = null;
		try {
			// 设置输出响应头信息，
			response.setContentType("application/vnd.ms-excel");
			String fileName = new String("拨打电话统计".getBytes(), "ISO-8859-1")+request.getParameter("createTimeStart")+"_"+request.getParameter("createTimeEnd");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			ouputStream = response.getOutputStream();
			// 在输出流中创建一个新的写入工作簿
			WritableWorkbook wwb = Workbook.createWorkbook(ouputStream);
            if(wwb == null){
            	return;
            }
			WritableSheet sheet = wwb.createSheet("用户信息", 0);// 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
			// 第一个参数表示列，第二个参数表示行
			Label label00 = new Label(0, 0, "拨打来源");// 填充第一行第二个单元格的内容
			Label label10 = new Label(1, 0, "业务类型");// 填充第一行第三个单元格的内容
			Label label20 = new Label(2, 0, "主叫角色");// 填充第一行第四个单元格的内容
			Label label30 = new Label(3, 0, "主叫姓名 ");// 
			Label label40 = new Label(4, 0, "主叫手机");// 
			Label label50 = new Label(5, 0, "主叫业务范围");// 填充第一行第五个单元格的内容
			Label label60 = new Label(6, 0, "被叫姓名");//
			Label label70 = new Label(7, 0, "被叫手机 ");//
			Label label80 = new Label(8, 0, "拨打时间");//
			sheet.addCell(label00);// 将单元格加入表格
			sheet.addCell(label10);
			sheet.addCell(label20);
			sheet.addCell(label30);
			sheet.addCell(label40);
			sheet.addCell(label50);
			sheet.addCell(label60);
			sheet.addCell(label70);
			sheet.addCell(label80);
			
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = callstatisticsService.getTotal(map);
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
    			map.put("startRow", startRow);
    			map.put("endRow", EXPORT_PAGE_SIZE);
            	ApiResult<List<CallstatisticsDTO>> apiResult = callstatisticsService.queryList(map);
            	List<CallstatisticsDTO> list = null;
            	if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
            
				/*** 循环添加数据到工作簿 ***/
				if (list != null && list.size() > 0) {
					for (int j = 0, lenght = list.size(); j < lenght; j++) {
						CallstatisticsDTO dto= list.get(j);
						Label label0 = new Label(0, j + 1 + startRow, dto.getSourceStr());
						Label label1 = new Label(1, j + 1 + startRow, "");
						Label label2 = new Label(2, j + 1 + startRow, String.valueOf(dto.getCallRoleStr()));
						Label label3 = new Label(3, j + 1 + startRow,  dto.getE_Name());
						Label label4 =  new Label(4, j + 1 + startRow,  dto.getE_Mobile());
						Label label5 = new Label(5, j + 1 + startRow, dto.getCallServiceType());
						Label label6 = new Label(6, j + 1 + startRow,  dto.getS_Name());
						Label label7 = new Label(7, j + 1 + startRow,  dto.getS_Mobile());
						Label label8 = new Label(8, j + 1 + startRow,  DateUtil.toString(dto.getCreateTime(), DateUtil.DATE_FORMAT_DATETIME));
						sheet.addCell(label0);//将单元格加入表格
						sheet.addCell(label1);
						sheet.addCell(label2);
						sheet.addCell(label3);
						sheet.addCell(label4);
						sheet.addCell(label5);
						sheet.addCell(label6);
						sheet.addCell(label7);
						sheet.addCell(label8);
					}
				}
				startRow += EXPORT_PAGE_SIZE;
    		}
			wwb.write();// 将数据写入工作簿
			wwb.close();// 关闭				
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				ouputStream.flush();
				ouputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

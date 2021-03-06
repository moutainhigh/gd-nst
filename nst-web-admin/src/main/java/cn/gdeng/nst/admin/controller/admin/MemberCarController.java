package cn.gdeng.nst.admin.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberCarDTO;
import cn.gdeng.nst.admin.service.admin.MemberCarManageService;
import cn.gdeng.nst.util.server.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
@Controller      
@RequestMapping("memberCar")
public class MemberCarController extends AdminBaseController{
	
	@Reference
	private MemberCarManageService memberCarManageService;

	@RequestMapping("index")
	public String index(){
		return "memberCar/memberCarList";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		ApiResult<AdminPageDTO> apiResult = memberCarManageService.queryPage(paramMap);
		return JSONObject.toJSONString(apiResult.getResult(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	@RequestMapping("queryMemberCarDetail/{carId}")
	public String querymemberCarDetail(@PathVariable("carId") String persaleId, HttpServletRequest request,ModelMap map){
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("id", persaleId);
		MemberCarDTO memberCarDTO = memberCarManageService.getMemberCarDetail(newMap).getResult();
		map.put("CreateTime",  DateUtil.toString(memberCarDTO.getCreateTime(), DateUtil.DATE_FORMAT_DATETIME));
        map.put("memberCarDTO", memberCarDTO);
		return "memberCar/memberCarDetail";
	}
	
	@ResponseBody
	@RequestMapping(value="memberCarUpdate", produces="application/json;charset=utf-8")
	public Map<String, Object> memberCarUpdate(HttpServletRequest request,MemberCarDTO memberCarDTO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//String mnageUserName = super.getUser(request).getUserName();
		paramMap.put("carNumber", memberCarDTO.getCarNumber());
		paramMap.put("id", memberCarDTO.getId());
		int  reCount = memberCarManageService.queryMemberCarNumber(paramMap).getResult();
		if(reCount>0){
			paramMap.put("msg", "车牌号已存在");
			return paramMap;
		}
		paramMap.put("updateUserId", super.getUser(request).getUserID());
		paramMap.put("carType", memberCarDTO.getCarType());
		paramMap.put("carLength", memberCarDTO.getCarLength());
		paramMap.put("load", memberCarDTO.getLoad());
		paramMap.put("id", memberCarDTO.getId());
		int re = memberCarManageService.updateMemberCarById(paramMap).getResult();
		if(re>0){
		paramMap.put("msg", "success");
		}else{
	     paramMap.put("msg", "修改车辆信息失败");
		}
		return paramMap;
	
	}

	/**
	 * 
	 * @Description: 根据ID删除车辆
	 * @param request
	 * @return
	 *
	 */
	@RequestMapping(value = "deleteMemberCar", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String deleteMemberCar(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", request.getParameter("id"));
		paramMap.put("isDeleted", 1);
		ApiResult<Integer> apiResult = memberCarManageService.deleteMemberCarById(paramMap);
		return JSONObject.toJSONString(apiResult);

	}

	@RequestMapping(value = "checkExportParams", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String checkExportParams(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
	/*	String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if (DateUtil.isDateIntervalOverFlow(startDate, endDate, 31)){
			resultMap.put("status", 0);
			resultMap.put("message", "请选择正确的日期范围, 系统最大支持范围为31天..");
			return JSONObject.toJSONString(resultMap);
		}*/
		try{
			Map<String, Object> map = getParametersMap(request);
			// 设置查询参数
			map.put("startRow", 0);
			map.put("endRow", 100000000);
			int total = memberCarManageService.queryMemberCarCount(map).getResult();
				//	queryPage(map).getResult().getTotal();
			if (total ==0){
				resultMap.put("status", 0);
				resultMap.put("message", "查询没有符合的结果 ,请修改其他查询条件...");
				return JSONObject.toJSONString(resultMap);
			}
			if (total > EXPORT_MAX_SIZE){
				resultMap.put("status", 0);
				resultMap.put("message", "查询结果集太大(>"+EXPORT_MAX_SIZE+"条), 请缩减日期范围, 或者修改其他查询条件...");
				return JSONObject.toJSONString(resultMap);
			}
			resultMap.put("status", 1);
			resultMap.put("message", "参数检测通过");
		}catch(Exception e){
			resultMap.put("status", 0);
			resultMap.put("message", "参数检测异常");
		}
		return JSONObject.toJSONString(resultMap);
	}

	
	@RequestMapping(value = "exportData")
	public String exportData(HttpServletRequest request) {
		Map<String, Object> map = getParametersMap(request);
		// 设置查询参数
		WritableWorkbook wwb = null;
		OutputStream ouputStream = null;
		try {
			// 设置输出响应头信息，
			response.setContentType("application/vnd.ms-excel");
			String fileName = new String("车辆信息".getBytes(), "ISO-8859-1")+DateUtil.toString(new Date(), "yyyy-MM-dd_HH:mm:ss");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			ouputStream = response.getOutputStream();
			// 在输出流中创建一个新的写入工作簿
			wwb = Workbook.createWorkbook(ouputStream);
			if (wwb != null) {
				WritableSheet sheet = wwb.createSheet("车辆信息", 0);// 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
				// 第一个参数表示列，第二个参数表示行
				Label label00 = new Label(0, 0, "车牌号 ");// 填充第一行第二个单元格的内容
				Label label10 = new Label(1, 0, "车辆类型");// 填充第一行第三个单元格的内容
				Label label20 = new Label(2, 0, "车长(米)");// 填充第一行第四个单元格的内容
				Label label30 = new Label(3, 0, "载重(吨)");// 
				Label label40 = new Label(4, 0, "用户姓名");// 
				Label label50 = new Label(5, 0, "用户手机");// 填充第一行第五个单元格的内容
				Label label60 = new Label(6, 0, "业务范围");// 填充第一行第五个单元格的内容
				Label label70 = new Label(7, 0, "创建时间");//
				Label label80 = new Label(8, 0, "车辆状态");//
				sheet.addCell(label00);// 将单元格加入表格
				sheet.addCell(label10);
				sheet.addCell(label20);
				sheet.addCell(label30);
				sheet.addCell(label40);
				sheet.addCell(label50);
				sheet.addCell(label60);
				sheet.addCell(label70);
				sheet.addCell(label80);
				// 查询数据list
			    int	endRow=EXPORT_PAGE_SIZE;
				int totalCount = memberCarManageService.queryMemberCarCount(map).getResult();
			    int exportCount = totalCount/EXPORT_PAGE_SIZE;//总条数/每页显示的条数=总页数 
			    int mod = totalCount % EXPORT_PAGE_SIZE;
			    if(mod != 0){
			    	exportCount++; 	
			    } 
			    int	startRow=0;
			    map.put("startRow", startRow);
			    map.put("endRow", endRow);
			    for(int i=1;i<=exportCount;i++){
				List<MemberCarDTO> list = (List<MemberCarDTO>) memberCarManageService.queryPage(map).getResult().getRows();
				for (int j = 0; j < list.size(); j++) {
					MemberCarDTO memberCarDTO= list.get(j);
					Label label0 = new Label(0, j+startRow + 1, String.valueOf(memberCarDTO.getCarNumber()));
					Label label1 = new Label(1, j+startRow + 1, String.valueOf(memberCarDTO.getCarTypeStr()));
					Label label2 = new Label(2, j+startRow + 1, String.valueOf(memberCarDTO.getCarLength()));
					Label label3 = new Label(3, j+startRow+ 1, String.valueOf(memberCarDTO.getLoad()));
					Label label4 = new Label(4, j+startRow+ 1, String.valueOf(memberCarDTO.getUserName()));
					Label label5 = new Label(5, j+startRow + 1, String.valueOf(memberCarDTO.getMobile()));
					String serviceTyep="";
					if("1".equals(String.valueOf(memberCarDTO.getServiceType()))){
						serviceTyep="干线业务";
					}
					if("2".equals(String.valueOf(memberCarDTO.getServiceType()))){
						serviceTyep="同城业务";
					}
					Label label6 = new Label(6, j+startRow + 1, serviceTyep);
					Label label7 = new Label(7, j+startRow + 1,  DateUtil.toString(memberCarDTO.getCreateTime(),
							DateUtil.DATE_FORMAT_DATETIME));
					
					String isDeleted="";
					if("0".equals(String.valueOf(memberCarDTO.getIsDeleted()))){
							isDeleted = "使用中";
					}
					if("1".equals(String.valueOf(memberCarDTO.getIsDeleted()))){
						isDeleted="已删除";
					}
					Label label8 = new Label(8, j+startRow + 1, isDeleted);
					sheet.addCell(label0);// 
					sheet.addCell(label1);
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label4);
					sheet.addCell(label5);
					sheet.addCell(label6);
					sheet.addCell(label7);
					sheet.addCell(label8);
				}	
				startRow=(endRow*i);
				map.put("startRow", startRow);
				}
				wwb.write();// 将数据写入工作簿
			}
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
		return null;
	}
	
	
	
}

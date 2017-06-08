package cn.gdeng.nst.admin.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.MemberInfoDTO;
import cn.gdeng.nst.admin.dto.admin.RuleOnoffDTO;
import cn.gdeng.nst.admin.service.admin.AreaManageService;
import cn.gdeng.nst.admin.service.admin.MemberInfoManageService;
import cn.gdeng.nst.admin.service.admin.RuleOnoffManageService;
import cn.gdeng.nst.util.server.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.JavaMd5;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
@Controller
@RequestMapping("memberInfo")
public class MemberInfoController extends AdminBaseController{
	@Reference
	private MemberInfoManageService memberInfoManageService;
	@Reference
	private AreaManageService areaManageService;
	@Reference
	private RuleOnoffManageService ruleOnoffManageService;
	
	
	@RequestMapping("index")
	public String index(){
		return "memberInfo/memberList";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		setCommParameters(request, paramMap);
		ApiResult<AdminPageDTO> apiResult = memberInfoManageService.queryPage(paramMap);
		return JSONObject.toJSONString(apiResult.getResult(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	@RequestMapping("queryMemberInfoDetail/{memberId}")
	public String queryMemberInfoDetail(@PathVariable("memberId") String persaleId, HttpServletRequest request,ModelMap map){
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("memberId", persaleId);
		MemberInfoDTO memberInfoDTO = memberInfoManageService.getMemberInfoDetail(newMap).getResult();
		String province=null;
		String city=null;
		String area=null;
		if(null!=memberInfoDTO.getDetail()){
		String addr[]=memberInfoDTO.getDetail().split("/");
		if(addr.length==3){
			province=addr[0];
			city=addr[1];
			area=addr[2];
			map.put("province",province);
			map.put("city",city);
			map.put("area",area);
		}
		}
		if(null!=memberInfoDTO.getCreateTime()){
		map.put("createTime",  DateUtil.toString(memberInfoDTO.getCreateTime(), DateUtil.DATE_FORMAT_DATETIME));
		}
        map.put("memberInfoDTO", memberInfoDTO);
		return "memberInfo/memberDetail";
	}
	@ResponseBody
	@RequestMapping("queryMemberRuleOnoffDetail/{memberId}")
	public Map<String, Object>  queryMemberRuleOnoffDetail(@PathVariable("memberId") String memberId) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("memberId", memberId);
		RuleOnoffDTO ruleOnoffDTO = memberInfoManageService.getMemberRuleOnoffDTODetail(newMap).getResult();
		if(null==ruleOnoffDTO){
			newMap.put("OnOff", 1);
		}else{
		newMap.put("OnOff", ruleOnoffDTO.getOnOff().toString());}
	return newMap;
	}

	@ResponseBody
	@RequestMapping("queryProvince")
	public String queryProvince() {
	ApiResult<AdminPageDTO> apiResult =areaManageService.queryTopArea();
		return JSONObject.toJSONString(apiResult.getResult().getRows(),SerializerFeature.WriteDateUseDateFormat);
	}


	
	@ResponseBody
	@RequestMapping("queryChildArea/{provinceId}")
	public String queryChildArea(@PathVariable("provinceId") String provinceId) {
		ApiResult<AdminPageDTO> apiResult =areaManageService.listChildArea(provinceId);
		return JSONObject.toJSONString(apiResult.getResult().getRows(),SerializerFeature.WriteDateUseDateFormat);
	}
	
	@ResponseBody
	@RequestMapping("memberInfoUpdate")
	public Map<String, Object> memberInfoUpdate(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> paraMap = getParametersMap(request);
		String mnageUserId=super.getUser(request).getUserID();
		//String mnageUserName=super.getUser(request).getUserName();
		String province=(String)paraMap.get("province");
		String city=(String)paraMap.get("city");
		String area=(String)paraMap.get("area");
		paraMap.put("updateUserId", mnageUserId);
		paraMap.put("realName", paraMap.get("userName"));
		paramMap.put("msg", "success");
		try {
         memberInfoManageService.updateNstMemberInfoAndRuleOnoffAndAssign(paraMap,province,city,area).getResult();	
		} catch (Exception e) {
			paramMap.put("msg", "修改失败！");
			return paramMap;
		}
		Integer r=memberInfoManageService.updateUserNameAndMobile(paraMap).getResult();
		if(null==r){
			paramMap.put("msg", "用户手机用户名修改失败！");
		}
		return paramMap;
	}
	

	@RequestMapping(value="checkExportParams", produces="application/json;charset=utf-8")
	@ResponseBody
	public String checkExportParams(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		/*String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if (DateUtil.isDateIntervalOverFlow(startDate, endDate, 31)){
			resultMap.put("status", 0);
			resultMap.put("message", "请选择正确的日期范围, 系统最大支持范围为31天..");
			return JSONObject.toJSONString(resultMap);
		}*/
		try{
			// 设置查询参数
			Map<String, Object> map = getParametersMap(request);
			map.put("startRow", 0);
			map.put("endRow", 100000000);
			int total = memberInfoManageService.queryMemberInfoCount(map).getResult();
			if (total ==0){
				resultMap.put("status", 0);
				resultMap.put("message", "查询没有符合的结果 ,请修改其他查询条件...");
				return JSONObject.toJSONString(resultMap);
			}
			if (total > 50000){
				resultMap.put("status", 0);
				resultMap.put("message", "查询结果集太大(>50000条), 请缩减日期范围, 或者修改其他查询条件...");
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
		map.put("startRow", 0);
		map.put("endRow", 100000000);
		WritableWorkbook wwb = null;
		OutputStream ouputStream = null;
		try {
			// 设置输出响应头信息，
			response.setContentType("application/vnd.ms-excel");
			String fileName = new String("用户信息".getBytes(), "ISO-8859-1")+DateUtil.toString(new Date(), "yyyy-MM-dd_HH:mm:ss");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			ouputStream = response.getOutputStream();
			// 查询数据list
		//	List<MemberInfoDTO> list = (List<MemberInfoDTO>) memberInfoManageService.queryPage(map).getResult().getRows();
			// 在输出流中创建一个新的写入工作簿
			wwb = Workbook.createWorkbook(ouputStream);
			if (wwb != null) {
				WritableSheet sheet = wwb.createSheet("用户信息", 0);// 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
				// 第一个参数表示列，第二个参数表示行
				Label label00 = new Label(0, 0, "账号");// 填充第一行第一个单元格的内容
				Label label10 = new Label(1, 0, "注册来源 ");// 填充第一行第二个单元格的内容
				Label label20 = new Label(2, 0, "姓名");// 填充第一行第三个单元格的内容
				Label label30 = new Label(3, 0, "手机");// 填充第一行第四个单元格的内容
				Label label40 = new Label(4, 0, "注册时间 ");// 
				Label label50 = new Label(5, 0, "最后一次登录时间");// 
				Label label60 = new Label(6, 0, "个人认证");// 填充第一行第五个单元格的内容
				Label label70 = new Label(7, 0, "企业认证");//
				Label label80 = new Label(8, 0, "所在城市 ");//
				Label label90 = new Label(9, 0, "账号状态 ");//
				Label label010 = new Label(10, 0, "业务范围 ");//
				Label label011 = new Label(11, 0, "货源是否指派 ");//
				Label label012 = new Label(12, 0, "指派的公司/车主 ");//
				sheet.addCell(label00);// 将单元格加入表格
				sheet.addCell(label10);// 将单元格加入表格
				sheet.addCell(label20);
				sheet.addCell(label30);
				sheet.addCell(label40);
				sheet.addCell(label50);
				sheet.addCell(label60);
				sheet.addCell(label70);
				sheet.addCell(label80);
				sheet.addCell(label90);
				sheet.addCell(label010);
				sheet.addCell(label011);
				sheet.addCell(label012);
				/*** 循环添加数据到工作簿 ***/
				// 查询数据list
			    int	endRow=EXPORT_PAGE_SIZE;
				int totalCount = memberInfoManageService.queryMemberInfoCount(map).getResult();
			    int exportCount = totalCount/EXPORT_PAGE_SIZE;//总条数/每页显示的条数=总页数 
			    int mod = totalCount % EXPORT_PAGE_SIZE;
			    if(mod != 0){
			    	exportCount++; 	
			    } 
			    int	startRow=0;
			    map.put("startRow", startRow);
			    map.put("endRow", endRow);
			    for(int i=1;i<=exportCount;i++){
				List<MemberInfoDTO> list = (List<MemberInfoDTO>) memberInfoManageService.queryPage(map).getResult().getRows();
				for (int j = 0; j < list.size(); j++) {
					MemberInfoDTO memberInfoDTO= list.get(j);
					Label label0 = new Label(0,  j+startRow  + 1, String.valueOf(memberInfoDTO.getAccount()));
					Label label1 = new Label(1,  j+startRow  + 1, String.valueOf(memberInfoDTO.getRegetypeStr()));
					Label label2 = new Label(2,  j+startRow + 1, String.valueOf(memberInfoDTO.getUserName()));
					Label label3 = new Label(3,  j+startRow + 1, String.valueOf(memberInfoDTO.getMobile()));
					Label label4 = new Label(4,  j+startRow  + 1,  DateUtil.toString(memberInfoDTO.getCreateTime(),
							DateUtil.DATE_FORMAT_DATETIME));
					Label label5 =  new Label(5, j+startRow  + 1,  DateUtil.toString(memberInfoDTO.getLatestLoginTime(),
							DateUtil.DATE_FORMAT_DATETIME));
					Label label6 = new Label(6,  j+startRow + 1, String.valueOf(memberInfoDTO.getCerPersonalStatusStr()));
					Label label7 = new Label(7,  j+startRow + 1,  String.valueOf(memberInfoDTO.getCerCompanyStatusStr()));
					Label label8 = new Label(8,  j+startRow + 1,  memberInfoDTO.getAddressStr());
					String status="";
					if("0".equals(String.valueOf(memberInfoDTO.getStatus()))){
						status="禁用";
					}
					if("1".equals(String.valueOf(memberInfoDTO.getStatus()))){
						status="启用";
					}
					Label label9 = new Label(9, j+startRow  + 1,  status);
					String serviceTyep="";
					if("1".equals(String.valueOf(memberInfoDTO.getServiceType()))){
						serviceTyep="干线业务";
					}
					if("2".equals(String.valueOf(memberInfoDTO.getServiceType()))){
						serviceTyep="同城业务";
					}
					Label label0010 = new Label(10,  j+startRow  + 1,  serviceTyep);
					String appointName="";
					if("0".equals(String.valueOf(memberInfoDTO.getAppoint()))){
						appointName="是";
					}
					if("1".equals(String.valueOf(memberInfoDTO.getAppoint()))){
						appointName="否";
					}
					Label label0011 = new Label(11,  j+startRow  + 1,  appointName);
					Label label0012 = new Label(12,  j+startRow  + 1,String.valueOf(memberInfoDTO.getMemberIdLogisticName()));
					sheet.addCell(label0);//将单元格加入表格
					sheet.addCell(label1);// 
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label4);
					sheet.addCell(label5);
					sheet.addCell(label6);
					sheet.addCell(label7);
					sheet.addCell(label8);
					sheet.addCell(label9);
					sheet.addCell(label0010);
					sheet.addCell(label0011);
					sheet.addCell(label0012);
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
	
	
	
	
	@RequestMapping("resetStatusById")
	@ResponseBody
	public Map<String,Object> resetStatusById(Integer memberId,HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		String mnageUserId=super.getUser(request).getUserID();
		String mnageUserName=super.getUser(request).getUserName();
		String userName=request.getParameter("userName");
		String status=(String)paramMap.get("status");
		System.out.println(memberId+"  "+userName+"  "+status+"  ");
		Integer r=memberInfoManageService.resetStatusById(paramMap).getResult();
		paramMap.put("msg", "success");
		if(r==null){
		paramMap.put("msg", "服务异常,修改失败！");
		}
		return paramMap;
	}
	@ResponseBody
	@RequestMapping("resetPwdById")
	public Map<String,Object> resetPwdById(String memberId, HttpServletRequest request) {
		Map<String, Object> paramMap = getParametersMap(request);
		String mnageUserId=super.getUser(request).getUserID();
		String mnageUserName=super.getUser(request).getUserName();
		System.out.println(memberId+"   "+mnageUserId+"   "+mnageUserName);
		String password=JavaMd5.getMD5("888888"+"gudeng2015@*&^-") .toUpperCase();
		paramMap.put("password", password);
		Integer r=memberInfoManageService.resetPwdById(paramMap).getResult();
		paramMap.put("msg", "success");
		if(r==null){
		paramMap.put("msg", "服务异常,修改失败！");
		}
		return paramMap;
	}
	
	
	
}

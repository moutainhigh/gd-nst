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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.admin.controller.right.AdminBaseController;
import cn.gdeng.nst.admin.dto.admin.AdminPageDTO;
import cn.gdeng.nst.admin.dto.admin.NstNoticeEntityDTO;
import cn.gdeng.nst.admin.service.admin.NstNoticeService;
import cn.gdeng.nst.entity.nst.SysRegisterUser;
import cn.gdeng.nst.util.server.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gudeng.framework.core2.GdLogger;
import com.gudeng.framework.core2.GdLoggerFactory;
/**
 * 农速通controller
 * @author xiaojun
 */
@Controller
@RequestMapping("notice")
public class NstNoticeController extends AdminBaseController {
	  /** 记录日志 */
    @SuppressWarnings("unused")
    private static final GdLogger logger = GdLoggerFactory.getLogger(NstNoticeController.class);
    
    @Reference
    private NstNoticeService nstNoticeService;
    
    @RequestMapping("index")
    public String index(Model model){
    	return "notice/nstNoticeList";
    }
    
    @RequestMapping("addNstNotice")
    public String addNstNotice(){
    	return "notice/addNstNotice";
    }
    /**
     * 获取公告列表
     * @param request
     * @param nstDto
     * @return
     */
    @RequestMapping("getNstNoticeList")
    @ResponseBody
    public String getNstNoticeList(HttpServletRequest request,NstNoticeEntityDTO nstDto){
		Map<String, Object> map = getParametersMap(request);
		//设定分页,排序
		setCommParameters(request, map);
		ApiResult<AdminPageDTO> apiResult = nstNoticeService.queryPage(map);
		if(apiResult != null){
			return JSONObject.toJSONString(apiResult.getResult(),SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
    }
    
    /**
     * 保存公告
     * @param request
     * @param nstDto
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest request,NstNoticeEntityDTO nstDto) {
    	Map<String, Object> map = getParametersMap(request);
    	SysRegisterUser user = getUser(getRequest());
    	map.put("content",map.get("content").toString().replaceAll("\\r\\n", ""));
    	getMap(map, nstDto, user);
    	ApiResult<Integer> titleCount =nstNoticeService.queryTitleCount(map);
    	if(titleCount.getResult()>0){
    		map.put("msg", "公告名已存在！");
    	  	return map;
    	}
    	try {
			nstNoticeService.insert(map);
			map.put("msg", "success");
		} catch (Exception e) {
			map.put("msg", "添加公告异常");
		}
    	return map;
    }
    /**
     * 拼装map
     * @param map
     */
	private void getMap(Map<String, Object> map,NstNoticeEntityDTO nstDto,SysRegisterUser user) {
		// TODO Auto-generated method stub
	
		map.put("createuserId", user==null?"":user.getUserID());
		map.put("updateuserId", user==null?"":user.getUserID());
	}
	
	/**
     * 删除公告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        ApiResult<String> result = nstNoticeService.delete(id);
        return result.getResult();
    }
    
	/**
     * 修改公告
     * 
     * @param request
     * @param response
     * @return
     */
    
	@RequestMapping("editById/{id}")
    public String editById(@PathVariable("id") String id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
        NstNoticeEntityDTO dto = nstNoticeService.selectById(map).getResult();
        model.addAttribute("dto", dto);
        return "notice/editNstNotice";
    }
	
    /**
     * 保存更新公告
     * @param request
     * @param nstDto
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object>  update(HttpServletRequest request,NstNoticeEntityDTO nstDto) {
    	Map<String, Object> map = getParametersMap(request);
    	SysRegisterUser user = getUser(getRequest());
    	map.put("content",map.get("content").toString().replaceAll("\\r\\n", ""));
    	getMap(map, nstDto, user);
    	ApiResult<Integer> titleCount =nstNoticeService.queryTitleCount(map);
    	if(titleCount.getResult()>0){
    		map.put("msg", "公告名已存在！");
    	  	return map;
    	}
    	try {
    		nstNoticeService.update(map);
    		map.put("msg", "success");
		} catch (Exception e) {
			map.put("msg", "修改公告异常");
		}
    	return map;
    }
    
    
    @RequestMapping("resetStatusById")
	@ResponseBody
	public Map<String,Object> resetStatusById(Integer memberId,HttpServletRequest request){
		Map<String, Object> paramMap = getParametersMap(request);
		String mnageUserId=super.getUser(request).getUserID();
		paramMap.put("updateuserId",mnageUserId);
		Integer r=nstNoticeService.resetStatusById(paramMap).getResult();
		paramMap.put("msg", "success");
		if(r==null){
		paramMap.put("msg", "服务异常,修改失败！");
		}
		return paramMap;
	}

	@RequestMapping(value="checkExportParams", produces="application/json;charset=utf-8")
	@ResponseBody
	public String checkExportParams(HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			// 设置查询参数
			Map<String, Object> map = getParametersMap(request);
			int total = nstNoticeService.queryListCount(map).getResult();
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
			String fileName = new String("公告信息".getBytes(), "ISO-8859-1")+DateUtil.toString(new Date(), "yyyy-MM-dd_HH:mm:ss");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			ouputStream = response.getOutputStream();
			// 在输出流中创建一个新的写入工作簿
			wwb = Workbook.createWorkbook(ouputStream);
			if (wwb != null) {
				WritableSheet sheet = wwb.createSheet("用户信息", 0);// 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
				// 第一个参数表示列，第二个参数表示行
				Label label00 = new Label(0, 0, "公告名称");// 填充第一行第一个单元格的内容
				Label label10 = new Label(1, 0, "渠道");// 填充第一行第二个单元格的内容
				Label label20 = new Label(2, 0, "所在城市");// 填充第一行第三个单元格的内容
				Label label30 = new Label(3, 0, "创建人");// 填充第一行第四个单元格的内容
				Label label40 = new Label(4, 0, "发布时间 ");// 
				Label label50 = new Label(5, 0, "公告状态");// 
				sheet.addCell(label00);// 将单元格加入表格
				sheet.addCell(label10);// 将单元格加入表格
				sheet.addCell(label20);
				sheet.addCell(label30);
				sheet.addCell(label40);
				sheet.addCell(label50);
				/*** 循环添加数据到工作簿 ***/
				// 查询数据list
			    int	endRow=EXPORT_PAGE_SIZE;
				int totalCount = nstNoticeService.queryListCount(map).getResult();
			    int exportCount = totalCount/EXPORT_PAGE_SIZE;//总条数/每页显示的条数=总页数 
			    int mod = totalCount % EXPORT_PAGE_SIZE;
			    if(mod != 0){
			    	exportCount++; 	
			    } 
			    int	startRow=0;
			    map.put("startRow", startRow);
			    map.put("endRow", endRow);
			    for(int i=1;i<=exportCount;i++){
				List<NstNoticeEntityDTO> list = (List<NstNoticeEntityDTO>) nstNoticeService.queryPage(map).getResult().getRows();
				for (int j = 0; j < list.size(); j++) {
					NstNoticeEntityDTO nstNoticeEntityDTO= list.get(j);
					Label label0 = new Label(0,  j+startRow  + 1, String.valueOf(nstNoticeEntityDTO.getTitle()));
					Label label1 = new Label(1,  j+startRow  + 1, String.valueOf(nstNoticeEntityDTO.getChannelStr()));
					Label label2 = new Label(2,  j+startRow + 1, String.valueOf(nstNoticeEntityDTO.getCity()));
					Label label3 = new Label(3,  j+startRow + 1, String.valueOf(nstNoticeEntityDTO.getUserName()));
					Label label4 = new Label(4,  j+startRow  + 1,  DateUtil.toString(nstNoticeEntityDTO.getCreateTime(),
							DateUtil.DATE_FORMAT_DATETIME));
					Label label5 =  new Label(5, j+startRow  + 1,  String.valueOf(nstNoticeEntityDTO.getOnOffStr()));
				
					sheet.addCell(label0);//将单元格加入表格
					sheet.addCell(label1);// 
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label4);
					sheet.addCell(label5);
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

package cn.gdeng.nst.admin.controller.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
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
import cn.gdeng.nst.admin.dto.admin.MemberLineDTO;
import cn.gdeng.nst.admin.service.admin.MemberLineService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.DateUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gudeng.framework.core2.GdLogger;
import com.gudeng.framework.core2.GdLoggerFactory;

/**
 * 会员长跑线路
 * 
 * @author wujiang
 *
 */
@Controller
@RequestMapping("memberLine")
public class MemberLineController extends AdminBaseController {
	@SuppressWarnings("unused")
	private static final GdLogger logger = GdLoggerFactory
			.getLogger(MemberLineController.class);

	@Reference
	private MemberLineService memberLineService;
	
	@RequestMapping("index")
	public String index() {
		return "memberLine/list";
	}

	/**
	 * 获取长跑线路列表
	 * 
	 * @param request
	 */
	@RequestMapping("queryPage")
	@ResponseBody
	public String queryPage(HttpServletRequest request) {
		Map<String, Object> map = getParametersMap(request);
		// 设定分页,排序
		setCommParameters(request, map);
		ApiResult<AdminPageDTO> apiResult = memberLineService.queryPage(map);
		if (apiResult != null) {
			return JSONObject.toJSONString(apiResult.getResult(),
					SerializerFeature.WriteDateUseDateFormat);
		}
		return null;
	}
	
	/**
	 * 查看常跑线路详情
	 * 
	 */
	@RequestMapping("showDetail/{id}")
	public String showDetail(@PathVariable("id") String id, Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		MemberLineDTO  memberLineDTO = memberLineService.showDetail(map).getResult();
		model.addAttribute("dto", memberLineDTO);
		return "memberLine/showDetail";
	}
	
	/**
	 * 导出前检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exportCheck",produces="application/json;charset=utf-8")
	@ResponseBody
	public String exportCheck(HttpServletRequest request){
		ApiResult<String> apiResult = new ApiResult<String>();
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);
		ApiResult<Integer> remoteApiResult = memberLineService.countTotal(paramMap);
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
	
	@RequestMapping("export")
	public void export(HttpServletRequest request, HttpServletResponse response){
		//查询参数
		Map<String, Object> paramMap = getParametersMap(request);	
		OutputStream ouputStream = null;
		try{
			String fileName = "常跑线路"+DateUtil.toString(new Date(), "yyyy-MM-dd_HH:mm:ss");
			 // 设置输出响应头信息，
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls");
            ouputStream = response.getOutputStream();
            
            WritableWorkbook wwb = Workbook.createWorkbook(ouputStream);
            if(wwb == null){
            	return;
            }
            
            // 创建一个工作页，第一个参数的页名，第二个参数表示该工作页在excel中处于哪一页
            WritableSheet sheet = wwb.createSheet("常跑线路数据", 0);
            
            String[] headers = {"出发地", "目的地", "线路类型", "用户姓名", "用户手机", "发布时间", "线路状态"};
            for(int i = 0, len = headers.length; i < len; i++){
            	 sheet.addCell(new Label(i, 0, headers[i]));
            }
            
			// 查询导出数据总数
			ApiResult<Integer> countApiResult = memberLineService.countTotal(paramMap);
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
            	ApiResult<List<MemberLineDTO>> apiResult = memberLineService.queryList(paramMap);
            	List<MemberLineDTO> list = null;
            	if(apiResult != null){
	    			list = apiResult.getResult();
	    		}
	   
	            if(CollectionUtils.isEmpty(list)){
	            	break;
	            }
            	for(int j = 0, len = list.size(); j < len; j++){
                	MemberLineDTO dto = list.get(i);
                	sheet.addCell(new Label(0, j+1+startRow, dto.getSDetailStr()));
                    sheet.addCell(new Label(1, j+1+startRow, dto.getEDetailStr()));
                    sheet.addCell(new Label(2, j+1+startRow, dto.getSourceTypeStr()));
                    sheet.addCell(new Label(3, j+1+startRow, dto.getPublisher()));
                    sheet.addCell(new Label(4, j+1+startRow, dto.getPhone()));
                    sheet.addCell(new Label(5, j+1+startRow, dto.getCreateTimeStr()));
                    sheet.addCell(new Label(6, j+1+startRow, dto.getIsDeletedStr()));
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

}

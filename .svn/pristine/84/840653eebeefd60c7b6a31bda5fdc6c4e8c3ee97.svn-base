package cn.gdeng.nst.web.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.order.SourceExamineDTO;
import cn.gdeng.nst.api.server.order.SourceExamineService;
import cn.gdeng.nst.api.vo.order.SourceExamineVo;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.web.controller.base.BaseController;
/**
 * 验货Controller
 * 
 * @author Kwang
 */
@Controller
@RequestMapping("SourceExamine")
public class SourceExamineController extends BaseController {
	@Reference
	private  SourceExamineService sourceExamineService;
    
	/**
	 * 根据OrderNo 查询验货信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("querySourceExamineByOrderNo")
	public Object querySourceExamineByOrderNo(HttpServletRequest request) throws Exception {
		SourceExamineDTO dto = getDecodeDto(request,SourceExamineDTO.class);  
		ApiResult<SourceExamineVo> result= sourceExamineService.querysourceExamine(dto);       
        return result; 
	 }
	
	/**
	 * 保存验货信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveSourceExamine")
	public Object saveSourceExamine(HttpServletRequest request) throws Exception {
		SourceExamineDTO dto = getDecodeDto(request,SourceExamineDTO.class);       
	    ApiResult<Long> result = sourceExamineService.saveSourceExamine(dto);
        return result; 
	 }

}

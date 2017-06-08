package cn.gdeng.nst.web.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Des3Response;

/** Spring MVC 公共异常拦截处理器
 * @author wjguo
 * data 2016年7月23日 上午9:37:13 
 *
 */
public class CommonExceptionHandler implements HandlerExceptionResolver{
	
	/**
	 * 定义记录日志信息
	 */
	protected Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			Exception ex) {
		ApiResult<?> result = new ApiResult<>();
		if(ex instanceof BizException){
			BizException biz = (BizException)ex;
			result.withError(biz.getCode(), biz.getMsg());
			logger.debug(JSON.toJSONString(result), ex);
		} else {
			result.withError(MsgCons.C_50000, MsgCons.M_50000);
			logger.error(JSON.toJSONString(result), ex);
		}
		
		try {
			//转换json字符串并加密
			String jsonStrCipher = Des3Response.encode(JSON.toJSONString(result));
			//String jsonStrCipher = JSON.toJSONString(result);
			//设置json头
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonStrCipher);
		} catch (Exception e) {
			logger.error("加密失败", e);
		}
		//不能直接返回null
		return new ModelAndView();
	}

}

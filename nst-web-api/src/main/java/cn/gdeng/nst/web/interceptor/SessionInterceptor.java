package cn.gdeng.nst.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import cn.gdeng.nst.api.server.member.MemberInfoApiService;
import cn.gdeng.nst.entity.nst.MemberInfoEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.jodis.JodisTemplate;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.Des3Request;
import cn.gdeng.nst.util.web.api.Des3Response;
import cn.gdeng.nst.util.web.api.GSONUtils;
/**
 * 验证token拦截器
 * @author xiaojun
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JodisTemplate jodisTemplate;

	@Reference
	private MemberInfoApiService memberInfoApiService;
    /** 不用检查的checkUrl */
    private List<String> doNotCheckUrl;
    /** 验证拦截  true=1:拦截 false=0:不拦截 **/
    private Boolean validate;

    public List<String> getDoNotCheckUrl() {
        return doNotCheckUrl;
    }

    public void setDoNotCheckUrl(List<String> doNotCheckUrl) {
        this.doNotCheckUrl = doNotCheckUrl;
    }
    
    public Boolean getValidate() {
		return validate;
	}

	public void setValidate(Boolean validate) {
		this.validate = validate;
	}

	/**
     * token校验
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//不拦截
    	if(null != validate
    			&& false == validate){
    		return true;
    	}
    	String url = request.getRequestURI();
    	String token=getTokenByRequest(request);
        if (!jodisTemplate.exists(token)) {
            if (doNotCheckUrl != null) {
               String str = "";
               for (int i = 0; i < doNotCheckUrl.size(); i++) {
                    str = doNotCheckUrl.get(i);
                    if (url.indexOf(str) >= 0) {
                        return super.preHandle(request, response, handler);
                   }
                }
            }
            logger.warn("-----------------------token校验失败---------------------"+token);
            responseErorr(response,MsgCons.C_21016, MsgCons.M_21016);
            return false;
       }
       boolean userValid= getMemberValidByToken(token);
       if(!userValid){
    	   responseErorr(response,MsgCons.C10000, MsgCons.M10000);  
    	  return false;
       }
       return super.preHandle(request, response, handler);
   }
    //错误返回
    public void responseErorr(HttpServletResponse response,Integer code,String msg)throws Exception {
    	   ApiResult<?> result = new ApiResult<>();
    	   result.withError(code,msg);
     	   String jsonStrCipher = Des3Response.encode(JSON.toJSONString(result));
    	  //设置json头
     	   response.setContentType("application/json; charset=UTF-8");
    	   response.getWriter().write(jsonStrCipher);
    }
    
    /**
     * 获取token
     * @param request
     * @return
     * @throws Exception
     */
    private String getTokenByRequest(HttpServletRequest request) throws Exception{
    	String encryptParam = request.getParameter("param");
    	String jsonStr=StringUtils.isBlank(encryptParam) ? "{}" : Des3Request.decode(encryptParam);
    	String resultStr=(String)GSONUtils.getJsonValueStr(jsonStr, "token");
    	return resultStr==null?"":resultStr;
    }
    
    /**
     * 判断用户当前状态（需要 memberId 的都需判断）
     * @param request
     * @return boolean
     * @throws Exception
     */
    private boolean getMemberValidByToken(String token) throws Exception{
    	String tokenJson=jodisTemplate.get(token);
    	String memberId=(String)GSONUtils.getJsonValueStr2(tokenJson, "memberId");
    	if(null!=memberId&&!"".equals(memberId)){
    		ApiResult<MemberInfoEntity> apiResult = memberInfoApiService.getById(Integer.valueOf(memberId));
    		if(null==apiResult.getResult()){
    		logger.warn("--------------------有memberId,nst.member_Info中没有------------------------"+memberId);
    			return true;
    		}
    		MemberInfoEntity memberInfoEntity = apiResult.getResult();
    	if((byte)0==memberInfoEntity.getStatus()){
    			return false;
    		}
    	}
    	return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}

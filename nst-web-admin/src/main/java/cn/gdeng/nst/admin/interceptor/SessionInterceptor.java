package cn.gdeng.nst.admin.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import cn.gdeng.nst.admin.dto.right.BaseMenu;
import cn.gdeng.nst.admin.service.right.SysMenuService;
import cn.gdeng.nst.entity.nst.SysMenu;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.admin.web.LoginUserUtil;
import cn.gdeng.nst.util.web.api.ApiResult;

public class SessionInterceptor extends HandlerInterceptorAdapter {
    private static List<SysMenu> allMenus = new ArrayList<>();
    /** 不用检查的checkUrl */
    private List<String> doNotCheckUrl;
    private static final String REQUEST_TYPE_AJAX = "ajax";
    private static final String REQUEST_TYPE = "Request-Type";

    public List<String> getDoNotCheckUrl() {
        return doNotCheckUrl;
    }

    public void setDoNotCheckUrl(List<String> doNotCheckUrl) {
        this.doNotCheckUrl = doNotCheckUrl;
    }

    @Reference
    private SysMenuService sysMenuService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (LoginUserUtil.getLoginUserId(request) == null) {
            // 如果不要进行检查的url，直接跳过;
            if (doNotCheckUrl != null) {
                String str = "";
                for (int i = 0; i < doNotCheckUrl.size(); i++) {
                    str = doNotCheckUrl.get(i);
                    if (url.indexOf(str) >= 0) {
                        return super.preHandle(request, response, handler);
                    }
                }
            }
            retMessage(request, response);
            return false;
        } else {
        	// 如果请求的路径不存在于系统菜单中，则不进行拦截
            if (!checkUrl(url)) {
                return super.preHandle(request, response, handler);
            }
            // 如果不要进行检查的url，直接跳过;
            if (doNotCheckUrl != null) {
                String str = "";
                for (int i = 0; i < doNotCheckUrl.size(); i++) {
                    str = doNotCheckUrl.get(i);
                    if (url.indexOf(str) >= 0) {
                        return super.preHandle(request, response, handler);
                    }
                }
            }

            @SuppressWarnings("unchecked")
			List<BaseMenu> menuList = (List<BaseMenu>) request.getSession().getAttribute("menuList");
            if (menuList != null && menuList.size() > 0) {
                for (BaseMenu baseMenu : menuList) {
                    if (StringUtils.isEmpty(baseMenu.getActionUrl())) {
                        continue;
                    } else {
                        if (url.indexOf(baseMenu.getActionUrl()) >= 0) {
                            return super.preHandle(request, response, handler);
                        }
                    }
                }
                retMessage(request, response);
                return false;
            } else {
                retMessage(request, response);
                return false;
            }
        }
    }

    private void retMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String requestType = request.getHeader(REQUEST_TYPE);
    	
    	// 如果请求类型为ajax请求，直接输出地址
		if(null != requestType
				&& requestType.contains(REQUEST_TYPE_AJAX)){
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JSON.toJSONString(new ApiResult<>().withError(MsgCons.C_30000, MsgCons.M_30000)));
		}else{
			// 跳转到登录页面
			String contextPath = request.getContextPath();
			response.getWriter().print("<script>window.top.location.href='"+contextPath+"/sys/loginOut'</script>");
		}
		
//        String backUrl = PathUtil.getBasePath(request);
//        // response.sendRedirect(backUrl);
//        PrintWriter pw = response.getWriter();
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html>");
//        sb.append("<meta charset=utf-8>");
//        sb.append("<link href='" + backUrl + "images/favicon.ico' type='image/x-icon' rel='icon' />");
//        sb.append("<title>请重新登陆</title>");
//        sb.append("<body style='text-align:center;'>");
//        sb.append("<p>请重新登陆</p>");
//        sb.append("<a href='" + backUrl + "'>返回</a>");
//        sb.append("</body>");
//        sb.append("</html>");
//        pw.println(sb.toString());
//        pw.flush();
//        pw.close();

        return;
    }

    /**
     * @Description checkUrl
     * @param url
     * @return 不存在为false，存在系统菜单中，为true
     * @throws Exception
     * @CreationDate 2015年11月26日 下午3:29:01
     * @Author lidong(dli@gdeng.cn)
     */
    private boolean checkUrl(String url) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (allMenus != null && allMenus.size() > 0) {

        } else {
            allMenus = sysMenuService.getAll(map);
        }
        for (SysMenu sysMenu : allMenus) {
            String menuUrl = sysMenu.getActionUrl();
            if (StringUtils.isEmpty(menuUrl)) {
                continue;
            } else {
                if (menuUrl.indexOf("/") < 0) {
                    if (url.indexOf("/" + menuUrl + "/") >= 0) {
                        return false;
                    }
                }
                if (url.indexOf(menuUrl) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

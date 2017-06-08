package cn.gdeng.nst.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

/**
 * 登陆统计接口
 * @author Administrator
 *
 */
public class AppCountControllerTest extends TestCase {

	public void testAppVersion() throws Exception {
		long a=System.currentTimeMillis();
		String url="http://10.17.1.201:8082/appactivitystat/startup";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberId", "6508");
		map.put("UUID", "6508");
		map.put("appVersion", "6508");
		map.put("appChannel", "6508");
		map.put("isLogin", "0");
		map.put("appType", "7");
		Gson gson = new Gson();
		System.out.println("请求参数：" + gson.toJson(map));
		String requestData = Des3.encode(gson.toJson(map));
		System.out.println("requestData：" + requestData);
		Map<String, Object> map2=new HashMap<>();
		map2.put("param", requestData);
		String reponseData= HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData));
		long b=System.currentTimeMillis();
		System.out.println(b-a);
	}
	
}

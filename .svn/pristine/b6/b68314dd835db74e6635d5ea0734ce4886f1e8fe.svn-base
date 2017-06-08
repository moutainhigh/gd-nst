package cn.gdeng.nst.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

public class AppVersionControllerTest extends TestCase {

	public void testAppVersion() throws Exception {
		long a=System.currentTimeMillis();
		String url="http://127.0.0.1:8080/v1/app/checkAppVesion";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("num", "1.0.3");
		map.put("type", "5");
		map.put("platform", "2");
		Gson gson = new Gson();
		System.out.println("请求参数：" + gson.toJson(map));
		String requestData = Des3.encode(gson.toJson(map));
		System.out.println("requestData：" + requestData);
		Map<String, Object> map2=new HashMap<>();
		map2.put("param", requestData);
		String reponseData= HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData)+"最终结果");
		long b=System.currentTimeMillis();
		System.out.println(b-a);
	}
	
}

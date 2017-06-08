package cn.gdeng.nst.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;

import com.google.gson.Gson;


public class AssignTest extends TestCase{
	public void testAssign() throws Exception {
		String url="http://localhost:8080/nst-web-api/v1/assign/validateAssign";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("memberIdShipper", "1");
		Gson gson = new Gson();
		System.out.println("请求参数：" + gson.toJson(map));
		String requestData = Des3.encode(gson.toJson(map));
		System.out.println("requestData：" + requestData);
		Map<String, Object> map2=new HashMap<>();
		map2.put("param", requestData);
		String reponseData= HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData)+"最终结果");
	}
}

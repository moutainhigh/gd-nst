package cn.gdeng.nst.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

public class OrderAgentControllerTest extends TestCase {
	private static String publicUrl = "http://localhost:8080/nst-web-api/v1/orderAgent/";

	
	// 查询信息详情
	public void queryOrderAgent() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "queryOrderAgent";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "80");
		map.put("token", "3B1684F1B6834DF681277D6AD6AADA56");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 分页查询信息订单
	public void queryOrderAgentPage() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "queryOrderAgentPage";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderStatus", "2");
		map.put("logisticMemberId", "266610");
		map.put("nstRule", "1");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}
}

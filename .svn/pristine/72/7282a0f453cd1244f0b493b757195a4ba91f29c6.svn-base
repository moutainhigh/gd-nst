package cn.gdeng.nst.admin.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

public class RuleLineTest extends TestCase {
private static String publicUrl = "http://localhost:8880/nst-web-admin/ruleLine/";

	
	// 分页查询
	public void queryRuleLinePage() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "queryPage";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "80");
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

package cn.gdeng.nst.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

public class MemberCarControllerTest extends TestCase {
	private static String publicUrl = "http://localhost:8880/nst-web-api/memberCarApi/";

	
	// 查询用户承运车辆
	public void queryMemberCarrierCarList() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "queryMemberCarrierCarList";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", "280082");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 修改用户车辆为承运车辆
	public void updateMemberCarrierCar() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "updateMemberCarrierCar";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", "324985");
		map.put("id", "66756");
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

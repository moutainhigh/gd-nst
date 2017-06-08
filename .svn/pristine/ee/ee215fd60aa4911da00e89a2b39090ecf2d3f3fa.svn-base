package cn.gdeng.nst.web.controller.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

public class OrderBeforeControllerTest extends TestCase {
	private static String publicUrl="http://localhost:8080/nst-web-api/v1/orderBefore/";
	// 司机接单
	public void driverReceiveOrder() throws Exception {
		long a = System.currentTimeMillis();
		String url =publicUrl+ "createOrderBefore";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sourceId", "13");
		map.put("carId", "2");
		map.put("shipperMemberId", "1");
		map.put("driverMemberId", "2");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 司机取消接单
	public void driverCancleOrderBefore() throws Exception {
		long a = System.currentTimeMillis();
		String url =publicUrl+ "cancleOrderBefore";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "45");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 司机查询预订单
	public void driverqueryOrderBefore() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl+"queryOrderBefore";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderBeforeId", "16");
		map.put("sourceId", "1");
		map.put("driverMemberId", "16");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 司机分页查询预订单
	public void driverqueryOrderBeforeStatus() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl+"queryOrderBeforePage";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sourceStatus", "1");
		map.put("driverMemberId", "2");
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

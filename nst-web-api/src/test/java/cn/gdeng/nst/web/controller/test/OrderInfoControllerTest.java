package cn.gdeng.nst.web.controller.test;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import junit.framework.TestCase;

public class OrderInfoControllerTest extends TestCase {
	private static String publicUrl = //"http://localhost:8090/nst-web-api/SourceExamine/";
			"http://localhost:8090/nst-web-api/OrderInfoTrans/";
           //"http://localhost:8090/nst-web-api/purchaseOrder/";
	   //物流信息
		public void OrderInfoTrans() throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			long a = System.currentTimeMillis();
		String	url = publicUrl + "queryOrderInfoTransAndSourceExamineByOrderNo";
			//String url = publicUrl + "queryOrderInfoTransByOrderId";
			map.put("orderNo", "132016121213000004");
//String url = publicUrl + "saveSourceExamine";
	    //   String url = publicUrl + "querySourceExamineByOrderNo";
	   /* map.put("orderNo", "132016121213000004");
			map.put("sourceId", "1104731");
			map.put("description", "说明");
			ArrayList imageUrlList = new ArrayList();
			imageUrlList.add("shenzhen.aliyuncs.com/2016/9/13/9b62cc5cbe1148fead3495894db05185.jpg");
			imageUrlList.add("shenzhen.aliyuncs.com/2016/9/13/9b62cc5cbe1148fead3495894db05185.jpg");
			map.put("imageUrlList", imageUrlList);
			map.put("status", 1);
			map.put("createUserId", "280082"); */
		/*String	url = publicUrl + "queryPurchaseOrderBySourceId";
		map.put("sourceId", "132663"); */
			Gson gson = new Gson();
			String requestData = Des3.encode(gson.toJson(map));
			Map<String, Object> map2 = new HashMap<>();
			map2.put("param", requestData);
			String reponseData = HttpClientUtil.doGet(url, map2);
			System.out.println(Des3.decode(reponseData) + "最终结果");
			long b = System.currentTimeMillis();
			System.out.println(b - a);
		}

	
	// (接受)新增运单
	public void createOrderInfo() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "createOrderInfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sourceId", "15");
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

	// 拒绝订单
	public void refuseOrderInfo() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "refuseOrderInfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sourceId", "13");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 确认收货
	public void confirmOrderInfo() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "confirmOrderInfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sourceId", "15");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 查询运单详情
	public void queryOrderInfo() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "queryOrderInfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sourceId", "75");
		map.put("token", "3B1684F1B6834DF681277D6AD6AADA56");
		//map.put("orderStatus", "1");
		//map.put("needLog", "Y");
		Gson gson = new Gson();
		String requestData = Des3.encode(gson.toJson(map));
		Map<String, Object> map2 = new HashMap<>();
		map2.put("param", requestData);
		String reponseData = HttpClientUtil.doGet(url, map2);
		System.out.println(Des3.decode(reponseData) + "最终结果");
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

	// 分页查询运单
	public void queryOrderInfoPage() throws Exception {
		long a = System.currentTimeMillis();
		String url = publicUrl + "queryOrderInfoPage";
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("orderStatus", "2");
		map.put("shipperMemberId", "266591");
		map.put("pageSize", "50");
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

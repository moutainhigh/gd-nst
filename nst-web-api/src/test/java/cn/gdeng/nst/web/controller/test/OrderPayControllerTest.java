package cn.gdeng.nst.web.controller.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.entity.nst.PayTradeEntity;
import cn.gdeng.nst.util.server.Base64;
import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.SerializeUtil;
import junit.framework.TestCase;

public class OrderPayControllerTest extends TestCase {
	
	public void testPay() throws Exception {
		long a=System.currentTimeMillis();
		String url="http://127.0.0.1:8085/v1/pay/confirmPay";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orderNo", "32208110000333");
		map.put("payAmt", "0.01");
		map.put("title", "大白菜");
		map.put("orderTime", "2016-08-18 20:35:45");
		map.put("payerUserId", "10005");
		map.put("payerMobile", "18279771123");
		map.put("payerName", "王里111");
		map.put("payeeUserId", "22");
		map.put("payeeMobile", "15112347543");
		map.put("payeeName", "李四-ios");
		map.put("orderType", "1");
		map.put("id", "9");
		map.put("shipperMemberId",10005);
		map.put("driverMemberId", 2);
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
	
	public static void main(String[] args) throws IOException {
		Gson gson=new Gson();
		//gson.fromJson(gson.toJson(resultStr), PayTradeEntity.class);
	}
}

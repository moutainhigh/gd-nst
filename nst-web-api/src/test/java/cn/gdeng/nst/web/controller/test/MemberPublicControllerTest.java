package cn.gdeng.nst.web.controller.test;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import cn.gdeng.nst.util.server.HttpClientUtil;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.JavaMd5;
import junit.framework.TestCase;

public class MemberPublicControllerTest extends TestCase {
	public static String urlPerfix="http://10.17.1.181:8089/";
			//"http://localhost:8080/";
	public void testGetVerifyCode() throws Exception {
		long a=System.currentTimeMillis();
		String url=urlPerfix+"v1/member/getVerifyCode";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("mobile", "18279661989");
		map.put("type", 0);
		String enstr=JavaMd5.getMD5("18279661989"+"gudeng2015@*&^-") .toUpperCase();
		map.put("encryptStr", enstr);
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
	
	public void testLogin() throws Exception {
		long a=System.currentTimeMillis();
		String url=urlPerfix+"v1/member/login";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("account", "18126819990");
		String password=JavaMd5.getMD5("123456"+"gudeng2015@*&^-") .toUpperCase();
		map.put("password", password);
		map.put("device_tokens", "999999999");
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
	
	public void testLoginout() throws Exception {
		long a=System.currentTimeMillis();
		String url=urlPerfix+"v1/member/logout";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("token", "04CA868EE60E455EB647464844FCD3F2");
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

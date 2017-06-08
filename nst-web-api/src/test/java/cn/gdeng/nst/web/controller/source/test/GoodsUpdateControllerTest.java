package cn.gdeng.nst.web.controller.source.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import cn.gdeng.nst.util.web.api.Base64;
import cn.gdeng.nst.web.http.HttpHelper;
import junit.framework.TestCase;

public class GoodsUpdateControllerTest extends TestCase {
	
	private final static String BASE_URL = "http://localhost:8081";
	//private final static String BASE_URL = "http://10.17.1.181:8089";
	//private final static String BASE_URL = "http://10.17.1.193:8281";
	
	
	public void test_insertForLogistics() throws Exception{
		//String url ="/v1/goodsUpdate/insertForLogistics";
		String url ="/v1/goodsUpdate/insertForShipper";
		String data="{sendDate:'2016-09-21 20:02',memberId:226,sLat:12,sLng:150"
				+ ",eProvinceId:130000,eCityId:130400,sCityName:北京市,sCityId:110000,sProvinceId:110000"
				+ ",eLat:1672,eLng:670,goodsType:"
				+ "101,totalWeight:222,totalSize:20,freight:30000,sendGoodsType:1,clients:2,"
				+ "remark:'新测试cat',sDetail:'湖北省/邯郸市/',eDetail:'湖北省/邢台市',carLength:-1,"
				+ "carType:-1,goodsName:'海鲜新货',shipperMobile:'13800138000',shipperName:'测试3'}";
		
		String result = HttpHelper.doPost(BASE_URL + url, data);
		System.out.println(result);
	}

}

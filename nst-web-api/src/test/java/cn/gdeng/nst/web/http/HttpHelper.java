package cn.gdeng.nst.web.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.Key;
import java.util.ArrayList;
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

public class HttpHelper {
	/**
	 *   
	 * 发起网络请求 
	 * 
	 * @param url
	 *            URL
	 * @param requestData
	 *            requestData
	 * @return INPUTSTREAM
	 * @throws AppException  
	 */
	public static String doPost(String url, String requestData) throws Exception {
		String responseBody = null;
		HttpPost httpPost = null;
		HttpClient httpClient = null;
		int statusCode = -1;

		try {
			httpPost = new HttpPost(url);
			httpClient = getHttpClient();
			// 设置HTTP POST请求参数必须用NameValuePair对象
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			//加密
			params.add(new BasicNameValuePair("param", specialEncode(requestData)));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			// 设置HTTP POST请求参数
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("HTTP" + "  " + "HttpMethod failed: " + httpResponse.getStatusLine());
			}
			InputStream is = httpResponse.getEntity().getContent();
			responseBody =specialDecode(getStreamAsString(is, HTTP.UTF_8)) ;
		} finally {
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
		}
	
		return responseBody;
	}

	/**
	 * 
	 * 将InputStream 转化为String
	 * 
	 * @param stream
	 *            inputstream
	 * @param charset
	 *            字符集
	 * @return
	 * @throws IOException
	 */
	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset), 8192);
			StringWriter writer = new StringWriter();

			char[] chars = new char[8192];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}

			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	/**
	 * 
	 * 得到httpClient
	 * 
	 * @return
	 */
	private static HttpClient getHttpClient() {
		final HttpParams httpParams = new BasicHttpParams();

		HttpClientParams.setRedirecting(httpParams, true);
		final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.14) Gecko/20110218 Firefox/3.6.14";

		HttpProtocolParams.setUserAgent(httpParams, userAgent);
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		HttpClientParams.setCookiePolicy(httpParams, CookiePolicy.BROWSER_COMPATIBILITY);
		HttpProtocolParams.setUseExpectContinue(httpParams, false);
		HttpClient client = new DefaultHttpClient(httpParams);

		return client;
	}
	
	//解密。注意秘钥和向量，与本地的工具方法是相反的。
    private static String specialDecode(String encryptText) throws Exception { 
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec("s1f2d3s4)5&6f7f8#9s0#1@2".getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec("20151009".getBytes());  
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        String decryptStr = new String(decryptData, "utf-8");
        return decryptStr;
    }
    
    //加密。注意秘钥和向量，与本地的工具方法是相反的。
    private static String specialEncode(String plainText) throws Exception { 
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec("h1k2#3s4f5d6%7d8s9@0s1f2".getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec("20151008".getBytes());  
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
        byte[] encryptData = cipher.doFinal(plainText.getBytes("utf-8"));  
        String encryptStr = Base64.encode(encryptData);
        return encryptStr;
    } 
}

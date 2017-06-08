package cn.gdeng.nst.util.web.api;

import java.util.UUID;

/**
 * UUID 生成token 
 * @author xiaojun
 */
public class TokenCreater {
	private static final String TOKEN_NAME="token:"; 
	/**
	 * 使用UUID字符串token
	 * @return
	 */
	public static String newId(){
		String token= UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return TOKEN_NAME+token;
	}
}

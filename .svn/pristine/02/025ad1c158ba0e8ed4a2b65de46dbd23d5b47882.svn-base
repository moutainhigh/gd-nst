package cn.gdeng.nst.util.server;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密组件
 * 
 * @author tanjun
 * @version 1.0
 * @since 1.0
 */
public class EncryptionUtil {

	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5 
	 * HmacSHA1 
	 * HmacSHA256 
	 * HmacSHA384 
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
	
	/**
	 * MD5加密
	 * @author songhui
	 * @date 创建时间：2015年4月14日 下午6:50:22
	 * @param plaintext
	 * @return
	 * @throws Exception
	 *
	 */
	public static String encodeByMD5(String plaintext) throws Exception {
		//BigInteger md5 = new BigInteger(encryptMD5(txt.getBytes()));
		return bytesToHexString(encryptMD5(plaintext.getBytes()));
	}

	/**
	 * 数组转换成十六进制字符串
	 * @param bArray
	 * @return HexString
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xff & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toLowerCase());
		}
		return sb.toString();
	}


	public static void main(String[] args) throws Exception{
		String inputStr = "简单加密";  
        System.err.println("原文:\n" + inputStr);
		byte[] inputData = inputStr.getBytes();
        String code = EncryptionUtil.encryptBASE64(inputData);  
        System.err.println("BASE64加密后:\n" + code);  
        byte[] output = EncryptionUtil.decryptBASE64(code);  
        String outputStr = new String(output);  
        System.err.println("BASE64解密后:\n" + outputStr);  
        String key = EncryptionUtil.initMacKey();  
        System.err.println("Mac密钥:\n" + key);  
        BigInteger md5 = new BigInteger(EncryptionUtil.encryptMD5(inputData));
        System.err.println("MD5:\n" + md5.toString(16));  
        BigInteger sha = new BigInteger(EncryptionUtil.encryptSHA("888888".getBytes()));  
        System.err.println("SHA:\n" + sha.toString(32));  
        BigInteger mac = new BigInteger(EncryptionUtil.encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:\n" + mac.toString(16));
        
        System.out.println(encodeByMD5("123456"));
        System.out.println(encodeByMD5("2001"+encodeByMD5("123456")));
	}
}
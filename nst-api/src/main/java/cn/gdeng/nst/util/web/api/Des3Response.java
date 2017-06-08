package cn.gdeng.nst.util.web.api;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 类说明 
 * @author 
 * @version 创建时间：2014年3月7日 上午11:51:30 * 
 */
public class Des3Response {
	 // 密钥  	 
    private final static String secretKey = "s1f2d3s4)5&6f7f8#9s0#1@2";  
    // 向量  
    private final static String iv = "20151009";  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8";  
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Des3Request.class);
  
    /** 
     * 3DES加密 
     *  
     * @param plainText 普通文本 
     * @return 
     * @throws Exception  
     */  
    public static String encode(String plainText) throws Exception { 
	        Key deskey = null;
	        LOGGER.info("3DES加密前报文 ：" + plainText);
	        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
	        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
	        deskey = keyfactory.generateSecret(spec);  
	  
	        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
	        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
	        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
	        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
	        String encryptStr = Base64.encode(encryptData);
	        //禁止加密的密文输出到日志。
	        //LOGGER.info("3DES加密后密文 ：" + encryptStr);
	        return encryptStr;
    }      
}

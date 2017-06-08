package cn.gdeng.nst.util.web.api;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 接收从手机app端发送过来的加密串进行解密
 * @author 
 */
public class Des3Request {
    // 密钥  	                                 
    private final static String secretKey = "h1k2#3s4f5d6%7d8s9@0s1f2";  
    // 向量  
    private final static String iv = "20151008";  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8"; 
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Des3Request.class);
    
    /** 
     * 3DES解密 
     *  
     * @param encryptText 加密文本 
     * @return 
     * @throws Exception 
     */  
    public static String decode(String encryptText) throws Exception { 
	        Key deskey = null;
	        LOGGER.info("3DES解密前密文 ：" + encryptText);
	        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
	        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
	        deskey = keyfactory.generateSecret(spec);  
	        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
	        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
	        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
	        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));  
	        String decryptStr = new String(decryptData, encoding);
	        LOGGER.info("3DES解密后报文：" + decryptStr);
	        return decryptStr;
    }  
}

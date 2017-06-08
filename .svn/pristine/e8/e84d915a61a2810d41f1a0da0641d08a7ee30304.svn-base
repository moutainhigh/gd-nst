package cn.gdeng.nst.util.web.api;
/** 
 * MD5加密方式
 * @author 
 * @version 创建时间：2014年3月18日 下午6:40:44 * 
 */
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  

public class JavaMd5 {  
  
	static GdProperties gdProperties;
	
	
    public static String getMD5(String content) {  
        try {  
            MessageDigest digest = MessageDigest.getInstance("MD5");  
            digest.update(content.getBytes());  
            return getHashString(digest);  
              
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    private static String getHashString(MessageDigest digest) {  
        StringBuilder builder = new StringBuilder();  
        for (byte b : digest.digest()) {  
            builder.append(Integer.toHexString((b >> 4) & 0xf));  
            builder.append(Integer.toHexString(b & 0xf));  
        }  
        return builder.toString();  
    }  
    
    public static void main(String[] args){
    	System.out.println("0b59292dfc827f4cd20cea750ce6add7".toUpperCase());
    }
}  
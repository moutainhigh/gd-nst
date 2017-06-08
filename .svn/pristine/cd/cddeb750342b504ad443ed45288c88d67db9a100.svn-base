package cn.gdeng.nst.util.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.security.Key;

/**
 * 百度API工具类
 * 
 * @author yangjj
 */
public class BaiDuApiUtil {

    public static final String  LOGIN_API_URL   = "https://api.baidu.com/sem/common/HolmesLoginService";

    public static final String  PRODUCT_API_URL = "https://api.baidu.com/json/tongji/v1/ProductService/api";

    public static final String  USER_NAME       = "谷登科技01";

    public static final String  PASSWORD        = "gDeng509";

    public static final String  TOKEN           = "b15ae72b5b842ce3f289d2d4b82cce30";

    private static final String KEY             = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHn/hfvTLRXViBXTmBhNYEIJeGGGDkmrYBxCRelriLEYEcrwWrzp0au9nEISpjMlXeEW4+T82bCM22+JUXZpIga5qdBrPkjU08Ktf5n7Nsd7n9ZeI0YoAKCub3ulVExcxGeS3RVxFai9ozERlavpoTOdUzEH6YWHP4reFfpMpLzwIDAQAB";

    /**
     * 接口服务枚举
     * 
     * @author yangjj
     */
    public static enum ApiServerEnum{
        
        PROFILE("profile"),//Profile服务
        
        REPORT("report");//Report服务
        
        private String value;
        
        private ApiServerEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    
    /**
     * 接口访问方法枚举
     * 
     * @author yangjj
     */
    public static enum ApiFunctionNameEnum {

        DOLOGIN("doLogin"), // 登录方法名

        DOLOGOUT("doLogout"), // 登出方法名

        SITES("getsites");// 获取站点方法名

        private String value;

        private ApiFunctionNameEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 接口调用的账户类型
     * 
     * @author yangjj
     */
    public static enum AccountTypeEnum {

        ZHANZHANG("1"), // 站长账号
        FENGCHAO("2"), // 凤巢账号
        LIANMENG("3"), // 联盟账号
        GELUNBU("4");// 哥伦布账号

        private String value;

        private AccountTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * 登录API请求
     * 
     * @param url 接口地址
     * @return
     * @author yangjj
     */
    public static LoginConnection makeConnection(String url) {
        LoginConnection connection;
        try {
            Key publicKey = RSAUtil.getPublicKey(KEY);
            connection = new LoginConnection(url);
            connection.setPublicKey(publicKey);
            return connection;
        } catch (Exception e) {
            throw new ClientInternalException(e);
        }
    }

    /**
     * 获取MAC地址
     * 
     * @author yangjj
     */
    public static String getLocalMac() {
        String macAddress = null;
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            macAddress = sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddress;
    }

    /**
     * Profile服务post请求
     * 
     * @param url 接口地址
     * @param request 请求参数
     * @param uuid MAC地址
     * @param userId 百度接口登录成功后UCID
     * @param tracker 选填，请求定位符，可用于定位请求
     * @return
     * @author yangjj
     */
    public static String sendPostData(String url, Object request, String uuid, long userId, String tracker) {
        try {
            String json = JacksonUtil.obj2Str(request);
            URL strURL = new URL(url);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) strURL.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "text/json; charset=utf-8");
            connection.setRequestProperty("UUID", uuid);
            connection.setRequestProperty("USERID", String.valueOf(userId));
            connection.setRequestProperty("tracker", tracker);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(json);
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sbf = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sbf.append(lines);
            }
            reader.close();
            connection.disconnect();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}

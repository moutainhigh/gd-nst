package cn.gdeng.nst.util.server;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.ObjectResult;

/**
 * 外包接口工具类
 * @author huang
 * @Date:2016年12月13日上午11:30:32
 */
public class InterfaceUtils {
	private static Logger logger =  LoggerFactory.getLogger(InterfaceUtils.class);
	/**
	 * 加密数据
	 * @param map
	 * @return
	 */
	 public static Map<String, Object> des3Map(Map<String, Object> map){
		    String json = GSONUtils.getGson().toJson(map);
			Map<String, Object> temp = new HashMap<String, Object>();
			try {
				temp.put("param", Des3.encode(json));
			} catch (Exception e) {
				return map;
			}
	   return temp;
	 }
	 
	 /**
		 * 公共处理方法 参数值 传入调用接口 返回字符串
		 * 
		 * @param url
		 * @param map
		 * @return
		 * @throws Exception
		 */
		public static ApiResult<?> pubMethod(String str) throws Exception {
			// 农速通api返回结果类
			ApiResult<?> apiResult = new ApiResult<>();
			// 谷登api返回结果类
			ObjectResult result = null;
			// 将返回结果解密
			String resultStr = Des3.decode(str);
			// 将解密后的结果 装换result
			result = JacksonUtil.str2Obj(resultStr, ObjectResult.class);
			// 处理返回结果
			logger.info("外部接口返回结果,statusCode:{},msg:{}",new Object[]{result.getStatusCode(),result.getMsg()});
			if (result.getStatusCode() == 0) {
				apiResult.setCodeMsg(MsgCons.C_10000, result.getMsg());
			} else {
				apiResult.withError(MsgCons.C_20000, result.getMsg());
			}
			return apiResult;
		}
}

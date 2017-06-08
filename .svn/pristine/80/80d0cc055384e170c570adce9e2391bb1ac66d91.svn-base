package cn.gdeng.nst.server.order.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.api.server.order.PurchaseOrderService;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.InterfaceUtil;
import cn.gdeng.nst.util.server.JacksonUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.ObjectResult;

import com.alibaba.dubbo.config.annotation.Service;
/**
 * 采购信息
 * @author kwang
 *
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterfaceUtil interfaceUtil;
	/**
	 * 根据sourceId查采购信息
	 * @param sourceId
	 * @throws BizException 
	 */
	@Override
	public Object queryPurchaseOrderBySourceId(String sourceId)
			throws BizException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("memberAddressId", sourceId);
		String re=interfaceUtil.queryPurchaseOrderBySourceId(des3Map(param));
		try {
			return pubMethod(re);
		} catch (Exception e) {
			throw new BizException(MsgCons.C_50000, MsgCons.M_50000);
		}
	}

	
	
	
	
	/**
	 * 公共处理方法（接口返回结果解密）
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private ApiResult<?> pubMethod(String str) throws Exception {
		// 农速通api返回结果类
		ApiResult<Object> apiResult = new ApiResult<Object>();
		// 谷登api返回结果类
		ObjectResult result = null;
		// 将返回结果解密
		String resultStr = Des3.decode(str);
		// 将解密后的结果 装换result
		result = JacksonUtil.str2Obj(resultStr, ObjectResult.class);
		// 处理返回结果
		if (result.getStatusCode() == 0) {
		    apiResult.setResult(result.getObject());
			apiResult.setCodeMsg(MsgCons.C_10000, result.getMsg());
		} else {
			logger.error("出错了"+result.getMsg());
			apiResult.withError(MsgCons.C_20000, result.getMsg());
		}
		return apiResult;
	}
	 public  Map<String, Object> des3Map(Map<String, Object> map){
			   String json = GSONUtils.getGson().toJson(map);
				Map<String, Object> temp = new HashMap<String, Object>();
				try {
					temp.put("param", Des3.encode(json));
				} catch (Exception e) {
					return map;
				}
		   return temp;
	   }
}

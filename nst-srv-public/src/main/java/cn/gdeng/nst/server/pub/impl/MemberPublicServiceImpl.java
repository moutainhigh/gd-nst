package cn.gdeng.nst.server.pub.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gdeng.nst.api.vo.pub.MemberPublicVO;
import cn.gdeng.nst.dao.BaseDao;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.pub.service.MemberPublicService;
import cn.gdeng.nst.util.server.InterfaceUtil;
import cn.gdeng.nst.util.server.JacksonUtil;
import cn.gdeng.nst.util.server.jodis.JodisTemplate;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.Des3;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.JSONUtils;
import cn.gdeng.nst.util.web.api.ObjectResult;
import cn.gdeng.nst.util.web.api.TokenCreater;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 农速通会员公共实现类
 * 
 * @author xiaojun
 */
@Service
public class MemberPublicServiceImpl implements MemberPublicService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterfaceUtil interfaceUtil;
	@Autowired
	private JodisTemplate jodisTemplate;
	@Autowired
	private BaseDao<?> baseDao;
	/** 超时时间6个月 **/
	private final static int TIME_OUT = 6*30*24*60*60;

	/**
	 * 获取验证码
	 */
	@Override
	public ApiResult<?> getVerifyCode(Map<String, Object> param) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		// 调用接口
		String resultStr = interfaceUtil.getVerifyCode(param);
		result = pubMethod(resultStr);
		return result;
	}

	/**
	 * 检验验证码 下一步
	 */
	@Override
	public ApiResult<?> registerNext(Map<String, Object> param) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		// 调用接口
		String resultStr = interfaceUtil.registerNext(param);
		result = pubMethod(resultStr);
		return result;
	}

	/**
	 * 设置密码注册
	 */
	@Override
	public ApiResult<?> register(Map<String, Object> param) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		// 调用接口
		String resultStr = interfaceUtil.register(param);
		result = pubMethod(resultStr);
		return result;
	}

	/**
	 * 登录
	 */
	@Override
	public ApiResult<?> login(Map<String, Object> param,String deviceType,String deviceTokens,String deviceApp,String appVersion) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		// 调用接口
		String resultStr = interfaceUtil.login(param);
		result = loginMethod(resultStr,deviceType,deviceTokens,deviceApp,appVersion);
		return result;
	}

	/**
	 * 1.忘记密码获取验证码下一步 2.修改手机号码下一步
	 */
	@Override
	public ApiResult<?> findPasswordNext(Map<String, Object> param) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		// 调用接口
		String resultStr = interfaceUtil.findPasswordNext(param);
		result = pubMethod(resultStr);
		return result;
	}
	
	@Override
	public ApiResult<?> selectServiceType(Map<String, Object> paramMap) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		try {
			baseDao.execute("member.updateMemberServiceType", paramMap);
		} catch (Exception e) {
			logger.info("选择业务类型失败", e);
			throw new BizException(MsgCons.C_21003, MsgCons.M_21003);
		}
		return result;
	}
	
	@Override
	public ApiResult<?> updateMobile(Map<String, Object> param) throws Exception {
		ApiResult<?> result = new ApiResult<>();
		// 调用接口
		String resultStr = interfaceUtil.updateMobile(param);
		result = pubMethod(resultStr);
		return result;
	}

	/**
	 * 公共处理方法 参数值 传入调用接口 返回字符串
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

	/**
	 * 登录返回接口处理方法
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private ApiResult<Map<String, Object>> loginMethod(String str,String deviceType,String deviceTokens,String deviceApp,String appVersion) throws Exception {
		// 农速通api返回结果类
		ApiResult<Map<String, Object>> apiResult = new ApiResult<>();
		// 谷登api返回结果类
		ObjectResult result = null;
		// 将返回结果解密
		String resultStr = Des3.decode(str);
		logger.debug("login in gd result: "+str);
		logger.debug("login in gd result: "+resultStr);
		Map<String, Object> resultMap = new HashMap<>();
		// 将解密后的结果 装换result
		result = JacksonUtil.str2Obj(resultStr, ObjectResult.class);
		// 处理返回结果
		if (result.getStatusCode() == 0) {
			apiResult.setCodeMsg(MsgCons.C_10000, result.getMsg());
			if (result.getObject() != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) result.getObject();
				// 拼装map给前端
				resultMap.put("sid", map.get("sid"));
				resultMap.put("memberId", map.get("memberId"));
				resultMap.put("mobile", map.get("mobile"));
				resultMap.put("level", map.get("level"));
				resultMap.put("deviceType", deviceType);
				resultMap.put("deviceApp", deviceApp);
				resultMap.put("appVersion", appVersion);
				// 生成token
				String token = TokenCreater.newId();
				// 放入缓存中
				jodisTemplate.setex(token,TIME_OUT ,JSONUtils.toJSONString(resultMap));
				resultMap.put("token", token);
				getMemberServiceStatus(resultMap);
				apiResult.setResult(resultMap);
				Map<String, Object> updateMemberMap=new HashMap<>();
				updateMemberMap.put("memberId", map.get("memberId"));
				updateMemberMap.put("deviceType", deviceType);
				updateMemberMap.put("deviceTokens", deviceTokens);
				updateMemberMap.put("deviceApp", deviceApp);
				updateMemberInfo(updateMemberMap);
			}
		} else {
			logger.error("出错了"+result.getMsg());
			apiResult.withError(MsgCons.C_20000, result.getMsg());
		}
		return apiResult;
	}
	/**
	 * 获取会员业务所需字段
	 * @param resultMap
	 */
	private void getMemberServiceStatus(Map<String, Object> resultMap){
		MemberPublicVO memberVO=baseDao.queryForObject("member.getServiceStatus", resultMap, MemberPublicVO.class);
		if (memberVO!=null) {		
			//会员认证状态   2 认证通过 3认证不通过
			resultMap.put("cerStatus", memberVO.getCerStatus());
			//现在业务状态  1 干线业务 2 同城业务 0表示没有选择
			resultMap.put("serviceType", memberVO.getServiceType());
			//联系人
			resultMap.put("userName", memberVO.getUserName());
			//真实姓名
			resultMap.put("realName", memberVO.getRealName());
		}else {
			//会员认证状态  2 认证通过 3认证不通过
			resultMap.put("cerStatus", "3");
			//现在业务状态  1 干线业务 2 同城业务 0表示没有选择
			resultMap.put("serviceType", "0");
			//联系人
			resultMap.put("userName", "");
			//真实姓名
			resultMap.put("realName", "");
		}
	}
	/**
	 * 更新会员信息
	 * 
	 * @param map
	 */
	private void updateMemberInfo(final Map<String, Object> updateMemberMap) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				baseDao.execute("member.updateMemberInfo", updateMemberMap);
			}
		}).start();
	}

    @Override
    public ApiResult<?> updateUserName(Map<String, Object> param) throws Exception{
        ApiResult<?> result = new ApiResult<>();
        String resultStr = interfaceUtil.updateUserName(param);
        result = pubMethod(resultStr);
        return result;
    }

    @Override
    public ApiResult<?> bankCardInfo(Map<String, Object> paramMap) throws Exception {
        ApiResult<?> result = new ApiResult<>();
        String resultStr = interfaceUtil.bankCardInfo(paramMap);
        result = pubMethod(resultStr);
        return result;
    }
    
    @Override
    public ApiResult<?> saveBankCardInfo(Map<String, Object> paramMap) throws Exception {
        ApiResult<?> result = new ApiResult<>();
        String resultStr = interfaceUtil.saveBankCardInfo(paramMap);
        result = pubMethod(resultStr);
        return result;
    }

    @Override
    public ApiResult<?> updateBankCardInfo(Map<String, Object> paramMap) throws Exception {
        ApiResult<?> result = new ApiResult<>();
        String resultStr = interfaceUtil.updateBankCardInfo(paramMap);
        result = pubMethod(resultStr);
        return result;
    }

    @Override
    public ApiResult<?> bankCardType(Map<String, Object> paramMap) throws Exception {
        ApiResult<?> result = new ApiResult<>();
        String resultStr = interfaceUtil.bankCardType(paramMap);
        result = pubMethod(resultStr);
        return result;
    }

	@Override
	public ApiResult<?> appStart(Map<String, Object> param,Object appVersion, String token) throws Exception {
	    //调用统计接口
		String resuestData = interfaceUtil.appStart(param);
		logger.info(Des3.decode(resuestData));
		//刷新缓存用户信息appVersion
		if(null != token
		    && !"".equals(token.trim())){
		 String memberInoJson =  jodisTemplate.get(token);
		 if(null == memberInoJson
		     || "".equals(memberInoJson.trim())){
		   logger.debug("\n get token null");
		   return null;
		 }
		 Map<String, Object> map = GSONUtils.fromJson(memberInoJson);
		 map.put("appVersion", appVersion);
		 jodisTemplate.setex(token,TIME_OUT ,JSONUtils.toJSONString(map));
		 logger.debug(token+" app version refresh success----->"+memberInoJson+"\n new version-->"+appVersion);
		}else{
		  logger.debug("\n no token");
		}
		
		return null;
	}

   
}

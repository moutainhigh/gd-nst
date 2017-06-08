package cn.gdeng.nst.web.controller.pub;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gdeng.nst.api.server.member.MemberInfoApiService;
import cn.gdeng.nst.api.vo.member.MemberInfoVo;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.pub.service.MemberPublicService;
import cn.gdeng.nst.util.server.jodis.JodisTemplate;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.TokenCreater;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 会员公共Controller
 * 
 * @author xiaojun
 */
@Controller
@RequestMapping("v1/member")
public class MemberPublicController extends BaseController {
	@Reference
	private MemberInfoApiService memberApiService;
	@Reference
	private MemberPublicService memberPublicService;
	@Autowired
	private JodisTemplate jodisTemplate;

	/**
	 * 获取会员个人信息
	 */
	@ResponseBody
	@RequestMapping("memberInfo")
	public Object membmembererInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);
		ApiResult<MemberInfoVo> result = memberApiService.findMember(paramMap);
		return result;
	}

	/**
	 * 修改会员个人信息
	 */
	@ResponseBody
	@RequestMapping("updateMember")
	public Object memberInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);
		ApiResult<Integer> result = memberApiService.updateByMap(paramMap);
		return result;
	}

	/**
	 * 修改联系人
	 */
	@ResponseBody
	@RequestMapping("updateUserName")
	public Object updateUserName(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getParamMap(request);
		return memberPublicService.updateUserName(paramMap);
	}

	/**
	 * 绑定银行卡信息
	 */
	@ResponseBody
	@RequestMapping("bankCard/save")
	public Object saveBankCardInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getParamMap(request);
		return memberPublicService.saveBankCardInfo(paramMap);
	}

	/**
	 * 查看绑定的银行卡
	 */
	@ResponseBody
	@RequestMapping("bankCard/getInfo")
	public Object bankCardInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getParamMap(request);
		return memberPublicService.bankCardInfo(paramMap);
	}

	/**
	 * 修改绑定银行卡信息
	 */
	@ResponseBody
	@RequestMapping("bankCard/update")
	public Object updateBankCardInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getParamMap(request);
		return memberPublicService.updateBankCardInfo(paramMap);
	}

	/**
	 * 获取银行卡类型
	 */
	@ResponseBody
	@RequestMapping("bankCard/getType")
	public Object bankCardType(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = getParamMap(request);
		return memberPublicService.bankCardType(paramMap);
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getVerifyCode")
	@ResponseBody
	public Object getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = getParamMap(request);
		return memberPublicService.getVerifyCode(map);
	}

	/**
	 * 检验验证码（下一步）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("registerNext")
	@ResponseBody
	public Object registerNext(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 参数传递需要将mobile转化成account
		Map<String, Object> map = getParamMap(request);
		return memberPublicService.registerNext(map);
	}

	/**
	 * 设置密码 点击注册 注： 如果 必传*, 0为未注册， 1为已注册(表示忘记密码进行修改)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("registerOrUpdatePassword")
	@ResponseBody
	public Object registerOrUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = getParamMap(request);
		return memberPublicService.register(map);
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonStr = getParamDecodeStr(request);
		Map<String, Object> checkMap = getDecodeMap(request);
		checkLoginParam(checkMap);
		// 设备类型 0 android 1 ios
		String deviceType = (String) GSONUtils.getJsonValueStr(jsonStr, "deviceType");
		// 友盟消息推送服务对设备的唯一标识
		String deviceTokens = (String) GSONUtils.getJsonValueStr(jsonStr, "device_tokens");
		// 当前登录APP类型：1 货主 2 车主 3 物流公司
		String deviceApp = (String) GSONUtils.getJsonValue(jsonStr, "deviceApp");
		// app版本
		String appVersion = (String) GSONUtils.getJsonValue(jsonStr, "appVersion");
		// 参数传递需要将mobile转化成account
		Map<String, Object> map = getParamMap(request);
		return memberPublicService.login(map, deviceType, deviceTokens, deviceApp, appVersion);
	}

	/**
	 * 找回密码 和 根据手机号码重置号码 下一步
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findPasswordOrResetPasswordNext")
	@ResponseBody
	public Object findPasswordOrResetPasswordNext(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 参数传递需要将mobile转化成account
		Map<String, Object> map = getParamMap(request);
		return memberPublicService.findPasswordNext(map);
	}

	/**
	 * 修改手机号码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateMobile")
	@ResponseBody
	public Object updateMobile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 参数传递需要将mobile转化成account
		Map<String, Object> map = getParamMap(request);
		return memberPublicService.updateMobile(map);
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getToken")
	@ResponseBody
	public Object getToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonStr = getParamDecodeStr(request);
		// 存入token
		String token = TokenCreater.newId();
		jodisTemplate.set(token, jsonStr);
		logger.info("获取token成功" + jsonStr);
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		ApiResult<Map<String, Object>> apiResult = new ApiResult<Map<String, Object>>(map, MsgCons.C_10000,
				MsgCons.M_10000);
		return apiResult;
	}

	/**
	 * 退出系统
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "logout")
	@ResponseBody
	public Object logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonStr = getParamDecodeStr(request);
		String token = (String) GSONUtils.getJsonValueStr(jsonStr, "token");
		// 退出 清楚缓存token
		jodisTemplate.del(token);
		ApiResult<?> apiResult = new ApiResult<String>("", MsgCons.C_10000, MsgCons.M_10000);
		return apiResult;
	}

	/**
	 * 业务类型选择
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "selectServiceType")
	@ResponseBody
	public Object selectServiceType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = getDecodeMap(request);
		checkParam(paramMap);
		return memberPublicService.selectServiceType(paramMap);
	}

	/**
	 * 更新业务 检查字段
	 * 
	 * @param paramMap
	 * @throws BizException
	 */
	public void checkParam(Map<String, Object> paramMap) throws BizException {
		// 用户id不能为空
		DataCheckUtils.assertIsNonNull("用户不能为空", paramMap.get("memberId"));
		// 业务类型不能空
		DataCheckUtils.assertIsNonNull("业务类型不能为空", paramMap.get("serviceType"));
	}
	/**
	 * 检查登录所需字段
	 * @param paramMap
	 * @throws BizException
	 */
	private void checkLoginParam(Map<String, Object> paramMap) throws BizException {
		// 设备类型 0 android 1 ios
		DataCheckUtils.assertIsNonNull("设备类型不能为空", paramMap.get("deviceType"));
		// 友盟消息推送服务对设备的唯一标识
		DataCheckUtils.assertIsNonNull("友盟标示不能为空", paramMap.get("device_tokens"));
		//当前登录APP类型：1 货主 2 车主 3 物流公司
		DataCheckUtils.assertIsNonNull("登录客戶端类型不能为空", paramMap.get("deviceApp"));
	}

}

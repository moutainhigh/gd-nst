package cn.gdeng.nst.web.controller.order;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gdeng.nst.api.dto.order.OrderPayDTO;
import cn.gdeng.nst.api.server.order.OrderBeforeService;
import cn.gdeng.nst.api.server.order.OrderInfoService;
import cn.gdeng.nst.api.server.order.OrderInfoTransService;
import cn.gdeng.nst.entity.nst.AccessSysConfigEntity;
import cn.gdeng.nst.enums.MsgCons;
import cn.gdeng.nst.util.server.Base64;
import cn.gdeng.nst.util.web.api.AccessSysSignUtil;
import cn.gdeng.nst.util.web.api.ApiResult;
import cn.gdeng.nst.util.web.api.AppVersionUtil;
import cn.gdeng.nst.util.web.api.BizException;
import cn.gdeng.nst.util.web.api.GSONUtils;
import cn.gdeng.nst.util.web.api.GdProperties;
import cn.gdeng.nst.util.web.api.JSONUtils;
import cn.gdeng.nst.util.web.api.NumUtils;
import cn.gdeng.nst.util.web.api.SerializeUtil;
import cn.gdeng.nst.web.controller.base.BaseController;
import cn.gdeng.nst.web.util.DataCheckUtils;

/**
 * 订单支付control
 * 
 * @author xiaojun
 */
@Controller
@RequestMapping("v1/pay")
public class OrderPayController extends BaseController {

	@Autowired
	private GdProperties gdProperties;
	@Reference
	private OrderBeforeService orderBeforeService;
	/**
	 * 订单信息物流接口
	 */
	@Reference
	private OrderInfoTransService orderInfoTransService;
	/**
	 * 订单信息接口
	 */
	@Reference
	private OrderInfoService orderInfoService;

	/**
	 * 确认支付
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("confirmPay")
	@ResponseBody
	public Object confirmPay(HttpServletRequest request) throws Exception {
		OrderPayDTO payDto = getDecodeDto(request, OrderPayDTO.class);
		checkPayData(payDto);
		String url = payHandleParamUrl(payDto, request);
		ApiResult<String> result = new ApiResult<String>(url, MsgCons.C_10000, MsgCons.M_10000);
		return result;
	}

	/**
	 * 组合支付参数 返回url
	 */
	private String payHandleParamUrl(OrderPayDTO payDto, HttpServletRequest request) throws Exception {
		// 获取公用回传参数，封装json中
		Map<String, Object> map = new HashMap<>();
		map.put("orderType", payDto.getOrderType());
		// 如果是信息订单则是信息id ,货运订单就是sourceId
		map.put("id", payDto.getId());
		map.put("sourceId", payDto.getSourceId());
		// 传入司机memberId
		map.put("driverMemberId", payDto.getDriverMemberId());
		// 传入货主memberId
		map.put("shipperMemberId", payDto.getShipperMemberId());
		String reParamJsonStr = JSONUtils.toJSONString(map);
		// 将回调参数以base64进行传输
		String reParam = Base64.encode(SerializeUtil.serialize(reParamJsonStr));
		// 获取ip地址
		String requestIp = request.getRemoteAddr();
		// 组装部分dto
		payDto.setRequestIp(requestIp);
		// 将金额设置为最多2位小数
		payDto.setPayAmt(NumUtils.format(Double.valueOf(payDto.getPayAmt()), 0, 2).replace(",", ""));
		payDto.setTotalAmt(payDto.getPayAmt());
		payDto.setReParam(reParam);
		// 处理dto 得到所需参数校验秘钥paramMap参数
		Map<String, String> paramMap = new HashMap<>();
		this.paramMapUrl(payDto, paramMap, request);
		// 获取私钥 并生成密文
		String privateKey = gdProperties.getPrivateKey();
		String link = AccessSysSignUtil.createLinkString(AccessSysSignUtil.paraFilter(paramMap));
		String sign = AccessSysSignUtil.sign(link, gdProperties.getKeyType(), privateKey);
		sign=Base64.encode(sign.getBytes());
		String url = gdProperties.getCenterPayUrl() + "?" + link + "&sign=" + sign;
		return url;
	}

	/**
	 * 校验秘钥所需map参数
	 * 
	 * @param dto
	 * @param paramMap
	 * @return
	 */
	private Map<String, String> paramMapUrl(OrderPayDTO dto, Map<String, String> paramMap,HttpServletRequest request) throws Exception{
		paramMap.put("version", gdProperties.getVersion());
		if ("1".equals(dto.getOrderType())) {
			paramMap.put("appKey", gdProperties.getAppKeyCar());
		}else {
			paramMap.put("appKey", gdProperties.getAppKeyGoods());
		}
		paramMap.put("timeOut", gdProperties.getTimeOut());
		paramMap.put("keyType", gdProperties.getKeyType());
		paramMap.put("returnUrl", gdProperties.getReturnUrl());
		paramMap.put("notifyUrl", gdProperties.getNotifyUrl());
		paramMap.put("title", gdProperties.getTitle());
		paramMap.put("orderNo", dto.getOrderNo());
		paramMap.put("orderTime", dto.getOrderTime());
		paramMap.put("payerUserId", dto.getPayerUserId());
		//账号使用手机号码
		paramMap.put("payerAccount", dto.getPayerMobile());
		paramMap.put("payerMobile", dto.getPayerMobile());
		paramMap.put("payerName", dto.getPayerName());
		paramMap.put("payeeUserId", dto.getPayeeUserId());
		//账号使用手机号码
		paramMap.put("payeeAccount", dto.getPayeeMobile());
		paramMap.put("payeeMobile", dto.getPayeeMobile());
		paramMap.put("payeeName", dto.getPayeeName());
		paramMap.put("totalAmt", dto.getTotalAmt());
		paramMap.put("payAmt", dto.getPayAmt().toString());
		paramMap.put("reParam", dto.getReParam());
		paramMap.put("requestIp", dto.getRequestIp());
		//是否支持微信支付 不传也表示无 默认0无 1支持微信支付
		paramMap.put("hasWeixinPay",(true == AppVersionUtil.isWebChat(getAppInfo(request))?"1":"0"));
		return paramMap;
	}

	/**
	 * 检查支付数据
	 * 
	 * @param dto
	 * @throws BizException
	 */
	private void checkPayData(OrderPayDTO dto) throws BizException {
		DataCheckUtils.assertIsNonNull("支付金额不能为空", dto.getPayAmt());
		DataCheckUtils.assertIsNonNull("订单类型不能为空", dto.getOrderType());
		DataCheckUtils.assertIsNonNull("订单号不能为空", dto.getOrderNo());
		DataCheckUtils.assertIsNonNull("付款方手机号不能为空", dto.getPayerMobile());
		DataCheckUtils.assertIsNonNull("付款方用户ID不能空", dto.getPayerUserId());
		DataCheckUtils.assertIsNonNull("付款方姓名不能为空", dto.getPayerName());
		DataCheckUtils.assertIsNonNull("收款方手机号不能为空", dto.getPayeeMobile());
		DataCheckUtils.assertIsNonNull("收款方用户ID不能为空", dto.getPayeeUserId());
		DataCheckUtils.assertIsNonNull("收款方姓名不能为空", dto.getPayeeName());
		DataCheckUtils.assertIsNonNull("下单时间不能为空", dto.getOrderTime());
		if ("2".equals(dto.getOrderType())) {
			DataCheckUtils.assertIsNonNull("货源ID不能为空", dto.getSourceId());
		}
	}

	/**
	 * 支付成功查询订单详情
	 * 
	 * @param request
	 * @param orderPayDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOrderDetail")
	public String queryOrderDetails(HttpServletRequest request, OrderPayDTO dto){
		try {
			Map<String, String> paramMap = new HashMap<>();
			this.getParamMap(dto, paramMap);
			// 获取订单详情map
			Map<String, Object> map = this.getParamMap(paramMap, "", dto);
			if(paramMap.containsKey("respCode")
					&&paramMap.get("respCode").equals("10000")){
				return "pay/paySuccess";
			}else{
				return "pay/payFailer";
			}
		} catch (Exception e) {
			logger.error("",e);
			return "pay/payFailer";
		}
	}

	/**
	 * 获取参数拼装Map
	 * 
	 * @param paramMap
	 * @return
	 * @throws BizException
	 */
	private Map<String, Object> getParamMap(Map<String, String> paramMap, String str, OrderPayDTO dto)
			throws Exception {
		// 获取签名方式
		String keyType = paramMap.get("keyType");
		// 获取签名
		String  sign=new String(Base64.decode(dto.getSign()), "utf-8");
		// 获取公共回调参数
		String reParam = paramMap.get("reParam");
		Object reParamObject = SerializeUtil.unserialize(Base64.decode(reParam));
		boolean isTrue = checkSign(paramMap, sign, keyType);
		if (!isTrue) {
			throw new BizException(MsgCons.C_25000, MsgCons.M_25000);
		}
		Map<String, Object> map = GSONUtils.fromJson(reParamObject.toString());
		return map;
	}

	/**
	 * 获取支付中心回调参数
	 * 
	 * @return
	 */
	private Map<String, String> getParamMap(OrderPayDTO dto, Map<String, String> paramMap) throws Exception {
		paramMap.put("version", dto.getVersion());
		paramMap.put("appKey", dto.getAppKey());
		paramMap.put("orderNo", dto.getOrderNo());
		paramMap.put("timeOut", dto.getTimeOut());
		paramMap.put("payCenterNumber", dto.getPayCenterNumber());
		paramMap.put("thirdPayNumber", dto.getThirdPayNumber());
		paramMap.put("payAmt", dto.getPayAmt());
		paramMap.put("reParam", dto.getReParam());
		paramMap.put("payType", dto.getPayType());
		paramMap.put("title", dto.getTitle());
		paramMap.put("keyType", dto.getKeyType());
		paramMap.put("payStatus", dto.getPayStatus());
		paramMap.put("payerUserId", dto.getPayerUserId());
		paramMap.put("payerAccount", dto.getPayerAccount());
		paramMap.put("payerMobile", dto.getPayerMobile());
		paramMap.put("payerName", dto.getPayerName());
		paramMap.put("payeeUserId", dto.getPayeeUserId());
		paramMap.put("payeeAccount", dto.getPayeeAccount());
		paramMap.put("payeeMobile", dto.getPayeeMobile());
		paramMap.put("payeeName", dto.getPayeeName());
		paramMap.put("totalAmt", dto.getTotalAmt());
		paramMap.put("respCode", dto.getRespCode());
		paramMap.put("respMsg", dto.getRespMsg());
		paramMap.put("returnUrl", dto.getReturnUrl());
		paramMap.put("notifyUrl", dto.getNotifyUrl());
		return paramMap;
	}

	/**
	 * 校验签名
	 * 
	 * @param paramMap
	 * @param sign
	 * @param keyType
	 * @return
	 */
	private boolean checkSign(Map<String, String> paramMap, String sign, String keyType) {
		AccessSysConfigEntity asce = new AccessSysConfigEntity();
		asce.setPublicKey(gdProperties.getPublicKey());
		asce.setKeyType(keyType);
		boolean isTrue = AccessSysSignUtil.verifySign(paramMap, sign, asce);
		return isTrue;
	}
	
	
	/**
	 * 预付款支付成功后的请求处理。
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("payPrePaymenSucc")
	@ResponseBody
	public Object payPrePaymenSucc(HttpServletRequest request) throws Exception {
		Map<String, String> params = super.getDecodeMapStr(request);
		String orderNo = params.get("orderNo");
		DataCheckUtils.assertIsNonNull(MsgCons.C_23033, MsgCons.M_23033, orderNo);
		return orderInfoTransService.saveForPrePaymentSucc(orderNo);
	}
	/**
	 * 尾款支付成功后的请求处理。
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("payFinalPaymenSucc")
	@ResponseBody
	public Object payFinalPaymenSucc(HttpServletRequest request) throws Exception {
		Map<String, String> params = super.getDecodeMapStr(request);
		String orderNo = params.get("orderNo");
		DataCheckUtils.assertIsNonNull(MsgCons.C_23033, MsgCons.M_23033, orderNo);
		return orderInfoTransService.payFinalPaymentSuccHandler(orderNo);
	}
	
	/**
	 * 退掉预付款成功后的请求处理。
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("repealPrePaymentSucc")
	@ResponseBody
	public Object repealPrePaymentSucc(HttpServletRequest request) throws Exception {
		Map<String, String> params = super.getDecodeMapStr(request);
		String orderNo = params.get("orderNo");
		DataCheckUtils.assertIsNonNull(MsgCons.C_23033, MsgCons.M_23033, orderNo);
		return orderInfoTransService.repealPrePaymentSucc(orderNo);
	}
	/**
	 * 支付预付款过期的处理
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("payPrePaymenOverdue")
	@ResponseBody
	public Object payPrePaymenOverdue(HttpServletRequest request) throws Exception {
		Map<String, String> params = super.getDecodeMapStr(request);
		String orderNos = params.get("orderNos");
		DataCheckUtils.assertIsNonNull(MsgCons.C_23033, MsgCons.M_23033, orderNos);
		String[] orderNosArr = orderNos.split(",");
		DataCheckUtils.assertIsGreater(orderNosArr.length, 0, MsgCons.C_23020, MsgCons.M_23020);
		
		return orderInfoTransService.payPrePaymtenOverdue(orderNosArr);
	}
	
	
	
}

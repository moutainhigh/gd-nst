package cn.gdeng.nst.util.web.api;


import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.gdeng.nst.entity.nst.ThirdPayConfigEntity;


public class AlipayConfigUtil {
	
	public static AlipayConfig buildAlipayConfig(ThirdPayConfigEntity cf) throws BizException{
		try {
			AlipayConfig c = new AlipayConfig();
			String paramJson = cf.getParamJson();
			JSONObject json = (JSONObject)JSON.parse(paramJson);
			String partner = json.getString("partner");
			
			String notifyUrl = json.getString("notifyUrl");
			String returnUrl =  json.getString("returnUrl");
			if(StringUtils.isEmpty(partner) || StringUtils.isEmpty(notifyUrl)
					|| StringUtils.isEmpty(returnUrl)){
				throw new BizException(20006, "系统接入支付方式配置错误，praramJson配置错误");
			}
			c.setPartner(partner);
			c.setSeller_id(partner);
			c.setNotify_url(notifyUrl);
			c.setReturn_url(returnUrl);
			setKey(c,cf);
			return c;
		} catch (Exception e) {
			throw new BizException(20006, "系统接入支付方式配置错误，praramJson配置错误");
		}
		
	}
	
	private static void setKey(AlipayConfig cf,ThirdPayConfigEntity config){
		cf.setKey(config.getMd5Key());
		cf.setKeyType(config.getKeyType());
		cf.setPrivateKey(config.getPrivateKey());
		cf.setPublicKey(config.getPublicKey());
		cf.setAlipayPublicKey(config.getSysPublicKey());
	}

	public static class AlipayConfig{
		
		private String partner;
		
		private String seller_id;
		
		private String appId;
		
		private String input_charset="utf-8";
		
		private String payment_type="1";
		
		private String service="alipay.wap.create.direct.pay.by.user";
		
		private String gateWay="https://mapi.alipay.com/gateway.do?";
		
		private String notify_url;
		
		private String return_url;
		
		private String key;
		
		private String keyType;
		
		private String privateKey;
		
		private String publicKey;
		
		private String alipayPublicKey;

		public String getPartner() {
			return partner;
		}

		public void setPartner(String partner) {
			this.partner = partner;
		}

		public String getSeller_id() {
			return seller_id;
		}

		public void setSeller_id(String seller_id) {
			this.seller_id = seller_id;
		}

		public String getInput_charset() {
			return input_charset;
		}

		public void setInput_charset(String input_charset) {
			this.input_charset = input_charset;
		}

		public String getPayment_type() {
			return payment_type;
		}

		public void setPayment_type(String payment_type) {
			this.payment_type = payment_type;
		}

		public String getService() {
			return service;
		}

		public void setService(String service) {
			this.service = service;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getNotify_url() {
			return notify_url;
		}

		public void setNotify_url(String notify_url) {
			this.notify_url = notify_url;
		}

		public String getReturn_url() {
			return return_url;
		}

		public void setReturn_url(String return_url) {
			this.return_url = return_url;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(String privateKey) {
			this.privateKey = privateKey;
		}

		public String getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(String publicKey) {
			this.publicKey = publicKey;
		}

		public String getAlipayPublicKey() {
			return alipayPublicKey;
		}

		public void setAlipayPublicKey(String alipayPublicKey) {
			this.alipayPublicKey = alipayPublicKey;
		}

		public String getGateWay() {
			return gateWay;
		}

		public void setGateWay(String gateWay) {
			this.gateWay = gateWay;
		}

		public String getKeyType() {
			return keyType;
		}

		public void setKeyType(String keyType) {
			this.keyType = keyType;
		}

	}
}

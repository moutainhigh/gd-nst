var NSTApi = {};
NSTApi.client = function(path){
	//编写公共服务
};
NSTApi.client.prototype = {
	/**
	 * 获取请求参数
	 */
	getReqParam : function(name){
		var url = location.search;
		if (url.indexOf("?") != -1) { 
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(var i = 0; i < strs.length; i++){ 
				this[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]); 
			}
			return this[name];
	   } 
	   return null; 
	},
	/**
	 * 判断移动终端浏览器版本
	 */
	appVersion: (function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
        	android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
        };
   	})(),
	/**
	 * Android IOS 参数加密处理
	 */
	enCode : function(anVal, iosVal, cbName, callback){
		// ios处理
		if(this.appVersion.ios){	
			window[cbName] = callback;
			window.webkit.messageHandlers.NativeEncrypt.postMessage(iosVal);
		}
		// 安卓处理
		else if(this.appVersion.android){
			return JavaH5WebInterface.enCode(anVal);
		}
	},
	/**
	 * Android IOS 返回结果解密处理
	 */
	deCode : function(anVal, iosVal, cbName, callback){
		if(this.appVersion.ios){
			window.webkit.messageHandlers.NativeDecrypt.postMessage(iosVal);
			window[cbName] = callback;
		}
		else if(this.appVersion.android){
			return JSON.parse(JavaH5WebInterface.deCode(anVal));
		}
	},
	/**
	 * 调用客户端打电话功能
	 */
	call : function(phone){
		if(this.appVersion.ios){	
			window.webkit.messageHandlers.NativeMakeCall.postMessage(phone);
		}
		else if(this.appVersion.android){
			JavaH5WebInterface.callMobile(phone);
		}
	}
};

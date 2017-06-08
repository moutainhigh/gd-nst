var $param = decodeURIComponent(location.search).replace("?","");
		var $map = paramMap($param);
		var listing;
		function search(key) { 
		 for(var j = 0; j < $map.length; j ++ ){
		   if(key == $map[j].key) { 
		    return $map[j].value;  
		    } 
		 } 
		  return null; 
	    } 
	    function paramMap(source) {
	      var array_one = source.split("&");
	      var array_two;
	      var map = new Array();
	      for(var i = 0; i < array_one.length; i ++) {
	        array_two = array_one[i].split("=");
	          map.push(new bean(array_two[0], array_two[1])); 
	       } 
	        return map; 
	    }
	    function bean(key, value) {
	      this.key = key;
	      this.value = value;
	      return this;
	    }

$.extend($, {
	// 描述：<br />判断移动终端浏览器版本
	appVersion: (function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
        	android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
        };
   	})()
});








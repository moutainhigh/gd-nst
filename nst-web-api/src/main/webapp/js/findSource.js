function getRootPath_web() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPaht;
}

var CONTEXT = getRootPath_web();
/*if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
	var sourceType = null, carType =null, carLength=null, s_provinceId= null, s_cityId=null,s_areaId= null, e_provinceId= null, e_cityId=null,e_areaId= null;
} else if (/(Android)/i.test(navigator.userAgent)) {*/
	var sourceType = "", carType ="", carLength="", s_provinceId= "", s_cityId="",s_areaId= "", e_provinceId= "", e_cityId="",e_areaId= "",recommend=1;
//} 
//var sourceType = null, carType =null, carLength=null, s_provinceId= null, s_cityId=null,s_areaId= null, e_provinceId= null, e_cityId=null,e_areaId= null;
	var sadr_text = "出发地";
	var count = 0, reqNum =0;
	var hasNextPage = true;
	var adrType;	
	//localStorage.clear();
	var storLen = localStorage.length;
	var allAdr;
	$(".find-main").height(screen.height);
	//获取数据
	function getData(){
		
		try 
		{
			//window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'pageNum\':\''+count+'\',\'pageSize\':\'10\',\'s_provinceId\':\''+s_provinceId+'\'}'+'","fun":"encryptCompleted"}');
			window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'pageNum\':\''+count+'\',\'pageSize\':\'10\',\'sourceType\':\''+sourceType+'\',\'carType\':\''+carType+'\',\'carLength\':\''+carLength+'\',\'s_provinceId\':\''+s_provinceId+'\',\'s_cityId\':\''+s_cityId+'\',\'s_areaId\':\''+s_areaId+'\',\'e_provinceId\':\''+e_provinceId+'\',\'e_cityId\':\''+e_cityId+'\',\'e_areaId\':\''+e_areaId+'\',\'visitSource\':\''+3+'\',\'recommend\':\''+recommend+'\'}'+'","fun":"encryptCompleted"}');
		}catch(e){}
		//安卓调用
		try 
		{
			var andData = JavaH5WebInterface.enCode("pageNum:"+count+"&pageSize:"+10+"&sourceType:"+sourceType+"&carType:"+carType+"&carLength:"+carLength+"&s_provinceId:"+s_provinceId+"&s_cityId:"+s_cityId+"&s_areaId:"+s_areaId+"&e_provinceId:"+e_provinceId+"&e_cityId:"+e_cityId+"&e_areaId:"+e_areaId+"&visitSource:"+3+"&recommend:"+recommend);
			requestajax(andData);
			//requestAll(andData)
		}catch(e){}
	}
	

	mui.init({
		pullRefresh: {
			contentdown : "下拉可以刷新",//可选，在下拉可刷新状态时，下拉刷新控件上显示的标题内容
		    contentover : "释放立即刷新",//可选，在释放可刷新状态时，下拉刷新控件上显示的标题内容
			contentrefresh: '正在刷新...',
			container: '#pullrefresh',
			down: {
				callback: pulldownRefresh
			},
			up: {
				contentrefresh: '正在加载...',
				callback: pullupRefresh
			}
		}
	});
	/**
	 * 下拉刷新具体业务实现
	 */
	function pulldownRefresh() {
		setTimeout(function() {			
			count =0;
			$('#sourceWrap').empty();
			getData()
			mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
		}, 500);
	};
	$("#adrTest").html("adrTest"+hasNextPage);

	/**
	 * 上拉加载具体业务实现
	 */
	//mui('#pullrefresh').pullRefresh().refresh(true);
	function pullupRefresh() {	
		/*if(count<2){
			$('#sourceWrap').empty();
		}*/
		setTimeout(function() {
			mui('#pullrefresh').pullRefresh().endPullupToRefresh(!hasNextPage); //参数为true代表没有更多数据了。
			//if(hasNextPage){
				getData()
			//}
		}, 500);
		count++;
	}
	//getData();
	if (mui.os.plus) {
		mui.plusReady(function() {
			setTimeout(function() {
				//count = 1;
				//getData();
				mui('#pullrefresh').pullRefresh().pullupLoading();
			}, 500);

		});
	} else {
		mui.ready(function() {
			//count = 1;
		//getData();
			mui('#pullrefresh').pullRefresh().pullupLoading();
		});
	}
	
	//点击出发地，目的地
$(".eadr").on("tap",function(){
	adrType = $(this).attr("adr-type")
	$('#sourceWrap').empty();
	$(".addressMain").show();
	$(".indexMain").hide();
	$(".provMain").show();
	$(".coutMain").hide();
	$(".cityMain").hide();
	//$("#adrTest11").html("hostText  "+hostText);
	
	requestAdr();
})
function requestAdr(data){
	$.ajax({
		url: CONTEXT+"/v1/area/listTopArea",
		data : data,
		type: "get",
		dataType: "text",
		contentType: "application/json",
		success: function(data){			
			try 
			{
			var andData = JavaH5WebInterface.deCode(data);
			var datasVal = JSON.stringify(andData).replace(/\\| /g,"");
			datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
			$("#directlyCityWarp").empty();
			$("#provinceWarp").empty();
			$("#regionWarp").empty();
			$('#directlyCityTemp').tmpl({areas:datasVal.result.directlyCity}).appendTo('#directlyCityWarp');
			$('#provinceTemp').tmpl({areasProv:datasVal.result.province}).appendTo('#provinceWarp');
			$('#regionTemp').tmpl({areasRegion:datasVal.result.region}).appendTo('#regionWarp');
			}catch(e){}
			try{
				window.webkit.messageHandlers.NativeDecrypt.postMessage('{"data":"'+data+'","fun":"decryptCompletedAdr"}');
				
			}catch(e){
				
			}
			
		}
	});
}
	
//获取省
function decryptCompletedAdr(data){
	var datasVal = JSON.stringify(data).replace(/\\| /g,"");
	datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
	$("#directlyCityWarp").empty();
	$("#provinceWarp").empty();
	$("#regionWarp").empty();
	$('#directlyCityTemp').tmpl({areas:datasVal.result.directlyCity}).appendTo('#directlyCityWarp');
	$('#provinceTemp').tmpl({areasProv:datasVal.result.province}).appendTo('#provinceWarp');
	$('#regionTemp').tmpl({areasRegion:datasVal.result.region}).appendTo('#regionWarp');	
}
//点击选择省
$("#choiseProv").on('tap',function(){
	$(this).addClass("curr-chiose").siblings().removeClass("curr-chiose");
	$(".provMain").show();
	$(".coutMain").hide();
	$(".cityMain").hide();
	$("#choiseCity").html('<em>选择城市</em><span class="mui-icon mui-icon-arrowright"></span>');
	$("#choiseArea").html('<em>选择区县</em>');
})
//点击选择市
$("#choiseCity").on("tap",function(){
	var provText = $("#choiseProv").find("em").text();
	var provId = $("#choiseProv").attr("data-id");
	if( provText !="选择省份"){
		$(this).addClass("curr-chiose").siblings().removeClass("curr-chiose");
		$(".provMain").hide();
		$(".cityMain").hide();
		$(".coutMain").show();
		$("#coutCity .city-title").text(provText);
		$("#choiseArea").html('<em>选择区县</em>');	
	}
})
//选择市
$(".provCity").on("tap","li",function(){
	count =1;
	recommend = 0;
	$(".provMain").hide();
	$(".coutMain").show();
	$(".cityMain").hide();
	sadr_text = $(this).text();
	$("#choiseProv").find("em").text(sadr_text);
	$("#coutCity .city-title").text(sadr_text);
	$("#choiseProv").attr("data-id",s_provinceId);
	$(this).parents(".provMain").find("li").removeClass("currCity");
	$(this).addClass("currCity");
	
	if(adrType == "eadr"){
		e_cityId="",e_areaId= "";
		e_provinceId = $(this).attr("areaid");
		$("#eAdr").text(sadr_text);		
	}else{
		s_cityId="",s_areaId= "";
		s_provinceId = $(this).attr("areaid");
		$("#sAdr").text(sadr_text);		
	}
	//选择全国
	if($(this).hasClass("allAddress")){
		//allAdr = "count";
		$(".indexMain").show();
		$(".addressMain").hide();
		$('#sourceWrap').empty();
		mui('#pullrefresh').pullRefresh().refresh(true);
		getData();	
	}else{
		parentId = $(this).attr("areaid");
		//$("#adrTest11").html("parentId  "+)
		//if($("#coutCityWarp").children().length == 0){
			try{
				var andData = JavaH5WebInterface.enCode("parentId:"+parentId);
				
				requestChildAdr(andData)
			}catch(e){}
			
			try{
				//window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'parentId\':'+parentId+'}'+'","fun":"encryptCompletedAdr"}');
				 window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":"{\'parentId\':\'' + parentId + '\'}","fun":"encryptCompletedAdr"}');
				}catch(e){}                                             //var iosVal = '{"data":"{\'sourceId\':\'' + sourceId + '\'}","fun":"handleData"}';
	//	}
		$("#choiseCity").addClass("curr-chiose").siblings().removeClass("curr-chiose");
	}
})

//点击市
$("#coutCity").on("tap","li",function(){
	count =1;
	recommend = 0;
	$(".provMain").hide();
	$(".coutMain").hide();
	$(".cityMain").show();
	$("#choiseCity").find("em").text($(this).text());
	$("#areaCity .city-title").text($(this).text());
	$("#choiseCity").attr("data-id",s_cityId);
	$(this).parents(".coutMain").find("li").removeClass("currCity");
	$(this).addClass("currCity");
	//选择全省
	if($(this).hasClass("allAddress")){
		
		sadr_text= $("#coutCity .city-title").text();
		
		//allAdr = "count";
		$(".indexMain").show();
		$(".addressMain").hide();
		if(adrType == "eadr"){
			e_cityId = "";e_areaId = "";
			e_provinceId = $(this).attr("areaid");
			$("#eAdr").text(sadr_text);	
			if(storLen>4){
				for(var i = 0;i<4;i++){			
					localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
				}
				localStorage.setItem("host4", e_provinceId+sadr_text);
			}else{
				localStorage.setItem("host"+storLen, e_provinceId+sadr_text);
			}
			//setStrog(e_provinceId)
		}else{
			s_cityId = "";s_areaId = "";
			s_provinceId  = $(this).attr("areaid");
			
			$("#sAdr").text(sadr_text);
			if(storLen>4){
				for(var i = 0;i<4;i++){			
					localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
				}
				localStorage.setItem("host4", s_provinceId+sadr_text);
			}else{
				localStorage.setItem("host"+storLen, s_provinceId+sadr_text);
			}
			//setStrog(s_provinceId);
		}
		$('#sourceWrap').empty();
		mui('#pullrefresh').pullRefresh().refresh(true);
		getData();		
	}else{
		sadr_text = $(this).text();
		if(adrType == "eadr"){
			e_cityId = $(this).attr("areaid");
			$("#eAdr").text(sadr_text);				
		}else{
			s_cityId  = $(this).attr("areaid");
			$("#sAdr").text(sadr_text);			
		}
			parentId = $(this).attr("areaid");
		//if($("#areaWarp").children().length == 0){
			try{
				var andData = JavaH5WebInterface.enCode("parentId:"+parentId);
				//$("#adrTest").html("66676: "+andData)
				requestChildArea(andData);
			}catch(e){}
			try{
				//window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'parentId\':'+parentId+'}'+'","fun":"encryptCompletedArea"}');
				 window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":"{\'parentId\':\'' + parentId + '\'}","fun":"encryptCompletedArea"}');
			}catch(e){}
		//}
		$("#choiseArea").addClass("curr-chiose").siblings().removeClass("curr-chiose");
	}
	
});

//点击区县
$("#areaCity").on("tap","li",function(){
	count=1;
	recommend = 0;
	$("#choiseArea").find("em").text($(this).text());
	$("#choiseArea").attr("data-id",s_areaId);
	$(this).parents(".cityMain").find("li").removeClass("currCity");
	$(this).addClass("currCity");	
		//adr_s_provinceId= $("#choiseProv").attr("data-id");
		//adr_s_cityId= $("#choiseCity").attr("data-id");
		//adr_s_areaId= $("#choiseArea").attr("data-id");
		//点击全市
		if($(this).hasClass("allAddress")){
			
			sadr_text = $("#areaCity .city-title").text();
			if(adrType == "eadr"){
				e_areaId = "";
				e_cityId = $(this).attr("areaid");
				$("#eAdr").text(sadr_text);	
				if(storLen>4){
					for(var i = 0;i<4;i++){			
						localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
					}
					localStorage.setItem("host4", e_cityId+sadr_text);
				}else{
					localStorage.setItem("host"+storLen, e_cityId+sadr_text);
				}
				//setStrog(e_cityId)
			}else{
				s_areaId = "";
				$("#sAdr").text(sadr_text);
				s_cityId = $(this).attr("areaid");
				if(storLen>4){
					for(var i = 0;i<4;i++){			
						localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
					}
					localStorage.setItem("host4", s_cityId+sadr_text);
				}else{
					localStorage.setItem("host"+storLen, s_cityId+sadr_text);
				}
				//setStrog(s_cityId)
			}
			$(".indexMain").show();
			$(".addressMain").hide();
			$('#sourceWrap').empty();
			mui('#pullrefresh').pullRefresh().refresh(true);
			getData();	
		}else{
			sadr_text = $(this).text();
			if(adrType == "eadr"){
				e_areaId = $(this).attr("areaid");
				$("#eAdr").text(sadr_text);	
				if(storLen>4){
					for(var i = 0;i<4;i++){			
						localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
					}
					localStorage.setItem("host4", e_areaId+sadr_text);
				}else{
					localStorage.setItem("host"+storLen, e_areaId+sadr_text);
				}
				//setStrog(e_areaId)
			}else{
				$("#sAdr").text(sadr_text);
				s_areaId = $(this).attr("areaid");
				if(storLen>4){
					for(var i = 0;i<4;i++){			
						localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
					}
					localStorage.setItem("host4", s_areaId+sadr_text);
				}else{
					localStorage.setItem("host"+storLen, s_areaId+sadr_text);
				}
				//setStrog(s_areaId)
			}
			//$("#adrTest11").html("hostText "+JSON.stringify(localStorage));
			$(".indexMain").show();
			$(".addressMain").hide();
			$('#sourceWrap').empty();
			getData();	
		}
		
})
$("#hositWrap").on("tap","li",function(){
	sadr_text = $(this).text();
	
	var areaId = $(this).attr("data-id");
	//JSON.stringify(localStorage)
	//$("#provCity .city-title").html("hostText "+adrType);
	if(adrType == "eadr"){		
		if(areaId.substring(2) == "0000"){
			e_provinceId = areaId;
		}else if(areaId.substring(4) == "00"){
			e_cityId = areaId;
		}else{
			e_areaId = areaId;
		}
		$("#eAdr").text(sadr_text);	
	}else if(adrType == "sadr"){
		$("#sAdr").text(sadr_text);
		if(areaId.substring(2) == "0000"){
			s_provinceId = areaId;
		}else if(areaId.substring(4) == "00"){
			s_cityId = areaId;
		}else{
			s_areaId = areaId;
			//$("#provCity .city-title").html("s_areaId "+s_areaId);
		}
	}
	
	$(".indexMain").show();
	$(".addressMain").hide();
	$('#sourceWrap').empty();
	getData();	
})
//设置localstrong
/*function setStrog(setId){
	if(storLen>4){
		for(var i = 0;i<4;i++){			
			localStorage.setItem("host"+i, localStorage.getItem("host"+(i+1)));
		}
		if(!setId){
			setId="000000";
		}
		localStorage.setItem("host4", setId+sadr_text);
	}else{
		localStorage.setItem("host"+storLen, setId+sadr_text);
	}
}*/

function requestChildAdr(data){
	$.ajax({
		url: CONTEXT+"/v1/area/listChildArea",
		data : {"param":data},
		type: "get",
		dataType: "text",
		contentType: "application/json",
		success: function(data){
			//$("#adrTest").html("6666: "+JSON.stringify(data))
			try{
				var andData = JavaH5WebInterface.deCode(data);
				//$("#adrTest").html("地址类型");
				//$("#coutCityWarp").html("6666: ")
				var datasVal = JSON.stringify(andData).replace(/\\| /g,"");
				datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
				$("#coutCityWarp").empty();
				$('#coutCityTemp').tmpl({areasRegion:datasVal.result}).appendTo('#coutCityWarp');
			}catch(e){}
			try{
				window.webkit.messageHandlers.NativeDecrypt.postMessage('{"data":"'+data+'","fun":"decryptCompletedChild"}');
			}catch(e){}			
		}
	});	
}
function requestChildArea(data){
	$.ajax({
		url: CONTEXT+"/v1/area/listChildArea",
		data : {"param":data},
		type: "get",
		dataType: "text",
		contentType: "application/json",
		success: function(data){
			try{
				window.webkit.messageHandlers.NativeDecrypt.postMessage('{"data":"'+data+'","fun":"decryptCompletedArea"}');
			}catch(e){}
			try{
				var andData = JavaH5WebInterface.deCode(data);
				//$("#adrTest").text("andData")
				var datasVal = JSON.stringify(andData).replace(/\\| /g,"");
				datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
				$("#areaWarp").empty();
				$('#areaTemp').tmpl({areaList:datasVal.result}).appendTo('#areaWarp');
			}catch(e){}
		}
	});	
}
//解密
function encryptCompletedAdr(data){
	requestChildAdr(data);			
}
function encryptCompletedArea(data){	
	requestChildArea(data);
			
}
/* IOS解密返回 */
//获取城市
function decryptCompletedChild(data){
	var datasVal = JSON.stringify(data).replace(/\\| /g,"");
	datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
	$("#coutCityWarp").empty();
	
	$('#coutCityTemp').tmpl({areasRegion:datasVal.result}).appendTo('#coutCityWarp');
}
//获取区县
function decryptCompletedArea(data){
	var datasVal = JSON.stringify(data).replace(/\\| /g,"");
	datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
	//$("#areaWarp").html("aaa: "+JSON.stringify(datasVal))
	$("#areaWarp").empty();
	$('#areaTemp').tmpl({areaList:datasVal.result}).appendTo('#areaWarp');
}
	//打电话功能
	$(".find-main").on("tap",".call-photo",function(e){
		 e.preventDefault();
		 e.stopPropagation();
		 var phone = $(this).attr("data-phone");
		 try{
			 window.webkit.messageHandlers.NativeMakeCall.postMessage(phone);
		 }catch(e){}
		 try{
			 JavaH5WebInterface.callMobile(phone)
		 }catch(e){}

	})
	function requestajax(dates){
		
		reqNum++;		
		var reqAjax = $.ajax({
			url: CONTEXT+"/v2/searchGoodsSource/queryGoodsSource",
			data : {"param":dates},
			timeout : 5000,
			type: "get",
			dataType: "text",
			contentType: "application/json",
			success: function(data){
				try 
				{
					window.webkit.messageHandlers.NativeDecrypt.postMessage('{"data":"'+data+'","fun":"decryptCompleted"}');
					
				}
				catch(e){
				}
				try 
				{
					var andData = JavaH5WebInterface.deCode(data);
					var datasVal = JSON.stringify(andData).replace(/\\| /g,"");
					datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));	
					var recordCount = datasVal.result.recordCount;
					//$("#adrTest").html((datasVal.result.recordList.length));
					if(recordCount == null){
						recordCount =0;
					}
					$("#sourceAll").html(recordCount);
					hasNextPage = datasVal.result.hasNextPage;
					
					if(datasVal.result.recordList == null && datasVal.result.currentPage == 0){
						if(reqNum == 1){
							$('<li class="nodata">暂无货源信息哦~~</li>').appendTo('#sourceWrap')
						}else{
							$('<li class="nodata">没有找到相关货源信息</li>').appendTo('#sourceWrap')
						}
					}else{
						$('#sourceTemp').tmpl({datalist:datasVal.result.recordList}).appendTo('#sourceWrap');
					}
				}
				catch(e){}
			},
			error:function(data){
				//异常处理；
				//console.log("error:"+type);
				$("#sourceWrap").html("000"+JSON.stringify(data))
				console.log("data2 error--->: "+JSON.stringify(data));
			},
			complete:function(XMLHttpRequest,status){ 
				if(status=='timeout'){
	
					reqAjax.abort(); //取消请求
					$("#sourceWrap").html('<li class="nodata">请求超时!请刷新重试</li>')
	
				}
			}
		});
}
	//统计总数
	function requestAll(dates){
		var reqAll = $.ajax({
			url: CONTEXT+"/v1/car/queryGoodsAllTotal",
			data : {"param":dates},
			type: "get",
			timeout : 5000,
			dataType: "text",
			contentType: "application/json",
			success: function(data){
				try{
					window.webkit.messageHandlers.NativeDecrypt.postMessage('{"data":"'+data+'","fun":"decryptCompletedAll"}');
				}catch(e){};
				try{
					var andData = JavaH5WebInterface.deCode(data);
					var datasVal = JSON.stringify(andData).replace(/\\| /g,"");
					datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
					$("#sourceAll").text(datasVal.result.total);
				}catch(e){}
			},
			complete:function(XMLHttpRequest,status){ 
				if(status=='timeout'){
					reqAll.abort(); //取消请求
					$("#sourceAll").html('0')
	
				}
			}
		});
	}
	function decryptCompleted(data){
		var datasVal = JSON.stringify(data).replace(/\\| /g,"");
		datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
		hasNextPage = datasVal.result.hasNextPage;
		var recordCount = datasVal.result.recordCount;
		if(recordCount == null){
			recordCount =0;
		}
		$("#sourceAll").html(recordCount);
		//$('#sourceWrap').empty();
		if(datasVal.result.recordList == null && datasVal.result.currentPage == 0){
			if(reqNum == 1){
				$('<li class="nodata">暂无货源信息哦~~</li>').appendTo('#sourceWrap')
			}else{
				$('<li class="nodata">没有找到相关货源信息</li>').appendTo('#sourceWrap')
			}
		}else{
			$('#sourceTemp').tmpl({datalist:datasVal.result.recordList}).appendTo('#sourceWrap');
		}
	}
function decryptCompletedAll(data){
	var datasVal = JSON.stringify(data).replace(/\\| /g,"");
		datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
		$("#sourceAll").text(datasVal.result.total);
	
}


function encryptCompleted(data){
	//$('#sourceWrap').html(data);
	requestajax(data);
	//requestAll(data);
	//$('#sourceTemp').tmpl({datalist:data.result.recordList}).appendTo('#sourceWrap');
}
//进入详情页
$("#sourceWrap").on("tap","li .cont-detail",function(e){
	 e.preventDefault();
	 e.stopPropagation()
	 var sourceId = $(this).parents("li").attr("date-id");
	 
	window.location="detail.html?sourceId="+sourceId;
})
//返回
$(".mui-icon-left-nav").on("tap",function(e){
	 e.preventDefault();
	 e.stopPropagation()
	 try{
		 window.webkit.messageHandlers.NativeMethod.postMessage("pop");
	 }catch(e){}
	 try{
		 JavaH5WebInterface.backKey();
	 }catch(e){}
})
$("body").on("tap",".mui-backdrop",function(){
	$("#popover").parent().removeClass("mui-active");
})

//点刷新
$(".rightRefulsh").on("tap",function(){
	recommend = 1;
	location.reload();
})
//地址返回
$(".addressBack").on("tap",function(){
	$(".addressMain").hide();
	$(".indexMain").show();
})

//点击清除
$("#clearRight").on("tap",function(){
	s_provinceId= "", s_cityId="",s_areaId= "", e_provinceId= "", e_cityId="",e_areaId= "";
	$(".indexMain").show();
	$(".addressMain").hide();
	$("#eAdr").text("出发地");	
	$("#sAdr").text("目的地");
	getData();
		
});
/* 找货源*/
mui("#popover").on("tap","li",function(){
	count = 1;
	recommend = 0;
	$(this).addClass("select-radio-option").siblings().removeClass("select-radio-option");
	$("#findText").text($.trim($(this).text()));
	$("#popover").parent().removeClass("mui-active");
	sourceType = $(this).attr("date-id").toString();
	$('#sourceWrap').empty();
	mui('#pullrefresh').pullRefresh().refresh(true);
	getData();
	mui(".mui-popover").popover('hide');
})
/* 筛选车型  车长*/
$(".select-list").on("tap","li",function(){
	count = 1;
	recommend = 0;
	$(this).parents("li").find(".list-text").text($.trim($(this).text()));
	$('#sourceWrap').empty();
	mui('#pullrefresh').pullRefresh().refresh(true);
	if($(this).parent("ul").hasClass("carstyle")){
		carType = $(this).attr("date-id").toString();
		getData();
	}else{
		carLength = $(this).attr("date-val").toString();
		getData();
	}
	mui(".mui-popover").popover('hide');
})
var hostText, hostId, hostVal;
if(storLen>5){
	for(var i = storLen-1; i>storLen-6;i--){
		hostText = localStorage.getItem("host"+i);
		hostId = hostText.substring(0,6);
		hostVal = hostText.substring(6);
		$("#hositWrap").append("<li data-id="+hostId+"> "+hostVal+" </li>");
	}
}else{
	for(var i = storLen-1; i>-1;i--){
		$("#adrTest11").html("hostText  "+hostText);
		hostText = localStorage.getItem("host"+i);
		hostId = hostText.substring(0,6);
		hostVal = hostText.substring(6);
		$("#hositWrap").append("<li data-id="+hostId+"> "+hostVal+" </li>");
	}
}



$("#adrTest").html("aa : "+JSON.stringify(localStorage))







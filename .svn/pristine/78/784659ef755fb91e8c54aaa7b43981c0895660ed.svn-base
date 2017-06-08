
/*获取本地地址  */
function getRootPath_web() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPaht;
}
var CONTEXT = getRootPath_web();
var storage = window.localStorage;
var len = storage.length;
var hostText, allAdr;
var s_provinceId= null, s_cityId=null,s_areaId= null, e_provinceId= null, e_cityId=null,e_areaId= null;
var addrSour =search("addrSour");
var sAdrText = search("sAdrText");
var sAdrId = search("sAdrId");

//$('#provCity .city-title').html("dataaa");
/*function deCode3(data){
	data =window.requestAdr2.decode(data);
	$('#provCity .city-title').html("dataaa0009: "+JSON.stringify(data));
}*/
function requestAdr(data){
	$.ajax({
		url: CONTEXT+"/v1/area/listTopArea",
		data : data,
		type: "get",
		dataType: "text",
		contentType: "application/json",
		success: function(data){
			//$('#provCity .city-title').html("dataaa001: "+JSON.stringify(data));
			//$('#coutCityWarp').html("data"+data);
			//deCode3(data);
			try 
			{
			var andData = JavaH5WebInterface.deCode(data);
			//$('#provCity .city-title').html("ok"+JSON.stringify(andData));
			var datasVal = JSON.stringify(andData).replace(/\\| /g,"");
			datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
			$("#directlyCityWarp").empty();
			$("#provinceWarp").empty();
			$("#regionWarp").empty();
			$('#directlyCityTemp').tmpl({areas:datasVal.result.directlyCity}).appendTo('#directlyCityWarp');
			$('#provinceTemp').tmpl({areasProv:datasVal.result.province}).appendTo('#provinceWarp');
			$('#regionTemp').tmpl({areasRegion:datasVal.result.region}).appendTo('#regionWarp');
			}
			catch(e){
				//$('#provCity .city-title').html("error"+JSON.stringify(e));
			}
			//$("#adrTest").html("地址类型");
			//$('#provCity .city-title').html("dataaa"+JSON.stringify(andData));
			//$("#adrTest").html("地址类型");
			
			try{
				window.webkit.messageHandlers.NativeDecrypt.postMessage('{"data":"'+data+'","fun":"decryptCompleted"}');
			}catch(e){
				
			}
			
		}
	});
}
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
requestAdr();
/* 解密返回 */
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
//获取省
function decryptCompleted(data){
	//$('#directlyCityWarp').html(JSON.stringify(data).replace(/\\| /g,""));
	//data.replace(/\/| /g,"") 
	var datasVal = JSON.stringify(data).replace(/\\| /g,"");
	datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
	
	//datasVal = datasVal.result;
	$("#directlyCityWarp").empty();
	$("#provinceWarp").empty();
	$("#regionWarp").empty();
	$('#directlyCityTemp').tmpl({areas:datasVal.result.directlyCity}).appendTo('#directlyCityWarp');
	$('#provinceTemp').tmpl({areasProv:datasVal.result.province}).appendTo('#provinceWarp');
	$('#regionTemp').tmpl({areasRegion:datasVal.result.region}).appendTo('#regionWarp');
	//$('#coutCityTemp').tmpl({areasRegion:datasVal.result}).appendTo('#coutCityWarp');
	
}
//点击清除
$("#clearRight").on("tap",function(){
	$("#adrTest").html("aaa")
	window.location="index.html";
});

$("#choiseProv").on("tap",function(){
	$(this).addClass("curr-chiose").siblings().removeClass("curr-chiose");
	$(".provMain").show();
	$(".coutMain").hide();
	$(".cityMain").hide();
	$("#choiseCity").html('<em>选择城市</em><span class="mui-icon mui-icon-arrowright"></span>');
	$("#choiseArea").html('<em>选择区县</em>');
	$("#coutCityWarp").empty();
	$("#areaWarp").empty();
	requestAdr();
})


//选择市
$(".provCity").on("tap","li",function(){
	var s_provinceId = $(this).attr("areaid");
	var adr_text = $(this).text();
	$("#choiseProv").find("em").text(adr_text);
	$("#coutCity .city-title").text(adr_text);
	$("#choiseProv").attr("data-id",s_provinceId);
	$(this).parents(".provMain").find("li").removeClass("currCity");
	$(this).addClass("currCity");
	
	//选择全国
	if($(this).hasClass("allAddress")){
		$(this).attr("areaid","");
		//$("#adrTest").html("6667688: "+sAdrText);
		allAdr = "count";
		window.location="index.html?adr_type="+addrSour+"&adr_text="+adr_text+"&s_provinceId="+s_provinceId+"&allAdr="+allAdr+"&sAdrText="+sAdrText+"&sAdrId="+sAdrId;
		if(addrSour == "eAdr"){
			
			window.location="index.html?adr_type="+addrSour+"&adr_text="+adr_text+"&adr_areaId="+s_provinceId+"&allAdr="+allAdr+"&sAdrText="+sAdrText+"&sAdrId="+sAdrId;
		}else{
			window.location="index.html?adr_type="+addrSour+"&adr_text="+adr_text+"&adr_areaId="+s_cityId+"&allAdr="+allAdr;
		}
		
		//window.location="index.html?adr_type="+adr_type+"&adr_text="+adr_text+"&adr_areaId="+adr_areaId;
		//$("#adrTest").html($(this).text())
	}else{
		$(this).parents(".city-main").hide();
		$(this).parents(".city-main").next(".city-main").show();
		//$("#adrTest").html("66676: "+andData)
		//console.log("s_provinceId: "+s_provinceId)
		try{
			var andData = JavaH5WebInterface.enCode("parentId:"+s_provinceId);
			
			requestChildAdr(andData)
		}catch(e){}
		
		try{
			window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'parentId\':'+s_provinceId+'}'+'","fun":"encryptCompleted"}');
		}catch(e){}
		
		$("#choiseCity").addClass("curr-chiose").siblings().removeClass("curr-chiose");
	}
	

})
function encryptCompleted(data){
	requestChildAdr(data);			
}
function encryptCompletedArea(data){	
	requestChildArea(data);
			
}
/* 选择市 */
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
		
		window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'parentId\':'+s_provinceId+'}'+'","fun":"encryptCompleted"}');
	
	}
})
//点击市
$("#coutCity").on("tap","li",function(){
	var s_cityId = $(this).attr("areaid");
	$("#choiseCity").find("em").text($(this).text());
	$("#areaCity .city-title").text($(this).text());
	$("#choiseCity").attr("data-id",s_cityId);
	$(this).parents(".coutMain").find("li").removeClass("currCity");
	$(this).addClass("currCity");
	//$("#adrTest").html(s_cityId)
	//选择全省
	if($(this).hasClass("allAddress")){
		var adr_type= addrSour;
		var adr_text = $("#coutCity .city-title").text();
		$("#adrTest").html(s_cityId)
		allAdr = "provice";
		window.location="index.html?adr_type="+adr_type+"&adr_text="+adr_text+"&s_provinceId="+s_cityId+"&allAdr="+allAdr;
	}else{
		$("#adrTest").html($(this).text())
		$(this).parents(".city-main").hide();
		$(".provMain").hide();
		$(this).parents(".city-main").next(".city-main").show();
		try{
			var andData = JavaH5WebInterface.enCode("parentId:"+s_cityId);
			//$("#adrTest").html("66676: "+andData)
			requestChildArea(andData);
		}catch(e){}
		try{
			window.webkit.messageHandlers.NativeEncrypt.postMessage('{"data":'+'"{\'parentId\':'+s_cityId+'}'+'","fun":"encryptCompletedArea"}');
		}catch(e){}
		
		$("#choiseArea").addClass("curr-chiose").siblings().removeClass("curr-chiose");
	
	}
		
	
	
})
//选择区县
$("#choiseArea").on("tap",function(){
	var provText = $("#choiseCity").find("em").text();
	var provId = $("#choiseArea").attr("data-id");
	if( provText !="选择城市"){
		$(this).addClass("curr-chiose").siblings().removeClass("curr-chiose");
		$(".provMain").hide();
		$(".cityMain").show();
		$(".coutMain").hide();
		$("#areaCity .city-title").text(provText);
	}
})

$("#areaCity").on("tap","li",function(){
	var s_cityId = $(this).attr("areaid");
	$("#choiseArea").find("em").text($(this).text());
	$("#choiseArea").attr("data-id",s_cityId);
	$(this).parents(".cityMain").find("li").removeClass("currCity");
	$(this).addClass("currCity");
		
		var adr_type =addrSour;
		var adr_text = $(this).text();		
		var adr_s_provinceId= $("#choiseProv").attr("data-id");
		var adr_s_cityId= $("#choiseCity").attr("data-id");
		var adr_s_areaId= $("#choiseArea").attr("data-id");
		//点击全市
		if($(this).hasClass("allAddress")){
			var adr_type= addrSour;
			var adr_text = $("#areaCity .city-title").text();
			var adr_areaId = $(this).attr("areaid");
			allAdr = "allCity"
			window.location="index.html?adr_type="+adr_type+"&adr_text="+adr_text+"&s_cityId="+s_cityId+"&allAdr="+allAdr;
		}else{
			window.location="index.html?adr_type="+adr_type+"&adr_text="+adr_text+"&s_areaId="+adr_s_areaId;
		}
		localStorage.setItem("host"+len, s_cityId+adr_text);
		
})
//点击历史城市
$("#hositCity").on("tap","li",function(){
	var adr_type =addrSour;
	var adr_s_areaId = $(this).attr("data-id");
	var adr_text = $(this).text();
	$("#adrTest").html(adr_text);
	window.location="index.html?adr_type="+adr_type+"&adr_text="+adr_text+"&adr_s_areaId="+adr_s_areaId;
})

$("#hositCity").on("tap","li",function(){
	var adr_type =addrSour;
	var adr_s_areaId = $(this).attr("data-id");
	var adr_text = $(this).text();
	$("#adrTest").html(adr_text);
	window.location="index.html?adr_type="+adr_type+"&adr_text="+adr_text+"&adr_s_areaId="+adr_s_areaId;
})



//$("#hositWrap").html(JSON.stringify(localStorage))
if(hostText){
	for(var i = len-1; i>len-6;i--){
		hostText = localStorage.getItem("host"+i);
		var hostId = hostText.substring(0,6);
		var hostVal = hostText.substring(6);
		$("#hositWrap").append("<li data-id="+hostId+"> "+hostVal+" </li>");
	}
}













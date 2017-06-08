var purchaseCtrl = new NSTApi.client();
NSTApi.client.prototype.purchaseHandler = {
	/**
	 * 静态变量
	 * 请求地址URL
	 */
	urlItems : {
		//purchaseURL :"/nst-web-api/purchaseOrder/queryPurchaseOrderBySourceId"
		purchaseURL :"/purchaseOrder/queryPurchaseOrderBySourceId"
	},
	/**
	 * 初始化
	 */
	init : function(){
		//$('.box').html("sourceId");
		var _this = this;
		var sourceId = purchaseCtrl.getReqParam("sourceId");
		//$('.box').html("sourceId"+ sourceId);
		//var sourceId = "132663"
		// 安卓查询参数串
		var anVal = 'sourceId:' + sourceId;
		// ios查询参数串
		var iosVal = '{"data":"{\'sourceId\':\'' + sourceId + '\'}","fun":"handleData"}';
		var data = purchaseCtrl.enCode(anVal, iosVal, 'handleData', function(data){
			_this.getData(data);
			//$('.box').html("sourceId"+ data);
		});
		if(purchaseCtrl.appVersion.android){
			_this.getData(data);
		}
	},
	// 请求订单数据
	getData : function(param){
		var _this = this;
		$.ajax({
			url: this.urlItems.purchaseURL,
			data : {"param" : param},
			type: "get",
			dataType: "text",
			contentType: "application/json",
			cache: false,
			success: function(data){
				//$('.box').html("srey"+ data);
				var data = purchaseCtrl.deCode(data, '{"data":"'+data+'","fun":"decryptCompleted"}', "decryptCompleted", function(data){
					//$('.box').html("stsId"+ data);
					var datasVal = JSON.stringify(data).replace(/\\| /g,"");
					
					datasVal = JSON.parse(datasVal.substring(1,datasVal.length-1));
					
					_this.render(datasVal);
					
				});
				if(purchaseCtrl.appVersion.android){
					_this.render(data);
				}
				
				//打电话功能
				  $("body").on("tap",".info_phone", function(){
					var phone = $(this).attr("data-phone");
					purchaseCtrl.call(phone);
				});
			}
		});
	},
	// 渲染页面
	render : function(data){
		$("#shop_weaper").html($("#shop_weaperTemp").tmpl(data.result));
		$("#goods_weaper").html($("#goods_weaperTemp").tmpl(data.result.productDetails));
		$("#car_weaper").html($("#car_weaperTemp").tmpl(data.result));
	}
};

$(function(){
	
	// 初始化
	purchaseCtrl.purchaseHandler.init();
	
});


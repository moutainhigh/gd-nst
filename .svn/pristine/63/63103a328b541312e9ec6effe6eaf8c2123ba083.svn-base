$(function(){
	// 同步默认广告-同步渠道-获取渠道上架广告列表
	$("#syncChannelSelect").change(function(){
		$("#selectAllBtn").attr("checked", false); //取消全选按钮勾选状态
		$("#syncAdList").html("");
		var channel = $(this).val();
		if (channel < 0) {
			return;
		}
		$.ajax({
	        type: "POST",
	        url: CONTEXT + "advertisement/listDefaultAdByChannel/"+channel,
	        dataType: "json",
	        success: function(data){
	        	if (data.code == 10000) {
	        		var syncAdList = "";
	        		$.each(data.result, function(index, item){
	        			syncAdList += "<label><input class='adId' type='checkbox' value='"+item.id+"' name='adId'/>默认广告"+item.sort+"："+item.name+"</label><br>";
	        		});
	        		$("#syncAdList").html(syncAdList);
	        	}
	        }
		});
	});
	
	// 全选
	$("#selectAllBtn").click(function(){
		if ($(this).attr("checked")) {
			$.each($("input[name='adId']"), function(index, item) {
				$(item).attr("checked", true);
			});
		} else {
			$.each($("input[name='adId']"), function(index, item) {
				$(item).attr("checked", false);
			});
		}
	});
	$(".adId").live('click',function() {
		var nodeCount = $("input[name='adId']").length;
		var checkCount = $("input[name='adId']:checked").length;
		if (checkCount == nodeCount) {
			$("#selectAllBtn").attr("checked", true);
		} else {
			$("#selectAllBtn").attr("checked", false);
		}
	});
});
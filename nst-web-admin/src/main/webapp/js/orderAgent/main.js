var disableExport = false;
function getParams(){
	var params = {
		"orderNo" : $("#orderAgentSearchForm #orderNo").val(),
		"acceptStartDate" : $("#orderAgentSearchForm #acceptStartDate").val(),
		"acceptEndDate" : $("#orderAgentSearchForm #acceptEndDate").val(),
		"confirmStartDate" : $("#orderAgentSearchForm #confirmStartDate").val(),
		"confirmEndDate" : $("#orderAgentSearchForm #confirmEndDate").val(),
		"logisticCompanyName" : $("#orderAgentSearchForm #logisticCompanyName").val(),
		"logisticMobile" : $("#orderAgentSearchForm #logisticMobile").val(),
		"driverName" : $("#orderAgentSearchForm #driverName").val(),
		"driverMobile" : $("#orderAgentSearchForm #driverMobile").val(),
		"payStatus" : $("#orderAgentSearchForm #payStatus").val(),
		"orderStatus" : $("#orderAgentSearchForm #orderStatus").val(),
		"s_provinceId" : $("#orderAgentSearchForm #sProvinceId").val(),
		"s_cityId" : $("#orderAgentSearchForm #sCityId").val(),
		"s_areaId" : $("#orderAgentSearchForm #sAreaId").val(),
		"e_provinceId" : $("#orderAgentSearchForm #eProvinceId").val(),
		"e_cityId" : $("#orderAgentSearchForm #eCityId").val(),
		"e_areaId" : $("#orderAgentSearchForm #eAreaId").val(),
		"shipperName" : $("#orderAgentSearchForm #shipperName").val(),
		"shipperMobile" : $("#orderAgentSearchForm #shipperMobile").val(),
	};
	return params;
}
$(document).ready(function(){
	initAreaTopList();
	fixRownumber();
	loadListData(null);
	//分页加载
	$("#orderAgentdg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	
	//查询
	$('#btn-search').click(function(){
		if(!$("#orderAgentSearchForm").form('validate')){
			return false;
		}
		var params = getParams();
		loadListData(params);
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#orderAgentSearchForm")[0].reset();
	});
});

function loadListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#orderAgentdg').datagrid({
		url:CONTEXT+'orderAgent/queryPage',
		queryParams: queryParams,
		height: 'auto', 
		nowrap:true,
		toolbar:'#orderAgenttb',
		pageSize:50,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		fit:true,
		columns:[[
			{field:'id',title:'',checkbox:true},
			{field:'orderNo',title:'订单号',align:'center',formatter:orderNoFormatter},
			{field:'sourceMemberName',title:'发布人姓名',align:'left'},
			{field:'sourceMemberMobile',title:'发布人手机',align:'center'},
			{field:'sDetailStr',title:'发货地',align:'left'},
			{field:'eDetailStr',title:'目的地',align:'left'},
			{field:'totalWeight',title:'货物重量',align:'center',formatter:totalWeightFormatter},
			{field:'logisticCompanyName',title:'物流公司名称',align:'left'},
			{field:'logisticMobile',title:'物流公司电话',align:'center'},
			{field:'driverName',title:'车主姓名',align:'left'},
			{field:'driverMobile',title:'车主手机',align:'center'},
			{field:'confirmTime',title:'车主接单时间',align:'center'},
			{field:'logisticTime',title:'接单处理时间',align:'center'},
			{field:'sourceTypeStr',title:'订单类型',align:'center'},
			{field:'orderStatusStr',title:'订单状态',align:'center'},
			{field:'orderStatus',title:'订单状态',hidden:true},
			{field:'payStatusStr',title:'支付状态',align:'center'},
			{field:'infoAmt',title:'订单金额',align:'center'}
		]],
		onLoadSuccess: function (data) {
			$(this).datagrid("fixRownumber");
		}
	}); 
}

function totalWeightFormatter(val) {
	if(val != undefined && val != "") {
		return val + "吨"
	}
	return "";
}

function logisticTimeFormatter(val,row, index){
	if(row.orderStatus == 1){
		return "";
	}
	return val;
}

function detail(id){
	$('#detailDialog').dialog({'title':'查看', 'width':800, 'height':400, 'href':CONTEXT+'orderAgent/detail/'+id}).dialog('open');
}

function batchDelete(){
	var row = $('#orderAgentdg').datagrid("getSelections");
	if($(row).length < 1 ) {
	   warningMessage("请选择要删除的数据！");
	   return;
	}
	jQuery.messager.confirm('提示', '您确定要删除所选数据吗?', function(r){
		if (r){
			var ids = getSelections("id");
			$.ajax({
		        type: "POST",
		        url: CONTEXT + "orderAgent/delete",
		        data:{"ids": ids},
		        dataType: "json",
		        success: function(data){
		        	if (data.code == 10000) {
		    			slideMessage("操作成功！");
		    			$("#orderAgentdg").datagrid('reload');
		    		}else{
		    			warningMessage(data.result);
		    			return;
		    		}
		        }
			});
		}
	});
}

function reload(){
	disableExport = false;
	$("#orderAgentdg").datagrid("load", getParams());
}

function exportData(){
	if (disableExport){
		slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
		return;
	}
	disableExport = true;
	
	var params = getParams();
	var paramList = "";
	for(key in params){ 
		paramList +=  key+"="+ params[key]+"&"
    } 
	$.ajax({
		url: CONTEXT+'orderAgent/exportCheck',
		data : params,
		type:'post',
		success : function(data){
			//检测通过
			if (data && data.code == 10000){
				slideMessage("数据正在导出中, 请耐心等待...");
				$.download(CONTEXT+'orderAgent/export',paramList,'post');
			}else{
				warningMessage(data.msg);
			}
		},
		error : function(){
			warningMessage("服务器出错");
		}
	});
	
	// 10秒后导出按钮重新可用
	setInterval(function(){
		disableExport = false;
	}, 10000);
}

$("#sProvinceId").change(function(){
	$("#sCityId").html("<option value=''>请选择城市</option>");
	$("#sAreaId").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#sCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});

$("#eProvinceId").change(function(){
	$("#eCityId").html("<option value=''>请选择城市</option>");
	$("#eAreaId").html("<option value=''>请选择区/县</option>")
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#eCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});

$("#sCityId").change(function(){
	$("#sAreaId").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#sAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});

$("#eCityId").change(function(){
	$("#eAreaId").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#eAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});

function initAreaTopList(){
	var areaTopList = queryAreaTopList();
	$.each(areaTopList, function(i, item){
		$("#sProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
		$("#eProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	}); 
}

function queryAreaTopList(){
	var result;
	$.ajax({
		async: false, 
		url: CONTEXT+'area/queryTopList',
		type:'post',
		dataType:"json",
		success : function(data){
			result = data.result;
		}
	});
	return result;
}

function queryAreaChildList(parentId){
	var result;
	$.ajax({
		async: false, 
		url: CONTEXT+'area/queryChildList/'+parentId,
		type:'post',
		dataType:"json",
		success : function(data){
			result = data.result;
		}
	});
	return result;
}
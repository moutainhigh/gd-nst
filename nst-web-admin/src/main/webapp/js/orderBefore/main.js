var disableExport = false;
function getSearchParams(){
	var params = {
		"sProvinceId" : $("#orderBeforeSearchForm #sProvinceId").val(),
		"sCityId" : $("#orderBeforeSearchForm #sCityId").val(),
		"sAreaId" : $("#orderBeforeSearchForm #sAreaId").val(),
		"eProvinceId" : $("#orderBeforeSearchForm #eProvinceId").val(),
		"eCityId" : $("#orderBeforeSearchForm #eCityId").val(),
		"eAreaId" : $("#orderBeforeSearchForm #eAreaId").val(),
		"sourceType" : $("#orderBeforeSearchForm #sourceType").val(),
		"sourceStatus" : $("#orderBeforeSearchForm #sourceStatus").val(),
		"acceptStartDate" : $("#orderBeforeSearchForm #acceptStartDate").val(),
		"acceptEndDate" : $("#orderBeforeSearchForm #acceptEndDate").val(),
		"releaseStartDate" : $("#orderBeforeSearchForm #releaseStartDate").val(),
		"releaseEndDate" : $("#orderBeforeSearchForm #releaseEndDate").val(),
		"shipperMobile" : $("#orderBeforeSearchForm #shipperMobile").val(),
		"regeType" : $("#orderBeforeSearchForm #regeType").val(),
		"sourceId" : $("#orderBeforeSearchForm #sourceId").val(),
		"orderAgentNo" : $("#orderBeforeSearchForm #orderAgentNo").val(),
		"orderAgentStatus" : $("#orderBeforeSearchForm #orderAgentStatus").val(),
		"orderInfoNo" : $("#orderBeforeSearchForm #orderInfoNo").val(),
	};
	return params;
}
function loadOrderBeforeListData(queryParams){
	$('#orderBeforeDG').datagrid({
		url:CONTEXT+'orderBefore/queryPage',
		queryParams: queryParams,
		height: 'auto', 
		nowrap:true,
		pageSize:50,
		toolbar:'#orderBeforeTB',
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		fit:true,
		columns:[[
		    {field:'sourceId',title:'货源ID',align:'center'},
		    {field:'shipperName',title:'发布人姓名',align:'left'},
		    {field:'shipperMobile',title:'发布人手机号码',align:'center'},
		    {field:'regeTypeStr',title:'发布人注册来源',align:'center'},
		    {field:'sDetailStr',title:'发货地',align:'left'},
		    {field:'eDetailStr',title:'目的地',align:'left'},
		    {field:'sourceTypeStr',title:'线路类型',align:'center'},
		    {field:'totalWeight',title:'重量',align:'center',formatter:totalWeightFormatter},
		    {field:'releaseTime',title:'发布时间',align:'center'},
			{field:'createTime',title:'接单时间',align:'center'},
			{field:'driverName',title:'车主姓名',align:'center'},
			{field:'driverMobile',title:'车主手机',align:'center'},
			{field:'orderAgentNo',title:'信息订单号',align:'center'},
			{field:'sourceStatusStr',title:'接单处理状态',align:'center'},
			{field:'orderAgentStatusStr',title:'信息订单状态',align:'center'},
			{field:'orderInfoNo',title:'货运订单号',align:'center'}
		]],
		onLoadSuccess: function (data) {
			$(this).datagrid("fixRownumber");
		}
	}); 
}

function totalWeightFormatter(val){
	if(val != undefined && val != ""){
		return val + "吨";
	}
	return "";
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

function initAreaTopList(){
	var areaTopList = queryAreaTopList();
	$.each(areaTopList, function(i, item){
		$("#sProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
		$("#eProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	}); 
}

function exportData(){
	if (disableExport){
		slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
		return;
	}
	disableExport = true;
	
	var params = getSearchParams();
	var paramList = "";
	for(key in params){ 
		paramList +=  key+"="+ params[key]+"&"
    }
	$.ajax({
		url: CONTEXT+'orderBefore/exportCheck',
		data : params,
		type:'post',
		success : function(data){
			//检测通过
			if (data && data.code == 10000){
				slideMessage("数据正在导出中, 请耐心等待...");
				$.download(CONTEXT+'orderBefore/export',paramList,'post');
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

$(document).ready(function(){
	fixRownumber();
	loadOrderBeforeListData(null);
	$("#orderBeforeDG").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	initAreaTopList();
	
	//查询
	$('#btn-search').click(function(){
		if(!$("#orderBeforeSearchForm").form('validate')){
			return false;
		}
		loadOrderBeforeListData(getSearchParams());
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#orderBeforeSearchForm")[0].reset();
	});
	
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
});
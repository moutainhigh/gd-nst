var disableExport = false;
function getParams(){
	var params = {
		"orderNo" : $("#orderInfoSearchForm #orderNo").val(),
		"sProvinceId" : $("#orderInfoSearchForm #sProvinceId").val(),
		"sCityId" : $("#orderInfoSearchForm #sCityId").val(),
		"sAreaId" : $("#orderInfoSearchForm #sAreaId").val(),
		"eProvinceId" : $("#orderInfoSearchForm #eProvinceId").val(),
		"eCityId" : $("#orderInfoSearchForm #eCityId").val(),
		"eAreaId" : $("#orderInfoSearchForm #eAreaId").val(),
		"startDate" : $("#orderInfoSearchForm #startDate").val(),
		"endDate" : $("#orderInfoSearchForm #endDate").val(),
		"driverName" : $("#orderInfoSearchForm #driverName").val(),
		"driverMobile" : $("#orderInfoSearchForm #driverMobile").val(),
		"shipperName" : $("#orderInfoSearchForm #shipperName").val(),
		"shipperMobile" : $("#orderInfoSearchForm #shipperMobile").val(),
		"sourceType" : $("#orderInfoSearchForm #sourceType").val(),
		"orderStatus" : $("#orderInfoSearchForm #orderStatus").val(),
		"sendGoodsType" : $("#orderInfoSearchForm #sendGoodsType").val(),
		"payStatus" : $("#orderInfoSearchForm #payStatus").val(),
		"goodsType" : $("#orderInfoSearchForm #goodsType").val(),
		"transStatus" : $("#orderInfoSearchForm #transStatus").val()
	};
	return params;
}

function loadListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#orderInfodg').datagrid({
		url:CONTEXT+'orderInfo/queryPage',
		queryParams: queryParams,
		//width: 1000,  
		height: 'auto', 
		nowrap:true,
		toolbar:'#orderInfotb',
		pageSize:50,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		fit:true,
		columns:[[
			{field:'id',title:'',checkbox:true},
			{field:'orderNo',title:'货运订单号',align:'center'},
			{field:'createTime',title:'订单生成时间',align:'center'},
			{field:'sDetailStr',title:'发货地',align:'left',formatter:sDetailFormatter},
			{field:'eDetailStr',title:'目的地',align:'left',formatter:eDetailFormatter},
			{field:'goodsTypeStr',title:'货物类型',align:'center'},
			{field:'totalWeight',title:'货物重量',align:'center',formatter:totalWeightFormatter},
			{field:'shipperName',title:'发布人',align:'left'},
			{field:'shipperMobile',title:'发布人手机',align:'center'},
			{field:'driverName',title:'车主',align:'left'},
			{field:'driverMobile',title:'车主手机',align:'center'},
			{field:'sourceTypeStr',title:'货运订单类型',align:'center'},
			{field:'freight',title:'意向运费',align:'center',formatter:freightFormatter},
			{field:'sendGoodsTypeStr',title:'发货方式',align:'center'},
			{field:'transStatus',title:'物流状态',align:'center',formatter:transStatusFormatter},
			{field:'orderStatusStr',title:'订单状态',align:'center'},
			{field:'payStatusStr',title:'支付状态',align:'center'}
		]],
		onLoadSuccess: function (data) {
			$(this).datagrid("fixRownumber");
		}
	}); 
}

function detail(id){
	$('#detailDialog').dialog({'title':'查看', 'width':800, 'height':400, 'href':CONTEXT+'orderInfo/detail/'+id}).dialog('open');
}

function batchDelete(){
	var row = $('#orderInfodg').datagrid("getSelections");
	if($(row).length < 1 ) {
	   warningMessage("请选择要删除的数据！");
	   return;
	}
	jQuery.messager.confirm('提示', '您确定要删除所选数据吗?', function(r){
		if (r){
			var ids = getSelections("id");
			$.ajax({
		        type: "POST",
		        url: CONTEXT + "orderInfo/delete",
		        data:{"ids": ids},
		        dataType: "json",
		        success: function(data){
		        	if (data.code == 10000) {
		    			slideMessage("操作成功！");
		    			$("#orderInfodg").datagrid('reload');
		    		}else{
		    			warningMessage(data.result);
		    			return;
		    		}
		        }
			});
		}
	});
}
//货物重量
function totalWeightFormatter(val){
	if(val != undefined && val != ""){
		return val+"吨";
	}
	return val;
}
//意向运费
function freightFormatter(val) {
	if(val > 0) {
		return val;
	} else {
		return "面议";
	}
}

//物流状态
function transStatusFormatter(val, row) {
	if (val == 1) {
		return "待验货";
	} else if (val == 2) {
		return "已发货";
	} else if (val == 3) {
		return "已送达";
	} else if (val == 4) {
		return "验货不通过";
	} else if (val == 5) {
		return "已拒收";
	}
	return "";
}

function reload(){
	disableExport = false;
	$("#orderInfodg").datagrid("load", getParams());
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
		url: CONTEXT+'orderInfo/exportCheck',
		data : params,
		type:'post',
		success : function(data){
			//检测通过
			if (data && data.code == 10000){
				slideMessage("数据正在导出中, 请耐心等待...");
				$.download(CONTEXT+'orderInfo/export',paramList,'post');
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

function initPageDataList(){
	loadListData(null);
	//分页加载
	$("#orderInfodg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
}

function initAreaTopList(){
	var areaTopList = queryAreaTopList();
	$.each(areaTopList, function(i, item){
		$("#sProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
		$("#eProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	}); 
}
$(document).ready(function(){
	fixRownumber();
	initPageDataList();
	initAreaTopList();
	
	//查询
	$('#btn-search').click(function(){
		if(!$("#orderInfoSearchForm").form('validate')){
			return false;
		}
		var params = getParams();
		loadListData(params);
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#orderInfoSearchForm")[0].reset();
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
$(document).ready(function(){
	loadListData(null);
	//分页加载
	$("#calldg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	
	//查询
	$('#btn-search').click(function(){
		loadListData(getSearchParams());
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#callSearchForm")[0].reset();
	});
});

var disableExport = false;

function getSearchParams(){
	var params = {
		"source" : $("#source").val(),
		"callRole" : $("#callRole").val(),
		"callServiceType" : $("#callServiceType").val(),
		"e_name" : $("#callSearchForm #e_name").val(),
		"e_mobile" : $("#callSearchForm #e_mobile").val(),
		"s_name" : $("#callSearchForm #s_name").val(),
		"s_mobile" : $("#callSearchForm #s_mobile").val(),
		"createTimeStart" : $("#callSearchForm #createTimeStart").val(),
		"createTimeEnd" : $("#callSearchForm #createTimeEnd").val()
	};
	return params;
}

function loadListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#calldg').datagrid({
		url:CONTEXT+'call/pageQuery',
		queryParams: queryParams,
		//width: 1000,  
		height: 'auto', 
		nowrap:true,
		toolbar:'#calltb',
		pageSize:50,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'sourceStr',title:'拨打来源',width:100,align:'center'},
			//{field:'sourceStr',title:'业务类型',width:100,align:'center'},
			{field:'callRoleStr',title:'主叫角色',width:100,align:'center'},
			{field:'e_Name',title:'主叫姓名',width:100,align:'center'},
			{field:'e_Mobile',title:'主叫手机',width:100,align:'center'},
			{field:'callServiceType',title:'主叫业务范围',width:100,align:'center'},
			{field:'s_Name',title:'被叫姓名',width:100,align:'center'},
			{field:'s_Mobile',title:'被叫手机',width:100,align:'center'},
			{field:'createTime',title:'拨打时间',width:100,align:'center'}
		]]
	}); 
}

function exportData(){
	if (disableExport){
		slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
		return;
	}
	disableExport = true;
	var params = getSearchParams();
	var paramList = "callRole=" + params.callRole +
		"&source=" + params.source +
		"&callServiceType=" + params.callServiceType + 
		"&e_name=" + params.e_name + 
		"&e_mobile=" + params.e_mobile +
		"&s_name=" + params.s_name + 
		"&s_mobile=" + params.s_mobile +
		"&createTimeStart=" + params.createTimeStart + 
	    "&createTimeEnd=" + params.createTimeEnd;
	$.ajax({
		url: CONTEXT+'call/checkExportParams',
		data : params,
		type:'post',
		success : function(data){
			//检测通过
			if (data && data.code == 10000){
				slideMessage("数据正在导出中, 请耐心等待...");
				//启动下载
				$.download(CONTEXT+'call/exportData',paramList,'post');
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
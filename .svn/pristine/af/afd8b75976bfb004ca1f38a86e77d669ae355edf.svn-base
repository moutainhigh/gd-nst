$(document).ready(function(){
	loadListData(null);
	//分页加载
	$("#adBannerdg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	
	//查询
	$('#btn-search').click(function(){
		var params = {
			"name" : $("#adBannerSearchForm #name").val(),
			"type" : $("#adBannerSearchForm #type").val(),
			"channel" : $("#adBannerSearchForm #channel").val(),
			"state" : $("#adBannerSearchForm #state").val(),
			"startDate" : $("#adBannerSearchForm #startDate").val(),
			"endDate" : $("#adBannerSearchForm #endDate").val()	
		};
		loadListData(params);
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#adBannerSearchForm")[0].reset();
	});
});

function loadListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#adBannerdg').datagrid({
		url:CONTEXT+'adBanner/queryPage',
		queryParams: queryParams,
		//width: 1000,  
		height: 'auto', 
		nowrap:true,
		toolbar:'#adBannertb',
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'id',title:'',width:50,checkbox:true},
			{field:'name',title:'广告名称',width:100,align:'center'},
			{field:'channelStr',title:'广告渠道',width:100,align:'center'},
			{field:'typeStr',title:'广告类型',width:100,align:'center'},
			{field:'startTime',title:'开始时间',width:100,align:'center'},
			{field:'endTime',title:'结束时间',width:100,align:'center'},
			{field:'state',hidden:'true'},
			{field:'stateStr',title:'状态',width:100,align:'center'},
			{field:'sort',title:'排序',width:100,align:'center'},
			{field:'createUserName',title:'创建人',width:100,align:'center'},
			{field:'createTime',title:'创建时间',width:100,align:'center'},
			{field:'opt',title:'操作',width:100,align:'center',formatter:optFormatter}
		]],
		onLoadError:function(){
			warningMessage("服务器出错");
		}
	}); 
}

function openAddDialog(){
	$('#addDialog').dialog({'title':'添加广告', 'width':600, 'height':500, 'href':CONTEXT+'adBanner/add'}).dialog('open');
}

function detail(id){
	$('#detailDialog').dialog({'title':'查看广告', 'width':600, 'height':500, 'href':CONTEXT+'adBanner/detail/'+id}).dialog('open');
}

function openEditDialog(id){
	$('#editDialog').dialog({'title':'编辑广告', 'width':600, 'height':500, 'href':CONTEXT+'adBanner/edit/'+id}).dialog('open');
}

function saveAdd(){
	if(!$("#addAdBannerForm").form('validate')){
		return false;
	}
	if($("#addAdBannerForm #J_Urls1").val() == ''){
		warningMessage("请上传文件！");
		return false;
	}
	$.ajax({
        type: "POST",
        url: CONTEXT + "adBanner/saveAdd",
        data:$('#addAdBannerForm').serialize(),
        dataType: "json",
        success: function(data){
        	if (data.code == 10000) {
    			slideMessage("操作成功！");
    			$('#addDialog').dialog('close');
    			$("#adBannerdg").datagrid('reload');
    		}else{
    			warningMessage(data.result);
    			return;
    		}
        },
        error: function(){
        	warningMessage("服务器出错");
        }
	});
}

function saveEdit(){
	if(!$("#editAdBannerForm").form('validate')){
		return false;
	}
	if($("#editAdBannerForm #J_Urls1").val() == ''){
		warningMessage("请上传文件！");
		return false;
	}
	$.ajax({
        type: "POST",
        url: CONTEXT + "adBanner/saveEdit",
        data:$('#editAdBannerForm').serialize(),
        dataType: "json",
        success: function(data){
        	if (data.code == 10000) {
    			slideMessage("操作成功！");
    			$('#editDialog').dialog('close');
    			$("#adBannerdg").datagrid('reload');
    		}else{
    			warningMessage(data.result);
    			return;
    		}
        },
        error: function(){
        	warningMessage("服务器出错");
        }
	});
}

function batchDelete(){
	var row = $('#adBannerdg').datagrid("getSelections");
	if($(row).length < 1 ) {
	   warningMessage("请选择要删除的数据！");
	   return;
	}
	jQuery.messager.confirm('提示', '您确定要删除所选数据吗?', function(r){
		if (r){
			var ids = getSelections("id");
			$.ajax({
		        type: "POST",
		        url: CONTEXT + "adBanner/delete",
		        data:{"ids": ids},
		        dataType: "json",
		        success: function(data){
		        	if (data.code == 10000) {
		    			slideMessage("操作成功！");
		    			$("#adBannerdg").datagrid('reload');
		    		}else{
		    			warningMessage(data.result);
		    			return;
		    		}
		        },
		        error: function(){
		        	warningMessage("服务器出错");
		        }
			});
		}
	});
}

function updateState(id, state){
	jQuery.messager.confirm('提示', '您确定要修改所选数据吗?', function(r){
		if (r){
			$.ajax({
		        type: "POST",
		        url: CONTEXT + "adBanner/updateState",
		        data:{"id": id, "state" : state},
		        dataType: "json",
		        success: function(data){
		        	if (data.code == 10000) {
		    			slideMessage("操作成功！");
		    			$("#adBannerdg").datagrid('reload');
		    		}else{
		    			warningMessage(data.result);
		    			return;
		    		}
		        },
		        error: function(){
		        	warningMessage("服务器出错");
		        }
			});
		}
	});
}
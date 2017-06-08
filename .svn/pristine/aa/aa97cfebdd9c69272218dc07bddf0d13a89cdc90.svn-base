var disableExport = false;
$(document).ready(function(){
	loadListRuleInfo(null);
	//分页加载
	$("#ruleInfodg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	
	//查询
	$('#btn-search').click(function(){
		loadListRuleInfo(getSearchParams());
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#ruleInfoSearchForm")[0].reset();
	});
});

function getSearchParams(){
	var params = {
			"name" : $("#ruleInfoSearchForm #name").val(),
			"onOff" : $("#ruleInfoSearchForm #onOff").val(),
			"sourceType" : $("#ruleInfoSearchForm #sourceType").val(),
			"provinceId" : $("#provinceId").val(),
			"cityId" : $("#cityId").val(),
			"areaId" : $("#areaId").val()
		};
	return params;
}

function loadListRuleInfo(queryParams){
	initAreaTopList();
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#ruleInfodg').datagrid({
		url:CONTEXT+'rule/pageQuery',
		queryParams: queryParams,
		//width: 1000,  
		height: 'auto', 
		nowrap:true,
		toolbar:'#ruleInfotb',
		pageSize:50,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		singleSelect:true,
		columns:[[
		    {field:'id',title:'',checkbox:true},
			{field:'name',title:'规则名称',width:100,align:'left',formatter: nameFmt},
			{field:'sourceAddress',title:'货源所在地',width:100,align:'center'},
			{field:'sourceTypeStr',title:'货源类别',width:100,align:'center'},
			{field:'onOffStr',title:'规则状态',width:100,align:'center'},
			{field:'createUser',title:'创建人',width:100,align:'center'},
			{field:'onTimeStr',title:'规则启用时间',width:100,align:'center'},
			{field:'offTimeStr',title:'规则禁用时间',width:100,align:'center'},
			{field:'createTime',title:'创建时间',width:100,align:'center'}
		]]
	}); 
}

function openAddDialog(){
	$('#addDialog').dialog({'title':'新增规则', 'width':850, 'height':500, 'href':CONTEXT+'rule/add'}).dialog('open');
}

function editRuleInfo(id){
	$('#editDialog').dialog({'title':'修改规则','href':CONTEXT+'rule/edit/'+id,'width': 850,'height': 500}).dialog('open');
}

function onOffFuc(){
	var rows = $('#ruleInfodg').datagrid("getSelections");
    if(rows.length < 1 ) {
    	warningMessage("请选择要操作的记录！");
        return false;
    }
    var onOff = rows[0].onOff;
    if(onOff == 2){
    	warningMessage("已禁用的规则不能重新启用！");
    	return false;
    }
    var onOffName = null;
    var onOffNew = null;
    if(onOff == 0){
    	onOffName = "启用";
    	onOffNew = 1;
    }
    if(onOff == 1){
    	onOffName = "禁用";
    	onOffNew = 2;
    }
    var content = " 确认要" + onOffName + "规则:    "+rows[0].name+"吗？";
	jQuery.messager.confirm('提示', content, function(result){
		if (result){
    		var id = rows[0].id;
    		jQuery.post(CONTEXT+"rule/resetOnOffById", {"id":id,"onOff":onOffNew}, function(data){
    			console.log(data);
    			if (data.code == 10000) {
    				$('#ruleInfodg').datagrid('load');
    				slideMessage(onOffName+"成功！");
    			}else{
					warningMessage(data.msg);
				}
    		}, "json");
		}
	});
}


$("#addRuleInfo").click(function(){
	addOncick();
});

$("#editRuleInfo").click(function(){
	editOnclick();
});

$("#btn-export").click(function(){
	if (disableExport){
		slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
		return;
	}
	disableExport = true;
	var params = getSearchParams();
	$.ajax({
		url: CONTEXT+'rule/exportCheck',
		data : params,
		type:'post',
		success : function(data){
			//检测通过
			if (data && data.code == 10000){
				slideMessage("数据正在导出中, 请耐心等待...");
				//启动下载
				$.download(CONTEXT+'rule/export',params);
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
});

$("#btn-exportDetail").click(function(){
	var rows = $('#ruleInfodg').datagrid("getSelections");
    if(rows.length < 1 ) {
    	warningMessage("请选择导出的规则！");
        return false;
    }
    
	if (disableExport){
		slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
		return;
	}
	disableExport = true;
	var params = {id : rows[0].id};
	$.ajax({
		url: CONTEXT+'rule/exportDetailCheck',
		data : params,
		type:'post',
		success : function(data){
			//检测通过
			if (data && data.code == 10000){
				slideMessage("数据正在导出中, 请耐心等待...");
				//启动下载
				$.download(CONTEXT+'rule/exportDetail',params);
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
});


function initAreaTopList(){
	var areaTopList = queryAreaTopList();
	$.each(areaTopList, function(i, item){
		$("#provinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");
	}); 
}

$("#provinceId").change(function(){
	$("#cityId").html("<option value=''>请选择城市</option>");
	$("#areaId").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#cityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});

$("#cityId").change(function(){
	$("#areaId").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#areaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});


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


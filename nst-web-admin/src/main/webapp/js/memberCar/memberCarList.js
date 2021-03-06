var disableExport = false;
$(document).ready(function(){
	fixRownumber();
	loadListData(null);
	//分页加载
	$("#memberCardg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	$('#win').window('close'); // close a window
	//查询
	$('#btn-search').click(function(){
		var endDate=$("#memberCarSearchForm #endDate").val();
		if(endDate.length>0){
			endDate=endDate+" 23:59:59"
		}
		var params = {
			"serviceType" : $("#memberCarSearchForm #memberCarServiceType").val(),	
			"carNumber" : $("#memberCarSearchForm #carNumber").val(),
			"mobile": $("#memberCarSearchForm #mobile").val(),
			"userName" : $("#memberCarSearchForm #userName").val(),
			"carType" : $("#memberCarSearchForm #carType").val(),
			"isDeleted" : $("#memberCarSearchForm #isDeleted").val(),
			"startDate" : $("#memberCarSearchForm #startDate").val(),
			"endDate" :endDate
		};
		loadListData(params);
	});
	
	//重置
	$('#btn-reset').click(function(){
		$("#memberCarSearchForm")[0].reset();
	});
});

function loadPage(){
	var endDate=$("#memberCarSearchForm #endDate").val();
	if(endDate.length>0){
		endDate=endDate+" 23:59:59"
	}
	var params = {
		"serviceType" : $("#memberCarSearchForm #memberCarServiceType").val(),	
		"carNumber" : $("#memberCarSearchForm #carNumber").val(),
		"mobile": $("#memberCarSearchForm #mobile").val(),
		"userName" : $("#memberCarSearchForm #userName").val(),
		"carType" : $("#memberCarSearchForm #carType").val(),
		"isDeleted" : $("#memberCarSearchForm #isDeleted").val(),
		"startDate" : $("#memberCarSearchForm #startDate").val(),
		"endDate" :endDate
	};
	loadListData(params);
}

function loadListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#memberCardg').datagrid({
		url:CONTEXT+'memberCar/queryPage',
		queryParams: queryParams,
		height: 'auto', 
		toolbar:'#memberCartb',
		nowrap:true,
		pageSize:50,
		singleSelect:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
		    {field:'id',title:'id',width:100,align:'left',hidden:true},	
			{field:'carNumber',title:'车牌号',width:100,align:'left',formatter :carNumberFormatter},
			{field:'carTypeStr',title:'车辆类型',width:100,align:'center'},
			{field:'carLength',title:'车长(米)',width:100,align:'center',formatter : function(i,r,v) {
			if(i==-2||i=='-2'){
				return "其他"
				}else{
					return i;
				}	
			}
			},
			{field:'load',title:'载重(吨)',width:50,align:'center'},
			{field:'userName',title:'用户姓名',width:100,align:'left'},
			{field:'mobile',title:'用户手机',width:100,align:'center'},
			{field:'serviceType',title:'业务范围',align:'center',formatter : function(v, d, i) {
				if (v == '1') {
					return "干线业务";
				} else if (v == '2') {
					return "同城业务";
				} }},
			{field:'createTime',title:'创建时间',width:100,align:'center'},
			{field:'isDeleted',title:'车辆状态',align:'center',formatter : function(v, d, i) {
				if (v == '0') {
					return "使用中";
				} else if (v == '1') {
					return "已删除";
				} }
			},
			{field:'action',width:100, fit:true,align:'center',formatter:updateOperate, title: '操作'}
		]],
		  onLoadSuccess: function (data) {
			  $(this).datagrid("fixRownumber");
		  }
		/*,
		onClickRow:function(i,r,v){
			editObj(r.id); 
		}*/
	}); 
}


function updateAction(id){
	var fatherText =id;  
    $('#fatherId').val(fatherText);    
	$('#deleteCar').dialog({
		'title':'删除',
		"width" : 300,
		"height" :200
		}).dialog('open');
}


function editObj(id) {
	$('#win').dialog({
		'title' : '车辆管理',
		'href' : CONTEXT + 'memberCar/queryMemberCarDetail/'+id,
		"width" : 800,
		"height" :450
	}).dialog('open');
}

function deleteMemberCar(){
	var id =$("#fatherId").val();
	$.ajax({
		url : CONTEXT + 'memberCar/deleteMemberCar',
		data:{'id':id},
		type : 'post',
		dataType: "json", 
		success:function(data) {
			if (data.success) {
				slideMessage(data.msg);
			} else {
				warningMessage(data.msg);
			}
			// 刷新父页面列表
			loadPage();
		},
		error : function(data) {
			warningMessage(data.msg);
		},
	});
	$('#deleteCar').dialog('close');
}
//数据导出
$("#exportData").click(
				function() {
					var endDate=$("#memberCarSearchForm #endDate").val();
					if(endDate.length>0){
						endDate=endDate+" 23:59:59"
					}
				   var queryParams = {
							"serviceType" : $("#memberCarSearchForm #memberCarServiceType").val(),	
							"carNumber" : $("#memberCarSearchForm #carNumber").val(),
							"mobile": $("#memberCarSearchForm #mobile").val(),
							"userName" : $("#memberCarSearchForm #userName").val(),
							"carType" : $("#memberCarSearchForm #carType").val(),
							"isDeleted" : $("#memberCarSearchForm #isDeleted").val(),
							"startDate" : $("#memberCarSearchForm #startDate").val(),
							"endDate" :endDate
						};
					var paramList = "carNumber=" + queryParams.carNumber
					        + "&mobile=" + queryParams.mobile
					        + "&userName=" + queryParams.userName
							+ "&carType=" + queryParams.carType
							+ "&isDeleted=" + queryParams.isDeleted 
							+ "&serviceType=" + queryParams.serviceType 
							+ "&startDate="+ queryParams.startDate
							+ "&endDate="+ queryParams.endDate;
				if(disableExport) {
					slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
					 return;
				}
					disableExport = true;
					$.ajax({
								url : CONTEXT + 'memberCar/checkExportParams',
								data : queryParams,
								type : 'post',
								success : function(data) {
									// 检测通过
									if (data && data.status == 1) {
											slideMessage("数据正在导出中, 请耐心等待...");
											// 启动下载
											$.download(CONTEXT
													+ 'memberCar/exportData',
													paramList, 'post');
									} else {
										warningMessage(data.message);
									}
								},
								error : function(data) {
									warningMessage(data);
								}
							});
					// 10秒后导出按钮重新可用
					setInterval(function(){
						disableExport = false;
					}, 10000);
				});

jQuery.download = function(url, data, method) {
	// 获得url和data
	if (url && data) {
		// data 是 string或者 array/object
		data = typeof data == 'string' ? data : jQuery.param(data);
		// 把参数组装成 form的  input
		var inputs = '';
		jQuery.each(data.split('&'), function() {
			var pair = this.split('=');
			inputs += '<input type="hidden" name="' + pair[0] + '" value="'
					+ pair[1] + '" />';
		});
		// request发送请求
		jQuery(
				'<form action="' + url + '" method="' + (method || 'post')
						+ '">' + inputs + '</form>').appendTo('body').submit()
				.remove();
	}
	;
};



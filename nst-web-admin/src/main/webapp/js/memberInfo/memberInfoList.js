var disableExport = false;
$(document).ready(function(){
	fixRownumber();
	loadMemberListData(null);
	//分页加载
	$("#memberInfodg").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
	$('#win').window('close'); // close a window
	//查询
	$('#member-btn-search').click(function(){
		var endDate=$("#memberInfoSearchForm #endDate").val();
		if(endDate.length>0||endDate!=""){
			endDate=endDate+" 23:59:59"
		}
		var params = {
			"userName" : $("#memberInfoSearchForm #listUserName").val(),
			"memberIdLogisticName": $("#memberInfoSearchForm #listMemberIdLogisticName").val(),
			"appoint": $("#memberInfoSearchForm #appoint").val(),
			"regetype": $("#memberInfoSearchForm #regetype").val(),
			"mobile" : $("#memberInfoSearchForm #listMobile").val(),
            "cerCompanyStatus": $("#memberInfoSearchForm #cerCompanyStatus").val(),
            "cerPersonalStatus": $("#memberInfoSearchForm #cerPersonalStatus").val(),
            "serviceType":$("#memberInfoSearchForm #serviceType").val(),
			"status" : $("#memberInfoSearchForm #status").val(),
			"startDate" : $("#memberInfoSearchForm #startDate").val(),
			"endDate" :endDate
		};
		loadMemberListData(params);
	});
	
	
	//重置
	$('#btn-reset').click(function(){
		$("#memberInfoSearchForm")[0].reset();
	});

});


function loadMemberListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#memberInfodg').datagrid({
		url:CONTEXT+'memberInfo/queryPage',
		queryParams: queryParams,
		//width: 1000,  
		height: 'auto', 
		toolbar:'#memberInfotb',
		nowrap:true,
		rownumWidth: 50,
		pageSize:50,
		singleSelect:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		fit:true,
		columns:[[
		/*	{field:'mobile',title:'账号',align:'center'},*/
		/*	{field:'appType',title:'用户类型',align:'center'},*/
			{field:'account',title:'账号',align:'left',formatter :accountFormatter},
			{field:'regetypeStr',title:'注册来源',align:'center'},
			{field:'userName',title:'姓名',align:'left'},
			{field:'mobile',title:'手机',align:'center'},
			{field:'createTime',title:'注册时间',align:'center'},
			{field:'latestLoginTime',title:'最后一次登录时间',align:'center'},
			{field:'cerPersonalStatusStr',title:'个人认证',align:'center'},
			{field:'cerCompanyStatusStr',title:'企业认证',align:'center'},
			{field:'addressStr',title:'所在城市',align:'left'},
			{field:'status',title:'账号状态',align:'center',formatter : function(v, r, i) {
				if (v == '1') {
					return "启用";
				} else if (v == '0') {
					return "禁用";
				} 
				}
			},
			{field:'appoint',title:'货源是否指派',align:'center',formatter : function(v, d, i) {
				if (v == '1') {
					return "否";
				} else if (v == '0') {
					return "是";
				}  else if (v == null) {
					return "否";
				} 
				
			}
			}, {field:'memberIdLogisticName',title:'指派的公司/车主',align:'center'},
			{field:'serviceType',title:'业务范围',align:'center',formatter : function(v, d, i) {
				if (v == '1') {
					return "干线业务";
				} else if (v == '2') {
					return "同城业务";
				} }}
		   
		]],
		  onLoadSuccess: function (data) {
			  $(this).datagrid("fixRownumber");
		  }/*,
		onClickRow:function(i,r,v){
			editObj(r.id); 
		}*/
	}); 
}


function editObj(id) {
	$('#win').dialog({
		'title' : '会员管理',
		'href' : CONTEXT + 'memberInfo/queryMemberInfoDetail/' + id,
		"width" : 800,
		"height" :400
	}).dialog('open');
}


//数据导出
$("#exportData").click(
				function() {
					var endDate=$("#memberInfoSearchForm #endDate").val();
					if(endDate.length>0){
						endDate=endDate+" 23:59:59"
					}
				   var queryParams = {
						   "userName" : $("#memberInfoSearchForm #listUserName").val(),
							"memberIdLogisticName": $("#memberInfoSearchForm #listMemberIdLogisticName").val(),
							"appoint": $("#memberInfoSearchForm #appoint").val(),
							"regetype": $("#memberInfoSearchForm #regetype").val(),
							"mobile" : $("#memberInfoSearchForm #listMobile").val(),
				            "cerCompanyStatus": $("#memberInfoSearchForm #cerCompanyStatus").val(),
				            "cerPersonalStatus": $("#memberInfoSearchForm #cerPersonalStatus").val(),
				            "serviceType":$("#memberInfoSearchForm #serviceType").val(),
							"status" : $("#memberInfoSearchForm #status").val(),
							"startDate" : $("#memberInfoSearchForm #startDate").val(),
							"endDate" :endDate
						};
					var paramList = "userName=" + queryParams.userName
					        + "&regetype=" + queryParams.regetype
					        + "&memberIdLogisticName=" + queryParams.memberIdLogisticName
					        + "&appoint=" + queryParams.appoint
					        + "&mobile=" + queryParams.mobile
					        + "&cerCompanyStatus=" + queryParams.cerCompanyStatus
					        + "&cerPersonalStatus=" + queryParams.cerPersonalStatus
					        + "&serviceType=" + queryParams.serviceType
							+ "&status=" + queryParams.status
							+ "&startDate=" + queryParams.startDate 
							+ "&endDate="+ queryParams.endDate;
					if (disableExport) {
						slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");	
						return;
					}
					disableExport = true;
					$.ajax({
								url : CONTEXT + 'memberInfo/checkExportParams',
								data : queryParams,
								type : 'post',
								success : function(data) {
									// 检测通过
									if (data && data.status == 1) {
											slideMessage("数据正在导出中, 请耐心等待...");
											// 启动下载
											$.download(CONTEXT
													+ 'memberInfo/exportData',
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

//禁用启用 点击事件
$("#disableBtn").click(function(){
	var rows = $('#memberInfodg').datagrid("getSelections");
    if(rows.length < 1 ) {
    	warningMessage("请选择要操作的记录！");
        return false;
    }
    if(rows.length > 1 ) {
    	warningMessage("操作的记录只能为1条！");
        return false;
    }
    var status=rows[0].status;
    var statusName="";
    var statusValue=0;
    if(status==0){
    	statusName=	"启用";
    	statusValue=1;
    }else{
    	statusName="禁用";
    	statusValue=0;
    }
    var name=rows[0].userName;
    if(name==undefined){
    	name=rows[0].mobile;
    }
    var promptForRemove = " 确认"+statusName+"用户      "+name+"";
	jQuery.messager.confirm('提示', promptForRemove, function(result){
		if (result){
    		var idStr = rows[0].id;
    		jQuery.post(CONTEXT+"memberInfo/resetStatusById", {"memberId":idStr,"status":statusValue,"userName":name}, function(data){
    			if (data.msg == "success") {
    				$('#memberInfodg').datagrid('load');
    				slideMessage(statusName+"成功！");
    			}else{
					warningMessage(data.msg);
				}
    		}, "json");
		}
	});
});

//重置密码 点击事件
$("#resetPwd").click(function(){
	var rows = $('#memberInfodg').datagrid("getSelections");
    if(rows.length < 1 ) {
    	warningMessage("请选择要操作的记录！");
        return false;
    }
    if(rows.length > 1 ) {
    	warningMessage("操作的记录只能为1条！");
        return false;
    }
    var name=rows[0].userName;
    if(name==undefined){
    	name=rows[0].mobile;
    }
    var promptForRemove = " 确认用户重置：      "+name+"  的密码？";
	jQuery.messager.confirm('提示', promptForRemove, function(result){
		if (result){
    		var idStr = rows[0].id;
    		jQuery.post(CONTEXT+"memberInfo/resetPwdById", {"memberId":idStr}, function(data){
    			if (data.msg == "success") {
    				$('#memberInfodg').datagrid('load');
    				slideMessage("修改成功！");
    			}else{s
					warningMessage(data.msg);
				}
    		}, "json");
		}
	});
})



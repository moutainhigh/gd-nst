var disableExport = false;
$(document).ready(function(){
	fixRownumber();
	initAreaTopList();
	load(null,CONTEXT+'notice/getNstNoticeList');
});
$('#nstNoticeSearchForm #icon-search').click(function(){ 
	var endDate=$("#nstNoticeSearchForm #startEndTime").val();
	if(endDate.length>0){
		endDate=endDate+" 23:59:59"
	}
	var params={
			"startBeginTime":$("#nstNoticeSearchForm #startBeginTime").val(),
			"startEndTime":endDate,
			"title":$("#nstNoticeSearchForm #noticeTitle").val(),
			"channel":$("#nstNoticeSearchForm #channelTypes").val(),
			"provinceId":$("#nstNoticeSearchForm #sProvinceId").val(),
			"cityId":$("#nstNoticeSearchForm #sCityId").val(),
			"onOff":$("#nstNoticeSearchForm #noticeOnOff").val()
	        };
	load(params,CONTEXT+'notice/getNstNoticeList');
});


$("#nstNoticeSearchForm #sProvinceId").change(function(){
	$("#sCityId").html("<option value=''>请选择城市</option>");
	var parentId = $(this).val();
	if(parentId==0){
		$("#sCityId").hide();
	}else{
		$("#sCityId").show();
	}
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#sCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});





function load(params, loadUrl){
	params = !params ? {}: params;
	//数据加载
	$('#nstNoticedg').datagrid({
		url:loadUrl,
		queryParams: params,
		height: 'auto', 
		nowrap:true,
		toolbar:'#nstNoticetb',
		pageSize:50,
		singleSelect:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
					{field:'title',title:'公告名称',width:200,align:'center',formatter :titleFormatter},
					{field:'channelStr',title:'渠道',width:100,align:'center'},
					{field:'city',title:'所在城市',width:100,align:'center'},
					{field:'userName',title:'创建人',width:100,align:'center'},
					{field:'createTime',title:'发布时间',width:100,align:'center'},
					{field:'onOffStr',title:'公告状态',width:100,align:'center'}
				]],
				  onLoadSuccess: function (data) {
					  $(this).datagrid("fixRownumber");
				  }
	}); 
	
}


//禁用启用 点击事件
$("#nstNoticeSearchForm #disableBtn").click(function(){

	var rows = $('#nstNoticedg').datagrid("getSelections");
  if(rows.length < 1 ) {
  	warningMessage("请选择要操作的记录！");
      return false;
  }
  if(rows.length > 1 ) {
  	warningMessage("操作的记录只能为1条！");
      return false;
  }
  var status=rows[0].onOff;
  var statusName="";
  var statusValue=0;
  if(status==0){
  	statusName=	"启用";
  	statusValue=2;
  }else  if(status==1){
	statusName=	"启用";
	statusValue=2;
  }else{
  	statusName="禁用";
  	statusValue=1;
  }
  var name=rows[0].title;
  var promptForRemove = " 确认"+statusName+" 公告    "+name+"？";
	jQuery.messager.confirm('提示', promptForRemove, function(result){
		if (result){
  		var idStr = rows[0].id;
  		jQuery.post(CONTEXT+"notice/resetStatusById", {"id":idStr,"onOff":statusValue}, function(data){
  			if (data.msg == "success") {
  				$('#nstNoticedg').datagrid('load');
  				slideMessage(statusName+"成功！");
  			}else{
					warningMessage(data.msg);
				}
  		}, "json");
		}
	});
});


$("#nstNoticeSearchForm #btn-reset").click(function(){
	$("#nstNoticeSearchForm")[0].reset();
});

$("#nstNoticeSearchForm #btn-add").click(function(){
	$('#nstNoticeDialog').dialog({'title':'添加公告', 'width':600, 'height':350, 'href':CONTEXT+'notice/addNstNotice'}).dialog('open');
});

$("#btn-save").click(function(){
	var content=$('#add #content').val();
	var title=$('#add #title').val();
	var city =$("#add #originCity_typeIn").val();
	var channelTypes =$("#add #channel").val();
	if(content==""){
		alert("输入内容为空,请输入！");
		return;
	}
	if(content.length>50){
		alert("输入内容过长,字长不得超过50个！");
		return;
	}
	if(city==""){
		alert("请选城市！");
		return;
	}
	if(channelTypes==0||channelTypes=='0'){
		alert("请选择渠道！");
		return;
	}
	if(title==""){
		alert("请输入公告名称！");
		return;
	}
	
	var url = CONTEXT + "notice/save";
	jQuery.post(url, $('#addForm').serialize(), function(data) {
		if (data.msg == "success") {
			slideMessage("保存成功！");
			// 刷新父页面列表
			$("#nstNoticedg").datagrid('reload');
			$('#nstNoticeDialog').dialog('close');
		} else {
			warningMessage(data.msg);
			return;
		}
	});
});


//修改
function editNotice(id){
		$('#editNoticeDialog').dialog({'title':'修改公告','href':CONTEXT+'notice/editById/'+id,'width': 600,'height': 350}).dialog('open');
}

$("#btn-edit").click(function(){
	var content=$('#edit #content').val();
	var title=$('#edit #title').val();
	var city =$("#edit #originCity_typeIn").val();
	var channelTypes =$("#edit #channel").val();
	if(content==""){
		alert("输入内容为空,请输入！");
		return;
	}
	if(content.length>50){
		alert("输入内容过长,字长不得超过50个！");
		return;
	}
	if(city==""){
		alert("请选城市！");
		return;
	}
	if(channelTypes==""){
		alert("请选择渠道！");
		return;
	}
	if(title==""){
		alert("请输入公告名称！");
		return;
	}
	
	var url = CONTEXT + "notice/update";
	jQuery.post(url, $('#editForm').serialize(), function(data) {
		if (data.msg == "success") {
			slideMessage("修改成功！");
			// 刷新父页面列表
			$("#nstNoticedg").datagrid('reload');
			$('#editNoticeDialog').dialog('close');
		} else {
			warningMessage(data.msg);
			return;
		}
	});
});



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
		$("#nstNoticeSearchForm #sProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
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


//数据导出
$("#nstNoticeSearchForm  #exportData").click(
				function() {
					var endDate=$("#nstNoticeSearchForm #startEndTime").val();
					if(endDate.length>0){
						endDate=endDate+" 23:59:59"
					}
					var queryParams={
							"startBeginTime":$("#nstNoticeSearchForm #startBeginTime").val(),
							"startEndTime":endDate,
							"title":$("#nstNoticeSearchForm #noticeTitle").val(),
							"channel":$("#nstNoticeSearchForm #channelTypes").val(),
							"provinceId":$("#nstNoticeSearchForm #sProvinceId").val(),
							"cityId":$("#nstNoticeSearchForm #sCityId").val(),
							"onOff":$("#nstNoticeSearchForm #noticeOnOff").val()
					        };
					var paramList = "startBeginTime=" + queryParams.startBeginTime
					        + "&startEndTime=" + queryParams.startEndTime
					        + "&title=" + queryParams.title
					        + "&channel=" + queryParams.channel
					        + "&provinceId=" + queryParams.provinceId
					        + "&cityId=" + queryParams.cityId
					        + "&onOff=" + queryParams.onOff;
					if (disableExport) {
						slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");	
						return;
					}
					disableExport = true;
					$.ajax({
								url : CONTEXT + 'notice/checkExportParams',
								data : queryParams,
								type : 'post',
								success : function(data) {
									// 检测通过
									if (data && data.status == 1) {
											slideMessage("数据正在导出中, 请耐心等待...");
											// 启动下载
											$.download(CONTEXT
													+ 'notice/exportData',
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


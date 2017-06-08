<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="../pub/constants.inc" %>
		<%@ include file="../pub/head.inc" %>
		<%@ include file="../pub/tags.inc" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9, IE=10, IE=11, IE=12"/>
		<title></title>
		<style type="text/css">
			.redFont{
				color:red;
			}
		</style>
<%-- 		<link href="${CONTEXT}css/uploader.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${CONTEXT}js/kissy-min.js"></script>
		<script type="text/javascript" src="${CONTEXT}js/gd.kissy.uploader.js"></script> --%>
		<script type="text/javascript" src="${CONTEXT}js/My97DatePicker/WdatePicker.js"></script>		
	</head>
<body>
	<table id="appVersionList"></table>
	
	
	<div id="appVersiontb" style="padding:5px;height:auto">
		<form id="appVersionSearchForm" method="post">
			<div>
				App名称:
				<select name="type" id="appType" style="width: 150px;">
					<option value="">---请选择---</option>
					<option value="1">农速通-货主</option>
					<option value="2">农速通-司机</option>
					<option value="3">农速通-物流公司</option>
				</select>
				版本号:
				<input  type="text" id="appnum"    class="easyui-validatebox" name="num" style="width:150px" >
				平台：
          	  		<select name="platform" id="platform_s" style="width:120px">
          	  		    <option value="">全部</option>
          	  		    <option value="1" >IOS</option>
          	  		    <option value="2" >Android</option>
          	  		    <option value="3" >HTML5</option>
          	  		</select>	
          	  		
          	  	上线时间：
				<input name="publishTime_st" id="publishTime_st" value="${version.publishTime }" 
	          onFocus="WdatePicker({onpicked:function(){publishTime_st.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss'})"   
	          onClick="WdatePicker({onpicked:function(){publishTime_st.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss'})"
	           type="text" class="text" size="20" maxlength="20">-      
	           
				<input name="publishTime_en" id="publishTime_en" value="${version.publishTime }" 
	          onFocus="WdatePicker({onpicked:function(){publishTime_en.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss'})"   
	          onClick="WdatePicker({onpicked:function(){publishTime_en.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss'})"
	           type="text" class="text" size="20" maxlength="20">	       	  					
				
				<a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="icon-search">查询</a>
				<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="icon-reload">重置</a>
			</div>
		</form>
	 
		<div style="margin-bottom:5px">
				<a class="easyui-linkbutton" iconCls="icon-reload" id="icon-refresh">刷新</a>
				<a class="easyui-linkbutton icon-add-btn" iconCls="icon-add" id="icon-add" >新增</a>
			<!-- <a class="easyui-linkbutton icon-remove-btn" iconCls="icon-remove" id="icon-remove">删除</a> -->
		</div>
		
		<div id="addAppVersionDialog" class="easyui-dialog" style="width:800px;height:500px;" closed="true" modal="true" buttons="#dlg-buttonsAdd">
			<div id="dlg-buttonsAdd" style="text-align:center">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveAdd()">保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addAppVersionDialog').dialog('close')">关闭</a>
	        </div>
		</div>
		
		<div id="editAppVersionDialog" class="easyui-dialog" style="width:800px;height:400px;" closed="true" modal="true" buttons="#dlg-buttonsEdit">
			<div id="dlg-buttonsEdit" style="text-align:center">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveEdit()">保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editAppVersionDialog').dialog('close')">关闭</a>
	        </div>
		</div>
		<div id="detailAppVersionDialog" class="easyui-dialog" style="width:800px;height:400px;" closed="true" modal="true" buttons="#dlg-buttonsDetail">
			<div id="#dlg-buttonsDetail" style="text-align:center">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detailAppVersionDialog').dialog('close')">关闭</a>
	        </div>
		</div>
		<div id="proAppVeresionDialog" class="easyui-dialog" style="width:800px;height:400px;" closed="true" modal="true" buttons="#dlg-buttonsPro">
			<div id="#dlg-buttonsPro" style="text-align:center">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#proAppVeresionDialog').dialog('close')">关闭</a>
	        </div>
		</div>
	</div>
</body>
<script type="text/javascript">
function editObj(id){
	$('#editAppVersionDialog').dialog({'title':'编辑数据','href':CONTEXT+'app/editinit?id='+id, 'width': 800,'height': 400}).dialog('open');	
}
//var reg = new RegExp("^[0-9]*[1-9][0-9]*$"); 
var reg = new RegExp("^([0-9])([0-9.]+)([0-9])$"); 
//新增验证
function addCheck(){
	var b=true;
	if($("#type").val()==""){
		alert("APP类型为必选项!");
		b=false;
	}
	if($("#num").val()==""){
		alert("版本号为必填项且不能全为空格!");
		b=false;
	}
	if(!reg.test($("#num").val())){
		alert("版本号格式错误");
		b=false;
	}		
 	if($("#apkAddress").val()==""){
		alert("APK地址为必填项且不能全为空格!");
		b=false;
	} 
	if($("#remark").val().length>200){
		alert("描述太长，能精简点么！");
		b=false;
	}
	if($("#svnAddress").val()==""){
		alert("svn地址不能为空！");
		return false;
	}		
	if($("#publishTime").val()==""){
		alert("发布时间不能为空！");
		return false;
	}	
	if($("#platform").val()==""){
		alert("平台不能为空！");
		return false;
	}		
	
	return b;
}

function addCheck_e(){
	var b=true;
	if($("#type_e").val()==""){
		alert("APP类型为必选项!");
		b=false;
	}
	if($("#num_e").val()==""){
		alert("版本号为必填项且不能全为空格!");
		b=false;
	}
	if(!reg.test($("#num_e").val())){
		alert("版本号格式错误");
		b=false;
	}		
 	if($("#apkAddress_e").val()==""){
		alert("APK地址为必填项且不能全为空格!");
		b=false;
	} 
	if($("#remark_e").val().length>200){
		alert("描述太长，能精简点么！");
		b=false;
	}
	if($("#svnAddress_e").val()==""){
		alert("svn地址不能为空！");
		return false;
	}		
	if($("#publishTime_e").val()==""){
		alert("发布时间不能为空！");
		return false;
	}	
	if($("#platform_e").val()==""){
		alert("平台不能为空！");
		return false;
	}		
	
	return b;
}

function saveAdd(){
	if($("#type").val()==""){
		alert("APP类型为必选项!");
		return false;
	}
	if($("#platform").val()==""){
		alert("平台不能为空！");
		return false;
	}		
	if($("#num").val()==""){
		alert("版本号为必填项且不能全为空格!");
		return false;
	}
	if(!reg.test($("#num").val())){
		alert("版本号格式错误");
		return false;
	}	
	
	if($("#productleader").val()==""){
		alert("产品负责人不能为空！");
		return false;
	}		
	
	if($("#publishTime").val()==""){
		alert("发布时间不能为空！");
		return false;
	}	
	

	if($("#remark").val().length>300){
		alert("描述太长，能精简点么！");
		return false;
	}
	if($("#svnAddress").val()==""){
		alert("svn地址不能为空！");
		return false;
	}	
/*   	if($("#apkAddress").val()==""){
		alert("主站下载地址不能为空!");
		return false;
	} 	 */ 
	
			var url = CONTEXT + "/app/add";
			jQuery.post(url, $('#versionForm').serialize(), function(data) {
				if (data.code == "success") {
					slideMessage("操作成功！");
					$("#appVersionList").datagrid('load', {});
					$('#addAppVersionDialog').dialog('close');
				} else if (data.code == "fail") {
					warningMessage("操作失败");
					return;
				}else if (data.code == "-1") {
					warningMessage("版本号必须比现有的版本号大");
					return;
				}else if (data.code == "-2") {
					warningMessage("主站下载地址没有下载包");
					return;
				} else {
					warningMessage("系统异常！");
//					parent.$('#openRoleDiv').window('close');
				}
			});	
}

function saveEdit(){
	if($("#type_e").val()==""){
		alert("APP类型为必选项!");
		return false;
	}
	if($("#platform_e").val()==""){
		alert("平台不能为空！");
		return false;
	}		
	if($("#num_e").val()==""){
		alert("版本号为必填项且不能全为空格!");
		return false;
	}
	if(!reg.test($("#num_e").val())){
		alert("版本号格式错误");
		return false;
	}	
	if($("#productleader_e").val()==""){
		alert("产品负责人不能为空！");
		return false;
	}		
	if($("#publishTime_e").val()==""){
		alert("发布时间不能为空！");
		return false;
	}	
	

	if($("#remark_e").val().length>300){
		alert("描述太长，能精简点么！");
		return false;
	}
	if($("#svnAddress_e").val()==""){
		alert("svn地址不能为空！");
		return false;
	}	
/*   	if($("#apkAddress_e").val()==""){
		alert("主站下载地址不能为空!");
		return false;
	} 	  */
	
	
			var url = CONTEXT + "/app/edit";
			jQuery.post(url, $('#versionForm_e').serialize(), function(data) {
				if (data.code == "success") {
					slideMessage("操作成功！");
					$("#appVersionList").datagrid('load', {});
					$('#editAppVersionDialog').dialog('close');
				} else if (data.code == "fail") {
					warningMessage("操作失败");
					return;
				}else if (data.code == "-1") {
					warningMessage("版本号必须比现有的版本号大");
					return;
				}else if (data.code == "-2") {
					warningMessage("主站下载地址没有下载包");
					return;
				}  else {
					warningMessage("系统异常！");
//					parent.$('#openRoleDiv').window('close');
				}
			});	
}


function delObj(id){
	jQuery.messager.confirm('提示', '您确定要删除该记录吗?', function(r){
		if (r){
    		jQuery.post(CONTEXT+"/app/delete",{"id":id},function(data){
				if (data.code == "0"){
					slideMessage("操作成功！");
					$("#appVersionList").datagrid('load', {});
				}else{
					warningMessage("操作失败！");
					return;
				}
    		});
		}
	});
}

function detailObj(id){
	$('#detailAppVersionDialog').dialog({'title':'查看数据','href':CONTEXT+'app/view?id='+id, 'width': 800,'height': 400}).dialog('open');
}

function loadList(params){
	params = !params ? {}: params;
	//数据加载
	$('#appVersionList').datagrid({
		url:CONTEXT+'app/version',
		queryParams: params,
		height: 'auto', 
		nowrap:true,
		toolbar:'#appVersiontb',
		pageSize:50,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		onLoadSuccess:function(){
			$("#appVersionList").datagrid('clearSelections');
		},
		columns:[[
			//{field:'id',title:'',width:0,checkbox:false},
			{field:'platform',title:'平台',width:100,align:'center',formatter:platformFormatter },
			{field:'type',title:'App名称',width:100,align:'center',formatter:appTypeFormatter },
			{field:'num',title:'版本号',width:100,align:'center' },
			{field:'productleader',title:'产品负责人',width:100,align:'center'},
			{field:'publishTime',title:'发布时间',width:100,align:'center'},
			//{field:'status',title:'状态',width:50,align:'center',formatter:statusFormatter },
			{field:'opt',title:'操作',width:100,align:'center',formatter:optformat}
		]]
	}); 
}

function statusFormatter(val, row){
	if(val == "1"){
		return "启用";
	}else{
		return "停用";
	}	
}

function platformFormatter(val, row){
	if(val == "1"){
		return "IOS";
	}else if(val == "2"){
		return "Android";
	}else if(val == "3"){
		return "H5";
	}else{
		return "";
	}	
}

function appTypeFormatter(val, row){
	if(val == "1"){
		return "农速通-货主";
	}else if(val == "2"){
		return "农速通-司机";
	}else if(val == "3"){
		return "农速通-物流公司";
	}else{
		return "";
	}
}

function qzgxFormatter(val, row){
	if(val == "0"){
		return "不强制";
	}else if(val == "1"){
		return "强制";
	}else{
		return "";
	}
}
function optformat(value,row,index){
	if(row.status=="1"){
		return "<a class='operate' href='javascript:;' onclick=detailObj('"+row.id+"');>查看</a>&nbsp;" +
		"<a class='operate' href='javascript:;' onclick=editObj('"+row.id+"');>编辑</a>&nbsp;"+
		"<a class='operate' href='javascript:;' onclick=delObj('"+row.id+"');>删除</a>&nbsp;"
	}else{
		return "<a class='operate' href='javascript:;' onclick=detailObj('"+row.id+"');>查看</a>&nbsp;"
	}
	//"<a class='operate' href='javascript:;' onclick=delObj('"+row.id+"');>删除</a>";
}


function initList(){
	loadList(null);
	//分页加载
	$("#appVersionList").datagrid("getPager").pagination({
		pageList: [10,20,50,100]
	});
}



$(document).ready(function(){
	//查询按钮
	$('#icon-search').click(function(){
		var params = {
				"type":$("#appType").val(),
				"num":$("#appnum").val(),
				"platform":$("#platform_s").val(),
				"publishTime_en":$("#publishTime_en").val(),
				"publishTime_st":$("#publishTime_st").val()
				
		};
		loadList(params);
	});
	
	//新增
	$('#icon-add').click(function(){
		$('#addAppVersionDialog').dialog({'title':'新增数据','href':CONTEXT+'app/addinit', 'width': 800,'height': 400}).dialog('open');
	});
	
	//刷新按钮
	$('#icon-refresh').click(function(){
		$('#appVersionSearchForm')[0].reset();
		$("#appVersionList").datagrid('load', {});
	});
	
	//重置按钮
	$('#icon-reload').click(function(){
		$('#appVersionSearchForm')[0].reset();
	});
	initList();
});
</script>
</html>
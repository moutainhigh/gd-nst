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
	<title>demo</title>
	<script type="text/javascript" src="${CONTEXT}js/kissy-min.js"></script>
	<script type="text/javascript" src="${CONTEXT}js/gd.kissy.uploader.js"></script>
</head>
<body>
	<table id="adBannerdg" title=""></table>
	<div id="adBannertb" style="padding:5px;height:auto">
		<form id="adBannerSearchForm" method="post">
		<div>
			<table>
				<tr>
					<td>广告名称：</td>
					<td><input type="text" id="name" name="name" style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>广告渠道：</td>
					<td>
						<select id="channel" name="channel" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="channel" items="<%=cn.gdeng.nst.enums.AdChannelEnum.values()%>">
								<option value="${channel.code }">${channel.name}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>创建时间：</td>
					<td>
						<input  type="text" id="startDate" name="startDate"  
							onFocus="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})"   
							onClick="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})" style="width:150px" > ~
						<input  type="text" id="endDate" name="endDate"   
							onFocus="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})"   
							onClick="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})" style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>状态：</td>
					<td>
						<select id="state" name="state" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="state" items="<%=cn.gdeng.nst.enums.AdBannerStateEnum.values() %>">
								<option value="${state.code }">${state.name}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td colspan="2">
						<a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;
						<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
					</td>
				</tr>	
			</table>
		</div>
		</form>
		<div style="margin-top:10px">
			<a class="easyui-linkbutton icon-add-btn" iconCls="icon-add" onclick="openAddDialog()">新增</a>
			<a class="easyui-linkbutton icon-remove-btn" iconCls="icon-remove" onclick="batchDelete()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick='location.reload(true)'">刷新</a>
		</div>
		<div id="addDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsAdd">
			<div id="dlg-buttonsAdd" style="text-align:center">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveAdd()">保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	        </div>
		</div>
		<div id="detailDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsDetail">
			<div id="dlg-buttonsDetail" style="text-align:center">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detailDialog').dialog('close')">关闭</a>
	        </div>
		</div>
		<div id="editDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsEdit">
			<div id="dlg-buttonsEdit" style="text-align:center">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveEdit()">保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	        </div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/adBanner/main.js"></script>
<script type="text/javascript">
	function optFormatter(value,row,index){
		var opt = "";
		if(row.state == 1){
			opt += "<a class='operate' href='javascript:;' onclick=updateState('"+row.id+"','"+2+"');>禁用</a>";
		}else if(row.state == 2){
			opt += "<a class='operate' href='javascript:;' onclick=updateState('"+row.id+"','"+1+"');>启用</a>";
		}
		opt += "<a class='operate' href='javascript:;' onclick=openEditDialog('"+row.id+"');>编辑</a>";
		opt += "<a class='operate' href='javascript:;' onclick=detail('"+row.id+"');>查看</a>";
		return opt;
	}
</script>
</html>


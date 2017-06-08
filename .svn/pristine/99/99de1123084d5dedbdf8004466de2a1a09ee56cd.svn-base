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
		<title>notice</title>
	</head>
	  <script type="text/javascript" src="${CONTEXT}js/My97DatePicker/WdatePicker.js"></script> 
<body>
	<table id="nstNoticedg" title="">
	</table>
	<div id="nstNoticetb" style="padding:5px;height:auto">
		<form id="nstNoticeSearchForm" method="post">
		<table>
				<tr>
					<td>公告名称：</td>
					<td>
					<input type="text" id="noticeTitle" name="noticeTitle" style="width:204px" placeholder="请输入公告名称">&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>渠道：</td>
					<td>
					<select  name="channelTypes" id="channelTypes"  style="width: 100px;" >
				    <option value="" selected="selected">请选择渠道</option>
				    <option value="1">车主APP</option>
				    <option value="2">货主APP</option>
				    <option value="3">物流公司APP</option>
				    </select>
				    </td>
					<td>发布时间：</td>
					<td><input  type="text"  id="startBeginTime" name="startBeginTime"  
				onFocus="WdatePicker({onpicked:function(){startBeginTime.focus();},maxDate:'#F{$dp.$D(\'startEndTime\')}',dateFmt:'yyyy-MM-dd'})"   
				onClick="WdatePicker({onpicked:function(){startBeginTime.focus();},maxDate:'#F{$dp.$D(\'startEndTime\')}',dateFmt:'yyyy-MM-dd'})"    
				style="width:150px" >~
			<input  type="text"    id="startEndTime" name="startEndTime"   
				onFocus="WdatePicker({onpicked:function(){startEndTime.focus();},minDate:'#F{$dp.$D(\'startBeginTime\')}',dateFmt:'yyyy-MM-dd'})"   
				onClick="WdatePicker({onpicked:function(){startEndTime.focus();},minDate:'#F{$dp.$D(\'startBeginTime\')}',dateFmt:'yyyy-MM-dd'})"><br>
			</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
				    <td>所在城市：</td>
					<td><select name="sProvinceId" id="sProvinceId">
						<option value="">请选择省份</option>
						<option value="0">默认</option>
					</select>
					<select name="sCityId" id="sCityId">
						<option value="">请选择城市</option>
					</select>
					</td>
					<td>公告状态：</td>
					<td>
						<select  name="noticeOnOff" id="noticeOnOff"  style="width: 100px;">
					        <option value="">全部</option>
 							<option value="0">待发</option>
 							<option value="1">禁用</option>
 							<option value="2">启用</option>
 					</select>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="2">
						<gd:btn btncode="NRGLGGGL001">
				        <a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="icon-search" OnClick ="return $('#nstNoticeSearchForm').form('validate');">查询</a>
			           </gd:btn>
			           <a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
			           <a class="easyui-linkbutton" iconCls="icon-reload" id="icon-refresh" onclick="location.reload(true)">刷新</a>
					</td>
				</tr>	
			</table>
		<div style="background:#efefef;padding:5px 0 5px 0;height:25px;">
			<div style="float: left;font-size:14px;margin-left:5px;">公告管理</div>
			<div style="float:right;margin-right:10px;">
			<gd:btn btncode="NRGLGGGL002">
				<a class="easyui-linkbutton" iconCls="icon-add" id="btn-add">增加</a>
			</gd:btn>
		    <gd:btn btncode='NOTICEDISABLE'>
			<a id="disableBtn" class="easyui-linkbutton" iconCls="icon-reload">禁用/启用</a>
			</gd:btn>
			 <gd:btn btncode='NOTICEEXP'>
		   <a class="easyui-linkbutton icon-save-btn" iconCls="icon-account-excel" id="exportData" >导出</a>&nbsp;&nbsp;
			</gd:btn>
			</div>
		</div>	
     </form>
	</div>
	<div id="nstNoticeDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#nstNoticeDetail">
		<div id="nstNoticeDetail" style="text-align:center">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" id="btn-save" >保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#nstNoticeDialog').dialog('close')">关闭</a>
		</div>
	</div>
	<div id="editNoticeDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#editNotice">
		<div id="editNotice" style="text-align:center">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" id="btn-edit" >保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editNoticeDialog').dialog('close')">关闭</a>
		</div>
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/notice/main.js"></script>
<script type="text/javascript">
function titleFormatter(v,r,i) {
			return "<gd:btn btncode='NRGLGGGL004'><a  class='operate' href='javascript:editNotice("+r.id+")' >	</gd:btn>"+v+"<gd:btn btncode='MEMBERCARDETAIL'></a>	</gd:btn>"
		}
</script>
</html>

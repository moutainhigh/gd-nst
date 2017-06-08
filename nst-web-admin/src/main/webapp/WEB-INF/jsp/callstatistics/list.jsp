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
		<title>拨打电话统计</title>
	</head>
<body>
	<table id="calldg" title="">
	</table>
	<div id="calltb" style="padding:5px;height:auto">
		<form id="callSearchForm" method="post">
		<div>
			<table>
				<tr>
					<td>主叫角色：</td>
					<td>
						<select id="callRole" name="callRole" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="callRole" items="<%=cn.gdeng.nst.enums.CallRoleEnum.values() %>">
								<option value="${callRole.code }">${callRole.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>主叫业务范围：</td>
					<td>
						<select id="callServiceType" name="callServiceType" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="callServiceType" items="<%=cn.gdeng.nst.enums.CallServiceTypeEnum.values() %>">
								<option value="${callServiceType.code }">${callServiceType.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>拨打来源：</td>
					<td>
						<select id="source" name="source" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="source" items="<%=cn.gdeng.nst.enums.CallSourceEnum.values() %>">
								<option value="${source.code }">${source.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>拨打电话时间：</td>
					<td>
						<input  type="text"  id="createTimeStart" name="createTimeStart"  onFocus="WdatePicker({onpicked:function(){createTimeStart.focus();},maxDate:'#F{$dp.$D(\'createTimeEnd\')}'})"    onClick="WdatePicker({onpicked:function(){createTimeStart.focus();},maxDate:'#F{$dp.$D(\'createTimeEnd\')}'})"    style="width:150px" placeholder="开始时间">
						~
						<input  type="text"   id="createTimeEnd" name="createTimeEnd"   onFocus="WdatePicker({onpicked:function(){createTimeEnd.focus();},minDate:'#F{$dp.$D(\'createTimeStart\')}'})"   onClick="WdatePicker({onpicked:function(){createTimeEnd.focus();},minDate:'#F{$dp.$D(\'createTimeStart\')}'})"   style="width:150px" placeholder="结束时间"> &nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: right">主叫姓名：</td>
					<td>
						<input type="text" id="e_name" name="e_name" placeholder="主叫人姓名"/>
					</td>
					<td style="text-align: right">主叫电话：</td>
					<td>
						<input type="text" id="e_mobile" name="e_mobile" placeholder="主叫人电话"/>
					</td>
					<td style="text-align: right">被叫姓名：</td>
					<td>
						<input type="text" id="s_name" name="s_name" placeholder="主叫人姓名"/>
					</td>
					<td style="text-align: right">被叫电话：</td>
					<td>
						<input type="text" id="s_mobile" name="s_mobile" placeholder="主叫人姓名"/>&nbsp;&nbsp;
						<gd:btn btncode="BDDHTJ001">
							<a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;
						</gd:btn>
						<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id='btn-reset'>重置</a>
					</td>
				</tr>
			</table>			
		</div>
		</form>
		
		<div style="background:#efefef;padding:5px 0 5px 0;height:25px;">
			<div style="float: left;font-size:14px;margin-left:5px;">拨打电话列表</div>
			<div style="float:right;margin-right:10px;">
				<gd:btn btncode="BDDHTJ002">
					<a class="easyui-linkbutton" iconCls="icon-save" id="exportData" onclick="exportData();">导出</a>&nbsp;&nbsp;
				</gd:btn>
			</div>
		</div>
		
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/callstatistics/main.js"></script>

</html>


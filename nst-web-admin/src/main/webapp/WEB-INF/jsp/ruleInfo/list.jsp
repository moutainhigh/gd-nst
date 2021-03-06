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
</head>
<body>
	<table id="ruleInfodg" title=""></table>
	<div id="ruleInfotb" style="padding:5px;height:auto">
		<form id="ruleInfoSearchForm" method="post">
		<div>
			<table>
				<tr>
					<td>规则名称：</td>
					<td><input type="text" id="name" name="name" maxlength="30" placeholder="请输入规则名" style="width:150px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>规则状态：</td>
					<td>
						<select id="onOff" name="onOff" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="onOff" items="<%=cn.gdeng.nst.enums.RuleInfoEnableEnum.values() %>">
								<option value="${onOff.code }">${onOff.name}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>货源类别:</td>
					<td><select id="sourceType" name="sourceType" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="sourceType" items="<%=cn.gdeng.nst.enums.SourceTypeEnum.values() %>">
								<option value="${sourceType.code }">${sourceType.name}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
				  <td align="right">货源所在地：</td>
					<td>
						<select name="provinceId" id="provinceId" style="width:100px">
							<option value="">请选择省份</option>
						</select>
						<select name="cityId" id="cityId" style="width:100px">
							<option value="">请选择城市</option>
						</select>
						<select name="areaId" id="areaId" style="width:100px">
							<option value="">请选择区/县</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				  <td colspan="3">
						<gd:btn btncode="WLGZGL001">
							<a class="easyui-linkbutton" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;
						</gd:btn>
						<a class="easyui-linkbutton" iconCls="icon-reload" id="btn-reset">重置</a>&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		</form>
		<div style="background:#efefef;padding:5px 0 5px 0;height:25px;">
			<div style="float: left;font-size:14px;margin-left:5px;">货源分配规则列表</div>
			<div style="float:right;margin-right:10px;">
				<gd:btn btncode="WLGZGL007">
					<a class="easyui-linkbutton" iconCls="icon-account-excel" id="btn-exportDetail">导出规则</a>&nbsp;&nbsp;
				</gd:btn>
				<gd:btn btncode="WLGZGL006">
				<a class="easyui-linkbutton" iconCls="icon-reload" onclick="onOffFuc()">启用/禁用</a>&nbsp;&nbsp;
					</gd:btn>
				<gd:btn btncode="WLGZGL002">
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="openAddDialog()">新增规则</a>&nbsp;&nbsp;
				</gd:btn>
				<gd:btn btncode="WLGZGL003">
					<a class="easyui-linkbutton" iconCls="icon-account-excel" id="btn-export">导出EXCEL</a>
				</gd:btn>
			</div>
		</div>
		<div id="addDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsAdd">
			<div id="dlg-buttonsAdd" style="text-align:center">
	        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="addRuleInfo">保存</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	        </div>
		</div>
		<div id="editDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsEdit">
			<div id="dlg-buttonsEdit" style="text-align:center">
	        	<gd:btn btncode="WLGZGL004">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="editRuleInfo">保存</a>
	            </gd:btn>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	        </div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/ruleInfo/main.js"></script>
<script type="text/javascript">
	function nameFmt(i,r,v){
		return "<gd:btn btncode='WLGZGL005'><a class='operate' href='javascript:editRuleInfo("+r.id+")'></gd:btn>"+i+"<gd:btn btncode='WLGZGL005'></a></gd:btn>";
	}
</script>
</html>


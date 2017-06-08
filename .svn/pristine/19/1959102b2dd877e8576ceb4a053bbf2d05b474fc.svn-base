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
	<table id="memberLinedg" title=""></table>
	<div id="memberLinetb" style="padding:5px;height:auto">
		<form id="SearchForm" method="post">
		<div>
			<table>
				<tr>
					<td>出发地：</td>
					<td>
						<select name="sProvinceId" id="sProvinceId">
							<option value="">请选择省份</option>
						</select>
						<select name="sCityId" id="sCityId" style="width:85px">
							<option value="">请选择城市</option>
						</select>
						<select name="sAreaId" id="sAreaId">
							<option value="">请选择区/县</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>目的地：</td>
					<td>
						<select name="eProvinceId" id="eProvinceId">
							<option value="">请选择省份</option>
						</select>
						<select name="eCityId" id="eCityId" style="width:85px">
							<option value="">请选择城市</option>
						</select>
						<select name="eAreaId" id="eAreaId">
							<option value="">请选择区/县</option>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>线路类型：</td>
					<td>
						<select id="lineType" name="lineType" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="lineType" items="<%=cn.gdeng.nst.enums.SourceTypeEnum.values() %>">
								<option value="${lineType.code }">${lineType.name}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>用户姓名：</td>
					<td><input type="text" id="publisher" name="publisher" placeholder="请输入发布人姓名" maxlength="30" style="width:295px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>用户手机：</td>
					<td><input type="text" id="phone" name="phone" placeholder="请输入发布人手机号码" maxlength="11" style="width:295px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>线路状态：</td>
					<td>
						<select id="isDeleted" name="isDeleted" style="width:150px">
							<option value="">全部</option>
							<c:forEach var="isDeleted" items="<%=cn.gdeng.nst.enums.IsDeletedEnum.values() %>">
								<option value="${isDeleted.code }">${isDeleted.name}</option>
							</c:forEach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						<gd:btn btncode="CPXL001">
							<a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;
						</gd:btn>
						<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
					</td>
				</tr>
			</table>
		</div>
		</form>
		<div style="background:#efefef;padding:5px 0 5px 0;height:25px;">
			<div style="float: left;font-size:14px;margin-left:5px;">常跑线路列表</div>
			<div style="float:right;margin-right:10px;">
				<gd:btn btncode="CPXL002">
					<a class="easyui-linkbutton icon-save-btn" iconCls="icon-account-excel" id="btn-export" onclick="exportData();">导出</a>&nbsp;&nbsp;
				</gd:btn>
			</div>
		</div>
		<div id="detailDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsDetail">
			<div id="dlg-buttonsDetail" style="text-align:center">
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detailDialog').dialog('close')">关闭</a>
	        </div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/memberLine/main.js"></script>
<script type="text/javascript">
	function sDetailFmt(i,r,v){
		return "<gd:btn btncode='CPXL003'><a class='operate' href='javascript:showDetail("+r.id+")'></gd:btn>"+i+"<gd:btn btncode='CPXL003'></a></gd:btn>";
	}
	
	function eDetailFmt(i,r,v){
		return "<gd:btn btncode='CPXL003'><a class='operate' href='javascript:showDetail("+r.id+")'> </gd:btn>"+i+" <gd:btn btncode='CPXL003'></a></gd:btn>"
	}
</script>
</html>


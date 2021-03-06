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
	<base href="${CONTEXT }">
	<title>demo</title>
</head>
<body>
	<table id="sourceShipperdg" title=""></table>
	<div id="sourceShippertb" style="height:auto;padding:0">
		<div style="overflow-x:auto">
			<div style="width:1100px;">
				<form id="sourceShipperSearchForm" method="post">
					<table>
						<tr>
							<td align="right">发货地：</td>
							<td>
								<select name="sProvinceId" id="sProvinceId" style="width:100px">
									<option value="">请选择省份</option>
								</select>
								<select name="sCityId" id="sCityId" style="width:100px">
									<option value="">请选择城市</option>
								</select>
								<select name="sAreaId" id="sAreaId" style="width:100px">
									<option value="">请选择区/县</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">目的地：</td>
							<td>
								<select name="eProvinceId" id="eProvinceId" style="width:100px">
									<option value="">请选择省份</option>
								</select>
								<select name="eCityId" id="eCityId" style="width:100px">
									<option value="">请选择城市</option>
								</select>
								<select name="eAreaId" id="eAreaId" style="width:100px">
									<option value="">请选择区/县</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">发布人姓名：</td>
							<td><input type="text" id="memberName" name="memberName" style="width:150px" maxlength="30" placeholder="请输入发布人姓名"></td>
						</tr>
						<tr>
							<td align="right">发布人手机：</td>
							<td><input type="text" id="memberMobile" name="memberMobile" class="easyui-validatebox" validtype="number" style="width:303px" maxlength="11" placeholder="请输入发布人手机号码">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right">货源类型：</td>
							<td>
								<select id="sourceGoodsType" name="sourceGoodsType" style="width:307px">
									<option value="">全部</option>
									<option value="1">自有</option>
									<option value="2">分配</option>
									<option value="3">指派</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">发货方式：</td>
							<td>
								<select id="sendGoodsType" name="sendGoodsType" style="width:154px">
									<option value="">全部</option>
									<c:forEach var="sendGoodsType" items="<%=cn.gdeng.nst.enums.SendGoodsTypeEnum.values() %>">
										<option value="${sendGoodsType.code }">${sendGoodsType.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">发布时间：</td>
							<td>
								<input  type="text" id="startDate" name="startDate"  
									onFocus="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:141px" placeholder="开始时间" readonly="readonly"> ~
								<input  type="text" id="endDate" name="endDate"   
									onFocus="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:142px" placeholder="结束时间" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">线路类型：</td>
							<td>
								<select id="sourceType" name="sourceType" style="width:307px">
									<option value="">全部</option>
									<c:forEach var="sourceType" items="<%=cn.gdeng.nst.enums.SourceTypeEnum.values() %>">
										<option value="${sourceType.code }">${sourceType.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">发布来源：</td>
							<td>
								<select id="clients" name="clients" style="width:154px">
									<option value="">全部</option>
									<c:forEach var="clients" items="<%=cn.gdeng.nst.enums.SourceClientsEnum.values() %>">
										<option value="${clients.code }">${clients.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>	
						<tr>
							<td align="right">货源状态：</td>
							<td>
								<select id="sourceStatus" name="sourceStatus" style="width:307px">
									<option value="">全部</option>
									<option value="1">已发布</option>
									<option value="3">已接单</option>
									<option value="4">已过期</option>
									<option value="5">已关闭</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">货物类型：</td>
							<td>
								<select id="goodsType" name="goodsType" style="width:307px">
									<option value="">全部</option>
									<c:forEach var="goodsType" items="<%=cn.gdeng.nst.enums.GoodsTypeEnum.values() %>">
										<option value="${goodsType.code }">${goodsType.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">货源ID：</td>
							<td><input type="text" id="id" name="id" style="width:150px" class="easyui-validatebox" validtype="number"  placeholder="请输入货源ID"></td>
						</tr>
						<tr>
						   	<td align="right">分配用户手机：</td>
							<td><input type="text" id="logisticMobile" name="logisticMobile" style="width:303px" class="easyui-validatebox" validtype="number" maxlength="11" placeholder="请输入公司/车主手机"></td>
							<td align="right">货源删除状态：</td>
							<td>
								<select id="isDeleted" name="isDeleted" style="width:307px">
									<option value="">全部</option>
									<option value="0">未删除</option>
									<option value="1">已删除</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="right">
								<gd:btn btncode="HYGL001"><a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;</gd:btn>
								<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div style="background:#efefef;line-height:35px;height:35px;">
			<div style="float: left;font-size:14px;">货源列表</div>
			<div style="float:right;margin-right:10px;">
				<gd:btn btncode="HYGL002"><a class="easyui-linkbutton icon-save-btn" iconCls="icon-account-excel" id="btn-export" onclick="exportData()">导出</a>&nbsp;&nbsp;</gd:btn>
			</div>
		</div>
	</div>
	
	<div id="detailDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsDetail">
		<div id="dlg-buttonsDetail" style="text-align:center">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detailDialog').dialog('close')">关闭</a>
        </div>
	</div>
	<div id="editDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsEdit">
		<div id="dlg-buttonsEdit" style="text-align:center">
        	<gd:btn btncode="HYGL003"><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveEdit()">保存</a></gd:btn>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
        </div>
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/sourceShipper/main.js"></script>
<script type="text/javascript">
function sDetailFormatter(val, row, index){
	var content = "";
	if(val != undefined){
		content += val;
	}
	return "<gd:btn btncode='HYGL004'><a class='operate' href='javascript:;' onclick=openEditDialog('"+row.id+"');></gd:btn>"+content+"<gd:btn btncode='HYGL004'></a></gd:btn>";
}

function eDetailFormatter(val, row, index){
	var content = "";
	if(val != undefined){
		content += val;
	}
	return "<gd:btn btncode='HYGL004'><a class='operate' href='javascript:;' onclick=openEditDialog('"+row.id+"');></gd:btn>"+content+"<gd:btn btncode='HYGL004'></a></gd:btn>";
}
</script>
</html>


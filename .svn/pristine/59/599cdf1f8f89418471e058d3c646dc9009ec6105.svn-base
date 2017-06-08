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
	<table id="sourceAssignHisDg" title=""></table>
	<div id="sourceAssignHisTb" style="height:auto;padding:0">
		<div style="overflow-x:auto">
			<div style="width:1100px;">
				<form id="sourceShipperSearchForm" method="post">
					<table>
						<tr>
							<td align="right">发货地：</td>
							<td>
								<select name="sProvinceId" id="sProvinceId" style="width:90px">
									<option value="">请选择省份</option>
								</select>
								<select name="sCityId" id="sCityId" style="width:90px">
									<option value="">请选择城市</option>
								</select>
								<select name="sAreaId" id="sAreaId" style="width:90px">
									<option value="">请选择区/县</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">目的地：</td>
							<td>
								<select name="eProvinceId" id="eProvinceId" style="width:90px">
									<option value="">请选择省份</option>
								</select>
								<select name="eCityId" id="eCityId" style="width:90px">
									<option value="">请选择城市</option>
								</select>
								<select name="eAreaId" id="eAreaId" style="width:90px">
									<option value="">请选择区/县</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">发布人姓名：</td>
							<td><input type="text" id="sourceMemberName" name="sourceMemberName" style="width:150px" maxlength="30" placeholder="请输入发布人姓名"></td>
						</tr>
						 <tr>
							<td align="right">发布人手机：</td>
							<td><input type="text" id="sourceMemberMobile" name="sourceMemberMobile" class="easyui-validatebox" validtype="number" style="width:273px" maxlength="11" placeholder="请输入发布人手机号码">&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right">货源ID：</td>
							<td><input type="text" id="sourceId" name="sourceId" style="width:273px" class="easyui-validatebox" validtype="number"  placeholder="请输入货源ID"></td>
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
							<td align="right">线路类型：</td>
							<td>
								<select id="sourceType" name="sourceType" style="width:277px">
									<option value="">全部</option>
									<c:forEach var="sourceType" items="<%=cn.gdeng.nst.enums.SourceTypeEnum.values() %>">
										<option value="${sourceType.code }">${sourceType.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">货物类型：</td>
							<td>
								<select id="goodsType" name="goodsType" style="width:277px">
									<option value="">全部</option>
									<c:forEach var="goodsType" items="<%=cn.gdeng.nst.enums.GoodsTypeEnum.values() %>">
										<option value="${goodsType.code }">${goodsType.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">货源类型：</td>
							<td>
								<select id="ruleType" name="ruleType" style="width:154px">
									<option value="">全部</option>
									<option value="1">分配</option>
									<option value="0">指派</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							
						</tr>	
						<tr>
						   	<td align="right">分配公司/车主手机：</td>
							<td><input type="text" id="assignMobile" name="assignMobile" style="width:273px" class="easyui-validatebox" validtype="number" maxlength="11" placeholder="请输入公司/车主手机"></td>
							<td align="right">分配处理状态：</td>
							<td>
								<select id="status" name="status" style="width:277px">
									<option value="">全部</option>
									<option value="2">接受</option>
									<option value="3">拒绝</option>
									<option value="4">超时</option>
								</select>
							</td>
							<td align="right">发布时间：</td>
							<td>
								<input  type="text" id="sourceStartDate" name="sourceStartDate"  
									onFocus="WdatePicker({onpicked:function(){sourceStartDate.focus();},maxDate:'#F{$dp.$D(\'sourceEndDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){sourceStartDate.focus();},maxDate:'#F{$dp.$D(\'sourceEndDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:65px" placeholder="开始时间" readonly="readonly"> ~
								<input  type="text" id="sourceEndDate" name="sourceEndDate"   
									onFocus="WdatePicker({onpicked:function(){sourceEndDate.focus();},minDate:'#F{$dp.$D(\'sourceStartDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){sourceEndDate.focus();},minDate:'#F{$dp.$D(\'sourceStartDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:65px" placeholder="结束时间" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							
					
							<td align="right">分配时间：</td>
							<td>
								<input  type="text" id="assignStartDate" name="assignStartDate"  
									onFocus="WdatePicker({onpicked:function(){assignStartDate.focus();},maxDate:'#F{$dp.$D(\'assignEndDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){assignStartDate.focus();},maxDate:'#F{$dp.$D(\'assignEndDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:126px" placeholder="开始时间" readonly="readonly"> ~
								<input  type="text" id="assignEndDate" name="assignEndDate"   
									onFocus="WdatePicker({onpicked:function(){assignEndDate.focus();},minDate:'#F{$dp.$D(\'assignStartDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){assignEndDate.focus();},minDate:'#F{$dp.$D(\'assignStartDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:127px" placeholder="结束时间" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td colspan="2">
								<gd:btn btncode="BTNFPJL001"><a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;</gd:btn>
								<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div style="background:#efefef;line-height:35px;height:35px;">
			<div style="float: left;font-size:14px;">货源分配列表</div>
			<div style="float:right;margin-right:10px;">
				<gd:btn btncode="BTNFPJL002"><a class="easyui-linkbutton icon-save-btn" iconCls="icon-account-excel" id="btn-export">导出</a>&nbsp;&nbsp;</gd:btn>
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
<script type="text/javascript" src="${CONTEXT}js/sourceAssignHis/main.js"></script>
</html>


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
	<table id="orderBeforeDG" title=""></table>
	<div id="orderBeforeTB" style="height:auto;padding:0">
		<div style="overflow-x:auto">
			<div style="width:1100px;">
				<form id="orderBeforeSearchForm" method="post">
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
								<select name="sAreaId" id="sAreaId" style="width:90px">
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
								<select name="eAreaId" id="eAreaId" style="width:90px">
									<option value="">请选择区/县</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">发布时间：</td>
							<td>
								<input  type="text" id="releaseStartDate" name="releaseStartDate"  
									onFocus="WdatePicker({onpicked:function(){releaseStartDate.focus();},maxDate:'#F{$dp.$D(\'releaseEndDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){releaseStartDate.focus();},maxDate:'#F{$dp.$D(\'releaseEndDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:72px" placeholder="开始时间" readonly="readonly"> ~
								<input  type="text" id="releaseEndDate" name="releaseEndDate"   
									onFocus="WdatePicker({onpicked:function(){releaseEndDate.focus();},minDate:'#F{$dp.$D(\'releaseStartDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){releaseEndDate.focus();},minDate:'#F{$dp.$D(\'releaseStartDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:71px" placeholder="结束时间" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right">货源ID：</td>
							<td>
								<input id="sourceId" name="sourceId" type="text" style="width:293px" placeholder="请输入货源ID">
							</td>
							<td align="right">发布人手机：</td>
							<td>
								<input id="shipperMobile" name="shipperMobile" type="text" class="easyui-validatebox" validtype="number" style="width:293px" maxlength="11" placeholder="请输入发布人手机号码">
							</td>
							<td align="right">发布人注册来源：</td>
							<td>
								<select id="regeType" name="regType" style="width:167px">
									<option value="">全部</option>
									<c:forEach var="regeType" items="<%=cn.gdeng.nst.enums.MemberInfoRegetypeEnum.values() %>">
										<option value="${regeType.code }">${regeType.name}</option>
									</c:forEach>
								</select>
							</td>
							
						</tr>
						<tr>
							<td align="right">线路类型：</td>
							<td>
								<select id="sourceType" name="sourceType" style="width:297px">
									<option value="">全部</option>
									<c:forEach var="sourceType" items="<%=cn.gdeng.nst.enums.SourceTypeEnum.values() %>">
										<option value="${sourceType.code }">${sourceType.name}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">信息订单号：</td>
							<td>
								<input id="orderAgentNo" name="orderAgentNo" type="text" style="width:293px" placeholder="请输入信息订单号">
							</td>
							<td align="right">货运订单号：</td>
							<td>
								<input id="orderInfoNo" name="orderInfoNo" type="text" style="width:163px" placeholder="请输入货运订单号">
							</td>
							
							
						</tr>
						<tr>
							<td align="right">信息订单状态：</td>
							<td>
								<select id="orderAgentStatus" name="orderAgentStatus" style="width:297px">
									<option value="">全部</option>
									<option value="1">待确认</option>
									<option value="2">已完成</option>
									<option value="3">已关闭</option>
								</select>
							</td>
							<td align="right">接单处理状态：</td>
							<td>
								<select id="sourceStatus" name="sourceStatus" style="width:297px">
									<option value="">全部</option>
									<option value="3">接受</option>
									<option value="4">拒绝</option>
									<option value="5">超时</option>
									<option value="6">取消</option>
								</select>	 
							</td>
							<td align="right">接单时间：</td>
							<td>
								<input type="text" id="acceptStartDate" name="acceptStartDate"  
									onFocus="WdatePicker({onpicked:function(){acceptStartDate.focus();},maxDate:'#F{$dp.$D(\'acceptEndDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){acceptStartDate.focus();},maxDate:'#F{$dp.$D(\'acceptEndDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:72px" placeholder="开始时间" readonly="readonly"> ~
								<input  type="text" id="acceptEndDate" name="acceptEndDate"   
									onFocus="WdatePicker({onpicked:function(){acceptEndDate.focus();},minDate:'#F{$dp.$D(\'acceptStartDate\')}',dateFmt:'yyyy-MM-dd'})"   
									onClick="WdatePicker({onpicked:function(){acceptEndDate.focus();},minDate:'#F{$dp.$D(\'acceptStartDate\')}',dateFmt:'yyyy-MM-dd'})" 
									style="width:71px" placeholder="结束时间" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6" align="right">
								<gd:btn btncode="JDJL001"><a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="btn-search">查询</a>&nbsp;&nbsp;</gd:btn>
								<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div style="background:#efefef;line-height:35px;height:35px;">
			<div style="float: left;font-size:14px;">接单信息列表</div>
			<div style="float:right;margin-right:10px;">
				<gd:btn btncode="JDJL002"><a class="easyui-linkbutton icon-save-btn" iconCls="icon-account-excel" id="btn-export" onclick="exportData()">导出</a>&nbsp;&nbsp;</gd:btn>
			</div>
		</div>
	</div>

	<div id="detailDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsDetail">
		<div id="dlg-buttonsDetail" style="text-align:center">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detailDialog').dialog('close')">关闭</a>
        </div>
	</div>
</body>
<script type="text/javascript" src="${CONTEXT}js/orderBefore/main.js"></script>
</html>


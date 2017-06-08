<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="orderBeforedg" title=""></table>
<script type="text/javascript">
$(document).ready(function(){
	var sourceId = $("#sourceId").val();
	loadOrderBeforeListData(sourceId);
});
function loadOrderBeforeListData(sourceId){
	$('#orderBeforedg').datagrid({
		url:CONTEXT+'sourceShipper/orderBeforeList/'+sourceId,
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:false,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'driverName',title:'司机姓名',width:100,align:'center'},
			{field:'driverMobile',title:'司机手机号码',width:100,align:'center'},
			{field:'createTime',title:'接单时间',width:100,align:'center'},
			{field:'nstRule',hidden:true},
			{field:'orderInfoConfirmOrderTime',hidden:true},
			{field:'orderAgentLogisticTime',hidden:true},
			{field:'sourceStatusStr',title:'接单处理状态',width:100,align:'center'},
			{field:'handleTime',title:'接单处理时间',width:100,align:'center',formatter:handleTimeFormatter},
		]]
	}); 
}
function handleTimeFormatter(val,row){
	if(row.sourceStatus == 1) {
		return "";
	}
	return val;
}
</script>
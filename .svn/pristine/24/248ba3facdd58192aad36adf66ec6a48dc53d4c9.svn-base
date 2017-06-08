<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../pub/tags.inc" %>

<div id="tb-edit" style="padding:20px;width:750px;height:350px;overflow-x: hidden !important; overflow-y: hidden !important;">
	<form id="updateForm" method="post" >
		<input type="hidden" name="id" value="${memberCarDTO.id}">
		<center>
			<table>
				<tr>
					<td align="right"><font color="red">*</font>车牌号：</td><td><input data-options="required:true"  style="width: 360px;" class="easyui-validatebox" maxlength="15" name="carNumber" id="carNumber" value="${memberCarDTO.carNumber}" /></td>
				</tr>
				<tr>
					<td align="right"><font color="red">*</font>车辆类型：</td><td>
						<input type="hidden" id="defaultCarType" value="${memberCarDTO.carType }">
						<input type="hidden" id="defaultCarTypeText" value="${memberCarDTO.carTypeStr }">
						<input id="carTypeBox" name="carType" value="" style="width:364px">
					</td>
				</tr>
				<tr>
					<td align="right"><font color="red">*</font>车长(米)：</td><td>
						<input id="carLengthBox" name="carLength" value="${memberCarDTO.carLength }" style="width:364px" class="easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td align="right"><font color="red">*</font>载重(吨)：</td>
					<td>
						<input name="load" id="load" style="width: 360px;" validtype="totalWeight" class="easyui-validatebox" required="true"     value="${memberCarDTO.load}">
					</td>
				</tr>
				<tr> 
					<td align="right">用户手机：</td><td>${memberCarDTO.mobile}</td>
				</tr>
				<tr>
					<td align="right" >用户姓名：</td><td>${memberCarDTO.userName} </td>
				</tr>
				<tr>
					<td align="right" border="1px solid #666">车辆照片：</td><td>
						 <a href="${memberCarDTO.vehicleUrl}" data-lightbox="vehicleUrl" width="330"  title="车牌号：${memberCarDTO.carNumber}<br>联系人：${memberCarDTO.userName} <br>联系人手机号：${memberCarDTO.mobile}">
						  	<img src="${memberCarDTO.vehicleUrl}" alt="" width="120" height="80"/>
						 </a>
					     <a href="${memberCarDTO.carHeadUrl}" data-lightbox="carHeadUrl"  width="330" title="车牌号：${memberCarDTO.carNumber}<br>联系人：${memberCarDTO.userName}<br>联系人手机号：${memberCarDTO.mobile}">
						  	<img src="${memberCarDTO.carHeadUrl}" alt=""    width="120" height="80"/>
						 </a>
					 	 <a href="${memberCarDTO.carRearUrl}" data-lightbox="carRearUrl"  width="330" title="车牌号：${memberCarDTO.carNumber}<br>联系人：${memberCarDTO.userName}<br>联系人手机号：${memberCarDTO.mobile}">
					  		<img src="${memberCarDTO.carRearUrl}" alt=""    width="120" height="80"/>
					 	 </a>
					</td>
				</tr>
			</table>
		</center>
	
		<div align="center" style="margin-top:30px">
	        <gd:btn btncode='MEMBERCARSAVE'>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="update()"> 保存 </a>
			</gd:btn>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
		</div>
	</form>
</div>	
<script type="text/javascript">
function initValidateRules(){
	$.extend($.fn.validatebox.defaults.rules, {
		totalWeight:{
			validator: function (value) {
				if(parseFloat(value) == 0){
					return false;
				}
	            return /^\d{1,3}(\.\d{1,2})?$/.test(value);
			},
	        message: '请输入正确的范围，可填范围0.01-999.99 ，不能为0'
		}
	});
}

$(function(){
	initValidateRules();	
}) 

function update() {
	if (!$('#updateForm').form('validate')) {
		return false;	
	}
	var url = CONTEXT + "memberCar/memberCarUpdate";
	jQuery.post(url, $('#updateForm').serialize(), function(data) {
		if (data.msg == "success") {
			slideMessage("修改成功！");
			// 刷新父页面列表
			$("#memberCardg").datagrid('reload');
			$('#win').dialog('close');
		} else {
			warningMessage(data.msg);
			return;
		}
	});
}

var carTypeData = [
	{"id": 1, "text": "面包车"},
	{"id": 2, "text": "金杯车"},
	{"id": 16, "text": "依维柯"},
	{"id": 10, "text": "平板车"},
	{"id": 8, "text": "厢式货车"},
	{"id": 7, "text": "敞车"},
	{"id": 9, "text": "高栏车"},
	{"id": 11, "text": "集装箱"},
	{"id": 12, "text": "保温车"},
	{"id": 13, "text": "冷藏车"},
	{"id": 14, "text": "鲜活水车"}
];
$('#carTypeBox').combobox({
	valueField : 'id',  
    textField : 'text', 
	required : false,
	editable : false,
	data: carTypeData
});
var defaultCarType = $("#defaultCarType").val();
var defaultCarTypeText = $("#defaultCarTypeText").val();
$('#carTypeBox').combobox('setValue',defaultCarType).combobox('setText', defaultCarTypeText);

var carLengthData = [
	{"id": 1.7, "text": "1.7"},
	{"id": 2.0, "text": "2.0"},
	{"id": 2.7, "text": "2.7"},
	{"id": 4.2, "text": "4.2"},
	{"id": 4.5, "text": "4.5"},
	{"id": 5.0, "text": "5.0"},
	{"id": 6.2, "text": "6.2"},
	{"id": 6.8, "text": "6.8"},
	{"id": 7.2, "text": "7.2"},
	{"id": 7.7, "text": "7.7"},
	{"id": 7.8, "text": "7.8"},
	{"id": 8.2, "text": "8.2"},
	{"id": 8.6, "text": "8.6"},
	{"id": 8.7, "text": "8.7"},
	{"id": 9.6, "text": "9.6"},
	{"id": 11.7, "text": "11.7"},
	{"id": 12.5, "text": "12.5"},
	{"id": 13.0, "text": "13.0"},
	{"id": 13.5, "text": "13.5"},
	{"id": 14.0, "text": "14.0"},
	{"id": 15.0, "text": "15.0"},
	{"id": 16.0, "text": "16.0"},
	{"id": 17.0, "text": "17.0"},
	{"id": 17.5, "text": "17.5"},
	{"id": 18.0, "text": "18.0"},
	{"id": 20.0, "text": "20.0"},
	{"id": -2, "text": "其他"}
];

$('#carLengthBox').combobox({
	valueField : 'id',  
    textField : 'text', 
	required : false,
	editable : false,
	data: carLengthData
});
</script>




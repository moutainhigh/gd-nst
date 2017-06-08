<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
<!--
#editSourceShipperForm table td{word-break: keep-all;white-space:nowrap;}
-->
</style>
<div style="margin: 10px">
	<input type="hidden" value="${sourceShipperDTO.SProvinceId }" id="pre_sProvinceId"/>
	<input type="hidden" value="${sourceShipperDTO.SCityId }" id="pre_sCityId"/>
	<input type="hidden" value="${sourceShipperDTO.SAreaId }" id="pre_sAreaId"/>
	<input type="hidden" value="${sourceShipperDTO.EProvinceId }" id="pre_eProvinceId"/>
	<input type="hidden" value="${sourceShipperDTO.ECityId }" id="pre_eCityId"/>
	<input type="hidden" value="${sourceShipperDTO.EAreaId }" id="pre_eAreaId"/>
	<form action="" id="editSourceShipperForm">
		<input type="hidden" name="id" value="${sourceShipperDTO.id }">
		<input type="hidden" name="version" value="${sourceShipperDTO.version }">
		<table style="margin-left:50px;">
			<tr>
				<td colspan="4">货运要求<hr></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>发货地：</td>
				<td colspan="3">
					<input type="hidden" id="sDetail" name="sDetail" value="${sourceShipperDTO.SDetail }">
					<select name="sProvinceId" id="sProvinceId" class="easyui-validatebox" required="required" style="width:100px">
						<option value="">请选择省份</option>
					</select>
					<select name="sCityId" id="sCityId" class="easyui-validatebox" required="required" style="width:100px">
						<option value="">请选择城市</option>
					</select>
					<select name="sAreaId" id="sAreaId" style="width:100px">
						<option value="">请选择区/县</option>
					</select>
					<input type="text" id="sDetailedAddress" name="sDetailedAddress" value="${sourceShipperDTO.SDetailedAddress }" style="width:195px">
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>目的地：</td>
				<td  colspan="3">
					<input type="hidden" id="eDetail" name="eDetail" value="${sourceShipperDTO.EDetail }">
					<select name="eProvinceId" id="eProvinceId" class="easyui-validatebox" required="required" style="width:100px">
						<option value="">请选择省份</option>
					</select>
					<select name="eCityId" id="eCityId" class="easyui-validatebox" required="required" style="width:100px">
						<option value="">请选择城市</option>
					</select>
					<select name="eAreaId" id="eAreaId" style="width:100px">
						<option value="">请选择区/县</option>
					</select>
					<input type="text" id="eDetailedAddress" name="eDetailedAddress" value="${sourceShipperDTO.EDetailedAddress }" style="width:195px">
				</td>
			</tr>
			<tr>
				<td align="right">装货时间：</td>
				<td colspan="3">
					<input type="text" id="sendDate"
						onFocus="WdatePicker({onpicked:function(){sendDate.focus();},minDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
						onClick="WdatePicker({onpicked:function(){sendDate.focus();},minDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						name="sendDate" style="width: 195px;" class="easyui-validatebox" readonly="readonly"
						value='<fmt:formatDate value="${sourceShipperDTO.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
					<font color="red">（不填则为不限时间）</font>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>车辆类型：</td>
				<td>
					<select name="carType" id="carType" class="easyui-validatebox" required="required" style="width:200px">
						<option value="">请选择车辆类型</option>
						<c:forEach var="carType" items="<%=cn.gdeng.nst.enums.CarTypeEnum.values() %>">
							<option value="${carType.code }" ${sourceShipperDTO.carType == carType.code ? "selected" : ""}>${carType.name}</option>
						</c:forEach>
					</select>
				</td>
				<td align="right">车长:</td>
				<td>
					<input id="carLengthBox" name="carLength" value="${sourceShipperDTO.carLength }" style="width:200px">
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>发货方式：</td>
				<td>
					<select name="sendGoodsType" id="sendGoodsType" class="easyui-validatebox" required="required" style="width:200px">
						<option value="">请选择发货方式</option>
						<c:forEach var="sendGoodsType" items="<%=cn.gdeng.nst.enums.SendGoodsTypeEnum.values() %>">
							<option value="${sendGoodsType.code }" ${sourceShipperDTO.sendGoodsType == sendGoodsType.code ? "selected" : ""}>${sendGoodsType.name}</option>
						</c:forEach>
					</select>
				</td>
				<td align="right">意向运费：</td>
				<td>
					<c:choose>
						<c:when test="${sourceShipperDTO.freight > 0}">
						<input type="text" name="freight" id="freight" validtype="freight" maxlength="5" class="easyui-validatebox" value="${sourceShipperDTO.freight }" style="width:196px"/>
						</c:when>
						<c:otherwise>
						<input type="text" name="freight" id="freight" validtype="freight" maxlength="5" class="easyui-validatebox" value="" style="width:196px"/>
						</c:otherwise>
					</c:choose>
					<font color="red">（不填则为面议）</font>
				</td>
			</tr>
			
			<tr><td colspan="4"><br>货物信息<hr></td>
			<tr>
				<td align="right">货物名称：</td>
				<td><input type="text" name="goodsName" id="goodsName" maxlength="20" style="width:200px" value="${sourceShipperDTO.goodsName }"/></td>
				<td align="right"><font color="red">*</font>货物类型：</td>
				<td>
					<select name="goodsType" id="goodsType" style="width:200px">
						<option value="">请选择货物类型</option>
						<c:forEach var="goodsType" items="<%=cn.gdeng.nst.enums.GoodsTypeEnum.values() %>">
							<option value="${goodsType.code }" ${sourceShipperDTO.goodsType == goodsType.code ? "selected" : ""}>${goodsType.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">货物重量：</td>
				<td><input type="text" name="totalWeight" id="totalWeight" validtype="totalWeight" class="easyui-validatebox"  value="${sourceShipperDTO.totalWeight }" style="width:200px"/>&nbsp;&nbsp;吨</td>
				<td align="right">货物体积：</td>
				<td><input type="text" name="totalSize" id="totalSize" validtype="number" maxlength="3" class="easyui-validatebox" value="${sourceShipperDTO.totalSize }" style="width:195px"/>&nbsp;&nbsp;立方米</td>
			</tr>
			<tr><td colspan="4"><br>联系人信息<hr></td></tr>
			<tr>
				<td align="right"><font color="red">*</font>手机号码：</td>
				<td><input type="text" name="shipperMobile" id="shipperMobile" value="${sourceShipperDTO.memberMobile }" maxlength="11" validtype="number" class="easyui-validatebox" required="required" style="width:200px"/></td>
				<td align="right"><font color="red">*</font>发布人姓名：</td>
				<td><input type="text" name="shipperName" id="shipperName" value="${sourceShipperDTO.memberName }" maxlength="30" class="easyui-validatebox" required="required" style="width:195px"/></td>
			</tr>
			<tr>
				<td align="right">货主留言：</td>
				<td colspan="3">
					<textarea name="remark" rows="5" cols="" style="width:503px" maxlength="50">${sourceShipperDTO.remark }</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" src="${CONTEXT}js/sourceShipper/edit.js"></script>
<script>
var data = [
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
	data: data
});
var carlength = '${sourceShipperDTO.carLength }';
var carlengthStr = "其他"
if(carlength > 0){
	carlengthStr = carlength;
}
$('#carLengthBox').combo('setValue', carlength).combo('setText', carlengthStr)
</script>
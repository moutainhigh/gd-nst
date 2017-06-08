<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../pub/tags.inc" %>
<div id="tb-edit" class="easyui-tabs" style="width:780px;height:360px;overflow-x: hidden !important; overflow-y: hidden !important;">
<div title="会员信息" style="overflow-y: hidden !important;">	
	<form id="updateForm" method="post"  data-ajax='false'>
	<input type="hidden" name="id" value="${memberInfoDTO.id}">
	<input type="hidden" name="memberId" value="${memberInfoDTO.id}">
	<input type="hidden" name="province" id="province" value="${province}">
	<input type="hidden" name="city"     id="city" value="${city}">
	<input type="hidden" name="area"     id="area"  value="${area}">
	<input type="hidden"     id="appointFlag"  value="${memberInfoDTO.appoint}">
	<div style="height: 290px;">
	<table>
	<tr>
	<td align="right" >账号：</td><td>  ${memberInfoDTO.account}</td>
	<td></td><td></td>
	</tr>
	<tr>
	<td align="right" >账号状态：</td><td>
	<c:if test="${memberInfoDTO.status==1}">启用</c:if>
    <c:if test="${memberInfoDTO.status==0}">未启用</c:if>
	</td>
	<td align="right" >注册时间：</td><td>${createTime}</td>
	</tr>
	<tr>
	<td align="right">货源是否指派：</td>
	<td>
	<c:if test="${memberInfoDTO.appoint==0}">
	<input type="radio" class="appoint" name="appoint" value="0" checked="checked" >是
	<input type="radio" class="appoint" name="appoint" value="1" >否
	</c:if>
	<c:if test="${memberInfoDTO.appoint==1||memberInfoDTO.appoint==null}">
	<input type="radio" class="appoint" name="appoint" value="0"  >是
	<input type="radio" class="appoint" name="appoint" value="1" checked="checked">否
	</c:if>
	</td>
	</tr>
	<tr>
	<td align="right">指派的公司/车主：</td>
	<td>
	<input name="memberIdLogisticName"   class="easyui-validatebox"  id="memberIdLogisticName"   readonly   value="${memberInfoDTO.memberIdLogisticName}" />
	<input  id="memberIdLogistic"  name="memberIdLogistic" value="${memberInfoDTO.memberIdLogistic}" type="hidden" />
	
	<input id="addCompanyAdd"  type="button" value="添加" style="display:none;"/>

	</td>
	<td align="right">指派的用户类型：</td>
	<td>
	 <select name="assignUserType" id="assignUserType" style="width:150px;height:20px;" <c:if test='${memberInfoDTO.appoint==0}'> disabled='disabled'</c:if>> 
			<c:if test="${memberInfoDTO.memberType==null}">
			   <option value="" selected="selected">请选择</option>
			   <option value="3">物流公司</option>
		       <option value="2" >车主</option>
			</c:if>
			<c:if test="${memberInfoDTO.memberType==3}">
			   <option value="">请选择</option>
		       <option value="3"  selected="selected">物流公司</option>
		       <option value="2" >车主</option>
			</c:if>
			<c:if test="${memberInfoDTO.memberType==2}">
			  <option value="">请选择</option>
		      <option value="2"  selected="selected">车主</option>
		      <option value="3" >物流公司</option>
			</c:if>
	</select>
	</td>
	</tr>
	<tr>
	<td align="right">个人认证状态：</td><td>
	<c:if test="${memberInfoDTO.cerPersonalStatus==0}">待认证</c:if>
	<c:if test="${memberInfoDTO.cerPersonalStatus==1}">认证中</c:if>
	<c:if test="${memberInfoDTO.cerPersonalStatus==2}">已认证</c:if> 
	<c:if test="${memberInfoDTO.cerPersonalStatus==3}">已驳回</c:if>
	</td>
	<td align="right">企业认证状态：</td><td>
	<c:if test="${memberInfoDTO.cerCompanyStatus==0}">待认证</c:if>
	<c:if test="${memberInfoDTO.cerCompanyStatus==1}">认证中</c:if>
	<c:if test="${memberInfoDTO.cerCompanyStatus==2}">已认证</c:if> 
	<c:if test="${memberInfoDTO.cerCompanyStatus==3}">已驳回</c:if>
	 </td>
	</tr>
    <tr>
	<td align="right">认证姓名：</td><td> ${memberInfoDTO.realName}</td>
	<td align="right">用户来源：</td><td>
	 ${memberInfoDTO.regetypeStr}
	</td>
	</tr>
   <tr>
	<td align="right">联系人姓名:</td><td><input data-options="required:true" class="easyui-validatebox" maxlength="10" id="userName" name="userName"   value="${memberInfoDTO.userName}"/></td>
	<td align="right">手机：</td><td><input data-options="required:true"  validtype="userMobile" class="easyui-validatebox" id="mobile"  name="mobile"  value="${memberInfoDTO.mobile}"/></td>
	</tr>
	<c:if test="${memberInfoDTO.cerCompanyStatus==2}">
   <tr>
	<td align="right">所在城市:</td><td colspan="3">
		    <input   class="easyui-combobox"  id="originProvince_comp" /> 
			<input   class="easyui-combobox"  id="originCity_comp"  />
			<input   class="easyui-combobox"  id="originArea_comp"  />
			<input type="hidden" id="originProvince_typeIn" name="provinceId" value="${memberInfoDTO.provinceId}">
			<input type="hidden" id="originCity_typeIn" name="cityId" value="${memberInfoDTO.cityId}">
			<input type="hidden" id="originArea_typeIn" name="areaId" value="${memberInfoDTO.areaId}">
	</td>
	</tr>
	</c:if>
	<tr><td align="right">会员头像:</td>
	<td>
	<a href="${memberInfoDTO.iconUrl}" data-lightbox="iconUrl" title="">
	  <img src="${memberInfoDTO.iconUrl}" alt="" width="120" height="80"/>
	 </a>
	</td>
	<td align="right">业务类型:</td>
	<td>
	<c:if test="${memberInfoDTO.serviceType==1}">干线业务</c:if>
    <c:if test="${memberInfoDTO.serviceType==2}">同城业务</c:if>
	</td>
	</tr>
	
	</table>
</div>
<div align="center">
		<gd:btn btncode='MEMBERINFOSAVE'>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" > 保存 </a>
		</gd:btn>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</form>
</div>

<div title="货源信息" style="overflow-x: hidden !important; overflow-y: auto !important;" >
<div style="height: 290px;overflow-x: hidden !important; overflow-y: auto !important;">
	<table id="sourceShipperdg" title="" >
	</table>
</div>
	<div align="center" >
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</div>

<div title="车辆信息" style="overflow-x: hidden !important; overflow-y: auto !important;" >
<div style="height: 290px;overflow-x: hidden !important; overflow-y: auto !important;">
	<table id="carDataGrid" title="" >
	</table>
</div>
	<div align="center" >
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</div>
<div title="常跑线路" style="overflow-x: hidden !important; overflow-y: auto !important;" >
<div style="height: 290px;overflow-x: hidden !important; overflow-y: auto !important;">
	<table id="memberLineDataGrid" title="" >
	</table>
</div>
	<div align="center" >
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</div>
<div title="货运订单" style="overflow-x: hidden !important; overflow-y: auto !important;" >
<div style="height: 290px;overflow-x: hidden !important; overflow-y: auto !important;">
	<table id="orderInfoDataGrid" title="" >
	</table>
</div>
	<div align="center" >
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</div>
<div title="信息订单" style="overflow-x: hidden !important; overflow-y: auto !important;" >
<div style="height: 290px;overflow-x: hidden !important; overflow-y: auto !important;">
	<table id="orderAgentDataGrid" title="" >
	</table>
</div>
	<div align="center" >
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</div>

<div title="物流规则" style="overflow-x: hidden !important; overflow-y: auto !important;" >
<div style="height: 290px;overflow-x: hidden !important; overflow-y: auto !important;">
	<table id="ruleInfoDataGrid" title="" >
	</table>
</div>
	<div align="center" >
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#win').dialog('close')">关闭</a>
	</div>
</div>
</div>	


<div id="companyDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-companyDialog">
	<div id="dlg-companyDialog" style="text-align:center">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="memberSelectCompany">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#companyDialog').dialog('close')">关闭</a>
    </div>
</div>

<script type="text/javascript">
var memberId=${memberInfoDTO.id};
</script>

<script type="text/javascript" src="${CONTEXT}../js/memberInfo/memberInfoDetail.js"></script>
 




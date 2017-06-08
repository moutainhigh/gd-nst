<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div  id="edit"  style="margin: 10px auto;width:80%">
	<form action="" name="" id="editForm">
		<input type="hidden" id="id" name="id" value="${dto.id}"/>
	<input type="hidden" name="province" id="province" value="${dto.province}">
	<input type="hidden" name="city"     id="city" value="${dto.city}">
		<table>
				<tr>
				<td>公告名称：</td>
				<td><input placeholder="请输入公告名称" data-options="required:true" class="easyui-validatebox" maxlength="10" id="title" name="title"   value="${dto.title}"/>
				<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>所属渠道：</td>
				<td>
				<select  name="channel" id="channel"  style="width: 305px;" >
				<option value="1" <c:if test="${dto.channel==1}">selected="selected"</c:if>>车主APP</option>
				<option value="2" <c:if test="${dto.channel==2}">selected="selected"</c:if>>货主APP</option>
				<option value="3" <c:if test="${dto.channel==3}">selected="selected"</c:if>>物流公司APP</option>
				</select>
				<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>所在城市：</td>
				<td>
		    <input   class="easyui-combobox"  id="originProvince_comp" /> 
			<input   class="easyui-combobox"  id="originCity_comp"  />
			<input   type="hidden" id="originProvince_typeIn" name="provinceId" value="${dto.provinceId}">
			<input   type="hidden" id="originCity_typeIn" name="cityId" value="${dto.cityId}">
			   </td>
			</tr>
			<tr>
				<td>公告：</td>
				<td>
		<textarea rows="10" cols="28" style="width: 298px;" name="content" maxlength="200" id="content">${dto.content}</textarea><font color="red">*</font>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" src="${CONTEXT}../js/notice/editNstNotice.js"></script>
 


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div  id="add"   style="margin: 10px auto;width:80%">
	<form action="" name="" id="addForm">
	<input type="hidden" name="province" id="province" value="">
	<input type="hidden" name="city"     id="city" value="">
		<table>
				<tr>
				<td>公告名称：</td>
				<td><input placeholder="请输入公告名称" data-options="required:true" class="easyui-validatebox" maxlength="10" id="title" name="title"   value=""/><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>所属渠道：</td>
				<td>
				<select  name="channel" id="channel"  style="width: 305px;" >
				<option value="0" selected="selected">请选择渠道</option>
				<option value="1">车主APP</option>
				<option value="2">货主APP</option>
				<option value="3">物流公司APP</option>
				</select>
				<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>所在城市：</td>
				<td>
		    <input   class="easyui-combobox"  id="originProvince_comp" /> 
			<input   class="easyui-combobox"  id="originCity_comp"  />
			<input   type="hidden" id="originProvince_typeIn" name="provinceId" value="">
			<input   type="hidden" id="originCity_typeIn" name="cityId" value="">
			   </td>
			</tr>
			<tr>
				<td>公告：</td>
				<td>
					<textarea rows="10" cols="28" style="width: 298px;" name="content" maxlength="200" id="content"></textarea><font color="red">*</font>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" src="${CONTEXT}../js/notice/addNstNotice.js"></script>
 

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="margin: 10px auto;width:80%">
	<form action="" id="addRuleInfoForm">
		<table>
			<tr>
				<td style="text-align:right">规则名称：</td>
				<td><input type="text" id="name" name="name" style="width:153px" class="easyui-validatebox" validType="length[1,30]" ONBLUR='validayName(this)' required="true"/>&nbsp;
				<font color="red">*</font>&nbsp;&nbsp;<span id="validateName" style="color:red;"></span></td>
			</tr>
			<tr>
				<td style="text-align:right">货源类别：</td>
				<td>
					<input type="radio" name="sourceType" value="1" checked="checked" />干线
					<input type="radio" name="sourceType" value="2" />同城 
				</td>
			</tr>
			<tr>
				<td style="text-align:right">所在城市：</td>
				<td>
					<select name="provinceId" id="provinceIds" style="width:100px">
							<option value="">请选择省份</option>
						</select>
						<select name="cityId" id="cityIds" style="width:100px">
							<option value="">请选择城市</option>
						</select>
					<select name="areaId" id="areaIds">
						<option value="">请选择区/县</option>
					</select>&nbsp;
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="text-align:right">其他通用默认规则：</td>
				<td>
					1、每个货源分配给3个公司/车主
				</td>
			</tr>
			<tr>
				<td></td>
				<td>2、货源在公司/车主最多停留15分钟</td>
			</tr>
			<tr>
				<td></td>
				<td>3、每次轮询分配1条货源，同一个货源不能分配给同一个公司/车主两次</td>
			</tr>
			<tr>
				<td></td>
				<td>4、在同等优先级条件下，按完成货运订单量由多至少、注册时间由早到迟优先分配</td>
			</tr>
		</table>
	</form>
		<div style="position:absolute; height:250px; width:720px;overflow-x:auto;">
			<table id="showCompanyAdd">
				<tr>
					<th colspan="4">使用以上规则的公司/车主</th>
					<th colspan="5" style="text-align: right"><button onclick="addCompanyAdd();" >添加公司/车主</button></th>
				</tr>
				<tr>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;" >序号</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px; white-space:nowrap;">公司/车主</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">电话</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">用户类型</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">优先级</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">总分配上限</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">日分配上限</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">分配开始时间</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">分配结束时间</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">已使用日配额</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">已使用总配额</th>
					<th style="text-align: center; word-break:keep-all;padding:0 5px;">操作</th>
				</tr>
			</table>
		</div>

	<div id="companyDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-companyDialog">
		<div id="dlg-companyDialog" style="text-align:center">
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="selectCompany">确定</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#companyDialog').dialog('close')">关闭</a>
        </div>
	</div>
</div>
<script type="text/javascript" src="${CONTEXT}../js/ruleInfo/add.js"></script>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="container" style="width:300px;margin:0 auto">
	<form action="advertisement/saveSync" id="syncForm" method="post">
		<input type="hidden" name="cityId" value="${syncCityId }">
		<table style="padding:20px">
			<tr>
				<td><font color="red">*</font>同步渠道：</td>
				<td>
					<select name="channel" id="syncChannelSelect">
						<option value="">--请选择--</option>
						<option value="1">车主APP</option>
						<option value="2">货主APP</option>
						<option value="3">物流公司APP</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><font color="red">*</font>同步广告：</td>
				<td><label><input type="checkbox" id="selectAllBtn"/>全选</label></td>
			</tr>
			<tr>
				<td></td>
				<td id="syncAdList"></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript" src="${CONTEXT }js/advertisement/sync.js"></script>


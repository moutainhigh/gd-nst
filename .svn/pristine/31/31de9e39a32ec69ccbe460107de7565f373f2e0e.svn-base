<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style="margin: 10px auto;width:80%">
	<table>
		<tr>
			<td align="right">广告名称：</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td align="right">广告渠道：</td>
			<td>${dto.channelStr }</td>
		</tr>
		<tr>
			<td align="right">开始时间：</td>
			<td><fmt:formatDate value="${dto.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td align="right">结束时间：</td>
			<td><fmt:formatDate value="${dto.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td align="right">状态：</td>
			<td>${dto.statusStr }</td>
		</tr>
		<tr>
			<td align="right">排序：</td>
			<td>${dto.sort }</td>
		</tr>
		<tr>
			<td align="right">广告图片：</td>
			<td><a href="${dto.imgUrl }" target="_blank"><img src="${dto.imgUrl }" title="图片" width="150px" height="150px"/></a></td>
		</tr>
		<tr>
			<td align="right">创建时间：</td>
			<td><fmt:formatDate value="${dto.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	</table>
</div>

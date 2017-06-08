<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../pub/tags.inc" %>
<script type="text/javascript" src="${CONTEXT}js/My97DatePicker/WdatePicker.js"></script> 
<div style="margin: 10px">
	<table>
		<tr style="margin-left:30px;">
			<td align="right">线路状态：</td>
			<td style="min-width:100px">
				<c:if test="${dto.isDeleted=='0'}">已发布</c:if>
				<c:if test="${dto.isDeleted=='1'}">已删除</c:if>
			</td>
			<td align="right">发布时间：</td>
			<td style="min-width:100px">
				<fmt:formatDate value="${dto.createTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> 
			</td>
		</tr>
		<tr>
			<td align="right">发布人：</td>
			<td style="min-width:100px">${dto.publisher}</td>
			<td align="right">发布人手机：</td>
			<td style="min-width:100px">${dto.phone}</td>
		</tr>
		<tr>
			<td align="right">始发地：</td>
			<td colspan="2">${dto.s_detail}</td>
		</tr>
		<tr>
			<td align="right">目的地：</td>
			<td colspan="2">${dto.e_detail}</td>
		</tr>
	</table>
</div>

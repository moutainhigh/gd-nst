<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin: 10px">
	
	<table style="margin-left:50px;">
		<tr>
			<td colspan="4" style="position:relative;left:-30px">货运要求<hr></td>
		</tr>
		<tr>
			<td>发货地：</td>
			<td colspan="3">
				<input type="text" value="${sourceShipperDTO.SDetailStr}&nbsp;${sourceShipperDTO.SDetailedAddress}" readonly="readonly" style="width:395px">
			</td>
		</tr>
		<tr>
			<td>目的地：</td>
			<td colspan="3">
				<input type="text" value="${sourceShipperDTO.EDetailStr }&nbsp;${sourceShipperDTO.EDetailedAddress}" readonly="readonly" style="width:395px">
			</td>
		</tr>
		<tr>
			<td>装货时间：</td>
			<td>
				<c:choose>
					<c:when test="${not empty sourceShipperDTO.sendDate }">
					<input type="text" value='<fmt:formatDate value="${sourceShipperDTO.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly"/>
					</c:when>
					<c:otherwise>
					<input type="text" value='不限时间' readonly="readonly"/>
					</c:otherwise>
				</c:choose>
				
			</td>
		</tr>
		<tr>
			<td>车辆类型：</td>
			<td>
				<input type="text" value="${sourceShipperDTO.carTypeStr }" readonly="readonly"/> 
			</td>
			<td>车长:</td>
			<td>
				<c:choose>
					<c:when test="${sourceShipperDTO.carLength > 0}">
						<input type="text" value="${sourceShipperDTO.carLength }" readonly="readonly"/> 
					</c:when>
					<c:otherwise>
						<input type="text" value="其他" readonly="readonly"/> 
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>发货方式：</td>
			<td>
				<input type="text" value="${sourceShipperDTO.sendGoodsTypeStr }" readonly="readonly"/> 
			</td>
			<td>意向运费：</td>
			<td>
				<c:choose>
					<c:when test="${sourceShipperDTO.freight > 0 }">
						<input type="text" value="${sourceShipperDTO.freight }" readonly="readonly"/>
					</c:when>
					<c:otherwise>
						<input type="text" value="面议" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		
		<tr><td colspan="4"  style="position:relative;left:-30px"><br>货物信息<hr></td>
		<tr>
			<td>货物名称：</td>
			<td><input type="text" value="${sourceShipperDTO.goodsName }" readonly="readonly"></td>
	
			<td>货物类型：</td>
			<td><input type="text" value="${sourceShipperDTO.goodsTypeStr }" readonly="readonly"></td>
		</tr>
		<tr>
			<td>货物重量：</td>
			<td><input type="text" value="${sourceShipperDTO.totalWeight }" readonly="readonly"/>&nbsp;&nbsp;吨</td>
			<td>货物体积：</td>
			<td><input type="text" value="${sourceShipperDTO.totalSize }" readonly="readonly"/>&nbsp;&nbsp;立方米</td>
		</tr>
		
		<tr><td colspan="4"  style="position:relative;left:-30px"><br>联系人信息<hr></td></tr>
		<tr>
			<td>手机号码：</td>
			<td><input type="text" value="${sourceShipperDTO.memberMobile }" readonly="readonly"/></td>
			<td>发布人姓名：</td>
			<td><input type="text" value="${sourceShipperDTO.memberName }" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>货主留言：</td>
			<td colspan="3">
				<textarea rows="5" cols="" style="width:395px" readonly="readonly">${sourceShipperDTO.remark }</textarea>
			</td>
		</tr>
	</table>
</div>
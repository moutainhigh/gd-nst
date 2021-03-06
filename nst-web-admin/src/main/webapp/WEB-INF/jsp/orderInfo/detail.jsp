<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
<!--
 #detailTb td{max-width:200px;word-wrap: break-word;word-break:break-all;} 
-->
</style>
<div style="margin: 10px">
	<table id="detailTb" style="margin-left:50px;width:94%">
		<tr>
			<td colspan="4" style="position:relative;left:-30px">订单信息<hr></td>
		</tr>
		<tr>
			<td align="right">货运订单号：</td>
			<td style="min-width:200px">${orderInfoDTO.orderNo }</td>
			<td align="right">订单状态：</td>
			<td style="min-width:200px">${orderInfoDTO.orderStatusStr }</td>
		</tr>
		<tr>
			<td align="right">订单类型：</td>
			<td>${orderInfoDTO.sourceTypeStr }</td>
			<td align="right">订单金额：</td>
			<td>${orderInfoDTO.transportAmt }</td>
		</tr>
		<tr>
			<td align="right">车主接单时间：</td>
			<td><fmt:formatDate value="${orderInfoDTO.acceptTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="right">订单生成时间：</td>
			<td><fmt:formatDate value="${orderInfoDTO.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td align="right">货主确认收货时间：</td>
			<td><fmt:formatDate value="${orderInfoDTO.confirmGoodsTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">货主姓名：</td>
			<td>${orderInfoDTO.shipperName }</td>
			<td align="right">货主手机：</td>
			<td>${orderInfoDTO.shipperMobile }</td>
		</tr>
		<tr>
			<td align="right">物流公司名称：</td>
			<td>${orderInfoDTO.logisticName }</td>
			<td align="right">物流公司手机：</td>
			<td>${orderInfoDTO.logisticMobile }</td>
		</tr>
		<tr>
			<td align="right">车主姓名：</td>
			<td>${orderInfoDTO.driverName }</td>
			<td align="right">车主手机：</td>
			<td>${orderInfoDTO.driverMobile }</td>
		</tr>
		
		<tr>
			<td colspan="4" style="position:relative;left:-30px"><br>货运信息<hr></td>
		</tr>
		<tr>
			<td align="right">发货地：</td>
			<td colspan="3">${orderInfoDTO.SDetailStr }${orderInfoDTO.SDetailedAddress }</td>
		</tr>
		<tr>
			<td align="right">目的地：</td>
			<td colspan="3">${orderInfoDTO.EDetailStr }${orderInfoDTO.EDetailedAddress }</td>
		</tr>
		<tr>
			<td align="right">装车时间：</td>
			<td colspan="3"><fmt:formatDate value="${orderInfoDTO.sendDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td align="right">车辆类型：</td>
			<td>${orderInfoDTO.carTypeStr }</td>
			<td align="right">车长：</td>
			<td>
				<c:choose>
					<c:when test="${orderInfoDTO.carLength > 0}">${orderInfoDTO.carLength }米</c:when>
					<c:otherwise>其他</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">发货方式：</td>
			<td>${orderInfoDTO.sendGoodsTypeStr }</td>
			<td align="right">意向价格：</td>
			<td>
				<c:choose>
					<c:when test="${orderInfoDTO.freight > 0 }">${orderInfoDTO.freight }</c:when>
					<c:otherwise>面议</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">货主留言：</td>
			<td colspan="3">${orderInfoDTO.remark }</td>
		</tr>
		
		<tr>
			<td colspan="4" style="position:relative;left:-30px"><br>货物信息<hr></td>
		</tr>
		<tr>
			<td align="right">货物名称：</td>
			<td colspan="3">${orderInfoDTO.goodsName }</td>
		</tr>
		<tr>
			<td align="right">货物类型：</td>
			<td colspan="3">${orderInfoDTO.goodsTypeStr }</td>
		</tr>
		<tr>
			<td align="right">货物重量：</td>
			<td>${orderInfoDTO.totalWeight }吨</td>
			<td align="right">货物体积：</td>
			<td><c:if test="${not empty orderInfoDTO.totalSize}">${orderInfoDTO.totalSize }立方米</c:if></td>
		</tr>
		
		<tr>
			<td colspan="4" style="position:relative;left:-30px"><br>验货信息<hr></td>
		</tr>
		<tr>
			<td align="right">验货结果：</td>
			<td>
				<c:choose>
					<c:when test="${orderInfoDTO.sourceExamineEntity.status == 1}">验证通过</c:when>
					<c:when test="${orderInfoDTO.sourceExamineEntity.status == 2}">验证不通过</c:when>
				</c:choose>
			</td>
			<td align="right">司机验货时间：</td>
			<td><fmt:formatDate value="${orderInfoDTO.sourceExamineEntity.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td align="right">验货说明：</td>
			<td colspan="3">${orderInfoDTO.sourceExamineEntity.description }</td>
		</tr>
		<tr>
			<td align="right">验货图片：</td>
			<td colspan="3">
				<c:if test="${!empty orderInfoDTO.sourceExamineEntity.imageUrl}">
					<a href="${orderInfoDTO.sourceExamineEntity.imageUrl}" data-lightbox="imageUrl">
						<img src="${orderInfoDTO.sourceExamineEntity.imageUrl}" style="width: 120px;height: 80px;"/>
					</a>
				</c:if>
				<c:if test="${!empty orderInfoDTO.sourceExamineEntity.imageUrl2}">
					<a href="${orderInfoDTO.sourceExamineEntity.imageUrl2}" data-lightbox="imageUrl2">
						<img src="${orderInfoDTO.sourceExamineEntity.imageUrl2}" style="width: 120px;height: 80px;"/>
					</a>
				</c:if>
				<c:if test="${!empty orderInfoDTO.sourceExamineEntity.imageUrl3}">
					<a href="${orderInfoDTO.sourceExamineEntity.imageUrl3}" data-lightbox="imageUrl3">
						<img src="${orderInfoDTO.sourceExamineEntity.imageUrl3}" style="width: 120px;height: 80px;"/>
					</a>
				</c:if>
				<c:if test="${!empty orderInfoDTO.sourceExamineEntity.imageUrl4}">
					<a href="${orderInfoDTO.sourceExamineEntity.imageUrl4}" data-lightbox="imageUrl4">
						<img src="${orderInfoDTO.sourceExamineEntity.imageUrl4}" style="width: 120px;height: 80px;"/>
					</a>
				</c:if>
				<c:if test="${!empty orderInfoDTO.sourceExamineEntity.imageUrl5}">
					<a href="${orderInfoDTO.sourceExamineEntity.imageUrl5}" data-lightbox="imageUrl5">
						<img src="${orderInfoDTO.sourceExamineEntity.imageUrl5}" style="width: 120px;height: 80px;"/>
					</a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="4" style="position:relative;left:-30px"><br>支付信息<hr></td>
		</tr>
		<tr>
			<td align="right">付款状态：</td>
			<td>${orderInfoDTO.orderPayDetail.payStatusStr }</td>
			<td align="right">支付方式：</td>
			<td>${orderInfoDTO.orderPayDetail.payTypeStr }</td>
		</tr>
		<tr>
			<td align="right">付款时间：</td>
			<td><fmt:formatDate value="${orderInfoDTO.orderPayDetail.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="right">支付金额：</td>
			<td>${orderInfoDTO.orderPayDetail.payMoney}</td>
		</tr>
		<tr>
			<td align="right">平台支付流水：</td>
			<td>${orderInfoDTO.orderPayDetail.platformPayWater}</td>
			<td align="right">第三方支付流水：</td>
			<td>${orderInfoDTO.orderPayDetail.thirdPartyPayWater}</td>
		</tr>
		<tr>
			<td align="right">付款账号：</td>
			<td>${orderInfoDTO.orderPayDetail.payAccountNo}</td>
			<td align="right">付款方姓名：</td>
			<td>${orderInfoDTO.orderPayDetail.payName}</td>
		</tr>
		<tr>
			<td align="right">收款账号：</td>
			<td>${orderInfoDTO.orderPayDetail.receiptNo}</td>
			<td align="right">收款方姓名：</td>
			<td>${orderInfoDTO.orderPayDetail.receiptName}</td>
		</tr>
	</table>
</div>
<script type="text/javascript" src="${CONTEXT}js/lightbox/js/lightbox-2.6.min.js"></script>
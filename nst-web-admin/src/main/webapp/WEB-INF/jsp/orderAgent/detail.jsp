<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<td align="right">信息订单号：</td>
			<td style="min-width:200px">${orderAgentDTO.orderNo }</td>
			<td align="right">订单状态：</td>
			<td style="min-width:200px">${orderAgentDTO.orderStatusStr }</td>
		</tr>
		<tr>
			<td align="right">支付状态：</td>
			<td>${orderAgentDTO.payStatusStr}</td>
			<td align="right">信息费：</td>
			<td>${orderAgentDTO.infoAmt}</td>
		</tr>
		<tr>
			<td align="right">接单时间：</td>
			<td><fmt:formatDate value="${orderAgentDTO.confirmTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="right">接单处理时间：</td>
			<td><fmt:formatDate value="${orderAgentDTO.logisticTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td align="right">货主姓名：</td>
			<td>${orderAgentDTO.sourceMemberName }</td>
			<td align="right">货主手机：</td>
			<td>${orderAgentDTO.sourceMemberMobile }</td>
		</tr>
		<tr>
			<td align="right">物流公司名称：</td>
			<td>${orderAgentDTO.logisticCompanyName }</td>
			<td align="right">物流公司手机：</td>
			<td>${orderAgentDTO.logisticMobile }</td>
		</tr>
		<tr>
			<td align="right">车主姓名：</td>
			<td>${orderAgentDTO.driverName }</td>
			<td align="right">车主手机：</td>
			<td>${orderAgentDTO.driverMobile }</td>
		</tr>
		
		<tr>
			<td colspan="4" style="position:relative;left:-30px"><br>支付信息<hr></td>
		</tr>
			<tr>
			<td align="right">付款状态：</td>
			<td>${orderAgentDTO.orderPayDetail.payStatusStr }</td>
			<td align="right">支付方式：</td>
			<td>${orderAgentDTO.orderPayDetail.payTypeStr }</td>
		</tr>
		<tr>
			<td align="right">付款时间：</td>
			<td><fmt:formatDate value="${orderAgentDTO.orderPayDetail.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="right">支付金额：</td>
			<td>${orderAgentDTO.orderPayDetail.payMoney}</td>
		</tr>
		<tr>
			<td align="right">平台支付流水：</td>
			<td>${orderAgentDTO.orderPayDetail.platformPayWater}</td>
			<td align="right">第三方支付流水：</td>
			<td>${orderAgentDTO.orderPayDetail.thirdPartyPayWater}</td>
		</tr>
		<tr>
			<td align="right">付款账号：</td>
			<td>${orderAgentDTO.orderPayDetail.payAccountNo}</td>
			<td align="right">付款方姓名：</td>
			<td>${orderAgentDTO.orderPayDetail.payName}</td>
		</tr>
		<tr>
			<td align="right">收款账号：</td>
			<td>${orderAgentDTO.orderPayDetail.receiptNo}</td>
			<td align="right">收款方姓名：</td>
			<td>${orderAgentDTO.orderPayDetail.receiptName}</td>
		</tr>
	</table>
</div>
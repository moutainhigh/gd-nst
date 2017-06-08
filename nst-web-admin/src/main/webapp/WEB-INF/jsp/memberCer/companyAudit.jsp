<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<%@ include file="../pub/constants.inc" %>
		<%@ include file="../pub/head.inc" %>
		<%@ include file="../pub/tags.inc" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9, IE=10, IE=11, IE=12"/>
		<title>企业认证审核</title>
</head>
<body>
     <div id="auditTab" class="easyui-tabs" style="width:844px;height:541px;margin: 10px auto;">   
	    <div title="企业认证" style="padding:20px;">  
	       <span>企业认证信息</span> 
	       <hr style="border:1px double #e8e8e8"/>
	       <div>
	          <table style="margin: 10px auto" width="750" height="95">
	              <tr>
	                <td style="width:120px;text-align: right">账号:</td>
	                <td style="width:366px;text-align: left">${memberCerDTO.account}</td>
	                <td style="text-align: right">认证来源:</td>
		            <td style="text-align: left">${memberCerDTO.certificFrom==1 ? '农速通-货主' : (memberCerDTO.certificFrom == 2 ? '农速通-车主' : (memberCerDTO.certificFrom == 3 ? '农速通-物流公司' : ''))}</td>		        
	              </tr>
		          <tr>		              
	                <td style="text-align: right">姓名:</td>
		            <td style="text-align: left">${memberCerDTO.realName}</td>		          
	                <td style="text-align: right">身份证号码:</td>
	                <td style="text-align: left">${memberCerDTO.idCard}</td>
		          </tr>
		          <tr>		           
		            <td style="text-align: right">申请时间:</td>
		            <td><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${not empty memberCerDTO.updateTime ? memberCerDTO.updateTime : memberCerDTO.createTime}" /></td>
		            <td style="text-align: right">手机:</td>
	                <td style="text-align: left">${memberCerDTO.mobile}</td>
		          </tr>
		          <tr>
		            <td style="text-align: right">企业名称:</td>
		            <td style="text-align: left">${memberCerDTO.companyName}</td>
		            <td style="text-align: right">车辆信息:</td>
	                <td style="text-align: left">${memberCerDTO.carNumber}</td>	               
		          </tr>
		          <tr style="text-align: right">
		            <td style="text-align: right;display:inline;vertical-align:right">身份证照片:</td>
		            <td>
		              <div style="float:left;margin-right:3px">
		                 <a href="${memberCerDTO.idCardUrl_z}" data-lightbox="IdCard_Z" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
		                    <img src="${memberCerDTO.idCardUrl_z}" style="width: 120px;height: 80px;display:${empty memberCerDTO.idCardUrl_z?'none':'block'}"/>		                    
		                 </a>
		              </div>
		              <div>
		                 <a href="${memberCerDTO.idCardUrl_f}" data-lightbox="IdCard_F" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
		                    <img src="${memberCerDTO.idCardUrl_f}" style="width: 120px;height: 80px;display:${empty memberCerDTO.idCardUrl_f?'none':'block'}"/>		                    
		                 </a>
		               </div>
		            </td>
		            <td style="text-align: right;display:inline;vertical-align:right">头像:</td>
		            <td>	
		                <a href="${memberCerDTO.iconUrl}" data-lightbox="headPhoto" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
		                   <img src="${memberCerDTO.iconUrl}" style="width: 120px;height: 80px;display:${empty memberCerDTO.iconUrl?'none':'block'}"/>
		                </a>		                
		             </td>
		          </tr>
		          <tr style="text-align: right">
		             <td style="text-align: right;display:inline;vertical-align:right">其他照片:</td>
		             <td>
		               <c:if test="${not empty memberCerDTO.doorUrl}">
			               <div style="float:left;margin-right:3px;">
			                  <a href="${memberCerDTO.doorUrl}" data-lightbox="otherPhoto1" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
			                  	 <img src="${memberCerDTO.doorUrl}" style="width: 120px;height: 80px"/>		                  
			                  </a>
			               </div>
		               </c:if>
		               <c:if test="${not empty memberCerDTO.visitingCardUrl}">
			               <div style="float:left;margin-right:3px">
			                  <a href="${memberCerDTO.visitingCardUrl}" data-lightbox="otherPhoto2" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
			                      <img src="${memberCerDTO.visitingCardUrl}" style="width: 120px;height: 80px"/>		                  
			                  </a>
			               </div>
			           </c:if>
			           <c:if test="${not empty memberCerDTO.bzlUrl}">
			               <div style="float:left;margin-right:3px">
			                  <a href="${memberCerDTO.bzlUrl}" data-lightbox="otherPhoto3" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
			                     <img src="${memberCerDTO.bzlUrl}" style="width: 120px;height:80px"/>		                    
			                  </a>
			               </div>
		               </c:if>
		               <c:if test="${not empty memberCerDTO.groupCardUrl}">
			               <div style="float:left">
			                  <a href="${memberCerDTO.groupCardUrl}" data-lightbox="otherPhoto4" onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
			                     <img src="${memberCerDTO.groupCardUrl}" style="width: 120px;height:80px"/>		                    
			                  </a>
			               </div>
		               </c:if>
		             </td>
		             <td></td>	
                     <td></td>
		          </tr>
		          <tr style="text-align: right">
		                <td style="text-align: right;display:inline;vertical-align:right">车辆照片:</td>
			            <td>
			              <div style="float:left;margin-right:3px">
			                 <a href="${memberCerDTO.carHeadUrl}" data-lightbox="car_Z"  onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
			                    <img src="${memberCerDTO.carHeadUrl}" style="width: 120px;height: 80px;display:${empty memberCerDTO.carHeadUrl?'none':'block'}"/>		                    
			                 </a>
			              </div>
			              <div style="float:left;margin-right:3px">
			                 <a href="${memberCerDTO.carRearUrl}" data-lightbox="car_F"  onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
			                    <img src="${memberCerDTO.carRearUrl}" style="width: 120px;height: 80px;display:${empty memberCerDTO.carRearUrl?'none':'block'}"/>		                    
			                 </a>
			               </div>
			               <div style="float:left">
				               <a href="${memberCerDTO.vehicleUrl}" data-lightbox="car_3"  onclick="setTitle($(this),'姓名:${memberCerDTO.realName}<br/>身份证号码：${memberCerDTO.idCard}<br/>企业名称：${memberCerDTO.companyName}')" onmouseover="clearTitle($(this))" title="">
				                    <img src="${memberCerDTO.vehicleUrl}" style="width: 120px;height: 80px;display:${empty memberCerDTO.vehicleUrl?'none':'block'}"/>		                    
				               </a>
			              </div>
			            </td>		            
		           </tr>
	          </table>
	       </div>
	       <c:if test="${isShow!='0'}">
	       <span>审核信息</span>    
	       <hr style="border:1px double #e8e8e8"/> 
	       <form id="auditForm">
	          <input id="hidCerId" type="hidden" name="cerId" value="${memberCerDTO.id}"/>
	          <input id="hidMemberId" type="hidden" name="memberId" value="${memberCerDTO.memberId}"/>
	          <input id="hidStatus" type="hidden" name="cerStatus" value="" />	        
	          <input id="hidUserType" type="hidden" name="userType" value="${memberCerDTO.userType}" />
		      <div style="height:49px;margin-top: 20px">
		          <span style="color: red">*</span>审核结果：
		          <input id="passRadio" type="radio" value="1" onclick="selectRadio($('#passRadio'),$('#noPassRadio'))"/>
		          <span>通过</span>
		          <input id="noPassRadio" type="radio" value="2" onclick="selectRadio($('#noPassRadio'),$('#passRadio'))"/>
		          <span>驳回</span>	          
		       </div>
		        <div>
		          <div style="float:left"><span style="color: red;">*</span>审核意见：</div>
		          <div style="float: right;margin-right: 224px">
			          <textarea id="auditOpinion" name="auditOpinion" style="height:100px;width:500px;"></textarea>
			      </div>	          
		       </div>
		    </form>
		    </c:if>
		    <c:if test="${isShow=='0'}">
		          <input id="hidCerId" type="hidden" name="cerId" value="${memberCerDTO.id}"/>
		     </c:if>
	    </div>   
	    <div title="审核记录" style="overflow:auto;padding:20px;">   
	        <table id="auditdg" title="">
	        </table>   
	    </div> 
     </div>
     <input id="hidShow" type="hidden" value="${isShow}"/>
     <script type="text/javascript" src="${CONTEXT}js/memberCer/companyCerAudit.js"></script>
     <script type="text/javascript" src="${CONTEXT}js/lightbox/js/lightbox-2.6.min.js"></script>
</body>

</html>
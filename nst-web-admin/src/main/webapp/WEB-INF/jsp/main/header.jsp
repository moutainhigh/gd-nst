<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="logo-box" style="width:100%;background: #588cda">
	<img src="${CONTEXT}images/logo.png" alt="谷登" >
	<span style="font-size:20px;position:absolute;top:35px;left:185px;color:#3178E6;">农速通管理后台</span>
</div>
<div class="u-box">
	<div class="loginWelcome">${systemUserCode }
		您好！ <a href="${CONTEXT}sys/loginOut" id="loginOut">退出 </a> 
		<span style="display: inline-block;margin-left: 13px;">|</span>
		<a style="margin-left: 10px;" href="javascript:editPassword()">修改密码</a>
	</div>
</div>

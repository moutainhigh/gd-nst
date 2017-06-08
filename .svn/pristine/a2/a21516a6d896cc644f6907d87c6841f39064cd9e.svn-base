<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<title>会员信息</title>
</head>
<body>
	<table id="memberInfodg" title=""></table>
	<div id="memberInfotb" style="padding:5px;height:auto">
		<form id="memberInfoSearchForm" method="post">
		<div>
			<table>
				<tr>
					<td>姓名：</td>
					<td><input type="text" maxlength="30" id="listUserName" name="listUserName" style="width:150px" placeholder="请输入姓名">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>手机：</td>
					<td><input type="text" maxlength="11" id="listMobile" name="listMobile" style="width:150px" placeholder="请输入手机号">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>帐号状态：</td>
					<td>
					<select  name="status" id="status" style="width: 60px;">
					  <option value="">全部</option>
					   <option value="0">禁用</option>
					   <option value="1">启用</option>
					</select>
					</td>
					<td>注册时间：</td>
					<td>
						<input  type="text" id="startDate" name="startDate"  
							onFocus="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})"   
							onClick="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})" 
							style="width:100px" placeholder="开始时间"> ~
						<input  type="text" id="endDate" name="endDate"   
							onFocus="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})"   
							onClick="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})" 
							style="width:100px" placeholder="结束时间">&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>注册来源：</td>
					<td>
					<select  name="regetype" id="regetype" style="width: 154px;">
					   <option value="">全部</option>
					   <option value="0">管理后台</option>
					   <option value="1">谷登农批</option>
					   <option value="3">农商友</option>
					   <option value="4">农商友-农批商</option> 
					   <option value="5">农批友</option> 
					   <option value="6">农商友-供应商</option>
					   <option value="7">POS刷卡</option>
					   <option value="8">微信授权</option>
					   <option value="9">农速通-货主</option>
					   <option value="10">农速通-司机</option> 
					   <option value="11">农速通-物流公司</option> 
					</select>
					</td>
					<td>
					个人认证:
					</td>
					<td>
				   <select  name="cerPersonalStatus" id="cerPersonalStatus" style="width: 154px;">
					   <option value="">全部</option>
					   <option value="0">待认证</option>
					   <option value="1">认证中</option>
					   <option value="2">已认证</option>
					   <option value="3">已驳回</option>
					</select>
					</td>
					<td>
					企业认证:
					</td>
					<td>
					<select  name="cerCompanyStatus" id="cerCompanyStatus">
					   <option value="">全部</option>
					   <option value="0">待认证</option>
					   <option value="1">认证中</option>
					   <option value="2">已认证</option>
					   <option value="3">已驳回</option>
					</select>
					</td>
					<td>
						业务范围:
					</td>
					<td>
					<select  name="serviceType" id="serviceType" style="width: 225px;">
					   <option value="">全部</option>
					   <option value="1">干线业务</option>
					   <option value="2">同城业务</option>
					</select>
					</td>
				  <td>
				  </td>
					<td>
					</td>
				</tr>
				<tr>
					<td>
					货源是否指派:
					</td>
					<td>
					<select  name="appoint" id="appoint" style="width: 154px;">
					   <option value="">全部</option>
					   <option value="0">是</option>
					   <option value="1">否</option>
					</select>
					</td>
					<td>
					指派的公司/车主：
					</td>
					<td>
					<input type="text" id="listMemberIdLogisticName" name="ListmemberIdLogisticName" style="width:150px" placeholder="请输入公司/车主" />
					</td>
					<td>
					</td>
					<td>
					</td>
					<td colspan="2">
						
						<a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="member-btn-search">查询</a>
						&nbsp;&nbsp;
						<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="btn-reset">重置</a>
					</td>
				</tr>	
			</table>
		</div>
		</form>
		<div style="background:#efefef;padding:5px 0 5px 0;height:25px;">
			<div style="float: left;font-size:14px;margin-left:5px;">会员信息列表</div>
			<div style="float:right;margin-right:10px;">
			<gd:btn btncode='MEMBERINFORESETPWD'>
				<a id="resetPwd" class="easyui-linkbutton" iconCls="icon-reload">重置密码</a>
			</gd:btn>
			<gd:btn btncode='MEMBERINFODISABLE'>
			<a id="disableBtn" class="easyui-linkbutton" iconCls="icon-reload">禁用/启用</a>
			</gd:btn>
	<!-- 		<a class="easyui-linkbutton" iconCls="icon-reload" onclick='location.reload(true)'">刷新</a> -->
		   <gd:btn btncode='MEMBERINFOEXP'>
		   <a class="easyui-linkbutton icon-save-btn" iconCls="icon-account-excel" id="exportData" >导出</a>&nbsp;&nbsp;
			</gd:btn>
			</div>
		</div>
	</div>
		<div id="win" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" >
	
	</div>	
</body>
<script type="text/javascript"
	src="${CONTEXT}js/memberInfo/memberInfoList.js"></script>
<script type="text/javascript" src="${CONTEXT}js/lightbox/js/lightbox-2.6.min.js"></script>
<script type="text/javascript">
function accountFormatter(v,r,i) {
	return "<gd:btn btncode='MEMBERINFODETAIL'><a  class='operate' href='javascript:editObj("+r.id+")' ></gd:btn>"+v+"<gd:btn btncode='MEMBERINFODETAIL'></a></gd:btn>"
		}
</script>
</html>


 
  

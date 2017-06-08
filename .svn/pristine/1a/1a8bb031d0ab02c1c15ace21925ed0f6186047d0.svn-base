<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../pub/tags.inc" %>
<%@ include file="../pub/constants.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<%@ include file="../pub/head.inc" %>
	<script type="text/javascript">
	
	$(function(){
		var act = "${act_status}";
		if(act!=""){
			//新增成功
			if(act=="sv_sucs"){
				jQuery.dialog({
				    content: "新增成功",
				    ok: function () {
				    	$.IncWindow.close();
				    	$.IncWindow.reload();
				    	return true;
				    }
				});
				return true;
			}else if(act=="app_not_exist"){
				jQuery.dialog({
				    content: "未读取到新版APP",
				    ok: function () {
				    	return true;
				    }
				});
				return true;
			}
			//错误信息
			jQuery.dialog({
			    content: act,
			    ok: function () {
			    	//$.IncWindow.close();
			    	return true;
			    }
			});
		}
	});
	
	//新增验证
	function addCheck(){
		
		if($.isEmpty($.trim($("#type").val()))){
			alert("APP为必选项!");
			return false;
		}
		if($.isEmpty($.trim($("#num").val()))){
			alert("版本号为必填项且不能全为空格!");
			return false;
		}
		if($.isEmpty($.trim($("#apkAddress").val()))){
			alert("APK地址为必填项且不能全为空格!");
			return false;
		}
		if($.trim($("#remark").val()).length>200){
			alert("描述太长，能精简点么！");
			return false;
		}
		if($.isEmpty($.trim($("#svnAddress").val()))){
			alert("svn地址不能为空！");
			return false;
		}		
		if($.isEmpty($.trim($("#publishTime").val()))){
			alert("发布时间不能为空！");
			return false;
		}	
		if($.isEmpty($.trim($("#platform").val()))){
			alert("平台不能为空！");
			return false;
		}				
		return true;
	}
	
	function doAction(){
		//新增验证
		  if(addCheck()){
			  $("#versionForm").submit();
		  }
	}
	 
	</script>
  </head>
  
<body>
<form action="add" method="post" id="versionForm">
<table width="100%" border="0" class="TableClear" style="height:auto">
  <tr>
    <td bgcolor="#58B2E9">版本信息</td>
  </tr>
  <tr>
    <td><table width="100%" class="table0">
	      <tbody>
	      <tr>
	      <td width="20%" align=right ><font color="red">*</font>APP名称：</td>
	      <td width="25%" align=left >
          	  		<select name="type" id="type" style="width:120px">
          	  		    <option value="">--请选择--</option>
          	  		    <option value="1" <c:if test="${version.type eq '1' }"> selected='selected'</c:if>>农速通-货主</option>
          	  		    <option value="2" <c:if test="${version.type eq '2' }"> selected='selected'</c:if>>农速通-司机</option>
          	  		    <option value="3" <c:if test="${version.type eq '3' }"> selected='selected'</c:if>>农速通-物流公司</option>
          	  		</select>
          	  </td>
           <td width="5%"  align="center">
           </td>
	      </tr>
	      
	      <tr>
	      <td width="20%" align=right ><font color="red">*</font>平台：</td>
	      <td width="25%" align=left >
          	  		<select name="platform" id="platform" style="width:120px">
          	  		    <option value="">--请选择--</option>
          	  		    <option value="1" <c:if test="${version.platform eq '1' }"> selected='selected'</c:if>>IOS</option>
          	  		    <option value="2" <c:if test="${version.platform eq '2' }"> selected='selected'</c:if>>Android</option>
          	  		    <option value="3" <c:if test="${version.platform eq '3' }"> selected='selected'</c:if>>HTML5</option>
          	  		</select>
          	  </td>
           <td width="5%"  align="center">
           </td>
	      </tr>

	      
	        <tr>
	          <td width="20%" align=right ><font color="red">*</font>版本号：</td>
	          <td width="25%" align=left ><input name="num" id="num" value="${version.num }" type="text" class="text" size="15" maxlength="12"></td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>
	      
	        <tr>
	          <td width="20%" align=right ><font color="red">*</font>产品负责人：</td>
	          <td width="25%" align=left ><input name="productleader" id="productleader" value="${version.productleader }" type="text" class="text" size="15" maxlength="12"></td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>
	        	        
	        <tr>
	          <td width="20%" align=right ><font color="red">*</font>上线时间：</td>
	          <td width="25%" align=left ><input name="publishTime" id="publishTime" value="${version.publishTime }" 
	          onFocus="WdatePicker({onpicked:function(){publishTime.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss'})"   
	          onClick="WdatePicker({onpicked:function(){publishTime.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss'})"
	           type="text" class="text" size="20" maxlength="20"></td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>
	        	        
	        <tr>
	          <td width="20%" align=right >功能说明：</td>
	          <td width="25%" align=left >
	          	<textarea name="remark" id="remark" rows="10" cols="76">${version.remark }</textarea>
	          </td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>
	        
	        <tr>
	          <td width="20%" align=right ><font color="red">*</font>SVN地址：</td>
	          <td width="25%" align=left ><input name="svnAddress" id="svnAddress" value="${version.svnAddress }" type="text" class="text" size="50" maxlength="128"></td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>		        
	        
	        <tr>
	          <td width="20%" align=right ><font color="red">*</font>是否强制升级：</td>
	          <td width="25%" align=left >
	          	<input type="radio" name="needEnforce" value="0" <c:if test="${version.needEnforce eq '0' || empty version.needEnforce }"> checked='checked'</c:if>>否
	          	<input type="radio" name="needEnforce" value="1"  <c:if test="${version.needEnforce eq '1'}" > checked='checked'</c:if> >是
	          </td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>
	        
	        <tr>
	          <td width="20%" align=right >主站下载地址：</td>
	          <td width="25%" align=left ><input name="apkAddress" id="apkAddress" value="${version.apkAddress }" type="text" class="text" size="50" maxlength="128"></td>
	          <td width="5%"  align="center">
	          </td>
	        </tr>	        
	        
	      </tbody>
        </table>
       </td>
      </tr>
      </table>
</form>
</body>

</html>

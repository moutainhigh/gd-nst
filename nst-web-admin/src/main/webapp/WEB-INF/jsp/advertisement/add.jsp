<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin: 10px auto;width:80%">
	<form action="" id="addAdvertisementForm">
		<input type="hidden" name="provinceId" id="provinceId" value="${provinceId }">
		<input type="hidden" name="cityId" id="cityId" value="${cityId }">
		<table>
			<tr>
				<td align="right"><font color="red">*</font>广告名称：</td>
				<td><input type="text" name="name" id="name" style="width:160px" maxlength="20" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>广告渠道：</td>
				<td>
					<select name="channel" id="channel" style="width:160px" class="easyui-validatebox" required="true">
						<option value="">-----请选择-----</option>
						<c:forEach var="channel" items="<%=cn.gdeng.nst.enums.AdChannelEnum.values()%>">
							<option value="${channel.code }">${channel.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>开始时间：</td>
				<td><input type="text" id="startTime"
					onFocus="WdatePicker({onpicked:function(){startTime.focus();},minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
					onClick="WdatePicker({onpicked:function(){startTime.focus();},minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					name="startTime" style="width: 160px;" class="easyui-validatebox" required="true" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>结束时间：</td>
				<td><input type="text" id="endTime"
					onFocus="WdatePicker({onpicked:function(){endTime.focus();},minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
					onClick="WdatePicker({onpicked:function(){endTime.focus();},minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					name="endTime" style="width: 160px;" class="easyui-validatebox" required="true" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>排序：</td>
				<td>
					<select name="sort" id="sort" style="width:160px" required="required" class="easyui-validatebox">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
					</select>&nbsp;越小越靠前
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>上传广告图片：<br>300*750&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
					<div class="user-iteam">
				        <div class="fl mr_10" id="uploadContainer">
				            <input type="file" class="g-u input01" id="upload_btn1" value="" name="file" />
				            <input type="hidden" id="J_Urls1" name="imgUrl"/><font color="red">*</font>
				        </div>
				        <div class="form-context">
				            <div class="clear"></div>
				            <ul id="picture_queen1" class="grid"></ul>
				        </div>
				        <BR>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
function initUploadModule(uploadBtn, uploadUrl, queueId, JUrls, pictureCountLimit, filesStr, certification){
	var config_mutiple = {
	        galleryUrl : CONTEXT+"js/uploader/",//控件静态地址
	        uploadBtn : uploadBtn,//初始的button
	        queueId : queueId,//图片装载容器id
	        JUrls : JUrls,//成功后返回url参数写入id
	        action : uploadUrl ,//上传地址
	        max : pictureCountLimit,//允许上传个数
	        type :"ajax",//上传类型
	        //imgUrlPrefix : '${imgHost}',
	        filesStr : filesStr,//此处是附加参数，已经上传需要第二次修的图片参数
	        certification : certification//此处是附加参数，已经上传需要第二次修，是否可以修改
	};
	gdKissySingleUploader.init(config_mutiple ,function(uploader){
	     var filesStr = config_mutiple.filesStr;
	     var certification = config_mutiple.certification;
	     if(filesStr != ''&&filesStr!=undefined){
	        var fileList = eval("("+  filesStr + ")");
	        gdKissySingleUploader.addFiles(fileList,uploader);
	        if(certification == 0){
	                $("#"+JUrls).parent().find(".file-input").attr("disabled","disabled");
	        }else{
	        	/**可以修改时注册删除事件**/
	        	$(".form-context").delegate(".imageUploader_del","click",function(){
	        		$("#JUrls1").val("");
				});
	        }
	     }
	},function(url){
 	    
	});
}
$(document).ready(function(){
	var masterUrl = CONTEXT+'uploadFile';
    initUploadModule("upload_btn1",masterUrl, "picture_queen1", "J_Urls1");
});
</script>
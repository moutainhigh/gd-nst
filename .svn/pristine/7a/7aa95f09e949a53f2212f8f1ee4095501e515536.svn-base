<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin: 10px auto;width:80%">
	<form action="" id="editAdForm">
		<input type="hidden" name="id" value="${dto.id }"/>
		<input type="hidden" name="cityId" value="${dto.cityId }"/>
		<table>
			<tr>
				<td align="right"><font color="red">*</font>广告名称：</td>
				<td><input type="text" name="name" id="name" style="width:160px" maxlength="20" value="${dto.name }" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>广告渠道：</td>
				<td><select name="channel" id="channel" style="width:160px" class="easyui-validatebox" required="true">
						<option value="">-----请选择-----</option>
						<c:forEach var="channel" items="<%=cn.gdeng.nst.enums.AdChannelEnum.values()%>">
							<option value="${channel.code }" ${dto.channel == channel.code ? "selected" : ""}>${channel.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>开始时间：</td>
				<td><input type="text" id="edit_startTime"
					onFocus="WdatePicker({onpicked:function(){edit_startTime.focus();},minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'edit_endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
					onClick="WdatePicker({onpicked:function(){edit_startTime.focus();},minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'edit_endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					name="startTime" style="width: 160px;" value="<fmt:formatDate value='${dto.startTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" class="easyui-validatebox" required="true" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>结束时间：</td>
				<td><input type="text" id="edit_endTime"
					onFocus="WdatePicker({onpicked:function(){edit_endTime.focus();},minDate:'#F{$dp.$D(\'edit_startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
					onClick="WdatePicker({onpicked:function(){edit_endTime.focus();},minDate:'#F{$dp.$D(\'edit_startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					name="endTime" style="width: 160px;" value="<fmt:formatDate value='${dto.endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" class="easyui-validatebox" required="true" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>排序：</td>
				<td>
					<select name="sort" id="sort" style="width:160px" required="required" class="easyui-validatebox">
						<option value="1" ${dto.sort == 1 ? "selected" : "" }>1</option>
						<option value="2" ${dto.sort == 2 ? "selected" : "" }>2</option>
						<option value="3" ${dto.sort == 3 ? "selected" : "" }>3</option>
						<option value="4" ${dto.sort == 4 ? "selected" : "" }>4</option>
						<option value="5" ${dto.sort == 5 ? "selected" : "" }>5</option>
						<option value="6" ${dto.sort == 6 ? "selected" : "" }>6</option>
						<option value="7" ${dto.sort == 7 ? "selected" : "" }>7</option>
						<option value="8" ${dto.sort == 8 ? "selected" : "" }>8</option>
					</select>&nbsp;越小越靠前
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>上传广告图片：<br>300*750&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
					<div class="user-iteam">
				        <div class="fl mr_10" id="edit_uploadContainer">
				            <input type="file" class="g-u input01" id="edit_upload_btn1" value="" name="file" />
				            <input type="hidden" id="edit_J_Urls1" name="imgUrl" />
				        </div>
				        <div class="form-context">
				            <div class="clear"></div>
				            <ul id="edit_picture_queen1" class="grid">
			   					<script type="text/uploader-files">[{"url":"${dto.imgUrl }"}]</script>
				            </ul>
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
	        		$("#edit_J_Urls1").val("");
				});
	        }
	     }
	},function(url){
 	    
	});
}
$(document).ready(function(){
	var masterUrl = CONTEXT+'uploadFile';
    initUploadModule("edit_upload_btn1",masterUrl, "edit_picture_queen1", "edit_J_Urls1");
});
</script>
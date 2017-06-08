<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin: 10px auto;width:80%">
	<form action="" id="addAdBannerForm">
		<table>
			<tr>
				<td>广告名称：</td>
				<td><input type="text" name="name" id="name" style="width:153px" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
			</tr>
			<tr>
				<td>广告渠道：</td>
				<td>
					<select name="channel" id="channel" style="width:158px" class="easyui-validatebox" required="true">
						<c:forEach var="channel" items="<%=cn.gdeng.nst.enums.AdChannelEnum.values()%>">
							<option value="${channel.code }">${channel.name}</option>
						</c:forEach>
					</select>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>广告类型：</td>
				<td>
					<select name="type" id="type" style="width:158px" class="easyui-validatebox" required="true">
						<c:forEach var="type" items="<%=cn.gdeng.nst.enums.AdBannerTypeEnum.values() %>">
							<option value="${type.code }">${type.name}</option>
						</c:forEach>
					</select>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>开始时间:</td>
				<td><input type="text" id="startTime"
					onFocus="WdatePicker({onpicked:function(){startTime.focus();},minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
					onClick="WdatePicker({onpicked:function(){startTime.focus();},minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					name="startTime" style="width: 153px;" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>结束时间:</td>
				<td><input type="text" id="endTime"
					onFocus="WdatePicker({onpicked:function(){endTime.focus();},minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"    
					onClick="WdatePicker({onpicked:function(){endTime.focus();},minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					name="endTime" style="width: 153px;" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td>排序：</td>
				<td><input type="text" name="sort" id="sort" style="width:153px" validtype="number" class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td>上传图片:</td>
				<td>
					<div class="user-iteam">
				        <div class="fl mr_10" id="uploadContainer">
				            <input type="file" class="g-u input01" id="upload_btn1" value="" name="file" />
				            <input type="hidden" id="J_Urls1" name="imgUrl"/><font color="red">*</font>
				        </div>
				        <div class="form-context">
				            <div class="clear"></div>
				            <ul id="picture_queen1" class="grid">
				    		<script type="text/uploader-theme">
								<li id="queue-file-{id}" class="g-u" data-name="{name}">
	                				<div class="pic"><a href="javascript:void(0);"><img class="J_Pic_{id} preview-img" src="" /></a></div>
	                				<div class=" J_Mask_{id} pic-mask"></div>
	                				<div class="status-wrapper">
	                    				<div class="status waiting-status"><p>等待上传，请稍候</p></div>
	                    				<div class="status start-status progress-status success-status">
	                        				<div class="J_ProgressBar_{id}"><s class="loading-icon"></s>上传中...</div>
	                    				</div>
	                    				<div class="status error-status"><p class="J_ErrorMsg_{id}">服务器故障，请稍候再试！</p></div>
									</div>
	                				<div class="imageUploader_menu"> 
										<a href="javascript:void(0);" class="imageUploader_del" id="imageUploader_del_{id}" title="删除">&nbsp;</a>
									</div>
	            				</li>
    						</script>
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
	        		$("#JUrls1").val("");
				    $(this).parents("li").get(0).remove();
				    var originalUrl = $(this).parent().siblings().find("a img").attr("data-original-url");
				    $("input[name*='"+JUrls+"']").each(function(){
				        if($(this).val()==originalUrl){
				            $(this).val("");
				        }
				    });
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/pub/constants.inc" %>
<%@ include file="/WEB-INF/jsp/pub/tags.inc" %>
<!DOCTYPE>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/pub/head.inc" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9, IE=10, IE=11, IE=12"/>
	<base href="${CONTEXT}">
	<title>push</title>
	<script type="text/javascript" src="${CONTEXT}js/kissy-min.js"></script>
	<script type="text/javascript" src="${CONTEXT}js/gd.kissy.uploader.js"></script>
	<script type="text/javascript" src="${CONTEXT}js/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="easyui-layout">
	<input type="hidden" id="provinceId" name="provinceId"/>
	<input type="hidden" id="cityId" name="cityId"/>
	<div data-options="region:'north'" style="padding-top: 10px;background: #fafafa;border-color: #ddd;overflow:hidden">
		<form id="adSearchForm" method="post">
			<input type="hidden" id="provinceId" name="provinceId">
			<input type="hidden" id="cityId" name="cityId">
			广告名称: 
			<input type="text" id="name" class="easyui-validatebox" name="name" style="width:150px" >
			广告时间 :
			<input  type="text"  id="startDate" name="startDateStr"  
				onFocus="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})"   
				onClick="WdatePicker({onpicked:function(){startDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})"    
				style="width:150px" >~
			<input  type="text"    id="endDate" name="endDateStr"   
				onFocus="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})"   
				onClick="WdatePicker({onpicked:function(){endDate.focus();},minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})"   style="width:150px">
			所属渠道:
			<select name="channel" id="channel" style="width: 100px;">
				<option value="">全部</option>
				<c:forEach var="channel" items="<%=cn.gdeng.nst.enums.AdChannelEnum.values() %>">
				<option value="${channel.code }">${channel.name }</option>
				</c:forEach>
			</select>
			广告状态:
			<select name="status" id="status" style="width: 100px;">
				<option value="">全部</option>
				<c:forEach var="status" items="<%=cn.gdeng.nst.enums.AdStatusEnum.values() %>">
					<c:if test="${status.code != 4 }">
						<option value="${status.code }">${status.name }</option>
					</c:if>
				</c:forEach>
			</select>
			<br><br>
			<gd:btn btncode="GGGL001"><a class="easyui-linkbutton icon-search-btn" iconCls="icon-add" id="icon-add">新建广告</a></gd:btn>
			<gd:btn btncode="GGGL004"><a class="easyui-linkbutton icon-search-btn" iconCls="icon-search" id="icon-search">查询</a></gd:btn>
			<a class="easyui-linkbutton icon-reload-btn" iconCls="icon-reload" id="icon-reset">重置</a>
			
			<gd:btn btncode="GGGL007"><a class="easyui-linkbutton" id="icon-sync">同步默认广告</a></gd:btn>
		</form>
	</div>   
    <div data-options="region:'center'" style="padding-top: 10px;background: #fafafa;">   
        <div class="easyui-layout" data-options="fit:true">   
            <div data-options="region:'west',title:'广告系统',split:true,border:true" style="width:250px;background-color:rgb(234, 234, 228);">
	            <ul id="treeMenu"></ul>
            </div>   
            <div data-options="region:'center'">
            	<table id="advertisementdg" title=""></table>
            </div>   
        </div>   
    </div>
    
    <div id="addAdDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsAdd">
		<div id="dlg-buttonsAdd" style="text-align:center">
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveAdd()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addAdDialog').dialog('close')">关闭</a>
        </div>
    </div>
    
    <div id="editDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsEdit">
		<div id="dlg-buttonsEdit" style="text-align:center">
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveEdit()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editClose()">关闭</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveEditAndPutOn()">上架</a>
        </div>
    </div>
    
    <div id="detailDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsDetail">
		<div id="dlg-buttonsDetail" style="text-align:center">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#detailDialog').dialog('close')">关闭</a>
        </div>
    </div>
    
    <div id="syncDialog" class="easyui-dialog" style="width:600px;height:220px;" closed="true" modal="true" buttons="#dlg-buttonsSync">
		<div id="dlg-buttonsSync" style="text-align:center">
		    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" id="icon-save-btn" onclick="saveSync()">确定</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#syncDialog').dialog('close')">关闭</a>
        </div>
    </div>
</body>
<script type="text/javascript" src="${CONTEXT }js/advertisement/main.js"></script>
<script type="text/javascript">
	function optFormatter(val, row, index){
		var status = row.status;
		var opt = "<gd:btn btncode='GGGL003'><a class='operate' href='javascript:;' onclick='detail("+row.id+")'>查看</a></gd:btn>";
		if(status == 1){
			opt += "<gd:btn btncode='GGGL006'><a class='operate' href='javascript:;' onclick='updateStatus("+row.id+", 2)'>下架</a></gd:btn>";
		}
		else if(status == 2){
			opt += "<gd:btn btncode='GGGL002'><a class='operate' href='javascript:;' onclick='editObj("+row.id+")'>编辑</a></gd:btn>";
			opt += "<gd:btn btncode='GGGL005'><a class='operate' href='javascript:;' onclick='updateStatus("+row.id+", 1)'>上架</a></gd:btn>";
		}
		return opt;
	}
</script>
</html>
var memberArray;
var number;
$(document).ready(function(){
	initValidateLimit();
	initAddAreaTopList();
	memberArray = new Array();
	number = 0;
});

function addCompanyAdd(){
	$('#companyDialog').dialog({'title':'物流公司/车主选择', 'width':600, 'height':350, 'href':CONTEXT+'rule/addCompany'}).dialog('open');
}

$("#selectCompany").click(function(){
	//判断是否选中
	var row = $('#companydg').datagrid("getSelections");
    if($(row).length < 1 ) {
		slideMessage("请选择要操作的数据！");
        return ;
    }else{
    	memberCheck(row);
    	$('#companyDialog').dialog('close');
    }
});

function memberCheck(rows){
	$.each(rows, function(i, item) {
		number = number+1;
		memberCompare(item, number);
    });
}
function memberCompare(item, num){
	var flag = false;
	if(memberArray.length < 1){
		memberArray.push(item);
		memberDisplay(item, num);
		return;
	}
	$.each(memberArray, function(i, item2) {
		if(item2.id == item.id){
			number = number-1;
			flag = true;
			return false;
		}
    });
	if(flag){
		$.messager.alert("添加已存在",item.companyName+"已存在于当前物流规则！","warning");
		//slideMessage(item.userName+"已存在于当前物流规则！");
		return;
	}
	memberArray.push(item);
	memberDisplay(item, num);
}
function memberDisplay(row, num){
	var select_options = "";
    for(var i=1; i<10; i++){
    	select_options = select_options + "<option value='" + i + "'>" +i +"</option>" ;
    }
	$("#showCompanyAdd").append(
			"<tr><td style='text-align: center;'>" + num + "</td>" +
            "<td style='text-align: center;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;' title='" + row.userName + "'>" + row.userName + "</td>" + 
            "<td style='text-align: center;'>" + row.mobile    + "</td>" +
            "<td style='text-align: center;'><select id='memberType_"+row.id+"' name='memberType'>" +
             "<option>请选择</option><option value=3>物流公司</option>"+"<option value=2>车主</option>"+
            "</select></td>" +
            "<td style='text-align: center;'><select id='level_"+row.id+"' name='level'>" +
            select_options+
            "</select></td>" +
            "<td style='text-align: center;'><input type='text' name='totalLimt' id='totalLimt_"+row.id+"' ONBLUR='validayLimt(this)'  validType='length[1,10]' required='true' style='width:40px'/></td>" +
            "<td style='text-align: center;'><input type='text' name='dayLimt' id='dayLimt_"+row.id+"' ONBLUR='validayLimt(this)'  validType='length[1,10]' required='true' style='width:40px'/></td>" +
            "<td style='text-align: center;'><input type='text' id='startTime_"+row.id+"' name='startTime'  onFocus=\"WdatePicker({onpicked:function(){startTime_"+row.id+".focus();},maxDate:'#F{$dp.$D(\\'endTime_"+row.id+"\\')}'})\" onClick=\"WdatePicker({onpicked:function(){startTime_"+row.id+".focus();},maxDate:'#F{$dp.$D(\\'endTime_"+row.id+"\\')}'})\" style='width:70px'/></td>" +
            "<td style='text-align: center;'><input type='text' id='endTime_"+row.id+"' name='endTime'  onFocus=\"WdatePicker({onpicked:function(){endTime_"+row.id+".focus();},minDate:'#F{$dp.$D(\\'startTime_"+row.id+"\\')}'})\" onClick=\"WdatePicker({onpicked:function(){endTime_"+row.id+".focus();},minDate:'#F{$dp.$D(\\'startTime_"+row.id+"\\')}'})\" style='width:70px'/></td>" +
            "<td style='text-align: center;'></td>" +
            "<td style='text-align: center;'></td>" +
            "<td style='text-align: center;'><a style='cursor: pointer;' onclick='deleteMember("+ row.id +")'>删除</a></td></tr>");
}

function validayLimt(limit){
	if(limit.value==""||limit.value==null){
		limit.className="easyui-validatebox validatebox-text validatebox-invalid";
	}else{
		limit.className="";
	}
}

function validayName(name){
	if(name.value!=""||name.value1!=null){
		var url = CONTEXT + "rule/validayRuleName";
		jQuery.post(url, {name:name.value}, function(data) {
			if (data.result>=1) {
				$("#validateName").html("已存在该规则名称!");
			}else{
				$("#validateName").html("");
			}
		})
	}
}

function addOncick(){
	if(!$("#addRuleInfoForm").form('validate')){
		return false;
	}
	if($("#validateName").html()!=""){
		slideMessage("已存在该规则名称!");
		return;
	}
	if($("#provinceIds").val()==null||$("#provinceIds").val()==""){
		$.messager.alert("警告","请选择所在城市省会!","warning");
		return;
	}
	if($("#cityIds").val()==null||$("#cityIds").val()==""){
		$.messager.alert("警告","请选择所在城市!","warning");
		return;
	}
	var result=true;
	$.each(memberArray, function(i, item) {
		if($("#memberType_"+item.id).val()=="请选择"){
			$.messager.alert("警告","请用户类型!","warning");
			result=false;
			return ;
		}
		if($("#totalLimt_"+item.id).val()==null||$("#totalLimt_"+item.id).val()==""){
			$.messager.alert("警告","总分配上限不能为空！","warning");
			result=false;
			return ;
		}
		if($("#dayLimt_"+item.id).val()==null||$("#dayLimt_"+item.id).val()==""){
			$.messager.alert("警告","日分配上限不能为空！","warning");
			result=false;
			return ;
		}
		var dayLimt=$("#dayLimt_"+item.id).val();
		var totalLimt=$("#totalLimt_"+item.id).val();
		if(parseInt(dayLimt)>parseInt(totalLimt)){
			$.messager.alert("警告","日分配上限不能超出货源分配总数！","warning");
			result=false;
			return ;
		}
		if($("#startTime_"+item.id).val()==null||$("#startTime_"+item.id).val()==""){
			$.messager.alert("警告","分配开始时间不能为空！","warning");
			result=false;
			return ;
		}
		if($("#endTime_"+item.id).val()==null||$("#endTime_"+item.id).val()==""){
			$.messager.alert("警告","分配结束时间不能为空！","warning");
			result=false;
			return ;
		}
    });
	if(result){
		if(memberArray.length<1){
			$.messager.alert("警告","当前物流规则内至少存在一个物流公司！","warning");
			return;
		}else{
			saveRuleInfo();
		}
	}

}


function deleteMember(id){
	$("#showCompanyAdd").empty();
	number = 0;
	var str  = 	"<tr><th colspan='4'>使用以上规则的公司/车主</th><th colspan='5' style='text-align: right'><button onclick='addCompanyAdd();'>添加公司/车主</button></th></tr>"+
				"<tr><th style='text-align: center; word-break:keep-all;padding:0 5px;'>序号</th>" +
				"<th style='text-align: center; word-break:keep-all;padding:0 5px; white-space:nowrap;'>公司/车主</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>电话</th>" +
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>用户类型</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>优先级</th>" +
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>总分配上限</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>日分配上限</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>分配开始时间</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>分配结束时间</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>已使用日配额</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>已使用总配额</th>"+
				"<th style='text-align: center; word-break:keep-all;padding:0 5px;'>操作</th></tr>";
	$("#showCompanyAdd").html(str);
	$.each(memberArray, function(i, item) {
		if(item.id == id){
			memberArray.splice($.inArray(item, memberArray), 1);
			return false;
		}
	});
	$.each(memberArray, function(i, item){
		number = i+1;
		memberDisplay(item,i+1);
	});
}

function saveRuleInfo(){
	var url = CONTEXT + "rule/saveAdd";
	var ids=new Array();
	var levels = new Array();
	var memberType = new Array();
	var totalLimt = new Array();
	var dayLimt = new Array();
	var startTime = new Array();
	var endTime = new Array();
	$.each(memberArray, function(i, item) {
		var level = $("#level_"+item.id).val();
		var memberTypes=$("#memberType_"+item.id).val();
		var totalLimts=$("#totalLimt_"+item.id).val();
		var dayLimts=$("#dayLimt_"+item.id).val();
		var startTimes = $("#startTime_"+item.id).val();
		var endTimes = $("#endTime_"+item.id).val();
		ids.push(item.id);
		levels.push(level);
		memberType.push(memberTypes);
		totalLimt.push(totalLimts);
		dayLimt.push(dayLimts);
		startTime.push(startTimes);
		endTime.push(endTimes);
	});
	jQuery.post(url, $('#addRuleInfoForm').serialize(), function(data) {
		if (data.success == true) {
			slideMessage("保存成功！");
			$("#ruleInfodg").datagrid('load');
			$('#addDialog').dialog('close');
			if(memberArray.length>0){
				if(ids.length>0){
					insertRuleLogistic(ids.join(), data.result, levels.join(),memberType.join(),totalLimt.join(),dayLimt.join(),startTime.join(),endTime.join(),0);
				}
			}
		} else {
			warningMessage(data.result);
			return;
		}
	});
}

function insertRuleLogistic(ids, ruleInfoId, levels,memberType,totalLimt,dayLimt,startTime,endTime,onOff){
	var url = CONTEXT + "rule/insertRuleLogistic";
	console.log("优先级："+levels);
	console.log("会员类型:"+memberType);
	console.log("日上限:"+dayLimt);
	jQuery.post(url, {ids:ids, ruleInfoId:ruleInfoId, levels:levels,memberType:memberType,totalLimt:totalLimt,dayLimt:dayLimt,startTime:startTime,endTime:endTime,onOff:onOff}, function(data) {
		
	});
}


function initValidateLimit(){
	$.extend($.fn.validatebox.defaults.rules, {
		dayLimtFuc:{
			validator: function (value) {
	            return /^[1-9][0-9]{0,10}$/.test(value);
			},
	        message: '请输入大于零的自然数！' 
		},
		totalLimtFuc:{
			validator: function (value) {
				 return /^[1-9][0-9]{0,10}$/.test(value);
			},
	        message: '请输入大于零的自然数！' 
		}
	});
}


function initAddAreaTopList(){
	var areaTopList = queryAreaTopList();
	$.each(areaTopList, function(i, item){
		$("#provinceIds").append("<option value='"+item.areaID+"'>"+item.area+"</option>");
	}); 
}

$("#provinceIds").change(function(){
	$("#cityIds").html("<option value=''>请选择城市</option>");
	$("#areaIds").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#cityIds").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});

$("#cityIds").change(function(){
	$("#areaIds").html("<option value=''>请选择区/县</option>");
	var parentId = $(this).val();
	var areaList = queryAreaChildList(parentId);
	$.each(areaList, function(i, item){
		$("#areaIds").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
	});
});


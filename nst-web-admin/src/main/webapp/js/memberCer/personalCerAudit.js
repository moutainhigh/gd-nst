$(function(){
	var hidShow = $('#hidShow').val();
	$('#auditTab').tabs({    
	    border:false,    
	    onSelect:function(title,index){    
	       if(index==1){
	    	   $("#icon-save-btn").hide();
	    	   var cerId= $("#hidCerId").val();
	    	   if(cerId){
	    		   loadAuditData(cerId);
	    	   }	    	  
	       }else{	    	   
	    		if(hidShow){
	    			$("#icon-save-btn").hide();
	    		}else{
	    			$("#icon-save-btn").show();
	    		}	    
	       }
	    }    
	});
	
	if(hidShow){
		$("#icon-save-btn").hide();
	}	
	
	$("#passRadio").click(function(){
		// 清除上次填写的文本框（审核意见）内容
		$("#auditOpinion").val("");
		
		if($(this).attr("checked")) {
			$("#noPassReasonSelect").attr("disabled","disabled");
		}
	});
	
	$("#noPassRadio").click(function(){
		// 清除上次填写的文本框（审核意见）内容
		$("#auditOpinion").val("");
		
		if($(this).attr("checked")) {
			$("#noPassReasonSelect").removeAttr("disabled");
			
			// 填充文本框（审核意见）内容
			var opinion = getReason($("#noPassReasonSelect").val());
			$("#auditOpinion").val(opinion);
		}
	});
	
	$("#noPassReasonSelect").change(function(){
		var opinion = getReason($(this).val());
		$("#auditOpinion").val(opinion);
	});
});

function setTitle(object,title){
	object.attr("title",title);
}

function clearTitle(object){
	object.attr("title","");
}

function selectRadio(object1,object2){
	if($(object1).attr("checked")=='checked'){
		object2.attr("checked",false);
		$("#hidStatus").val($(object1).val());
	}
}

function loadAuditData(cerId){
	$('#auditdg').datagrid({
		url:CONTEXT+'memberCer/personal/auditRecord/query/'+cerId, 
		height: 'auto', 
		nowrap:true,
		pageSize:50,
		rownumbers:true,
		pagination:false,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'cerStatus',title:'审核结果',width:100,align:'center',formatter:function(value,row,index){
			    if(value=='1'){
					return '通过';
				}else if(value=='2'){
					return '驳回';
				}
			}},
			{field:'auditOpinion',title:'审核意见',width:100,align:'center'},
			{field:'createUserId',title:'审核员',width:100,align:'center'},
			{field:'createTime',title:'审核时间',width:100,align:'center'}
		]]
	}); 
}

function getReason(key) {
	var map = {
		"1" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的身份证正反面照片，需显示姓名、证件号、时间期限。",	
		"2" : "尊敬的用户：您填写的信息与身份证照片上的信息不一致，请核实姓名或证件号后重新提交认证。",
		"3" : "尊敬的用户：您提交的身份证已不在有效期，请重新提交有效期内的身份证照片。",
		"4" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的行驶证照片，需显示清晰的车牌号。",
		"5" : "尊敬的用户：您填写的车牌号与认证图片上车牌号不一致，请核实后重新提交认证。",
		"6" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的车头或车尾照片，需清晰显示车牌号。",
		"7" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的身份证照片加上行驶证/车头照/车尾照。",
		"8" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的营业执照照片，需显示姓名、证件号、时间期限。",
		"9" : "尊敬的用户：您提交的身份证已不在有效期，请重新提交有效期内的营业执照照片。",
		"10" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的与填写信息一致的名片照片。",
		"11" : "尊敬的用户：为确保认证信息真实、有效，请上传清晰的与填写信息一致的门头照片。",
		"12" : "尊敬的用户：请上传清晰的身份证照片，加上营业执照/名片照/门头照/老板与业务员合影照。"
	};
	return map[key];
} 
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
	})
	if(hidShow){
		$("#icon-save-btn").hide();
	}
})

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
	//数据加载
	$('#auditdg').datagrid({
		url:CONTEXT+'memberCer/company/auditRecord/query/'+cerId,
		//width: 1000, 
		height: 'auto', 
		nowrap:true,
		pageSize:50,
		rownumbers:true,
		pagination:true,
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
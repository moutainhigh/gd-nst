var provinceId ;
var cityId ;
var areaId ;
var address ;

var originProvinceId ;
var originCityId ;
var originAreaId ;

$(function(){
	$('#tb-edit').tabs({
	    border:false,
	    onSelect:function(title){
	    	var params = {
	    			memberId:memberId
	    		};
			if(title=="车辆信息"){
				loadCarListData(params);
			}else if(title=="货源信息"){
				loadSourceShippersListData(params);
			}else if(title=="常跑线路"){
				loadMemberLineListData(params);
			}else if(title=="货运订单"){
				loadOrderInfoListData(params);
			}else if(title=="信息订单"){
				loadOrderAgentListData(params);
			}else if(title=="物流规则"){
				loadRuleInfoListData(params);
			}
	    }
	});
	initValidateRules();
	initAddress();
	$("#icon-save-btn").click(function(){
		var val_appoint = $('input[name="appoint"]:checked ').val();
		if(val_appoint==0||val_appoint=='0'){
		  if($("#memberIdLogistic").length==0||$("#memberIdLogistic").val()==""){
			  $("#memberIdLogisticName").focus();
			  slideMessage("请选择指定物流公司！");
			  return;
		  }
		  if($("#assignUserType").val()==""){
			  slideMessage("请选择指派的用户类型！");
			  return;
		  }
		}
		if($("#userName").length==0||$("#userName").val()==""){
			  $("#userName").focus();
			 slideMessage("请填写联系人姓名！");
			  return;
		}
		if($("#mobile").length==0||("#mobile").length==""){
			  $("#mobile").focus();
			 slideMessage("请填写联系人手机！");
			  return;
		}
		if ($('#updateForm').form('validate')) {
			var url = CONTEXT + "memberInfo/memberInfoUpdate";
			jQuery.post(url, $('#updateForm').serialize(), function(data) {
				if (data.msg == "success") {
					slideMessage("修改成功！");
					// 刷新父页面列表
					$("#memberInfodg").datagrid('reload');
					$('#win').dialog('close');
				} else {
					warningMessage(data.msg);
					// 刷新父页面列表
					$("#memberInfodg").datagrid('reload');
					$('#win').dialog('close');
					return;
				}
			});
		}
	});	
	 $('.appoint').change(function() { 
		 var val_appoint = $('input[name="appoint"]:checked ').val();
		 var flag=$("#appointFlag").val();
		if((val_appoint==0||val_appoint=='0')&&(flag==1||flag=='1')){
			$("#addCompanyAdd").show();
		}else{
			$("#addCompanyAdd").hide();
			if(flag==1||flag=='1'){
			$("#memberIdLogisticName").val("");
			$("#memberIdLogistic").val("");
			}
		}
	 });
	
	$("#addCompanyAdd").click(function(){
		$('#companyDialog').dialog({'title':'物流公司/车主选择', 'width':600, 'height':350, 'href':CONTEXT+'rule/addCompany'}).dialog('open');
	});
	

	$("#memberSelectCompany").click(function(){
		//判断是否选中
		var row = $('#companydg').datagrid("getSelections");
	    if($(row).length < 1 ) {
			slideMessage("请选择要操作的数据！");
	        return ;
	    }else if($(row).length > 1 ){
	    	slideMessage("只能选择其中一家物流公司！");
	        return ;
	    }else{
	    	memberCheck(row);
	    	$('#companyDialog').dialog('close');
	    }
	});

	function memberCheck(rows){
		$("#memberIdLogisticName").val(rows[0].userName);
		$("#memberIdLogistic").val(rows[0].id);
	}

});
//车辆信息
function loadCarListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#carDataGrid').datagrid({
		url:CONTEXT+'memberCar/queryPage',
		queryParams: queryParams,
		pageList:[10,20,50,100],
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'carNumber',title:'车牌号',width:100,align:'left'},
			{field:'carTypeStr',title:'车辆类型',width:100,align:'center'},
			{field:'carLength',title:'车长(米)',width:100,align:'center'},
			{field:'load',title:'载重(吨)',width:100,align:'center'}
		]]
	}); 
}
//常跑线路
function loadMemberLineListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#memberLineDataGrid').datagrid({ 
		url:CONTEXT+'memberLine/queryPage',
		queryParams: queryParams,
		pageList:[10,20,50,100],
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'sDetailStr',title:'出发地',width:100,align:'left'},
			{field:'eDetailStr',title:'目的地',width:100,align:'left'},
			{field:'sourceTypeStr',title:'线路类型',width:100,align:'left'},
			{field:'createTime',title:'发布时间',width:100,align:'center'},
			{field:'isDeletedStr',title:'线路状态',width:100,align:'center'}
		]]
	}); 
}





//货源信息
function loadSourceShippersListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#sourceShipperdg').datagrid({
		url:CONTEXT+'sourceShipper/queryPage',
		queryParams: queryParams,
		pageList:[10,20,50,100],
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'createTime',title:'发布时间',width:100,align:'center'},
			{field:'nstRuleStr',title:'货源类型',width:100,align:'center'},
			{field:'sourceTypeStr',title:'线路类型',width:100,align:'center'},
			{field:'sDetailStr',title:'发货地',width:100,align:'left'},
			{field:'eDetailStr',title:'目的地',width:100,align:'left'},
			{field:'totalWeight',title:'货物重量',width:100,align:'center', formatter:totalWeightFormatter},
			{field:'sendGoodsTypeStr',title:'发货方式',width:100,align:'center'},
			{field:'freight',title:'意向运费',width:100,align:'center',formatter:freightFormatter},
			{field:'clientsStr',title:'发布来源',width:100,align:'center'},
			{field:'sourceStatusStr',title:'货源状态',width:100,align:'center',formatter:sourceStatusFormatter}
		]]
	}); 
}

function sourceStatusFormatter(val, row, index){
	if(row.isDeleted == "1"){
		return "已删除"
	}
	return val;
}
function freightFormatter(val){
	if(val != undefined && val == "0"){
		return "面议";
	}
	return val;
}
function totalWeightFormatter(val){
	if(val != undefined && val != ""){
		return val + "吨";
	}
	return val;
}
//货运订单
function loadOrderInfoListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#orderInfoDataGrid').datagrid({
		url:CONTEXT+'orderInfo/queryPage',
		queryParams: queryParams,
		pageList:[10,20,50,100],
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'orderNo',title:'货运订单号',width:100,align:'center'},
			{field:'sDetailStr',title:'发货地',align:'left'},
			{field:'eDetailStr',title:'目的地',align:'left'},
			{field:'shipperName',title:'发运人姓名',width:100,align:'left'},
			{field:'shipperMobile',title:'发运人手机',width:100,align:'center'},
			{field:'driverName',title:'承运人姓名',width:100,align:'left'},
			{field:'driverMobile',title:'承运人手机',width:100,align:'center'},
			{field:'sourceTypeStr',title:'订单类型',width:100,align:'center'},
			{field:'sendGoodsTypeStr',title:'发货方式',width:100,align:'center'},
			{field:'createTime',title:'订单生成时间',width:100,align:'center'},
			{field:'orderStatusStr',title:'订单状态',width:100,align:'center'},
			{field:'payStatusStr',title:'支付状态',width:100,align:'center'},
			{field:'payMoney',title:'订单金额',width:100,align:'center'},
		]]
	}); 
}/*
function sDetailFormatter(val, row, index){
	var content = "";
	if(val != undefined){
		content += val;
	}
	if(row.sDetailedAddress != undefined){
		content += row.sDetailedAddress;
	}
	return content;
}

function eDetailFormatter(val, row, index){
	var content = "";
	if(val != undefined){
		content += val;
	}
	if(row.eDetailedAddress != undefined){
		content += row.eDetailedAddress;
	}
	return content;
}*/

//信息订单
function loadOrderAgentListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#orderAgentDataGrid').datagrid({
		url:CONTEXT+'orderAgent/queryPage',
		queryParams: queryParams,
		pageList:[10,20,50,100],
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'orderNo',title:'订单号',width:100,align:'center'},
			{field:'logisticName',title:'物流公司名称',width:100,align:'left'},
			{field:'logisticMobile',title:'物流公司电话',width:100,align:'center'},
			{field:'driverName',title:'车主姓名',width:100,align:'left'},
			{field:'driverMobile',title:'车主手机',width:100,align:'center'},
			{field:'confirmTime',title:'车主接单时间',width:100,align:'center'},
			{field:'logisticTime',title:'物流公司确认时间',width:100,align:'center'},
			{field:'orderStatusStr',title:'订单状态',width:100,align:'center'},
			{field:'payStatusStr',title:'支付状态',width:100,align:'center'},
			{field:'payMoney',title:'订单金额',width:100,align:'center'}
		]]
	}); 
}

//物流规则 （接收开关）
function loadRuleInfoListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	var url = CONTEXT + "memberInfo/queryMemberRuleOnoffDetail/"+queryParams.memberId;
	
	$.ajax({
        url:url,
        data:queryParams,
        type:'post',
        dataType:'json',
        success:function(data){
        	if (data.OnOff == "1") {//是否开启(1:关闭;2:开启)',
    			$("#ruleInfoDataGrid").html("接收分配货源：关闭");
    		} else if(data.OnOff == "2") {
    			$("#ruleInfoDataGrid").html("接收分配货源：开启");
    		}
        }
	}
	);
	/* jQuery.post(url,queryParams, function(data) {
		if (data.OnOff == "1") {//是否开启(1:关闭;2:开启)',
			$("#ruleInfoDataGrid").html("接收分配货源：关闭");
		} else if(data.OnOff == "2") {
			$("#ruleInfoDataGrid").html("接收分配货源：开启");
		}
	}); */
	
	//数据加载
/* 	$('#ruleInfoDataGrid').datagrid({
		url:CONTEXT+'rule/pageQuery',
		queryParams: queryParams,
		pageList:[10,20,50,100],
		height: 'auto', 
		nowrap:true,
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'name',title:'规则名称',width:100,align:'center'},
			{field:'startTime',title:'规则生效日期',width:100,align:'center'},
			{field:'endTime',title:'规则失效日期',width:100,align:'center'},
			{field:'dayLimt',title:'分配货源上限',width:100,align:'center'},
			{field:'totalLimt',title:'使用规则物流公司数量',width:100,align:'center'},
			{field:'userName',title:'创建人',width:100,align:'center'},
			{field:'createTime',title:'创建时间',width:100,align:'center'}
		]]
	});  */
}

//地址
function initAddress(){
		originProvinceId = $("#originProvince_typeIn").val();
		originCityId = $("#originCity_typeIn").val();
		originAreaId = $("#originArea_typeIn").val();
		updateOriginProvince();
	}
var doingInitOriginPlace = true ;
//产地省
function updateOriginProvince(){
	//省
	$('#originProvince_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryProvince' ,
		onSelect:function(record){
			$('#originCity_comp').next(".combo").show();
			$('#originArea_comp').next(".combo").show();
			$('#originProvince_typeIn').val(record.areaID);
		//	$('#originProvince_comp').combobox('getValue')获取当前选中的值
			var province=$('#originProvince_comp').combobox('getText');//获取当前选中的文字
			$("#province").val(province);
			updateOriginCity(record.areaID);
		},
		onLoadSuccess : function(){
			//省份的数据肯定存在
			var data = $('#originProvince_comp').combobox('getData');
			if (doingInitOriginPlace){
				if(originProvinceId){
					$('#originProvince_comp').combobox('select', originProvinceId);
				}else{
					$("#originProvince_comp").combobox('setValue','');
					$("#originProvince_comp").combobox('setText','请选择省');
				//	$('#originProvince_comp').combobox('select', data[0].areaID);
				}
			}else{
				$('#originProvince_comp').combobox('select', data[0].areaID);
			} 
		}
	});
}

//产地-市
function updateOriginCity(provinceId){
	//
	$('#originCity_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryChildArea/'+ provinceId,
		onSelect:function(record){
			var data = $('#originCity_comp').combobox('getData');
			if (data.length > 0) {
				$('#originCity_typeIn').val(record.areaID);
				var city=$('#originCity_comp').combobox('getText');//获取当前选中的文字
				$("#city").val(city);
				updateOriginArea(record.areaID);
			}
		},
		onLoadSuccess : function(){
			var data = $('#originCity_comp').combobox('getData');
			//当市级数据不存在时, 则为港澳台;
			if (data.length > 0) {
				if (doingInitOriginPlace){
					if(originCityId){
						$('#originCity_comp').combobox('select', originCityId);
					}else{
						 $("#originCity_comp").combobox('setValue','');
						$("#originCity_comp").combobox('setText','请选择市'); 
						/*$('#originCity_comp').combobox('select', data[0].areaID);*/
					}
				}else{
					$('#originCity_comp').combobox('select', data[0].areaID);
				}
			}else {//港澳台
				$('#originCity_typeIn').val(0);
				$('#originArea_typeIn').val(0);
			} 
			
		}
	});
}

//产地-区/县
function updateOriginArea(cityId){
	//
	$('#originArea_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryChildArea/' + cityId,
		onSelect:function(record){
			$('#originArea_typeIn').val(record.areaID);
			var area=$('#originArea_comp').combobox('getText');//获取当前选中的文字
			$("#area").val(area);
		},
		onLoadSuccess : function(){
		var data = $('#originArea_comp').combobox('getData');
			if (doingInitOriginPlace){
				doingInitOriginPlace = false;
				if(originAreaId){
					$('#originArea_comp').combobox('select', originAreaId);
				}else{
					$("#originArea_comp").combobox('setValue','');
					$("#originArea_comp").combobox('setText','全市');
					var areaRetrun=$("#area").val();
					if(areaRetrun=="null"|areaRetrun==""){
						$("#area").val("全市");
					}
					/* 	$('#originArea_comp').combobox('select', data[0].areaID);*/
				}
			}else{
				$('#originArea_comp').combobox('select', data[0].areaID);
			} 
		},loadFilter:function(data){
            var o = [{'areaID':'','area':'全市'}];
            $('#add #originArea_comp').combobox("select",0);
            $("#area").val("全市");
            $('#originArea_typeIn').val("");
            return o.concat(data);
        }
	});
}



function initValidateRules(){
	$.extend($.fn.validatebox.defaults.rules, {
		userMobile:{
			validator: function (value) {
	            return /^1[3|4|5|7|8]\d{9}$/.test(value);
			},
	        message: '请输入正确的手机号码。' 
		}
	});
}





var provinceId ;
var cityId ;
var areaId ;
var address ;

var originProvinceId ;
var originCityId ;
var originAreaId ;

$(function(){
	initAddress();
});
//车辆信息


//地址
function initAddress(){
		originProvinceId = $("#add #originProvince_typeIn").val();
		originCityId = $("#add #originCity_typeIn").val();
		originAreaId = $("#add #originArea_typeIn").val();
		updateOriginProvince();
	}
var doingInitOriginPlace = true ;
//产地省
function updateOriginProvince(){
	//省
	$('#add #originProvince_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryProvince' ,
		onSelect:function(record){
			$('#add #originCity_comp').next(".combo").show();
			$('#add #originProvince_typeIn').val(record.areaID);
		//	$('#add #originProvince_comp').combobox('getValue')获取当前选中的值
			var province=$('#add #originProvince_comp').combobox('getText');//获取当前选中的文字
			$("#add #province").val(province);
			if(record.areaID==0){
				$('#add #originCity_comp').next(".combo").hide();
				$('#add #originCity_typeIn').val('0');
				$("#add #city").val('默认');
			}else{
				$('#add #originCity_comp').next(".combo").show();
			updateOriginCity(record.areaID);}
		},loadFilter:function(data){
            var o = [{'areaID':'0','area':'默认'}];
            $('#add #originProvince_comp').combobox("select",0);
            return o.concat(data);
        },
		onLoadSuccess : function(da){
			//省份的数据肯定存在
			var data = $('#add #originProvince_comp').combobox('getData');
			if (doingInitOriginPlace){
				if(originProvinceId){
					$('#add #originProvince_comp').combobox('select', originProvinceId);
				}else{
					$("#add #originProvince_comp").combobox('setValue','');
					$("#add #originProvince_comp").combobox('setText','请选择省');
					$("#add #originCity_comp").combobox('setText','请选择市'); 
				//	$('#add #originProvince_comp').combobox('select', data[0].areaID);
				}
			}else{
				$('#add #originProvince_comp').combobox('select', data[0].areaID);
			} 
		}
	});
}
//产地-市
function updateOriginCity(provinceId){
	//
	$('#add #originCity_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryChildArea/'+ provinceId,
		onSelect:function(record){
			var data = $('#add #originCity_comp').combobox('getData');
			if (data.length > 0) {
				$('#add #originCity_typeIn').val(record.areaID);
				var city=$('#add #originCity_comp').combobox('getText');//获取当前选中的文字
				$("#add #city").val(city);
			}
		},
		onLoadSuccess : function(){
			var data = $('#add #originCity_comp').combobox('getData');
			//当市级数据不存在时, 则为港澳台;
			if (data.length > 0) {
				if (doingInitOriginPlace){
					if(originCityId){
						$('#add #originCity_comp').combobox('select', originCityId);
					}else{
					//	 $("#add #originCity_comp").combobox('setValue','');
					/*	$("#add #originCity_comp").combobox('setText','请选择市'); */
						$('#add #originCity_comp').combobox('select', data[0].areaID);
					}
				}else{
					$('#add #originCity_comp').combobox('select', data[0].areaID);
				}
			}else {//港澳台
				$('#add #originCity_typeIn').val(0);
				$('#add #originArea_typeIn').val(0);
			} 
			
		}
	});
}







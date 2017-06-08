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
		originProvinceId = $("#edit #originProvince_typeIn").val();
		originCityId = $("#edit #originCity_typeIn").val();
		updateOriginProvince();
	}
var doingInitOriginPlace = true ;
//产地省
function updateOriginProvince(){
	//省
	$('#edit #originProvince_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryProvince' ,
		onSelect:function(record){
			$('#edit #originCity_comp').next(".combo").show();
			$('#edit #originProvince_typeIn').val(record.areaID);
		//	$('#edit #originProvince_comp').combobox('getValue')获取当前选中的值
			var province=$('#edit #originProvince_comp').combobox('getText');//获取当前选中的文字
			$("#edit #province").val(province);
			if(record.areaID==0){
				$('#edit #originCity_comp').next(".combo").hide();
				$('#edit #originCity_typeIn').val('0');
				$("#edit #city").val('默认');
			}else{
				$('#edit #originCity_comp').next(".combo").show();
			    updateOriginCity(record.areaID);
			}
		},loadFilter:function(data){
            var o = [{'areaID':'0','area':'默认'}];
            $('#edit #originProvince_comp').combobox("select",0);
            return o.concat(data);
        },
		onLoadSuccess : function(){
			//省份的数据肯定存在
			var data = $('#edit #originProvince_comp').combobox('getData');
			if (doingInitOriginPlace){
				if(originProvinceId){
					$('#edit #originProvince_comp').combobox('select', originProvinceId);
				//	updateOriginCity($('#edit #originProvince_comp').combobox('getValue'));
				}else{
					$("#edit #originProvince_comp").combobox('setValue','');
					$("#edit #originProvince_comp").combobox('setText','请选择省');
					$("#edit #originCity_comp").combobox('setText','请选择市'); 
				//	$('#edit #originProvince_comp').combobox('select', data[0].areaID);
				}
			}else{
				$('#edit #originProvince_comp').combobox('select', data[0].areaID);
			} 
		}/*,onLoadError:function(){
			
		}*/
	});
}
//产地-市
function updateOriginCity(provinceId){
  if(provinceId==0){
	  originCityId=undefined;
	  $('#edit #originCity_comp').next(".combo").hide(); 
  }
	//
	$('#edit #originCity_comp').combobox({
		valueField:'areaID',
		textField:'area',
		editable:false ,
		url: CONTEXT+'memberInfo/queryChildArea/'+ provinceId,
		onSelect:function(record){
			var data = $('#edit #originCity_comp').combobox('getData');
			if (data.length > 0) {
				$('#edit #originCity_typeIn').val(record.areaID);
				var city=$('#edit #originCity_comp').combobox('getText');//获取当前选中的文字
				$("#edit #city").val(city);
			}
		},
		onLoadSuccess : function(){
			var data = $('#edit #originCity_comp').combobox('getData');
			//当市级数据不存在时, 则为港澳台;
			if (data.length > 0) {
				if (doingInitOriginPlace){
					if(originCityId){
						$('#edit #originCity_comp').combobox('select', originCityId);
						originCityId=undefined;
					}else{
					//$("#edit #originCity_comp").combobox('setValue',data[0].areaID);
					//	$("#edit #originCity_comp").combobox('setText','请选择市'); 
					$('#edit #originCity_comp').combobox('select', data[0].areaID);
					}
				}else{
					$('#edit #originCity_comp').combobox('select', data[0].areaID);
				}
			}else {//港澳台
				$('#edit #originCity_typeIn').val(0);
				$('#edit #originArea_typeIn').val(0);
			} 
			
		}
	});
}



/*
function initValidateRules(){
	$.extend($.fn.validatebox.defaults.rules, {
		userMobile:{
			validator: function (value) {
	            return /^1[3|4|5|7|8]\d{9}$/.test(value);
			},
	        message: '请输入正确的手机号码。' 
		}
	});
}*/





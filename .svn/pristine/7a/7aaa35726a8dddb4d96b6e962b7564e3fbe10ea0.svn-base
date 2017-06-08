function editInitAreaTopList(){
	var preSProvinceId = $("#pre_sProvinceId").val();
	var preEProvinceId = $("#pre_eProvinceId").val();
	var areaTopList = queryAreaTopList();
	$.each(areaTopList, function(i, item){
		if(preSProvinceId == item.areaID){
			$("#editSourceShipperForm #sProvinceId").append("<option value='"+item.areaID+"' selected>"+item.area+"</option>");   
		}else{
			$("#editSourceShipperForm #sProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
		}
		if(preEProvinceId == item.areaID){
			$("#editSourceShipperForm #eProvinceId").append("<option value='"+item.areaID+"' selected>"+item.area+"</option>"); 
		}else{
			$("#editSourceShipperForm #eProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
		}
	}); 
	//二级区域目录初始化
	var preSCityId = $("#pre_sCityId").val();
	var preECityId = $("#pre_eCityId").val();
	if(preSProvinceId != ''){
		var sCityList = queryAreaChildList(preSProvinceId);
		$.each(sCityList, function(i, item){
			if(preSCityId == item.areaID){
				$("#editSourceShipperForm #sCityId").append("<option value='"+item.areaID+"' selected>"+item.area+"</option>");   
			}else{
				$("#editSourceShipperForm #sCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
			}
		});
	}
	if(preEProvinceId != ''){
		var eCityList = queryAreaChildList(preEProvinceId);
		$.each(eCityList, function(i, item){
			if(preECityId == item.areaID){
				$("#editSourceShipperForm #eCityId").append("<option value='"+item.areaID+"' selected>"+item.area+"</option>");   
			}else{
				$("#editSourceShipperForm #eCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
			}
		});
	}
	//三级区域目录初始化
	var preSAreaId = $("#pre_sAreaId").val();
	var preEAreaId = $("#pre_eAreaId").val();
	if(preSCityId != ''){
		var sAreaList = queryAreaChildList(preSCityId);
		$.each(sAreaList, function(i, item){
			if(preSAreaId == item.areaID){
				$("#editSourceShipperForm #sAreaId").append("<option value='"+item.areaID+"' selected>"+item.area+"</option>");   
			}else{
				$("#editSourceShipperForm #sAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
			}
		});
	}
	if(preECityId != ''){
		var eAreaList = queryAreaChildList(preECityId);
		$.each(eAreaList, function(i, item){
			if(preEAreaId == item.areaID){
				$("#editSourceShipperForm #eAreaId").append("<option value='"+item.areaID+"' selected>"+item.area+"</option>");   
			}else{
				$("#editSourceShipperForm #eAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
			}
		});
	}
}
$(function(){
	editInitAreaTopList();
	
	$("#editSourceShipperForm #sProvinceId").change(function(){
		$("#editSourceShipperForm #sCityId").html("<option value=''>请选择城市</option>");
		$("#editSourceShipperForm #sAreaId").html("<option value=''>请选择区/县</option>");
		var parentId = $(this).val();
		var areaList = queryAreaChildList(parentId);
		$.each(areaList, function(i, item){
			$("#editSourceShipperForm #sCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
		});
	});
	
	$("#editSourceShipperForm #eProvinceId").change(function(){
		$("#editSourceShipperForm #eCityId").html("<option value=''>请选择城市</option>");
		$("#editSourceShipperForm #eAreaId").html("<option value=''>请选择区/县</option>")
		var parentId = $(this).val();
		var areaList = queryAreaChildList(parentId);
		$.each(areaList, function(i, item){
			$("#editSourceShipperForm #eCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
		});
	});
	
	$("#editSourceShipperForm #sCityId").change(function(){
		$("#editSourceShipperForm #sAreaId").html("<option value=''>请选择区/县</option>");
		var parentId = $(this).val();
		var areaList = queryAreaChildList(parentId);
		$.each(areaList, function(i, item){
			$("#editSourceShipperForm #sAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
		});
	});
	
	$("#editSourceShipperForm #eCityId").change(function(){
		$("#editSourceShipperForm #eAreaId").html("<option value=''>请选择区/县</option>");
		var parentId = $(this).val();
		var areaList = queryAreaChildList(parentId);
		$.each(areaList, function(i, item){
			$("#editSourceShipperForm #eAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
		});
	});
});
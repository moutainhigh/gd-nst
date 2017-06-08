var ruleLineCtrl  = new NSTAdmin.client();

NSTAdmin.client.prototype.ruleLineMain = {
	disableExport : false,
	/**
	 * 请求地址URL
	 */
	urlItems : {
		queryByPageURL : CONTEXT+'ruleLine/queryPage',
		queryTopAreaURL : CONTEXT+'area/queryTopList',
		queryChildAreaURL : CONTEXT+'area/queryChildList',
		exportCheckURL : CONTEXT+'ruleLine/exportCheck',
		exportDataURL : CONTEXT+'ruleLine/export'
	},
	/**
	 * 加载分页数据
	 */
	loadListData : function(queryParams) {
		_this = this;
		queryParams = !queryParams ? {} : queryParams;
		//数据加载
		$('#ruleLineDg').datagrid({
			url:_this.urlItems.queryByPageURL,
			queryParams: queryParams,
			height: 'auto', 
			nowrap:true,
			toolbar:'#ruleLineTb',
			pageSize:50,
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			fit:true,
			singleSelect:true,
			columns:[[
				{field:'id',title:'',width:50,checkbox:true},
				{field:'publisher',title:'用户姓名',width:100,align:'left'},
				{field:'phone',title:'用户手机',width:100,align:'center'},
				{field:'sDetailStr',title:'起始地',width:100,align:'left'},
				{field:'eDetailStr',title:'目的地',width:100,align:'left'},
				{field:'createTimeStr',title:'添加时间',width:100,align:'center'},
				{field:'isDeletedStr',title:'线路状态',width:100,align:'center'}
			]],
			onLoadSuccess: function (data) {
				$(this).datagrid("fixRownumber");
			}
		}); 
	},
	/**
	 * 分页初始化
	 */
	initPageListData : function() {
		_this = this;
		_this.loadListData(null);
		$("#ruleLineDg").datagrid("getPager").pagination({
			pageList: [10,20,50,100]
		});
	},
	/**
	 * 获取省份列表
	 * @returns
	 */
	queryTopArea : function () {
		var result;
		$.ajax({
			async: false, 
			url: _this.urlItems.queryTopAreaURL,
			type:'post',
			dataType:"json",
			success : function(data){
				result = data.result;
			}
		});
		return result;
	},
	/***
	 * 根据parentId获取下级区域列表
	 * @param parentId
	 * @returns
	 */
	queryAreaChildList : function (parentId){
		var result;
		$.ajax({
			async: false, 
			url: _this.urlItems.queryChildAreaURL + '/' + parentId,
			type:'post',
			dataType:"json",
			success : function(data){
				result = data.result;
			}
		});
		return result;
	},
	/***
	 * 初始化省份列表
	 */
	initAreaTopList : function (){
		_this = this;
		var areaTopList = _this.queryTopArea();
		$.each(areaTopList, function(i, item){
			$("#sProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>");   
			$("#eProvinceId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
		}); 
	},
	/**
	 * 获取查询参数
	 * @returns {___anonymous2668_3926}
	 */
	getSearchParams : function (){
		var params = {
				"sProvinceId" : $("#ruleLineSearchForm #sProvinceId").val(),
				"sCityId" : $("#ruleLineSearchForm #sCityId").val(),
				"sAreaId" : $("#ruleLineSearchForm #sAreaId").val(),
				"eProvinceId" : $("#ruleLineSearchForm #eProvinceId").val(),
				"eCityId" : $("#ruleLineSearchForm #eCityId").val(),
				"eAreaId" : $("#ruleLineSearchForm #eAreaId").val(),
				"publisher" : $("#ruleLineSearchForm #publisher").val(),
				"phone" : $("#ruleLineSearchForm #phone").val(),
				"createTimeStartDate" : $("#ruleLineSearchForm #createTimeStartDate").val(),
				"createTimeEndDate" : $("#ruleLineSearchForm #createTimeEndDate").val(),
				"isDeleted" : $("#ruleLineSearchForm #isDeleted").val()	
		};
		return params;
	},
	/***
	 * 导出数据
	 */
	exportData : function (){
		_this = this;
		if (_this.disableExport){
			slideMessage("已进行过一次数据导出,导出功能已禁用,请10秒钟过后再点...");
			return;
		}
		_this.disableExport = true;
		
		var params = _this.getSearchParams();
		var paramList = "";
		for(key in params){ 
			paramList +=  key+"="+ params[key]+"&"
	    }
		$.ajax({
			url: _this.urlItems.exportCheckURL,
			data : params,
			type:'post',
			success : function(data){
				//检测通过
				if (data && data.code == 10000){
					slideMessage("数据正在导出中, 请耐心等待...");
					$.download(_this.urlItems.exportDataURL, paramList, 'post');
				}else{
					warningMessage(data.msg);
				}
			},
			error : function(){
				warningMessage("服务器出错");
			}
		});
		// 10秒后导出按钮重新可用
		setInterval(function(){
			_this.disableExport = false;
		}, 10000);
	},
	event : function () {
		_this = this;
		//查询
		$('#btn-search').click(function(){
			if(!$("#ruleLineSearchForm").form('validate')){
				return false;
			}
			var searchParams = _this.getSearchParams();
			_this.loadListData(searchParams);
		});
		
		//导出
		$("#btn-export").click(function() {
			_this.exportData();
		});
		
		//重置
		$('#btn-reset').click(function(){
			$("#ruleLineSearchForm")[0].reset();
		});
		
		$("#sProvinceId").change(function(){
			$("#sCityId").html("<option value=''>请选择城市</option>");
			$("#sAreaId").html("<option value=''>请选择区/县</option>");
			var parentId = $(this).val();
			var areaList = _this.queryAreaChildList(parentId);
			$.each(areaList, function(i, item){
				$("#sCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
			});
		});
		
		$("#eProvinceId").change(function(){
			$("#eCityId").html("<option value=''>请选择城市</option>");
			$("#eAreaId").html("<option value=''>请选择区/县</option>")
			var parentId = $(this).val();
			var areaList = _this.queryAreaChildList(parentId);
			$.each(areaList, function(i, item){
				$("#eCityId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
			});
		});
		
		$("#sCityId").change(function(){
			$("#sAreaId").html("<option value=''>请选择区/县</option>");
			var parentId = $(this).val();
			var areaList = _this.queryAreaChildList(parentId);
			$.each(areaList, function(i, item){
				$("#sAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
			});
		});
		
		$("#eCityId").change(function(){
			$("#eAreaId").html("<option value=''>请选择区/县</option>");
			var parentId = $(this).val();
			var areaList = _this.queryAreaChildList(parentId);
			$.each(areaList, function(i, item){
				$("#eAreaId").append("<option value='"+item.areaID+"'>"+item.area+"</option>"); 
			});
		});
	}
};

/**
 * 初始化
 */
$(function(){
	// 初始化分页
	ruleLineCtrl.ruleLineMain.initPageListData();
	// 初始化事件
	ruleLineCtrl.ruleLineMain.event();
	// 初始化省份列表
	ruleLineCtrl.ruleLineMain.initAreaTopList();
	
	fixRownumber();
});


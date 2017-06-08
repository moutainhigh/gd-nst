$(document).ready(function(){
	//默认过滤 不显示会员数据
	var params = {"userName" : "init"};
	loadListData(params);
	//查询
	$('#company-search').click(function(){
		var params = {
			"userName" : $("#companySearchForm #userName").val(),
			"mobile" : $("#companySearchForm #mobile").val()
		};
		loadListData(params);
	});
});

function loadListData(queryParams){
	queryParams = !queryParams ? {} : queryParams;
	//数据加载
	$('#companydg').datagrid({
		url:CONTEXT+'memberInfo/queryPage',
		queryParams: queryParams,
		//width: 1000,  
		height: 'auto', 
		nowrap:true,
		toolbar:'#companytb',
		pageSize:10,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		fit:true,
		columns:[[
			{field:'id',title:'',align:'center',checkbox:true},
			{field:'userName',title:'会员姓名',width:100,align:'center'},
			{field:'mobile',title:'联系电话',width:100,align:'center'}
		]]
	}); 
}



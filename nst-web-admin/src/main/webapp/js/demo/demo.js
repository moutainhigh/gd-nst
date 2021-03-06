/**
 * 例子
 * @author zhangnf
 * @since 2016-12-6
 */
var demonCtrl  = new NSTAdmin.client();

NSTAdmin.client.prototype.demon = {
	/**
	 * 请求地址URL
	 */
	urlItems : {
		queryByPageURL :PATH+"/demo/queryPage",
		test1URL : PATH+"/demo/test1",
		test2URL : PATH+"/demo/test2",
		test3URL : PATH+"/demo/test3"
	},
	/**
	 * ajaxA正常展示
	 */
	ajaxTest1 : function(){
		$.ajax({
			url:_this.urlItems.test1URL,
			dataType : "json",
			type : 'post',
			success : function(data) {
				infoMessage(data.msg);
			}
		});
	},
	/**
	 * ajaxA异常消息展示
	 */
	ajaxTest2 : function(){
		$.ajax({
			url:_this.urlItems.test2URL,
			dataType : "json",
			type : 'post',
			success : function(data) {
				
			}
		});
	},
	/**
	 * ajaxA服务器异常展示
	 */
	ajaxTest3 : function(){
		$.ajax({
			url:_this.urlItems.test3URL,
			dataType : "json",
			type : 'post',
			success : function(data) {
				
			}
		});
	},
	/**
	 * 分页查询
	 */
	pageQuery : function(){
		_this = this;
		//数据加载
		$('#demodg').datagrid({
			url:_this.urlItems.queryByPageURL,//CONTEXT+'demo/queryPage',
			//width: 1000,  
			height: 'auto', 
			nowrap:true,
			toolbar:'#demotb',
			pageSize:10,
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			fit:true,
			columns:[[
				{field:'id',title:'',width:50,checkbox:true},
				{field:'userName',title:'姓名',width:100,align:'center'},
				{field:'opt',title:'操作',width:100,align:'center',formatter:function(value,row,index){
					return "<a class='operate' href='javascript:;' onclick=delObj('"+row.id+"');>删除</a>";
				}}
			]]
		}); 
		//分页加载
		$("#demodg").datagrid("getPager").pagination({
			pageList: [10,20,50,100]
		});
	},
	/**
	 * 业务方法例子
	 * @param id
	 */
	doDelete : function(id){
		productCtrl.ajaxA({
			url :_this.urlItems.deleteURL,
			data : {id:id},
			type : 'post',
			success : function(data) {
				if(null == data){
					return;
				}
				if(0== data.flag){
					productCtrl.alertDialog(data.msg);
					$("#deleteProductW").hide();
					_this.doSearchCallback(0);
				}else{
					productCtrl.errorDialog(data.msg);
				}
			}
		});
	},
	/**
	 * 事件绑定
	 */
	event : function(){
		_this = this;
		$("#btn001").click(function(){
			_this.ajaxTest1();
		});
		$("#btn002").click(function(){
			_this.ajaxTest2();
		});
		$("#btn003").click(function(){
			_this.ajaxTest3();
		});
	}
};

/**
 * 初始化
 */
$(function(){
	//初始化按钮事件
	demonCtrl.demon.event();
	//初始化分页
	demonCtrl.demon.pageQuery();
});

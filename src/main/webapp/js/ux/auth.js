var INDEX = {
		role_add:"#role-add",
		user_add:"#user-add",
		user_edit:"#user-edit",
		role_edit:"#role-edit",
		menu_edit:"#auth-menu-edit",
		btn_edit:"#auth-btn-edit",
		role_tab:"#roleList",
		user_tab:"#userList",
		menu_tab:"#auth_menu_list",
		btn_tab:"#auth_btn_list",
		search:"#searchUser",
		searchImg:".searchbox-button",
		addUserForm:"#addUserForm",
		addRoleForm:"#addRoleForm"
};


common = function(){
	return{
		
		/**
		 * 弹出框
		 */
		alert:function(title, msg, icon, callback){
			$.messager.alert(title,msg,icon,callback);
		},
		
		/**
		 * ajax
		 */
		ajax:function(url,param,callback){
			$.ajax({
				method : 'post',
				url : url,
				async : false,
				 data: JSON.stringify(param),     
		         dataType : "json",
				contentType: "application/json; charset=utf-8",
				success : function(data) {
					if($.isFunction(callback)){
			 			callback(data);
			 		}
				},
				error : function(response, textStatus, errorThrown) {
					common.alert('提示','通讯异常！', 'error')
				}
		})
	},
		
	/**
	 * f分页栏设置
	 */
	pagination:function(id){
		var p = $(id).datagrid('getPager'); 
		$(p).pagination({
		    total:0,
	        rows : 0,
		    pageNumber : 1,//初始化页码
			pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [5,10,20],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页,共 {pages} 页',  
	        displayMsg: ''
		});
	}
	
 }
}();

fn = function(){
	
	return{
		/**
		 * 展示菜单权限列表
		 */
		displayMenuList:function(param){
			var menu_tab = INDEX.menu_tab;
			$(menu_tab).datagrid({
			    url:"/dmcWeb/displayAuthMenuList",
			    queryParams:param,
				pagination:true,
			    pagePosition:'bottom',
			    rownumbers:true,
			    fitColumns:true,
			    singleSelect:true,
			    loadMsg:'正在加载中......',
			    columns:[[
			              {field:'NAME',title:'菜单名',width:30},
					     {field:'URL',title:'URL',width:70}
			    ]]
			});
			common.pagination(menu_tab);
		},
		
		roleInCombox:function(id){
			id.combobox({
			    url:'roleInCombox',
			    method:'post',
			    valueField:'ROLE',
			    textField:'ROLE_NAME',
//			    onLoadSuccess: function() { $(this).combobox('setValue', '')},
			});
		},
		/**
		 * 展示按钮权限列表
		 */
		displayBtnList:function(param){
			var btn_tab = INDEX.btn_tab;
			$(btn_tab).datagrid({
			    url:"/dmcWeb/displayAuthBtnList",
			    queryParams:param,
				pagination:true,
			    pagePosition:'bottom',
			    rownumbers:true,
			    fitColumns:true,
			    singleSelect:true,
			    loadMsg:'正在加载中......',
			    columns:[[
			              {field:'BTN_NAME',title:'按钮名称',width:30},
			              {field:'BTN_ID',title:'按钮ID',width:70}
			    ]]
			});
			common.pagination(btn_tab);
		},
		
		/**
		 * 展示角色列表
		 */
		displayRoleList:function(){
			var role_tab = INDEX.role_tab;
			$(role_tab).datagrid({
				url:'/dmcWeb/displayRole',
			    pagination:true,
			    pagePosition:'bottom',
			    fitColumns:true,
			    rownumbers:true,
			    singleSelect:true,
			    loadMsg:'正在加载中......',
			    columns:[[
					{field:'ROLE',title:'角色',width:30},
					{field:'ROLE_NAME',title:'角色名',width:70},
					{field:'MENU_LIST',title:'菜单权限',width:0,hidden:true},
					{field:'BTN_LIST',title:'按钮权限',width:0,hidden:true}
			    ]],
			    onClickRow:function(rowIndex,rowData){
			    	var menu_list = rowData.MENU_LIST||'';
			    	var btn_list = rowData.BTN_LIST||'';
			    	var role = rowData.ROLE||'';
			    	fn.displayMenuList({menuList:menu_list,role:role});
			    	fn.displayBtnList({btnList:btn_list,role:role});
			    }
			});
			common.pagination(role_tab);
		},
		
		/**
		 * 展示用户列表
		 */
		displayUserList:function(user_pro_list,pagination){
			var user_tab = INDEX.user_tab;
			$(user_tab).datagrid({
			    queryParams:user_pro_list,
			    url:'/dmcWeb/displayUser',
			    pagination:pagination,
			    pagePosition:'bottom',
			    rownumbers:true,
			    fitColumns:true,
			    singleSelect:true,
			    loadMsg:'正在加载中......',
			    columns:[[
			              {field:'NICKNAME',title:'昵称',width:30},
					     {field:'EMAIL',title:'邮箱',width:40},
					      {field:'ROLE',title:'角色',width:30}
			    ]]
			});
			common.pagination(user_tab);
		},
		
		/**
		 * 通过邮箱查找用户
		 */
		searchUserByEmail:function(user_pro_list,pagination){
			var user_tab = INDEX.user_tab;
			$(user_tab).datagrid({
			    queryParams:user_pro_list,
			    url:'/dmcWeb/searchUserByEmail',
			    pagination:pagination,
			    pagePosition:'bottom',
			    rownumbers:true,
			    fitColumns:true,
			    singleSelect:true,
			    loadMsg:'正在加载中......',
			    columns:[[
			              {field:'NICKNAME',title:'昵称',width:30},
					     {field:'EMAIL',title:'邮箱',width:40},
					      {field:'ROLE',title:'角色',width:30}
			    ]]
			});
			common.pagination(user_tab);
		},
	
		/**
		 * 用户搜索
		 */
		searchUser:function(){
			var searchOpts = $(INDEX.search).searchbox("options"), searcher = searchOpts.searcher;
	        searchOpts.searcher = function (value, name) {
	        	if ($.isFunction(searcher)) { searcher.apply(this, arguments); }
	        	 //添加搜索条件
		        var conJson = {};
		        if(value=='')
		        {
		        	fn.displayUserList(conJson,true);
		        }
		        else
		        {
		          	conJson.email = value;
		        	fn.searchUserByEmail(conJson,true);
		        }
	        }
		},
		
		/**
		 * 编辑用户
		 */
		editUser:function(){
			var edit_user_tab = $(INDEX.user_tab);
			var param = {nickName:$('#edit_nickName').val(),email:$('#edit_email').val(),role:$('#edit_role').combobox('getValue')};
			common.ajax('editUser', param, function(data){
				if(data.resultCode=='1001'){
					$(INDEX.user_tab).datagrid('reload');
					common.alert("提示", data.resultMsg, "info");
					$('#btn-edituser-close').click();
				}
				else if(data.resultCode=='1002')
				{
					common.alert("提示", data.resultMsg, "info");
				}
				else{
					common.alert("提示", data.resultMsg, "error");
				}
			});
		},
		
		/**
		 * 增加角色，分配权限
		 */
		addRole:function(){
			var addRoleForm = $(INDEX.addRoleForm);
			if(addRoleForm.form('validate')){
				var param = {role:$('#add_role2').val(),auth:$('#add_auth').val()};
				common.ajax('addRole', param, function(data){
					if(data.resultCode=='1001'){
						$(INDEX.role_tab).datagrid('reload');
						common.alert("提示", data.resultMsg, "info");
						$('#btn-addRole-close').click();
					}
					else if(data.resultCode=='1002')
					{
						common.alert("提示", data.resultMsg, "info");
					}
					else{
						common.alert("提示", data.resultMsg, "error");
					}
				});
			}
		},
		
		/**
		 * 加载权限列表到combobox
		 */
		authInCombotree:function(id){
			id.combotree({
				url:'loadAuthList',
				editable:true,
				required: true
			});
		},
		
		/**
		 * 添加用户
		 */
		addUser:function(){
				var addUserForm = $(INDEX.addUserForm);
			    	if(addUserForm.form('validate')){
					var param = {nickName:$('#add_nickName').val(),email:$('#add_email').val(),pwd:$('#add_pwd').val(),role:$('#add_role').combobox('getValue')};
					common.ajax('addUser', param, function(data){
						if(data.resultCode=='1001'){
							$(INDEX.user_tab).datagrid('reload');
							common.alert("提示", data.resultMsg, "info");
							$('#btn-adduser-close').click();
						}
						else if(data.resultCode=='1002')
						{
							common.alert("提示", data.resultMsg, "info");
						}
						else{
							common.alert("提示", data.resultMsg, "error");
						}
					});
			    	}
			}
		}
}();

$(function(){

	//用户搜索
	$(INDEX.searchImg).click(function(){
		fn.searchUser();
	});
	
//	角色列表
	fn.displayRoleList();
			
//			用户列表
    fn.displayUserList(null,true);
   
//	增加用户事件
	$(INDEX.user_add).click(function() {
		$('#add-user-win').dialog('open');
		fn.roleInCombox($('#add_role'));
	});
	$('#btn-adduser-close').click(function() {
		$('#add-user-win').dialog('close');
	});
	$('#btn-adduser-submit').click(function(){
		fn.addUser();
	});
	
	//编辑用户
	$(INDEX.user_edit).click(function() {
		//选择行
		var row = $(INDEX.user_tab).datagrid('getSelected');
	    if (row){
//	    	alert(row.EMAIL+'  '+row.NICKNAME+'   '+row.ROLE);
	    	$('#edit-user-win').dialog('open');
	    	var $edit_role = $('#edit_role');
	    	$('#edit_nickName').val(row.NICKNAME);
	    	$('#edit_email').val(row.EMAIL);
	    	$('#edit_email').attr('readonly',true); 
		    fn.roleInCombox($edit_role);
	    	$($edit_role).combobox('setValue',row.ROLE);
	    }else
	    {
	    	common.alert("提示", "请选择一行", "info");
	    	return;
	    }
	});
	$('#btn-edituser-close').click(function() {
		$('#edit-user-win').dialog('close');
	});
	$('#btn-edituser-submit').click(function(){
		fn.editUser();
	});
	
	//增加角色及权限
	$(INDEX.role_add).click(function(){
		$('#add-role-win').dialog('open');
		fn.authInCombotree($('#add_auth'));
	});
	$('#btn-addRole-close').click(function() {
		$('#add-role-win').dialog('close');
	});
	$('#btn-addRole-submit').click(function(){
		fn.addRole();
	});
	
	//编辑角色及权限
});

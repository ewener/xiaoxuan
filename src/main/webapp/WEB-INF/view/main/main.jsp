<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Asiainfo Reporting System</title>
<%@include file="../resource.jsp"%>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/ux/main/main.js"></script>
</head>
<body class="easyui-layout" id="mainbody">
	<!-- 顶部功能标题区 -->
	<div data-options="region:'north',split:false,border:false" style="overflow: hidden;">
	<div class="header">
		     Asiainfo Reporting System
		     <div id="timeSpan"></div>
		</div>
		<div class="panel-header panel-header-noborder top-toolbar"style="padding: 2px;">
		    <div class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-hamburg-user'"  style="position:absolute;line-height:26px;padding-left: 1px; background-position: left center;">
               您好,${user.nickName}
            </div>
			<div style="position: absolute; right: 5px;" class="buttonbar">
				<c:forEach var="btn" items="${btnList}">
					<a class="easyui-linkbutton" href="javascript:void(0);" data-options="plain:true" id="${btn.btn_id}"  >${btn.btn_name}</a> 
				</c:forEach>
				<a class="easyui-linkbutton" href="javascript:void(0);" data-options="plain:true" id="modify-pwd-btn">修改密码</a> 
				<a class="easyui-linkbutton" href="javascript:void(0);" data-options="plain:true,iconCls:'icon-hamburg-sign-out'" onclick="main.logout()">退出</a>
			</div>
		<!-- 	<div style="position: absolute; right: 5px; padding-top: 2px;">
				<input id="combobox_themes" />
			</div> -->
		</div>
	</div>
	<!-- 左边树形菜单 -->
	<div id="left_layout" data-options="region:'west',split:true,title:'导航菜单',iconCls:'icon-standard-application-view-tile'" style="width: 200px;">
		<div id="tree-box" class="easyui-accordion" data-options="fit:true,border:false">
			<c:forEach var="item" items="${menuList}">
				<div title="${item.text}" data-options="iconCls:'icon-standard-chart-bar'">
					<c:forEach var="node" items="${item.children}">
						<a class="menu-item" href="javascript:void(0);"><span id="${node.url}">${node.text}</span></a>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- 底部版权 -->
	<div data-options="region:'south',split:true,border:false,collapsed:true" style="height: 30px; overflow: hidden;">
		<div class="panel-header" style="border: none; text-align: center;">CopyRight &copy; 2015 asiainfo 版权所有. &nbsp;&nbsp;</div>
	</div>
	<!-- 中间内容页面 -->
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div class="easyui-tabs" id="tab-box" data-options="fit:true,tools:'#index_tabs_tools'">
			<div title="Welcome" data-options="fit:true,border:false,closable:false" style="padding: 20px; overflow: hidden;"></div>
		</div>
		<div id="index_tabs_tools"  class="tabs-tool">
		   <table>
		      <tr>
				   <td><a class="easyui-linkbutton easyui-tooltip" title="主页" data-options="plain:true,iconCls:'icon-hamburg-home'" onclick="main.main_page()"></a></td> 
				   <td><a class="datagrid-btn-separator"></a></td>
					<td><a class="easyui-linkbutton easyui-tooltip" title="展开浏览站点" data-options="plain:true,iconCls:'icon-standard-arrow-inout'" onclick="main.expand_view()"></a></td>  
				    <td><a class="datagrid-btn-separator"></a></td>
				    <td><a class="easyui-linkbutton easyui-tooltip" title="关闭当前页面" data-options="plain:true,iconCls:'icon-standard-application-form-delete'" onclick="main.close_tab()"></a></td> 
					<td><a class="datagrid-btn-separator"></a></td>
					<td><a class="easyui-linkbutton easyui-tooltip" title="全屏浏览站点" data-options="plain:true,iconCls:'icon-standard-arrow-inout'" onclick="main.full_screen_view()"></a></td> 
				    <td><a class="datagrid-btn-separator"></a></td>
				    <td><a class="easyui-linkbutton easyui-tooltip" title="关闭其他页面"  data-options="plain:true,iconCls:'icon-standard-cancel'" onclick="main.close_other_tabs()"></a></td> 
					<td><a class="datagrid-btn-separator"></a></td>
					<td><a class="easyui-linkbutton easyui-tooltip" title="关闭全部页面"  data-options="plain:true,iconCls:'icon-standard-cross'" onclick="main.close_all_tabs()"></a></td> 
             </tr>		   
		   </table>
		   
		</div>
	</div>
	<!--  modify password start -->
	<div id="modify-pwd-win" class="easyui-dialog" buttons="#editPwdbtn" title="修改用户密码" data-options="closed:true,iconCls:'icon-save',modal:true" style="width: 350px; height: 200px;">
		<form id="pwdForm" action="modifyPwd.do" class="ui-form" method="post">
			<input class="hidden" name="id"> <input class="hidden" name="email">
			<div class="ui-edit">
				<div class="fitem">
					<label>旧密码:</label>
				    <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</div>
				<div class="fitem">
					<label>新密码:</label>
				    <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</div>
				<div class="fitem">
					<label>重复密码:</label> 
					<input id="rpwd" name="rpwd" type="password" class="easyui-validatebox" required="required" validType="equals['#newPwd']" />
				</div>
			</div>
		</form>
		<div id="editPwdbtn" class="dialog-button">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-submit">Submit</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-close">Close</a>
		</div>
	</div>
	<!-- modify password end  -->
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var loginDialog;
	var defaultUserInfoDialog;
	$(function() {
		$('#loginForm input').keyup(function(event) {
			if (event.keyCode == '13') {
				loginFun();
			}
		});
		
		/* 自动获焦 */
		/* window.setTimeout(function() {
			$('#loginForm input[name=name]').focus();
		},0); */
		

		loginDialog = $('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			buttons : [ /* {
				text : '注册',
				handler : function() {
					$('#registerDialog').dialog('open');
				}
			},  */{
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ]
		});

		defaultUserInfoDialog = $('#defaultUserInfoDialog').show().dialog({
			top : 0,
			left : 200
		});

		var sessionInfo_userId = '${sessionInfo.id}';
		if (sessionInfo_userId) {/*目的是，如果已经登陆过了，那么刷新页面后也不需要弹出登录窗体*/
			loginDialog.dialog('close');
			defaultUserInfoDialog.dialog('close');
		}else{
			/* 自动获焦 */
			window.setTimeout(function() {
				$('#loginForm input[name=name]').focus();
			},0);
		}
	});
	function loginFun() {
		if (layout_west_tree) {//当west功能菜单树加载成功后再执行登录
			if ($('#loginForm').form('validate')) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/admin/login', $('#loginForm').serialize(), function(result) {
					if (result.code == "SUCCESS") {
						/* if (!layout_west_tree_url) {
							layout_west_tree.tree({
								url : '${basePath}/web/menu/tree'
							});
						}
						layout_west_tree.tree('reload');//刷新系统菜单，这个菜单会根据用户ID，查找出他应该看到的菜单，没有权限就不会显示
						$('#loginDialog').dialog('close');
						$('#sessionInfoDiv').html($.formatString('[<strong>{0}</strong>]，欢迎你！您使用后台管理系统！' )); */
						parent.location.reload();
					} else {
						$.messager.alert('错误', result.message, 'error');
					}
					parent.$.messager.progress('close');
				}, "JSON");
			}
		}
	}
</script>
<div id="loginDialog" title="用户登录" style="width: 300px; height: 180px; overflow: hidden; display: none;">
	<form id="loginForm" method="post">
		<table class="table table-hover table-condensed">
			<tr>
				<th>登录名</th>
				<td><input name="name" type="text"  id="name" placeholder="请输入登录名" class="easyui-validatebox" data-options="required:true" value=""></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" placeholder="请输入密码" class="easyui-validatebox" data-options="required:true" value=""></td>
			</tr>
		</table>
	</form>
</div>


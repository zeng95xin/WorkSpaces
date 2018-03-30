<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head></head>
<body>
<script type="text/javascript" charset="utf-8">
	/**
	 * @author 
	 * 
	 * @requires jQuery,EasyUI,jQuery cookie plugin
	 * 
	 * 更换EasyUI主题的方法
	 * 
	 * @param themeName
	 *            主题名称
	 */
	function changeThemeFun(themeName) {
		if ($.cookie('easyuiThemeName')) {
			$('#layout_north_pfMenu').menu('setIcon', {
				target : $('#layout_north_pfMenu div[title=' + $.cookie('easyuiThemeName') + ']')[0],
				iconCls : 'emptyIcon'
			});
		}
		$('#layout_north_pfMenu').menu('setIcon', {
			target : $('#layout_north_pfMenu div[title=' + themeName + ']')[0],
			iconCls : 'tick'
		});

		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);

		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				try {
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				} catch (e) {
					try {
						ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
					} catch (e) {
					}
				}
			}
		}

		$.cookie('easyuiThemeName', themeName, {
			expires : 7
		});

	};

	function logoutFun(b) {
		$.getJSON('${basePath}/web/admin/logout', {
			t : new Date()
		}, function(result) {
			if (b) {
				location.replace('${basePath}/login');
			} else {
				$('#sessionInfoDiv').html('');
				$('#loginDialog').dialog('open');
			}
		});
	}

	function editCurrentUserPwd() {
		parent.$.modalDialog({
			title : '修改密码',
			width : 300,
			height : 250,
			href : '${basePath}/web/admin/editCurrentUserPwdPage',
			buttons : [ {
				text : '修改',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#editCurrentUserPwdForm');
					f.submit();
				}
			} ]
		});
	}
	
</script>
<div style="position: absolute; right: 0px; bottom: 0px;background-color: white;border-radius:4px;opacity:0.8">
	<a href="javascript:void(0);" class="easyui-menubutton" style="color: #000000;font-weight: 600;" data-options="menu:'#layout_north_pfMenu',iconCls:'cog'">更换主题</a> 
	<a href="javascript:void(0);" class="easyui-menubutton" style="color: #000000;font-weight: 600;" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'" onclick="editCurrentUserPwd();">修改密码</a> 
	<a href="javascript:void(0);" class="easyui-menubutton" style="color: #000000;font-weight: 600;" data-options="menu:'#layout_north_zxMenu',iconCls:'cog'" onclick="logoutFun(true);">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeThemeFun('default');" title="default">默认</div>
	<div onclick="changeThemeFun('cupertino');" title="cupertino">清新蓝</div>
	<div onclick="changeThemeFun('dark-hive');" title="dark-hive">古典黑</div>
	<div onclick="changeThemeFun('metro-gray');" title="metro-gray">科技灰</div>
	<div onclick="changeThemeFun('metro-green');" title="metro-green">草绿色</div>
	<div onclick="changeThemeFun('metro-orange');" title="metro-orange">橙色</div>
	<div onclick="changeThemeFun('metro-red');" title="metro-red">桃红色</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
	var layout_west_tree;
	var layout_west_tree_url = '';
	var sessionInfo_userId = '${sessionInfo.id}';
	
	if (sessionInfo_userId) {
		layout_west_tree_url = '${basePath}/web/menu/tree';
	}
	$(function() {
		layout_west_tree = $('#layout_west_tree').tree({
			url : layout_west_tree_url,
			parentField : 'pid',
			onLoadSuccess : function(node, data) {
				 $('#layout_west_tree').tree('collapseAll');
            },
            onSelect: function(node){
				$(this).tree('expand',node.target);
            },
			//lines : true,
			onClick : function(node) {
				if (node.attributes && node.attributes.url) {
					var url;
					var iframe;
					if (node.attributes.url.indexOf('/') == 0) {/*如果url第一位字符是"/"，那么代表打开的是本地的资源*/
						url = '${basePath}' + node.attributes.url;
						if (url.indexOf('/druidController') == -1) {/*如果不是druid相关的控制器连接，那么进行遮罩层屏蔽*/
							/* parent.$.messager.progress({
								title : '提示',
								text : '数据处理中，请稍后....'
							}); */
							 /* if (!${sessionInfo.resourceList}.contains(node.attributes.url)) {
								$.messager.alert('提示', '[' +  + ']不可以被关闭！', 'info');
							} */
						} 
					} else {/*打开跨域资源*/
						url = node.attributes.url;
					}
					//iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;" sandbox></iframe>';
					iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
					var t = $('#index_tabs');
					var opts = {
						title : node.text,
						closable : true,
						iconCls : node.iconCls,
						content : iframe,
						border : false,
						fit : true
					};
					if (t.tabs('exists', opts.title)) {
						t.tabs('select', opts.title);
						parent.$.messager.progress('close');
					} else {
						t.tabs('add', opts);
					}
				}
			}
		});
	});
	
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="功能菜单" style="padding: 5px;" data-options="border:false,isonCls:'cog',tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'resultset_next',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'resultset_previous',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<div class="well well-small">
			<ul id="layout_west_tree"></ul>
		</div>
	</div>
	
</div>
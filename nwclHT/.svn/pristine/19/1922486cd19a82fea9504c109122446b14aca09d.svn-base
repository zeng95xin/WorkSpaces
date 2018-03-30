<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>资源管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<%-- <c:if test="${fn:contains(sessionInfo.resourceList, '/web/menu/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/web/menu/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if> --%>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid').treegrid({
			url : '${basePath}/web/menu/treeGrid',
			idField : 'id',
			treeField : 'name',
			/* parentField : 'pid', */
			fit : true,
			fitColumns : false,
			border : false, 
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 0,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '资源名称',
				width : 200
			}, {
				field : 'url',
				title : '资源路径',
				width : 200
			}, {
				field : 'typeName',
				title : '资源类型',
				width : 100
			}, {
				field : 'pname',
				title : '上级资源',
				width : 100
			}, {
				field : 'action',
				title : '操作',
				width : 150,
				formatter : function(value, row, index) {
					var str = '';
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
					if ($.canEdit) {
					}
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
					if ($.canDelete) {
					}
					return str;
				}
			}, {
				field : 'remark',
				title : '备注',
				width : 150,
				formatter : function(value, row, index) {
					if (value) {
						return $.formatString('<span title="{0}">{1}</span>', value, value);
					}
					return '';
				}
			} ] ],
			toolbar : '#toolbar',
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		
	});

	function deleteFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.messager.confirm('询问', '您是否要删除当前资源？', function(b) {
				if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/menu/delete', {
						id : node.id
					}, function(result) {
						if (result.code=="SUCCESS") {
							parent.$.messager.alert('提示', result.message, 'info');
							treeGrid.treeGrid('reload');
							parent.layout_west_tree.tree('reload');

						}
						parent.$.messager.progress('close');
					}, 'JSON');
				}
			});
		}
	}

	function editFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.modalDialog({
				title : '编辑资源',
				width : 500,
				height : 300,
				href : '${basePath}/web/menu/editPage?id=' + node.id,
				buttons : [ {
					text : '保存',
					handler : function() {
						parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ]
			});
		}
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加资源',
			width : 500,
			height : 300,
			href : '${basePath}/web/menu/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<%-- <c:if test="${fn:contains(sessionInfo.resourceList, '/web/menu/addPage')}">
		</c:if> --%>
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>
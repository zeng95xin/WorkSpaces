<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>产品管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${basePath}/web/hProduct/dataGrid?hid=${hid}',
							fit : true,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 30, 50, 100 ],
							sortName : 'id',
							sortOrder : 'asc ',
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							nowrap : false,
							columns : [ [
									{
										field : 'id',
										title : '序号',
										width : 50,
										sortable : false,
									},
									{
										field : 'title',
										title : '名称',
										width : 50,
										sortable : false
									},
									{
										field : 'description',
										title : '详情',
										width : 100,
										sortable : false
									},
									{
										field : 'original',
										title : '市场价',
										width : 50,
										sortable : false
									},
									{
										field : 'price',
										title : '现价',
										width : 50,
										sortable : false
									},
									{
										field : 'rowAddTime',
										title : '添加时间',
										width : 50,
										sortable : false

									},
									{
										field : 'action',
										title : '操作',
										width : 50,
										formatter : function(value, row, index) {
											var str = '&nbsp;';
											str += $
													.formatString(
															'<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>',
															row.id,
															'${basePath}/style/images/extjs_icons/pencil.png');
											str += '&nbsp;&nbsp;&nbsp;';
											
											str += $
													.formatString(
															'<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
															row.id,
															'${basePath}/style/images/extjs_icons/cancel.png');
											return str;
										}
									} ] ],
							toolbar : '#toolbar',
							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$(this).datagrid('unselectAll');
								$(this).datagrid('selectRow', rowIndex);
								$('#menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							}
						});
		parent.$.messager.progress('close');
	});
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 350,
			height : 350,
			href : '${basePath}/web/hProduct/productAddPage?hid=${hid}',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function editFun(id) {
		if (id == typeof (undefined) || id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 350,
			height : 350,
			href : '${basePath}/web/hProduct/productEditPage?id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function deleteFun(id) {		
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除信息 ', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';
				if (currentUserId) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/hProduct/delete', {
						id : id
					}, function(result) {
						if (result.code == "SUCCESS") {
							parent.$.messager.alert('提示', result.message,
									'info');
							dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
		});
	}


</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'pencil_add'">添加</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>

</body>
</html>
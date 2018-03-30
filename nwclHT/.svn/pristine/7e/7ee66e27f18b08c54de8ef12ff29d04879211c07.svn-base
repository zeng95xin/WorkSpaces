<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>来访单据详情管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${basePath}/web/visitors/detailDataGrid?id='
									+ ${id},
							fit : true,
							fitColumns : false,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 30, 50, 100 ],
							checkOnSelect : false,
							selectOnCheck : false,
							nowrap : false,

							columns : [ [
									/* {
										field : 'id',
										title : '编号',
										width : 120,
										checkbox : true
									}, */
									{
										field : 'name',
										title : '来访人姓名',
										width : 120,
										sortable : false
									},
									{
										field : 'phone',
										title : '来访人电话',
										width : 150,
										sortable : false
									},
									{
										field : 'rowAddTime',
										title : '实际来访时间',
										width : 150,
										sortable : false
									},
									/* {
										field : 'action',
										title : '操作',
										width : 100,
										formatter : function(value, row, index) {
											var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
											str += $
													.formatString(
															'<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
															row.id,
															'${basePath}/style/images/extjs_icons/cancel.png');
											return str;
										}
									}  */] ],
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

	/* function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除信息 ', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';
				if (currentUserId != id) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/visitors/detailDataGrid', {
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

	function batchDeleteFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					var currentUserId = '${sessionInfo.id}';//当前登录用户的ID
					var flag = false;
					for (var i = 0; i < rows.length; i++) {
						if (currentUserId != rows[i].id) {
							ids.push(rows[i].id);
						} else {
							flag = true;
						}
					}
					$.getJSON('${basePath}/web/visitors/detailBatchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code == "SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid(
									'unselectAll').datagrid('clearSelections');
						}
						if (flag) {
							parent.$.messager.show({
								title : '提示',
								msg : '不可以删除自己！'
							});
						} else {
							parent.$.messager.alert('提示', result.message,
									'info');
						}
						parent.$.messager.progress('close');
					});
				}
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	} */

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));

	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<!-- <div id="toolbar" style="display: none;">
		<a onclick="batchDeleteFun();" href="javascript:void(0);"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	</div> -->



</body>
</html>
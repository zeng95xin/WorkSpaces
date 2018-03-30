<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>物品借用管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid( {
			url : '${basePath}/web/borrow/dataGrid',
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
			rownumbers : true,
			nowrap : false,
			columns : [ [
					{
						field : 'roomNo',
						title : '房间号',
						width : 40,
						sortable : false
					},
					{
						field : 'thingName',
						title : '物品名称',
						width : 40,
						sortable : false
					},
					{
						field : 'userName',
						title : '借用人',
						width : 40,
						sortable : false
					},
					{
						field : 'state',
						title : '借用状态',
						width : 40,
						sortable : false,
						formatter : function(value) {
							if(value == 0){
								return "<font color=green>未同意</font>";
							}else if (value == 1) {
								return "同意未领取";
							} else if(value == 2){
								return "已领取";
							}else if(value == 3){
								return "已归还";
							}else if(value == 4){
								return "已拒绝";
							}else if(value == -1){
								return "已取消";
							}
						}
					},
					{
						field : 'borrowReason',
						title : '借用说明',
						width : 60,
						sortable : false
					},
					{
						field : 'borrowTime',
						title : '借用时间',
						width : 50,
						sortable : false
					},
					{
						field : 'action',
						title : '操作',
						width : 40,
						formatter : function(value, row, index) {
							var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
							str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',row.id,'${basePath}/style/images/extjs_icons/cancel.png');
							str += '&nbsp;&nbsp;&nbsp;&nbsp;';
							if(row.state == 0){
								str += $.formatString('<img onclick="stateFun(\'{0}\',1);" src="{1}" title="同意借用"/>',row.id,'${basePath}/style/images/extjs_icons/tick.png');
								str += '&nbsp;&nbsp;&nbsp;&nbsp;';
								str += $.formatString('<img onclick="stateFun(\'{0}\',4);" src="{1}" title="拒绝借用"/>',row.id,'${basePath}/style/images/extjs_icons/delete.png');
							}
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

	function deleteFun(id) {
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
					$.post('${basePath}/web/borrow/delete', {
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
	
	
	function stateFun(id,state) {
		var aa="";
		if(state == 1){
			aa = '您是否要同意物品借用？';
		}
		if(state == 4){
			aa = '您是否要拒绝物品借用？';
		}
		parent.$.messager.confirm('询问', aa, function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/borrow/goodsManager', {
					id : id,
					state : state
				}, function(result) {
					if (result.code == "SUCCESS") {
						parent.$.messager.alert('提示', result.message,
								'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false"
			style="height: 65px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%;">
					<tr>
						<th>状态</th>
						<td><select name="status" class="span2" style="width:210px;">
								<option value="">--请选择--</option>
								<option value="0">等待处理</option>
								<option value="1">借用中</option>
								<option value="2">已归还</option>
						</select></td>
						
						<td><a href="javascript:void(0);" class="easyui-linkbutton"
							data-options="iconCls:'brick_add',plain:true"
							onclick="searchFun();">查询</a> <a href="javascript:void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'brick_delete',plain:true"
							onclick="cleanFun();">清空</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;"></div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	</div>

</body>
</html>
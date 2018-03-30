<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>来访单据管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid(
		{
			url : '${basePath}/web/visitors/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 30, 50, 100 ],
			checkOnSelect : false,
			selectOnCheck : false,
			singleSelect : true,
			nowrap : false,
			/* rownumbers:true, */
			columns : [ [
					{
						field : 'serial',
						title : '来访单据编号',
						width : 50,
						sortable : false
					},
					{
						field : 'unitNo',
						title : '单元编号',
						width : 50,
						sortable : false
					}, {
						field : 'nickname',
						title : '账号昵称',
						width : 50,
						sortable : false
					},{
						field : 'phone',
						title : '发起人账号',
						width : 50,
						sortable : false
					},{
						field : 'vName',
						title : '预计来访人',
						width : 50,
						sortable : false
					},{
						field : 'visitorsName',
						title : '实际来访人',
						width : 50,
						sortable : false
					},{
						field : 'expectArriveTime',
						title : '预计来访时间',
						width : 50,
						sortable : false
					},{
						field : 'action',
						title : '操作',
						width : 50,
						formatter : function(value, row, index) {
							var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
							str += $
									.formatString(
											'<img onclick="eyeFun(\'{0}\');" src="{1}" title="查看"/>',
											row.id,
											'${basePath}/style/images/extjs_icons/eye.png');
							/* str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
							str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png'); */
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

	/* function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除信息 ', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/visitors/delete', {
					id : id
				}, function(result) {
					if (result.code == "SUCCESS") {
						parent.$.messager.alert('提示', result.message, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
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
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.getJSON('${basePath}/web/visitors/batchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code == "SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid(
									'unselectAll').datagrid('clearSelections');
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
	function eyeFun(id) {
		window.open('${basePath}/web/visitors/detailManager?id=' + id, window,
				"dialogHeight:500px;dialogWidth:1000px;status:no;");
	}

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
		<div data-options="region:'north',title:'查询条件'"
			style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width: 70%">
					<tr>
						<c:if test="${loginUser.roleId == 1 }">
							<td>小区</td>
							<td><select name="communityId" style="width: 100px"
								class="easyui-combobox"
								data-options="editable:false,width:120,panelHeight:'auto'">
									<option value="-1">--请选择小区--</option>
									<c:forEach items="${community }" var="list">
										<option value="${list.id }">${list.name }</option>
									</c:forEach>
							</select></td>
						</c:if>
						<td>查询选项</td>
						<td><select name="option" style="width: 100px"
							class="easyui-combobox"
							data-options="editable:false,width:120,panelHeight:'auto'">
								<option value="-1">--请选择--</option>
								<option value="1">房间号</option>
								<option value="2">预计来访时间</option>
								<option value="3">业主电话</option>
						</select></td>
						<td>关键字</td>
						<td><input class="easyui-textbox" style="width: 100px"
							name="key" class="span2" /></td>
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
	<!-- <div id="toolbar" style="display: none;">
		<a onclick="batchDeleteFun();" href="javascript:void(0);"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	</div> -->



</body>
</html>
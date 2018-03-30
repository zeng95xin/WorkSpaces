<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/user/dataGrid',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [10,30,50,100],
		sortName : 'id',
		sortOrder : 'asc ',
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect:true,
		nowrap : false,
		rownumbers:true,
		frozenColumns : [ [ {
			field : 'id',
			checkbox : true
		}] ],
		columns : [ [ /* {
			field : 'id',
			title : '序号',
			width : 30,
			sortable : false
		}, */
		{
			field : 'roomId',
			title : '房间号',
			width : 50,
			sortable : false
		},
		{
			field : 'name',
			title : '姓名',
			width : 50,
			sortable : false
		},/* {
			field : '',
			title : '身份',
			width : 40,
			sortable : false
		} */{
			field : 'phone',
			title : '电话',
			width : 50,
			sortable : false
		}] ],
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
function selectFun(){
	var rows = dataGrid.datagrid('getChecked');
	var names=[];
	for(var i=0; i<rows.length; i++){
	    names.push(rows[i].name);
	}
	var value=names.join(",");
	 window.returnValue = value;
     window.close();
}
</script>
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%;">
					<tr>
						<th>小区</th>
						<td><select name="status" class=" span2" >
							<option value="">--请选择--</option>
							<option value="0">未使用</option>
							<option value="1">使用中</option>
						</select></td>
						<th>用户组</th>
						<td><select name="nickname" class=" span2" >
							<option value="">--请选择--</option>
							<option value="phone">账号</option>
							<option value="sex">性别</option>
							<option value="rowAddTime">注册时间</option>
						</select></td>
						<th>楼宇</th>
						<td><select name="nickname" class=" span2" >
							<option value="">--请选择--</option>
							<option value="phone">账号</option>
							<option value="sex">性别</option>
							<option value="rowAddTime">注册时间</option>
						</select></td>
						<th>房间号</th>
						<td><input name="name" type="text" class=" span2"/></td>
						
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="selectFun();">选择</a>
						</td>
					</tr>
				</table>
			</form>
		</div>	
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		
	</div>

	
</body>
</html>
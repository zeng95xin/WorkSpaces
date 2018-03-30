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
		columns : [ [ {
			field : 'id',
			title : '序号',
			width : 30,
			sortable : false
		},
		{
			field : 'phone',
			title : '账号',
			width : 50,
			sortable : false
		},
		{
			field : 'nickname',
			title : '昵称',
			width : 50,
			sortable : false
		},{
			field : 'status',
			title : '状态',
			width : 30,
			sortable : false,
			formatter: function(value){
				if(value==0){
					return  "未使用";
				}
				if(value==1){
					return '<font color="green">使用中</font>';
				}
				}
		},{
			field : 'roomId',
			title : '房间号',
			width : 40,
			sortable : false
		},{
			field : 'sex',
			title : '性别',
			width : 30,
			sortable : false
		},
		{
			field : 'headPortrait',
			title : '头像',
			width : 50,
			sortable : false,
			formatter: function(value){	
				
				var str = "";
			     if(value!="" || value!=null){
			     str = "<img style=\"height: 25px;width: 30px;\" src=\""+value+"\"/>";
			     return str;
			      }else{
			    	  return value;
			      }											
				}
		},{
			field : 'rowAddTime',
			title : '注册时间',
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
</script>
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:900px;">
					<tr>
						<th>状态</th>
						<td><select name="status" class=" span2" >
							<option value="">--请选择--</option>
							<option value="0">未使用</option>
							<option value="1">使用中</option>
						</select></td>
						<th>查询选项</th>
						<td><select name="nickname" class=" span2" >
							<option value="">--请选择--</option>
							<option value="phone">账号</option>
							<option value="sex">性别</option>
							<option value="rowAddTime">注册时间</option>
						</select></td>
						<th>关键字</th>
						<td><input name="name" class="span2" /></td>
						
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空</a>
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

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
	
</body>
</html>
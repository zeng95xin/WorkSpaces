<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>人员管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/suser/dataGrid',
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
			field : 'name',
			title : '姓名',
			width : 50,
			sortable : false,
		},
		{
			field : 'headPortrait',
			title : '头像',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if (value != "" && value != null) {
					return $.formatString('<img src="{0}" style="width:40px;height:40px;"/>', value);
				}else{
					return $.formatString('<img src="{0}" style="width:40px;height:40px;"/>', '${basePath}/images/user1.png');
				}
			}
		},
		{
			field : 'sex',
			title : '性别',
			width : 20,
			sortable : false
		},
		{
			field : 'status',
			title : '状态',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 1){
					return "启用";
				}else if(value == 0){
					return "禁用";
				}
			}
		},
		{
			field : 'bizStatus',
			title : '业务状态',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if(row.role != 3){
					return "";
				}
				if(value == 0){
					return "离线";
				}else if(value == 1){
					return "在线";
				}else if(value == 2){
					return "忙碌";
				}
			}
		},
		{
			field : 'signStatus',
			title : '签到状态',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 0){
					return "签退";
				}else if(value == 1){
					return "签到";
				}
			}
		},
		{
			field : 'role',
			title : '部门',
			width : 20,
			sortable : false,
			formatter : function(value, row, index){
				return row.deptName;
			}
		},
		{
			field : 'community',
			title : '小区',
			width : 50,
			sortable : false
		},
		/* {
			field : 'building',
			title : '楼栋',
			width : 50,
			sortable : false
		},		 */
		{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
				str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
			    return str;
			}
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
function  addFun(){
	parent.$.modalDialog({
		title : '添加',
		width : 750,
		height : 600,
		href : '${basePath}/web/suser/addPage',
		buttons : [{
				text : '添加',
				handler : function(){
					parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
			} 
		}]
	});
}

function editFun(id) {
	if (id==typeof(undefined)||id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 750,
		height : 600,
		href : '${basePath}/web/suser/editPage?id=' + id,
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
	parent.$.messager.confirm('询问', '您是否要删除员工信息 ', function(b) {
		if (b) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.post('${basePath}/web/suser/delete', {
				id : id
			}, function(result) {
				if (result.code=="SUCCESS") {
					parent.$.messager.alert('提示', result.message, 'info');
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
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:900px;">
					<tr>
						<c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="communityId" class=" span2"  onchange="communitySelect()" id="communityId">
							<option value="-1">--请选择--</option>
							<c:forEach items="${comus }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						</c:if>
						<th>部门</th>
						<td><select name="role" class=" span2" >
							<option value="-1">--请选择--</option>
							<c:forEach items="${departments }" var="list">
								<option value="${list.id }">${list.deptName }</option>
							</c:forEach>							
						</select></td>
						<th>查询选项</th>
						<td><select name="option" class=" span2" >
							<option value="-1">--请选择--</option>
							<option value="1">员工姓名</option>
							<option value="2">手机号码</option>
						</select></td>
						<th>关键字</th>
						<td><input name="key" class="span2" /></td>	
						
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
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
	
</body>
</html>
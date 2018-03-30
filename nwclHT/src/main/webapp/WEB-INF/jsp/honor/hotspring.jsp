<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>温泉养生</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/honor/dataGrid?type=2',
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
			width : 25,
			sortable : false
		},
		{
			field : 'title',
			title : '名称',
			width : 50,
			sortable : false
		},
		{
			field : 'address',
			title : '地址',
			width : 50,
			sortable : false
		},
		/* {
			field : 'content',
			title : '介绍',
			width : 100,
			sortable : false
		}, */
		{
			field : 'userName',
			title : '联系人姓名',
			width : 50,
			sortable : false
		},
		{
			field : 'userPosition',
			title : '联系人职位',
			width : 50,
			sortable : false
		},
		{
			field : 'userPhone',
			title : '联系人电话',
			width : 50,
			sortable : false
			
		},{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
			    str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="ratingFun(\'{0}\');" src="{1}" title="活动评论"/>', row.id, '${basePath}/style/images/extjs_icons/comments.png');
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
		title : '添加温泉养生',
		width : 650,
		height : 600,
		href : '${basePath}/web/honor/springAddPage',
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
		width : 650,
		height : 600,
		href : '${basePath}/web/honor/springEditPage?id=' + id,
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
			if (currentUserId ) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/honor/delete', {
					id : id
				}, function(result) {
					if (result.code=="SUCCESS") {
						parent.$.messager.alert('提示', result.message, 'info');
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
// 添加温泉票
function productFun() {
		var rows = dataGrid.datagrid('getSelections');
		if (rows == null || rows=="") {
			parent.$.messager.alert('提示', "请选择一条记录", 'info');
		}else{
			var id = rows[0].id;
			window.open('${basePath}/web/hProduct/hProductManager?hid=' + id,
				'newwindow','height=500,width=830,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'
			);
	}
}

//评论
function ratingFun(id) {
	if (id==typeof(undefined)||id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	window.open('${basePath}/web/hComment/hCommentManager?hid=' + id,
		'newwindow','height=500,width=830,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'
	);
}
</script>
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:900px;">
					<tr>
						<th>查询选项</th>
						<td><select name="Stype" class=" span2" >
							<option value="">--请选择--</option>
							<option value="title">名称</option>
							<option value="address">地址</option>
							<option value="user">联系人</option>
						</select></td>
						<th>关键字</th>
						<td><input name="Svalue" class="span2" /></td>						
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
		<a onclick="productFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'door_in'">温泉票</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
	<div id="products"></div>
</body>
</html>
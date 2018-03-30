<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>消息通知</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/notify/dataGrid',
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
			width : 20,
			sortable : false
		},
		{
			field : 'type',
			title : '消息类型',
			width : 40,
			sortable : false,
			formatter : function(value, row, index) {
				if(value == 1){
					return '管家消息';
				}else if(value == 2){
					return '借用单消息';
				}else if(value == 3){
					return '挂画单消息';
				}else if(value == 4){
					return '维修单消息';
				}else if(value == 5){
					return '访客消息';
				}else if(value == 6){
					return '放行条单消息';
				}else if(value == 7){
					return '授权消息';
				}else if(value == 9){
					return '邻里帮消息';
				}else if(value == 10){
					return '公告消息';
				}else if(value == 11){
					return '业主讲堂消息';
				}else if(value == 12){
					return '业主市集消息';
				}else{
					return '无';
				}
			}
		}, 
		{
			field : 'title',
			title : '标题',
			width : 50,
			sortable : false
		},
		{
			field : 'detail',
			title : '内容',
			width : 100,
			sortable : false
			
		},
		{
			field : 'status',
			title : '状态',
			width : 50,
			sortable : false,
			formatter : function(value, row, index) {
				if(value == 1){
					return '<font color="green">已发送</font>';
				}else if(value == 0){
					return '<font color="red">待审核</font>';
				}
			}
			
		},{
			field : 'rowAddTime',
			title : '发布时间',
			width : 50,
			sortable : false
		},{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
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
		title : '添加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(上传的图片不超过20M)',
		width : 850,
		height : 600,
		href : '${basePath}/web/notify/addPage',
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


function deleteFun(id) {
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	parent.$.messager.confirm('询问', '您是否要删除通知信息 ', function(b) {
		if (b) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.post('${basePath}/web/notify/delete', {
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
				<table class="table table-hover table-condensed" style="width:920px">
					<tr>
						<th>小区</th>
						<td><select name="communityId" class=" span2" >
							<option value="">--请选择--</option>
							<c:forEach items="${communities }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>
						</select></td>
						
						<th>查询选项</th>
						<td><select name="sname" class=" span2" >
							<option value="">--请选择--</option>
							<option value="title">标题</option>
							<option value="detail">内容</option>
							<option value="remark">备注</option>
							<option value="rowAddTime">发布时间</option>
						</select></td>
						<th>关键字</th>
						<td><input name="svalue" class="span2" /></td>						
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
	</div>
	
</body>
</html>
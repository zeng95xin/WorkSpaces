<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>公告通知管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/announcementNotice/dataGrid',
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
			sortable : true
		},
		/* {
			field : 'title',
			title : '小区',
			width : 40,
			sortable : false
		}, */
		{
			field : 'title',
			title : '标题',
			width : 50,
			sortable : true
		},
		/* {
			field : 'detail',
			title : '内容',
			width : 100,
			sortable : false
			
		}, */
		{
			field : 'summary',
			title : '简介',
			width : 50,
			sortable : false
		},{
			field : 'rowAddTime',
			title : '发布时间',
			width : 50,
			sortable : false
			
		},{
			field : 'isTop',
			title : '是否置顶',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 1){
					return '是';
				}else{
					return '否';
				}
			}
		},{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				//var icon = heart; '+icon+' icon = heart_delete;
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
				if(row.isTop == 1){
				    str += $.formatString('<img onclick="topFun(\'{0}\');" src="{1}" title="取消置顶"/>', row.id, '${basePath}/style/images/extjs_icons/heart_delete.png', top);
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				}else{
				    str += $.formatString('<img onclick="topFun(\'{0}\');" src="{1}" title="置顶"/>', row.id, '${basePath}/style/images/extjs_icons/heart.png', top);
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				}
				
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
		title : '添加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(上传的图片不超过20M)',
		width : 850,
		height : 600,
		href : '${basePath}/web/announcementNotice/addPage',
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
		title : '编辑&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(上传的图片不超过20M)',
		width : 850,
		height : 600,
		href : '${basePath}/web/announcementNotice/editPage?id=' + id,
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
function topFun(id){
	$.getJSON('${basePath}/web/announcementNotice/top?v=' + new Date().getTime(),{id:id},function(result){
		parent.$.messager.alert('提示', result.message, 'info', function(){dataGrid.datagrid('reload');});
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
			$.post('${basePath}/web/announcementNotice/delete', {
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

//评论
function ratingFun(id) {
	if (id==typeof(undefined)||id == "undefined") {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	window.open('${basePath}/web/AnComment/manager?hid=' + id,
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
					<c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="communityId" class=" span2" >
							<option value="">--请选择--</option>
							<c:forEach items="${comus }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>
						</select></td>
						</c:if>
						<th>查询选项</th>
						<td><select name="sname" class=" span2" >
							<option value="">--请选择--</option>
							<option value="title">标题</option>
							<option value="detail">内容</option>
							<option value="remark">备注</option>
							<!-- <option value="publishPeopleId">发布人</option> -->
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
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
	
</body>
</html>
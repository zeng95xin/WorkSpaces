<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<title>兴趣小组</title>
<jsp:include page="../inc.jsp"></jsp:include>
<style type="text/css">
	.pagination-num{margin-top: 10px;}
</style>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/interestG/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'groupId',
			pageSize : 10,
			pageList : [ 10,30,50,100 ],
			sortName : '',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			singleSelect : true,
			columns : [ [ 
				{
				field : 'groupId',
				title : '编号',
				width : 100,
				checkbox : true
				},{
				field:'comName',
				title:'小区',
				width:100,
				},{
				field:'groupName',
				title:'活动名称',
				width:100
				},{
				field:'groupContent',
				title:'活动内容',
				width:100
				},{
				field:'groupReadAmount',
				title:'阅读量',
				width:100
				},{
				field:'groupInterestedAmount',
				title:'感兴趣人数',
				width:100
				},{
				field:'roleName',
				title:'发布人',
				width:100
				},{
				field:'groupTime',
				title:'发布时间',
				width:100
				},{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
					
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.groupId, '${basePath}/style/images/extjs_icons/pencil.png');
					
					
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					
				    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.groupId, '${basePath}/style/images/extjs_icons/cancel.png');
					
					
					return str;
				}}
			 ] ],
			
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');
			},
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
	});

	

	function deleteFun(groupId) {
		if (groupId == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			inId = rows[0].groupId;
			alert(inId);
		}
		parent.$.messager.confirm('询问', '您是否要删除当前记录？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.inId}';/*当前登录用户的ID*/
				if (currentUserId != inId) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/interest/delete', {
						id : inId
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
					var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
					var flag = false;
					for ( var i = 0; i < rows.length; i++) {
						if (currentUserId != rows[i].inId) {
							ids.push(rows[i].inId);
						} else {
							flag = true;
						}
					}
					$.getJSON('${basePath}/interest/deletes', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code=="SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						if (flag) {
							parent.$.messager.show({
								title : '提示',
								msg : '不可以删除自己！'
							});
						} else {
							parent.$.messager.alert('提示', result.message, 'info');
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
	}

	function editFun(inId) {
		if (inId == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			inId = rows[0].inId;
		}
		parent.$.modalDialog({
			title : '编辑用户',
			width : 600,
			height : 400,
			href : '${basePath}/interest/editInterestItem?inId=' + inId,
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

	function addFun() {
		parent.$.modalDialog({
			title : '兴趣小组分类添加',
			width : 600,
			height : 400,
			href : '${basePath}/interest/addInterestItem',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
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
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;width: 80%">
					<tr>
						<th>小区</th>
						<td><select name="comName" style="width: 150px" onchange="searchFun();">
							<c:forEach items="${nCommunity}" var="list">
							<option value="${list.conId }">${list.comName}</option>
							</c:forEach>
						</select>
						</td>
						<th>查询选项</th>
						<td><select name="condition" style="width: 100px">
							<option value="0">活动名称</option>
							<option value="1">活动内容</option>
							<option value="2">发布人</option>
						</select>
						</td>
						<th>标题</th>
						<td><input name="titel" class="span2" /></td>
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
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>
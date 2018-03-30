<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>维修单据管理</title>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/maintenance/dataGrid',
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
		rownumbers:true,
		nowrap : false,
		columns : [ [ {
			field : 'serial',
			title : '维修单号',
			width : 60,
			sortable : false
		},
		{
			field : 'room',
			title : '房间号',
			width : 40,
			sortable : false
		},{
			field : 'user',
			title : '手机账号',
			width : 40,
			sortable : false
		},{
			field : 'status',
			title : '维修状态',
			width : 40,
			sortable : false,
			formatter: function(value){
			    if(value == 0 || value == 1 || value == 2) {
			        return '<font color="red">待处理</font>';
			    }else if (value == 7 || value == 8 || value == 9 || value == 10) {
			        return "已完结";
			    }else if (value == -1) {
			        return '<font color="orange">已取消</font>';
			    }else{
			        return '<font color="green">处理中</font>';
			    }
			}
		},{
			field : 'visit',
			title : '是否回访',
			width : 40,
			sortable : false,
			formatter: function(value){
			    if (value && value == 1) {
			        return '<font color="green">是</font>';
			    }
			    else {
			        return '<font color="red">否</font>';
			    }
			}
		},{
			field : 'visit2',
			title : '回访结果',
			width : 40,
			sortable : false,
			formatter: function(value,row,index){
			    if (row.visit && row.visit == 1) {
			        return row.reason;
			    }
			}
		}
		,{
			field : 'description',
			title : '报修内容',
			width : 100,
			sortable : false
		}
		,{
			field : 'rowAddTime',
			title : '报修时间',
			width : 40,
			sortable : false
		}
		,{
			field : 'repairman',
			title : '维修人员',
			width : 40,
			sortable : false
		}
		,{
			field : 'repairmanPhone',
			title : '维修人员电话',
			width : 40,
			sortable : false
		}
		/* ,{
			field : 'prospectingDescription',
			title : '勘探结果',
			width : 100,
			sortable : false
		} */
		,{
			field : 'rowUpdateTime',
			title : '最后操作时间',
			width : 40,
			sortable : false
		}
		,{
			field : 'reason',
			title : '取消原因',
			width : 40,
			sortable : false
		}
		,{
			field : 'comments',
			title : '评价',
			width : 40,
			sortable : false
		}
		,{
			field : 'payStatus',
			title : '是否付款',
			width : 40,
			sortable : false,
			formatter : function(value){
				if(value == 0){
					return "否";
				}else if (value == 1) {
					return "是";
				}
			}
		}
		,{
			field : 'payMoney',
			title : '金额',
			width : 40,
			sortable : false
		},{
			field : 'action',
			title : '操作',
			width : 60,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
			    if(1!=row.visit&&row.status > 6){
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="visitFun(\'{0}\');" src="{1}" title="回访"/>', row.id, '${basePath}/style/images/extjs_icons/book.png');
			    }else{
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			    }
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

function editFun(id) {
	if (id==typeof(undefined)||id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 600,
		height : 600,
		href : '${basePath}/web/maintenance/editPage?id=' + id,
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

function settingFun() {
	
	parent.$.modalDialog({
		title : '设置维修状态',
		width : 250,
		height : 220,
		href : '${basePath}/web/maintenance/setStatusPage',
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
function visitFun(id) {
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	
	parent.$.messager.prompt('询问', '请输入回访内容:', function(r){
		if (r){
			parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/maintenance/visit', {
					id : id, content:r
				}, function(result) {
					if (result.code=="SUCCESS"){
						parent.$.messager.alert('提示', result.message, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
		}
	});
	
}
function deleteFun(id) {
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
				$.post('${basePath}/web/maintenance/delete', {
					id : id
				}, function(result) {
					if (result.code=="SUCCESS"){
						parent.$.messager.alert('提示', result.message, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			} else {
				/* parent.$.messager.show({
					title : '提示',
					msg : '不可以删除自己！'
				}); */
		}
	});
}
</script>
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 65px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%;">
					<tr>
						<c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="communityId" class=" span2" >
							<option value="-1">--请选择--</option>
							<c:forEach items="${comus }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						</c:if>								
						<th>维修状态</th>
						<td><select name="status" class="span2" style="width:210px;" >
						      <option value="">--请选择--</option>
						      <option value="0">待处理</option>
						      <option value="1">处理中</option>
						      <option value="2">已完结</option>
						    </select></td>	
					    <th>查询选项</th>
						<td><select name="serial" class="span2" style="width:210px;" >
					          <option value="">--请选择--</option>
						      <option value="serial">维修单号</option>
						      <option value="description">内容</option>
						      
						    </select></td>	
						<th>关键字</th>
						<td><input name="description" class="span2" style="width:210px;" /></td>						
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
		<a onclick="settingFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">师傅状态设置</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
	
</body>
</html>
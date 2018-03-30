<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>挂画服务管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/hangPicture/dataGrid',
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
			field : 'room',
			title : '单元编号',
			width : 40,
			sortable : false
		},{
			field : 'user',
			title : '昵称',
			width : 40,
			sortable : false
		},{
			field : 'name',
			title : '业主姓名',
			width : 40,
			sortable : false
		},{
			field : 'phone',
			title : '业主电话',
			width : 40,
			sortable : false
		},{
			field : 'serial',
			title : '订单号',
			width : 50,
			sortable : false
		},{
			field : 'status',
			title : '维修状态',
			width : 40,
			sortable : false,
			formatter: function(value){
			    if (value == 0 || value == 1) {
			        return '<font color="red">待处理</font>';
			    }
			    if (value > 1 && value < 6) {
			        return '<font color="green">处理中</font>';
			    }
			    else {
			        return "已完结";
			    }
			}
		},{
			field : 'description',
			title : '描述',
			width : 50,
			sortable : false
		},{
			field : 'expectTime',
			title : '预计上门时间',
			width : 50,
			sortable : false
		},{
			field : 'rowAddTime',
			title : '提交时间',
			width : 50,
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
		,{
			field : 'pictures',
			title : '完工图片',
			width : 40,
			sortable : false,
			formatter: function(value, row, index){
					if (value != "" && value != null) {
					   var str =  "<img style='width:40px;height:40px' src="+value+">";
					   return str;
					}
			}
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
		},
		{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
				str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
				str += '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
			    str += '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="addFun(\'{0}\');" src="{1}" title="派工"/>', row.id, '${basePath}/style/images/extjs_icons/pencil_add.png');
			    str += '&nbsp;&nbsp;&nbsp;&nbsp;';
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
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	parent.$.modalDialog({
		title : '编辑',
		width : 600,
		height : 400,
		href : '${basePath}/web/hangPicture/editPage?id=' + id,
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

function addFun(id) {
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	parent.$.modalDialog({
		title : '派工',
		width : 300,
		height : 200,
		href : '${basePath}/web/hangPicture/assignPage?id=' + id,
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
			if (currentUserId) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/hangPicture/delete', {
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
</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 65px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:60%;">
					<tr>		
						<tr>
						<c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="communityId" class=" span2" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<c:forEach items="${community }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						</c:if>
																			
					    <th>查询选项</th>
						<td><select name="key" class="span2" style="width:150px;" >
					          <option value="">--请选择--</option>
						      <option value="1">描述</option>
						      <option value="2">维修人员</option>
						      <option value="3">单元编号</option>
						      <option value="4">订单号</option>
						    </select></td>	
						<th>关键字</th>
						<td><input name="value" class="span2" style="width:150px;" /></td>						
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center'">
			<table id="dataGrid"></table>
		</div>
	</div>
	<!-- <div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'pencil_add'">派工</a>
	</div> -->

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">派工</div>
	</div>

</body>
</html>
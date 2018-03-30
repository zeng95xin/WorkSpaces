<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>楼盘</title>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/bjwdt/floor/dataGrid',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'code',
		pageSize : 10,
		pageList : [10,30,50,100],
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect:true,
		nowrap : false,
		columns : [ [ 
			{field : 'name', title : '名称', width : 150, align : 'center', sortable : false }
			,{field : 'descopentime', title : '开盘时间', width : 150, align : 'center', sortable : false }
			,{field : 'floorprice', title : '价格', width : 150, align : 'center', sortable : false }
			,{field : 'label', title : '标签', width : 150, align : 'center', sortable : false }
			,{field : 'flagsale', title : '是否在售', width : 150, formatter : function(value, row, index) {
				if(value){
					if(value == 1){
						return "是";
					}else{
						return "否";
					}
				}else{
					return "";
				}
			}}
			,{field : 'floorimg', title : '图片', width : 150, formatter : function(value, row, index) {
				return "<img src='"+value+"' style='height:40px;width:100px'/>";
			}}
		    ,{field : 'action', title : '操作', width : 250, formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.code, '${basePath}/style/images/extjs_icons/pencil.png');
				str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.code, '${basePath}/style/images/extjs_icons/cancel.png');
				str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				str += $.formatString('<img onclick="readingFun(\'{0}\');" src="{1}" title="查看详情"/>', row.code, '${basePath}/style/images/extjs_icons/book_open.png');
				return str;
			}}
		] ],
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
function readingFun(id) {
	window.open(
		'${basePath}/web/bjwdt/floor2/manager?florCode=' + id, window,
		"height=700, width=1000, top=10, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}
function  addFun(){
	parent.$.modalDialog({
		title : '添加',
		width : 600,
		height : 600,
		href : '${basePath}/web/bjwdt/floor/addPage',
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
		id = rows[0].code;
	}
	var rowData;
	var rowss = dataGrid.datagrid('getRows');
	for (var i = 0; i < rowss.length; i++) {
		if (rowss[i].code == id) {
			rowData = rowss[i];
			break;
		}
	}
	parent.$.modalDialog.rowData = rowData;
	parent.$.modalDialog({
		title : '编辑',
		width : 600,
		height : 600,
		href : '${basePath}/web/bjwdt/floor/editPage?code=' + id,
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
		id = rows[0].code;
	}
	parent.$.messager.confirm('询问', '您是否要删除 ', function(b) {
		if (b) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.post('${basePath}/web/bjwdt/floor/delete', {
				code : id
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
						<%-- <c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="communityId" class=" span2" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<c:forEach items="${community }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						</c:if> --%>
						<th>类型</th>
						<td><select name="flagSale" class=" span2" onchange="searchFun()" >
							<option value="" selected="selected">--全部--</option>
							<option value="1">--在售--</option>
							<option value="0">--非在售--</option>
						</select></td>
						<!-- <th>关键字</th>
						<td><input name="svalue" class="span2" /></td> 					
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空</a>
						</td> -->
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
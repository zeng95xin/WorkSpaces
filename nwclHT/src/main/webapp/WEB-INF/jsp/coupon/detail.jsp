<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>优惠券管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/coupon/dataGrid2',
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
			field : 'title',
			title : '名称',
			width : 50,
			sortable : false
		},
		{
			field : 'honourEnjoyName',
			title : '商家',
			width : 50,
			sortable : false
		},
		{
			field : 'type',
			title : '版块',
			width : 50,
			sortable : false,
            formatter: function(value){				
				if(value == "1"){
					return "酒店公寓";
				}else if(value == "2"){
					return "温泉养生";
				}else if(value == "3"){
					return "商圈特惠";
				}
			}
		},{
			field : 'end',
			title : '有效期至',
			width : 50,
			sortable : false
		},{
			field : 'status',
			title : '状态',
			width : 50,
			sortable : false
		},{
			field : 'getTimes',
			title : '领取次数',
			width : 50,
			sortable : false
		},{
			field : 'useTimes',
			title : '使用次数',
			width : 50,
			sortable : false
		},{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="checkFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${basePath}/style/images/extjs_icons/eye.png');
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
		width : 450,
		height : 600,
		href : '${basePath}/web/coupon/addPage',
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
		width : 450,
		height : 600,
		href : '${basePath}/web/coupon/editPage?id=' + id,
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
			if (currentUserId != id) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/web/coupon/delete', {
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

//评论
function checkFun(id) {
	if (id==typeof(undefined)||id == "undefined") {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	window.open('${basePath}/web/coupon/manager3?cid=' + id,
		'newwindow','height=500,width=830,top=150,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'
	);
}
</script>
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 65px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%;">
					<tr>															
					    <th>状态</th>
						<td><select name="status" class="span2" style="width:200px;" >
					          <option value="">--请选择--</option>
						      <option value="1">生效中</option>
						      <option value="0">已失效</option>
						    </select></td>	
						<th>版块</th>
						<td><select name="type" class=" span2" >
							<option value="">--请选择--</option>
							<option value="1">酒店公寓</option>
							<option value="2">温泉养生</option>
							<option value="3">商圈特惠</option>
						</select></td>
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

</body>
</html>
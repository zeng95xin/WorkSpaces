<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
  <head>
    <title>自荐列表</title>
    <jsp:include page="../inc.jsp"></jsp:include>
   <script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/web/IntroducingWebController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10,30,50,100 ],
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
/* 			frozenColumns : [ [  ] ], */
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 100,
				checkbox : true
			}, {
				field : 'title',
				title : '标题',
				width : 100,
				sortable : false
			}, /*{
				field : 'content',
				title : '内容',
				width : 100,
				sortable : false
			}, {
				field : 'imgPath',
				title : '图片名称',
				width : 100,
				sortable : true,
				formatter:function(value,row,index){
					return "<img src='"+value+"' style='height:50px;width:50px'  />";
				}
			}, {
				field : 'description',
				title : '个人介绍',
				width : 100,
				sortable : true
			},  */{
				field : 'name',
				title : '姓名',
				width : 100,
				/* sortable : true */
			}, {
				field : 'phone',
				title : '电话',
				width : 100,
				/* sortable : true */
			},{
				field : 'rowAddTime',
				title : '提交时间',
				width : 100,
				/* sortable : true */
			},  {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
				    str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="detailsFun(\'{0}\');" src="{1}" title="查看详情"/>', row.id, '${basePath}/style/images/extjs_icons/eye.png');
				    /* str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="sendFun(\'{0}\');" src="{1}" title="发布到业主讲堂"/>', row.id, '${basePath}/style/images/extjs_icons/book_next.png'); */
				    return str;
				}
			} ] ],
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

	

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前记录？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/IntroducingWebController/delete', {
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
					for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
					}
					$.getJSON('${basePath}/web/IntroducingWebController/batchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code=="SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
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

	
	function detailsFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '详情',
			width : 700,
			height : 600,
			href : '${basePath}/web/IntroducingWebController/introducingDetails?id=' + id,
			/* buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ] */
		});
	}
	
	function sendFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要把当前记录到发布业主讲堂？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/IntroducingWebController/IntroducingSendY', {
						id : id
					}, function(result) {
						if (result.code=="SUCCESS") {
							sendFun2(id);
						}else {
							parent.$.messager.alert('提示', result.message, 'info');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
			}
		});
	}
	
	function sendFun2(id) {
		parent.$.modalDialog({
			title : '详情',
			width : 600,
			height : 220,
			href : '${basePath}/web/IntroducingWebController/IntroducingSendPage?id=' + id,
			buttons : [ {
				text : '发送',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	
	function explainFun() {
		window.open(
			'${basePath}/web/IntroducingWebController/explain', window,
			"dialogHeight:500px;dialogWidth:1000px;status:no;");

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
				<table class="table table-hover table-condensed" style="width: 70%">
					<tr>
					<c:if test="${loginUser.roleId == 1 }">
						<td>小区</td>
						<td>
						<select name="communityId" style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
						<option value="-1">--请选择小区--</option>
						<c:forEach items="${community }" var="list">
							<option value="${list.id }">${list.name }</option>
						</c:forEach>
						</select>
						</td>
						</c:if>
						<td>查询选项</td>
						<td>
						<select name=option style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<!-- <option value="1">标题</option>
							<option value="2">内容</option> -->
							<option value="3">姓名</option>
							<option value="4">电话</option>
							<option value="5">提交时间</option>
						</select>
						</td>
						<td>关键字</td>
						<td><input class="easyui-textbox" style="width: 100px" name="key" class="span2" /></td>
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
		<a onclick="explainFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">自荐说明</a>
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="detailsFun();" data-options="iconCls:'eye'">查看详情</div>
		<!-- <div onclick="sendFun();" data-options="iconCls:'book_next'">发布到业主讲堂</div> -->
	</div>
</body>
</html>

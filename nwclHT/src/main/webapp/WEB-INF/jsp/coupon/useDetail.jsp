<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>优惠券详情</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${basePath}/web/coupon/dataGrid3?cid=${cid}',
							fit : true,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
							pageSize : 10,
							pageList : [ 10, 30, 50, 100 ],
							sortName : 'id',
							sortOrder : 'asc ',
							checkOnSelect : false,
							selectOnCheck : false,
							singleSelect : true,
							nowrap : false,
							columns : [ [
									{
										field : 'id',
										title : '序号',
										width : 50,
										sortable : false,
									},
									{
										field : 'userAccount',
										title : '帐号',
										width : 50,
										sortable : false
									},
									{
										field : 'userNickName',
										title : '昵称',
										width : 50,
										sortable : false
									},
									{
										field : 'userName',
										title : '姓名',
										width : 50,
										sortable : false
									},
									{
										field : 'rowAddTime',
										title : '领取时间',
										width : 50,
										sortable : false

									},
									{
										field : 'rowUpdateTime',
										title : '使用时间',
										width : 50,
										sortable : false,
										formatter : function(value, row, index){
											if(row.useTime >0){
												return value;
											}else{
												return "未使用";
											}
										}
									},
									{
									/* 	field : 'action',
										title : '操作',
										width : 50,
										formatter : function(value, row, index) {
											var str = '&nbsp;';
											str += $
													.formatString(
															'<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>',
															row.id,
															'${basePath}/style/images/extjs_icons/pencil.png');
											str += '&nbsp;&nbsp;&nbsp;';
											
											str += $
													.formatString(
															'<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>',
															row.id,
															'${basePath}/style/images/extjs_icons/cancel.png');
											return str; 
										}*/
									} ] ],
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

</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<table class="table table-hover table-condensed" style="width:100%;">
				<tr>
					<th>${model.title }</th>
					<th>状态</th>
					<td>${model.status }</td>
					<th>有效期至</th>
					<td>${model.end }</td>
				
				</tr>	
		</table>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
	</div>

	<!-- <div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div> -->

</body>
</html>
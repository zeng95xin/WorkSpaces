<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>车辆缴费信息</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : "${basePath}/web/depotPay/dataGrid",
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
		columns : [ [ 
		{
			field : 'cardNo',
			title : '车辆号',
			width : 15,
			sortable : false
		},
		{
			field : 'buserId',
			title : '人员名称',
			width : 10,
			sortable : false,
			formatter : function(value, row, index){
				return row.uaerName;
			}
		},
		{
			field : 'payChannel',
			title : '缴费渠道',
			width : 10,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 'alipay'){
					return "支付宝";
				}else if(value == 'wx'){
					return "微信支付";
				}
			}
		},
		{
			field : 'dateStr',
			title : '月份',
			width : 10,
			sortable : false
		},
		{
			field : 'payStatus',
			title : '是否交清',
			width : 9,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 1){
					return "有";
				}else if(value == 0){
					return "没有";
				}
			}
		},
		{
			field : 'payTime',
			title : '缴费时间',
			width : 15,
			sortable : false
			
		},
		{
			field : 'payNo',
			title : '缴费编号',
			width : 10,
			sortable : false
			
		},
		{
			field : 'itemName',
			title : '缴费项目名称',
			width : 20,
			sortable : false
			
		},
		{
			field : 'itemMoney',
			title : '缴费项目金额',
			width : 20,
			sortable : false
			
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
</script> 
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:1000px;">
					<tr>
						<th>车辆号:</th>
						<td>
						<input type="text" name="cardNo" id="cardNo" placeholder="请输入车辆号">
						</td>
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
</body>
</html>
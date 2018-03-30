<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>考勤管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/AttendanceRecord/dataGrid',
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
			field : 'communityId',
			title : '小区名称',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				return row.community;
			}
		},
		{
			field : 'role',
			title : '部门',
			width : 30,
			sortable : false,
			formatter : function(value, row, index){
				return row.deptName;
			}
		},
		{
			field : 'suserId',
			title : '人员名称',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				return row.uaerName;
			}
		},
		{
			field : 'isEarly',
			title : '是否早退',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 0){
					return "没有";
				}else if(value == 1){
					return "有";
				}
			}
		},
		{
			field : 'isLate',
			title : '是否迟到',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 0){
					return "没有";
				}else if(value == 1){
					return "有";
				}
			}
		},
		{
			field : 'outOutside',
			title : '是否外勤打卡',
			width : 30,
			sortable : false,
			formatter : function(value, row, index){
				if(value == 0){
					return "否";
				}else if(value == 1){
					return "是";
				}
			}
			
		},
		{
			field : 'inTime',
			title : '上班打卡时间,格式(HH:mm:ss)',
			width : 50,
			sortable : false
			
		},
		{
			field : 'outTime',
			title : '下班打卡时间,格式(HH:mm:ss)',
			width : 50,
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
						<c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="communityId" class=" span2"  onchange="communitySelect()" id="communityId">
							<option value="-1">--请选择--</option>
							<c:forEach items="${comus }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						</c:if>
						<!-- <th>部门</th>
						<td><select name="role" class=" span2" >
							<option value="-1">--请选择--</option>
							<c:forEach items="${departments }" var="list">
								<option value="${list.id }">${list.deptName }</option>
							</c:forEach>							
						</select></td> -->
						<th>是否早退</th>
						<td><select name="isEarly" class=" span2" >
							<option value="-1">--请选择--</option>
							<option value="1">有</option>
							<option value="0">没有</option>
						</select></td>
						<th>是否迟到</th>
						<td><select name="isLate" class=" span2" >
							<option value="-1">--请选择--</option>
							<option value="1">有</option>
							<option value="0">没有</option>
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
</body>
</html>
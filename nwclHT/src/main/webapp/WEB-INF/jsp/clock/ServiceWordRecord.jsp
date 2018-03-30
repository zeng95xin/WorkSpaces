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
<title>车辆出入记录</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">

var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : "${basePath}/web/ServiceWordRecord/dataGrid",
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
				return row.payChannel;
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
			field : 'imgPahta',
			title : '记录图片',
			width : 50,
			sortable : false,
			formatter : function(row, value, index){
				var str = "";
				for(var i=0;i<row.length;i++){
					str+="<img src='";
					str += row[i].imgPath;
					str+="' style='height:50px;width:50px;margin-left:10px;' />";
				}
				return str;
			}
		},
		{
			field : 'description',
			title : '描述',
			width : 50,
			sortable : false
		},
		{ 
			field : 'sound',
			title : '声音地址',
			width : 50,
			sortable : false,
			formatter : function(value, row, index){
				if (value != "" && value != null) {
					return $.formatString('<video src="{0}" style="width:150px;height:40px;" controls="controls"></video>', value);
					}else{
					return $.formatString('<audio src="{0}" style="width:40px;height:40px;" title="声音地址"/>', '没有声音');
				}
			}
		},
		{
			field : 'longitude',
			title : '位置经度',
			width : 50,
			sortable : false
		},
		{
			field : 'latitude',
			title : '位置纬度',
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
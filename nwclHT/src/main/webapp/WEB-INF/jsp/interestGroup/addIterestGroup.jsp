<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no,initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<script type="text/javascript">
	$(function() {
	
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/interest/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				console.trace(result);
				//alert(result);
				result = $.parseJSON(result);
				//alert("sssssss"+result);
				if (result.code=="SUCCESS") {
					
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为goodsManagement.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
	
	});

   
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>分类名称</th>
					<td><input name="name" type="text"
						class="easyui-validatebox span2" data-options="required:true" style="width: 150px" value=""></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3" style="height: 400px;">
						<textarea name="memo" id="memo" style="width:500px;height: 250px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
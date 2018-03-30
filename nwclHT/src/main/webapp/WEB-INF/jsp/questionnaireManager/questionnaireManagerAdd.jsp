<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no,initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- 引入kindEditor插件 -->
<link rel="stylesheet" href="${basePath}/js/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript" src="${basePath}/js/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>
<script type="text/javascript">
	
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/QuestionnaireManagerWebController/add',
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
				//console.trace(result);
				result = $.parseJSON(result);
				if (result.code == "SUCCESS") {
					parent.$.messager.alert('提示', result.message, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});


    
    
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				
				<tr>
					<th>问卷分类</th>
					<td><select name="classifyId" style="width: 100px" class="easyui-combobox">
						<option value="">--请选择分类--</option>
						<c:forEach items="${questionnaireClassify }" var="list">
							<option value="${list.id }">${list.classifyname }</option>
						</c:forEach>
						</select></td>
						
					<th>标题</th>
					<td>
					<input type="text" name="title" class="easyui-validatebox span2" 
						data-options="required:true" >
					</td>	
				</tr>
				
			</table>
		</form>
	</div>
</div>
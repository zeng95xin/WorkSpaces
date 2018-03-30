<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/js/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/UEditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/UEditor/umeditor.min.js"></script>
<script type="text/javascript" src="/js/UEditor/lang/zh-cn/zh-cn.js"></script> -->
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/department/add',
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
		<form id="form" method="post" encType="multipart/form-data"
			<%-- action="${basePath}/web/indexImg/add" --%>>
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 50px;">部门编号</th>
					<td>
						<input name="no" type="text" style="width: 200px;"
						placeholder="标题" class="easyui-validatebox span2"
						data-options="required:true">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">部门名称</th>
					<td>
						<input name="name" type="text" style="width: 200px;"
						placeholder="标题" class="easyui-validatebox span2"
						data-options="required:true">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">备注</th>
					<td>
						<textarea name="note"style="width: 210px;"
					    class="easyui-validatebox span2"></textarea>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



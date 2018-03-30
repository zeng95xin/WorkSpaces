<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			url : '${basePath}/maintainItem/edit',
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
				result = $.parseJSON(result);
				if (result.code=="SUCCESS") {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
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
					<th>维修项目名称</th>
					<td><input name="itemName"  value="${model.itemName }" type="text" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>收费金额</th>
					<td><input name="itemFee"  value="${model.itemFee }" type="text" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td style="height: 300px">
						<textarea name="itemMemo" rows="" style="width:450px;height:250px;">${model.itemMemo }</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" name="itemId" value="${model.itemId }" />
		</form>
	</div>
</div>
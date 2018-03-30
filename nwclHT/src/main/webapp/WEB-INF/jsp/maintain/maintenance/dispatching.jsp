<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			url : '${basePath}/maintenance/dispatching',
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
$.extend($.fn.validatebox.defaults.rules, {
    noteq: {
		validator: function(value,param){
			return value != "--请选择--";
		},
		message: '请选择一个选项'
    }
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" enctype="multipart/form-data">
			<table class="table table-hover table-condensed">
				<tr>
					<th>维修项目</th>
					<td>
						<select style="width:100px;" name="mainItemId" data-options="editable:false" class="easyui-combobox">
							<c:forEach items="${items }" var="item">
								<option value="${item.itemId }">${item.itemName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>维修人</th>
					<td>
						<select name="mainRepairmanId" data-options="editable:false,validType:'noteq[]'" class="easyui-combobox" style="width:150px;">
						    <option value="-1">--请选择--</option>
						    <c:forEach var="user" items="${repairUsers }">
						    	<option value="${user.userId }">${user.userName }</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>维修人电话</th>
					<td><input name="mainRepairmanNumber" data-options="required:true" type="text" class="easyui-validatebox" data-options="" /></td>
				</tr>
			</table>
			<input type="hidden" name="mainId" value="${mainId }" />
		</form>
	</div>
</div>
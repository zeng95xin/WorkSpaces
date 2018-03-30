<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/label/add',
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
				if (result.code=="SUCCESS") {
					parent.$.messager.alert('提示',result.message,'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}else {
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
					<th style="text-align: right; width: 160px;">标签类型</th>
						<td>
							<select name="typeId" style="width: 210px;" class="easyui-validatebox span2"
								data-options="required:true">
								<option> --- 请选择 --- </option>
								<option value="1"> 爱分享</option>
								<option value="2">邻里帮</option>
								<option value="3">业主市集</option>
								<option value="4">业主意见</option>
							</select>
						</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 160px;">标签内容</th>
					<td><input name="content" type="text" style="width: 210px;"
						class="easyui-validatebox span2" data-options="required:true" ></td>
				</tr>
			</table>
		</form>
	</div>
</div>



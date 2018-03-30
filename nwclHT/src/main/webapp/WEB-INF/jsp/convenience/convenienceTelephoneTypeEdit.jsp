<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/convenienceTelephoneType/edit',
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
					<td><input type="hidden" name="id"value="${c.id}"></td>
					<td><input type="hidden" name="communityId"value="${c.communityId}"></td>
				</tr>
			  	<tr>		
					<th colspan="2" style="text-align: center; color: red;">小区不能修改,如果如下在其他小区添加相同分类,<br/>请直接在添加选择该小区</th>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">名称</th>
					<td><input name="name" type="text" style="width: 210px;"
						placeholder="名称" class="easyui-validatebox span2"
						data-options="required:true" value="${c.name}"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



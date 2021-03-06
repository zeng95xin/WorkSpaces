<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/OwnersMarketWebController/activityCommentEdit',
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
		<form id="form" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${ownersMarketRating.id }" name="id">
			<table class="table table-hover table-condensed">
				<tr>
					<tr>
					<th>评论内容</th>
					<td colspan="3" style="height: 400px;">
						<textarea name="content" id="activity"  style="width:500px;height: 200px;">${ownersMarketRating.content }</textarea>
				</tr>
			</table>
		</form>
	</div>
</div>



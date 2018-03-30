<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}web/maintenance/setMansStatus',
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
				<c:if test="${model != null }">
					<input type="hidden" name="id" value="${model.id }">
				</c:if>
				<br>
				繁忙：
				<c:if test="${model != null }"> 
					<input type="text" name="status" style="width:100px;" value="${model.status }" /> 单
				</c:if>
				<c:if test="${model == null }"> 
					<input type="text" name="status" style="width:100px;"  /> 单
				</c:if>
				<br><br>
				维修状态：待维修的维修单大于<br>填写的单数则维修人员为繁忙状态
		</form>
	</div>
</div>



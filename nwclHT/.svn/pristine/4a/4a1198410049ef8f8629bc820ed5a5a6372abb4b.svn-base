<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/building/edit',
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
				<input type="hidden" name="id" value="${b.id}">
			<table class="table table-hover table-condensed">
				<c:if test="${loginUser.roleId == 1 }">
				<tr>
					<th style="text-align: right; width: 50px;">小区</th>
					<td><select name="communityId" style="width: 210px;" id="comId">
						<option value="">--请选择小区--</option>
					    <c:forEach items="${community}" var="list">
					    <option value="${list.id }" <c:if test="${list.id==b.communityId }">selected</c:if>>${list.name }</option>
					    </c:forEach>
					</select></td>
				</tr>
				</c:if>
				<tr>
					<th style="text-align: right; width: 160px;">楼宇名称</th>
					<td><input name="name" type="text" style="width: 210px;"
						placeholder="楼宇名称" class="easyui-validatebox span2"
						data-options="required:true" value="${b.name}"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">楼宇地址</th>
					<td><input name="address" type="text" style="width: 210px;"
						placeholder="楼宇地址" class="easyui-validatebox span2"
						value="${b.address}"></td>
				</tr>			
				<tr>
					<th style="text-align: right; width: 50px;">备注</th>
					<td>
					<textarea name="note"style="width: 210px;"
					    class="easyui-validatebox span2">${b.note}</textarea></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/maintenancePrice/edit',
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
					<td><input name="id" type="hidden" value="${m.id}"></td>
					<td><input name="communityId" type="hidden" value="${m.communityId}"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">名称</th>
					<td><input name="serviceProject" type="text" style="width: 210px;"
						placeholder="名称" class="easyui-validatebox span2"
						data-options="required:true"value="${m.serviceProject}" ></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">规格/单位</th>
					<td><select name="unit"  style="width: 210px;"
						 class="easyui-validatebox span2" >
						  <option value="个"  <c:if test="${m.unit == '个' }">selected</c:if>>个</option>
						  <option value="支"  <c:if test="${m.unit == '支' }">selected</c:if>>支</option>
						  <option value="只"  <c:if test="${m.unit == '只' }">selected</c:if>>只</option>
						  <option value="台"  <c:if test="${m.unit == '台' }">selected</c:if>>台</option>
						  <option value="盏"  <c:if test="${m.unit == '盏' }">selected</c:if>>盏</option>
						  <option value="米"  <c:if test="${m.unit == '米' }">selected</c:if>>米</option>
						  <option value="条"  <c:if test="${m.unit == '条' }">selected</c:if>>条</option>
						 </select></td>
				</tr>			
				<tr>
					<th style="text-align: right; width: 100px;">普通楼盘标准</th>
					<td><input name="normalServicePrice" type="text" style="width: 210px;"
						placeholder="元" class="easyui-validatebox span2"value="${m.normalServicePrice}"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">工时</th>
					<td><input name="seniorServicePrice" type="text" style="width: 210px;"
						placeholder="元" class="easyui-validatebox span2" value="${m.seniorServicePrice}"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



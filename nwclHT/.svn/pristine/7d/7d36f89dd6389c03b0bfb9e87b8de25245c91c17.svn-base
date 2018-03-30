<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/thing/add',
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
		<form id="form" method="post"  encType="multipart/form-data" action="${basePath}/web/thing/add">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 100px;">物品名称</th>
					<td><input name="name" type="text" style="width: 210px;"
						placeholder="物品名称" class="easyui-validatebox span2"
						data-options="required:true" ></td>
				</tr>	
				<tr>
					<th style="text-align: right; width: 100px;">所属小区</th>
					<td>
						<select id=""  name="communityId">
							<option>---请选择---</option>
							<c:forEach items="${coms }" var="m">
								<option value="${m.id }" >${m.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>						
				<tr>
					<th style="text-align: right; width: 100px;">物品数量</th>
					<td><input name="num" type="text" style="width: 210px;"
						placeholder="物品数量" class="easyui-validatebox span2"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">物品价格</th>
					<td><input name="compensationPrice" type="text" style="width: 210px;"
						placeholder="物品价格" class="easyui-validatebox span2"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">图片</th>
					<td><input type="file" name="file" id="file" class="file"  style="width: 210px;">
				</td>
				<tr>
					<th style="text-align: right; width: 100px;">物品载重</th>
					<td><input name="loadweight" type="text" style="width: 210px;"
						placeholder="物品载重" class="easyui-validatebox span2"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">物品规格尺寸</th>
					<td><input name="size" type="text" style="width: 210px;"
						placeholder="物品规格尺寸" class="easyui-validatebox span2"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



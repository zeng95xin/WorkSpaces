<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/IntroducingWebController/IntroducingSend',
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
		<input type="hidden" value="${lectureHallIntroducing.id }" name="id">
			<table class="table table-hover table-condensed">
			 <tr>
			 <c:if test="${loginUser.roleId == 1 }">
					<th>小区</th>
					<td><select name="communityId" style="width: 100px">
						<c:forEach items="${community }" var="list">
							<option value="${list.id }" <c:if test="${list.id==lectureHallIntroducing.communityId }">selected</c:if> >${list.name }</option>
						</c:forEach>
						</select></td>
						</c:if>
				</tr>
				<tr>
					<th>报名截止时间</th>
					<td colspan="3"><input name="endTime" type="text"
						class="easyui-datetimebox span2" data-options="required:true,editable:false" 
						></td>
						
				</tr>
				<tr>
					<th>活动结束时间</th>
					<td colspan="3"><input name="activityEndTime" type="text"
						class="easyui-datetimebox span2" data-options="required:true,editable:false" 
						></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${basePath}/js/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/hangPicture/edit',
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
				if (result.code == "SUCCESS") {
					parent.$.messager.alert('提示', result.message, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
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
		<form id="form" method="post"  >
			<input type="hidden" name="id" value="${t.id}">
			<table class="table table-hover table-condensed">
				
			
				<tr>
					<th style="text-align: right; width: 50px;">订单号</th>
					<td><input name="serial" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${t.serial}" readonly="readonly"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">提交时间</th>
					<td>
						<input  name="rowAddTime" class="easyui-datetimebox"  data-options="required:true" value="<fmt:formatDate
									value="${t.rowAddTime}" type="both"  />" style="width:170px" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">预计上门时间</th>
					<td>
					<input  name="expectTime" class="easyui-datetimebox" data-options="required:true" value="<fmt:formatDate
									value="${t.expectTime}" type="both"  />" style="width:170px">
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">描述</th>
					<td>
					<textarea name="description" style="width: 400px;height: 150px;"class="easyui-validatebox span2" >${t.description}
					</textarea>
					</td>
				</tr>
				
	
			</table>
		</form>
	</div>
</div>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		if(parent.$.modalDialog.rowData.appType == 1){
			$('#form input[name="appType"]').eq(0).attr('checked', 'true');
			$('#form input[name="appType"]').eq(0).attr('checked', 'checked');
		}else{
			$('#form input[name="appType"]').eq(1).attr('checked', 'true');
			$('#form input[name="appType"]').eq(1).attr('checked', 'checked');
		}
		if(parent.$.modalDialog.rowData.flagObliged == 1){
			$('#form input[name="flagObliged"]').eq(0).attr('checked', 'true');
		}else{
			$('#form input[name="flagObliged"]').eq(1).attr('checked', 'true');
		}
		$('#form input[name="version"]').val(parent.$.modalDialog.rowData.version);
		$('#form [name="desc"]').val(parent.$.modalDialog.rowData.desc);
		$('#form input[name="url"]').val(parent.$.modalDialog.rowData.url);
		$('#form').form({
			url : '${ctx}/web/bjwdt/version/edit',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				param.code = parent.$.modalDialog.rowData.code;
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
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
		<form id="form" method="post" encType="multipart/form-data">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 150px;">app类型</th>
					<td>
						<input name="appType" type="radio" value="1">安卓
						<input name="appType" type="radio" value="2">IOS
					</td>				
				</tr>
				<tr>
					<th style="text-align: right; width: 150px;">是否强制更新</th>
					<td>
						<input name="flagObliged" type="radio" value="1">强制<input name="flagObliged" type="radio" value="2">非强制
					</td>				
				</tr>
				<tr>
					<th style="text-align: right;">版本号</th>
					<td>
						<input name="version" type="text" class="easyui-validatebox" placeholder="版本号" data-options="required:true" style="width: 300px">
					</td>
				</tr>
				<tr>
					<th style="text-align: right;">描述</th>
					<td>
						<textarea name="desc" style="width:300px;height: 190px;"></textarea>
					</td>
				</tr>
				<tr>
					<th style="text-align: right;">链接地址</th>
					<td>
						<input name="url" type="text" class="easyui-validatebox" placeholder="链接地址" data-options="required:true" style="width: 300px">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>



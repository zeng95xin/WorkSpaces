<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${basePath}/js/ajaxfileupload.js" charset="utf-8"></script>
<!-- 引入kindEditor插件 -->
<link rel="stylesheet" href="${basePath}/js/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript" src="${basePath}/js/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#Content', {
				width : '95%',
				height : '400px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${basePath}/fileController/upload',
				fileManagerJson : '${basePath}/fileController/fileManage',
				afterChange : function() {
                   }  ,
				allowFileManager : true
				
			});									
			parent.$.messager.progress('close');
		}, 1);
		
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/announcementModel/edit',			
			onSubmit : function() {
				editor.sync();
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
		<form id="form" method="post" encType="multipart/form-data" >
			<input type="hidden" name="id" value="${model.id }">
			<table class="table table-hover table-condensed">

				<tr>
					<th style="text-align: right; width: 50px;">标题</th>
					<td><input name="title" type="text" style="width: 95%;"
						placeholder="标题" class="easyui-validatebox span2"
						data-options="required:true" value="${model.title }"></td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">内容</th>
					<td><textarea name="detail"  style="width:100px;height: 190px;" id="Content"
							class="easyui-validatebox span2">${model.detail }</textarea></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">备注</th>
					<td><textarea name="remark"  style="width:95%;height: 60px;"
							class="easyui-validatebox span2">${model.remark }</textarea></td>
				</tr>

			</table>
		</form>
	</div>
</div>

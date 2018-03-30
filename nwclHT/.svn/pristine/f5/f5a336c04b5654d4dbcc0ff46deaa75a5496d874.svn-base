<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/convenienceTelephone/add',
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
	
		function getTypes(){
			var communityId = $("#comId").val();
			if(communityId == ''){
				alert('请选择小区！');
			}
			$.ajax({
				type : 'get',
				url : '${basePath}/web/convenienceTelephone/getTypes',
				data : {'communityId' : communityId},
				dataType : 'json',
				error : function(){
					alert('发生错误');
				},
				success : function(data){
					if(data.code == "SUCCESS"){
						if(data.result != null){
							var sss= "<option> --- 请选择 ---</option>";
							for (var i = 0; i < data.result.length; i++) {
								sss += "<option value="+data.result[i].id+">"+data.result[i].name+"</option>";
							}
							$("#type").html(sss);
						}
					}
				}
			});
		}
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 50px;">所在小区</th>
					<td>
						<select name="communityId" id="comId"  style="width: 210px;" class="easyui-validatebox span2"
						onchange="getTypes()"
						data-options="required:true" >
							<option value="0">---请选择---</option>
							<c:forEach items="${comus }" var="c">
								<option value="${c.id }">${c.name }</option>
							</c:forEach>    					
						</select>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">分类</th>
					<td>
						<select name="type" id="type" style="width: 210px;" class="easyui-validatebox span2" 
						data-options="required:true" >
							<option>---请选择---</option>
							<c:forEach items="${types }" var="t">
								<option value="${t.id }">${t.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">名称</th>
					<td><input name="name" type="text" style="width: 210px;"
						placeholder="名称" class="easyui-validatebox span2"
						data-options="required:true" ></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">号码</th>
					<td><input name="telephone" type="text" style="width: 210px;"
						placeholder="号码" class="easyui-validatebox span2"
						data-options="required:true" ></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/doorControl/edit',
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
	
	function getBuilingFun(){
		var communityId = $("#community").val();
		if(communityId == ''){
			alert('请选择小区！');
		}
		$.ajax({
			type : 'post',
			url : '${basePath}/web/info/getBuildings',
			data : {'communityId' : communityId},
			dataType : 'json',
			error : function(){
				alert('发生错误');
			},
			success : function(data){
				if(data.code == "SUCCESS"){
					if(data.result != null){
						var sss= "<option>--请选择--</option><option value='-1'>大门</option>";
						for (var i = 0; i < data.result.length; i++) {
							sss += "<option value="+data.result[i].id+">"+data.result[i].name+"</option>";
						}
						$("#Building").html(sss);
						$("#dposition").html("");
						$("#btn_p").html("");
					}
				}
			}
		});
	}
	
	function gateFun(){
		var communityId = $("#community").val();
		if(communityId == ''){
			alert('请选择小区！');
		}
		var buidingId = $("#Building").val();
		if(buidingId == -1){
			$("#btn_p").html("<input type='button' value='选择楼栋' onclick='chooseB()' />");
		}else{
			$("#dposition").html("");
			$("#btn_p").html("");
		}
	}
	
	function chooseB(){
		var communityId = $("#community").val();
		if(communityId == ''){
			alert('请选择小区！');
		}
		var buidingId = $("#Building").val();
		if(buidingId == -1){
			$.ajax({
				type : 'post',
				url : '${basePath}/web/info/getBuildings',
				data : {'communityId' : communityId},
				dataType : 'json',
				error : function(){
					alert('发生错误');
				},
				success : function(data){
					if(data.code == "SUCCESS"){
						if(data.result != null){
							var sss= "";
							for (var i = 0; i < data.result.length; i++) {
								sss += "<input type='checkbox' name='gateBuildingId'  value="+data.result[i].id+" style='margin:2px;'/>"+data.result[i].name+"<br/>";
							}
							var str="<th style='text-align: right; width: 70px;'>选择楼栋</th><td>"+sss+"</td>";
							$("#dposition").html(str);
						}
					}
				}
			});
		}else{
			$("#dposition").html("");
		}
	};
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<input type="hidden" name="id" value="${model.id}">
			<input type="hidden" name="communityId" value="${model.communityId}">
			<table class="table table-hover table-condensed">
				<tr >
					<th style="text-align: right; width: 70px;">位置</th>
					<td>
						<select name="buildingId"  style="width: 210px;" id="Building" class="easyui-validatebox span2" 
						data-options="required:true" onchange="gateFun()">
							<option value="">--请选择--</option>
								<option value="-1" <c:if test="${-1 ==model.buildingId }">selected</c:if>>大门</option>
								<c:forEach items="${buildings}" var="list">
					    			<option value="${list.id }" <c:if test="${list.id ==model.buildingId }">selected</c:if>>${list.name }</option>
					   	 		</c:forEach>
						</select>
					</td>
					<td id="btn_p"></td>
				</tr>
				<tr id="dposition"></tr>
				<tr>
					<th style="text-align: right; width: 70px;">门禁名称</th>
					<td><input name="name" type="text" style="width: 210px;"
						placeholder="门禁名称" class="easyui-validatebox span2"
						value="${model.name }"
					   ></td>
				</tr>
				<%-- <tr>
					<th style="text-align: right; width: 70px;">门禁类型</th>
					<td>
						<select name="doorTypeId" style="width: 210px;" class="easyui-validatebox span2" data-options="required:true" >
							<c:forEach items="${doorType }" var="d">
								<option value="${d.id }" <c:if test="${d.id ==model.doorTypeId }">selected</c:if>>${d.name }</option>
							</c:forEach>
						</select>
					   </td>
				</tr> --%>
				<tr>
					<th style="text-align: right; width: 70px;">设备号</th>
					<td><input name="device" type="text" style="width: 210px;"
						placeholder="设备号" class="easyui-validatebox span2"
						value="${model.device }"
					   ></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">备注</th>
					<td>
					<textarea name="note"style="width: 210px;"
					    class="easyui-validatebox span2">${model.note }</textarea></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



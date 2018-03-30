<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}web/scheduling/add',
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
	function typeSelect(a) {
		if (a == 1){
			$("#all").hide();
		}else{
			$("#all").show();
		}
		if (a == -1) {
			$("#suserId").html("");
		}
		if ($("#Com").val() == -1) {
			alert("请选择小区！");
		} else {
			communityId = $("#Com").val();
			$.post('${basePath}web/scheduling/getSuser', {
				type : a,
				communityId : communityId
			}, function(data) {
				if (data.length > 0) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						str += "<option value="+data[i].id+">" + data[i].name
								+ "</option>";
					}
					$("#suserId").html(str);
				} else {
					$("#suserId").html("");
				}
			}, "json");//这里返回的类型有：json,html,xml,text
		}
	}
	
	function checkboxAllfun(){
		if($('#buildingAll').is(':checked')){
			$("input[name='building']").each(function(){this.checked=true});
		}else{
			$("input[name='building']").each(function(){this.checked=false});
		}
		
	}
	
	
	function communitySelect(a) {
		$("#suserId").html("");
		if (a == -1) {
			$("#buildingId").html("");
		}
		$.post('${basePath}web/scheduling/getBuilding', {
			communityId : a
		}, function(data) {
			if (data.length > 0) {
				var str = "<span style='display: block;' id='all'><input onclick='checkboxAllfun()' id='buildingAll'  type='checkbox' value='-1'/>所有楼栋</span>";
				for (var i = 0; i < data.length; i++) {
					str += "<input type='checkbox' name='building' value="+data[i].id+"/>" + data[i].name+"&nbsp&nbsp&nbsp&nbsp";
				}
				var typeStr = "<option>--请选择--</option>"
						+ "<option value='1'>管家</option>"
						+ "<option value='2'>保安</option>"
						+ "<option value='3'>维修人员</option>";

				$("#type").html(typeStr);
				$("#suserId").html("");
				$("#buildingId").html(str);
			} else {
				$("#buildingId").html("");
			}
		}, "json");//这里返回的类型有：json,html,xml,text
	}
	
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 100px;">小区</th>
					<td colspan="3"><select name="communityId" style="width: 200px" id="Com"
						onchange="communitySelect(this.value)">
							<option value="-1">--请选择小区--</option>
							<c:forEach items="${communitys }" var="c">
								<option value="${c.id }">${c.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">排班类型</th>
					<td><select id="type" name="type" style="width: 200px;"
						data-options="required:true" onchange="typeSelect(this.value)">
							<option value="-1">--- 请选择 ---</option>
							<option value="1">管家</option>
							<option value="2">保安</option>
							<option value="3">维修人员</option>
					</select></td>
					<th style="text-align: right; width: 100px;">值班人员</th>
					<td>
					<select id="suserId" name="suserId"  style="width: 200px;">
					</select>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">开始时间</th>
					<td>
						<input class="easyui-datetimebox" name="startTime" data-options="showSeconds:false"style="width:200px"/>
					</td>
					<th style="text-align: right; width: 100px;">结束时间</th>
					<td>
						<input class="easyui-datetimebox" name="endTime" data-options="showSeconds:false"style="width:200px"/>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">楼栋</th>
					<td colspan="3" id="buildingId">
						<c:if test="${loginUser.roleId != 1 }">
							<span style="display: block;" id="all"><input onclick="checkboxAllfun()" id="buildingAll"  type="checkbox" value="-1"/>所有楼栋</span>
							<c:forEach items="${building }" var="list">
								<input type="checkbox" name='building' value="${list.id }">${list.name }&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</c:if>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



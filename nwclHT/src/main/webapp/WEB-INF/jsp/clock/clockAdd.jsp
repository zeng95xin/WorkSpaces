<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/js/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/UEditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/UEditor/umeditor.min.js"></script>
<script type="text/javascript" src="/js/UEditor/lang/zh-cn/zh-cn.js"></script> -->
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}web/Clock/add',
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
	function setValue(value) {
		document.getElementById("distance").value = value;
	}
	function setValuel(value) {
		document.getElementById("region").value = value;
	}
	function setValuelis(value) {
		document.getElementById("longitude").value = value;
	}

	function dt() {
		$('#wid').window({
			title : '地图',
			width : 900,
			height : 700,
			resizable : true,
			href : '${basePath}/web/Clock/dt',
		});
		$('#win').window('open'); // open a window
		$('#win').window('close');

	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" encType="multipart/form-data">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 100px;">小区</th>
					<td><select name="communityId" id="communityId"
						style="width: 200px" class="easyui-validatebox span2"
						data-options="required:true">
							<option>--请选择小区--</option>
							<c:forEach items="${coms }" var="p">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
					</select></td>
					<th style="text-align: right; width: 50px;">部门</th>
					<td><select id="role" name="role" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:forEach items="${depts }" var="d">
								<option value="${d.id }">${d.deptName }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">地区的名称</th>
					<td><input name="region" id="region" type="text"
						style="width: 200px;" readonly="readonly" placeholder=""
						class="easyui-validatebox span2" data-options="required:true">
					</td>
					<th style="text-align: right; width: 50px;">经纬度</th>
					<td><input name="longitude" id="longitude" type="text"
						style="width: 200px;" readonly="readonly" placeholder=""
						class="easyui-validatebox span2" data-options="required:true">
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">有效距离</th>
					<td><input type="text" readonly="readonly" name="distance" id="distance"
						style="width: 200px;" placeholder=""
						class="easyui-validatebox span2" data-options="required:true">
					</td>
					<th style="text-align: right; width: 50px;"></th>
					<td><a href="../attached/clockdt.jsp" target="view_window"><input type="button" value="获取经纬度"></a>
				</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">上班时间</th>
					<td><input class="easyui-datetimebox" name="clockInTime"
						data-options="showSeconds:false" style="width: 200px" /></td>
					<th style="text-align: right; width: 50px;">下班时间</th>
					<td><input class="easyui-datetimebox" name="clockOutTime"
						data-options="showSeconds:false" style="width: 200px" /></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">上班延后时间</th>
					<td><input type="text" id="inInterval" name="inInterval"
						style="width: 200px;" value="0" /></td>
					<th style="text-align: right; width: 50px;">下班提前时间</th>
					<td><input type="text" id="outInterval" name="outInterval"
						style="width: 200px;" value="0" /></td>
				</tr>
				<tr>
			</table>
		</form>
	</div>
</div>



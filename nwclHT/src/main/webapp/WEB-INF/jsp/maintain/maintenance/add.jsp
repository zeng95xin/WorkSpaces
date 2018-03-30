<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no,initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/maintenance/add',
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
				if (result.code=="SUCCESS") {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});
function uploadImg(){
	var str="<input type='file' style='width:150px;' name='imgs' />";
	$("#imgtd").prepend(str);
}
function communitySelect2(temp){
	if(temp.value=="-1"){
		$("#building2").combobox("setValue",-1);
		return;
	}
	$("#building2").combobox("reload","${basePath}/maintenance/getBuilding?comId="+temp.value);
}
$.extend($.fn.validatebox.defaults.rules, {
    noteq: {
		validator: function(value,param){
			return value != "--请选择--";
		},
		message: '请选择一个选项'
    }
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" enctype="multipart/form-data">
			<table class="table table-hover table-condensed">
				<tr>
					<th>报修内容</th>
					<td>
						<input name="mainContent" type="text" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>详情描述</th>
					<td>
						<textarea name="mainDescription" data-options="required:true" class="easyui-validatebox" rows="" style="width:450px;height:50px;"></textarea>
					</td>
				</tr>
				<tr>
					<th>附件</th>
					<td id="imgtd">
						<a data-options="" onclick="uploadImg();" href="javascript:void(0);" class="easyui-linkbutton"style="font-weight: 600;">上传</a>
					</td>
				</tr>
				<tr>
					<th>维修项目</th>
					<td>
						<select style="width:100px;" name="mainItemId" data-options="editable:false,onSelect:communitySelect" class="easyui-combobox">
							<c:forEach items="${items }" var="item">
								<option value="${item.itemId }">${item.itemName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>小区：</th>
					<td>
						<select id="community2" name="comId" data-options="editable:false,onSelect:communitySelect2,validType:'noteq[]'" class="easyui-combobox" style="width:100px;">
						    <option value="-1">--请选择--</option>
						    <c:forEach var="com" items="${comms }">
						    	<option value="${com.comId }">${com.comName }</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>楼宇：</th>
					<td>
						<select id="building2" name="bId" data-options="editable:false,textField:'bName',valueField:'bId',validType:'noteq[]'" class="easyui-combobox" style="width:100px;">
						    <option value="-1">--请选择--</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>房间号</th>
					<td>
						<input name="mainRoom" type="text" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>报修人</th>
					<td>
						<select id="mainUserId" name="mainUserId" data-options="editable:false,validType:'noteq[]'" class="easyui-combobox" style="width:150px;">
						    <option value="-1">--请选择--</option>
						    <c:forEach var="user" items="${mainUsers }">
						    	<option value="${user.userId }">${user.userName }</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>报修人电话</th>
					<td><input name="mainUserNumber" type="text" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>预约时间</th>
					<td><input name="mainOppintTime" type="text" class="easyui-datetimebox" data-options="required:true,showSeconds:true" /></td>
				</tr>
				<tr>
					<th>维修人</th>
					<td>
						<select name="mainRepairmanId" data-options="editable:false,validType:'noteq[]'" class="easyui-combobox" style="width:150px;">
						    <option value="-1">--请选择--</option>
						    <c:forEach var="user" items="${repairUsers }">
						    	<option value="${user.userId }">${user.userName }</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>维修人电话</th>
					<td><input name="mainRepairmanNumber" data-options="required:true" type="text" class="easyui-validatebox" data-options="" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td>
						<textarea name="mainMemo" rows="" style="width:450px;height:100px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/admin/edit',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.alert('提示', result.message, 'info');
				} else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});

	//选择物业---加载小区
	$("#Property").change(
			function() {
				var fid = $(this).val();
				if (fid == '') {
					$("#Community").empty();
					$("#Community").append("<option value=''>请选择小区</option>");
					return;
				}
				$.ajax({
					type : "post",
					url : '${basePath}/web/info/getCommunities',
					data : {
						'propertyId' : fid
					},
					dataType : 'json',
					error : function() {
						alert('发生错误');
					},
					success : function(text) {
						if (text.result != null) {
							$("#Community").empty();
							$("#Community").append(
									"<option value=''>请选择小区</option>");
							$("#Employee").empty();
							$("#Employee").append(
									"<option value=''>请选择员工</option>");
							for (var i = 0; i < text.result.length; i++) {
								$("#Community").append(
										"<option value='"+text.result[i].id+"'>"
												+ text.result[i].name
												+ "</option>");
							}
						}
					}
				});
			});

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;">
		<form id="form" method="post">
			<input name="id" type="hidden" value="${model.id }">
			<table class="table table-hover table-condensed">
				<tr>
					<th>登录帐号</th>
					<td><input name="name" type="text" placeholder="请输入登录帐号"
						class="easyui-validatebox span2" data-options="required:true" readonly="readonly"
						value="${model.name }"></td>
				</tr>
				 <tr>
					<th>登录新密码</th>
					<td><input name="pwd" type="password" placeholder="请输入新密码" 
					class="easyui-validatebox span2" data-options="required:false" ></td>	
			    </tr>
				 <tr>
					<th>确认新密码</th>
					<td><input name="newPwd" type="password" placeholder="请输入新密码" 
					class="easyui-validatebox span2" data-options="required:false,validType:'eqPwd[\'#form input[name=pwd]\']'" ></td>	
			    </tr>
				<tr>
					<th>登录名称</th>
					<td><input name="employeeName" type="text"
						placeholder="请输入用户名称" class="easyui-validatebox span2"
						data-options="required:true" value="${model.employeeName }"></td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td><input name="phoneNumber" type="text"
						placeholder="请输入联系电话" class="easyui-validatebox span2"
						data-options="required:true" value="${model.phoneNumber }"></td>
					<!-- <td><input name="pwd" type="password" placeholder="请输入密码" class="easyui-validatebox span2" data-options="required:true"></td>	 -->
				</tr>
				<c:if test="${model.type != 1  }">
				<tr>
					<th>帐号类型</th>
					<td>
						<select name="type" style="width:140px;" class="easyui-validatebox span2" data-options="required:true">
							<option value="2" 
							<c:if test="${model.type == 2  }">selected = "selected"</c:if>>物业公司帐号</option>
							<option value="3"
							<c:if test="${model.type == 3  }">selected = "selected"</c:if>
							>小区管理员帐号</option>
						</select>
					</td>	
				</tr>
				</c:if>
				<tr>
					<th>用户角色</th>
					<td><select name="roleId" style="width:140px;"
						class="easyui-validatebox span2" data-options="required:true">
							<option value="${model.roleId }">
								<c:forEach items="${roles }" var="pp">
									<c:if test="${model.roleId == pp.id  }"><span>${pp.roleName }</span></c:if>
								</c:forEach>
							</option>
							<c:forEach items="${roles }" var="p">
									<option value="${p.id }">${p.roleName }</option>
								<c:if test="${p.id  ne 1 } ">
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
				<%-- <tr>
					<th>物业</th>
					<td><select name="propertyId" id="Property"
						style="width:140px;" class="easyui-validatebox span2"
						data-options="required:true">
							<option value="${model.propertyId }">${model.propertyName }</option>
							<c:forEach items="${props }" var="p">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
					</select></td>
				</tr> --%>
				<tr>
					<th>小区</th>
					<td><select name="communityIds" id="Community"
						style="width:140px;" class="easyui-combobox span2"
value="<c:forEach items="${community }" var="p"><c:if test="${communityId_strs.contains(p.id.toString().concat(',')) }">${p.name },</c:if></c:forEach>"
						data-options="required:true,multiple:true" multiple="multiple">
							<c:forEach items="${community }" var="p">
								<option value="${p.id }" <c:if test="${communityId_strs.contains(p.id.toString().concat(',')) }">selected="selected"</c:if> >${p.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>使用状态</th>
					<td><select name="status" id="Status"
						style="width:140px;" class="easyui-validatebox span2"
						data-options="required:true">
							<c:if test="${model.status == 1 }">
								<option value="1" id="">使用中</option>
								<option value="0" id="">停用</option>
							</c:if>
							<c:if test="${model.status == 0 }">
								<option value="0" id="">已停用</option>
								<option value="1" id="">使用</option>
							</c:if>
					</select></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><textarea name="note" cols="2" style="width:140px;"> ${model.note }</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
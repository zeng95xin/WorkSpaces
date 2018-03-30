<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/suser/detailEdit?suserId=${model.suserId}',
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
					<th style="text-align: right; width: 50px;">头像</th>
					<td colspan="3">
						<input type="hidden" name="id" value="${model.id }">
						<img src="${model.headPortrait }" id="img1"  style="height: 116px;width: 97px;margin-left: 40%;">
						<input type="file" name="img" hidden="hidden">
					</td>							
				</tr>
					<c:if test="${loginUser.roleId == 1 }">				
				<tr>
					<th style="text-align: right; width: 50px;">物业</th>
					<td>
						<select name="propertyId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<option value="${model.propertyId }">
								<c:forEach items="${props }" var="pp">
									<c:if test="${model.propertyId == pp.id  }"><span>${pp.name }</span></c:if>
								</c:forEach>
							</option>
							<c:forEach items="${props }" var="p">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</td>	
					<th style="text-align: right; width: 50px;">小区</th>
					<td>
						<select name="communityId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<option value="${model.communityId }">
								<c:forEach items="${coms }" var="c">
									<c:if test="${model.communityId == c.id  }"><span>${c.name }</span></c:if>
								</c:forEach>
							</option>
							<c:forEach items="${coms }" var="p">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</td>	
				</tr>
					</c:if>				
				<tr>
					<th style="text-align: right; width: 50px;">部门</th>
					<td>
						<select name="deptId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:forEach items="${depts }" var="p">
								<option value="${p.id }" <c:if test="${model.deptId == p.id  }">selected=selected</c:if> >${p.deptName }</option>
							</c:forEach>
						</select>
					</td>			
							
					<th style="text-align: right; width: 50px;">员工状态</th>
					<td>
						<select name="status" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:choose>
								<c:when test="${model.status == 1 }">
									<option value="1">在职</option>
									<option value="0">离职</option>
								</c:when>
								<c:when test="${model.status == 0 }">
									<option value="0">离职</option>
									<option value="1">在职</option>
								</c:when>
								<c:otherwise>
									<option value="1">在职</option>
									<option value="0">离职</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>					
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">姓名</th>
					<td>
						<input name="name" type="text" style="width: 200px;"
						placeholder="姓名" class="easyui-validatebox span2"
						data-options="required:true" value="${model.name }">
					</td>					
							
					<th style="text-align: right; width: 50px;">员工代码</th>
					<td>
						<input name="employeeNo" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${model.employeeNo }">
					</td>	
						
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">性别</th>
					<td>
						<select name="sex" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:choose>
								<c:when test="${model.sex == '男' }">
									<option value="男">男</option>
									<option value="女">女</option>
								</c:when>
								<c:when test="${model.sex == '女' }">
									<option value="女">女</option>
									<option value="男">男</option>
								</c:when>
								<c:otherwise>
									<option value="男">男</option>
									<option value="女">女</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>			
							
					<th style="text-align: right; width: 50px;">工作岗位</th>
					<td>
						<input name="position" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${model.position }">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">最高学历</th>
					<td>
						<select name="edBackground"  style="width: 200px;">
							<option value="${model.edBackground }">${model.edBackground }</option>
							<option value="小学">小学</option>
							<option value="初中">初中</option>
							<option value="高中">高中</option>
							<option value="大专">大专</option>
							<option value="本科">本科</option>
							<option value="本科以上">本科以上</option>
						</select >
					</td>			
							
					<th style="text-align: right; width: 50px;">身份证号</th>
					<td>
						<input name="idNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${model.idNumber }">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">出生日期</th>
					<td>
						<input class="easyui-datebox" name="birthday"
						data-options="required:true"  style="width:200px" value="${model.birthday }"/>
					</td>			
							
					<th style="text-align: right; width: 50px;">入职日期</th>
					<td>
						<input class="easyui-datebox" name="employedDate"
						data-options="required:true"  style="width:200px" value="${model.employedDate }"/>
					</td>				
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">手机号码</th>
					<td>
						<input name="mobilephoneNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${model.mobilephoneNumber }">
					</td>			
							
					<th style="text-align: right; width: 50px;">电话号码</th>
					<td>
						<input name="telephoneNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${model.telephoneNumber }">
					</td>					
				</tr>
				
				
				<tr>
					<th style="text-align: right; width: 50px;">备注</th>
					<td>
						<textarea name="note"style="width: 210px;"
					    class="easyui-validatebox span2">${model.note }</textarea>
					</td>
					<th style="text-align: right; width: 50px;">个人介绍</th>
					<td>
						<textarea name="intro"style="width: 210px;"
					    class="easyui-validatebox span2">${model.intro }</textarea>
					</td>
				</tr>	
			</table>
		</form>
	</div>
</div>



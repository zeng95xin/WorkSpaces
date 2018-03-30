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
<!-- <link href="/js/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/UEditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/UEditor/umeditor.min.js"></script>
<script type="text/javascript" src="/js/UEditor/lang/zh-cn/zh-cn.js"></script> -->
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/employee/add',
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
		<form id="form" method="post" encType="multipart/form-data"
			<%-- action="${basePath}/web/indexImg/add" --%>>
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 50px;">头像</th>
					<td>
						<input type="file" name="img" style="width: 200px;"
						class="easyui-validatebox span2">
					</td>							
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">物业</th>
					<td>
						<select name="propertyId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:forEach items="${props }" var="p">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</td>	
									
					<th style="text-align: right; width: 50px;">小区</th>
					<td>
						<select name="communityId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:forEach items="${coms }" var="p">
								<option value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">部门</th>
					<td>
						<select name="deptId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:forEach items="${depts }" var="d">
								<option value="${d.id }">${d.deptName }</option>
							</c:forEach>
						</select>
					</td>			
							
					<th style="text-align: right; width: 50px;">员工状态</th>
					<td>
						<select name="status" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<option value="1">在职</option>
							<option value="0">离职</option>
						</select>
					</td>					
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">姓名</th>
					<td>
						<input name="name" type="text" style="width: 200px;"
						placeholder="姓名" class="easyui-validatebox span2"
						data-options="required:true">
					</td>					
							
					<th style="text-align: right; width: 50px;">员工代码</th>
					<td>
						<input name="employeeNo" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>	
						
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">性别</th>
					<td>
						<select name="sex" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</td>			
							
					<th style="text-align: right; width: 50px;">工作岗位</th>
					<td>
						<input name="position" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">学历</th>
					<td>
						<input name="edBackground" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>			
							
					<th style="text-align: right; width: 50px;">身份证号</th>
					<td>
						<input name="idNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">出生日期</th>
					<td>
						<input class="easyui-datebox" name="birthday"
						data-options="required:true"  style="width:200px"/>
					</td>			
							
					<th style="text-align: right; width: 50px;">入职日期</th>
					<td>
						<input class="easyui-datebox" name="employedDate"
						data-options="required:true"  style="width:200px"/>
					</td>				
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">合同开始时间</th>
					<td>
						<input class="easyui-datebox" name="contractStartDate"
						data-options="required:true"  style="width:200px"/>
					</td>			
							
					<th style="text-align: right; width: 50px;">合同结束时间</th>
					<td>
						<input class="easyui-datebox" name="contractEndDate"
						data-options="required:true"  style="width:200px"/>
					</td>				
				</tr>
					<tr>
					<th style="text-align: right; width: 50px;">手机号码</th>
					<td>
						<input name="mobilephoneNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>			
							
					<th style="text-align: right; width: 50px;">电话号码</th>
					<td>
						<input name="telephoneNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>					
				</tr>
				
				
				<tr>
					<th style="text-align: right; width: 50px;">备注</th>
					<td>
						<textarea name="note"style="width: 210px;"
					    class="easyui-validatebox span2"></textarea>
					</td>
					<th style="text-align: right; width: 50px;">个人介绍</th>
					<td>
						<textarea name="intro"style="width: 210px;"
					    class="easyui-validatebox span2"></textarea>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



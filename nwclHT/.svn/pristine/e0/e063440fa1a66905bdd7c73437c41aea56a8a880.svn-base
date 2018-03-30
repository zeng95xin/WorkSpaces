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
			url : '${ctx}/web/suser/edit',
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
	
	function setImagePreview(fileId, imgId) {
		var docObj = document.getElementById(fileId);
		var localImag = document.getElementById(imgId);
		var fileList = docObj.files;
		for (var i = 0; i < fileList.length; i++) {
			var imgObjPreview = document.getElementById(imgId);

			//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
			var url = window.URL.createObjectURL(docObj.files[i]);
			var img = new Image();
			img.src = url;
			img.onerror = function() {
				alert("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
				document.getElementById(fileId).value = "";
				return false;
			};
			/* var imgObjPreview=document.getElementById("preview"); */
			if (docObj.files && docObj.files[i]) {
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
			} else {
				//IE下，使用滤镜
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById(imgId);
				//必须设置初始大小
				localImagId.style.width = "150px";
				localImagId.style.height = "150px";
				localImagId.style.margin = "10px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';
				document.selection.empty();
			}
		}
		return true;
	};
	
	function btn(n){
		$("#file"+n).click();
	}
	function suserRoleChange(){
		var dept = $("#role").val();
		console.log(dept);
		if(dept == 3){
			$(".repairManType").show();
		}else{
			$(".repairManType").hide();
		}
	}
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" encType="multipart/form-data"
			<%-- action="${basePath}/web/indexImg/add" --%>>
			<input name="id" type="hidden" value="${model.id }">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 50px;">头像</th>
					<td>
					<c:if test="${model.headPortrait ==''}">
						<img src="../images/user1.png" id="img1" style="height: 116px;width: 97px"  onclick="detailFun(${model.id})">
					</c:if>
					<c:if test="${model.headPortrait !=''}">
						<img src="${model.headPortrait }" id="img1" style="height: 116px;width: 97px"  onclick="detailFun(${model.id})">
					</c:if>	
						<input type="file" name="img" id="file1" class="file" style="width: 200px;display: none;"onchange="setImagePreview('file1', 'img1')"><br/>
						<input type="button" value="上传头像" style="margin-left: 10px;" onclick="btn(1)">
					</td>
											
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">姓名</th>
					<td colspan="3">
						<input name="name" type="text" style="width: 200px;"
						value="${model.name }"
						placeholder="姓名" class="easyui-validatebox span2"
						data-options="required:true">
					</td>
				</tr>
				
				
				<tr>
					<th style="text-align: right; width: 50px;">账号</th>
					<td>
						<input type="text" name="account" style="width: 200px;"
						value="${model.account }"
						class="easyui-validatebox span2">
					</td>			
							
					<th style="text-align: right; width: 50px;">密码</th>
					<td>
						<input type="password" name="password" style="width: 200px;"
						class="easyui-validatebox span2">
					</td>					
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">物业</th>
					<td>
						<select name="propertyId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<c:forEach items="${props }" var="p">
								<option <c:if test="${model.propertyId == p.id }">selected="selected"</c:if> value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</td>	
					<th style="text-align: right; width: 100px;">小区</th>
					<td>
						<select name="communityId" style="width: 200px" id="Com" onchange="communitySelect(this.value)">
						<option>--请选择小区--</option>
						<c:forEach items="${coms }" var="p">
								<option <c:if test="${model.communityId == p.id }">selected="selected"</c:if> value="${p.id }">${p.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">部门</th>
					<td>
						<select name="deptId" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true" onChange="departmentChange()">
							<c:forEach items="${depts }" var="d">
								<option
								<c:if test="${model.deptId == d.id }">selected="selected"</c:if>
								 value="${d.id }">${d.deptName }</option>
							</c:forEach>
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
					<th style="text-align: right; width: 50px;">角色类型</th>
					<td>
						<select id="role" name="role" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true" onChange="suserRoleChange()">
							<option <c:if test="${model.role == 1 }">selected="selected"</c:if> value="1">管家</option>
							<option <c:if test="${model.role == 2 }">selected="selected"</c:if> value="2">保安</option>
							<option <c:if test="${model.role == 3 }">selected="selected"</c:if> value="3">维修</option>
							<option <c:if test="${model.role == 4 }">selected="selected"</c:if> value="4">调度</option>
						</select>
					</td>
					<th class="repairManType" style="text-align: right; width: 50px;">维修类型</th>
					<td class="repairManType">
						<select name="types" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true,multiple:true" multiple="multiple">
							<c:forEach items="${types }" var="d">
								<option value="${d.id }" <c:if test="${relations_str.contains(d.id.toString()) }">selected="selected"</c:if>>${d.name }</option>
							</c:forEach>
						</select>
					</td>	
				</tr>
				
					
				<tr>
					<th style="text-align: right; width: 50px;">工号</th>
					<td>
						<input type="text" name="employeeNo" style="width: 200px;"
						value="${model.employeeNo }"
						class="easyui-validatebox span2">
					</td>	
					<th style="text-align: right; width: 50px;">性别</th>
					<td>
						<select name="sex" style="width: 200px;"
						class="easyui-validatebox span2" data-options="required:true">
							<option
							<c:if test="${model.sex == '男' }">selected="selected"</c:if>
							 value="男">男</option>
							<option
							<c:if test="${model.sex == '女' }">selected="selected"</c:if>
							 value="女">女</option>
							<option
							<c:if test="${model.sex == '保密' }">selected="selected"</c:if>
							 value="保密">保密</option>
						</select>
					</td>	
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">手机号码</th>
					<td>
						<input name="mobilephoneNumber" type="text" style="width: 200px;"
						value="${model.mobilephoneNumber }"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true">
					</td>	
					<th style="text-align: right; width: 50px;">电话号码</th>
					<td>
						<input name="telephoneNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						value="${model.telephoneNumber }">
					</td>							
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">最高学历</th>
					<td>
						<select name="edBackground"  style="width: 200px;">
							<option value="小学" <c:if test="${model.edBackground == '小学' }">selected="selected"</c:if>>小学</option>
							<option value="初中" <c:if test="${model.edBackground == '初中' }">selected="selected"</c:if>>初中</option>
							<option value="高中" <c:if test="${model.edBackground == '高中' }">selected="selected"</c:if>>高中</option>
							<option value="大专" <c:if test="${model.edBackground == '大专' }">selected="selected"</c:if>>大专</option>
							<option value="本科" <c:if test="${model.edBackground == '本科' }">selected="selected"</c:if>>本科</option>
							<option value="本科以上" <c:if test="${model.edBackground == '本科以上' }">selected="selected"</c:if>>本科以上</option>
						</select >
					</td>			
							
					<th style="text-align: right; width: 50px;">身份证号</th>
					<td>
						<input name="idNumber" type="text" style="width: 200px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true" value="${model.idNumber }">
					</td>					
				</tr>
				<%-- <tr>
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
				</tr> --%>
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



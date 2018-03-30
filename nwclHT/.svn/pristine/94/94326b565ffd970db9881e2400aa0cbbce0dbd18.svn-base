<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}web/maintenance/edit',
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
			<tr>			
					<td><input name="id" type="hidden" value="${m.id}"></td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">维修单号</th>
					<td><input name="serviceProject" type="text" style="width: 200px;"
						placeholder="维修单号" class="easyui-validatebox span2"
						data-options="required:true"value="${m.serial}" readonly="readonly"></td>
				
					<th style="text-align: right; width: 50px;">维修状态</th>
					<td>
					<c:if test="${m.status==0 || m.status == 1}">
					<font color="red">待处理</font>
					</c:if>
					<c:if test="${m.status>1 && m.status < 6}">
					<font>处理中</font>
					</c:if>
					<c:if test="${m.status== -1 || m.status==6 || m.status == 7}">
					<font color="green">已完结</font>
					</c:if>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">单元编号</th>
					<td><input name="serviceProject" type="text" style="width: 200px;"
						placeholder="房间号" class="easyui-validatebox span2"
						data-options="required:true"value="${m.room}"  readonly="readonly"></td>
				
					<th style="text-align: right; width: 50px;">手机账号</th>
					<td><input name="normalServicePrice" type="text" style="width: 200px;"readonly="readonly"
						placeholder="报修人" class="easyui-validatebox span2"value="${m.user}"></td>
				</tr>
				
				
				<tr>
					<th style="text-align: right; width: 50px;">报修时间</th>
					<td><input class="easyui-datetimebox"   name="rowAddTime" data-options="required:true"  readonly="readonly" value="<fmt:formatDate
									value="${m.rowAddTime}" type="both" />" style="width:150px;height:40px">
					</td>
				
					<th style="text-align: right; width: 50px;">价格</th>
					<td><input  type="text" style="width: 200px;" readonly="readonly"
					 class="easyui-validatebox span2"value="${m.payMoney}"></td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">维修人员</th>
					<td>
						<select name="repairMainId">
							<option value="${m.repairMainId }">
								<c:forEach items="${serviceUsers }" var="s">
									<c:if test="${s.id == m.repairMainId }">
										<span>${s.name }</span>
									</c:if>
								</c:forEach>
							</option>
							<c:forEach items="${serviceUsers }" var="s">
								<option value="${s.id }">${s.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">预计工时</th>
					<td>
						<input readonly="readonly" type="text" value="${m.expectTime }">
					</td>
					
					<th style="text-align: right; width: 50px;">延长工时</th>
					<td>
						<input readonly="readonly" type="text" value="${m.delayTime }">
					</td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 50px;">报修内容</th>
					<td>
					<textarea name="description"  style="width: 200px;height:80px;" readonly="readonly">${m.description}</textarea>
					</td>
				
					<th style="text-align: right; width: 50px;">勘探结果</th>
					<td>
					<textarea name="prospectingDescription"  style="width: 200px;height:80px;" readonly="readonly">${m.prospectingDescription}</textarea>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">报修图片</th>
					<td colspan="3">
						<c:forEach items="${m.images }" var="img">
							<img src="${img.imgPath }" style="width: 450px;height: 400px;margin-right: 5px;">
						</c:forEach>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>



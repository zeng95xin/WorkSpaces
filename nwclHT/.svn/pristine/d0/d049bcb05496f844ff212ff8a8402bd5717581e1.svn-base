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
					<th style="text-align: right; width: 100px;">维修单号</th>
					<td><input name="serial" type="text" style="width: 210px;"
						placeholder="维修单号" class="easyui-validatebox span2"
						data-options="required:true"value="${m.serial}" readonly="readonly"></td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 100px;">房间号</th>
					<td><input name="roomId" type="text" style="width: 210px;"
						placeholder="房间号" class="easyui-validatebox span2"
						data-options="required:true"value="${m.roomId}" ></td>
				</tr>			
				
				<tr>
					<th style="text-align: right; width: 100px;">报修人</th>
					<td><input name="buserId" type="text" style="width: 210px;"
						placeholder="报修人" class="easyui-validatebox span2"value="${m.buserId}"></td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 100px;">维修状态</th>
					<td>
					<c:if test="${m.status==0}">
					<font color="red">待处理</font>
					</c:if>
					<c:if test="${m.status==5}">
					<font>已完结</font>
					</c:if>
					<c:if test="${m.status!=0&&m.status!=5}">
					<font color="green">处理中</font>
					</c:if>
					</td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 100px;">报修内容</th>
					<td>
					<textarea name="description" style="width: 400px;height: 150px;"class="easyui-validatebox span2" >${m.description}
					</textarea>
						</td>
				</tr>
				
				
				<tr>
					<th style="text-align: right; width: 100px;">保修时间</th>
					<td><input class="easyui-datetimebox" name="rowAddTime" data-options="required:true" value="<fmt:formatDate
									value="${m.rowAddTime}" type="both" />" style="width:150px">
					</td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 100px;">维修人员</th>
					<td><input name="repairMainId" type="text" style="width: 210px;"
						placeholder="维修人员" class="easyui-validatebox span2" value="${m.repairMainId}" readonly="readonly"></td>
				</tr>
				
				
				<tr>
					<th style="text-align: right; width: 100px;">勘探结果</th>
					<td>
					<textarea name="prospectingDescription" style="width: 400px;height: 150px;"class="easyui-validatebox span2" >${m.prospectingDescription}
					</textarea>
					
				</tr>
				
				<tr>
					<th style="text-align: right; width: 100px;">完成时间</th>
					<td>
					<input class="easyui-datetimebox" name="rowUpdateTime" data-options="required:true" value="<fmt:formatDate
									value="${m.rowUpdateTime}" type="both" />" style="width:150px">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/BizUserWebController/generate',
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
		
		
		
		$(function(){
	 		$("#community").combobox({
	 			url:'${basePath}/web/SelectCommunityWebController/selectCommunity',
					valueField:'id',
					textField:'name',
					width:120,
					panelHeight:'auto',
					editable:false,
					onLoadSuccess:function(){
						$(this).combobox('setValue',"-1");
					},
					onSelect:function(){
					communityId = $(this).combobox('getValue');
					/*
					$('#myCombobox_city').combobox({
					url:'demo/getAllCitiesByPid.action?pid='+$value
					});
					*/
					$("#building").combobox("setValue","");
				$("#building").combobox('reload',"${basePath}/web/SelectCommunityWebController/selectBuilding?id="+communityId);
	 		}
	 		});
	 		
	 		$("#building").combobox({
						valueField:'id',
						textField:'name',
						width:120,
						panelHeight:'auto',
						editable:false,
						onLoadSuccess:function(){
						$(this).combobox('setValue',"-1");
							},
						onSelect:function(){
						building=$(this).combobox('getValue');
						
						$("#room").combobox("setValue","");
					$("#room").combobox('reload',"${basePath}/web/SelectCommunityWebController/selectRoom?id="+building);
						}
					});
	 		$("#room").combobox({
						valueField:'id',
						textField:'roomNo',
						width:120,
						panelHeight:'auto',
						editable:false,
						onLoadSuccess:function(){
						$(this).combobox('setValue',"-1");
							},
						onSelect:function(){
						room=$(this).combobox('getValue');
						$("#user").combobox("setValue","");
						$("#user").combobox('reload',"${basePath}/web/SelectCommunityWebController/selectUser?id="+room);
						}
					});
	 		$("#user").combobox({
						valueField:'id',
						textField:'name',
						width:120,
						panelHeight:'auto',
						editable:false,
						onLoadSuccess:function(){
						$(this).combobox('setValue',"-1");
							},
						onSelect:function(){
						room=$(this).combobox('getValue');
						}
					});
	 	
	 		});
	 		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<td>小区</td>
					<td><input name="" id="community"></td>
				</tr>
				<tr>
					<td>楼宇</td>
					<td>
						<select id="building" name="">
							<option value="-1">--前选择楼宇--</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>房间号</td>
					<td>
						<select id="room" name="">
							<option value="-1">--前选择房间号--</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>房主</td>
					<td>
						<select id="user" name="userId">
							<option value="-1">--前选择房主--</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>授权类型</td>
					<td>
						<select id="user" name="type"  class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="">--前选择授权类型--</option>
							<option value="01">租客</option>
							<option value="02">家属</option>
						</select>
					</td>
				</tr>
				<tr>
				<td>有效时间</td>
					<td><input name="validTime" type="text"
						class="easyui-datetimebox span2" 
						value=""></td>
				</tr>
				
				
			</table>
		</form>
	</div>
</div>



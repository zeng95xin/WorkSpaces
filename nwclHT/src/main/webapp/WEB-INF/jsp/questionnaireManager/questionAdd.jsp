<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no,initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />

<style>
.text-box,.text-box2{width: 600px;margin: auto;}
.item{margin-top: 10px;width: 100%;clear: both;display: inline-block;}
.lspan{width: 20%;display: block;float: left;text-align: right;}
.input_text{float: left;width: 50%;}
.box-delete{float: left;cursor: pointer;}
</style>
<script type="text/javascript">
	
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/QuestionWebController/add',
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
				//console.trace(result);
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
	<script type="text/javascript">
		$("#add").click(function(){
		$(".text-box").append('<div class="item"><span class="lspan">1:</span> <input name="content" type="text" class="input_text"><span class="box-delete">×</span></div>');
		$(".box-delete").click(function(){
			$(this).parent(".item").remove();
		});
		});
	$(".box-delete").click(function(){
		$(this).parent(".item").remove();
	});
	
	function selectchange(){
		if($("#selectbox option:selected").val()==3){
			$(".text-box").hide();
			$(".text-box2").show();
		}else{
			$(".text-box2").hide();
			$(".text-box").show();
		}
	}

</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post">
				<input type="hidden" value="${id }" name="id">
			<table class="table table-hover table-condensed">
				<tr>
					<th>类型</th>
					<td><select name="type" id="selectbox" onchange="selectchange()" style="width: 100px">
						<option value="-1">--请选择--</option>
						<option value="1">单选题</option>
						<option value="2">多选题</option>
						<option value="3">填空题</option>
						</select></td>
						
					<th>题目</th>
					<td>
					<input type="text" name="title" class="easyui-validatebox span2" 
						data-options="required:true" >
					</td>	
				</tr>
				<tr>
				<th colspan="4">选项</th>
				</tr>
				<tr>
				<td colspan="4">	
				<div class="text-box">
					<input type="button" value="添加" id="add">
					<div class="item"><span class="lspan">1:</span> <input name="content" type="text" class="input_text"><span class="box-delete">×</span></div>
					<div class="item"><span class="lspan">2:</span> <input name="content" type="text" class="input_text"><span class="box-delete">×</span></div>
					<div class="item"><span class="lspan">3:</span> <input name="content" type="text" class="input_text"><span class="box-delete">×</span></div>
					<div class="item"><span class="lspan">4:</span> <input name="content" type="text" class="input_text"><span class="box-delete">×</span></div>
				</div>
				<div class="text-box2" style="display:none">
					<div class="item"><span class="lspan">1:</span> <input type="text" name="contentsss" class="input_text"></div>
				</div>
				</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>
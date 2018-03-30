<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>住户管理</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/BizUserWebController/dataGrid',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [10,30,50,100],
		sortName : 'id',
		sortOrder : 'asc ',
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect:true,
		nowrap : false,
		columns : [ [ {
			field : 'unitNo',
			title : '单元编号',
			width : 50,
			sortable : false
		},{
			field : 'name',
			title : '业主姓名',
			width : 50,
			sortable : false
		},{
			field : 'phone',
			title : '业主账号（手机号）',
			width : 100,
			sortable : false
		},{
			field : 'nickname',
			title : '业主昵称',
			width : 100,
			sortable : false
		},{
			field : 'headPortrait',
			title : '头像',
			width : 100,
			sortable : false,
			formatter : function(value, row, index){
				if(value != null){
					return "<img src='"+value+"' style='height:50px;width:50px'  />";
				}
			}
		},{
			field : 'sex',
			title : '性别',
			width : 100,
			sortable : false
			
		},{
			field : 'owner',
			title : '身份',
			width : 100,
			sortable : false
			
		},/* {
			field : 'point',
			title : '积分',
			width : 100,
			sortable : false
			
		}, */{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
			    str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
				str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				<!--str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');--> 
				if(row.owner == "业主"){
					str += '&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="addBUserFun(\'{0}\');" src="{1}" title="添加成员"/>', row.id, '${basePath}/style/images/extjs_icons/new.png');
			    }
			    return str;
			} 
		}] ],
		toolbar : '#toolbar',
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow', rowIndex);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
	parent.$.messager.progress('close');
	});
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));	
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	
	function generateCodeFun() {
		parent.$.modalDialog({
			title : '生成授权码(业主已经注册)',
			width : 600,
			height : 400,
			href : '${basePath}/web/BizUserWebController/codePage',
			buttons : [ {
				text : '生成',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	
	function generateCodeFun2() {
		parent.$.messager.prompt("请输入业主手机号", "手机号", function(r){
			if(!r){
				return;
			}else{
				$.getJSON("${basePath}/web/BizUserWebController/updateInfo",{msg:r},function(data){
					if(data.code=="SUCCESS"){
						parent.$.modalDialog({
							title : '生成授权码',
							width : 400,
							height : 200,
							href : '${basePath}/web/BizUserWebController/codePage2',
							buttons : [ {
								text : '生成',
								handler : function() {
									parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
									var f = parent.$.modalDialog.handler.find('#form');
									f.submit();
								}
							} ]
						});
					}else{
						parent.$.messager.alert("提示",data.message,"warning");
					}
				});
			}
		});
	}
	
	function  addFun(){
		parent.$.modalDialog({
			title : '添加住户',
			width : 700,
			height : 320,
			href : '${basePath}/web/BizUserWebController/addManager',
			buttons : [{
					text : '添加',
					handler : function(){
						parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
				} 
			}]
		});
	}


	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除住户信息 ', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';
				if (currentUserId) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/BizUserWebController/delete', {
						id : id
					}, function(result) {
						if (result.code=="SUCCESS") {
							parent.$.messager.alert('提示', result.message, 'info');
							dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除！'
					});
				}
			}
		});
	}
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑&nbsp;&nbsp;&nbsp;图片上传支持格式：JPG、JPEG、PNG、GIF 大小2M以内',
			width : 500,
			height : 380,
			href : '${basePath}/web/BizUserWebController/editPage?id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function addBUserFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '添加住户&nbsp;&nbsp;&nbsp;图片上传支持格式：JPG、JPEG、PNG、GIF 大小2M以内',
			width : 600,
			height : 400,
			href : '${basePath}/web/BizUserWebController/addBUserPage?id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}


	function communitySelect(){
		var a = $("#communityId").val();
		$.post('${basePath}/web/BizUserWebController/getBuilding',{communityId : a},
				 function(data){
				   if (data.length > 0) {
					var str = "<option value='-1'>请选择</option>";
					for (var i = 0; i < data.length; i++) {
						str += "<option value="+data[i].id+">"+data[i].name+"</option>";
					}
					$("#buildingId").html(str);
				}
			 },
			 "json");//这里返回的类型有：json,html,xml,text
	}

</script>
  </head>
  
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:900px;">
					<tr>
						<th>小区</th>
						<td><select name="communityId" class=" span2"  onchange="communitySelect()" id="communityId">
							<option value="-1">--请选择--</option>
							<c:forEach items="${comus }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						<th>楼栋</th>
						<td><select name="buildingId" class=" span2"  id="buildingId">
							<option value="-1">--请选择--</option>
						</select></td>
						<th>查询选项</th>
						<td><select name="option" class=" span2" >
							<option value="-1">--请选择--</option>
							<option value="1">单元编号</option>
							<option value="2">业主姓名</option>
							<option value="4">业主昵称</option>
							<option value="3">业主账号（手机号）</option>
						</select></td>
						<th>关键字</th>
						<td><input name="key" class="span2" /></td>						
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>	
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
	<a onclick="generateCodeFun2();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">生成授权码</a>
		<!--<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div> 
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		 <a onclick="generateCodeFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">生成授权码(业主已经注册)</a>
		<a onclick="generateCodeFun2();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">生成授权码</a>-->
	</div>
	
</body>
</html>
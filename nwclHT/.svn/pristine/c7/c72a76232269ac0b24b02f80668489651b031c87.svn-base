<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<title>维修单据管理</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<style type="text/css">
	.pagination-num{margin-top: 10px;}
</style>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/maintenance/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'mainId',
			pageSize : 10,
			pageList : [ 10,30,50,100 ],
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			remoteSort: false,
			columns : [ [ 
				{field : 'mainNo',title : '维修单号',width : 50},
				{field : 'community',title : '小区名称',width : 50,formatter : function(value,row,index){
					return value.comName;
				}},
				{field : 'building',title : '楼栋名称',width : 50,formatter : function(value,row,index){
					return value.bName;
				}},
				{field : 'mainRoom',title : '房间号',width : 50},
				{field : 'nuser',title : '报修人',width : 50,formatter:function(value,row,index){
					var str="";
					if(value!=null){
						str+=value.userName;
					}
					return str;
				}},
				{field : 'mainStatus',sortable:true,title : '维修状态',width : 50,formatter:function(value,row,index){
					if(value=="1_REPAIRS"){
						return "<span style='color:red;'>待处理</span>";
					}else if(value=="2_DISTRIBUTE"||value=="3_OPPINT"||value=="4_ARRIVING"){
						return "<span style='color:green;'>处理中</span>";
					}else{
						return "已完结";
					}
				}},
				{field : 'mainUserNumber',title : '报修人联系电话',width : 50},
				{field : 'mainContent',title : '报修内容',width : 50},
				{field : 'rawAddTime',sortable:true,title : '报修时间',width : 50},
				{field : 'mainOppintTime',sortable:true,title : '预约时间',width : 50},
				{field : 'mainRepairman',title : '维修人员',width : 50,formatter:function(value,row,index){
					var str="";
					if(value!=null){
						str+=value.userName;
					}
					return str;
				}},
				{field : 'mainRepairmanNumber',title : '维修人员联系电话',width : 50},
				{field : 'mainDoneTime',sortable:true,title : '完成时间',width : 50},
				{field : 'mainOperator',title : '操作人',width : 50},
				{field : 'mainMemo',title : '备注',width : 50},
				{field : 'maintenanceRating',title : '评价',width : 50,formatter:function(value,row,index){
					var str="";
					if(value!=null){
						str+=value.ratingContent;
					}
					return str;
				}},
				{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.mainId, '${basePath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.mainId, '${basePath}/style/images/extjs_icons/cancel.png');
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');
			},
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
	});

	

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].mainId;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前记录？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${basePath}/maintenance/delete', {
					id : id
				}, function(result) {
					if (result.code=="SUCCESS") {
						parent.$.messager.alert('提示', result.message, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}

	function batchDeleteFun() {
		var rows = dataGrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].mainId);
					}
					$.getJSON('${basePath}/maintenance/batchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code=="SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						parent.$.messager.alert('提示', result.message, 'info');
						parent.$.messager.progress('close');
					});
				}
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].mainId;
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 700,
			height : 830,
			href : '${basePath}/maintenance/to_edit?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 700,
			height : 720,
			href : '${basePath}/maintenance/to_add',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
function communitySelect(temp){
	if(temp.value=="-1"){
		$("#building").combobox("setValue",-1);
		return;
	}
	$("#building").combobox("reload","${basePath}/maintenance/getBuilding?comId="+temp.value);
}
function searchTypeSelect(temp){
	var date =new Date();
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var str = y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	if(temp.value=="5"||temp.value=="6"||temp.value=="9"||temp.value=="12"){
		$("#keyword").datebox({
			value:str,
		    showSeconds: true
		});
	}else{
		$("#keyword_td").html("<input id='keyword' placeholder='选择日期，将会查询当天的信息' name='keyword' class='span3' /></td>");
	}
}
function showDetails(){
	var row = dataGrid.datagrid('getSelections')[0];
	console.log(row);
	$("#detail_userName").html(row.nuser.userName);
	$("#detail_postition").html(row.community.comName+row.building.bName+row.mainRoom);
	$("#detail_number").html(row.mainUserNumber);
	$("#detail_mainDescription").html(row.mainDescription);
	var str_imgs="";
	for(var i=0 ;i<row.images.length;i++){
		str_imgs+="<img style='width:40px;height:40px;' src='${basePath}/"+row.images[i].imgPath+"' />&nbsp;&nbsp;";
	}
	$("#detail_images").html(str_imgs);
	var str_progress="";
	var str_operation="";
	str_progress+=row.rawAddTime+"提交报修单<br/>";
	str_progress+=row.rawAddTime+"中心接收报修单，准备派发<br/>";
	if(row.mainStatus!="1_REPAIRS"){
		str_progress+=row.mainDistributeTime+"中心已派发<br/>";
		if(row.mainStatus!="2_DISTRIBUTE"){
			str_progress+=row.mainOrderTime+"维修人员"+row.mainRepairman.userName+"已接单<br/>";
			if(row.mainStatus!="3_OPPINT"){
				str_progress+=row.ArriveTime+"维修人员"+row.mainRepairman.userName+"已上门维修<br/>";
				if(row.mainStatus!="4_ARRIVING"){
					str_progress+=row.mainDoneTime+"维修人员"+row.mainRepairman.userName+"维修完成";
				}
			}
		}
	}else{
		str_operation+="<a id='detail_search' href='#' onclick='dispatching("+row.mainId+")' class='easyui-linkbutton' data-options=''>派工</a>";
	}
	$("#detail_progress").html(str_progress);
	$("#detail_operation").html(str_operation);
	$("#detail_search").linkbutton({});
	$("#showDetails").show().dialog({
		title:"详情",
	    width: 400,
	    height: 300
	});
}
function dispatching(id){
	$("#showDetails").dialog("close");
	parent.$.modalDialog({
		title : '派工',
		width : 400,
		height : 200,
		href : '${basePath}/maintenance/to_dispatching?id='+id,
		buttons : [ {
			text : '派工',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width: 90%">
					<tr>
						<th>小区：</th>
						<td>
							<select id="community" name="comId" data-options="editable:false,onSelect:communitySelect" class="easyui-combobox" style="width:100px;">
							    <option value="-1">--请选择--</option>
							    <c:forEach var="com" items="${comms }">
							    	<option value="${com.comId }">${com.comName }</option>
							    </c:forEach>
							</select>
						</td>
						<th>楼宇：</th>
						<td>
							<select id="building" name="bId" data-options="editable:false,textField:'bName',valueField:'bId'" class="easyui-combobox" style="width:100px;">
							    <option value="-1">--请先选择小区--</option>
							</select>
						</td>
						<th>维修状态：</th>
						<td>
							<select name="status" data-options="editable:false" class="easyui-combobox" style="width:100px;">
							    <option value="-1">--请选择--</option>
							    <option value="1">待处理</option>
							    <option value="2">处理中</option>
							    <option value="3">已完结</option>
							</select>
						</td>
						<th>查询选项：</th>
						<td>
							<select id="searchType" name="searchType" data-options="onSelect:searchTypeSelect,editable:false" class="easyui-combobox" style="width:100px;">
							    <option value="-1">--请选择--</option>
							    <option value="1">维修单编号</option>
							    <option value="2">房间号</option>
							    <option value="3">内容</option>
							    <option value="4">报修人姓名</option>
							    <option value="7">操作人</option>
							    <option value="8">联系电话</option>
							    <option value="10">维修人员姓名</option>
							    <option value="11">备注</option>
							    <option value="5">报修时间</option>
							    <option value="6">预约时间</option>
							    <option value="9">接单时间</option>
							    <option value="12">完结时间</option>
							</select>
						</td>
						<th>关键字：</th>
						<td id="keyword_td"><input id="keyword" placeholder="选择日期，将会查询当天的信息" name="keyword" class="span3" /></td>
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
		<div id="showDetails" style="width: 120px; display: none;">
	<table>
		<tr>
			<td style="width:100px; text-align: right;">业主姓名：</td>
			<td id="detail_userName"></td>
			<td>位置：</td>
			<td id="detail_postition"></td>
		</tr>
		<tr>
			<td style="width:100px; text-align: right;">联系电话：</td>
			<td colspan="3" id="detail_number"></td>
		</tr>
		<tr>
			<td style="width:100px; text-align: right;">问题描述：</td>
			<td colspan="3" id="detail_mainDescription"></td>
		</tr>
		<tr>
			<td style="width:100px; text-align: right;">图片：</td>
			<td colspan="3" id="detail_images"></td>
		</tr>
		<tr>
			<td style="width:100px; text-align: right;">报修进度：</td>
			<td colspan="3" id="detail_progress"></td>
		</tr>
		<tr>
			<td colspan="4" id="detail_operation" style="text-align: right;"></td>
		</tr>
		
	</table>
	</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		<div onclick="showDetails();" data-options="iconCls:'pencil'">查看详情</div>
	</div>
	
</body>
</html>
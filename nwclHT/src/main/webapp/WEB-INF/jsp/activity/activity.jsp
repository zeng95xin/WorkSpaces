<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
  <head>
    <title>社区文化</title>
    <jsp:include page="../inc.jsp"></jsp:include>
   <script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/web/activityController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10,30,50,100 ],
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
/* 			frozenColumns : [ [  ] ], */
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 50,
				checkbox : true
			}, {
				field : 'type',
				title : '栏目',
				width : 100,
				formatter:function(value){
					if(value == 1){
						return "文艺活动";
					}
					if(value == 2){
						return "义工活动";
					}
					if(value == 3){
						return "关爱老人";
					}
					
				}
			},{
				field : 'title',
				title : '标题',
				width : 150,
			/* }, {
				field : 'content',
				title : '内容',
				width : 300, */
			}, {
				field : 'imgPath',
				title : '图片',
				width : 120,
				formatter : function(value,row,index){
					if(value != null){
					return "<img src='"+value+"' style='height:80px;width:100px'/>";
				}
					}
			}, {
				field : 'state',
				title : '状态',
				width : 100,
				formatter : function(value,row,index){
					if(value == "报名中"){
						return "<font color='gree'>报名中</font>";
					}else{
						return value;
					}
				}
			}, {
				field : 'publish',
				title : '发布人',
				width : 100,
			},{
				field : 'isTop',
				title : '是否置顶',
				width : 60,
				formatter : function(value,row,index){
					if(value == 1){
						return "是";
					}else{
						return "否";
					}
					
				}
			}, {
				field : 'rowAddTime',
				title : '发布时间',
				width : 100,
			},{
				field : 'endTime',
				title : '报名截止时间',
				width : 100,
			},  {
				field : 'action',
				title : '操作',
				width : 250,
				formatter : function(value, row, index) {
					var top = "置顶";
					if(row.isTop == 1){
						top = "取消置顶";
					}
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="topFun(\'{0}\');" src="{1}" title="{2}"/>', row.id, '${basePath}/style/images/extjs_icons/tick.png', top);
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
				    str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="ratingFun(\'{0}\');" src="{1}" title="活动评论"/>', row.id, '${basePath}/style/images/extjs_icons/comments.png');
				    str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="registerFun(\'{0}\');" src="{1}" title="报名列表"/>', row.id, '${basePath}/style/images/extjs_icons/eye.png');
				    str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="readingFun(\'{0}\');" src="{1}" title="阅读记录"/>', row.id, '${basePath}/style/images/extjs_icons/book_open.png');
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
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前记录？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/activityController/delete', {
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
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
					}
					$.getJSON('${basePath}/web/activityController/batchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code=="SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
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
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑&nbsp;&nbsp;&nbsp;图片上传支持格式：JPG、JPEG、PNG、GIF 大小20M以内',
			width : 700,
			height : 500,
			href : '${basePath}/web/activityController/editPage?id=' + id,
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

	function addFun() {
		parent.$.modalDialog({
			title : '添加&nbsp;&nbsp;&nbsp;图片上传支持格式：JPG、JPEG、PNG、GIF 大小20M以内',
			width : 700,
			height : 500,
			href : '${basePath}/web/activityController/addPage',
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
	
	function ratingFun(id) {
		window.open(
			'${basePath}/web/ActivityRatingWebController/manager?id=' + id, window,
			"dialogHeight:500px;dialogWidth:1000px;status:no;");
	}
	
	
	function registerFun(id) {
		window.open(
			'${basePath}/web/ActivityRegisterWebController/manager?id=' + id, window,
			"dialogHeight:500px;dialogWidth:1000px;status:no;");
	}
	function readingFun(id) {
		window.open(
			'${basePath}/web/readingRecord/manager?contentId=' + id+'&type=1', window,
			"dialogHeight:500px;dialogWidth:800px;status:no;");
	}
	
	
	

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	function topFun(id){
		$.getJSON('${basePath}/web/activityController/top?v=' + new Date().getTime(),{id:id},function(result){
			parent.$.messager.alert('提示', result.message, 'info', function(){dataGrid.datagrid('reload');});
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width: 90%">
					<tr>
						<td>小区</td>
						<td>
						<select name="community" style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
						<option value="-1">--请选择小区--</option>
						<c:forEach items="${communities }" var="list">
							<option value="${list.id }">${list.name }</option>
						</c:forEach>
						</select>
						</td>
						<td>栏目</td>
						<td>
						<select name="type" style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择栏目--</option>
							<option value="1">文娱活动</option>
							<option value="2">义工活动</option>
							<option value="3">关爱老人</option>
						</select>
						</td>
						<td>查询选项</td>
						<td>
						<select name="selectOption" style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<option value="1">标题</option>
							<option value="2">内容</option>
							<option value="3">发布人</option>
							<option value="4">发布时间</option>
						</select>
						</td>
						<td>关键字</td>
						<td><input class="easyui-textbox" style="width: 100px" name="key" class="span2" /></td>
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
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>

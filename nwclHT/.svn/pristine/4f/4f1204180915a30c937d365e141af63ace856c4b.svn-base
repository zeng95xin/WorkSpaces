<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
  <head>
    <title>邻里帮</title>
    <jsp:include page="../inc.jsp"></jsp:include>
   <script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/web/NeighborHelpWebController/dataGrid',
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
				width : 100,
				checkbox : true
			},{
				field : 'unitNo',
				title : '单元编号',
				width : 50,
				sortable : false
			},{
				field : 'userName',
				title : '业主姓名',
				width : 50,
				sortable : false
			}, {
				field : 'nickname',
				title : '昵称',
				width : 50,
				sortable : false
			},{
				field : 'phone',
				title : '业主电话',
				width : 50,
				sortable : false
			},{
				field : 'title',
				title : '标题',
				width : 150,
				sortable : false
			}, {
				field : 'label',
				title : '分类标签',
				width : 100,
				sortable : false
			}, {
				field : 'content',
				title : '内容',
				width : 200,
				sortable : false
			}, {
				field : 'imgPath',
				title : '图片',
				width : 150,
				sortable : false,
				formatter:function(value,row,index){
					if(value != null){
						return "<img src='"+value+"' style='height:50px;width:50px'  />";
					}
					
				}
			}, {
				field : 'status',
				title : '是否解决',
				width : 100,
				sortable : false,
				formatter:function(value){
					if(value==0){
						return "否";
					}
					if(value==1){
						return "是";
					}
				}
			}, {
				field : 'isUserDelete',
				title : '是否已被删除',
				width : 100,
				sortable : false,
				formatter:function(value){
					if(value==0){
						return "否";
					}
					if(value==1){
						return "是";
					}
				}
			}, {
				field : 'reward',
				title : '悬赏',
				width : 150,
				sortable : false
			},{
				field : 'rowAddTime',
				title : '发布时间',
				width : 150,
				/* sortable : true */
			},  {
				field : 'action',
				title : '操作',
				width : 150,
				formatter : function(value, row, index) {
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${basePath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="commentFun(\'{0}\');" src="{1}" title="活动评论"/>', row.id, '${basePath}/style/images/extjs_icons/comments.png');
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
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
					$.post('${basePath}/web/NeighborHelpWebController/delete', {
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
					$.getJSON('${basePath}/web/NeighborHelpWebController/batchDelete', {
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
			width : 800,
			height : 600,
			href : '${basePath}/web/NeighborHelpWebController/editPage?id=' + id,
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
	
	function commentFun(id) {
		window.open(
			'${basePath}/web/NeighborHelpWebController/commentPage?id=' + id, window,
			"dialogHeight:500px;dialogWidth:1000px;status:no;");
	}
	
	function readingFun(id) {
		window.open(
			'${basePath}/web/readingRecord/manager?contentId=' + id+'&type=5', window,
			"dialogHeight:500px;dialogWidth:800px;status:no;");
	}
	
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
					s=$(this).combobox('getValue');
					}
				});
 	
 		});
	function allFun() {
		dataGrid.datagrid('load',{});
		
	}
	
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	function sensitive(){
		dataGrid.datagrid('load',{"isSensitive":"1"});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 70px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width: 100%">
					<tr>
					<c:if test="${loginUser.roleId == 1 }">
						<td>小区</td>
						<td><input name="communityId" id="community"></td>
						<td>楼宇</td>
						<td>
							<select id="building" name="building">
								<option value="-1">--前选择楼宇--</option>
							</select>
						</td>
						</c:if>
						<td>是否解决</td>
						<td>
							<select  style="width: 100px" name="status" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<option value="1">--是--</option>
							<option value="0">--否--</option>
						</select>
						</td>
						
						<td>查询选项</td>
						<td>
						<select name=option style="width: 100px"  class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<option value="1">标题</option>
							<option value="2">发布时间</option>
							<option value="3">内容</option>
							<option value="4">房间号</option>
							<option value="5">发布人</option>
							<option value="6">电话</option>
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
		<!-- <a onclick="allFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'color_wheel'">全部信息</a>
		<a onclick="sensitive();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'emoticon_unhappy'">敏感词</a> -->
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		<div onclick="commentFun();" data-options="iconCls:'comments'">活动评论</div>
	</div>
</body>
</html>

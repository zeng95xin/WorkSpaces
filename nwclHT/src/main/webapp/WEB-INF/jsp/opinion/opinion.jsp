<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
  <head>
    <title>意见反馈</title>
    <jsp:include page="../inc.jsp"></jsp:include>
   <script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/web/OpinionWebController/dataGrid',
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
				field : 'content',
				title : '意见',
				width : 100,
				sortable : false
			},{
				field : 'replay',
				title : '是否回复',
				width : 50,
				sortable : false,
				formatter:function(value,row,index){
					if(value == 1){
						return "<span style='color:green;'>是</span>";
					}
					return "<span style='color:red;'>否</span>";
				}
			}, {
				field : 'replayContent',
				title : '回复内容',
				width : 100,
				sortable : false
			}, {
				field : 'imgPath',
				title : '图片',
				width : 100,
				sortable : false,
				formatter:function(value,row,index){
					if(value!=null){
						var aa='<a href="#" onclick="imgFun(\'' + row.id+ '\')">' + "<img src='"+value+"' style='height:50px;width:50px'  />"+ '</a>';
						return   aa;
					}
				}
			},{
				field : 'rowAddTime',
				title : '反映时间',
				width : 100,
				sortable : true
			},  {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="imgFun(\'{0}\');" src="{1}" title="图片"/>', row.id, '${basePath}/style/images/extjs_icons/eye.png');
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
					str += $.formatString('<img onclick="replayFun(\'{0}\',\'{2}\');" src="{1}" title="回复"/>', row.id, '${basePath}/style/images/extjs_icons/text_align_center.png',row.replay);
					str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				    str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${basePath}/style/images/extjs_icons/cancel.png');
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
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId ) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/OpinionWebController/delete', {
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
						msg : '不可以删除自己！'
					});
				}
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
					var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
					var flag = false;
					for ( var i = 0; i < rows.length; i++) {
						if (currentUserId != rows[i].id) {
							ids.push(rows[i].id);
						} else {
							flag = true;
						}
					}
					$.getJSON('${basePath}/web/OpinionWebController/batchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.code=="SUCCESS") {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						if (flag) {
							parent.$.messager.show({
								title : '提示',
								msg : '不可以删除自己！'
							});
						} else {
							parent.$.messager.alert('提示', result.message, 'info');
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

	
	function replayFun(id,replay) {
		if(1==replay){
			parent.$.messager.alert('提示', '该信息已经被回复过了', 'info');
			return;
		}
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		
		$.messager.prompt('提示', '请输入回复内容,最多900字。', function(r){
			if (r){
				$.post("${basePath}/web/OpinionWebController/replay?id="+id+"&content="+r,function(data){
					if(data.code=="SUCCESS"){
						parent.$.messager.alert('提示', data.message, 'info');
						dataGrid.datagrid('reload');
					}else{
						parent.$.messager.alert('错误', data.message, 'error');
					}
				});
			}
		});
		return;
		
		
	}
	
	function imgFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '图片',
			width : 700,
			height : 500,
			href : '${basePath}/web/OpinionWebController/imgPage?id=' + id,
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
						<td>查询选项</td>
						<td>
						<select name=option style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<option value="1">房间号</option>
							<option value="2">电话</option>
						</select>
						</td>
						<td>关键字</td>
						<td><input class="easyui-textbox" style="width: 100px" name="key" class="span2"/></td>
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
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	</div>
</body>
</html>

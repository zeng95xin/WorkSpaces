<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
  <head>
    <title>爱分享</title>
    <jsp:include page="../inc.jsp"></jsp:include>
   <script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${basePath}/api/visitorPassportRecord/dataGrid',
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
			//d.`name` buserName,e.`name` deviceName,a.pastime, a.visitorName,c.serial
			columns : [ [ {
				field : 'type',
				title : '开门方式',
				width : 50,
				sortable : false,
				formatter : function(value, row, index){
					if(value == '1'){
						return '<span style="color:green;">业主开门</span>';
					}else if(value == '2'){
						return '<span style="color:blue;">访客开门</span>';
					}
				}
			},{
				field : 'userName',
				title : '姓名',
				width : 50,
				sortable : false
			},{
				field : 'userPhone',
				title : '手机',
				width : 50,
				sortable : false
			},{
				field : 'communityName',
				title : '小区',
				width : 50,
				sortable : false
			}, {
				field : 'doorName',
				title : '门禁',
				width : 50,
				sortable : false
			}, {
				field : 'openTime',
				title : '通过时间',
				width : 50,
				sortable : false
			}, {
				field : 'action',
				title : '操作',
				width : 150,
				formatter : function(value, row, index) {
					var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
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
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/api/visitorPassportRecord/delete', {
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
					$.getJSON('${basePath}/api/visitorPassportRecord/batchDelete', {
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
	
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		
	}
	function allFun() {
		dataGrid.datagrid('load',{});
		
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width: 90%">
					<tr>
						<c:if test="${loginUser.roleId == 1 }">
						<td>小区</td>
						<td>
							<select name="communityId" class="easyui-combobox" id="communityId">
								<option value="-1">--所有小区--</option>
								<c:forEach items="${communitys }" var="c">
									<option value="${c.id }">${c.name }</option>
								</c:forEach>
							</select>
						</td>
						</c:if>
						<td>类型</td>
						<td>
						<select name=type style="width: 100px" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--所有类型--</option>
							<option value="1">--业主开门--</option>
							<option value="2">--访客开门--</option>
						</select>
						</td>
						<td>姓名</td>
						<td>
							<input name='name' />
						</td>
						<td>手机号</td>
						<td>
							<input name='phone' />
						</td>
					</tr>
					<tr>
						<!-- <td>房间号</td>
						<td>
							<input name='roomNo' />
						</td> -->
						<td>开始时间</td>
						<td>
<input class="easyui-datetimebox" name="startTime" data-options="showSeconds:true"style="width:200px"/>
						</td>
						<td>结束时间</td>
						<td>
<input class="easyui-datetimebox" name="endTime" data-options="showSeconds:true"style="width:200px"/>
						</td>
						<!-- <td>关键字</td>
						<td><input class="easyui-textbox" style="width: 100px" name="key" class="span2"/></td> -->
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
		<a onclick="sensitiveFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'emoticon_unhappy'">带敏感词信息</a> -->
		<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	</div>
</body>
</html>

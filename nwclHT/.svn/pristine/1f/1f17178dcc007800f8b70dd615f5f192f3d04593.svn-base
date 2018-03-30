<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>物业缴费</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
var dataGrid;
$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '${basePath}/web/PropertyPayWebController/dataGrid',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'orderNo',
		pageSize : 10,
		pageList : [10,30,50,100],
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect:true,
		rownumbers:true,
		nowrap : false,
		columns : [ [ {
			field : 'unitNo',
			title : '单元编号',
			width : 40,
			sortable : false
		},{
			field : 'phone',
			title : '手机号码',
			width : 40,
			sortable : false
		},{
			field : 'payTime',
			title : '缴费时间',
			width : 50,
			sortable : false
		},{
			field : 'payStatus',
			title : '支付状态',
			width : 40,
			sortable : false,
			formatter: function(value){
			    if (value == "ALREADY_PAY") {
			        return '<font style="color: green;">已支付</font>';
			    }
			    if (value == "NOT_PAY") {
			        return '<font style="color: red;">未支付</font>';
			    }
			    if(value == "TIME_EXPIRE"){
			    	return '已过期';
			    }
				if(value == "OTHER_PAY"){
			    	return '<font style="color: blue;">其他用户已帮忙支付</font>';
			    }
			}
		},{
			field : 'payChannel',
			title : '支付渠道',
			width : 40,
			sortable : false,
			formatter: function(value){
			    if (value == "wx") {
			        return '微信';
			    }
			    if (value == "alipay") {
			        return '支付宝';
			    }
			}
		},{
			field : 'payMoney',
			title : '缴费金额',
			width : 50,
			sortable : false,
			formatter : function(value){
				return '￥'+(value/100).toFixed(2);
			}
		},{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;&nbsp;&nbsp;&nbsp;';
				str += $.formatString('<img onclick="seeFun(\'{0}\');" src="{1}" title="查看"/>', row.orderNo, '${basePath}/style/images/extjs_icons/eye.png');
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
		},
		onLoadSuccess : function(data) {
			var option = '￥'+(data.money2/100).toFixed(2);
			$("#money").html(option);
			var option2 = '￥'+(data.money/100).toFixed(2);
			$("#money2").html(option2);
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


function seeFun(id) {
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].orderNo;
	}
	parent.$.modalDialog({
		title : '查看',
		width : 600,
		height : 400,
		href : '${basePath}/web/PropertyPayWebController/seePropertyPay?id=' + id,
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

</script>
</head>

<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 65px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="width:100%;">
					<tr>		
						<tr>
						<c:if test="${loginUser.roleId == 1 }">
						<th>小区</th>
						<td><select name="community" class=" span2" class="easyui-combobox" data-options="editable:false,width:120,panelHeight:'auto'">
							<option value="-1">--请选择--</option>
							<c:forEach items="${community }" var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>							
						</select></td>
						</c:if>
																			
						<th>支付渠道</th>
						<td><select name="pay" class="span2" style="width:150px;" >
					          <option value="">--请选择--</option>
						      <option value="1">微信</option>
						      <option value="2">支付宝</option>
						    </select>
						</td>						
						<th>查询时间</th>
						<td>
							<input name="time" class="easyui-datebox span2" data-options="editable:false" >--
							<input name="time2" class="easyui-datebox span2" data-options="editable:false" >
						</td>
						<th>最近</th>
						<td><select name="time3" class="span2" style="width:150px;" >
					          <option value="">--请选择--</option>
						      <option value="1">一个月</option>
						      <option value="2">两个月</option>
						      <option value="3">三个月</option>
						    </select>
						</td>
						<th>查询选项</th>
						<td><select name="key" class="span2" style="width:150px;" >
					          <option value="">--请选择--</option>
						      <option value="1">手机号</option>
						      <option value="2">单元编号</option>
						    </select></td>	
						<th>关键字</th>
						<td><input name="value" class="span2" style="width:150px;" /></td>
						<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空</a>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div data-options="region:'center'">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<div>
			<div style="float:left;">
				&nbsp;&nbsp;<input type="checkbox" checked="checked">&nbsp;&nbsp;是否自动发送给未缴费的业主
			</div>
			<div style="float:right;">
			<span>今日总收入</span>
				<span><font style="color: blue;" id="money"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span>本月总收入</span>
				<span><font style="color: blue;" id="money2"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</div>
			<div style="clear:both"></div>
			</div>
		</div>

	<!-- <div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="seeFun();" data-options="iconCls:'pencil_see'">查看</div>
	</div> -->

</body>
</html>
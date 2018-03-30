<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		
	});
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" enctype="multipart/form-data">
			<table class="table table-hover table-condensed">
			<tr>
				<td>订单号</td>
				<td>${payOrder.orderNo }</td>
				<td>手机号</td>
				<td>${bizUser.phone }</td>
				<td>单元号</td>
				<td>${room.unitNo }</td>
			</tr>
			<tr>
				<td>缴费时间</td>
				<td><fmt:formatDate value="${payOrder.payTime }" type="both"/></td>
				<td>支付渠道</td>
				<c:if test="${payOrder.payChannel == 'wx' }">
					<td>微信</td>
				</c:if>
				<c:if test="${payOrder.payChannel == 'alipay' }">
					<td>支付宝</td>
				</c:if>
				<td>缴费金额</td>
				<td>￥<fmt:formatNumber type="number" value="${payOrder.payMoney/100 }" minFractionDigits="2"/></td>
			</tr>
			<tr style="background-color: #DCDCDC">
				<th colspan="7">缴费明细</th>
			</tr>
			<c:forEach items="${propertyPayMent }" var="list">
			<tr>
				<td colspan="5">${list.receivableDispname }</td>
				<td>￥<fmt:formatNumber type="number" value="${list.receivableAmount/100 }" minFractionDigits="2"/></td>
			</tr>
			</c:forEach>
			</table>
		</form>
	</div>
</div>



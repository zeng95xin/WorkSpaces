<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="application/xhtml+xml; charset=utf-8" http-equiv="Content-Type">
		<meta name="viewport" id="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
		<title>二维码</title>
		<script type="text/javascript" src="${basePath}js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${basePath}js/jquery.qrcode.min.js"></script>
		<script>
			$(function(){
				$("#code").qrcode({ 
				    render: "canvas", //table方式 
				    width: $('#code').width(), //宽度 
				    height:200, //高度 
				    text: "${serialCode}" //任意内容 
				}); 
			});
		</script>
		<style>
		</style>
	</head>
	<body>
		<div style="width:50%;float: left;">
			<table>
				<tr>
					<td><img style="width:100%;height:70px;" src="${basePath}images/serial_backgroud.png"/>
						<span style="position: absolute;top: 30px;left: 30px;">${serial }</span>
					</td>
				</tr>
				<tr>
					<td>
					<c:if test="${type == 1 }">到访人</c:if>
					<c:if test="${type == 2 }">放形物件</c:if>
					</td>
				</tr>
				<tr>
					<td>${str1 }</td>
				</tr>
				<tr>
					<td>
					<c:if test="${type == 1 }"></c:if>
					<c:if test="${type == 2 }">放形人数</c:if>
					</td>
				</tr>
				<tr>
					<td>${str2 }</td>
				</tr>
				<tr>
					<td>
					<c:if test="${type == 1 }">预计到访时间</c:if>
					<c:if test="${type == 2 }">有效时间</c:if>
					</td>
				</tr>
				<tr>
					<td>${str3 }</td>
				</tr>
			</table>
		</div>
		<div style="width:49%;float: left;" id="code">
		</div>
		<br/>
			<div style="clear: both;height: 40px; line-height: 40px;">到访记录</div>
		<div style="width: 100%;">
			<c:forEach items="${list }" var="temp">
			<div style="float: left;width: 30%;text-align: right;">进入小区</div>
			<div style="float: left;text-align: right;width: 70%;">${temp }</div>
			</c:forEach>
		</div>
	</body>
</html>
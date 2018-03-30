<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript">
parent.$.messager.progress('close');
</script>
<style>
	.receiver,.sender{   
		clear:both;   
		min-height: 50px;
	} 
	.sender div:nth-of-type(1){ 
	   	float: left;
	}        
	.sender div:nth-of-type(2){ 
		background-color: aquamarine; 
		margin: 0 20px 10px 60px;
		padding: 10px 10px 10px 0px;
		border-radius:7px;
		word-wrap: break-word;
	}
	.receiver div:first-child img, 
	.sender div:first-child img{
		width:40px;
		height: 40px; 
		border-radius: 10px;
	} 
	.receiver div:nth-child(1){    
		float: right;    
	}  
	.receiver div:nth-of-type(2){   
		float:right;
		background-color: gold;   
		margin: 0 10px 10px 20px;
		padding: 10px 0px 10px 10px;
		border-radius:7px; 
		max-width: 89%;
		word-wrap: break-word;
	}
	.left_triangle{
		height:0px;width:0px;border-width:8px;
		border-style:solid;
		border-color:transparent aquamarine transparent transparent;
		position: relative;
		left:-16px;
		top:3px;
	}
	.right_triangle{
		height:0px;width:0px;border-width:8px;border-style:solid;
		border-color:transparent transparent transparent gold;
		position: relative;
		right:-16px;top:3px;
	}
	.time{
		margin-bottom: 5px;
		color: #636363;
		font-size: 12px;
	}
	.time-left{
		padding-left: 60px;
	}
	.time-right{
		padding-right: 60px;
		text-align: right;
		clear: both;
	}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
	<c:forEach items="${housekeeperUserMessage }" var="list">
		<c:if test="${list.sendType==1 }">
		<div class="wrapper">
		<div class="time-left">${bizUser.nickname }&nbsp;&nbsp;<fmt:formatDate value="${list.rowAddTime }" type="both"/></div>
		<div class="sender">
			<div>
			<c:if test="${bizUser.headPortrait==null }">
			<img src="${basePath}/images/user1.png">
			</c:if>
			<c:if test="${bizUser.headPortrait!=null }"> 
			<img src="${bizUser.headPortrait }">
			</c:if>
			</div>
			<div style="min-width: 20px;">
				<div class="left_triangle"></div> 
					<span>${list.content }</span> 
			</div>
		</div>
	</div>
	</c:if>
	<!-- 右边的  一般判断 如果是自己的信息 显示右边-->
	<c:if test="${list.sendType==0 }">
    <div class="wrapper">
		<div class="time-right">${housekeeper.name }&nbsp;&nbsp;<fmt:formatDate value="${list.rowAddTime }" type="both"/></div>
		<div class="receiver">
			<div>
			<c:if test="${housekeeper.headPortrait==null }">
				<img src="${basePath}/images/user1.png">
			</c:if>
			<c:if test="${housekeeper.headPortrait!=null }">
				<img src="${housekeeper.headPortrait }"> 
			</c:if>
			</div>
			<div>
				<div class="right_triangle"></div> 
				<span>${list.content }</span> 
			</div>
		</div>
	</div>
	</c:if>
	</c:forEach>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.div_span{width:100%;display: inline-block;}
</style>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<table class="table table-hover table-condensed">
			<tr>
			<tr>
				<th>标题</th>
				<td><input value="${lecture.title }" type="text"
					class="easyui-validatebox span2" /></td>
			</tr>
			<tr>
				<th>内容</th>
				<td colspan="3" style="height: 200px;"><textarea
						style="width:500px;height: 200px;">${lecture.content }</textarea>
			</tr>
			<tr>
				<th>个人介绍</th>
				<td colspan="3" style="height: 200px;"><textarea
						style="width:500px;height: 200px;">${lecture.description }</textarea>
			</tr>
			<tr>
				<th>图片</th>
				<td>
					<c:forEach items="${lectureHallIntroducingImg }" var="list">
				<div class="div_span">
						<img alt="" src="${list.imgPath }" 
						style="max-width:500px;_width:expression(this.width > 500 ? '500px' : this.width),">
						<br/><br/>
				</div>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
</div>
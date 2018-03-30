<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

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

<style>
.span_div{position:relative}
.span_div>img{cursor:default}
.close_span,.close_span1{position:absolute;top:5px;right:5px;color:#fff;font-weight:bold;font-size:20px;cursor: pointer}
#div_span{width:100%;display: inline-block;}
</style>
<script type="text/javascript">

/**
 * 上传头像 选择头像显示预览，以及前端判断
 */
	 function setImagePreview() {
		var docObj=document.getElementById("doc");
		var localImag=document.getElementById("localImag");
		localImag.innerHTML="";
		var fileList=docObj.files;
		for (var i = 0; i < fileList.length; i++) {
		localImag.innerHTML+="<div style='clear:both'> <span style='position:relative;float:left'><img id='preview" + i + "'  /> </span></div>";
		var imgObjPreview=document.getElementById("preview"+i);
		
		//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
		var url = window.URL.createObjectURL(docObj.files[i]);
		var img = new Image();
		img.src = url;
		img.onerror=function(){
			alert("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
			document.getElementById("doc").value="";
			return false;
	};
	//判断是否为正确的图片格式END
	//判断图片的大小是否超过2M
	var imageSize=docObj.files[i].size;
	if(imageSize>20*1024*1024){
		alert("上传图片太大,不得超过20M");
		document.getElementById("doc").value="";
		document.getElementById("preview"+i).src="";
		return false;
	}
    /* var imgObjPreview=document.getElementById("preview"); */
    if(docObj.files && docObj.files[i]){
         //火狐下，直接设img属性
         imgObjPreview.style.display = 'block';
         imgObjPreview.style.width = '150px';
         imgObjPreview.style.height = '150px';
         imgObjPreview.style.margin = '10px';
         //imgObjPreview.src = docObj.files[0].getAsDataURL();
         //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
    	 imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
    }else{
        //IE下，使用滤镜
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag");
        //必须设置初始大小
        localImagId.style.width = "150px";
        localImagId.style.height = "150px";
        localImagId.style.margin = "10px";
        //图片异常的捕捉，防止用户修改后缀来伪造图片
		try{
            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        }catch(e){
        	alert("您上传的图片格式不正确，请重新选择!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
     }
     
     }
     return true;
 } 
 
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/OwnersMarketWebController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				//console.trace(result);
				result = $.parseJSON(result);
				if (result.code == "SUCCESS") {
					parent.$.messager.alert('提示', result.message, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});
	
	
	function deleteImg(id){
		parent.$.messager.confirm('询问', '您是否要删除当前图片？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/OwnersMarketWebController/deleteImg', {
						id : id
					}, function(result) {
						if (result.code=="SUCCESS") {
							$('.item'+id).remove();
							parent.$.messager.alert('提示', result.message, 'info');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
			}
		});
	}

    
    
</script>
	<script type="text/javascript">
	$("#add").click(function(){
		var size = new Date().getTime();
		$(".text-box").append('<div class="item"><div class="localImag" id="localImag'+size+'"><img class="preview" id="preview'+size+'"  src="" style="diplay:none" /></div><input type="file" name="file" id="doc'+size+'" class="input_text"  onchange="javascript:setImagePreview('+size+');"/><span class="box-delete">×</span></div>');
		$(".box-delete").click(function(){
			$(this).parent(".item").remove();
		});
	});
	$(".box-delete").click(function(){
		$(this).parent(".item").remove();
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post"  encType="multipart/form-data" >
				<input type="hidden" value="${ownersMarket.id }" name="id">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 40px;">姓名</th>
					<td>
					<input type="text" name="title" value="${bizUser.name }" class="easyui-validatebox span2" readonly="readonly" >
					</td>
					<th>房间号</th>
					<td>
					<input type="text" name="title" value="${room.roomNo }" class="easyui-validatebox span2" readonly="readonly"
						data-options="required:true,editable:false" >
					</td>
					<th>电话</th>
					<td >
					<input type="text" name="title" value="${bizUser.phone }" class="easyui-validatebox span2" readonly="readonly"
						data-options="editable:false" >
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 40px;">标题</th>
					<td colspan = "5">
					<input type="text" name="title" value="${ownersMarket.title }" class="easyui-validatebox span2" 
						data-options="required:true" >
					</td>
						
				</tr>
				<tr>
					<th style="text-align: right; width: 40px;">内容</th>
					<td colspan="5" style="height: 200px;">
						<textarea name="content" id="activity"  style="width:500px;height: 200px;">${ownersMarket.content }</textarea>
				</tr>
				<tr>
					<th style="text-align: right; width: 40px;">图片</th>
					<td colspan="4" class="text-box">
					<c:forEach items="${ownersMarketImg }" var="list">
					<div id="div_span">
					<div class="item${list.id } span_div">
						<img src="${list.imgPath }"/>
						<span onclick="deleteImg(${list.id })" class="close_span"><img alt="" width="25px" height="25px" src="/images/delete.png"></span>
					</div>
					</div>
					</c:forEach>
					<div >
					 <div id="localImag"></div>
					 <div style="clear:both">
					<input type="file" name="file" id="doc" multiple="multiple"  onchange="javascript:setImagePreview();" accept="image/*"/>
					</div> 
					</div>
					<td>
				</tr>
				
			</table>
		</form>
	</div>
</div>
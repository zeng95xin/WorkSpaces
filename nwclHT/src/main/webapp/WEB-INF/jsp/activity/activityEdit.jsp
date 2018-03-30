<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
/**
 * 上传头像 选择头像显示预览，以及前端判断
 */
function setImagePreview() {
	var docObj=document.getElementById("doc");
	//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
	var url = window.URL.createObjectURL(docObj.files[0]);
	var img = new Image();
	img.src = url;
	img.onerror=function(){
		messagerError("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
		document.getElementById("doc").value="";
		return false;
	}//判断是否为正确的图片格式END
	//判断图片的大小是否超过2M
	var imageSize=docObj.files[0].size;
	if(imageSize>20*1024*1024){
		messagerError("上传图片太大,不得超过20M");
		document.getElementById("doc").value="";
		document.getElementById("preview").src="";
		return false;
	}
    var imgObjPreview=document.getElementById("preview");
    if(docObj.files && docObj.files[0]){
         //火狐下，直接设img属性
         imgObjPreview.style.display = 'block';
         imgObjPreview.style.width = '100px';
         imgObjPreview.style.height = '100px';                    
         //imgObjPreview.src = docObj.files[0].getAsDataURL();
         //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
    	 imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
    }else{
        //IE下，使用滤镜
        docObj.select();
        var imgSrc = document.selection.createRange().text;
        var localImagId = document.getElementById("localImag");
        //必须设置初始大小
        localImagId.style.width = "100px";
        localImagId.style.height = "100px";
        //图片异常的捕捉，防止用户修改后缀来伪造图片
		try{
            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
        }catch(e){
        	messagerError("您上传的图片格式不正确，请重新选择!");
            return false;
        }
        imgObjPreview.style.display = 'none';
        document.selection.empty();
     }
     return true;
 }


	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#activity', {
				width : '100%',
				height : '400px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${basePath}/fileController/upload',
				fileManagerJson : '${basePath}/fileController/fileManage',
				afterChange : function() {
                       //
		 				
                   }  ,
				allowFileManager : true
				
			});									
			parent.$.messager.progress('close');
		}, 1);
		
		
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/activityController/edit',
			onSubmit : function() {
				editor.sync();
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
				console.trace(result);
				result = $.parseJSON(result);
				if (result.code=="SUCCESS") {
					parent.$.messager.alert('提示',result.message,'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});
	
	function pushFun(){
		var push = $("#push").val();
		if(push == 2){
			$("#pushContent").hide();
		}
		if(push == 1){
			$("#pushContent").show();
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${activity.id }" name="id">
		<input type="hidden" value="${activity.imgPath }" name="imgPath">
			<table class="table table-hover table-condensed">
			<tr>
				<th>标题</th>
					<td colspan="3">
					<input name="title" type="text"
						class="easyui-validatebox span2" 
						data-options="required:true" 
						value="${activity.title }">
					</td>
			</tr>
			 <tr>
					<th>小区</th>
					<td><select name="community" style="width: 100px" class="easyui-combobox" >
						<c:forEach items="${communities }" var="list">
							<option value="${list.id }" <c:if test="${list.id==activity.communtiyId }">selected</c:if> >${list.name }</option>
						</c:forEach>
						</select></td>
					<th>栏目</th>
						<td>
						<select name="type" style="width: 100px" class="easyui-combobox" >
							<option value="1" <c:if test="${activity.type==1 }">selected</c:if>>文娱活动</option>
							<option value="2" <c:if test="${activity.type==2 }">selected</c:if>>义工活动</option>
							<option value="3" <c:if test="${activity.type==3 }">selected</c:if>>关爱老人</option>
						</select>
						</td>
				</tr>
				
				<tr>
					<th>报名截止时间</th>
					<td colspan="3"><input name="endTime" type="text"
						class="easyui-datetimebox span2" data-options="required:true,editable:false" 
						value="${endTime }"></td>
						
				</tr>
				<tr>
					<th>活动结束时间</th>
					<td colspan="3"><input name="activityEndTime" type="text"
						class="easyui-datetimebox span2" data-options="required:true,editable:false" 
						value="${activityEndTime }"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">&nbsp;</th>
					<td><span style="color: red;">图片最佳大小为5:1的比例,比如750：150</span></td>
				</tr>
				<tr>
					<th>首页图片</th>
					<td colspan="3">
						<div id="localImag"><img id="preview"  src="${activity.imgPath }" style="diplay:none" /></div>
 						<input type="file" name="file" id="doc" onchange="javascript:setImagePreview();"/>
					</td>
				</tr>
				
				<tr>
					<th>内容</th>
					<td colspan="3" style="height: 400px;">
						<textarea name="content" id="activity"  style="width:500px;height: 200px;">${activity.content }</textarea>
				</tr>
				<tr>
					<th>是否首页推荐</th>
					<td>
						<select name="recommended" style="width: 100px">
							<option value="0" <c:if test="${recommended == 1 }">selected="selected"</c:if>>否</option>
							<option value="1" <c:if test="${recommended == 2 }">selected="selected"</c:if>>是</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>是否推送</th>
					<td>
						<select name="push" onchange="pushFun()" id="push" style="width: 100px">
							<option value="2">--请选择--</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</td>
				</tr>
				
				<tr style="display: none" id="pushContent">
					<th>推送内容</th>
					<td colspan="3" style="height: 200px;">
						<textarea name="pushContent"style="width: 500px;height: 190px"
					    class="easyui-validatebox span2"></textarea>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



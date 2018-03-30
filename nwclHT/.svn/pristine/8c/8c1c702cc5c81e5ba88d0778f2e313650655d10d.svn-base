<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
/**
 * 上传头像 选择头像显示预览，以及前端判断
 */
function setImagePreview() {
	$('#form input[name="oldImgPath"]').val('');
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
		parent.$.messager.progress('close');
		
		$('#form input[name="loginName"]').val(parent.$.modalDialog.rowData.loginName);
		$('#form input[name="nickName"]').val(parent.$.modalDialog.rowData.nickName);
		
		$('#form').form({
			url : '${ctx}/web/bjwdt/member/edit',
			onSubmit : function(param) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				param.code = parent.$.modalDialog.rowData.code;
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" encType="multipart/form-data">
			<table class="table table-hover table-condensed">
<tr><th style="text-align: right;">登录名</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="loginName"></td></tr>
<tr><th style="text-align: right;">昵称</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="nickName"></td></tr>
			</table>
		</form>
	</div>
</div>



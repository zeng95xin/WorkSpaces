<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#Content', {
				width : '95%',
				height : '300px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${basePath}/fileController/upload',
				fileManagerJson : '${basePath}/fileController/fileManage',
				afterChange : function() {
                   }  ,
				allowFileManager : true
				
			});
			editor2 = KindEditor.create('#notice', {
				width : '95%',
				height : '300px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				uploadJson : '${basePath}/fileController/upload',
				fileManagerJson : '${basePath}/fileController/fileManage',
				afterChange : function() {
                   }  ,
				allowFileManager : true
				
			});
			parent.$.messager.progress('close');
		}, 1);
		
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/coupon/edit',
			onSubmit : function(param) {
				editor.sync();
				editor2.sync();
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
	function getHonorFun(){
		var type = $("#ctype").val();
		$.ajax({
			type : 'get',
			url : '${basePath}/web/coupon/getHonor',
			data : {'type' : type},
			dataType : 'json',
			error : function(){
				alert('发生错误');
			},
			success : function(data){
				if(data.code == "SUCCESS"){
					if(data.result != null){
						var sss= "<option> --- 请选择 ---</option>";
						for (var i = 0; i < data.result.length; i++) {
							sss += "<option value="+data.result[i].id+">"+data.result[i].title+"</option>";
						}
						$("#cshop").html(sss);
					}
				}
			}
		});
	}
	
	//上传照片按钮
	$("#doUp").bind("click", function() {
		$("#imgs").trigger("click");
	});

	/**
	 * 上传酒店图片 选择显示预览，以及前端判断
	 */
	function setImagePreview() {
		var docObj = document.getElementById("imgs");
		var localImag = document.getElementById("localImag");
		//localImag.innerHTML = "";
		var fileList = docObj.files;
		/* fileUploadFun(fileList); */
		for (var i = 0; i < fileList.length; i++) {
			//localImag.innerHTML += "<li style='float:left;margin-right:15px' > <img id='preview" + i + "'  /> </li>";
			$("#localImag").prepend("<li style='float:left;margin-right:15px' > <img id='preview" + i + "'  /> </li>");
			var imgObjPreview = document.getElementById("preview" + i);

			//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
			var url = window.URL.createObjectURL(docObj.files[i]);
			var img = new Image();
			img.src = url;
			img.onerror = function() {
				alert("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
				document.getElementById("imgs").value = "";
				return false;
			};
			//判断是否为正确的图片格式END
			//判断图片的大小是否超过2M
			var imageSize = docObj.files[i].size;
			if (imageSize > 20 * 1024 * 1024) {
				alert("上传图片太大,不得超过20M");
				document.getElementById("imgs").value = "";
				document.getElementById("preview" + i).src = "";
				return false;
			}
			/* var imgObjPreview=document.getElementById("preview"); */
			if (docObj.files && docObj.files[i]) {
				//火狐下，直接设img属性
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '97px';
				imgObjPreview.style.height = '116px';
				//imgObjPreview.style.margin = '10px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
			} else {
				//IE下，使用滤镜
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("localImag");
				//必须设置初始大小
				localImagId.style.width = "150px";
				localImagId.style.height = "150px";
				localImagId.style.margin = "10px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';
				document.selection.empty();
			}
		}
		// 图片上传
		fileUploadFun(this);
		return true;
	};
	function fileUploadFun(file) {
		var id = "imgs";
		$.ajaxFileUpload({
            url:'${basePath}/web/coupon/fileUpload',
            secureuri:false,
            fileElementId:id,
            dataType: 'text',
            success: function (data){  
            	//var data1 = JSON.parse(data);
            	//alert(data1);
            	var end = data.length - 6;
				data = data.substring(59, end);
				data = $.parseJSON(data);
            	if(data.code =="SUCCESS"){
                  //alert(data.message);
                 // alert(data.result.length);
					//var images = $("#pictures").val();
					var paths = $("#pictures").val();
                 	for (var i = 0; i < data.result.length; i++) {
                 		if(paths != ""){
                 			paths =paths + "," + data.result[i];
                 		}else{
                 			paths = paths + data.result[i];
                 		}
					}
                 	$("#pictures").val(paths);
             	}else{
             		//alert('error');
             	};
            },
        }) ;
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${model.id }">
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 100px;">关联版块</th>
					<td>
						<select id="ctype"  name="type" onchange="getHonorFun()">
							<option value="3" <c:if test="${3 == model.type }">selected</c:if> >商圈特惠</option>
							<option value="1" <c:if test="${1 == model.type }">selected</c:if> >酒店公寓</option>
							<option value="2" <c:if test="${2 == model.type }">selected</c:if>>温泉养生</option>
						</select>
					</td>
				</tr>			
				<tr>
					<th style="text-align: right; width: 100px;">关联商家</th>
					<td>
						<select id="cshop"  name="honourEnjoyId">
							<c:forEach items="${honors }" var="c">
								<option value="${c.id }" <c:if test="${c.id == model.honourEnjoyId }">selected</c:if> >${c.title }</option>
							</c:forEach>
						</select>
					</td>
				</tr>			
				<tr>
					<th style="text-align: right; width: 100px;">优惠券标题</th>
					<td><input name="title" type="text" style="width: 210px;" class="easyui-validatebox span2"  value="${model.title }"></td>
				</tr>				
				<tr>
					<th style="text-align: right; width: 100px;">英文标题</th>
					<td><input name="enTitle" type="text" style="width: 210px;" class="easyui-validatebox span2"
						 value="${model.enTitle }"></td>
				</tr>				
				<tr>
					<th style="text-align: right; width: 100px;">优惠券价格</th>
					<td><input name="price" type="text" style="width: 210px;" class="easyui-validatebox span2"
						data-options="required:true" value="${model.price }"></td>
				</tr>	
				<tr>
					<th style="text-align: right; width: 100px;">英文价格</th>
					<td><input name="enPrice" type="text" style="width: 210px;" class="easyui-validatebox span2"
						 value="${model.enPrice }"></td>
				</tr>	
				<tr>
					<th style="text-align: right; width: 100px;">发放次数</th>
					<td>(0) 表示不限次数<br><input name="sendCount" type="text" style="width: 210px;" 
						placeholder="0 不限次数"	class="easyui-validatebox span2"  value="${model.sendCount }"></td>
				</tr>	
				<tr>
					<th style="text-align: right; width: 100px;">有效期从</th>
					<td>
						<input class="easyui-datebox" name="startTime"
						data-options="required:true"  style="width:210px" value="${model.startTime }"/>
					</td>	
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">有效期至</th>
					<td>
						<input class="easyui-datebox" name="endTime"
						data-options="required:true"  style="width:210px"  value="${model.endTime }"/>
					</td>	
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">广告图</th>
					<td>
						<div>
							<ul style="list-style-type: none">
								<c:forEach items="${model.images }" var="list">
									<li style='float:left;margin:10px' class="item${list.id } span_div">
										<img class="hp" alt="" src="${list.imgPath }" style="height: 97px";width="116px">
										<span onclick="deleteImg(${list.id })" class="close_span"><img class="dimg" alt=""  src="/images/delete.png"></span>
									</li>
								</c:forEach>
							</ul>
						</div>
					</td>
				</tr> 
				
				<tr>
					<th style="text-align: right; width: 70px;">添加广告图</th>
					<td>
						<input type="hidden" name="pictures" id="pictures">
						<div>
							<input type="file" autocomplete="off" name="imgs" id="imgs"
								style="display:none;" multiple="multiple"
								onchange="javascript:setImagePreview();" accept="image/*" />
							<ul class="page_imgs clearfix" id="localImag"
								style="list-style-type: none">
								<li><img alt="" src="${basePath}/images/up_img.png" id="doUp"></li>
							</ul>
						</div>
					</td>
				</tr> 
				<tr>
					<th style="text-align: right; width: 100px;">优惠券详情</th>
					<td>
						<textarea name="detail"style="width: 210px;" id="Content"
					    class="easyui-validatebox span2">${model.detail }</textarea>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 100px;">体验须知</th>
					<td>
						<textarea id="notice" name="notice" style="width: 210px;"
					    class="easyui-validatebox span2">${model.notice }</textarea>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>



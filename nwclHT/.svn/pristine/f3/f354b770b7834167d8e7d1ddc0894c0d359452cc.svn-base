<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
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
		
		
		
		if(parent.$.modalDialog.rowData.img){
			$('#form #preview').attr('src', parent.$.modalDialog.rowData.img).show();
			$('#form input[name="oldImgPath"]').val(parent.$.modalDialog.rowData.img);
		}
		console.log(parent.$.modalDialog.rowData);
		$('#form input[name="name"]').val(parent.$.modalDialog.rowData.name);
		$('#form input[name="descsale"]').val(parent.$.modalDialog.rowData.descsale);
		$('#form input[name="flagsale"]').val(parent.$.modalDialog.rowData.flagsale);
		$('#form input[name="floorprice"]').val(parent.$.modalDialog.rowData.floorprice);
		$('#form input[name="label"]').val(parent.$.modalDialog.rowData.label);
		$('#form input[name="descopentime"]').val(parent.$.modalDialog.rowData.descopentime);
		$('#form input[name="estatedid"]').val(parent.$.modalDialog.rowData.estatedid);
		$('#form input[name="flagApartment"]').val(parent.$.modalDialog.rowData.flagApartment);
		$('#form input[name="flagSample"]').val(parent.$.modalDialog.rowData.flagSample);
		$('#form input[name="flagReal"]').val(parent.$.modalDialog.rowData.flagReal);
		$('#form input[name="flagSupporting"]').val(parent.$.modalDialog.rowData.flagSupporting);
		$('#form input[name="flagBuy"]').val(parent.$.modalDialog.rowData.flagBuy);
		$('#form input[name="tel"]').val(parent.$.modalDialog.rowData.tel);
		$('#form input[name="descbuilding"]').val(parent.$.modalDialog.rowData.descbuilding);
		$('#form input[name="descpropertyyear"]').val(parent.$.modalDialog.rowData.descpropertyyear);
		$('#form input[name="descvolume"]').val(parent.$.modalDialog.rowData.descvolume);
		$('#form input[name="descgreening"]').val(parent.$.modalDialog.rowData.descgreening);
		$('#form input[name="descdoor"]').val(parent.$.modalDialog.rowData.descdoor);
		$('#form input[name="descfloor"]').val(parent.$.modalDialog.rowData.descfloor);
		$('#form input[name="descpropertymoney"]').val(parent.$.modalDialog.rowData.descpropertymoney);
		$('#form input[name="descpropertycompany"]').val(parent.$.modalDialog.rowData.descpropertycompany);
		$('#form input[name="desccar"]').val(parent.$.modalDialog.rowData.desccar);
		$('#form input[name="descsubmittime"]').val(parent.$.modalDialog.rowData.descsubmittime);
		$('#form input[name="descschool"]').val(parent.$.modalDialog.rowData.descschool);
		$('#form input[name="descbus"]').val(parent.$.modalDialog.rowData.descbus);
		$('#form input[name="descSpeed"]').val(parent.$.modalDialog.rowData.descSpeed);
		$('#form input[name="flooraddress"]').val(parent.$.modalDialog.rowData.flooraddress);
		$('#form input[name="descbuy"]').val(parent.$.modalDialog.rowData.descbuy);
		$('#form input[name="descdesc"]').val(parent.$.modalDialog.rowData.descdesc);
		$('#form input[name="reginCode"]').val(parent.$.modalDialog.rowData.reginCode);
		$('#form input[name="descDecoration"]').val(parent.$.modalDialog.rowData.descDecoration);
		
		$('#form').form({
			url : '${ctx}/web/bjwdt/floor/edit',
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
<tr><th style="text-align: right;">城市</th><td>
<select name="reginCode">
<%
	JSONArray citys = (JSONArray)request.getAttribute("citys");
	for(int i = 0; i<citys.size();i++){
		JSONObject temp = citys.getJSONObject(i);
		out.print("<option value='"+temp.getString("code")+"'>"+temp.getString("name")+"</option>");
	}
%>
</select>
</td></tr>
<tr><th style="text-align: right;">建筑类型</th><td>
<select id="descBuilding">
	<option value="住宅">住宅</option>
	<option value="别墅">别墅</option>
	<option value="商住">商住</option>
</select>
</td></tr>
<tr><th style="text-align: right;">装修类型</th><td>
<select id="descDecoration">
	<option value="毛坯">毛坯</option>
	<option value="简装">简装</option>
	<option value="精装">精装</option>
	<option value="拎包入住">拎包入住</option>
</select>
</td></tr>
<tr><th style="text-align: right;">名称</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="name"></td></tr>
<tr><th style="text-align: right;">电话</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="tel"></td></tr>
<tr><th style="text-align: right;">地址</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="flooraddress"></td></tr>
<tr><th style="text-align: right;">开盘时间</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descopentime"></td></tr>
<tr><th style="text-align: right;">价格</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="floorprice"></td></tr>
<tr><th style="text-align: right;">销售描述</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descsale"></td></tr>
<tr><th style="text-align: right;">标签 </th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="label"></td></tr>
<tr><th style="text-align: right;">产权年限</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descpropertyyear"></td></tr>
<tr><th style="text-align: right;">容积率</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descvolume"></td></tr>
<tr><th style="text-align: right;">绿化率</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descgreening"></td></tr>
<tr><th style="text-align: right;">规划户数</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descdoor"></td></tr>
<tr><th style="text-align: right;">楼层状况</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descfloor"></td></tr>
<tr><th style="text-align: right;">物业管理费</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descpropertymoney"></td></tr>
<tr><th style="text-align: right;">物业公司</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descpropertycompany"></td></tr>
<tr><th style="text-align: right;">车位比</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="desccar"></td></tr>
<tr><th style="text-align: right;">交房时间</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descsubmittime"></td></tr>
<tr><th style="text-align: right;">学校</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descschool"></td></tr>
<tr><th style="text-align: right;">公交</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descbus"></td></tr>
<tr><th style="text-align: right;">高速</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descSpeed"></td></tr>
<tr><th style="text-align: right;">团购描述</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descbuy"></td></tr>
<tr><th style="text-align: right;">项目描述</th><td><input type="text" class="easyui-validatebox" style="width: 300px" name="descdesc"></td></tr>
<tr><th style="text-align: right;">是否在售</th><td>
<select name="flagsale">
<option value="1">是</option>
<option value="0">否</option>
</select>
</td></tr>
				<tr>
					<th>图片</th>
					<td>
						<div id="localImag"><img id="preview"  style="diplay:none" /></div>
 						<input type="file" name="img" id="doc" onchange="javascript:setImagePreview();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>



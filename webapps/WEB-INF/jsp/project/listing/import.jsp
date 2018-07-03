<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="bjui-pageContent">
	<form id="file_form" data-loadingmask="true" method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath()%>/project/listinginfo/listInfoImport.do"
		data-toggle="validate" data-reload="true">
	<input type="hidden" value="20120022" name="listingGuid" id="listingGuid"/>
	<input type="hidden" id="excelFileName" name="excelFileName" value=""/>
	<input type="hidden" id="memberId" name="memberId" value="23"/>
	<input type="hidden" id="operatorId" name="operatorId" value="112"/>
	<input type="hidden" id="excelFilePath" name="excelFilePath" value=""/>
		<table id="userfiles" class="table other-table table-bordered">
		 	<thead>
		 	</thead>
		 	<thead>
		 		<tr><th >导入系统的挂牌产品文件信息</th><td colspan="2"></td></tr>
		 	</thead>	
		 	<tbody>		
			 	<tr>
				 	<td>
						<label class="control-label x160 pull-right" >请选择导入文件：</label> 
					</td>
					<td>
		            	<input data-rule="required;" type="file" name="file" id="file_input">
		            </td>
		            <td>
		            	<input type="submit" class="btn-blue pull-left submitButton" value="导入数据" class="file">
		            </td>
	            </tr>
			</tbody>
		 </table>
	</form>
</div>
<script type="text/javascript">
function applySaveCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("ok", json.message, {autoClose:false,okCall:function(){$(this).dialog("closeCurrent").navtab("refresh");}});
	}else {
		$(this).alertmsg("error", json.message);
	}
}
function uploadSuccess(file, data, $upload) {
	var json = $.parseJSON(data);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
    	var showFileName = json.originalFilename;
    	var linkFileName = json.linkFileName;
    	$("#excelFileName").val(showFileName+":"+linkFileName);
    	$("#excelFilePath").val(json.excelFilePath);
    	$("#div_1").html('<a target="_blank" class="label-tag" style="position:relative;display:block;" href="'+json.accessPath+linkFileName+'">'+showFileName+'<button data-url="/offsite/invest/delfile.do" data-data="excelFilePath='+json.excelFilePath+'&fileName='+linkFileName+'" data-callback="afterDelFile" data-toggle="doajax" class="close" data-confirm-msg="确定删除该文件吗？" data-callback="afterDelFile">×</button></a>');
    	showStaticValue(json);
    }else {
    	$(this).alertmsg("error", json.message);
    }
}
function showStaticValue(json) {
	$("#totalMoneyShow").text($.formatMoney(json.recordsResult.totalMoney)+" 元");
	$("#totalNumShow").text(json.recordsResult.totalNum+" 条");
	$("#buyNumShow").text(json.recordsResult.totalInvesters+" 人");
	if(json.duplicate.length>0) {
		$("#duplicateTr").show();
		var html = '<td colspan="3"><label class="control-label x140">重复数据：</label>';
		for(var i=0; i<json.duplicate.length; i++) {
			html += '<span id="overBuyTime">第'+json.duplicate[i]+'条数据重复</span>';	
		}
		html += '</td>';
		$("#duplicateTr").html(html);
	}else {
		$("#duplicateTr").hide();
	}
}
function hideStaticValue(json) {
	$("#totalMoneyShow").text("--");
	$("#totalNumShow").text("--");
	$("#buyNumShow").text("--");
	$("#duplicateTr").hide();
}
function afterDelFile(json){
	if(json.statusCode == 200) {
		$("#div_1").html('');
		hideStaticValue();
	}
}
</script>

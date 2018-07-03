<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 新增转让登记申请 -->
<div class="bjui-pageContent">
<form id="projectupate" data-loadingmask="true" action="<%=request.getContextPath()%>/offsite/invest/apply_register.do" data-toggle="validate" data-reload="true" data-callback="applySaveCallback">
	<input type="hidden" value="${listingInfo.listingGuid }" name="listingGuid" id="listingGuid"/>
	<input type="hidden" id="excelFileName" name="excelFileName" value=""/>
	<input type="hidden" id="excelFilePath" name="excelFilePath" value=""/>
	 <table class="table table-condensed table-noborder table-hover">
	 	<thead></thead>
	 	<tbody>
	 		<tr>
				<td>
					<label class="control-label x140">挂牌代码：</label> 
					<span>${listingInfo.listingCode }</span>
				</td>
				<td colspan="2">
					<label class="control-label x140">挂牌名称：</label> 
					<span>${listingInfo.listingFullName }</span>
				</td>
			</tr>
	 		<tr>
				<td>
					<label class="control-label x140">发行规模：</label> 
					<span><fmt:formatNumber value="${listingInfo.listingMoney }" pattern="#,###.00"/> 元</span>
				</td>
				<td colspan="2">
					<label class="control-label x140"><span class="red">*</span>起息日：</label> 
					<span><input data-rule="预计起息日:required;date;match[gte, todayForJudge, date];" type="text" value="<fmt:formatDate value="${listingInfo.valueDate}" pattern="yyyy-MM-dd"/>" name="valueDate" id="valueDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true"></span>
				</td>
			</tr>
	 	</tbody>
	 	<thead>
	 		<tr><th colspan="3">相关附件</th></tr>
	 	</thead>
	 	<tbody>
	 		<tr>
	 			<td colspan="3">
	 				<table id="userfiles" class="table other-table table-bordered">
			        	<thead>
			                <tr>
			                    <th width="16%" align="center">文件类型</th>
			                    <th width="50%" align="center">说明</th>
			                    <th width="24%">文件名称</th>
			                    <th width="10%" align="center">操作</th>
			                </tr>
			            </thead>
			            <tbody>
			                <tr>
			                    <td><span class="red">*</span>投资明细文件</td>
			                    <td style="text-align:left;">同一产品的投资明细数据合并在一个文件中上传，支持excel文件格式(xlsx/xls)，大小控制在10MB以内。<a href="<%=request.getContextPath()%>/offsite/invest/downinvesttemplate.do">下载：投资明细模板</a></td>
			                    <td style="text-align:left;" id="div_1">
			                    </td>
			                    <td>
			                    	<div class="inline-upload upload"
				                           data-uploader="//<%=request.getServerName() %>:8080/offsite/invest/uploadInvestRecords.do"  
				                           data-toggle="upload"
				                           data-file-type-exts="*.xlsx;*.xls" 
				                           data-auto="true" 
				                           data-on-upload-success="uploadSuccess"
				                           data-form-data='{"j":"${jsessionid}","listingGuid":"${listingInfo.listingGuid}"}'
				                           data-button-text="上传"
				                           data-width="50">
				                           </div>
			                    </td>
			                </tr>
			            </tbody>
			        </table>
	 			</td>
	 		</tr>
		</tbody>
		<thead>
	 		<tr><th colspan="3">投资登记信息</th></tr>
	 	</thead>
	 	<tbody>
	 		<tr>
				<td>
					<label class="control-label x140">募集总金额：</label> 
					<span id="totalMoneyShow">--</span>
				</td>
				<td>
					<label class="control-label x140">登记数据条数：</label> 
					<span id="totalNumShow">--</span>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label class="control-label x140">登记投资总人数：</label> 
					<span id="buyNumShow">--</span>
				</td>
			</tr>
			<tr>
				<td id="duplicateTr" colspan="3" style="display:none">
					<label class="control-label x140">重复数据：</label>
				</td>
			</tr>
	 		<tr>
				<td colspan="3" style="text-align:center;">
					<button type="submit" class="btn-green" id="save">保存</button>
					<button type="button" class="btn-close" data-icon="close">关闭</button>	
				</td>
			</tr>
	 	</tbody>
	</table>
</form>
</div>
<script>
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
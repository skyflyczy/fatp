<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 新增转让登记申请 -->
<div class="bjui-pageContent">
<form id="projectupate" data-loadingmask="true" action="<%=request.getContextPath()%>/offsite/invest/saveapply.do" data-toggle="validate" data-reload="true" data-callback="applyCallback">
	<input type="hidden" value="${project.projectId }" name="projectId" id="projectId"/>
	<input type="hidden" value="${apply.applyGuid }" name="applyGuid"/>
	<input type="hidden" id="submit" name="submit" value="0">
	 <table class="table table-condensed table-noborder table-hover">
	 	<thead></thead>
	 	<tbody>
	 		<tr>
				<td>
					<label class="control-label x140">挂牌代码：</label> 
					<span>${project.projectCode }</span>
				</td>
				<td colspan="2">
					<label class="control-label x140">挂牌名称：</label> 
					<span>${project.projectFullName }</span>
				</td>
			</tr>
	 		<tr>
				<td>
					<label class="control-label x140">发行规模：</label> 
					<span><fmt:formatNumber value="${project.projectMoney }" pattern="#,###.00"/> 元</span>
				</td>
				<td colspan="2">
					<label class="control-label x140">认购期限：</label> 
					<span><fmt:formatDate value="${project.buyTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>——<fmt:formatDate value="${project.buyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x140">起投金额：</label> 
					<span><fmt:formatNumber value="${project.investAmountMin }" pattern="#,###.00"/> 元</span>
				</td>
				<td>
					<label class="control-label x140">投资上限：</label> 
					<span><fmt:formatNumber value="${project.investAmountMax }" pattern="#,###.00"/> 元</span>
				</td>
				<td>
					<label class="control-label x140">追加金额：</label> 
					<span><fmt:formatNumber value="${project.investAmountAppend }" pattern="#,###.00"/> 元</span>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label class="control-label x140"><span class="red">*</span>起息日：</label> 
					<span><input data-rule="预计起息日:required;date;match[gte, todayForJudge, date];" type="text" value="<fmt:formatDate value="${valueDate}" pattern="yyyy-MM-dd"/>" name="valueDate" id="valueDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true"></span>
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
			                    <td style="text-align:left;">同一产品的投资明细数据合并在一个文件中上传，支持excel文件格式(xlsx/xls)，大小控制在10MB以内。<a href="<%=request.getContextPath()%>/offsite/invest/downinvesttemplate.do?fileType=9">下载：投资明细模板</a></td>
			                    <td style="text-align:left;" id="div_1">
				                 	<c:if test="${not empty excelDataFile }">
				                 		<a target="_blank" class="label-tag" style="position:relative;display:block;" href="${accessPath}f${excelDataFile.fileGuid},${excelDataFile.linkFileName}">${excelDataFile.originalFileName}<button data-url="/offsite/invest/delfile.do" data-data="did=div_1&fileName=${excelDataFile.linkFileName}&id=${excelDataFile.fileGuid}&projectId=${project.projectId}" data-toggle="doajax" class="close" data-confirm-msg="确定删除该文件吗？" data-callback="afterDelFile">×</button></a>
				                 	</c:if>
			                     </td>
			                    <td>
			                    	<div class="inline-upload upload"
				                           data-uploader="//<%=request.getServerName() %>:8080/offsite/invest/uploadInvestRecords.do"  
				                           data-toggle="upload"
				                           data-file-type-exts="*.xlsx;*.xls" 
				                           data-auto="true" 
				                           data-on-upload-success="uploadSuccess"
				                           data-form-data='{"j":"${jsessionid}","projectId":"${project.projectId}"}'
				                           data-button-text="上传"
				                           data-width="50">
				                           </div>
				                    <input type="hidden" name="excelDataFile" id="excelDataFile" class="pic-name" <c:if test="${not empty excelDataFile }">value="${excelDataFile.originalFileName }:${excelDataFile.linkFileName }"</c:if>>
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
					<span id="totalMoneyShow"><c:choose><c:when test="${empty apply.bizimportFileSummaryVo }">--</c:when><c:otherwise><fmt:formatNumber value="${apply.bizimportFileSummaryVo.totalMoney }" pattern="#,#00.00"/> 元</c:otherwise></c:choose></span>
				</td>
				<td>
					<label class="control-label x140">登记数据条数：</label> 
					<span id="totalNumShow"><c:choose><c:when test="${empty apply.bizimportFileSummaryVo }">--</c:when><c:otherwise>${apply.bizimportFileSummaryVo.totalNum } 条</c:otherwise></c:choose></span>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label class="control-label x140">登记投资总人数：</label> 
					<span id="buyNumShow"><c:choose><c:when test="${empty apply.bizimportFileSummaryVo }">--</c:when><c:otherwise>${apply.bizimportFileSummaryVo.buyerNum } 人</c:otherwise></c:choose></span>
					<c:if test="${apply.bizimportFileSummaryVo.totalMoney>project.projectMoney }">
					&nbsp;&nbsp;&nbsp;&nbsp;<span id='warntip' class='red'>预计将出现<fmt:formatNumber value="${apply.bizimportFileSummaryVo.totalMoney-project.projectMoney }" pattern="#,#00.00"/> 元的超募，系统按交易时间先后顺序进行确权，超募部分不会确权。</span>
					</c:if>
				</td>
			</tr>
			<tr id="errortip" <c:if test="${empty apply.bizimportFileSummaryVo||tradeImportResult.overBuyTime==0&&tradeImportResult.overInvestLimit==0&&tradeImportResult.lessBuy==0 && tradeImportResult.notEqAppend==0}">style="display:none"</c:if>>
				<td colspan="3">
				<i class="fa fa-exclamation-triangle text-danger"></i>以下信息与挂牌信息不符，可能会导致登记失败，请再次确认
				</td>
			</tr>
			<tr id="duplicateTr" <c:if test="${tradeImportResult.getDuplicateMapSize()==0}">style="display:none"</c:if>>
				<td colspan="3">
				<label class="control-label x140">重复数据：</label>
				<c:forEach items="${tradeImportResult.duplicateMap}" var="m">
				<span id="overBuyTime">第${m.value}条数据重复</span>
				</c:forEach>
				</td>
			</tr>
	 		<tr>
				<td colspan="3" style="text-align:center;">
					<button type="submit" class="btn-green" id="save">保存</button>
					<button type="submit" class="btn-blue" data-icon="submitBtn" id="submitBtn">提交申请</button>
					<button type="button" class="btn-close" data-icon="close">取消</button>	
				</td>
			</tr>
	 	</tbody>
	</table>
</form>
</div>
<script>
$(':submit').click(function(){
	if($(this).attr("id")=="submitBtn") {
		$("#submit").val(1); 
	}else {
		$("#submit").val(0); 
	}
});
function applyCallback(json) {
	if(json.statusCode == 200) {
		var msg = json.message;
		if(json.submit) {
			msg += "，请等待交易中心审核";
		}
		if(json.submit) {
			$(this).alertmsg("ok", msg, {autoClose:false,okCall:function(){$(this).dialog("closeCurrent").navtab("refresh");;}});
		}else {
			$(this).alertmsg("ok", msg, {autoClose:false,okCall:function(){$(this).dialog("reload", {"url":"/offsite/invest/apply_register_edit.do?applyGuid="+json.id}).navtab("refresh");}});
		}
	}else {
		if(json.retcode && json.retcode == 40022) {
			$(this).alertmsg("error", json.message +"，请<a href='<%=request.getContextPath()%>/offsite/invest/downinvesttemplate.do?fileType=14'>下载最新模板</a>并在模板中添加客户简称信息。");
		}else {
			$(this).alertmsg("error", json.message);
		}
	}
}
function uploadSuccess(file, data, $upload) {
	var json = $.parseJSON(data);
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
    	var showFileName = json.originalFilename;
    	var linkFileName = json.fileName;
    	var obj = $upload.next();
    	if (!window.FileReader && $.hasFlash.v >= 9){
    		obj =  $('#'+$upload.attr('id')).parent().next();	
		}
    	obj.val(json.fileName);
    	$("#div_1").html('<a target="_blank" class="label-tag" style="position:relative;display:block;" href="'+json.accessPath+linkFileName+'">'+showFileName+'<button data-url="/offsite/invest/delfile.do" data-data="projectId='+${project.projectId}+'&did=div_1&fileName='+linkFileName+'" data-callback="afterDelFile" data-toggle="doajax" class="close" data-confirm-msg="确定删除该文件吗？" data-callback="afterDelFile">×</button></a>');
    	showStaticValue(json);
    }else {
    	$(this).alertmsg("error", json.message);
    }
}
function showStaticValue(json) {
	$("#totalMoneyShow").text($.formatMoney(json.totalMoney)+" 元");
	$("#totalNumShow").text(json.totalNum+" 条");
	$("#buyNumShow").text(json.totalInvesters+" 人");
	if(json.overBuyTime>0) {
		$("#overBuyTime").text(json.overBuyTime+" 条").parent().show();
	}else {
		$("#overBuyTime").parent().hide();
	}
	if(json.overInvestLimit>0) {
		$("#overInvestLimit").text(json.overInvestLimit+" 条").parent().show();
	}else {
		$("#overInvestLimit").parent().hide();
	}
	if(json.lessBuy>0) {
		$("#lessBuy").text(json.lessBuy+" 条").parent().show();
	}else {
		$("#lessBuy").parent().hide();
	}
	if(json.notEqAppend>0) {
		$("#notEqAppend").text(json.notEqAppend+" 条").parent().show();
	}else {
		$("#notEqAppend").parent().hide();
	}
	var saleMoney = ${project.projectMoney};
	if(json.totalMoney>saleMoney) {
		$("#warntip").remove();
		$("#buyNumShow").after("&nbsp;&nbsp;&nbsp;&nbsp;<span id='warntip' class='red'>预计将出现" + $.formatMoney(json.totalMoney-saleMoney) + " 元的超募，系统按交易时间先后顺序进行确权，超募部分不会确权。</span>");
	}
	if(json.overBuyTime>0 || json.overInvestLimit>0 || json.lessBuy>0 || json.notEqAppend > 0) {
		$("#errortip").show();
		$("#errormsg").show();
	}else {
		$("#errortip").hide();
		$("#errormsg").hide();
	}
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
	$("#overBuyTime").text("--");
	$("#overInvestLimit").text("--");
	$("#lessBuy").text("--");
	$("#notEqAppend").text("--")
	$("#warntip").remove();
	$("#errortip").hide();
	$("#errormsg").hide();
	$("#duplicateTr").hide();
}
function afterDelFile(json){
	if(json.statusCode == 200) {
		$("#"+json.did).next().children('input.pic-name').val('');
		$("#"+json.did).html('');
		if(json.did == 'div_1') {
			hideStaticValue();
		}
	}
}
</script>

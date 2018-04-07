<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
	<form id="projectupate" data-loadingmask="true" action="<%=request.getContextPath()%>/project/record/syq/update.do" data-toggle="validate" data-reload="true">
		 <input type="hidden" name="id" value="${obj.id}">
		 <input type="hidden" name="versionNo" value="${obj.versionNo}">
		 <input type="hidden" id="recordStatus" name="recordStatus" value="${obj.recordStatus}">
		 <input type="hidden" name="todayForJudge" data-rule="当天:" value="${todayForJudge}">
		 <input type="hidden" name="toAudit" id="toAudit" value="false">
		 <table class="table table-condensed table-noborder table-hover">
		 	<thead>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x140">业务类型：</label> 
						<span>金融产品发行</span>
					</td>
					<td>
						<label class="control-label x140">产品类型：</label> 
						<span>	
							<c:forEach var="item" items="${systypeProjectList}" varStatus="status">
								<c:if test="${item.id  eq  obj.projectTypeId}">
									${item.typeName}
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x140">备案代码：</label> 
						<span>${obj.recordCode}</span>
					</td>
					<td>
						<label class="control-label x140"><span class="red">*</span>发行人：</label> 
						<input type="hidden" name="loanUserId" id="loanUserId" value='${loanUserId}'>
						<input readonly type="text" name="loader" id="loader" size="15" value="${loanUserName}" id="loader"
							data-toggle="lookup" data-title="发行人选择" data-url="<%=request.getContextPath()%>/user/lookupload.do" data-width="800" data-height="400">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x140"><span class="red">*</span>备案名称：</label>
						<input size="25" type="text" name="recordFullName" id="recordFullName" data-rule="required;" value="<c:out value='${obj.recordFullName}'/>" maxlength="100">
					</td>
					<td>
						<label class="control-label x140">备案简称：</label>
						<input type="text" name="recordName" id="recordName" value="<c:out value='${obj.recordName}'/>" maxlength="100">
					</td>
				</tr>
		 		<tr>
		 			<td>
						<label class="control-label x140"><span class="red">*</span>拟融资规模：</label> 
						<input data-rule="拟融资规模:required;integer[+];range[~10000000000];" data-msg-range="请输入正确的数值" data-rule="required;projectMoney;" maxlength="12" class="digitUppercase" type="text" name="projectMoney" id="projectMoney" <c:if test="${obj.projectMoney>0}">value="<fmt:formatNumber value="${obj.projectMoney}" pattern="#"/></c:if>"> 元
					</td>
					<td>
						<label class="control-label x140"><span class="red">*</span>产品拟定期限：</label> 
						<input data-rule="产品拟定期限:required;;projectLimit;" data-rule-projectLimit="validFunction.projectLimit" data-autoClose="true" type="text" name="projectLimit" id="projectLimit" value="${obj.projectLimit}" id="projectLimit">
						<select data-rule="validate(projectLimit)" name="projectLimitTypeId" id="projectLimitTypeId" data-toggle="selectpicker" class="show-tick">
							<c:forEach var="item" items="${projectLimitTypeList}">
							<option value="${item.type}" <c:if test="${item.type==obj.projectLimitTypeId}">selected="selected"</c:if>>${item}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x140"><span class="red">*</span>拟定年化收益率：</label> 
						<input data-rule="拟定年化收益率:required;validtwodecimal" data-rule-validtwodecimal="validFunction.validtwodecimal" type="text" name="investProfit" id="investProfit" <c:if test="${obj.projectMoney>0}">value="<fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/>"</c:if> size="10"> %
					</td>
					<td>
						<label class="control-label x140"><span class="red">*</span>拟定还款方式：</label> 
						<select data-rule="required" name="repayTypeId" id="repayTypeId" data-toggle="selectpicker" class="show-tick" style="display: none;">
							<option value="">-选择还款方式-</option>
							<c:forEach var="item" items="${systypeRepayList}" varStatus="status">
								<option value="${item.id}"  <c:if test="${item.id  eq  obj.repayTypeId}">selected="selected"</c:if>>${item.repayTypeName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x140"><span class="red">*</span>增信说明：</label>
						<textarea data-rule="required" size="65" data-toggle="autoheight" name="creditNote" id="creditNote" maxlength="100" placeholder="如： xxxx担保并出具担保函 xxxxx承诺回购并出具回购承诺函"><c:out value="${obj.creditNote}"/></textarea> 
						<!-- </div> -->
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x140">基础资产说明（如有）：</label> 
						<textarea size="65" data-toggle="autoheight" name="basicAssetNote" id="basicAssetNote"  maxlength="65535"><c:out value="${content.basicAssetNote}"/></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn-blue submitButton" data-icon="save" id="saveBtn">保存</button>
						<button type="button" class="btn-close" data-icon="close">关闭</button>	
					</td>
				</tr>
		 	</tbody>
		 </table>
	</form>
</div>
<script>
var validFunction = {
	settleInvestHasVal : function(element) {
		if($("#repayTypeId").val() == 6) {
			if($("#settleInvestMonth").val() != '' && $("#settleInvestDay").val() == '')
				return "日期不能为空";
			else if($("#settleInvestMonth").val() == '' && $("#settleInvestDay").val() != '')
				return "月份不能为空";
		} 
		return true;
	},
	validtwodecimal:function(element){
    	var reg = /^\d{1,2}(\.\d{1,3})?$/;
    	if(reg.test(element.value)){
    		return true;
    	}else{
    		return "小于100且最多只支持3位小数";
    	}
    },
    projectLimit:function(element) {
    	var reg = /^[1-9]\d*$/;
    	if(!reg.test(element.value)){
    		return "请填写正整数";
    	}
    	var selectVal = $("#projectLimitTypeId").val();
    	if(selectVal == 1) {
    		if(element.value > 7300) {
    			return "请填写小于7300的正整数";
    		}
    	}else if(selectVal == 2) {
    		if(element.value > 240) {
    			return "请填写小于240的正整数";
    		}
    	}else if(selectVal == 3) {
    		if(element.value > 20) {
    			return "请填写小于20的正整数";
    		}
    	}
    	return true;
    }
}

$('.submitButton').click(function(){
	var _this=$(this);
	$("#projectupate").attr("data-callback", "projectupateCallback");
	$("#projectupate").data("confirmMsg",function(){
		return '确定要'+_this.text().trim()+'吗？';
	});
	if(_this.attr("id")=="submitBtn") {
		$("#toAudit").val(true);
		$("#projectupate").submit();
	}else{
		$("#toAudit").val(false);
	}
});
$("#projectupate").on('invalid.form', function(e, form, errors) {
	var errortab=$('.msg-wrap.n-error:first').closest('.tab-pane')
	if(errortab.length>0){
		errortab.parent('.tab-content').find('.tab-pane').each(function(){
			errortab.parent('.tab-content').siblings('.nav-tabs').find('li').eq(errortab.index()).find('a').tab('show')
		})
	}
	
});
//保存
function projectupateCallback(json) {
	if(json.statusCode == 200) {
		if(json.forCheck) {
			$(this).alertmsg("ok", "提交成功，请等待审核。", {autoClose:false});
			$(this).dialog("closeCurrent").navtab("refresh");
		}else {
			$(this).alertmsg("correct", json.message);
			$(this).dialog("refresh");
		}
	}else {
		$(this).alertmsg("error", json.message);
	}
}
</script>

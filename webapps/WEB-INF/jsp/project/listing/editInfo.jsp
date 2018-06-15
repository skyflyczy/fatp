<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
	<form id="projectupate" data-loadingmask="true" action="<%=request.getContextPath()%>/project/listinginfo/update.do" data-toggle="validate" data-reload="true">
		 <input type="hidden" name="id" value="${obj.id}">
		 <input type="hidden" name="versionNo" value="${obj.versionNo}">
		 <input type="hidden" name="todayForJudge" data-rule="当天:" value="${todayForJudge}">
		 <table class="table table-condensed table-noborder table-hover">
		 	<thead>
		 	</thead>
		 	<thead>
		 		<tr><th colspan="2">基本信息</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x130">挂牌代码：</label> 
						<span>${obj.listingCode }</span>
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>挂牌名称：</label>
						<input  data-rule="required;" type="text" name="listingFullName"  size="25" value="<c:out value='${obj.listingFullName}'/>" maxlength="120">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>挂牌金额：</label> 
						<input  data-rule="挂牌金额:required;integer[+]" data-msg-range="请输入正确的数值" maxlength="12" class="digitUppercase" type="text" name="listingMoney" value="<fmt:formatNumber value="${obj.listingMoney}" pattern="#"/>"> 元
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>发行人：</label> 
						<input data-rule="required;" maxlength="120" type="text" name="issuer" value="${obj.issuer}">
					</td>
				</tr>
                <tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>管理人：</label> 
						<input data-rule="required;" maxlength="120" type="text" name="listingManager" value="${obj.listingManager}">
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>投资顾问：</label> 
						<input data-rule="required;" maxlength="120" type="text" name="investManager" value="${obj.investManager}">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>发行渠道：</label> 
						<input data-rule="required;" maxlength="120" type="text" name="partnerBiz" value="${obj.partnerBiz}">
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>发行渠道产品代码：</label> 
						<input data-rule="required;" maxlength="120" type="text" name="partnerBizCode" value="${obj.partnerBizCode}">
					</td>
				</tr>
		 	</tbody>
			<thead>
		 		<tr><th colspan="2">结算信息</th></tr>
		 	</thead>
		 	<tbody>	
		 			<tr>
    					<td colspan="2">
        					<label class="control-label x130"><span class="red">*</span>收益率类型：</label>
	        				<select  data-rule="required" name="investProfitType" id="investProfitType" data-toggle="selectpicker" class="show-tick" style="display: none;">
	                            <option value="">-选择收益率类型-</option>
	                            <c:forEach var="item" items="${investProfitTypeList}" varStatus="status">
	                                <option value="${item.type}"  <c:if test="${item.type  eq  obj.investProfitType}">selected="selected"</c:if>>${item}</option>
	                            </c:forEach>
	                        </select>
    					</td>
					</tr>
					<tr>
						<td colspan="2">
							<p><span class="red">*</span><b>预期收益率：</b></p>
							<table id="investProfitTE" data-noread="true" data-reload-navtab="true" class="table other-table table-bordered"  data-toggle="tabledit" data-initnum="0" data-action="" data-single-noindex="true">
			            		<thead>
				                	<tr>
					                    <th title="最低投资金额(元)" width="30%">
					                    	<input placeholder="请输入最低投资金额" class="digitUppercase" data-rule="required;" type="text" name="listingTradeList[#index#].minInvestMoney"/>
					                    </th>
					                    <th title="最高投资金额(元)" width="30%">
					                    	<input placeholder="请输入最高投资金额" class="digitUppercase" data-rule="required;" type="text" name="listingTradeList[#index#].maxInvestMoney"/>
					                    </th>
					                    <th title="指定预期收益率(%)" width="20%"><input data-rule="required;validthreedecimal" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="listingTradeList[#index#].investProfit" style="width:90%;"/>%</th>
					                    <th title="新增" data-addbtn="true" width="10%">
					                        <a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" id="_#index#">删除</a>
					                    </th>
				                	</tr>
			            		</thead>
						        <tbody>
						        	<c:forEach var="listingTrade" items="${listingTradeList}" varStatus="status"> 
						        	<tr data-id="${ status.index + 1}">
							        	<td>${listingTrade.minInvestMoney }</td>
							        	<td>${listingTrade.maxInvestMoney }</td>
							        	<td><fmt:formatNumber value="${listingTrade.investProfit*100 }" pattern="0.000"/></td>
							        	<td data-noedit="true">
								        	<input type="hidden" value="${listingTrade.id }" name="listingTradeList[#index#].id"/>
								        	<a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" id="_#index#">删除</a>
							        	</td>
						        	</tr>
						        	</c:forEach>
						        </tbody>
		        			</table>
						</td>
					</tr>
					<tr>
    					<td>
        					<label class="control-label x130"><span class="red">*</span>起息日：</label>
       						 <input  data-rule="预计起息日:required;date;match[gte, todayForJudge, date]" type="text" value="<fmt:formatDate value="${obj.valueDate}" pattern="yyyy-MM-dd"/>" name="valueDate" id="valueDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true">
    					</td>
    					<td>
        					<label class="control-label x130"><span class="red">*</span>预计到期日：</label>
        					<input  data-rule="预计到期日:required;date;match[gte, valueDate, date];" type="text" value="<fmt:formatDate value="${obj.expireDate}" pattern="yyyy-MM-dd"/>" name="expireDate" id="expireDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true">
    					</td>
					</tr>
					<tr>
    					<td colspan="2">
        					<label class="control-label x130"><span class="red">*</span>产品期限：</label>
        					<input  data-rule="required;listingLimit;" data-rule-listingLimit="validFunction.listingLimit" type="text" name="listingLimit" value="${(obj.expireDate==null || obj.valueDate==null)?'':obj.listingLimit}" id="listingLimit" data-autoClose="true">
	        				<select data-rule="validate(listingLimit)" name="listingLimitType" id="listingLimitType" data-toggle="selectpicker" class="show-tick">
		            			<c:forEach var="item" items="${listingLimitTypeList}">
		               		 		<option value="${item.type}" <c:if test="${item.type==obj.listingLimitType}">selected="selected"</c:if>>${item}</option>
		            			</c:forEach>
	        				</select>
    					</td>
					</tr>
					<tr>
    					<td>
        					<label class="control-label x130"><span class="red">*</span>到期日规则：</label>
        					<select  name="expireDateStyle" id="expireDateStyle" data-toggle="selectpicker" class="show-tick">
		            			<c:forEach var="item" items="${expireDateStyleList}">
		               		 		<option value="${item.style}" <c:if test="${item.style==obj.expireDateStyle}">selected="selected"</c:if>>${item}</option>
		            			</c:forEach>
	        				</select>
    					</td>
    					<td>
        					<label class="control-label x130"><span class="red">*</span>到期日是否计息：</label>
        					<input <c:if test="${obj.expireDateInterest==1}">checked</c:if> id="ransferable0" name="expireDateInterest" type="radio" value="1" data-toggle="icheck" data-label="是" data-rule="checked">
        					<input <c:if test="${obj.expireDateInterest==0}">checked</c:if> id="ransferable1" name="expireDateInterest" type="radio" value="0" data-toggle="icheck" data-label="否" data-rule="checked">
    					</td>
					</tr>
					<tr>
	                    <td>
	                        <label class="control-label x130"><span class="red">*</span>付息方式：</label>
	                        <select  data-rule="required" name="payInterestType" id="payInterestType" data-toggle="selectpicker" class="show-tick" style="display: none;">
	                            <option value="">-选择付息方式-</option>
	                            <c:forEach var="item" items="${payInterestTypeList}" varStatus="status">
	                                <option value="${item.type}"  <c:if test="${item.type  eq  obj.payInterestType}">selected="selected"</c:if>>${item}</option>
	                            </c:forEach>
	                        </select>
	                    </td>
						<td>
	                        <label class="control-label x130"><span class="red">*</span>计息类型：</label>
	                        <select  data-rule="required" name="interestType" id="interestType" data-toggle="selectpicker" class="show-tick" style="display: none;">
	                            <option value="">-选择付息类型-</option>
	                            <c:forEach var="item" items="${interestTypeList}" varStatus="status">
	                                <option value="${item.type}"  <c:if test="${item.type  eq  obj.interestType}">selected="selected"</c:if>>${item}</option>
	                            </c:forEach>
	                        </select>
	                    </td>
					</tr>
					<tr>
	                    <td>
	                        <label class="control-label x130"><span class="red">*</span>计息频率：</label>
	                        <select  data-rule="required" name="interestRate" id="interestRate" data-toggle="selectpicker" class="show-tick" style="display: none;">
	                            <option value="">-选择计息频率-</option>
	                            <c:forEach var="item" items="${interestRateList}" varStatus="status">
	                                <option value="${item.value}"  <c:if test="${item.value  eq  obj.interestRate}">selected="selected"</c:if>>${item}</option>
	                            </c:forEach>
	                        </select>
	                    </td>
						<td>
	                        <label class="control-label x130"><span class="red">*</span>计息基准：</label>
	                        <select  data-rule="required" name="interestBase" id="interestBase" data-toggle="selectpicker" class="show-tick" style="display: none;">
	                            <option value="">-选择计息基准-</option>
	                            <c:forEach var="item" items="${interestBaseList}" varStatus="status">
	                                <option value="${item.value}"  <c:if test="${item.value  eq  obj.interestBase}">selected="selected"</c:if>>${item}</option>
	                            </c:forEach>
	                        </select>
	                    </td>
					</tr>
			</tbody>	
		 </table>
		 
	</form>
</div>
<script>
var validFunction = {
    validthreedecimal:function(element){
    	var reg = /^\d{1,2}(\.\d{1,3})?$/;
    	if(reg.test(element.value)){
    		return true;
    	}else{
    		return "小于100且最多只支持3位小数";
    	}
    },
    listingLimit:function(element) {
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
$("#projectupate").on('invalid.form', function(e, form, errors) {
	var errortab=$('.msg-wrap.n-error:first').closest('.tab-pane')
	if(errortab.length>0){
		errortab.parent('.tab-content').find('.tab-pane').each(function(){
			errortab.parent('.tab-content').siblings('.nav-tabs').find('li').eq(errortab.index()).find('a').tab('show')
		})
	}
});
$("#submitBtn").click(function(){
	$("#projectupate").data("confirmMsg",function(){
		return '确定要保存吗？';
	})
	$("#projectupate").submit();
})
function projectupateCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message);
		$(this).dialog("refresh");
	}else {
		$(this).alertmsg("error", json.message);
	}
}
function getDateDiff(date1,date2){   
   return Math.floor((date2-date1)/(1000 * 60 * 60 * 24));   
}
function genExpireDate() {
	var add = $("#listingLimit").val();
	var valueDate = $('#valueDate').val();
	if(add && valueDate) {
		valueDate = valueDate.parseDate("yyyy-MM-dd");
		var limitType = $("#listingLimitType").val();
		var yyyy = valueDate.getFullYear();
		var mm = valueDate.getMonth();
		if(limitType==1) {
			valueDate.setDate(valueDate.getDate()+parseInt(add));
		}else if(limitType==2) {
			valueDate.setMonth(valueDate.getMonth()+parseInt(add));
			if((valueDate.getFullYear()*12+valueDate.getMonth()) > yyyy*12+mm+parseInt(add))
				valueDate = new Date(valueDate.getFullYear(),valueDate.getMonth(),0);
		}else if(limitType==3) {
			valueDate.setFullYear(valueDate.getFullYear()+parseInt(add));
			if((valueDate.getFullYear()*12+valueDate.getMonth()) > (yyyy+parseInt(add))*12+mm)
				valueDate = new Date(valueDate.getFullYear(),valueDate.getMonth(),0);
		}
		$("#expireDate").val(valueDate.formatDate("yyyy-MM-dd"));
	}
}

$('#listingLimit').on("blur", function() {
	if($('#listingLimit').val()) {
        genExpireDate();
    }
});

$("#expireDate,#valueDate").on("input propertychange afterchange.bjui.datepicker", function(e, data){
	var valueDate = $('#valueDate').val();
	var expireDate = $('#expireDate').val();
	if(valueDate && expireDate) {
		$("#listingLimit").val(1).selectpicker('refresh');
		valueDate = valueDate.parseDate("yyyy-MM-dd");
		expireDate = expireDate.parseDate("yyyy-MM-dd");
		$("#listingLimit").val((expireDate-valueDate)/86400000);
	}
});
</script>

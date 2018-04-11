<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.fa-info-circle {cursor:pointer;}
</style>
<div class="bjui-pageContent">
	<form id="projectupate" data-loadingmask="true" action="<%=request.getContextPath()%>/project/listing/dxrz/update.do" data-toggle="validate" data-reload="true">
		<input type="hidden" name="id" value="${obj.id}">
		<input type="hidden" name="versionNo" value="${obj.versionNo}">
		<input type="hidden" id="projectStatus" name="projectStatus" value="${obj.projectStatus}">
		<input type="hidden" id="projectSourceId" value="${obj.projectSourceId}">
		<input type="hidden" name="todayForJudge" data-rule="当天:" value="${todayForJudge}">
		<input type="hidden" name="canQuoteMoneyForJudege" data-rule="可挂牌金额:" value="${canQuoteMoney }">
		<input type="hidden" id="loanUserId" value="${record.loanUserId}">
		<input type="hidden" id="loanUserName" value="${obj.loanUserName}">
		<input type="hidden" name="projectRecordId" value=${obj.projectRecordId }>
		<input type="hidden" id="memberId" value="${obj.memberId}">
		<input type="hidden" id="memberName" value="${obj.memberName}">
        <input type="hidden" name="orignExpireDate" id="orignExpireDate" value="${obj.expireDate}">
        <input type="hidden" name="orignValueDate" id="orignValueDate" value="${obj.valueDate}">
        <input type="hidden" name="toAudit" id="toAudit" value="false">
		<table class="table table-condensed table-noborder table-hover">
		 	<thead>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">业务类型：</label> 
						<span>定向融资计划</span>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备案代码：</label> 
						<span>${record.recordCode}</span>
					</td>
					<td>
						<label class="control-label x110">备案名称：</label>
						<span>${record.recordFullName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备案核准规模：</label> 
						<span><fmt:formatNumber value="${record.projectMoney}" pattern="#,##0.00"/> 元</span>
					</td>
					<td>
						<label class="control-label x110">已成功发行规模：</label> 
						<span><fmt:formatNumber value="${quotedMoney }" pattern="#,##0.00"/> 元</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">已挂牌次数：</label> 
						<span>${record.quotedCnt }</span>
					</td>
					<td>
						<label class="control-label x110">可挂牌金额：</label> 
						<span><fmt:formatNumber value="${canQuoteMoney }" pattern="#,##0.00"/> 元</span>
					</td>
				</tr>
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">基本信息 </th></tr>
		 	</thead>
	 		<tbody>
	 			<tr>
		 			<td>
						<label class="control-label x110">挂牌代码：</label> 
						<span>${obj.projectCode }</span>
					</td>
					<td>
						<label class="control-label x110"><span class="red">*</span>挂牌名称：</label>
						<input data-rule="required;" type="text" name="projectFullName"  size="30" value="<c:out value='${obj.projectFullName}'/>" maxlength="120">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110"><span class="red">*</span>挂牌金额：</label> 
						<input data-rule="挂牌金额:required;integer[+];match[lte, canQuoteMoneyForJudege]" data-msg-range="请输入正确的数值" maxlength="12" class="digitUppercase" type="text" name="projectMoney" value="<fmt:formatNumber value="${obj.projectMoney}" pattern="#"/>"> 元
					</td>
					<td>
						<label class="control-label x110"><span class="red">*</span>最低募集规模：</label> 
						<input data-rule="最低募集规模:required;integer[+0];match[lte, projectMoney]" maxlength="12" class="digitUppercase" type="text" name="projectAmountMin" value="<fmt:formatNumber value="${obj.projectAmountMin}" pattern="#"/>"> 元
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">发行人：</label> 
						${obj.loanUserName }
					</td>
					<td>
						<label class="control-label x110"><span class="red">*</span>产品面值：</label> 
						<input data-rule="required;integer[+];" type="text" name="projectUnitPrice"  value='<fmt:formatNumber value="${obj.projectUnitPrice}" pattern="#"/>' maxlength="120">
					</td>
				</tr>
	 		</tbody>
	 		<thead>
		 		<tr><th colspan="2">交易信息 </th></tr>
		 	</thead>
	 		<tbody>
	 			<tr>
					<td>
						<label class="control-label x110"><span class="red">*</span>承销方式：</label> 
						<select data-rule="required;" name="projectSaleagent[0].saleTypeId" data-toggle="selectpicker">
		                    <option value="">--请选择--</option>
		                    <c:forEach var="saleType" items="${saleTypeIdList}"> 
		                    	<option value="${saleType.type}" <c:if test="${saleType.type==saleagentList[0].saleTypeId}">selected="selected"</c:if>>${saleType}</option>
		                    </c:forEach> 
	                    </select>
					</td>
					<td>
						<label class="control-label x110"><span class="red">*</span>发布时间：</label> 
						<input data-rule="发布时间:required;datetime;validate(buyTimeStart)" type="text" name="publishTime" value="<fmt:formatDate value="${obj.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-toggle="datepicker" 
							data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-min-date="{%y}-%M-%d" data-max-date="{%y+2}-%M-{%d+1}">
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="在资产交易平台展示该产品信息的时间"></i>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110"><span class="red">*</span>认购开始时间：</label> 
						<input data-rule="认购开始时间:required;datetime;match[gte, publishTime, date];validate(buyTimeEnd)" type="text" name="buyTimeStart" id="buyTimeStart" value="<fmt:formatDate value="${obj.buyTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+2}-%M-{%d+1}" data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true">

					</td>
					<td>
						<label class="control-label x110"><span class="red">*</span>认购结束时间：</label> 
						<input data-rule="募集期结束时间:required;datetime;match[gt, buyTimeStart, date];" type="text" name="buyTimeEnd" value="<fmt:formatDate value="${obj.buyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+2}-%M-{%d+1}" data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true">
						
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110"><span class="red">*</span>起投金额：</label> 
						<input data-rule="单笔起投金额:required;integer[+];match[lte, projectMoney]" maxlength="12" class="digitUppercase" type="text" id="investAmountMin" name="investAmountMin" value="<fmt:formatNumber value="${obj.investAmountMin}" pattern="#"/>"> 元
					</td>
					<td style="position:static;">
						<label class="control-label x110"><span class="red">*</span>交易方式：</label>
						<input data-rule="checked" value="1" <c:if test="${obj.tradeType==1}">checked</c:if> type="radio" name="tradeType" data-toggle="icheck" value="true" data-label="场内交易">
						<input data-rule="checked" value="2" <c:if test="${obj.tradeType==2}">checked</c:if> type="radio" name="tradeType" data-toggle="icheck" value="true" data-label="场外交易">
						<i tabindex="0" data-trigger="focus" tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="场内交易：通过交易中心交易平台购买；<br/>场外交易：通过自有交易平台购买" data-html="true"></i>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="position:static;">
						<label class="control-label x110"><span class="red">*</span>是否允许转让：</label> 
						<input data-rule="checked" id="canTransfer1" value="1" <c:if test="${obj.canTransfer==1}">checked</c:if> type="radio" name="canTransfer" data-toggle="icheck" value="true" data-label="是">
						<input data-rule="checked" id="canTransfer0" value="0" <c:if test="${obj.canTransfer==0}">checked</c:if> type="radio" name="canTransfer" data-toggle="icheck" value="true" data-label="否">
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="产品成立5个工作日后，持有者可在交易中心进行转让"></i>
					</td>
				</tr>
				<tr id="transfer_flow_limit">
					<td colspan="2">
						<label class="control-label x110"><span class="red">*</span>转让流程期限：</label> 
						产品成立日后第 <input type="text" name="transferAfter" value="${obj.transferAfter }" id="transferAfter" size="4" data-rule="required(#canTransfer1:checked);required(#transferLimit:filled)"> 天开始，转让期限为 <input type="text" name="transferLimit" value="${obj.transferLimit }" id="transferLimit" size="4" data-rule="required(#canTransfer1:checked);required(#transferAfter:filled)"> 天 
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">合格投资者资格要求：</label> 
						<input type="text" name="tradePartyQualification" id="tradePartyQualification" value="<c:out value='${content.tradePartyQualification}'/>" maxlength="100" size="60">
					</td>
				</tr>
	 		</tbody>
		 	<thead>
		 		<tr><th colspan="2">结算信息 </th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td style="position:static;">
                       <label class="control-label x110"><span class="red">*</span>计息方式：</label>
                       <c:forEach var="item" items="${interestModes}"> 
                       		<input value="${item.typeId }"  <c:if test="${obj.interestMode==item.typeId}">checked</c:if> type="radio" name="interestMode" data-toggle="icheck" value="true" data-rule="checked" data-label="${item.typeName }">
	                   </c:forEach>
	                   <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="附息固定：存续期内利率不变；<br/>附息浮动：存续期内利率随市场定期浮动" data-html="true"></i>
                    </td>
					<td>
						<label class="control-label x110"><span class="red">*</span>融资收款方：</label> 
						<select
						data-rule="required" id="receiveUserType"
						onchange="receiveUserBankList()" name="receiveUserType"
						data-toggle="selectpicker" class="show-tick"
						style="display: none;">
							<c:forEach var="item" items="${receiveUserTypeDesc}">
								<option value="${item.type }" <c:if test="${item.type==obj.receiveUserType}">selected="selected"</c:if>><c:choose><c:when test="${item.type ==1 }">融资人</c:when><c:when test="${item.type ==2 }">主承销商</c:when><c:otherwise>${item }</c:otherwise></c:choose></option>
							</c:forEach>
						</select> 
						<input type="hidden"
							<c:if test="${obj.receiveUserType ==1 }">
										value="${obj.loanUserId}" 
									</c:if>
							<c:if test="${obj.receiveUserType ==2 }">
										value="${obj.memberId}" 
									</c:if>
							id="receiveAccountUser">
						<select data-rule="required" name="receiveAccountId"
							id="receiveAccountId" data-toggle="selectpicker" class="show-tick"
							style="display: none;" data-width="100">
								<option value="">-选择银行卡-</option>
								<c:forEach var="item" items="${bankcardList}">
									<option value="${item.id}"
										<c:if test="${item.id==obj.receiveAccountId}">selected="selected"</c:if>>[${item.accountName }]${item.channelName}[${item.cardAccountShow}]</option>
								</c:forEach>
						</select> 
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="募集资金收款账户，一般打款给融资人，特殊情况可打款给主承销商。"></i>
					</td>
				</tr>
				<tr>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>预计起息日：</label>
                       <input data-rule="预计起息日:required;date;match[gte, todayForJudge, date];validate(publishTime);validate(buyTimeEnd)" type="text" value="<fmt:formatDate value="${obj.valueDate}" pattern="yyyy-MM-dd"/>" name="valueDate" id="valueDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true">
                   </td>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>预计到期日：</label>
                       <input data-rule="预计到期日:required;date;match[gte, valueDate, date];" type="text" value="<fmt:formatDate value="${obj.expireDate}" pattern="yyyy-MM-dd"/>" name="expireDate" id="expireDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true">
                   </td>
               </tr>
               <tr>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>产品期限：</label>
                       <input data-rule="required;projectLimit;" data-rule-projectLimit="validFunction.projectLimit" type="text" name="projectLimit" value="${(obj.expireDate==null || obj.valueDate==null)?'':obj.projectLimit}" id="projectLimit" data-autoClose="true">
                       <select data-rule="validate(projectLimit)" name="projectLimitTypeId" id="projectLimitTypeId" data-toggle="selectpicker" class="show-tick">
                           <c:forEach var="item" items="${projectLimitTypeList}">
                               <option value="${item.type}" <c:if test="${item.type==obj.projectLimitTypeId}">selected="selected"</c:if>>${item}</option>
                           </c:forEach>
                       </select>
                       <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="默认期限为起息日（含当日）至到期日（不含当日）的自然天数。也可按产品合约约定自定义修改。"></i>
                   </td>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>还款方式：</label>
                       <select data-rule="required" name="repayTypeId" id="repayTypeId" data-toggle="selectpicker" class="show-tick" style="display: none;">
                           <option value="">-选择还款方式-</option>
                           <c:forEach var="item" items="${systypeRepayList}" varStatus="status">
                               <option value="${item.value}"  <c:if test="${item.value  eq  obj.repayTypeId}">selected="selected"</c:if>>${item}</option>
                           </c:forEach>
                       </select>
                   </td>
               </tr>
               <tr>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>预期年化收益率：</label>
                       <input data-rule="预期收益率:required;validthreedecimal" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="investProfit" value="<fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/>" size="10">%
                   </td>
                   <td>
                   	   <label class="control-label x110"><span class="red">*</span>计息基准天数：</label>
                   	   <input id="jxdate" data-rule="required"  name="interestBaseType" type="radio" <c:if test="${obj.interestBaseType==0}">checked</c:if> value="0" data-toggle="icheck" data-label="" data-rule="checked" data-autoClose="true">
                   	   <input id="interestBaseDays" data-rule="计息基准天数:required;validpositiveinteger" data-rule-validpositiveinteger="validFunction.validpositiveinteger" name="interestBaseDays" value='${obj.interestBaseDays}'>
                   	   <input   data-rule="required" name="interestBaseType" type="radio" <c:if test="${obj.interestBaseType==1}">checked</c:if> value="1" data-toggle="icheck" data-label="365/366" data-rule="checked" data-autoClose="true">
					  
                       <i tabindex="0" data-trigger="focus" data-html="true" class="fa fa-info-circle" data-toggle="popover" data-content="日利率按照此天数计算。通常包含360天、365天和365/366三种方式,其中365/366方式指产品期限跨平/闰年,平年部分按照365天计算,闰年部分按照366天计算。"></i> 
                      
                   </td>
               </tr>
               <tr>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>还款日：</label>
                      	早于兑付日 <input data-rule="required;integer[+]" type="text" name="repayPeriodDay" value="<c:out value='${obj.repayPeriodDay}'/>" maxlength="2" size="3"> 个工作日
                      	<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="融资人还款到交易中心的工作日期"></i>
                   </td>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>兑付日：</label>
                       <span id="settleInvestDaySpan">
					   <c:if test="${obj.repayTypeId==1 }">产品到期日的所属工作日</c:if>
					   <c:if test="${obj.repayTypeId!=1 }">
                           <c:choose>
                               <c:when test="${obj.repayTypeId==5 }">产品设立日起每季末月</c:when>
                               <c:when test="${obj.repayTypeId==6 }">产品设立日起每年度 <input data-autoClose="true" data-rule="range[1~12];settleInvestHasVal;validate(settleInvestDay)" data-rule-settleInvestHasVal="validFunction.settleInvestHasVal" type="text" id="settleInvestMonth" name="settleInvestMonth" value="<c:out value='${obj.getSettleInvestMonthShow()}'/>" maxlength="2" size="3"> 月 </c:when>
                               <c:otherwise>产品设立日起每月</c:otherwise>
                           </c:choose>
                           <input data-autoClose="true" data-rule="range[1~28];settleInvestHasVal;validate(settleInvestMonth)" data-rule-settleInvestHasVal="validFunction.settleInvestHasVal" type="text" id="settleInvestDay" name="settleInvestDay" value="<c:out value='${obj.getSettleInvestDayShow()}'/>" maxlength="2" size="3"> 日的所属工作日
                       </c:if>
					   </span>
					   <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="交易中心兑付给投资者的工作日期"></i>
                   </td>
               </tr>
               <tr>
               		<td>
						<label class="control-label x110">结算方式：</label>
						<input <c:if test="${obj.settleTypeId==1}">checked</c:if> name="settleTypeId" id="settleTypeId1" type="radio" value="1" data-toggle="icheck" data-label="场内结算" data-rule="checked">					
						<input <c:if test="${obj.settleTypeId==2}">checked</c:if> name="settleTypeId" id="settleTypeId3" type="radio" value="2" data-toggle="icheck" data-label="场外结算" data-rule="checked">
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="场内结算：募集资金由交易中心放款到借款人；场外结算：募集资金不经交易中心放款到借款人" data-html="true"></i>
					</td>
					<td>
					</td>
               </tr>
               <tr>
                   <td>
                       <label class="control-label x110"><span class="red">*</span>还款资金：</label>
                     	<input data-rule="checked" value="1" <c:if test="${obj.manageDuration==1}">checked</c:if> type="radio" name="manageDuration" id="manageDuration0" data-toggle="icheck" value="true" data-label="是">
						<input data-rule="checked" value="0" <c:if test="${obj.manageDuration==0}">checked</c:if> type="radio" name="manageDuration" id="manageDuration1" data-toggle="icheck" value="true" data-label="否">
						 经过交易中心
                   </td>
                   <td>
                       <label class="control-label x120"><span class="red">*</span>生成还款兑付计划：</label>
                       <input <c:if test="${obj.generatePlan==1}">checked</c:if> <c:if test="${obj.manageDuration==0}">disabled</c:if> id="generatePlan1" name="generatePlan" type="radio" value="1" data-toggle="icheck" data-label="交易中心" data-rule="checked" data-autoClose="true">						
					   <input <c:if test="${obj.generatePlan==0}">checked</c:if> id="generatePlan0" name="generatePlan" type="radio" value="0" data-toggle="icheck" data-label="主承销商" data-rule="checked"  data-autoClose="true">
                       <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="如选择主承销商生成，则还款时需由其上传还款计划"></i> 
                   </td>
               </tr>
               <tr>
                   <td>
						<label class="control-label x110">兑付收款方：</label>						
						<input <c:if test="${obj.payinvestType==1}">checked</c:if> <c:if test="${obj.generatePlan==0 || obj.manageDuration==0}">disabled</c:if> id="payinvestType1" name="payinvestType" type="radio" value="1" data-toggle="icheck" data-label="投资人" data-rule="checked" data-autoClose="true">						
						<input <c:if test="${obj.payinvestType==2}">checked</c:if> <c:if test="${obj.manageDuration==0}">disabled</c:if> id="payinvestType2" name="payinvestType" type="radio" value="2" data-toggle="icheck" data-label="主承销商" data-rule="checked" data-autoClose="true">
					</td>
		 			<td>
						<label class="control-label x110">兑付中间方：</label>
						<input name="payinvestId" id="payinvestId" type="hidden" value="${obj.payinvestId }" >
						<input data-rule="required(#payinvestType2:checked);chooseAdvisorUser" type="text" placeholder="请输入机构全称" name="payinvestname" id="payinvestname" value="${payInvestName}"  data-rule-chooseAdvisorUser="chooseAdvisorUser" <c:if test="${obj.payinvestType==1}">disabled</c:if>>
						<select data-rule="required(payinvestname)" name="payinvestBankCardId" id="payinvestBankCardId" data-toggle="selectpicker" class="show-tick" data-width="150" <c:if test="${obj.payinvestType==1}">disabled</c:if>  >
							<option value="0">-选择银行卡-</option>
							<c:forEach var="item" items="${payInvestBankcardList}">
							<option value="${item.id}" <c:if test="${item.id==obj.payinvestBankCardId}">selected="selected"</c:if>>[${item.accountName}]${item.channelName}[${item.cardAccountShow}]</option>
							</c:forEach>
						</select>
					</td>
				</tr>
               </tr>
		 	</tbody>
			<thead>
		 		<tr><th colspan="2">费用相关</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">承销服务费：</label> 
						<input data-rule="required;validthreedecimal;addlt100" data-rule-addlt100="validFunction.addlt100" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="advioserFee" id="advioserFee" value="<fmt:formatNumber value="${obj.advioserFee*100}" pattern="0.000"/>" size="10"> %/次
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="由主承销商向融资人收取<br/>计算公式：服务费=费率*确权金额" data-html="true"></i>
					</td>
					<td>
						<label class="control-label x110">交易服务费：</label> 
						<input type="text" data-rule="required;validthreedecimal;addlt100" data-rule-addlt100="validFunction.addlt100" data-rule-validthreedecimal="validFunction.validthreedecimal" name="platformFee" id="platformFee" value="<fmt:formatNumber value="${obj.platformFee*100}" pattern="0.000"/>" size="8"> %
						<select data-rule="required;" id="platformFeeType" name="platformFeeType" data-toggle="selectpicker">
	                    <c:forEach var="item" items="${platformFeeTypeList}" varStatus="status">
	                    	<option value="${item.type}" <c:if test="${item.type==obj.platformFeeType}">selected</c:if>>${item}</option>
						</c:forEach>
	                    </select>
	                    <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="由交易中心向融资人收取<br/>计算公式：服务费=费率*确权金额" data-html="true"></i>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">逾期罚息率：</label> 
						<input data-rule="required;validfourdecimal" data-rule-validfourdecimal="validFunction.validfourdecimal" type="text" name="overduePayFee" value="<fmt:formatNumber value="${obj.overduePayFee*100}" pattern="0.0000"/>" size="10"> %/天
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="指还款人未按期还款，需对未还本息罚息，支付给投资者"></i>
					</td>
				</tr>
			</tbody>
			<thead>
		 		<tr><th colspan="2">其他相关</th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td colspan="2">
						<label class="control-label x130">重大事项披露：</label> 
						<input type="text" name="projectInfo" id="projectInfo" value="<c:out value='${content.projectInfo}'/>" maxlength="100" size="60">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">本产品其他说明：</label> 
						<input type="text" name="basicAssetNote" id="basicAssetNote" value="<c:out value='${content.basicAssetNote}'/>" maxlength="100" size="60">
					</td>
				</tr>
			</tbody>
			<tr>
				<td colspan="2" style="text-align:center;">
					<button type="submit" class="btn-blue submitButton" data-icon="save" id="saveBtn">保存</button>
					<button type="button" class="btn-close" data-icon="close">关闭</button>	
				</td>
			</tr>
		 </table>
	</form>
</div>
<script>
if(${obj.canTransfer==0}) {
	$("#transfer_flow_limit").hide();	
}else {
	$("#transfer_flow_limit").show();	
}
    $(function(){
        if($("input[name='interestBaseType'][checked]").val()==1) {
            $('#interestBaseDays').val("");
            $('#interestBaseDays').attr('disabled','disabled');
		}
	});
//计息基准天数前端验证
$('input[name="interestBaseType"]').on("ifChecked", function(){
	$('#interestBaseDays').val("");
	$('#interestBaseDays').attr('disabled','disabled');
});
$('#jxdate').on("ifChecked", function(){
	
	
	$('#interestBaseDays').removeAttr("disabled");
	
});

var validFunction = {
	buyTimeEnd : function(element) {
		var valueDate = $("#valueDate").val();
		if(new Date(valueDate).getTime() < new Date(element.value.substring(0,10)).getTime()){
			return "不能大于起息日";
		}
		return true;
	},
	settleInvestHasVal : function(element) {
		if($("#repayTypeId").val() == 6) {
			if($("#settleInvestMonth").val() == '') {
				return "月份不能为空";
			}else if($("#settleInvestDay").val() == '') {
				return "日期不能为空";
			}
		}else if($("#repayTypeId").val() != 1) {
			if($("#settleInvestDay").val() == '') {
				return "日期不能为空";
			}
		} 
		return true;
	},
	validfourdecimal:function(element){
    	var reg = /^\d{1,2}(\.\d{1,4})?$/;
    	if(reg.test(element.value)){
    		return true;
    	}else{
    		return "小于100且最多只支持4位小数";
    	}
    },
    validthreedecimal:function(element){
    	var reg = /^\d{1,2}(\.\d{1,3})?$/;
    	if(reg.test(element.value)){
    		return true;
    	}else{
    		return "小于100且最多只支持3位小数";
    	}
    },
    addlt100: function(element) {
    	if($("#investAdvioserFee").val() == '' || $("#advioserFee").val() == '')
    		return true;
    	var addv = Number(parseFloat($("#advioserFee").val())+parseFloat($("#platformFee").val()));
    	if(addv>=0 && addv<100){
    		return true;
    	}else{
    		return "请输入正确的数值";
    	}
    },
    modelinvestmin:function(element){
    	var investmin = $("input[name='investAmountMin']").val();
    	if(investmin == null){
    		return true;
    	}
    	if(investmin % element.value == 0){
    		return true;
    	}
    	return false||"起投金额必须为追加金额的倍数"
    },
    saleMoney:function(element) {
    	var investAmountMin = $("#investAmountMin").val();
    	if(parseInt(element.value) < parseInt(investAmountMin)) {
    		return "承销金额必须大于等于起投金额";
    	}
    	return true;
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
    },
    validpositiveinteger:function(element){
        var reg = /^[1-9][0-9]{0,9}$/;
        if(reg.test(element.value)){
            return true;
        }else{
            return "请输入正整数且不能超过10位";
        }
    }
}
var projectLimitModifiedValidator = function(){};
projectLimitModifiedValidator.modified = ($(":hidden[name=orignExpireDate]").val()!='' && $(":hidden[name=orignValueDate]").val()!='');
projectLimitModifiedValidator.isModified = function () {
    return projectLimitModifiedValidator.modified;
}
projectLimitModifiedValidator.setModifiedTrue = function () {
    projectLimitModifiedValidator.modified = true;
}
$("#platformFeeType").on("change", function(){
	var text = '服务费=费率*确权金额';
	if($(this).val() == 2) {
		text += '*期限(天数)/365';
	}
	$(this).siblings('p.text-muted').text(text);
});
$('.submitButton').click(function(){
	var _this=$(this);
	$("#projectupate").data("confirmMsg",function(){
		return '确定要'+_this.text().trim()+'吗？';
	});
	if($("#payinvestname").val() != ""){
		$("#projectupate").attr("data-callback", "projectupateCallback").data('validator').options.ignore = '#payinvestname';
	}else{
		$("#projectupate").attr("data-callback", "projectupateCallback");
	}
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
function projectupateCallback(json) {
	if(json.statusCode == 200) {
		if(json.forCheck) {
			$(this).alertmsg("ok", "提交成功，请等待审核。", {autoClose:false});
			$(this).dialog("closeCurrent").navtab("refresh");
		}else {
			$(this).alertmsg("correct", json.message);
			$(this).dialog("refresh");
			$("#projectName").focus();
		}
	}else {
		$(this).alertmsg("error", json.message);
	}
}
function getDateDiff(date1,date2){   
   return Math.floor((date2-date1)/(1000 * 60 * 60 * 24));   
}
$("#repayTypeId").on("change", function(e, data) {
	var html = "产品到期日的所属工作日";
	if($(this).val() != 1) {
		if($(this).val() == 5) {
			html = "产品设立日起每季末月";
		}else if($(this).val() == 6) {
			html = '产品设立日起每年度 <input data-rule="range[1~12];settleInvestHasVal;validate(settleInvestDay)" data-rule-settleInvestHasVal="validFunction.settleInvestHasVal" type="text" class="form-control ok" style="width: 30px;" id="settleInvestMonth" name="settleInvestMonth" value="${obj.getSettleInvestMonthShow()}" maxlength="2" size="3"> 月';
		}else {
			html = "产品设立日起每月";
		}
		html += '<input data-rule="range[1~28];settleInvestHasVal;validate(settleInvestMonth)" data-rule-settleInvestHasVal="validFunction.settleInvestHasVal" type="text" class="form-control ok" style="width: 30px;" id="settleInvestDay" name="settleInvestDay" value="${obj.getSettleInvestDayShow()}" maxlength="2" size="3"> 日的所属工作日';
	}
	$("#settleInvestDaySpan").html(html);
});
function genExpireDate() {
	var add = $("#projectLimit").val();
	var valueDate = $('#valueDate').val();
	if(add && valueDate && !projectLimitModifiedValidator.isModified()) {
		valueDate = valueDate.parseDate("yyyy-MM-dd");
		var limitType = $("#projectLimitTypeId").val();
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

$('#projectLimit').on("blur", function() {
    if($('#projectLimit').val()) {
        genExpireDate();
        projectLimitModifiedValidator.setModifiedTrue();
    }
});

$("#expireDate,#valueDate").on("input propertychange afterchange.bjui.datepicker", function(e, data){
	var valueDate = $('#valueDate').val();
	var expireDate = $('#expireDate').val();
	if(valueDate && expireDate && !projectLimitModifiedValidator.isModified()) {
		$("#projectLimitTypeId").val(1).selectpicker('refresh');
		valueDate = valueDate.parseDate("yyyy-MM-dd");
		expireDate = expireDate.parseDate("yyyy-MM-dd");
		$("#projectLimit").val((expireDate-valueDate)/86400000);
        projectLimitModifiedValidator.setModifiedTrue();
	}
});
function addBankClose() {
	var receiveUserType = $("#receiveUserType").val();
	var userId = $("#loanUserId").val();
	if(receiveUserType == 2) {
		userId = $("#memberId").val();
	}
	getUserBank(userId);
}
$('#loader').on('afterchange.bjui.lookup', function(e, data) {
	var userId = data.loanUserId;
	if(data.receiveUserType == 2 ) {
		userId = data.memberId;
		$("#receiveUserName").html($("#memberName").val())
	}else {
		$("#receiveUserName").html($("#loanUserName").val())
	}
	$('#receiveAccountUser').val(userId)
	getUserBank(data.loanUserId);
});
function getUserBank(userId) {
	$.ajax({
		url: '<%=request.getContextPath()%>/user/bankcard/ajaxlist.do?userId='+userId,
		type: 'get',
		cache: 'false',
		dataType: 'json',
		success: function(data) {
			var array = ['<option value="">-选择银行卡-</option>'];
			var dbSelected = 0;
			<c:if test="${obj.receiveAccountId!=null}">
			dbSelected = ${obj.receiveAccountId};
			</c:if>
			for(var i=0; i<data.length; i++) {
				var bank = data[i];
				var selected = "";
				if(dbSelected == bank.id) {
					selected = "selected";
				}
				array.push('<option value="'+bank.id+'" '+selected+'>'+'['+bank.accountName+']'+bank.channelName+'['+bank.cardAccountShow+']</option>');
			}
			$('#receiveAccountId').html(array.join("")).selectpicker('refresh');
		}
	});
}
function receiveUserBankList() {
	
	var receiveUserType = $("#receiveUserType").val();
	var userId = $("#loanUserId").val();
	if(receiveUserType == 2) {
		userId = $("#memberId").val();
		$("#receiveUserName").html($("#memberName").val())
	}else {
		$("#receiveUserName").html($("#loanUserName").val())
	}
	$('#receiveAccountUser').val(userId)
	getUserBank(userId);
}
/**
 * 选择兑付中间方
 */
function chooseAdvisorUser(){
	var payinvestname = $("#payinvestname").val();
	return $.ajax({
		url:'<%=request.getContextPath()%>/user/chooseuser.do',
		type:'post',
		data:{
			memberName:payinvestname
		},
		success:function(data){
			if(data.statusCode == 200){
				$("#payinvestId").val(data.userId);
				getAdvisorBank(data.userId);
			}
		}
	})
}

function getAdvisorBank(userId) {
	$.ajax({
		url: '<%=request.getContextPath()%>/user/bankcard/ajaxlist.do?userId='+userId,
		type: 'get',
		cache: 'false',
		dataType: 'json',
		success: function(data) {
			var array = ['<option value="">-选择银行卡-</option>'];
			var dbSelected = 0;
			<c:if test="${obj.payinvestBankCardId!=null}">
			dbSelected = ${obj.payinvestBankCardId};
			</c:if>
			for(var i=0; i<data.length; i++) {
				var bank = data[i];
				var selected = "";
				if(dbSelected == bank.id) {
					selected = "selected";
				}
				array.push('<option value="'+bank.id+'" '+selected+'>'+'['+bank.accountName+']'+bank.channelName+'['+bank.cardAccountShow+']</option>');
			}
			$('#payinvestBankCardId').html(array.join("")).selectpicker('refresh');
		}
	});
}
$('input[name="manageDuration"]').on("ifChecked", function(){
	
	if($(this).val()==1) {//经过交易所
		$('input[name="payinvestType"]').iCheck('enable');
		if($('input[name="payinvestType"]:checked').val()==1){
			$("#payinvestname").attr("disabled",true);  
			$("#payinvestBankCardId").attr("disabled",true);
			$("#payinvestname").val("");
			$("#payinvestBankCardId").html('<option value="">-选择银行卡-</option>').selectpicker('refresh');
		}else{
			$("#payinvestname").attr("disabled",false);  
			$("#payinvestBankCardId").attr("disabled",false);
		} 
	}else{
		$('input[name="payinvestType"]').iCheck('disable').iCheck('uncheck');
		$("#payinvestname").val("");
		$("#payinvestBankCardId").html('<option value="">-选择银行卡-</option>').selectpicker('refresh');
		$("#payinvestname").attr("disabled",true);
		$("#payinvestBankCardId").attr("disabled",true);
	}	
})

$('input[name="canTransfer"]').on("ifChecked", function(){
	
	if($(this).val() == 1) {//允许进行转让
		$("#transfer_flow_limit").show();
	}else{
		$("#transfer_flow_limit").hide();
	}	
})

$('input[name="payinvestType"]').on("ifChecked", function(){
	
	if($(this).val()==1) {//兑付收款方为投资者
		$("#payinvestname").attr("disabled",true);  
		$("#payinvestBankCardId").attr("disabled",true);
		$("#payinvestname").val("");
		$("#payinvestBankCardId").html('<option value="">-选择银行卡-</option>').selectpicker('refresh');
		$("#payinvestId").val("");
	}else{
		$("#payinvestname").attr("disabled",false);  
		$("#payinvestBankCardId").attr("disabled",false);
	}	
})

$('#generatePlan0').on("ifChecked", function(){
	$('#payinvestType1').iCheck('disable').iCheck('uncheck');
})
$('#generatePlan0').on("ifUnchecked", function(){
	$('#payinvestType1').iCheck('enable');
})

$('#manageDuration1').on("ifChecked", function(){
	$('#generatePlan1').iCheck('disable');
	$('input[name=generatePlan]').iCheck('uncheck');
})
$('#manageDuration1').on("ifUnchecked", function(){
	$('#generatePlan1').iCheck('enable');
})

</script>

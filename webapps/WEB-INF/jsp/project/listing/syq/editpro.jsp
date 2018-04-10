<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
	<form id="projectupate" data-loadingmask="true" action="<%=request.getContextPath()%>/project/listing/syq/update.do" data-toggle="validate" data-reload="true">
		 <input type="hidden" name="id" value="${obj.id}">
		 <input type="hidden" name="versionNo" value="${obj.versionNo}">
		 <input type="hidden" id="projectStatus" name="projectStatus" value="${obj.projectStatus}">
		 <input type="hidden" id="userNumberLimit" value="${userNumberLimit}">
		  <input type="hidden" id="projectSourceId" value="${obj.projectSourceId}">
		  <input type="hidden" name="todayForJudge" data-rule="当天:" value="${todayForJudge}">
		  <input type="hidden" name="canQuoteMoneyForJudege" data-rule="可挂牌金额:" value="${canQuoteMoney }">
		  <input type="hidden" id="loanUserId" value="${record.loanUserId}">
		  <input type="hidden" name="projectRecordId" value=${obj.projectRecordId }>
		  <input type="hidden" name="toAudit" id="toAudit" value="false">
		  <input type="hidden" name="orignExpireDate" id="orignExpireDate" value="${obj.expireDate}">
		  <input type="hidden" name="orignValueDate" id="orignValueDate" value="${obj.valueDate}">
		  <input type="hidden" name="interestBaseType" id="interestBaseType" value="${obj.interestBaseType}">
		  <input type="hidden" name="interestBaseDays" id="interestBaseDays" value="${obj.interestBaseDays}">
		 <table class="table table-condensed table-noborder table-hover">
		 	<thead>
		 	</thead>
		 	<tbody>				<tr>
		 			<td>
						<label class="control-label x130">业务类型：</label> 
						<span>收益权转让计划</span>
					</td>
					<td>
						<label class="control-label x130">备案代码：</label> 
						<span>${record.recordCode}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130">产品类型：</label> 
						<span><c:forEach var="item" items="${systypeProjectList}" varStatus="status">
								<c:if test="${item.id==obj.projectTypeId}">${item.typeName}</c:if>
							</c:forEach></span>
					</td>
					<td>
						<label class="control-label x130">备案名称：</label> 
						<span>${record.recordFullName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130">发行人：</label> 
						<span>${obj.loanUserName}</span>
					</td>
					<td>
						<label class="control-label x130">已挂牌次数：</label> 
						<span>${record.quotedCnt }</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130">备案核准规模：</label> 
						<span><fmt:formatNumber value="${record.projectMoney}" pattern="#,#00.00"/> 元</span>
					</td>
					<td>
						<label class="control-label x130">可挂牌金额：</label> 
						<span><fmt:formatNumber value="${canQuoteMoney}" pattern="#,##0.00"/> 元</span>
					</td>
				</tr>
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">基本信息</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x130">挂牌代码：</label> 
						<span>${obj.projectCode }</span>
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>挂牌名称：</label>
						<input  data-rule="required;" type="text" name="projectFullName"  size="25" value="<c:out value='${obj.projectFullName}'/>" maxlength="120">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>挂牌金额：</label> 
						<input  data-rule="挂牌金额:required;integer[+];match[lte, canQuoteMoneyForJudege]" data-msg-range="请输入正确的数值" maxlength="12" class="digitUppercase" type="text" name="projectMoney" value="<fmt:formatNumber value="${obj.projectMoney}" pattern="#"/>"> 元
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>最低募集规模：</label> 
						<input  data-rule="最低募集规模:required;integer[+0];match[lte, projectMoney]" maxlength="12" class="digitUppercase" type="text" name="projectAmountMin" value="<fmt:formatNumber value="${obj.projectAmountMin}" pattern="#"/>">元
					</td>
				</tr>
               
                
		 	</tbody>
			<thead>
		 		<tr><th colspan="2">交易信息</th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>起投金额：</label> 
						<input  data-rule="单笔起投金额:required;integer[+];match[lte, projectMoney]" maxlength="12" class="digitUppercase" type="text" id="investAmountMin" name="investAmountMin" value="<fmt:formatNumber value="${obj.investAmountMin}" pattern="#"/>"> 元
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>追加金额：</label> 
						<input  data-rule="required;integer[+];match[lte, projectMoney];" data-rule-modelinvestmin="validFunction.modelinvestmin" maxlength="12" type="text" class="digitUppercase" name="investAmountAppend" value="<fmt:formatNumber value="${obj.investAmountAppend}" pattern="#"/>"> 元
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>投资上限：</label> 
						<input  data-rule="投资上限:required;investAmountMax;integer[+];match[lte, projectMoney]" maxlength="12" type="text" class="digitUppercase" name="investAmountMax" value="<fmt:formatNumber value="${obj.investAmountMax}" pattern="#"/>"> 元
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>发布时间：</label> 
						<input  data-rule="发布时间:required;datetime;validate(buyTimeStart);match[gte, todayForJudge, datetime]" type="text" name="publishTime" value="<fmt:formatDate value="${obj.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-toggle="datepicker" 
							data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-min-date="{%y}-%M-%d" data-max-date="{%y+2}-%M-{%d+1}">
							<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="在交易平台展示该产品信息的时间"></i>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>认购开始时间：</label> 
						<input  data-rule="认购开始时间:required;datetime;match[gte, publishTime, date];validate(buyTimeEnd)" type="text" name="buyTimeStart" id="buyTimeStart" value="<fmt:formatDate value="${obj.buyTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+2}-%M-{%d+1}" data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true">

					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>认购结束时间：</label> 
						<input  data-rule="认购结束时间:required;datetime;match[gt, buyTimeStart, date];" type="text" name="buyTimeEnd" value="<fmt:formatDate value="${obj.buyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+2}-%M-{%d+1}" data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true">
					</td>
				</tr>
				<tr>
					<td>
                        <label class="control-label x130"><span class="red">*</span>年化收益率：</label>
                        <input  data-rule="预期收益率:required;validthreedecimal" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="investProfit" value="<fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/>" size="10">%
                    </td>
                    <td>
                        <label class="control-label x130"><span class="red">*</span>还款方式：</label>
                        <select  data-rule="required" name="repayTypeId" id="repayTypeId" data-toggle="selectpicker" class="show-tick" style="display: none;">
                            <option value="">-选择还款方式-</option>
                            <c:forEach var="item" items="${systypeRepayList}" varStatus="status">
                                <option value="${item.value}"  <c:if test="${item.value  eq  obj.repayTypeId}">selected="selected"</c:if>>${item}</option>
                            </c:forEach>
                        </select>
                    </td>
                    </td>
					
				</tr>
					
				<tr>
					<td colspan="2">
						<p><b>本产品挂牌交易，如需指定投资顾问进行定量销售，请设置以下信息：<a name="anchor_agent" id="anchor_agent"/></b></p>
						<table id="saleagentTE" data-noread="true" data-reload-navtab="true" class="table other-table table-bordered"  data-toggle="tabledit" data-initnum="0" data-action="" data-single-noindex="true">
		            		<thead>
			                	<tr>
				                    <th title="指定投资顾问" width="30%"><input onblur="agentMoney(this);" placeholder="请输入投资顾问的机构全称" data-rule="required;" type="text" name="projectSaleagent[#index#].saleMemberName"/></th>
				                    <th title="销售方式" width="20%">
				                    	<select data-rule="required;" data-width="98%" name="projectSaleagent[#index#].saleTypeId" data-toggle="selectpicker">
					                    <option value="">--请选择--</option>
					                    <c:forEach var="saleType" items="${saleTypeIdList}"> 
					                    	<option value="${saleType.type}">${saleType.type}</option>
					                    </c:forEach> 
					                    </select>
				                    </th>
				                    <th title="销售额度(元)" width="20%"><input value="<fmt:formatNumber value="${obj.projectMoney}" pattern="#"/>" data-rule="required;saleMoney" data-rule-saleMoney="validFunction.saleMoney" type="text" class="digitUppercase" name="projectSaleagent[#index#].saleMoney" style="width:90%;" maxlength="12"/>元</th>
				                    <th title="指定投资顾问服务费(%)" width="20%"><input data-rule="required;validthreedecimal" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="projectSaleagent[#index#].saleFeeRate" style="width:90%;"/>%</th>
				                    <th title="新增" data-addbtn="true" width="10%">
				                        <a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" id="_#index#">删除</a>
				                    </th>
			                	</tr>
		            		</thead>
					        <tbody>
					        	<c:forEach var="agent" items="${saleagentList}" varStatus="status"> 
					        	<tr data-id="${ status.index + 1}">
						        	<td>${agent.companyName }</td>
						        	<td data-val="${agent.saleTypeId }"></td>
						        	<td>${agent.saleMoney }</td>
						        	<td><fmt:formatNumber value="${agent.saleFeeRate*100 }" pattern="0.000"/></td>
						        	<td data-noedit="true">
							        	<input type="hidden" value="${agent.id }" name="projectSaleagent[#index#].id"/>
							        	<a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" id="_#index#">删除</a>
						        	</td>
					        	</tr>
					        	</c:forEach>
					        </tbody>
	        			</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130" >合格投资者资格要求：</label> 
						<textarea  name="tradePartyQualification"  data-toggle="autoheight" style="width:60%" ><c:out value='${content.tradePartyQualification}'/></textarea>
					</td>
				</tr>
			</tbody>
			<thead>
		 		<tr><th colspan="2">结算信息</th></tr>
		 	</thead>
		 	<tbody>	 	
			 		<tr>
						<td style="position:static;">
							<label class="control-label x130"><span class="red">*</span>交易方式：</label>
							<input value="1" <c:if test="${obj.tradeType==1}">checked</c:if> type="radio" name="tradeType" id="inside" data-toggle="icheck" value="true" data-label="场内交易">
							<input value="2" <c:if test="${obj.tradeType==2}">checked</c:if> type="radio" name="tradeType" id="outside" data-toggle="icheck" value="true" data-label="场外交易">
							<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="场内交易：通过交易中心交易平台购买；场外交易：通过自有交易平台购买"></i>
						</td>
						<td>
							<label class="control-label x130"><span class="red">*</span>结算方式：</label>
							<input <c:if test="${obj.settleTypeId==1}">checked</c:if> name="settleTypeId" id="settleTypeId1" type="radio" value="1" data-toggle="icheck" data-label="场内结算" data-rule="checked">					
							<input <c:if test="${obj.settleTypeId==2}">checked</c:if> <c:if test="${obj.tradeType==1}">disabled</c:if> name="settleTypeId" id="settleTypeId3" type="radio" value="2" data-toggle="icheck" data-label="场外结算" data-rule="checked">
							<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="场内结算：募集资金由交易中心放款到借款人；场外结算：募集资金不经交易中心放款到借款人"></i>
						</td>
				</tr>
				<tr>
					
						<td>
                        <label class="control-label x130"><span class="red">*</span>融资收款方：</label>发行人
                        </td>
                         <td>
                        <label class="control-label x130"><span class="red" id="receiveAccountTip" <c:if test="${obj.settleTypeId != 1}">style="display:none;"</c:if>>*</span>收款账户：</label>
                        <select data-rule="required" <c:if test="${obj.settleTypeId != 1}">novalidate="true"</c:if> name="receiveAccountId" id="receiveAccountId" data-toggle="selectpicker" class="show-tick" style="display: none;">
                            <option value="">-请选择-</option>
                            <c:forEach var="item" items="${bankcardList}">
                                <option value="${item.id}" <c:if test="${item.id==obj.receiveAccountId}">selected="selected"</c:if>>${item.channelName}[${item.cardAccountShow}]</option>
                            </c:forEach>
                        </select>
                        </td>
				</tr>
				
					<tr>
    					<td>
        					<label class="control-label x130"><span class="red">*</span>预计起息日：</label>
       						 <input  data-rule="预计起息日:required;date;match[gte, todayForJudge, date];validate(publishTime);validate(buyTimeEnd)" type="text" value="<fmt:formatDate value="${obj.valueDate}" pattern="yyyy-MM-dd"/>" name="valueDate" id="valueDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true">
    					</td>
    					<td>
        					<label class="control-label x130"><span class="red">*</span>预计到期日：</label>
        					<input  data-rule="预计到期日:required;date;match[gte, valueDate, date];" type="text" value="<fmt:formatDate value="${obj.expireDate}" pattern="yyyy-MM-dd"/>" name="expireDate" id="expireDate" data-toggle="datepicker" data-min-date="{%y}-%M-%d" data-max-date="{%y+13}-%M-{%d+1}" data-nobtn="true">
    					</td>
					</tr>
					<tr>
    					<td colspan="2">
        				<label class="control-label x130"><span class="red">*</span>产品期限：</label>
        				<input  data-rule="required;projectLimit;" data-rule-projectLimit="validFunction.projectLimit" type="text" name="projectLimit" value="${(obj.expireDate==null || obj.valueDate==null)?'':obj.projectLimit}" id="projectLimit" data-autoClose="true">
        				<select  data-rule="validate(projectLimit)" name="projectLimitTypeId" id="projectLimitTypeId" data-toggle="selectpicker" class="show-tick">
            			<c:forEach var="item" items="${projectLimitTypeList}">
               		 <option value="${item.type}" <c:if test="${item.type==obj.projectLimitTypeId}">selected="selected"</c:if>>${item}</option>
            		</c:forEach>
        				</select>
       					 <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="默认期限为起息日（含当日）至到期日（不含当日）的自然天数。也可按产品合约约定自定义修改。"></i>
    					</td>
					</tr>
					<tr>
   						 <td>
       						 <label class="control-label x130"><span class="red">*</span>兑付日：</label>
                        <span id="settleInvestDaySpan">
						<c:if test="${obj.repayTypeId==1 }">产品到期日的所属工作日</c:if>
						<c:if test="${obj.repayTypeId!=1 }">
                            <c:choose>
                                <c:when test="${obj.repayTypeId==5 }">产品设立日起每季末月</c:when>
                                <c:when test="${obj.repayTypeId==6 }">产品设立日起每年度 <input data-autoClose="true" data-rule="range[1~12];settleInvestHasVal;validate(settleInvestDay)" data-rule-settleInvestHasVal="validFunction.settleInvestHasVal" type="text" id="settleInvestMonth" name="settleInvestMonth" value="<c:out value='${obj.getSettleInvestMonthShow()}'/>" maxlength="2" size="3"> 月 </c:when>
                                <c:otherwise>产品设立日起每月</c:otherwise>
                            </c:choose>
                            <input data-autoClose="true" data-rule="required;range[1~28];settleInvestHasVal;validate(settleInvestMonth)" data-rule-settleInvestHasVal="validFunction.settleInvestHasVal" type="text" id="settleInvestDay" name="settleInvestDay" value="<c:out value='${obj.getSettleInvestDayShow()}'/>" maxlength="2" size="3"> 日的所属工作日
                        </c:if>
						</span>
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="交易中心兑付给投资者的工作日期，即结息日"></i>
    						</td>
    						<td>
      						  <label class="control-label x130"><span class="red">*</span>还款日：</label>
       						 早于兑付日 <input data-rule="required;integer[+]" type="text" name="repayPeriodDay" value="<c:out value='${obj.repayPeriodDay}'/>" maxlength="2" size="3"> 个工作日
       						 <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="借款人还款到交易中心的工作日期"></i>
    						</td>
						</tr>
				
				
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>还款资金：</label>
						<input <c:if test="${obj.manageDuration==1}">checked</c:if> id="manageDuration0" name="manageDuration" type="radio" value="1" data-toggle="icheck" data-label="是" data-rule="checked">
						<input <c:if test="${obj.manageDuration==0}">checked</c:if> id="manageDuration1" name="manageDuration" type="radio" value="0" data-toggle="icheck" data-label="否" data-rule="checked">
						到交易中心
						
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>生成还款计划：</label>
						<input <c:if test="${obj.generatePlan==1}">checked</c:if> <c:if test="${obj.manageDuration==0}">disabled</c:if> id="generatePlan1" name="generatePlan" type="radio" value="1" data-toggle="icheck" data-label="是" data-rule="checked" data-autoClose="true">						
						<input <c:if test="${obj.generatePlan==0}">checked</c:if> id="generatePlan0" name="generatePlan" type="radio" value="0" data-toggle="icheck" data-label="否" data-rule="checked" data-autoClose="true">
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="是：交易中心生成还款计划；否：交易中心不生成还款计划。"></i>
					</td>
				</tr>
		 		<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>兑付收款方：</label>						
						<input <c:if test="${obj.payinvestType==1}">checked</c:if> <c:if test="${obj.generatePlan==0 || obj.manageDuration==0}">disabled</c:if> id="payinvestType1" name="payinvestType" type="radio" value="1" data-toggle="icheck" data-label="投资人" data-rule="checked" data-autoClose="true">						
						<input <c:if test="${obj.payinvestType==2}">checked</c:if> <c:if test="${obj.manageDuration==0}">disabled</c:if> id="payinvestType2" name="payinvestType" type="radio" value="2" data-toggle="icheck" data-label="中间方" data-rule="checked" data-autoClose="true">
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="兑付资金收款账户，一般打款给投资人，特殊情况可打款给中间方（产品管理人、投资顾问）。"></i>
					</td>
		 			<td>
						<label class="control-label x130"><span class="red">*</span>兑付收款账户：</label>
						<input name="payinvestId" id="payinvestId" type="hidden" value="${obj.payinvestId }" >
						<input data-rule="required(#payinvestType2:checked);chooseAdvisorUser" type="text" placeholder="请输入会员机构全称" name="payinvestname" id="payinvestname" value="${payInvestName}"  data-rule-chooseAdvisorUser="chooseAdvisorUser" <c:if test="${obj.payinvestType==1}">disabled</c:if>>
						<select data-rule="required(payinvestname)" name="payinvestBankCardId" id="payinvestBankCardId" data-toggle="selectpicker" class="show-tick" data-width="150" <c:if test="${obj.payinvestType==1}">disabled</c:if>  >
							<option value="0">-选择银行卡-</option>
							<c:forEach var="item" items="${payInvestBankcardList}">
							<option value="${item.id}" <c:if test="${item.id==obj.payinvestBankCardId}">selected="selected"</c:if>>[${item.accountName}]${item.channelName}[${item.cardAccountShow}]</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
   					 <td>
        			<label class="control-label x130"><span class="red">*</span>是否允许转让：</label>
        			<input <c:if test="${obj.canTransfer==1}">checked</c:if> id="ransferable0" name="canTransfer" type="radio" value="1" data-toggle="icheck" data-label="是" data-rule="checked">
        			<input <c:if test="${obj.canTransfer==0}">checked</c:if> id="ransferable1" name="canTransfer" type="radio" value="0" data-toggle="icheck" data-label="否" data-rule="checked">
        			<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="投资者持有5个工作日后可在交易中心进行转让"></i>

   					 </td>


    				<td>
        				<!-- <p><b>本产品成立后，如允许持有者在交易中心平台进行转让，请设置以下信息：</b></p> -->
        				<label class="control-label x130" >转让流程期限：</label>
        					产品成立日后第 <input type="text" name="transferAfter" value="${obj.transferAfter }" id="transferAfter" size="4" data-rule="required(#transferLimit:filled)"> 天</span>
   					 </td>



				</tr>
			</tbody>	
			<thead>
		 		<tr><th colspan="2">费用相关</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>产品管理费：</label> 
						<input size="6" data-rule="required;validthreedecimal;addlt100" data-rule-addlt100="validFunction.addlt100" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="advioserFee" id="advioserFee" value="<fmt:formatNumber value="${obj.advioserFee*100}" pattern="0.000"/>" size="10">%<span class="text-muted"></span>
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="由产品管理人向发行人收取，计算公式：服务费=费率*确权金额"></i>
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>交易服务费：</label> 
						<input size="6" data-rule="required;validthreedecimal;addlt100" data-rule-addlt100="validFunction.addlt100" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="platformFee" id="platformFee" value="<fmt:formatNumber value="${obj.platformFee*100}" pattern="0.000"/>">%
						<select data-rule="required;" id="platformFeeType" name="platformFeeType" data-toggle="selectpicker">
	                    <c:forEach var="item" items="${platformFeeTypeList}" varStatus="status">
	                    	<option value="${item.type}" <c:if test="${item.type==obj.platformFeeType}">selected</c:if>>${item}</option>
						</c:forEach>
	                    </select>
	                    <i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="由交易中心向发行人收取，计算公式：服务费=费率*确权金额"></i>
	         
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x130"><span class="red">*</span>投资顾问费：</label> 
						<input size="6" data-rule="required;validthreedecimal;addlt100" data-rule-addlt100="validFunction.addlt100" data-rule-validthreedecimal="validFunction.validthreedecimal" type="text" name="investAdvioserFee" id="investAdvioserFee" value="<fmt:formatNumber value="${obj.investAdvioserFee*100}" pattern="0.000"/>" size="10"> %<span class="text-muted"></span>
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="由投资顾问向发行人收取，计算公式：服务费=费率*确权金额"></i>
					</td>
					<td>
						<label class="control-label x130"><span class="red">*</span>逾期罚息率：</label> 
						<input size="6" data-rule="required;validfourdecimal" data-rule-validfourdecimal="validFunction.validfourdecimal" type="text" name="overduePayFee" value="<fmt:formatNumber value="${obj.overduePayFee*100}" pattern="0.0000"/>" size="8"> %<span class="text-muted"></span>
						<i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content="指还款人未按期还款，需对未还本息罚息，支付给投资者"></i>
					</td>
				</tr>
			</tbody>
			<thead>
		 		<tr><th colspan="2">其他选项</th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td colspan="2">
						<label class="control-label x130" >重大事项披露：</label> 
						<textarea  name="projectInfo"  data-toggle="autoheight" style="width:60%;"><c:out value='${content.projectInfo}'/></textarea>
					</td> 
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130" >基础资产说明：</label> 
						<textarea  name="basicAssetNote"  data-toggle="autoheight" style="width:60%;"><c:out value='${content.basicAssetNote}'/></textarea>
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
			if($("#settleInvestMonth").val() != '' && $("#settleInvestDay").val() == '')
				return "日期不能为空";
			else if($("#settleInvestMonth").val() == '' && $("#settleInvestDay").val() != '')
				return "月份不能为空";
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
    	var addv = Number(parseFloat($("#advioserFee").val())+parseFloat($("#platformFee").val())+parseFloat($("#investAdvioserFee").val()));
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
    }
}

var projectLimitModifiedValidator = function(){};
projectLimitModifiedValidator.modified = ($(":hidden[name=orignExpireDate]").val()!=''
											&& $(":hidden[name=orignValueDate]").val()!=''
											&& $(":hidden[name=versionNo]").val()!=1);

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
	})
	if($("#payinvestname").val() != ""){
		$("#projectupate").attr("data-callback", "projectupateCallback").data('validator').options.ignore = '#payinvestname';
	}else{
		$("#projectupate").attr("data-callback", "projectupateCallback");
	}
	$("#projectupate").attr("data-callback", "projectupateCallback");
	if($("#outside").attr("checked")=="checked") {
		var agent = $('input[name="projectSaleagent[0].saleMemberName"]').val();
		if(agent == undefined || agent == "") {
			$(this).alertmsg("error", "场外交易需要指定投资顾问！");
			return false;
		}
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
	getUserBank($("#loanUserId").val());
}
$('#loader').on('afterchange.bjui.lookup', function(e, data) {
	$("#addBank").attr("href", "<%=request.getContextPath()%>/user/bankcard/list.do?userId="+data.loanUserId);
	getUserBank(data.loanUserId);
})
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
				array.push('<option value="'+bank.id+'" '+selected+'>'+bank.channelName+'['+bank.cardAccountShow+']</option>');
			}
			$('#receiveAccountId').html(array.join("")).selectpicker('refresh');
		}
	})
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
	/* 移除生成还款计划的选中状态 */
	$('input[name="generatePlan"]').iCheck('uncheck');
	
	
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
$('input[name="tradeType"]').on("ifChecked",function(){
	if($(this).val()==1) {
		
		$("#settleTypeId3").iCheck('disable').iCheck('uncheck');
		$("#settleTypeId1").iCheck('check');
	
	}else{
		$("#settleTypeId1").iCheck('enable');
		$("#settleTypeId3").iCheck('enable');
		
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
	$('input[name=generatePlan]').iCheck('check');
})
$('#manageDuration1').on("ifUnchecked", function(){
	$('#generatePlan1').iCheck('enable');
})
$("#ransferable1").on("ifChecked",function(){
	$("#transferAfter").attr("disabled",true);

})
$("#ransferable0").on("ifChecked",function(){
	$("#transferAfter").attr("disabled",false);

})
$("#settleTypeId1").on("ifChecked",function(){
	$("#receiveAccountId").removeAttr('novalidate');
	$("#receiveAccountTip").show();
});
$("#settleTypeId3").on("ifChecked",function(){
	$("#receiveAccountId").attr("novalidate", "true");
	$("#receiveAccountTip").hide();
});
function agentMoney(obj) {
	var targetName = $(obj).attr("name").replace("saleMemberName", "saleMoney");
	$('input[name="'+targetName+'"]').val($('input[name="projectMoney"]').val())
}
</script>

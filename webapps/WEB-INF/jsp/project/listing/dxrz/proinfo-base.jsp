<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
    <table class="table table-condensed table-noborder table-hover">
        <thead>
        </thead>
        <tbody>
        <tr>
            <td>
                <label class="control-label x150">业务类型：</label>
                <span>定向融资计划</span>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td>
                <label class="control-label x150">备案代码：</label>
                <span>${record.recordCode}</span>
            </td>
            <td>
				<label class="control-label x150">备案名称：</label> 
				<span>${record.recordFullName}</span>
			</td>
        </tr>
        <tr>
            <td colspan="2">
                <label class="control-label x150">备案核准规模：</label>
                <span><fmt:formatNumber value="${record.projectMoney}" pattern="#,#00.00"/> 元</span>
            </td>
        </tr>
        </tbody>
        <thead>
	 		<tr><th colspan="2">基本信息 </th></tr>
	 	</thead>
 		<tbody>
 			<tr>
	 			<td>
					<label class="control-label x150">挂牌代码：</label>
               		<span>${obj.projectCode}</span>
				</td>
				<td>
					<label class="control-label x150">挂牌名称：</label>
               		<span>${obj.projectFullName}</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x150">挂牌金额：</label> 
					<span><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/> 元</span>
				</td>
				<td>
					<label class="control-label x150">最低募集规模：</label> 
					<span><fmt:formatNumber value="${obj.projectAmountMin}" pattern="#,##0.00"/> 元</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x150">融资人：</label> 
					<span>${obj.loanUserName }</span>
				</td>
				<td>
					<label class="control-label x150">产品面值：</label> 
					<span><fmt:formatNumber value="${obj.projectUnitPrice}" pattern="#"/></span>
				</td>
			</tr>
 		</tbody>
       	<thead>
	 		<tr><th colspan="2">交易信息 </th></tr>
	 	</thead>
 		<tbody>
 			<tr>
				<td>
					<label class="control-label x150">承销方式：</label> 
                    <c:forEach var="saleType" items="${saleTypeIdList}"> 
                    	<c:if test="${saleType.type==saleagentList[0].saleTypeId}">${saleType}</c:if>
                    </c:forEach> 
				</td>
				<td>
					<label class="control-label x150">发布时间：</label> 
					<span><fmt:formatDate value="${obj.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x150">认购开始时间：</label> 
					<span><fmt:formatDate value="${obj.buyTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</td>
				<td>
					<label class="control-label x150">认购结束时间：</label> 
					<span><fmt:formatDate value="${obj.buyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x150">起投金额：</label> 
					<span><fmt:formatNumber value="${obj.investAmountMin}" pattern="#,##0.00"/> 元</span>
				</td>
				<td>
					<label class="control-label x150">交易方式：</label> 
					<span><c:if test="${obj.tradeType==1}">场内交易</c:if><c:if test="${obj.tradeType==2}">场外交易</c:if></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x150">是否允许转让：</label> 
					<span><c:if test="${obj.canTransfer==1}">是</c:if><c:if test="${obj.canTransfer==0}">否</c:if></span>
				</td>
			</tr>
			<c:if test="${obj.canTransfer==1}">
			<tr>
				<td colspan="2">
					<label class="control-label x150">转让流程期限：</label> 
					<span>产品成立日后第${obj.transferAfter }天开始，转让期限为${obj.transferLimit }天</span>
				</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<label class="control-label x150">合格投资者资格要求：</label> 
					<span>${content.tradePartyQualification}</span>
				</td>
			</tr>
 		</tbody>
        <thead>
	 		<tr><th colspan="2">结算信息 </th></tr>
	 	</thead>
	 	<tbody>
			<tr>
				<td>
					<label class="control-label x150">融资收款方：</label> 
					<span>${bankcard.accountName}&nbsp;<c:if test="${not empty bankcard && not empty bankcard.accountName }">(<c:choose><c:when
                        test="${not empty obj.receiveUserType && obj.receiveUserType == 2}">主承销商</c:when><c:otherwise>融资人</c:otherwise></c:choose>)</c:if></span>
				</td>
				<td>
	                <label class="control-label x150">收款账户：</label>
	                <span>${bankcard.channelName}<c:if test="${bankcard.cardAccountShow!=null}">[${bankcard.cardAccountShow}]</c:if></span>
	            </td>
			</tr>
			<tr>
                  <td>
                      <label class="control-label x150">预计起息日：</label>
                      <span><fmt:formatDate value="${obj.valueDate}" pattern="yyyy-MM-dd"/></span>
                  </td>
                  <td>
                      <label class="control-label x150">预计到期日：</label>
                      <span><fmt:formatDate value="${obj.expireDate}" pattern="yyyy-MM-dd"/></span>
                  </td>
              </tr>
              <tr>
                  <td>
                      <label class="control-label x150">产品期限：</label>
                      <c:if test="${(obj.expireDate!=null && obj.valueDate!=null)}">
	                    <span>${obj.projectLimit}
	                        <c:forEach var="item" items="${projectLimitTypeList}">
	                            <c:if test="${item.type==obj.projectLimitTypeId}">${item}</c:if>
	                        </c:forEach>
	                    </span>
	                  </c:if>
                  </td>
                  <td>
                      <label class="control-label x150">还款方式：</label>
                      <span>
                      	<c:forEach var="item" items="${systypeRepayList}" varStatus="status">
		                    <c:if test="${item.value==obj.repayTypeId}">${item}</c:if>
		                </c:forEach>
		               </span>
                  </td>
              </tr>
              <tr>
                  <td colspan="2">
                      <label class="control-label x150">预期年化收益率：</label>
                      <span><fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/> %</span>
                  </td>
              </tr>
              <tr>
                  <td>
                      <label class="control-label x150">计息方式：</label>
                      <c:forEach var="item" items="${interestModes}"> 
                      		<c:if test="${obj.interestMode==item.typeId}">${item.typeName }</c:if>
                   		</c:forEach>
                  </td>
                  <td>
                      <label class="control-label x150">计息基准天数：</label>
                      <span>
                      	<c:choose>
							<c:when test="${obj.interestBaseType==0}">${obj.interestBaseDays}</c:when>
							<c:when test="${obj.interestBaseType==1}">365/366</c:when>
							<c:otherwise>365</c:otherwise>
						</c:choose>
						&nbsp;天
                      </span>
                  </td>
              </tr>
              <tr>
                  <td>
                      <label class="control-label x150">还款日：</label>
                     	早于兑付日 ${obj.repayPeriodDay} 个工作日
                  </td>
                  <td>
                      <label class="control-label x150">兑付日：</label>
                      <span id="settleInvestDaySpan">
						<c:if test="${obj.repayTypeId==1 }">产品到期日的所属工作日</c:if>
						<c:if test="${obj.repayTypeId!=1 }">
                            <c:choose>
                                <c:when test="${obj.repayTypeId==5 }">产品设立日起每季末月</c:when>
                                <c:when test="${obj.repayTypeId==6 }">产品设立日起每年度 ${obj.getSettleInvestMonthShow()} 月 </c:when>
                                <c:otherwise>产品设立日起每月</c:otherwise>
                            </c:choose>
                            ${obj.getSettleInvestDayShow()} 日的所属工作日
                        </c:if>
						</span>
                  </td>
              </tr>
              <!-- <tr>
                   <td>
                       <label class="control-label x150">还款资金：</label>
                       <c:if test="${obj.manageDuration==0 }">不</c:if>经过交易中心
                   </td>
                   <td>
                       <label class="control-label x150">生成还款兑付计划：</label>
                   </td>
               </tr>
               <tr>
                   <td>
                       <label class="control-label x150">兑付收款方：</label>
                   </td>
                   <td>
                       <label class="control-label x150">兑付收款账户：</label>
                   </td>
               </tr> -->
	 	</tbody>
        <thead>
	 		<tr><th colspan="2">费用相关</th></tr>
	 	</thead>
	 	<tbody>
	 		<tr>
				<td>
					<label class="control-label x150">承销服务费：</label> 
					<span><fmt:formatNumber value="${obj.advioserFee}" type="PERCENT" maxFractionDigits="3" minFractionDigits="2"/></span>
				</td>
				<td>
					<label class="control-label x150">交易服务费：</label> 
					<span><fmt:formatNumber value="${obj.platformFee}" type="PERCENT" maxFractionDigits="3" minFractionDigits="2"/>/<c:forEach var="item" items="${platformFeeTypeList}" varStatus="status">
                    <c:if test="${item.type==obj.platformFeeType}">${item}</c:if>
                	</c:forEach></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x150">逾期罚息率：</label> 
					<span><fmt:formatNumber value="${obj.overduePayFee}" type="PERCENT" maxFractionDigits="4" minFractionDigits="4"/></span>
				</td>
			</tr>
		</tbody>
		<thead>
	 		<tr><th colspan="2">其他相关</th></tr>
	 	</thead>
	 	<tbody>
			<tr>
				<td colspan="2">
					<label class="control-label x150">重大事项披露：</label> 
					<span>${content.projectInfo}</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x150">本产品其他说明：</label> 
					<span>${content.basicAssetNote}</span>
				</td>
			</tr>
			<tr>
            <td>
                <label class="control-label x150">起息日变动规则：</label>
                <span>
						<c:if test="${obj.valueDateChangeStyle==1}">
                            募集结束日
                        </c:if>
						<c:if test="${obj.valueDateChangeStyle==2}">
                            放款日
                        </c:if>
						<c:if test="${obj.valueDateChangeStyle==3}">
                            产品录入
                        </c:if>
						<c:if test="${obj.valueDateChangeStyle==4}">
                            起息日待定
                        </c:if>
						</span>
            </td>
            <td>
                <label class="control-label x150">到期日变动规则：</label>
                <span>
						<c:if test="${obj.expireDateChangeStyle==1}">固定期限</c:if>
						<c:if test="${obj.expireDateChangeStyle==2}">固定到期日</c:if>
						</span>
            </td>
        </tr>
        <tr>
			        	<td <c:if test="${obj.payinvestType==1}">colspan="2"</c:if> >
			              		<label class="control-label x150">兑付收款方：</label>
			              		<span><c:choose><c:when test="${obj.payinvestType==1}">投资人</c:when><c:otherwise>中间方</c:otherwise></c:choose></span>
			            </td>
			            
			            <c:if test="${obj.payinvestType==2}">
			            	<td>
			                	<label class="control-label x150">兑付中间方：</label>
			                	<span>
			                		${payInvestName} ${payInvestBankcard.channelName}[${payInvestBankcard.cardAccountShow}]
			                	</span>
			            	</td>
			            </c:if>
            
        		</tr>
        <tr>
					<td>
						<label class="control-label x150">还款资金：</label> 
						<span><c:choose><c:when test="${obj.manageDuration==1}">经过交易中心</c:when><c:otherwise>不过交易中心</c:otherwise></c:choose></span>
					</td>
					<td>
						<label class="control-label x150">还款兑付计划：</label> 
						<span><c:choose><c:when test="${obj.generatePlan==1}">交易中心生成</c:when><c:otherwise>交易中心不生成</c:otherwise></c:choose></span>
					</td>
				</tr>
		</tbody>
    </table>
</div>

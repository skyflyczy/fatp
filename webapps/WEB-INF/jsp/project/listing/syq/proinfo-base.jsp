<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="bjui-pageContent">
	<table class="table table-condensed table-noborder table-hover">
		<thead>
		</thead>
		<tbody>
			<tr>
				<td><label class="control-label x130">业务类型：</label> <span>金融产品发行</span>
				</td>
				<td><label class="control-label x130">备案代码：</label> <span>${record.recordCode}</span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label x130">产品类型：</label> <span><c:forEach
							var="item" items="${systypeProjectList}" varStatus="status">
							<c:if test="${item.id==obj.projectTypeId}">${item.typeName}</c:if>
						</c:forEach></span></td>
				<td><label class="control-label x130">备案名称：</label> <span>${record.recordFullName}</span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label x130">发行人：</label> <span>${obj.loanUserName}</span>
				</td>
				<td><label class="control-label x130">备案核准规模：</label> <span><fmt:formatNumber
							value="${record.projectMoney}" pattern="#,#00.00" /> 元</span></td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="2">基本信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><label class="control-label x130">挂牌代码：</label> <span>${obj.projectCode}</span>
				</td>
				<td><label class="control-label x130">挂牌名称：</label> <span>${obj.projectFullName}</span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label x130">挂牌金额：</label><span><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/> 元</span>
				</td>
				<td><label class="control-label x130">最低募集规模:</label> <span><fmt:formatNumber
							value="${obj.projectAmountMin}" pattern="#,##0.00" /> 元</span></td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="2">交易信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><label class="control-label x130">起投金额：</label> <span><fmt:formatNumber
							value="${obj.investAmountMin}" pattern="#,##0.00" /> 元</span></td>
				<td><label class="control-label x130">追加金额：</label> <span><fmt:formatNumber
							value="${obj.investAmountAppend}" pattern="#,##0.00" /> 元</span></td>
			</tr>
			<tr>
				<td><label class="control-label x130">投资上限：</label> <span><fmt:formatNumber
							value="${obj.investAmountMax}" pattern="#,##0.00" /> 元</span></td>
				<td><label class="control-label x130">发布时间：</label> <span><fmt:formatDate
							value="${obj.publishTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
			</tr>
			<tr>
				<td><label class="control-label x130">认购开始时间：</label> <span><fmt:formatDate
							value="${obj.buyTimeStart}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
				<td><label class="control-label x130">认购结束时间：</label> <span><fmt:formatDate
							value="${obj.buyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
			</tr>
			<tr>
				<td><label class="control-label x130">年化收益率：</label> <span><fmt:formatNumber
							value="${obj.investProfit*100}" pattern="0.000" /> %</span></td>
				<td><label class="control-label x130">还款方式：</label> <span><c:forEach
							var="item" items="${systypeRepayList}" varStatus="status">
							<c:if test="${item.value==obj.repayTypeId}">${item}</c:if>
						</c:forEach></span></td>
			</tr>
			<c:if test="${saleagentList!=null && saleagentList.size()>0 }">
				<tr>
					<td colspan="2">
						<table
							class="table table-condensed table-hover other-table table-bordered">
							<thead>
								<tr>
									<th width="30%">指定投资顾问</th>
									<th width="20%">销售方式</th>
									<th width="20%">销售额度(元)</th>
									<th width="20%">投资顾问服务费率</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="agent" items="${saleagentList}"
									varStatus="status">
									<tr>
										<td>${agent.companyName }</td>
										<td><c:forEach var="saleType" items="${saleTypeIdList}">
												<c:if test="${agent.saleTypeId==saleType.type }">${saleType}</c:if>
											</c:forEach></td>
										<td><fmt:formatNumber value="${agent.saleMoney }"
												pattern="#,##0.00" /></td>
										<td><fmt:formatNumber value="${agent.saleFeeRate}"
												type="PERCENT" maxFractionDigits="3" minFractionDigits="2" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2"><label class="control-label x130">合格投资者要求：</label>
					<textarea class="solve-pre" data-toggle="autoheight" style="width:80%;" readonly="readonly">${content.tradePartyQualification}</textarea></td>
			</tr>
			<%-- <tr>
		 			<td colspan="2">
						<label class="control-label x130">发行平台：</label> 
						<input class="copyField" type="radio" <c:if test="${obj.tradeType==1 }">checked</c:if> disabled data-toggle="icheck" name="tradeType" value="1" id="" data-rule="checked" data-label="${exchange.simpleName }资产交易平台" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="copyField" type="radio" <c:if test="${obj.tradeType==2 }">checked</c:if> disabled data-toggle="icheck" name="tradeType" value="2" id="" data-rule="checked" data-label="其他" />
						<u>&nbsp;&nbsp;&nbsp;&nbsp;${obj.tradePlatform }&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</td>
		 		</tr> --%>
		</tbody>
		<thead>
			<tr>
				<th colspan="2">结算信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><label class="control-label x130">交易方式：</label> <span><c:if
							test="${1==obj.tradeType }">场内交易</c:if>
						<c:if test="${2==obj.tradeType }">场外交易</c:if>
						<c:if test="${3==obj.tradeType }">场内+场外交易</c:if></span></td>

				<td><label class="control-label x130">结算方式：</label> <span>${obj.settleName() }</span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label x130">融资收款方：</label> <span>发行人</span>
				</td>
				<td><label class="control-label x130">收款账户：</label> <span>${bankcard.channelName}<c:if
							test="${bankcard.cardAccountShow!=null}">[${bankcard.cardAccountShow}]</c:if></span>
				</td>
			</tr>
			<tr>
				<td><label class="control-label x130">预计起息日：</label> <span><fmt:formatDate
							value="${obj.valueDate}" pattern="yyyy-MM-dd" /></span></td>
				<td><label class="control-label x130">预计到期日：</label> <span><fmt:formatDate
							value="${obj.expireDate}" pattern="yyyy-MM-dd" /></span></td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label x130">产品期限：</label>
					<c:if test="${(obj.expireDate!=null && obj.valueDate!=null)}">
						<span>${obj.projectLimit} <c:forEach var="item"
								items="${projectLimitTypeList}">
								<c:if test="${item.type==obj.projectLimitTypeId}">${item}</c:if>
							</c:forEach>
						</span>
					</c:if></td>
			</tr>
			<tr>
				<td><label class="control-label x130">兑付日：</label> <span
					id="settleInvestDaySpan"> <c:if
							test="${obj.repayTypeId==1 }">产品到期日的所属工作日</c:if> <c:if
							test="${obj.repayTypeId!=1 }">
							<c:choose>
								<c:when test="${obj.repayTypeId==5 }">产品设立日起每季末月</c:when>
								<c:when test="${obj.repayTypeId==6 }">产品设立日起每年度 ${obj.getSettleInvestMonthShow()} 月 </c:when>
								<c:otherwise>产品设立日起每月</c:otherwise>
							</c:choose>
						${obj.getSettleInvestDayShow()} 日的所属工作日
						</c:if>
				</span></td>
				<td><label class="control-label x130">还款日：</label> 早于兑付日
					${obj.repayPeriodDay} 个工作日</td>

			</tr>
			<tr>
				<td><label class="control-label x130">还款资金：</label> <span><c:choose>
							<c:when test="${obj.manageDuration==1}">经过交易中心</c:when>
							<c:otherwise>不过交易中心</c:otherwise>
						</c:choose></span></td>
				<td><label class="control-label x130">生成还款计划：</label> <span><c:choose>
							<c:when test="${obj.generatePlan==1}">是</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose></span></td>

			</tr>
			<tr>
				<td <c:if test="${obj.payinvestType==1}">colspan="2"</c:if>><label
					class="control-label x130">兑付收款方：</label> <span><c:choose>
							<c:when test="${obj.payinvestType==1}">投资人</c:when>
							<c:otherwise>中间方</c:otherwise>
						</c:choose></span></td>
			<c:if test="${obj.payinvestType==2}">
				<td><label class="control-label x130">兑付收款账户：</label> <span>
						${payInvestBankcard.accountName}
						${payInvestBankcard.channelName}[${payInvestBankcard.cardAccountShow}]
				</span></td>
			</c:if>
			</tr>
			<tr>
				<td><label class="control-label x130">是否允许转让：</label> <span><c:if test="${obj.canTransfer==1 }">是</c:if><c:if test="${obj.canTransfer==0 }">否</c:if></span>
				</td>
				<td>
				<c:if test="${obj.canTransfer==1 }">
					<label class="control-label x130">转让流程期限：</label><span>产品成立日后第${obj.transferAfter }天开始</span>
				</c:if>
				</td>
			</tr>
		</tbody>
		<thead>
			<tr>
				<th colspan="2">费用相关</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><label class="control-label x130">产品管理费：</label> <span><fmt:formatNumber
							value="${obj.advioserFee}" type="PERCENT" maxFractionDigits="3"
							minFractionDigits="2" /></span>/次（由产品管理人收取，按次计算）</td>
				<td><label class="control-label x130">交易服务费：</label> <span><fmt:formatNumber
							value="${obj.platformFee}" type="PERCENT" maxFractionDigits="3"
							minFractionDigits="2" /></span>/<c:forEach var="item"
						items="${platformFeeTypeList}" varStatus="status">
						<c:if test="${item.type==obj.platformFeeType}">${item}</c:if>
					</c:forEach>（由交易中心平台收取）</td>


			</tr>
			<tr>
				<td><label class="control-label x130">投资顾问费：</label> <span><fmt:formatNumber
							value="${obj.investAdvioserFee}" type="PERCENT"
							maxFractionDigits="3" minFractionDigits="2" /></span>/次（由投资顾问收取，按次计算）</td>
				<td><label class="control-label x130">逾期罚息率：</label> <span><fmt:formatNumber
							value="${obj.overduePayFee}" type="PERCENT" maxFractionDigits="4"
							minFractionDigits="4" /></span>/天（由投资人收取，按天数计算）</td>
			</tr>


		</tbody>
		<thead>
			<tr>
				<th colspan="2">其他选项</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="2"><label class="control-label x130">重大事项披露：</label>
					<textarea class="solve-pre" data-toggle="autoheight" style="width:80%;" readonly="readonly">${content.projectInfo}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label x130">基础资产说明：</label>
					<textarea class="solve-pre" data-toggle="autoheight" style="width:80%;" readonly="readonly">${content.basicAssetNote}</textarea></td>
			</tr>
		</tbody>
	</table>
</div>

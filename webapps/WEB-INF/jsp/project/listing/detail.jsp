<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
	 <table class="table table-condensed table-noborder table-hover">
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
					<label class="control-label x130">挂牌名称：</label>
					<span>${obj.listingFullName }</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x130">挂牌金额：</label> 
					<span><fmt:formatNumber value="${obj.listingMoney }" pattern="#,##0.00"/> 元</span>
				</td>
				<td>
					<label class="control-label x130">起投金额：</label> 
					<span><fmt:formatNumber value="${obj.minInvestMoney}" pattern="#,##0.00"/> 元</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x130">发行人：</label> 
					<span>${obj.issuer }</span>
				</td>
				<td>
					<label class="control-label x130">管理人：</label> 
					<span>${obj.listingManager }</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x130">投资顾问：</label> 
					<span>${obj.investManager }</span>
				</td>
				<td>
					<label class="control-label x130">发行渠道：</label> 
					<span>${obj.partnerBiz }</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x115">发行渠道产品代码：</label> 
					<span>${obj.partnerBizCode }</span>
				</td>
			</tr>
	 	</tbody>
		<thead>
	 		<tr><th colspan="2">结算信息</th></tr>
	 	</thead>
	 	<tbody>	
	 			<tr>
		 			<td>
		 				<label class="control-label x130">结算账户：</label>
		 				<span>${obj.settleAccountName}</span>
		 			</td>
		 			<td>
		 				<label class="control-label x130">结算账号：</label>
		 				<span>${obj.getSettleCardAccountDesc()}</span>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td colspan="2">
		 				<label class="control-label x130">结算开户行：</label>
		 				<span>${obj.settleSubBankName}</span>
		 			</td>
		 		</tr>
	 			<tr>
   					<td colspan="2">
       					<label class="control-label x130">收益率类型：</label>
					    <span>${obj.getInvestProfitTypeDesc() }</span>
   					</td>
				</tr>
				<tr>
					<td colspan="2">
						<p><b>预期收益率：</b></p>
						<table id="investProfitTE" data-noread="true" data-reload-navtab="true" class="table other-table table-bordered"  data-toggle="tabledit" data-initnum="0" data-action="" data-single-noindex="true">
		            		<thead>
			                	<tr>
				                    <th title="最低投资金额(元)" width="30%">
				                    </th>
				                    <th title="最高投资金额(元)" width="30%">
				                    </th>
				                    <th title="指定预期收益率(%)" width="20%">
			                	</tr>
		            		</thead>
					        <tbody>
					        	<c:forEach var="listingTrade" items="${listingTradeList}" varStatus="status"> 
					        	<tr >
					        	
						        	<td>${listingTrade.minInvestMoney}</td>
						        	<td>${listingTrade.maxInvestMoney }</td>
						        	<td>${listingTrade.investProfit*100  }</td>
					        	</tr>
					        	</c:forEach>
					        </tbody>
	        			</table>
					</td>
				</tr>
				<tr>
   					<td>
       					<label class="control-label x130">起息日：</label>
       					<fmt:formatDate value="${obj.valueDate}" pattern='yyyy-MM-dd'/>
						
      					</td>
   					<td>
       					<label class="control-label x130">预计到期日：</label>
       					<fmt:formatDate value="${obj.expireDate}" pattern='yyyy-MM-dd'/>
						
       				</td>
				</tr>
				<tr>
					<td colspan="2">
       					<label class="control-label x130">产品期限：</label>
						<span>${obj.listingLimit} ${obj.getListingLimitTypeDesc() }</span>
   					</td>
				</tr>
				<tr>
   					<td>
       					<label class="control-label x130">到期日规则：</label>
						<span>${obj.getExpireDateStyleDesc() }</span>
   					</td>
   					<td>
	       				<label class="control-label x130">到期日是否计息：</label>
						<span>${obj.expireDateInterest==1?'是':'否' }</span>
   					</td>
				</tr>
				<tr>
					<td>
                        <label class="control-label x130">付息方式：</label>
                        <span>${obj.getPayInterestTypeDesc() }</span>									
                    </td>			                    
					<td>
                        <label class="control-label x130">计息类型：</label>
						<span>${obj.getInterestTypeDesc() }</span>

                    </td>
				</tr>
				<tr>
					 <td>
                        <label class="control-label x130">计息频率：</label>
						<span>${obj.getInterestRateDesc() }</span>
					
                    </td>
					<td>
                        <label class="control-label x130">计息基准：</label>
						<span>${obj.getInterestBaseDesc() }</span>
                    </td>
				</tr>
		</tbody>	
	 </table>
</div>
<div class="bjui-pageFooter">
	<button type="button" class="btn-close pull-right" data-icon="close">关闭</button>	
</div>
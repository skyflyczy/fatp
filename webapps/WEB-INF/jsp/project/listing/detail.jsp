<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
      <!-- Tab panes -->
      <div class="tab-content">
		<div class="bjui-pageContent">
			<form id="projectupate" data-loadingmask="true"  data-toggle="validate" data-reload="true">
				 <table class="table table-condensed table-noborder table-hover">
				 	<thead>
				 	</thead>
				 	<thead>
				 		<tr><th colspan="3">基本信息</th></tr>
				 	</thead>
				 	<tbody>
				 		<tr>
				 			<td>
								<label class="control-label x110">挂牌代码：</label> 
								<span>${obj.listingCode }</span>
							</td>
							<td>
								<label class="control-label x110"><span class="red"></span>挂牌名称：</label>
								<span>${obj.listingFullName }</span>
							</td>
							<td>
								<label class="control-label x110"><span class="red"></span>挂牌金额：</label> 
								<span>${obj.listingMoney }</span>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label x110"><span class="red"></span>发行人：</label> 
								<span>${obj.issuer }</span>
							</td>
							<td>
								<label class="control-label x110"><span class="red"></span>管理人：</label> 
								<span>${obj.listingManager }</span>
							</td>
							<td>
								<label class="control-label x110"><span class="red"></span>投资顾问：</label> 
								<span>${obj.investManager }</span>
							</td>
						</tr>
						<tr>
							<td>
								<label class="control-label x110"><span class="red"></span>交易所：</label> 
								<span>${obj.exchangeId }</span>
							</td>
							<td>
								<label class="control-label x110"><span class="red"></span>发行渠道：</label> 
								<span>${obj.partnerBiz }</span>
							</td>
							<td>
								<label class="control-label x115"><span class="red"></span>发行渠道产品代码：</label> 
								<span>${obj.partnerBizCode }</span>
							</td>
						</tr>
				 	</tbody>
					<thead>
				 		<tr><th colspan="3">结算信息</th></tr>
				 	</thead>
				 	<tbody>	
				 			<tr>
		    					<td colspan="3">
		        					<label class="control-label x110"><span class="red"></span>收益率类型：</label>
								    <span>${obj.getInvestProfitTypeDesc() }</span>
		    					</td>
							</tr>
							<tr>
								<td colspan="3">
									<p><span class="red"></span><b>预期收益率：</b></p>
									<table id="investProfitTE" data-noread="true" data-reload-navtab="true" class="table other-table table-bordered"  data-toggle="tabledit" data-initnum="0" data-action="" data-single-noindex="true">
					            		<thead>
						                	<tr>
							                    <th title="最低投资金额(元)" width="30%">
							                    </th>
							                    <th title="最高投资金额(元)" width="30%">
							                    </th>
							                    <th title="指定预期收益率(%)" width="20%">
						                	</tr>${obj.listingTradeList}
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
		        					<label class="control-label x110"><span class="red"></span>产品期限：</label>
									<span>${(obj.expireDate==null || obj.valueDate==null)?'':obj.listingLimit}</span>
									<span>${obj.getListingLimitTypeDesc() }</span>
		    					</td>
		    					<td>
		        					<label class="control-label x110"><span class="red"></span>起息日：</label>
		        					<fmt:formatDate value="${obj.valueDate}" pattern='yyyy-MM-dd'/>
									
		       					</td>
		    					<td>
		        					<label class="control-label x110"><span class="red"></span>预计到期日：</label>
		        					<fmt:formatDate value="${obj.expireDate}" pattern='yyyy-MM-dd'/>
									
		        				</td>
							</tr>
							<tr>
		    					<td>
		        					<label class="control-label x110"><span class="red"></span>到期日规则：</label>
								<span>${obj.expireDateStyle==1?'固定期限':'固定到期日' }</span>
		        					
		    					</td>
		    					<td>
		        					<label class="control-label x110"><span class="red"></span>到期日是否计息：</label>
								<span>${obj.expireDateInterest==1?'是':'否' }</span>
		    					</td>
			                    <td>
			                        <label class="control-label x110"><span class="red"></span>付息方式：</label>
			                        <span>${obj.getPayInterestTypeDesc() }</span>									
			                    </td>
							</tr>
							<tr>			                    
								<td>
			                        <label class="control-label x110"><span class="red"></span>计息类型：</label>
									<span>${obj.interestType==1?'单利':'复利'}</span>

			                    </td>
			                    <td>
			                        <label class="control-label x110"><span class="red"></span>计息频率：</label>
									<span>${obj.getInterestRateDesc() }</span>
								
			                    </td>
								<td>
			                        <label class="control-label x110"><span class="red"></span>计息基准：</label>
									<span>${obj.getInterestBaseDesc() }</span>
			                    </td>
							</tr>
							<tr>			                    
								<td>
			                        <label class="control-label x110"><span class="red"></span>挂牌状态：</label>
									<span>${obj.listingStatus==1?'正常':'删除'}</span>
			                    </td>								<td>
			                        <label class="control-label x110"><span class="red"></span>创建时间：</label>
		        					<fmt:formatDate value="${obj.createTime}" pattern='yyyy-MM-dd HH:ss'/>
			                    </td>								<td>
			                        <label class="control-label x110"><span class="red"></span>更新时间：</label>
		        					<fmt:formatDate value="${obj.updateTime}" pattern='yyyy-MM-dd HH:ss'/>
			                    </td>
			                </tr>
					</tbody>	
				 </table>
				 
			</form>
		</div>
</div>

<div class="bjui-pageFooter">
	<button type="button" class="btn-close pull-right" data-icon="close">关闭</button>	
</div>
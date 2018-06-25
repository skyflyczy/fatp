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
								    <c:set var="investProfitType" value="${obj.investProfitType}" scope="request" ></c:set>								
								<%							
									int inp=(int)pageContext.findAttribute("investProfitType");
									switch(inp){
									  case 1:
									    out.println("固定收益率");
									    break;
									  case 2:
										    out.println("阶梯收益率");
										    break;
									  case 3:
										    out.println("浮动收益率");
										    break;								
									  default:
									    out.println("-");
									}
									%>
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
									<c:set var="listingLimitType" value="${obj.listingLimitType}" scope="request" ></c:set>								
								<%							
									int lim=(int)pageContext.findAttribute("listingLimitType");
									switch(lim){
									  case 1:
									    out.println("天");
									    break;
									  case 2:
										    out.println("月");
										    break;
									  case 3:
										    out.println("年");
										    break;								
									  default:
									    out.println("-");
									}
									%>
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
									<c:set var="payInterestType" value="${obj.payInterestType}" scope="request" ></c:set>								
								<%							
									int pay=(int)pageContext.findAttribute("payInterestType");
									switch(pay){
									  case 1:
									    out.println("一次性还本付息");
									    break;
									  case 2:
										    out.println("等额本息");
										    break;
									  case 3:
										    out.println("按月付息到期还本");
										    break;
									  case 4:
										    out.println("按季付息到期还本");
										    break;
									  case 5:
										    out.println("按半年息到期还本");
										    break;									
									  default:
									    out.println("按年付息到期还本");
									}
									%>
			                    </td>
							</tr>
							<tr>			                    
								<td>
			                        <label class="control-label x110"><span class="red"></span>计息类型：</label>
									<span>${obj.interestType==1?'单利':'复利'}</span>

			                    </td>
			                    <td>
			                        <label class="control-label x110"><span class="red"></span>计息频率：</label>
								<c:set var="interestRate" value="${obj.interestRate}" scope="request" ></c:set>
								
								<%							
									int t=(int)pageContext.findAttribute("interestRate");
									switch(t){
									  case 1:
									    out.println("按日计息");
									    break;
									  case 2:
										    out.println("按月计息");
										    break;
									  case 3:
										    out.println("按年计息");
										    break;
									  case 4:
										    out.println("按季计息");
										    break;
									  case 5:
										    out.println("按半年计息");
										    break;									
									  default:
									    out.println("其它");
									}
									%>
								
			                    </td>
								<td>
			                        <label class="control-label x110"><span class="red"></span>计息基准：</label>
								
								<c:set var="interestBase" value="${obj.interestBase}" scope="request" ></c:set>	
								<%							
									int b=(int)pageContext.findAttribute("interestBase");
									switch(b){
									  case 1:
									    out.println("ACT/365");
									    break;
									  case 2:
										    out.println("ACT/360");
										    break;
									  case 3:
										    out.println("ACT/ACT");
										    break;								
									  default:
									    out.println("其它");
									}
									%>								
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
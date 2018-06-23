<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function afterRepayCompleted(json){
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message);
		$(this).dialog("refresh");
	}else{
		$(this).alertmsg("error", json.message);
	}
}
</script>
<div class="bjui-pageHeader"  id="bidlist-payoffline-cfm-header">
<table class="table table-condensed table-hover" width="100%">
        <tbody>
            <tr>
                <td>
                    <label class="control-label x120">挂牌名称：</label>
                    <span>${listingInfo.listingFullName}</span>
                </td>
                <td colspan="2">
                    <label class="control-label x120">挂牌规模：</label>
                    <span><fmt:formatNumber value="${listingInfo.listingMoney}" pattern="0.00" maxFractionDigits="2"/> 元</span>
                </td>
                 
            </tr>
            <tr>
            	<td>
                    <label class="control-label x120">付息方式：</label>
                    <span>${listingInfo.getPayInterestTypeDesc() }</span>
                </td>
                <td>
                    <label class="control-label x120">计息频率：</label>
                    <span>${listingInfo.getInterestRateDesc() }</span>
                </td>
                <td >
                    <label class="control-label x120">计息基准：</label>
                    <span>${listingInfo.getInterestBaseDesc() } 个</span>
                </td>
            </tr>
   	</tbody>
  </table>
</div>
<div class="bjui-pageContent" id="bidlist-payoffline-cfm-list">
 <table data-toggle="tablefixed" data-width="100%" data-height="300">
    	<thead>
    	<tr>
    		<th align="center" width="15%">期数</th>
    		<th align="center" width="20%">还款本金</th>
    		<th align="center" width="20%">还款利息</th>
    		<th align="center" width="15%">还款总额</th>
    		<th align="center" width="15%">应还日期</th>
    		<th align="center" width="15%">还款状态</th>
    		<th align="center" width="15%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    	  <c:if test="${not empty list}">
    	  	<c:forEach items="${list}" var="obj">
    		<tr>
    			<td align="center">${obj.periodNumber}</td>
    			<td align="center"><fmt:formatNumber value="${obj.principal}" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td align="center"><fmt:formatNumber value="${obj.interest}" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td align="center"><fmt:formatNumber value="${obj.principal + obj.interest }" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td align="center"><fmt:formatDate value="${obj.planRepayDate}" pattern="yyyy-MM-dd"/></td>
    			<td align="center">${obj.getRepayStatusDesc()}</td>
    			<td align="center">
    			<c:if test="${obj.canRepayCompleted()}">
    				<a href="/biz/plan/repay_completed.do?id=${obj.repayPlanGuid}" 
	    					class="btn btn-blue" data-toggle="doajax" data-callback="afterRepayCompleted()" data-confirm-msg="确定还款完成吗？" data-mask="true" >还款完成</a>
	    		</c:if>
	    			<a href="/biz/plan/payinvest_plan_list.do?id=${obj.repayPlanGuid}" 
	    					class="btn btn-blue" data-toggle="dialog" data-width="1000"  
	    					data-height="500" data-id="dialog-trade-details" data-mask="true" >兑付计划</a>
				</td>
    		</tr>
    		</c:forEach>
    	  </c:if>
    	</tbody>
    </table> 
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>


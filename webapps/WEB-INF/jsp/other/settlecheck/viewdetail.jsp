<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 对账明细 -->
<div class="bjui-pageHeader" id="prolist-issue-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
        <input type="hidden" name="payCheckId" value="${search.payCheckId}"/>
        <div class="bjui-searchBar">
			<label>业务类型：</label>
			<select name="bizPayTypeId" id="select1" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${bizPayTypeDesc}">
					<option value="${item.type}" <c:if test="${search.bizPayTypeId==item.type}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>
			<label>对账状态：</label>
			<select name="checkStatus" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${checkStatusDesc}">
					<option value="${item.status}" <c:if test="${search.checkStatus==item.status}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>
			<label>对账日期：</label>
			<input type="text" name="checkDate" value="${search.checkDate }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
			<button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent" id="prolist-issue-list">
 <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">业务类型</th>
	    		<th align="center" width="9%">对账日期</th>
	    		<th align="center" width="15%">支付流水</th>
	    		<th align="right" width="11%">交易金额(元)</th>
	    		<th align="right" width="12%">商户应收金额(元)</th>
	    		<th align="right" width="14%">支付渠道应收金额(元)</th>
	    		<th align="right" width="12%">业务支付金额(元)</th>
	    		<th align="center" width="10%">业务支付状态</th>
	    		<th align="center" width="7%">对账状态</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:if test="${not empty list}">
    			<c:forEach items="${list}" var="obj">
    		<tr align="center">
    			<td>
    				${obj.getBizPayTypeDesc()}
    			</td>
    			<td><fmt:formatDate value="${obj.checkDate}" pattern="yyyy-MM-dd"/></td>
    			<td>${obj.paymentNo}</td>
    			<td align="right"><fmt:formatNumber value="${obj.payTxAmount}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td align="right"><fmt:formatNumber value="${obj.payUserAmount}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td align="right"><fmt:formatNumber value="${obj.payChannelAmount}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td align="right"><fmt:formatNumber value="${obj.bizPayMoney}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td>
    				<c:forEach items="${payStatusDesc}" var="item">
    					<c:if test="${obj.bizPayStatus == item.status }">${item}</c:if>
    				</c:forEach>
    			</td>
    			<td>
    				<c:forEach items="${checkStatusDesc}" var="item">
    					<c:if test="${obj.checkStatus == item.status }">${item}</c:if>
    				</c:forEach>
    			</td>
    		</tr>
    			</c:forEach>
    		</c:if>
    		
    	</tbody>
    </table> 
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

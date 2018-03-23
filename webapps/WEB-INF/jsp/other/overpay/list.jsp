<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 订单超额支付 -->
<div class="bjui-pageHeader" id="prolist-issue-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
        <div class="bjui-searchBar">
			<label>订单id：</label>
			<input type="text" name="bizOrderId" value="<c:out value='${search.bizOrderId}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>业务类型：</label>
			<select name="orderTypeId" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${orderTypeDesc}">
					<option value="${item.type}" <c:if test="${search.orderTypeId==item.type}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>
			<label>统计日期：</label>
			<input type="text" name="statDate" value="${search.statDate }" class="form-control" size="15" data-toggle="datepicker"
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
	    		<th align="center" width="10%">订单id</th>
	    		<th align="center" width="14%">业务类型</th>
	    		<th align="right" width="20%">订单金额(元)</th>
	    		<th align="right" width="20%">实付金额(元)</th>
	    		<th align="right" width="20%">应退金额(元)</th>
	    		<th align="center" width="16%">统计日期</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:if test="${not empty list}"><c:forEach items="${list}" var="obj">
    		<tr align="center">
    			<td>${obj.bizOrderId}</td>
    			<td>
    				<c:forEach items="${orderTypeDesc}" var="item">
    					<c:if test="${obj.orderTypeId == item.type }">${item}</c:if>
    				</c:forEach>		
    			</td>
    			<td align="right"><fmt:formatNumber value="${obj.orderMoney}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td align="right"><fmt:formatNumber value="${obj.paidMoney}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td align="right"><fmt:formatNumber value="${obj.paidMoney.subtract(obj.orderMoney)}"  pattern="#,##0.00" maxFractionDigits="2"/></td>
    			<td>
    				<fmt:formatDate value="${obj.statDate}" pattern="yyyy-MM-dd"/>
    			</td>
    		</tr>
    		</c:forEach></c:if>
    		
    	</tbody>
    </table> 
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

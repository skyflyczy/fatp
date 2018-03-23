<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 审核记录 -->
<div class="bjui-pageHeader" style="display:none;">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/transfer/audit-history.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <input type="hidden" name="applyId" value="${search.applyId}">
    </form>
</div>
<div class="bjui-pageContent">
    <table class="table table-condensed table-hover">
    	<thead>
	    	<tr>
	    		<th align="center" width="20%">审核时间</th>
	    		<th align="center" width="20%">审核人</th>
	    		<th width="40%">审核意见</th>
	    		<th align="center" width="10%">审核动态</th>
	    		<th align="center" width="10%">产品状态变更</th>
	    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list}">
    	<tr align="center">
    		<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    		<td>${obj.createOperatorName}</td>
    		<td align="left">${obj.flowFeedOpinion}</td>
    		<td>${obj.flowFeedText}</td>
    		<td>${obj.flowFeedDesc}</td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader" style="display:none;">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/feed.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <input type="hidden" name="userId" value="${userId}">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-condensed table-hover">
    	<thead>
    	<tr>
    		<th width="25%">操作时间</th>
    		<th width="15%">操作人</th>
    		<th width="15%">操作动态</th>
    		<th width="30%">操作意见</th>
    		<th width="15%">状态变更</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list}">
    	<tr>
    		<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    		<td>${obj.createOperatorName}</td>
    		<td>${obj.flowFeedText}</td>
    		<td>${obj.flowFeedOpinion}</td>
    		<td><c:forEach var="item" items="${UserFlowFeedTypeDesc}"><c:if test="${item.value==obj.flowFeedType}">${item}</c:if></c:forEach></td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

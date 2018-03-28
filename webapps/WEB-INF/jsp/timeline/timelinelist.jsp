<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent tableContent">
    <table class="table table-condensed table-hover">
    	<thead>
	    	<tr>
	    		<th align="center" width="20%">操作时间</th>
	    		<th align="center" width="15%">操作人</th>
	    		<th width="35%">操作意见</th>
	    		<th align="center" width="15%">操作动态</th>
	    		<th align="center" width="15%">状态变更</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}">
	    	<tr align="center">
	    		<td><fmt:formatDate value="${obj.eventTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>${obj.eventOperatorName}</td>
	    		<td align="left">${obj.eventRemark}</td>
	    		<td>${obj.eventName}</td>
	    		<td>${obj.statusChangeDesc}</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>

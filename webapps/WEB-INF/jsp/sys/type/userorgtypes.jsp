<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 用户组织类型 -->
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">序号</th>
	    		<th width="90%">用户组织类型</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}" varStatus="statu">
	    	<tr>
	    		<td align="center">${statu.index +1}</td>
	    		<td>${list.orgTypeName}</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
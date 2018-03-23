<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 行业分类列表 -->
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">行业id</th>
	    		<th width="90%">行业名称</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}">
	    	<tr>
	    		<td align="center">${list.id}</td>
	    		<td>${list.industryName}</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>

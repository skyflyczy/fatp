<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 证件类型 -->
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th>编号</th>
	    		<th>名称</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<tr>
	    		<td>1</td>
	    		<td>身份证</td>
	    	</tr>
	    	<tr>
	    		<td>22</td>
	    		<td>组织机构代码</td>
	    	</tr>
	    	<c:forEach var="list" items="${list}">
	    	<tr>
	    		<td>${list.projectid}</td>
	    		<td>${list.projectname}</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
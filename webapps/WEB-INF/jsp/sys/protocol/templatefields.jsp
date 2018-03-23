<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent tableContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">序号</th>
	            <th width="40%">名称</th>
	            <th width="50%">含义</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="obj" items="${list}" varStatus="status"> 
                <tr>
                    <td align="center">${status.index+1}</td>
                    <td>${obj.getKey() }</td>
                    <td>
                    	${obj.getDescription()}
                    </td>
                </tr>
            </c:forEach>
    	</tbody>
    </table>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 其他附件 -->
<div class="bjui-pageContent tableContent">
        <table class="table table-hover">
        	<thead>
	        	<tr>
		    		<th width="22%">账户名称</th>
		    		<th align="center" width="20%">账户号码</th>
		    		<th align="center" width="12%">开户行</th>
		    		<th align="center" width="16%">开户行所在地</th>
		    		<th width="20%">分支行</th>
		    		<th align="center" width="10%">默认收款账户</th>
	    		</tr>
            </thead>
            <tbody>
               	<c:forEach var="obj" items="${cardList}">
		    	<tr align="center">
		    		<td align="left">${obj.accountName}</td>
                    <td>${obj.cardAccount}</td>
                    <td>${obj.channelName}</td>
                    <td>${obj.provinceName}${obj.cityName}</td>
                    <td align="left">${obj.subBankName}</td>
                    <td><c:if test="${obj.defaultCard==1}">是</c:if><c:if test="${obj.defaultCard==0}">否</c:if></td>
		    	</tr>
				</c:forEach>
            </tbody>
        </table>
</div>

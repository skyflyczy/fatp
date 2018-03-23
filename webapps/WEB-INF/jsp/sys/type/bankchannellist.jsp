<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 工作流列表 -->
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">编号</th>
	    		<th align="center" width="10%">银行id</th>
	    		<th align="center" width="10%">支付通道银行id</th>
	    		<th align="center" width="30%">通道银行名称</th>
	    		<th align="center" width="20%">通道名称</th>
	    		<th align="center" width="20%">状态</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}" varStatus="statu">
	    	<tr align="center">
	    		<td>${list.id}</td>
	    		<td>${list.bankId}</td>
	    		<td>${list.thirdBankId}</td>
	    		<td>${list.bankChannelName}</td>
	    		<td>${list.payCenterId}</td>
	    		<td>${list.channelStatus}</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>

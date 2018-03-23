<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
        <thead>
            <tr>
                <th>编号</th>
                <th>银行名称</th>
                <th>银行全称</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="list" items="${list}" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.bankName}</td>
                <td>${list.bankFullname}</td>
                <td>	
               		<a href="javascript:;" data-toggle="lookupback" data-args="{transferBankName:'${list.bankName}'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


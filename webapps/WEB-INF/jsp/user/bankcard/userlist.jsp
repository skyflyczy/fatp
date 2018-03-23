<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资者/个人、企业 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/bankcard/userlist.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/user/custom/search_itembase.jsp" %>
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
        <div class="bjui-moreSearch">
			<%@ include file="/WEB-INF/jsp/user/custom/search_itemext.jsp" %>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center">客户账号</th>
	    		<th align="center">客户简称</th>
	    		<th align="center">所属会员</th>
	    		<th align="center">注册日期</th>
	    		<th align="center">客户类型</th>
	    		<th align="center">账户状态</th>
	    		<th align="center">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}">
	    	<tr>
	    		<td>${obj.userCode}</td>
	    		<td>${obj.userName}</td>
	    		<td>${obj.ownerUserName}</td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td><c:forEach var="item" items="${OrgType}"><c:if test="${item.type==obj.orgTypeId}">${item}</c:if></c:forEach></td>
	    		<td><c:forEach var="item" items="${DelStatusDesc}"><c:if test="${item.value==obj.userStatus}"><c:choose><c:when test="${item.value==3 }"><c:if test="${obj.auditStatus==1 }">待提交</c:if><c:if test="${obj.auditStatus==2 }">待审核</c:if></c:when><c:otherwise>${item}</c:otherwise></c:choose></c:if></c:forEach></td>
	    		<td>
	    		<a href="<%=request.getContextPath()%>/user/view.do?id=${obj.id}"  data-width="1000"
	    			data-height="600"	data-toggle="dialog">客户详情</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资者/个人、企业 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/custom/checklist.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
            <label>客户账号：</label>
			<input type="text" name="userCode" value="<c:out value='${search.userCode}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>客户简称：</label>
			<input type="text" name="userName" value="<c:out value='${search.userName}'/>" class="form-control" size="15" />
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
	    		<th align="center" width="8%">客户账号</th>
	    		<th width="14%">客户简称</th>
	    		<th width="20%">所属会员</th>
	    		<th width="22%">客户全称</th>
	    		<th align="center" width="14%">注册日期</th>
	    		<th align="center" width="10%">客户类型</th>
	    		<th align="center" width="6%">状态</th>
	    		<th align="center" width="8%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}">
	    	<tr align="center">
	    		<td>${obj.userCode}</td>
	    		<td align="left">${obj.userName}</td>
	    		<td align="left">${obj.ownerUserName}</td>
	    		<td align="left">${obj.realName}</td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td><c:forEach var="item" items="${OrgType}"><c:if test="${item.type==obj.orgTypeId}">${item}</c:if></c:forEach></td>
	    		<td><c:forEach var="item" items="${DelStatusDesc}"><c:if test="${item.value==obj.userStatus}">${item}</c:if></c:forEach></td>
	    		<td>
	    			<a href="<%=request.getContextPath()%>/user/checkview.do?id=${obj.id}" 
	    				class="btn btn-blue" data-toggle="dialog" data-width="1000"  
	    				data-height="800" data-id="dialog-mask" data-mask="true">审核</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
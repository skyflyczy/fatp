<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.huajin.ptoms.domain.UcUser, com.huajin.ptoms.util.StatusUtil" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 业务资格审核 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/member/biztypeapply/list.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
        	&nbsp;&nbsp;&nbsp;&nbsp;
	        <select name="applyType" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="0">申请类型</option>
				<c:forEach var="item" items="${applyTypeList}">
			    	<option value="${item.type}" <c:if test="${search.applyType == item.type}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;
            <label>会员账号：</label>
			<input type="text" name="userCode" value="<c:out value='${search.userCode}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>会员简称：</label>
			<input type="text" name="userName" value="<c:out value='${search.userName}'/>" class="form-control" size="15" />
            <label>操作员姓名：</label>
            <input type="text" name="operatorName" value="<c:out value='${search.operatorName}'/>" class="form-control" size="15" />
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
        <div class="bjui-moreSearch">
			<label>申请时间：</label> 
			<input type="text" name="createTimeBegin" value="${search.createTimeBegin }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
			<label>-</label>
			<input type="text" name="createTimeEnd" value="${search.createTimeEnd }" class="form-control" size="15" data-toggle="datepicker"
				data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="7%">会员账号</th>
	    		<th align="center" width="14%">会员简称</th>
	    		<th align="center" width="20%">操作员姓名</th>
	    		<th align="center" width="7%">申请类型</th>
	    		<th align="center" width="7%">审核状态</th>
	    		<th align="center" width="20%">申请时间</th>
	    		<th align="center" width="12%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}">
	    	<tr align="center">
	    		<td>${obj.userCode}</td>
	    		<td><span class="text-omit pull-left">${obj.userName}</span></td>
	    		<td>${obj.operatorName}</td>
	    		<td>${obj.getApplyTypeDesc() }</td>
	    		<td>待审核</td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
	    		<a href="<%=request.getContextPath()%>/user/member/to/biztypeapply/audit.do?applyGuid=${obj.applyGuid}&memberId=${obj.memberId}" 
	    				class="btn btn-blue" data-toggle="dialog" data-width="800"  
	    				data-height="450" data-id="dialog-mask" data-mask="true" data-on-close="function(){$(this).navtab('refresh');}">审核</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

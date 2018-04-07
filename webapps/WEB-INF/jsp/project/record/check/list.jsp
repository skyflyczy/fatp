<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader" id="prolist-review-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/project/record/check/list.do" method="post">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <input type="hidden" name="orderField" value="">         
        <input type="hidden" name="orderDirection" value=""> 
        <div class="bjui-searchBar">
            <label>备案代码：</label>
			<input type="text" name="recordCode" value="<c:out value='${search.recordCode}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>备案名称：</label>
			<input type="text" name="recordFullName" value="<c:out value='${search.recordFullName}'/>" class="form-control" size="15" />
			<label>发行人：</label>
			<input type="text" name="loanUserName" value="<c:out value='${search.loanUserName}'/>" class="form-control" size="10" />
            <label>更新时间：</label>
            <input type="text" name="updateTimeBegin" value="${search.updateTimeBegin }" class="form-control" size="10" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
			<label>-</label>
			<input type="text" name="updateTimeEnd" value="${search.updateTimeEnd }" class="form-control" size="10" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent" id="prolist-review-list">
    <table data-toggle="tablefixed">
    	<thead>
    	<tr>
    		<th align="center" width="6%">序号</th>
    		<th align="center" width="8%" data-order-field="project_recordinfo.RecordCode">备案代码</th>
    		<th width="14%">备案名称</th>
    		<th width="15%">发行人</th>
    		<th align="right" width="12%" data-order-field="project_recordinfo.ProjectMoney">备案金额(元)</th>
    		<th align="center" width="8%">期限</th>
    		<th align="center" width="10%" data-order-field="project_recordinfo.InvestProfit">收益率(%)</th>
    		<th align="center" width="15%" data-order-field="project_recordinfo.UpdateTime">更新时间</th>
    		<th align="center" width="12%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list}" varStatus="status">
    	<tr align="center">
    		<td>${status.index + 1}</td>
    		<td><a href="<%=request.getContextPath()%>/project/record/view.do?id=${obj.id}" 
    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
    				data-mask="true" class="text-omit pull-left">${obj.recordCode}</a></td>
    		<td align="left"><a href="<%=request.getContextPath()%>/project/record/view.do?id=${obj.id}" 
    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
    				data-mask="true" class="text-omit pull-left">${obj.recordFullName}</a></td>
    		<td align="left"><a href="<%=request.getContextPath()%>/user/view.do?id=${obj.loanUserId}" 
    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
    				data-mask="true" class="text-omit pull-left">${obj.loanUserName}</a></td>
			<td align="right"><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/></td>
    		<td>${obj.projectLimit} <c:forEach var="item" items="${projectLimitTypeList}"><c:if test="${obj.projectLimitTypeId==item.type}">${item}</c:if></c:forEach></td>
    		<td><fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/> %</td>
    		<td><fmt:formatDate value="${obj.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    		<td>
    			<a href="<%=request.getContextPath()%>/project/record/check/view.do?id=${obj.recordGuid}" 
	   				class="btn btn-blue" data-toggle="dialog" data-width="700"  
	   				data-height="300" data-id="dialog-mask" data-mask="true">审核</a>
    		</td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

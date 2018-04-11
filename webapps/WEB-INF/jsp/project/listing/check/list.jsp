<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader" id="prolist-review-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/project/listing/check/list.do" method="post">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <input type="hidden" name="orderField" value="">         
        <input type="hidden" name="orderDirection" value=""> 
        <div class="bjui-searchBar">
            <label>挂牌代码：</label>
			<input type="text" name="projectCode" value="<c:out value='${search.projectCode}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>挂牌名称：</label>
			<input type="text" name="projectFullName" value="<c:out value='${search.projectFullName}'/>" class="form-control" size="15" />
			<label>发行人：</label>
			<input type="text" name="loanUserName" value="<c:out value='${search.loanUserName}'/>" class="form-control" size="15" />
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
	    		<th align="center" width="8%" data-order-field="ProjectCode">挂牌代码</th>
	    		<th width="16%">挂牌名称</th>
	    		<th width="15%">发行人</th>
	    		<th align="right" width="14%" data-order-field="ProjectMoney">挂牌金额(元)</th>
	    		<th align="center" width="6%">期限</th>
	    		<th align="center" width="9%" data-order-field="InvestProfit">收益率(%)</th>
	    		<th align="center" width="14%" data-order-field="UpdateTime">更新时间</th>
	    		<th align="center" width="12%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}" varStatus="status">
	    	<tr align="center">
	    		<td>${status.index + 1 }</td>
	    		<td><a href="<%=request.getContextPath()%>/project/listing/view.do?id=${obj.id}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" >${obj.projectCode}</a></td>
	    		<td align="left">
	    			<a href="<%=request.getContextPath()%>/project/listing/view.do?id=${obj.id}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${obj.projectFullName}</a>
	    		</td>
	    		<td align="left">${obj.loanUserName}</td>
	    		<td align="right"><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/></td>
	    		<td>${obj.projectLimit} <c:forEach var="item" items="${projectLimitTypeList}"><c:if test="${obj.projectLimitTypeId==item.type}">${item}</c:if></c:forEach></td>
	    		<td><fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/> %</td>
	    		<td><fmt:formatDate value="${obj.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
	    			<a href="<%=request.getContextPath()%>/project/listing/check/view.do?id=${obj.projectGuid}" 
		   				class="btn btn-blue" data-toggle="dialog" data-width="800"  
		   				data-height="480" data-id="dialog-mask" data-mask="true">审核</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

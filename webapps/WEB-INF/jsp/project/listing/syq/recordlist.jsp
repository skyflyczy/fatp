<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader" id="prolist-review-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/project/listing/syq/recordlist.do" method="post">
        <input type="hidden" name="orderField" value="${search.orderField}">         <!-- 排序字段 -->
		<input type="hidden" name="orderDirection" value="${search.orderDirection}">
        <div class="bjui-searchBar">
			<label>备案代码：</label>
			<input type="text" name="recordCode" value="<c:out value='${search.recordCode}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>备案名称：</label>
			<input type="text" name="recordFullName" value="<c:out value='${search.recordFullName}'/>" class="form-control" size="15" />
			<label>发行人：</label>
			<input type="text" name="loanUserName" value="<c:out value='${search.loanUserName}'/>" class="form-control" size="10" />
             <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
         <div class="bjui-moreSearch">
			<label>更新时间：</label>
			<input type="text" name="updateTimeBegin" value="${search.updateTimeBegin }" class="form-control" size="10" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
			<label>-</label>
			<input type="text" name="updateTimeEnd" value="${search.updateTimeEnd }" class="form-control" size="10" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
        </div>
    </form>
</div>

<div class="bjui-pageContent" id="prolist-review-list">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="8%" data-order-field="recordCode">备案代码</th>
	    		<th width="18%">备案名称</th>
	    		<th width="18%">发行人</th>
	    		<th align="right" width="12%" data-order-field="projectMoney">备案金额(元)</th>
	    		<th align="right" width="12%">可挂牌金额(元)</th>
	    		<th align="center" width="8%">已挂牌次数</th>
	    		<th align="center" width="10%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list}">
    	<tr>
    		<td align="center">${obj.recordCode}</td>
    		<td>${obj.recordFullName}</td>
    		<td>${obj.loanUserName}</td>
			<td align="right"><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/></td>
    		<td align="right"><fmt:formatNumber value="${obj.projectMoney-obj.quotedMoney}" pattern="#,##0.00"/></td>
    		<td align="center">${obj.quotedCnt }</td>
    		<td align="center">	
            	<a href="javascript:;" data-toggle="lookupback" data-args="{projectRecordId:${obj.id}, recordCode:'${obj.recordCode}', recordFullName:'${obj.recordFullName}', loanUserName:'${obj.loanUserName}', quotedCnt:${obj.quotedCnt }, projectTypeDesc:'${obj.projectTypeName }'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
            </td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

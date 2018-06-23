<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/project/listinginfo/infolist.do">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/project/listing/search_itembase.jsp" %>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed" data-nowrap="true">
    	<thead>
    	<tr>
    		<th align="center" width="4%">序号</th>
    		<th align="center" width="8%">挂牌代码</th>
    		<th width="12%">挂牌名称</th>
    		<th width="12%">发行人</th>
    		<th align="right" width="12%">挂牌金额(元)</th>
    		<th align="center" width="8%">期限</th>
    		<th align="center" width="14%">更新时间</th>
    		<th align="center" width="12%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}" varStatus="varStatus">
    		<tr>
	    		<td align="center">${varStatus.count}</td>
				<td align="center">${list.listingCode}</td>
	    		<td><a href="<%=request.getContextPath()%>/project/listinginfo/view.do?id=${list.listingGuid}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${list.listingFullName}</a></td>
	    		<td><span title="${list.issuer}">${list.issuer}</span></td>
	    		<td align="right"><fmt:formatNumber value="${list.listingMoney}" pattern="#,##0.00"/></td>
	    		<td align="center">${list.listingLimit} ${list.getListingLimitTypeDesc()}</td>
	    		<td align="center"><fmt:formatDate value="${list.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td align="center">
	    			<a href="<%=request.getContextPath()%>/project/listinginfo/edit.do?id=${list.listingGuid}" 
	    				class="btn btn-blue" data-toggle="dialog" data-width="1000"  
	    				data-height="600" data-id="dialog-edit" data-mask="true" >还款计划</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

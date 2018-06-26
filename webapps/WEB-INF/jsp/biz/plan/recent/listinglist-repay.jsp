<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader" id="prolist-repay-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/biz/plan/recentrepaylist.do" method="post">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
        	<%@ include file="/WEB-INF/jsp/project/listing/search_itembase.jsp" %>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent"  id="prolist-repay">
    <table data-toggle="tablefixed" data-width="100%" >
    	<thead>
    	<tr>
    		<th align="center" width="18%">挂牌信息</th>
    		<th align="center" width="18%">发行人</th>
    		<th align="center" width="16%">融资信息</th>
    		<th align="center" width="15%">应还情况</th>
    		<th align="center" width="10%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="list" items="${list}">
    	<tr>
    		<td>编号：<a href="<%=request.getContextPath()%>/project/listinginfo/view.do?id=${list.listingGuid}" 
    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
    				data-mask="true" >${list.listingCode}</a><br />
    			<span class="text-tit pull-left">名称：</span><a href="<%=request.getContextPath()%>/project/listinginfo/view.do?id=${list.listingGuid}" 
    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
    				data-mask="true" class="text-omit pull-left">${list.listingFullName}</a>
    		</td>
    		<td>${list.issuer}</td>
    		<td>
			融资金额：<fmt:formatNumber value="${list.listingMoney}" pattern="0.00"/> 元<br />
			融资期限：${list.listingLimit} ${list.getListingLimitTypeDesc()}<br />
			还款方式：${list.getPayInterestTypeDesc()}<br />
			</td>
    		<td>
    		应还期数:${list.periodNumber}<br />
    		应还日期:<fmt:formatDate value="${list.planRepayDate}" pattern="yyyy-MM-dd"/><br />
    		应还金额:<fmt:formatNumber value="${list.principal+list.interest}" pattern="0.00"/>元
    		</td>
    		<td>
    		<a href="/biz/plan/repay_completed.do?id=${list.repayPlanGuid}" 
	    					class="btn btn-blue" data-toggle="doajax" data-loadingmask="false" data-callback="function(){$(this).navtab('refresh');}" data-confirm-msg="确定还款完成吗？" data-mask="true" >还款完成</a>
    		</td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
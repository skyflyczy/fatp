<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader" id="fasset-prolist-search-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchControllerRoot}/search.do" method="post">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
			<%@ include file="/WEB-INF/jsp/pro/fasset/search/search_itembase.jsp" %>
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
        <div class="bjui-moreSearch">
			<%@ include file="/WEB-INF/jsp/pro/fasset/search/search_itemext.jsp" %>
        </div>
    </form>
</div>

<div class="bjui-pageContent"  id="fasset-prolist-search-list">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th width="14%">产品信息</th>
	    		<th width="14%">转让方信息</th>
	    		<th width="16%">转让信息</th>
	    		<th width="20%">时间相关</th>
	    		<th width="20%">转让情况</th>
	    		<th align="center" width="8%">产品状态</th>
	    		<th align="center" width="8%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}">
	    	<tr>
				<td>编号：${list.projectCode}<br />
	    			<span class="text-tit pull-left">名称：</span><a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/view.do?id=${list.id}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${list.projectName}</a>
	    		</td>
				<td>
					<span class="text-tit pull-left">转让方：</span><a href="<%=request.getContextPath()%>/user/view.do?id=${list.loanUserId}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${list.loanUserName}</a><br />
					<span class="text-tit pull-left">交易顾问：</span><a href="<%=request.getContextPath()%>/user/view.do?id=${list.memberId}" 
				    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
				    				data-mask="true" class="text-omit pull-left">${list.memberName}</a>
	    		</td>
	    		<td>
		    		挂牌金额：<fmt:formatNumber value="${list.projectMoney}" pattern="#,##0.00"/> 元<br />交易保证金：<fmt:formatNumber value="${list.guaranteeMoney }" pattern="#,##0.00"/> <c:if test="${list.guaranteeValueType ==1 }">元</c:if><c:if test="${list.guaranteeValueType ==2 }">%</c:if>元 <br />
		    		挂牌天数：${list.projectLimit} 天<br />转让方式：${list.getTransferTypeDesc() }</td>
	    		<td>
		    		创建时间：<fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
		    		发布时间：<fmt:formatDate value="${list.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
		    		转让开始：<fmt:formatDate value="${list.buyTimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
		    		转让结束：<fmt:formatDate value="${list.buyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
		    		核保时间：<fmt:formatDate value="${list.cfmRightTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
		    		<c:if test="${list.projectStatus != -1}">
		    		放款时间：<fmt:formatDate value="${list.releaseMoneyTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
		    		</c:if>
	    		</td>
	    		<td>
		    		成交金额：<fmt:formatNumber value="${list.cfmRightMoney}" pattern="#,##0.00"/> 元<br />
		    		放款金额：<fmt:formatNumber value="${list.cfmRightMoney-list.fee}" pattern="#,##0.00"/> 元<br />
		    		转让费用：<fmt:formatNumber value="${list.fee}" pattern="#,##0.00"/> 元<br />
	    		</td>
	    		<td align="center"><c:forEach var="item" items="${projectStatusDesc}"><c:if test="${item.value==list.projectStatus}">${item}</c:if></c:forEach></td>
	    		<td align="center">
	    			<a href="<%=request.getContextPath()%>/fasset/orderapply/bidlist.do?projectId=${list.id}" 
	    				data-toggle="dialog" data-width="1000"  
	    				data-height="600" data-id="dialog-mask" data-mask="true">转让详情</a><br />
	    			<c:if test="${list.projectStatus == -1 ||  list.projectStatus == 10}">
		    			<a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/cfmright-index.do?projectId=${list.id}" 
		    				data-toggle="dialog" data-width="1000"  
		    				data-height="600" data-id="dialog-mask" data-mask="true">核保结果</a><br >
	    			</c:if>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

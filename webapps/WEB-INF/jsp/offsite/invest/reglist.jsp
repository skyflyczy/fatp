<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资明细登记列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/invest/reglist.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
           <label class="control-label">挂牌代码：</label>
           <input type="text" name="listingCode" value="<c:out value='${search.listingCode}'/>" class="form-control" size="10"/>
           <label class="control-label">挂牌名称：</label>
           <input type="text" name="listingFullName" value="<c:out value='${search.listingFullName}'/>" class="form-control" size="10"/>
			<button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="6%">挂牌代码</th>
	    		<th align="center" width="12%">挂牌名称</th>
    			<th align="center" width="12%">发行人</th>
	    		<th align="right" width="12%">挂牌金额(元)</th>
	    		<th align="right" width="12%">已登记金额(元)</th>
	    		<th align="right" width="12%">已登记数量</th>
	    		<th align="center" width="14%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${list}" var="obj" varStatus="status">
    			<tr align="center">
    				<td align="center">${obj.listingCode}</td>
    				<td align="center"><a 
	    				href="<%=request.getContextPath()%>/project/listinginfo/view.do?id=${obj.listingGuid}"
    					data-toggle="dialog" data-width="1000"  
    					data-height="600" data-id="dialog-mask" data-mask="true" class="text-omit pull-left">${obj.listingFullName}</a>
    				</td>
    				<td align="center">${obj.issuer}</td>
    				<td align="right">
    					<fmt:formatNumber value="${obj.listingMoney}" pattern="#,##0.00"/>
    				</td>
    				<td align="right">
    					<fmt:formatNumber value="${obj.applyMoney}" pattern="#,##0.00"/>
    				</td>
    				<td align="right">
    					${obj.applyNum } 个
    				</td>
    				<td>
    					<a href="/offsite/invest/to_apply_register.do?id=${obj.listingGuid}" 
    					class="btn btn-blue" data-toggle="dialog" data-width="1000"  
    					data-height="600" data-id="dialog-mask" data-mask="true" data-confirm-msg="确定要关闭吗？">申请登记</a>
    					<c:if test="${obj.applyMoney > 0 }">
	    					<a href="/offsite/invest/tradeapplylist.do?id=${obj.listingGuid}" 
	    					class="btn btn-blue" data-toggle="dialog" data-width="1000"  
	    					data-height="600" data-id="dialog-mask" data-mask="true" data-confirm-msg="确定要关闭吗？">已登记列表</a>
    					</c:if>
    				</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
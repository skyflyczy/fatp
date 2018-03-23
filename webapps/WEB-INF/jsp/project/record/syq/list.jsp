<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/project/record/syq/list.do">
        <input type="hidden" name="orderField" value="${search.orderField}">         <!-- 排序字段 -->
		<input type="hidden" name="orderDirection" value="${search.orderDirection}">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/project/record/syq/search_itembase.jsp" %>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <div class="pull-right">
                <div class="btn-group">
                       <a href="<%=request.getContextPath()%>/project/record/syq/new.do"
                       		class="btn btn-blue" data-toggle="dialog" data-width="700" data-icon="plus"
                       		data-height="320" data-id="dialog-addRecord" data-mask="true">新增</a>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed" data-nowrap="true">
    	<thead>
	    	<tr>
	    		<th align="center" width="4%">序号</th>
	    		<th align="center" width="8%" data-order-field="recordCode">备案代码</th>
	    		<th width="14%">备案名称</th>
	    		<th width="12%">发行人</th>
	    		<th align="right" width="12%" data-order-field="projectMoney">备案金额(元)</th>
	    		<th align="center" width="8%">期限</th>
	    		<th align="center" width="10%"data-order-field="investProfit">收益率</th>
	    		<th align="center" width="14%" data-order-field="updateTime">更新时间</th>
	    		<th align="center" width="8%">备案状态</th>
	    		<th align="center" width="15%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}" varStatus="varStatus">
	    	<tr>
	    		<td align="center">${varStatus.count}</td>
	    		<td align="center">${list.recordCode}</td>
	    		<td><a href="<%=request.getContextPath()%>/project/record/syq/view.do?id=${list.id}" 
	    				data-toggle="dialog" data-width="1000"  data-height="800" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${list.recordFullName}</a></td>
	    		<td><span title="${list.loanUserName}">${list.loanUserName}</span></td>
	    		<td align="right"><fmt:formatNumber value="${list.projectMoney}" pattern="#,##0.00"/></td>
	    		<td align="center">
	    			${list.projectLimit} 
					<c:if test="${list.projectLimitTypeId == 1 }">
						天
					</c:if>
					<c:if test="${list.projectLimitTypeId == 2 }">
						月
					</c:if>
					<c:if test="${list.projectLimitTypeId == 3 }">
						年
					</c:if>
				</td>
				<td align="center"><fmt:formatNumber value="${list.investProfit*100}" pattern="0.000"/> %</td>
				<td align="center"><fmt:formatDate value="${list.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
					<span data-toggle="tooltip" data-placement="bottom" title="${list.auditRemark }">
					${list.recordStatusDesc}
					<c:if test="${list.recordStatus==5}"><i tabindex="0" data-trigger="focus" class="fa fa-info-circle" data-toggle="popover" data-content=""></i></c:if>
					</span>
				</td>
	    		<td align="center">
	    				<c:if test="${list.recordStatus == 1 ||list.recordStatus == 3 ||list.recordStatus == 5 }">
			   				<a href="<%=request.getContextPath()%>/project/record/syq/edit.do?id=${list.id}" 
			   					class="btn btn-blue" data-toggle="dialog" data-width="1000"  
			   					data-height="650" data-id="dialog-edit" data-mask="true" data-on-close="function(){$(this).navtab('refresh');}">编辑</a>
			   				<a href="<%=request.getContextPath()%>/project/record/syq/delete.do?id=${list.id}" 
			   					class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
	   					</c:if>
	    		</td>
	
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
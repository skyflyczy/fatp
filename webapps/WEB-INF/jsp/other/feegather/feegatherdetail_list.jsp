<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 费用归集明细列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
       <input type="hidden" name="feeGatherId" value="${search.feeGatherId}">
       <input type="hidden" name="feeGatherPaySettleId" value="${search.feeGatherPaySettleId}">
       <div class="bjui-searchBar"> 
        	 <label>支付状态：</label>
          	<select name="payStatus" data-toggle="selectpicker" class="show-tick" style="display: none;" size="10">
				<option value="">-全部-</option>
				<c:forEach var="item" items="${payStatusDesc}" >
					<option value="${item.status}" <c:if test="${search.payStatus==item.status }">selected</c:if>>${item}</option>
				</c:forEach> 
			</select>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch"  data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="25%">编号</th>
	    		<th align="right" width="25%">费用金额(元)</th>
	    		<th align="center" width="25%">支付状态</th>
	    		<th align="center" width="25%">创建时间</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="obj" items="${list}">
	    	<tr align="center">
	    		<td>${obj.id }</td>
	    		<td align="right"><fmt:formatNumber value="${obj.feeAmount }" pattern="#,##0.00"/></td>
	    		<td>${obj.getPayStatusDesc() }</td>
		    	<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    	</tr>
    		</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

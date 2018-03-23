<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--费用归集受理列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
        <div class="bjui-searchBar">
          <label>归集状态：</label>
          	<select name="gatherStatus" data-toggle="selectpicker" class="show-tick" style="display: none;" size="10">
				<option value="">-全部-</option>
				<c:forEach var="item" items="${gatherStatusDesc}" >
					<option value="${item.status}" <c:if test="${search.gatherStatus==item.status }">selected</c:if>>${item}</option>
				</c:forEach> 
			</select>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
         </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
    	<tr>
    		<th align="center" width="10%">归集编号</th>
    		<th align="right" width="10%">计划归集金额(元)</th>
    		<th align="right" width="10%">实际归集金额(元)</th>
    		<th align="center" width="10%">归集状态</th>
    		<th align="center" width="10%">受理时间</th>
    		<th align="center" width="10%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list }">
	    	<tr align="center">
	    		<td>${obj.id }</td>
	    		<td align="right"><fmt:formatNumber value="${obj.gatherPlanMoney }" pattern="#,##0.00"/></td>    		
	    		<td align="right"><fmt:formatNumber value="${obj.gatherRealMoney }" pattern="#,##0.00"/></td>
	    		<td>${obj.getGatherStatusDesc() }</td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
	  				<a href="<%=request.getContextPath()%>/other/payfeegather/feegatherdetail_list.do?feeGatherId=${obj.id}" 
	  				data-toggle="dialog" data-width="1000"  
	  				data-height="600" data-id="dialog-mask" data-mask="true">费用归集受理明细</a>
	    		</td>  
	    	</tr>
    	</c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

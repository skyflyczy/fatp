<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
兑付登记管理--初审
 -->
<div class="bjui-pageHeader" id="fasset-prolist-search-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/payinvest/firstaudit-list.do" method="post">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">        
	        <label class="control-label">挂牌代码：</label>
	        <input type="text" name="projectCode" value="<c:out value='${search.projectCode}'/>" class="form-control" size="10"/>
	        <label class="control-label">挂牌名称：</label>
	        <input type="text" name="projectFullName" value="<c:out value='${search.projectFullName}'/>" class="form-control" size="10"/>
	        <label class="control-label">投顾简称：</label>
	        <input type="text" name="investMemberName" value="<c:out value='${search.investMemberName}'/>" class="form-control" size="10"/>
				   	
			<label class="control-label">登记状态：</label>
			<select name="applyStatus" id="select3" data-toggle="selectpicker" class="show-tick">
				<option value="" >-全部-</option>
				<c:forEach var="item" items="${applyStatusArray }">
					<option value="${item.status }" <c:if test="${search.applyStatus == item.status }">selected</c:if>>${item }</option>
				</c:forEach>
			</select>				
				<button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>  
        </div>          
        <div class="bjui-moreSearch">
	        <label class="control-label">提交时间：</label>
				<input type="text" name="applyCreateTimeBegin" value="${search.applyCreateTimeBegin }" class="form-control" size="10" data-toggle="datepicker"
					   data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
				<label>-</label>
				<input type="text" name="applyCreateTimeEndText" value="${search.applyCreateTimeEndText }" class="form-control" size="10" data-toggle="datepicker"
					   data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>			
        </div>
    </form>
</div>

<div class="bjui-pageContent"  id="fasset-prolist-search-list">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
    			<th align="center" width="4%">序号</th>
				<th align="center" width="6%">挂牌代码</th>
				<th width="16%">挂牌名称</th>
				<th align="right" width="12%">确权总额(元)</th>
				<th align="center" width="10%">到期日</th>
				<th width="12%">投顾简称</th>
				<th align="right" width="12%">兑付确认总额(元)</th>
				<th align="center" width="14%">提交时间</th>
				<th align="center" width="6%">登记状态</th>
				<th align="center" width="12%">操作</th>
			</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}" varStatus="status">
			<tr align="center">
				<td>${status.index + 1}</td>
	    		<td>${list.projectCode}</td>
	    		<td align="left">
		    		<c:choose>
		    			<c:when test="${list.productTypeId == 1 }">
		    				<a href="<%=request.getContextPath()%>/debt/proissue/proinfo/view.do?id=${list.projectId}" 
				    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
				    				data-mask="true" >${list.projectFullName}</a>
		    			</c:when>
		    			<c:when test="${list.productTypeId == 2 }">
		    						<a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/view.do?id=${list.projectId}" 
				    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
				    				data-mask="true" class="text-omit pull-left">${list.projectFullName}</a>
				    	</c:when>
				    	<c:otherwise>
				    		<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${list.projectId}" 
				    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
				    				data-mask="true" class="text-omit pull-left">${list.projectFullName}</a>
				    	</c:otherwise>
		    		</c:choose>
	    		</td>
	    		<td align="right"><fmt:formatNumber value="${list.saleCfmRightMoney}" pattern="#,##0.00"/></td>
	    		<td><fmt:formatDate value="${list.expireDate}" pattern="yyyy-MM-dd"/></td>
	    		<td align="left"><a href="<%=request.getContextPath()%>/user/view.do?id=${list.applyMemberId}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${list.investMemberName}</a></td>
	    		<td align="right"><fmt:formatNumber value="${list.registerSuccessMoney}" pattern="#,##0.00"/></td>
	    		<td><fmt:formatDate value="${list.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>${list.applyStatusDesc }</td>
	    		<td><a href="<%=request.getContextPath()%>/offsite/payinvest/to-audit-apply.do?applyId=${list.applyId}"
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
	    				data-mask="true" class="btn btn-blue">审核</a>
	    				<a href="<%=request.getContextPath()%>/offsite/payinvest/audit-history.do?applyId=${list.applyId}"
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
	    				data-mask="true">审核记录</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 
转让登记管理--初审
 -->
<div class="bjui-pageHeader" id="fasset-prolist-search-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/transfer/secondaudit-list.do" method="post">
         <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
	        <select name="applyStatus" id="applyStatus" data-toggle="selectpicker" class="show-tick">
				<option value="" >-转让登记状态-</option>
				<c:forEach var="item" items="${applyStatusArray }">
					<option value="${item.status }" <c:if test="${search.applyStatus == item.status }">selected</c:if>>${item }</option>
				</c:forEach>
			</select>
			<select name="searchProjectMode" id="searchProjectMode" data-toggle="selectpicker" class="show-tick">
				<option value="0" <c:if test="${search.searchProjectMode == 0 }">selected</c:if>>挂牌代码</option>
				<option value="1" <c:if test="${search.searchProjectMode == 1 }">selected</c:if>>挂牌名称</option>
			</select>
			<input type="text" name="searchProjectKeyword" value="<c:out value='${search.searchProjectKeyword}'/>" class="form-control" size="30" />
			&nbsp;&nbsp;&nbsp;
			<select name="searchPeriodMode" id="searchPeriodMode" data-toggle="selectpicker" class="show-tick">
				<option value="0" >-请选择时间-</option>
				<option value="1" <c:if test="${search.searchPeriodMode == 1 }">selected</c:if>>转让时间段</option>
				<option value="2" <c:if test="${search.searchPeriodMode == 2 }">selected</c:if>>申请创建时间</option>
				<option value="3" <c:if test="${search.searchPeriodMode == 3 }">selected</c:if>>登记更新时间</option>
			</select>
			<input type="text" name="timeBegin" value="${search.timeBegin }" class="form-control" size="15" data-toggle="datepicker"
				   data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="datetime"/>
			<label>-</label>
			<input type="text" name="timeEnd" value="${search.timeEnd }" class="form-control" size="15" data-toggle="datepicker"
				   data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="datetime"/>
			
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent"  id="fasset-prolist-search-list">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
				<th align="center" width="6%">挂牌代码</th>
				<th width="16%">挂牌名称</th>
				<th align="center" width="8%">到期日</th>
				<th align="center" width="7%">登记成功数</th>
				<th align="center" width="17%">转让时间段</th>
				<th align="center" width="14%">申请创建时间</th>
				<th align="center" width="14%">登记更新时间</th>
				<th align="center" width="6%">登记状态</th>
				<th align="center" width="12%">操作</th>
			</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="list" items="${list}">
	    	<tr align="center">
	    		<td>
	    			<c:choose>
    					<c:when test="${list.productTypeId == 1 }">
		    				<a href="<%=request.getContextPath()%>/debt/proissue/proinfo/view.do?id=${list.projectId}" 
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
		    				data-mask="true" >${list.projectCode }</a>
    					</c:when>
    					<c:when test="${list.productTypeId == 2 }">
    						<a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/view.do?id=${list.projectId}" 
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
		    				data-mask="true" class="text-omit pull-left">${list.projectCode}</a>
    					</c:when>
    					<c:otherwise>
    						<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${list.projectId}" 
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
		    				data-mask="true" >${list.projectCode}</a>
    					</c:otherwise>
    				</c:choose>
	    		</td>
	    		<td align="left">
	    			<c:choose>
    					<c:when test="${list.productTypeId == 1 }">
		    				<a href="<%=request.getContextPath()%>/debt/proissue/proinfo/view.do?id=${list.projectId}" 
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
		    				data-mask="true" >${list.projectFullName }</a>
    					</c:when>
    					<c:when test="${list.productTypeId == 2 }">
    						<a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/view.do?id=${list.projectId}" 
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
		    				data-mask="true" class="text-omit pull-left">${list.projectFullName}</a>
    					</c:when>
    					<c:otherwise>
    						<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${list.projectId}" 
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
		    				data-mask="true" >${list.projectFullName}</a>
    					</c:otherwise>
    				</c:choose>
	    		</td>
	    		<td><fmt:formatDate value="${list.expireDate}" pattern="yyyy-MM-dd"/></td>
	    		<td>
	    		<c:if test="${list.registerSuccessNum > 0 }">
		    		<a href="<%=request.getContextPath()%>/offsite/transfer/apply_history.do?projectGuid=${list.projectGuid}&getSuccess=1" 
			    				data-toggle="dialog" data-width="900"  data-height="600" data-id="register-history-1" 
			    				data-mask="true" data-title="登记成功"
			    				data-on-close="function(){$(this).navtab('refresh');}" 
			    				>
	    				${list.registerSuccessNum}
	    			</a>
    			</c:if>
    			<c:if test="${list.registerSuccessNum == 0 }">
    			0
    			</c:if>
	    		</td>
	    		<td>
	    			<span style="display:inline-block;vertical-align:middle;"><fmt:formatDate value="${list.startDate}" pattern="yyyy-MM-dd"/><br><fmt:formatDate value="${list.startDate}" pattern="HH:mm:ss"/></span>
	    			~
	    		    <span style="display:inline-block;vertical-align:middle;"><fmt:formatDate value="${list.endDate}" pattern="yyyy-MM-dd"/><br><fmt:formatDate value="${list.endDate}" pattern="HH:mm:ss"/></span>
	    		</td>
	    		<td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td><fmt:formatDate value="${list.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>${list.applyStatusDesc }</td>
	    		<td><a href="<%=request.getContextPath()%>/offsite/transfer/to-audit-apply.do?applyId=${list.applyId}"
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
	    				data-mask="true" class="btn btn-blue">审核</a>
	    				<a href="<%=request.getContextPath()%>/offsite/transfer/audit-history.do?applyId=${list.applyId}"
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
	    				data-mask="true">审核记录</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 转让登记申请列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/transfer/apply-list.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
        	<%--
        	<select name="productExpiration" id="select2" data-toggle="selectpicker" class="show-tick">
				<option value="0" >-产品到期-</option>
				<option value="1" <c:if test="${search.productExpiration == 1 }">selected</c:if>>存续期内</option>
				<option value="2" <c:if test="${search.productExpiration == 2 }">selected</c:if>>已到期</option>
			</select>
			 --%>
			<select name="productType" id="select2" data-toggle="selectpicker" class="show-tick">
				<option value="0" >-业务类型-</option>
				<c:forEach var="item" items="${productTypeArray }">
					<option value="${item.value }" <c:if test="${search.productType == item.value }">selected</c:if>>${item }</option>
				</c:forEach>
			</select>
			<%-- <select name="applyStatus" id="applyStatus" data-toggle="selectpicker" class="show-tick">
				<option value="" >-转让登记状态-</option>
				<c:forEach var="item" items="${applyStatusArray }">
					<option value="${item.status }" <c:if test="${search.applyStatus == item.status }">selected</c:if>>${item }</option>
				</c:forEach>
			</select> --%>
			<select name="projectModeType" id="select4" data-toggle="selectpicker" class="show-tick">
				<option value="0" <c:if test="${search.projectModeType == 0 }">selected</c:if>>挂牌代码</option>
				<option value="1" <c:if test="${search.projectModeType == 1 }">selected</c:if>>挂牌名称</option>
			</select>
			<input type="text" name="projectMode" value="<c:out value='${search.projectMode}'/>" class="form-control" size="10"/>
			<label class="control-label x100">最新提交时间：</label>
			<input type="text" name="createTimeBegin" value="${search.createTimeBegin }" class="form-control" size="15" data-toggle="datepicker"
				data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="datetime"/>
			<label>-</label>
			<input type="text" name="createTimeEnd" value="${search.createTimeEnd }" class="form-control" size="15" data-toggle="datepicker"
				data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="datetime"/>
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
    		<th align="left" width="14%">挂牌名称</th>
    		<th align="center" width="14%">投资顾问</th>
    		<th align="center" width="8%">业务类型</th>
    		<th align="right" width="10%">确权总额(元)</th>
    		<th align="center" width="8%">到期日</th>
    		<th align="center" width="8%">登记成功数</th>
    		<th align="center" width="14%">最新申请创建时间</th>
    		<th align="center" width="8%">最新登记状态</th>
    		<th align="center" width="10%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="obj" items="${list }">
	    		<tr align="center">
	    			<td>
	    				<c:choose>
	    					<c:when test="${obj.productTypeId == 1 }">
			    				<a href="<%=request.getContextPath()%>/debt/proissue/proinfo/view.do?id=${obj.projectId}" 
			    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
			    				data-mask="true" >${obj.projectCode }</a>
	    					</c:when>
	    					<c:when test="${obj.productTypeId == 2 }">
	    						<a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/view.do?id=${obj.projectId}" 
			    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
			    				data-mask="true" class="text-omit pull-left">${obj.projectCode}</a>
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${obj.projectId}" 
			    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
			    				data-mask="true" >${obj.projectCode}</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
		    		<td align="left">
		    			<c:choose>
	    					<c:when test="${obj.productTypeId == 1 }">
			    				<a href="<%=request.getContextPath()%>/debt/proissue/proinfo/view.do?id=${obj.projectId}" 
			    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
			    				data-mask="true" >${obj.projectFullName }</a>
	    					</c:when>
	    					<c:when test="${obj.productTypeId == 2 }">
	    						<a href="<%=request.getContextPath()%>/fasset/proissue/proinfo/view.do?id=${obj.projectId}" 
			    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
			    				data-mask="true" class="text-omit pull-left">${obj.projectFullName}</a>
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${obj.projectId}" 
			    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
			    				data-mask="true" >${obj.projectFullName}</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			<td><span class="text-omit pull-left">${obj.investMemberName }</span></td>
		    		<td>${obj.getProductTypeDesc() }</td>
		    		<td align="right"><fmt:formatNumber value="${obj.cfmMoney }" pattern="#,#00.00#" /></td>
		    		<td><fmt:formatDate value="${obj.expireDate}" pattern="yyyy-MM-dd"/><br /></td>
		    		<td>
		    		<c:if test="${obj.successApplyNum >0 }">
		    			<a href="<%=request.getContextPath()%>/offsite/transfer/apply_history.do?projectGuid=${obj.projectGuid}&getSuccess=1" 
			    				data-toggle="dialog" data-title="登记成功" data-width="900"  data-height="600" data-id="register-history-1" 
			    				data-mask="true" 
			    				data-on-close="function(){$(this).navtab('refresh');}" 
			    				>${obj.successApplyNum }</a>
		    		</c:if>
		    		<c:if test="${obj.successApplyNum == 0 }">
		    			0
		    		</c:if>
		    		</td>
		    		<td><fmt:formatDate value="${obj.latestApplyCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    		<td>${obj.latestApplyStatus }</td>
		    		<td>
		    			<c:choose>
		    				<c:when test="${not empty obj.latestApplyStatus.status }">
				    			<a href="<%=request.getContextPath()%>/offsite/transfer/apply_history.do?projectGuid=${obj.projectGuid}" 
				    				data-toggle="dialog" data-width="900"  data-height="600" data-id="register-history-1" 
				    				data-mask="true" 
				    				data-on-close="function(){$(this).navtab('refresh');}" 
				    				>登记历史</a>
		    				</c:when>
		    				<c:otherwise>
		    					---
		    				</c:otherwise>
		    			</c:choose>
		    		</td>
	    		</tr>
    		</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

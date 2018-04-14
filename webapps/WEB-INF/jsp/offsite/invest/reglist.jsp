<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资明细登记列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/invest/reglist.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
           <label class="control-label">挂牌代码：</label>
           <input type="text" name="projectCode" value="<c:out value='${search.projectCode}'/>" class="form-control" size="10"/>
           <label class="control-label">挂牌名称：</label>
           <input type="text" name="projectFullName" value="<c:out value='${search.projectFullName}'/>" class="form-control" size="10"/>
            <label class="control-label">产品状态：</label>
        	<select name="projectStatus" id="select2" data-toggle="selectpicker" class="show-tick">
				<option value="" >-全部-</option>
				<c:forEach var="item" items="${projectStatusDesc }">
					<option value="${item.value }" <c:if test="${search.projectStatus == item.value}">selected</c:if>>${item }</option>
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
	    		<th align="center" width="6%">挂牌代码</th>
	    		<th align="center" width="12%">挂牌名称</th>
    			<th align="center" width="12%">发行人</th>
    			<th align="center" width="12%">产品状态</th>
	    		<th align="right" width="12%">挂牌金额(元)</th>
	    		<th align="right" width="12%">剩余登记金额(元)</th>
	    		<th align="center" width="12%">剩余登记次数</th>
	    		<th align="center" width="14%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${list}" var="obj" varStatus="status">
    			<tr align="center">
    				<td align="center">${obj.projectCode}</td>
    				<td align="center"><a 
	    				href="<%=request.getContextPath()%>/project/listing/view.do?id=${obj.projectId}"
    					data-toggle="dialog" data-width="1000"  
    					data-height="600" data-id="dialog-mask" data-mask="true" class="text-omit pull-left">${obj.projectFullName}</a>
    				</td>
    				<td align="center">${obj.loanUserName}</td>
    				<td>
    				<c:forEach items="${projectStatusDesc}" var="item">
    						<c:if test="${item.value == obj.projectStatus }">
    							${item}
    						</c:if>
    					</c:forEach>
    				</td>
    				<td align="right">
    					<fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/>
    				</td>
    				<td align="right">
    					<fmt:formatNumber value="${obj.projectMoney-obj.applyMoney}" pattern="#,##0.00"/>
    				</td>
    				<td>${obj.releaseNum - obj.applyNum}</td>
    				<td>
    					<a href="/offsite/invest/apply_register_index.do?projectGuid=${obj.projectGuid}" 
    					class="btn btn-blue" data-toggle="dialog" data-width="1000"  
    					data-height="600" data-id="dialog-mask" data-mask="true" data-confirm-msg="确定要关闭吗？">申请登记</a>
    					<a href="/offsite/invest/apply_register_edit.do?applyGuid=${obj.projectGuid}" 
    					class="btn btn-blue" data-toggle="dialog" data-width="1000"  
    					data-height="600" data-id="dialog-mask" data-mask="true" data-confirm-msg="确定要关闭吗？">编辑</a>
    				</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
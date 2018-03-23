<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资明细登记初审列表页 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/offsite/invest/invest-registrationquery-list.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
           <label class="control-label">挂牌代码：</label>
           <input type="text" name="projectCode" value="<c:out value='${search.projectCode}'/>" class="form-control" size="10"/>
           <label class="control-label">挂牌名称：</label>
           <input type="text" name="projectFullName" value="<c:out value='${search.projectFullName}'/>" class="form-control" size="10"/>
             <label class="control-label">投资顾问：</label>
           <input type="text" name="investMemberName" value="<c:out value='${search.investMemberName}'/>" class="form-control" size="10"/>
            <label class="control-label">产品状态：</label>
        	<select name="projectStatus" id="select2" data-toggle="selectpicker" class="show-tick">
				<option value="" >-全部-</option>
				<c:forEach var="item" items="${projectStatusDesc }">
					<option value="${item.value }" <c:if test="${search.projectStatus == item.value}">selected</c:if>>${item }</option>
				</c:forEach>
			</select>
			<label class="control-label">审核状态：</label>
			<select name="applyStatus" id="select3" data-toggle="selectpicker" class="show-tick">
				<option value="" >-全部-</option>
				<c:forEach var="item" items="${applyStatusDesc }">
					<option value="${item.getStatus() }" <c:if test="${search.applyStatus == item.status}">selected</c:if>>${item }</option>
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
	    		<th width="18%">挂牌名称</th>
	    		<th width="18%">投资顾问</th>
	    		<th align="right" width="9%">承销规模(元)</th>
	    		<!--  <th align="right" width="9%">确权总额(元)</th>	-->
	    		<th align="center" width="6%">产品状态</th>
	    		<th align="right" width="9%">上传总金额(元)</th>
	    		<!-- <th align="center" width="12%">申请提交时间</th>	-->
	    		<th align="center" width="12%">最新审核时间</th>
	    		<th align="center" width="7%">审核状态</th>
	    		<th align="center" width="15%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${list}" var="obj" varStatus="status">
    			<tr align="center">
    				<td>
	    				<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${obj.projectId}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" >${obj.projectCode}</a>
    				</td>
    				<td align="left">
	    				<a href="<%=request.getContextPath()%>/incomeright/proinfo/view.do?id=${obj.projectId}" 
	    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask" 
	    				data-mask="true" class="text-omit pull-left">${obj.projectFullName}</a>
    				</td>
    				<td align="left">
    					<span class="text-omit pull-left">${obj.investMemberName}</span>
    				</td>
    				<td align="right">
    					<fmt:formatNumber value="${obj.saleMoney}" pattern="#,##0.00"/>
    				</td>
    				<!--
    				<td align="right">
    					<fmt:formatNumber value="${obj.cfmMoney}" pattern="#,##0.00"/>
    				</td>
    				-->
    				<td>
    					${obj.getProjectStatusDesc() }
    				</td>
    				<td align="right">
    					<fmt:formatNumber value="${obj.uploadTotolMoney}" pattern="#,##0.00"/>
    				</td>
    				<!--
    				<td>
    				<fmt:formatDate value="${obj.applyCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    				</td>
    				-->
    				<td>
    					<fmt:formatDate value="${obj.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
    				</td>
    				<td>
    					<c:choose>
			    			<c:when test="${obj.applyStatus == 9 }"><a title="点击查看原因" href="javascript:;" data-toggle="alertmsg"  data-title="登记失败原因" data-type="info" data-msg="${obj.auditRemark eq null?' ':obj.auditRemark}" data-ok-name="关闭" data-auto-close="false" >${obj.getApplyStatusDesc()}</a></c:when>
			    			<c:otherwise>${obj.getApplyStatusDesc()}</c:otherwise>
			    		</c:choose>
    				</td>
    				<td>
		    		<a href="<%=request.getContextPath()%>/offsite/invest/to_registrationdetail.do?applyGuid=${obj.applyGuid}"
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
		    				data-mask="true">登记结果</a>
    				<a href="<%=request.getContextPath()%>/offsite/transfer/audit-history.do?applyId=${obj.bizImportApplyId}"
		    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
		    				data-mask="true" class="text-omit pull-left">审核记录</a>
    				</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

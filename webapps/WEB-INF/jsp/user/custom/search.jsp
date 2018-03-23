<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资者/个人、企业 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/custom/search.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
         <input type="hidden" name="totalpage" value="${totalpage }">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/user/custom/search_itembase.jsp" %>
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
	        <a id="dataexport" class="btn btn-blue">导出数据</a>
            <%-- <label class=" pull-right" style="margin-right:50px;margin-top:5px;">客户总数：${normalStatusUserSize} 个</label>  --%>
        </div>
        <div class="bjui-moreSearch">
			<%@ include file="/WEB-INF/jsp/user/custom/search_itemext.jsp" %>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="8%">客户账号</th>
	    		<th width="14%">客户简称</th>
	    		<th width="18%">所属会员</th>
	    		<th width="20%">客户全称</th>
	    		<th align="center" width="14%">注册日期</th>
	    		<th align="center" width="10%">客户类型</th>
	    		<th align="center" width="6%">账户状态</th>
	    		<th align="center" width="13%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}">
	    	<tr align="center">
	    		<td>${obj.userCode}</td>
	    		<td align="left">${obj.userName}</td>
	    		<td align="left">${obj.ownerUserName}</td>
	    		<td align="left">${obj.realName}</td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td><c:forEach var="item" items="${OrgType}"><c:if test="${item.type==obj.orgTypeId}">${item}</c:if></c:forEach></td>
	    		<td><c:forEach var="item" items="${DelStatusDesc}"><c:if test="${item.value==obj.userStatus}"><c:choose><c:when test="${item.value==3 }"><c:if test="${obj.auditStatus==1 }">待提交</c:if><c:if test="${obj.auditStatus==2 }">待审核</c:if><c:if test="${obj.auditStatus==3 }">审核退回</c:if></c:when><c:otherwise>${item}</c:otherwise></c:choose></c:if></c:forEach></td>
	    		<td>
		    		<a href="<%=request.getContextPath()%>/user/view.do?id=${obj.id}" 
		    				data-toggle="dialog" data-width="1000"  
		    				data-height="600" data-id="dialog-mask" data-mask="true">客户详情</a>
		    		<c:if test="${obj.userStatus==5 && obj.registerSource==1}">
		    		<br/><a href="<%=request.getContextPath()%>/user/sendstatus.do?userId=${obj.id}" 
		    				data-toggle="dialog" data-width="1000"  
		    				data-height="600" data-title="查看默认密码发送状态" data-id="dialog-mask" data-mask="true" style="margin-top:5px;">重发默认密码</a>
		    		</c:if>
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
<script>
$(function(){
	$("#dataexport").on('click',function() {
		var json = $("#pagerForm").serializeJson();
		$(this).dialog({data:json, id:'dialog-mask', url:'<%=request.getContextPath()%>/dataexport/user/tocustomer.do', title:'导出数据', type: 'post', width:700, height:200, mask:true});
	});
})
</script>
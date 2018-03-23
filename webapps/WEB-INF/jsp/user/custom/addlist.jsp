<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资者/个人、企业 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/custom/list.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/user/custom/search_itembase.jsp" %>
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <c:if test="${powerlist.contains('/user/custom/toAddPage.do') }">
            <div class="pull-right">
                <div class="btn-group">
                       <a href="<%=request.getContextPath()%>/user/custom/toAddPage.do?userChildType=4"
                       		class="btn btn-blue" data-toggle="dialog" data-width="600" 
                       		data-height="400" data-id="dialog-mask" data-mask="true">新增客户</a>
                </div>
            </div>
            </c:if>
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
    		<th align="center" width="14%">客户简称</th>
    		<th align="center" width="22%">客户全称</th>
    		<th align="center" width="12%">负责人</th>
    		<th align="center" width="10%">负责人手机<br/>(找回密码手机)</th>
    		<th align="center" width="14%">注册时间</th>
    		<th align="center" width="6%">状态</th>
    		<th align="center" width="14%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list}">
    	<tr>
    		<td>${obj.userCode}</td>
    		<td>${obj.userName}</td>
    		<td>${obj.realName}</td>
    		<td>${obj.linkMan }</td>
    		<td align="center">${obj.linkPhone}</td>
    		<td align="center"><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    		<td align="center"><c:forEach var="item" items="${DelStatusDesc}"><c:if test="${item.value==obj.userStatus}"><c:choose><c:when test="${item.value==3 }"><c:if test="${obj.auditStatus==1 }">待提交</c:if><c:if test="${obj.auditStatus==2 }">待审核</c:if></c:when><c:otherwise>${item}</c:otherwise></c:choose></c:if></c:forEach></td>
    		<td>
    		<c:if test="${powerlist.contains('/user/custom/toEditPage.do') && (obj.auditStatus==1||obj.userStatus==4)}">
    		<a href="<%=request.getContextPath()%>/user/custom/toEditPage.do?id=${obj.id}&isSetPwd=0" 
    				class="btn btn-blue" data-toggle="dialog" data-width="1000"  
    				data-height="800" data-id="dialog-mask" data-mask="true" data-on-close="function(){$(this).navtab('refresh');}">编辑</a>
    				</c:if>
    		<c:if test="${powerlist.contains('/user/custom/delete.do') && (obj.auditStatus==1||obj.userStatus==4)}">
    		<a href="<%=request.getContextPath()%>/user/custom/delete.do?id=${obj.id}" 
    				class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
    				</c:if>
    		<a href="<%=request.getContextPath()%>/user/view.do?id=${obj.id}"  data-width="1000"
    			data-height="600" data-mask="true"	data-toggle="dialog">客户详情</a>
    		</td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
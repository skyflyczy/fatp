<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/lookupload.do">
        <div class="bjui-searchBar">
        	<input type="hidden" name="pageSize" value="${pageSize}">
        	<input type="hidden" name="noId" value="${search.noId }">
            <label>客户全称：</label><input type="text" value="<c:out value='${search.realName}'/>" name="realName" size="10" class="form-control"/>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
        <thead>
            <tr>
                <th align="center" width="10%">客户编号</th>
                <th width="25%">客户简称</th>
                <th width="30%">客户全称</th>
                <th align="center" width="20%">创建日期</th>
                <th align="center" width="10%">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="list" items="${list}" varStatus="status">
            <tr>
                <td align="center">${list.userCode}</td>
                <td><span class="text-omit pull-left">${list.userName}</span></td>
                <td><span class="text-omit pull-left">${list.realName}</span></td>
                <td align="center"><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td align="center">	
                	<a href="javascript:;" data-toggle="lookupback" data-args="{loanUserId:'${list.id}',ownerUserId:'${list.id}', loader:'${list.userName}'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

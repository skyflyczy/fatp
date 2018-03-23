<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/companySearch.do">
        <div class="bjui-searchBar">
        	<input type="hidden" name="pageSize" value="${pageSize}">
            <label>营业执照号码(统一社会信用代码或注册号)：</label><input type="text" value="<c:out value='${search.companyOrgCode}'/>" name="companyOrgCode" size="10" class="form-control"/>&nbsp;
            <label>机构全称：</label><input type="text" value="<c:out value='${search.companyName}'/>" name="companyName" size="10" class="form-control"/>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
        <thead>
            <tr>
            	<th width="20%" align="center">营业执照号码</th>
                <th width="35%" align="center">机构全称</th>
                <th width="20%" align="center">法人代表</th>
                <th width="15%" align="center">操作</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="list" items="${list}" varStatus="status">
            <tr>
                <td>${list.companyOrgCode}</td>
                <td>${list.companyName}</td>
                <td>${list.companyRepresentative}</td>
                <td>	
               		<a href="javascript:;" data-toggle="lookupback" data-args="{companyOrgCode:'${list.companyOrgCode}', companyName:'${list.companyName}'}" class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

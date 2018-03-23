<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 系统参数-->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/param/list.do" method="post">
        <div class="bjui-searchBar">
            <label>参数key：</label><input type="text" name="paramKey" value="<c:out value='${paramKey}'/>" class="form-control" size="10" />
            <label>参数名：</label><input type="text" name="paramName" value="<c:out value='${paramName}'/>" class="form-control" size="10" />
            <label>参数值：</label><input type="text" name="paramValue" value="<c:out value='${paramValue}'/>" class="form-control" size="10" />
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="5%">编号</th>
	    		<th width="20%">参数名</th>
	    		<th width="25%">参数值</th>
	    		<th width="25%">参数Key</th>
	    		<th width="25%">备注</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}" varStatus="statu">
	    	<tr>
		    	<td align="center">${statu.index +1}</td>
		    	<td>${obj.paramName}</td>
		    	<td>
		    		<c:choose>
		    			<c:when test="${obj.valueCanView == 1 }">
		    				${obj.paramValue}
		    			</c:when>
		    			<c:otherwise></c:otherwise>
		    		</c:choose>
		    	</td>
		    	<td>${obj.paramKey}</td>
		    	<td>${obj.remark}</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>

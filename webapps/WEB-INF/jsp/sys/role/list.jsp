<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	//删除callback
	function deleteCallback(json){
		if(json.statusCode == 200){
			$(this).navtab('refresh')
				   .alertmsg('ok','删除成功',{"alertTimeout":1000});
		}else{
			$(this).bjuiajax('ajaxDone', json)
		}
	} 
	
</script>
<!-- 角色列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/sys/role/list.do" method="post">
        <input type="hidden" name="pageSize" value="${search.pageSize}">
        <input type="hidden" name="pageCurrent" value="${search.pageCurrent}">
        <input type="hidden" name="orderField" value="${search.orderField}">
        <input type="hidden" name="orderDirection" value="${search.orderDirection}">
        <div class="bjui-searchBar">
            <label>角色名称：</label><input type="text" name="roleName" value="<c:out value='${search.roleName}'/>" class="form-control" size="10" />
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <div class="pull-right">
                <div class="btn-group">
                       <a href="${ctx}/sys/role/add.do"
                       		class="btn btn-blue" data-toggle="dialog" data-width="600" 
                       		data-height="300" data-id="dialog-mask" data-mask="true">新增角色 </a>
                    	
                </div>
            </div>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">编号</th>
	    		<th align="center" width="20%">角色名称</th>
	    		<th align="center" width="10%">角色类型</th>
	    		<th align="center" width="20%">创建时间</th>
	    		<th align="center" width="20%">更新时间</th>
	    		<th align="center" width="20%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:if test="${empty sysRoles}">
	    		<tr align="center"><td colspan="6">暂无数据</td></tr>
	    	</c:if>
	    	<c:if test="${!empty sysRoles}">
	    	<c:forEach var="role" items="${sysRoles}" varStatus="statu">
	    	<tr align="center">
	    		<td>${statu.index +1}</td>
	    		<td>${role.roleName}</td>
	    		<td>
	    			<c:if test="${role.roleType == 1}">管理角色</c:if>
					<c:if test="${role.roleType == 2}">业务角色</c:if>
				</td>
	    		<td><fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td><fmt:formatDate value="${role.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
	    			<a href="${ctx}/sys/role/edit.do?id=${role.id}" 
	    				class="btn btn-blue" data-toggle="dialog" data-width="600"  
	    				data-height="300" data-id="dialog-mask" data-mask="true">编辑</a>
	    			<a href="${ctx}/sys/role/showpurview.do?id=${role.id}" 
	    				class="btn btn-blue" data-toggle="dialog" data-width="600"  
	    				data-height="560" data-id="dialog-mask" data-mask="true">菜单权限</a>
	    			<a href="${ctx}/sys/role/delete.do?id=${role.id}" 
	    				class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？" data-callback="deleteCallback">删除</a>
	    		</td>
	    	</tr>
	    	</c:forEach>
	    	</c:if>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

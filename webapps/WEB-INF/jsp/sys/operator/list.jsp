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
	//更新状态callback
	function updateStatuCallback(json){
		if(json.statusCode == 200){
			$(this).bjuiajax('ajaxDone', json)
				   .navtab('refresh');
		}else{
			$(this).bjuiajax('ajaxDone', json)
		}
	}
</script>
<!-- 操作人列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${ctx}/member/operator/list.do" method="post">
    	<input type="hidden" name="pageSize" value="${search.pageSize}">
        <input type="hidden" name="pageCurrent" value="${search.pageCurrent}">
        <input type="hidden" name="orderField" value="${search.orderField}">
        <input type="hidden" name="orderDirection" value="${search.orderDirection}">
        <div class="bjui-searchBar">
            <label>操作员登录名：</label><input type="text" name="loginName" value="<c:out value='${search.loginName}'/>" class="form-control" size="10" />
            <label>操作员姓名：</label><input type="text" name="realName" value="<c:out value='${search.realName}'/>" class="form-control" size="10" />
            <label>手机号码：</label><input type="text" name="phone" value="<c:out value='${search.phone}'/>" class="form-control" size="10" />
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <div class="pull-right">
                <div class="btn-group">
                       <a href="${ctx}/member/operator/add.do"
                       		class="btn btn-blue" data-toggle="dialog" data-width="600" 
                       		data-height="560" data-id="dialog-mask" data-mask="true">新增操作员 </a>
                    	
                </div>
            </div>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="6%">编号</th>
	    		<th align="center" width="10%">操作员登录名</th>
	    		<th align="center" width="10%">操作员姓名</th>
	    		<th align="center" width="10%">手机号码</th>
	    		<th align="center" width="8%">操作员类型</th>
	    		<th align="center" width="10%">职务/职业</th>
	    		<th align="center" width="12%">所属部门</th>
	    		<th align="center" width="6%">状态</th>
	    		<th align="center" width="12%">更新时间</th>
	    		<th align="center" width="15%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:if test="${empty operatorlist}">
	    		<tr align="center"><td colspan="10">暂无数据</td></tr>
	    	</c:if>
	    	<c:if test="${!empty operatorlist}"><c:forEach var="operator" items="${operatorlist}" varStatus="statu">
	    	<tr align="center">
	    		<td>${operator.id}</td>
	    		<td>${operator.loginName}</td>
	    		<td>${operator.realName}</td>
	    		<td>${operator.phone}</td>
	    		<td>
	    			<c:if test="${operator.operatorType == 1 }">
	    				业务人员
	    			</c:if>
	    			<c:if test="${operator.operatorType == 2 }">
	    				系统管理员
	    			</c:if>
	    		</td>
	    		<td>${operator.duty}</td>
	    		<td>${operator.department}</td>
	    		<td>
	    			<c:if test="${operator.operatorStatus == 0 }">
	    				冻结
	    			</c:if>
	    			<c:if test="${operator.operatorStatus == 1 }">
	    				正常
	    			</c:if>
	    			<c:if test="${operator.operatorStatus == 2 }">
	    				待激活
	    			</c:if>
	    		</td>
	    		<td><fmt:formatDate value="${operator.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td align="left">
	    			<c:if test="${operator.vipOperator==0}">
			    		<a href="${ctx}/member/operator/edit.do?id=${operator.id}" 
			    				 class="btn btn-blue" data-toggle="dialog" data-width="600"  
			    				data-height="560" data-id="dialog-mask" data-mask="true">编辑</a>
		   				<a href="${ctx}/member/operator/setrole.do?id=${operator.id}" 
				   				 class="btn btn-blue" data-toggle="dialog" data-width="600"   
				   				data-height="460" data-id="dialog-mask" data-mask="true">设置角色</a>
						<a href="${ctx}/member/operator/delete.do?id=${operator.id}" 
								class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？" data-callback="deleteCallback">删除</a>
	    			<c:if test="${operator.operatorStatus == 0}">
	    				<a href="${ctx}/member/operator/updateopestatu.do?id=${operator.id}&operatorStatus=${operator.operatorStatus}" 
	    					 class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要解冻？" data-callback="updateStatuCallback">
	    					解冻
	    				</a>
	    			</c:if>
	    			<c:if test="${operator.operatorStatus == 1}">
	    				<a href="${ctx}/member/operator/updateopestatu.do?id=${operator.id}&operatorStatus=${operator.operatorStatus}"  
	    					class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要冻结？" data-callback="updateStatuCallback">
	    					冻结
	    				</a>
	    			</c:if>
	    			</c:if>
	    		</td>
	    	</tr>
	    	</c:forEach></c:if>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

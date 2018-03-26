<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- 投资者/个人、企业 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/member/list.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/user/member/search_itembase.jsp" %>
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <div class="pull-right">
                <div class="btn-group">
                       <a href="<%=request.getContextPath()%>/user/member/toAddPage.do"
                       		class="btn btn-blue" data-toggle="dialog" data-width="600" 
                       		data-height="300" data-id="dialog-mask" data-mask="true">会员账号注册</a>
                </div>
            </div>
        </div>
        <div class="bjui-moreSearch">
			<%@ include file="/WEB-INF/jsp/user/member/search_itemext.jsp" %>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">会员编号</th>
	    		<th width="14%">会员简称</th>
	    		<th width="20%">会员全称</th>
	    		<th align="center" width="12%">经办人</th>
	    		<th align="center" width="10%">经办人手机</th>
	    		<th align="center" width="14%">注册时间</th>
	    		<th align="center" width="8%">状态</th>
	    		<th align="center" width="18%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:forEach var="obj" items="${list}">
	    	<tr align="center">
	    		<td>${obj.userCode}</td>
	    		<td align="left"><span class="text-omit pull-left">${obj.userName}</span></td>
	    		<td align="left"><span class="text-omit pull-left">${obj.realName}</span></td>
	    		<td><span id="agent_${obj.id}"></span></td>
	    		<td><span id="agentphone_${obj.id}"></span></td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
		    		${obj.showUserStatus() }
	    		</td>
	    		<td>
		    		<c:if test="${obj.canEdit()}">
			    		<a href="<%=request.getContextPath()%>/user/member/toEditPage.do?id=${obj.id}&isSetPwd=0" 
			    				class="btn btn-blue" data-toggle="dialog" data-width="1000"  
			    				data-height="800" data-id="dialog-mask-editPage" data-mask="true" 
			    				data-on-close="function(){$(this).navtab('refresh');}">编辑</a>
		    		</c:if>
			    	<a href="<%=request.getContextPath()%>/user/member/delete.do?id=${obj.id}" 
			    				class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
		    		<a href="<%=request.getContextPath()%>/user/view.do?id=${obj.id}"  data-width="1000"
		    			data-height="600" data-mask="true"	data-toggle="dialog">会员详情</a>
	    		
	    		</td>
	    	</tr>
	    	</c:forEach>
    	</tbody>
    </table>
</div>
<script type="text/javascript">
$.ajax({
	type:"GET",
	url:"<%=request.getContextPath()%>/user/member/registeragentlist.do?ids=${ids}",
	cache: 'false',
	dataType: 'json',
	success:function(data){
		if(data != undefined) {
			for(var i=0; i<data.length; i++) {
				var info = data[i];
				if(info.realName!= null){
					$("#agent_"+info.memberId).text(info.realName);
				}
				if(info.phone != null) {
					$("#agentphone_"+info.memberId).text(info.phone);
				}
			}
		}
	}
});
</script>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.huajin.ptoms.domain.UcUser, com.huajin.ptoms.util.StatusUtil" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 投资者/个人、企业 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/member/search.do">
        <input type="hidden" name="pageSize" value="${pageSize}">
        <input type="hidden" name="totalpage" value="${totalpage }">
        <div class="bjui-searchBar">
            <%@ include file="/WEB-INF/jsp/user/member/search_itembase.jsp" %>
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <a id="dataexport" class="btn btn-blue">导出数据</a>
        	<label class=" pull-right" style="margin-top:5px;">会员总数：${normalStatusUserSize} 个</label>
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
	    		<th align="center" width="8%">会员账号</th>
	    		<th width="16%">会员简称</th>
	    		<th width="22%">会员全称</th>
	    		<th align="center" width="15%">经办人</th>
	    		<th align="center" width="9%">经办人手机</th>
	    		<th align="center" width="14%">注册时间</th>
	    		<th align="center" width="6%">状态</th>
	    		<th align="center" width="13%">操作</th>
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
	    		    <% UcUser user = (UcUser)pageContext.getAttribute("obj"); %>
	                <%= StatusUtil.userStatus(user) %>
	    		</td>
	    		<td>
		    		<c:if test="${obj.userStatus==1||obj.userStatus==5 }">
		    		<a href="/user/member/frozen.do?id=${obj.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要冻结吗？">冻结</a>
		    		</c:if>
		    		<c:if test="${obj.userStatus==2 }">
		    		<a href="/user/member/frozen.do?id=${obj.id}" class="btn btn-green" data-toggle="doajax" data-confirm-msg="确定要冻结吗？">解冻</a>
		    		</c:if>
		    		<c:if test="${obj.userStatus==3||obj.userStatus==4}"> 
		    		<a href="/user/member/delete.do?id=${obj.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
		    		</c:if>
		    		<a href="<%=request.getContextPath()%>/user/view.do?id=${obj.id}"  data-width="1000"
		    			data-height="600" data-mask="true" data-toggle="dialog">会员详情</a>
		    		<c:if test="${obj.userStatus==1 && obj.canResend }">
		    		<br/><a href="<%=request.getContextPath()%>/user/sendstatus.do?userId=${obj.id}" 
		    				class="btn btn-blue" data-toggle="dialog" data-width="1000"  
		    				data-height="600" data-title="查看默认密码发送状态" data-id="dialog-mask" data-mask="true" style="margin-top:5px;">重发默认密码</a>
		    		</c:if>
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

$(function(){
	$("#dataexport").on('click',function() {
		var json = $("#pagerForm").serializeJson();
		$(this).dialog({data:json, id:'dialog-mask', url:'<%=request.getContextPath()%>/dataexport/user/tomember.do', title:'导出数据', type: 'post', width:700, height:200, mask:true});
	});
})
</script>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 融资顾问银行卡 -->
<script type="text/javascript">
function callback(json){
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message);
		$(this).bjuiajax('refreshLayout', {target:'#bankinfo',url:$("#bankinfo_link").attr('href')});
		$('#bankinfo').removeClass('bjui-layout')
	}else{
		$(this).alertmsg("error", json.message);
	}
}
</script>
<div style="height:100%;overflow:auto;">
	<table class="table table-hover table-striped">
	    <thead>
	        <tr>
	        	<th width="16%">账户名称</th>
	            <th width="18%">账户号码</th>
	            <th align="center" width="11%">开户行</th>
	            <th width="24%">开户行所在地</th>
	            <th align="center" width="10%">默认收款账户</th>
	            <th width="21%">
	            	<a href="<%=request.getContextPath()%>/user/bankcard/toAdd.do?userId=${user.id}"
	                       		class="btn btn-green" data-toggle="ajaxload" data-mask="true" data-target="#bank-wrap">新增</a>
	            </th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach var="obj" items="${list}" varStatus="status">
	        <tr>
	        	<td>${obj.accountName}</td>
	            <td>${obj.cardAccount}</td>
	            <td align="center">${obj.channelName}</td>
	            <td>${obj.provinceName}${obj.cityName}${obj.subBankName}</td>
	            <td align="center"><c:if test="${obj.defaultCard==1}">是</c:if><c:if test="${obj.defaultCard==0}">否</c:if></td>
	            <td>
	            	<a href="<%=request.getContextPath()%>/user/bankcard/toAdd.do?userId=${user.id}&id=${obj.id}" 
	    				class="btn btn-blue" data-toggle="ajaxload" data-mask="true" data-target="#bank-wrap">编辑</a>
	                <c:if test="${obj.defaultCard==0}"><a data-callback="callback" data-toggle="doajax" href="<%=request.getContextPath()%>/user/bankcard/del.do?userId=${user.id }&id=${obj.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删除</a></c:if>
	                <c:if test="${obj.defaultCard==0}"><a data-callback="callback" data-toggle="doajax" href="<%=request.getContextPath()%>/user/bankcard/updateDefault.do?userId=${obj.userId }&cardId=${obj.id}" class="btn btn-blue row-del" data-confirm-msg="确定要设为默认卡吗？">设为默认卡</a></c:if>
	            </td>
	        </tr>
	     	</c:forEach>
	    </tbody>
	</table>
	<div id="bank-wrap"></div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function resendCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message);
		$(this).dialog("refresh");
	}else {
		$(this).alertmsg("error", json.message);
	}
}
</script>
<div class="bjui-pageContent">
	<form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/user/sendstatus.do?userId=${search.userId}">
    	<input type="hidden" name="pageSize" value="${pageSize}">
    </form>
    <c:if test="${canResend }">
    <p>
	    <a href="/user/resend.do?userId=${search.userId}" data-toggle="doajax" data-callback="resendCallback" class="btn btn-primary">重新发送</a>
	    &nbsp; &nbsp; &nbsp;向<c:if test="${userIdentity == 1 }">该会员</c:if><c:if test="${userIdentity == 2 }">该客户</c:if>发送短信，短信内容为开户结果、<c:if test="${userIdentity == 1 }">会员运营平台</c:if><c:if test="${userIdentity == 2 }">资产交易平台</c:if>账号及密码。
    </p>
   	</c:if>
   	<p><b>短信发送记录</b></p>
    <table data-toggle="tablefixed">
        <thead>
            <tr>
                <th align="center">手机号</th>
                <th align="center">发送通道</th>
                <th align="center">发送时间</th>
                <th align="center">发送状态</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="list" items="${list}" varStatus="status">
            <tr align="center">
                <td>${list.mobileNo}</td>
                <td>
					<c:forEach var="item" items="${smsChannels}" >
						<c:if test="${item.channelId == list.smsChannel }"> ${item } </c:if>
					</c:forEach> 
                </td>
                <td><fmt:formatDate value="${list.realSendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><c:choose>
                <c:when test="${list.sendStatus==1}">成功</c:when>
                <c:when test="${list.sendStatus==0}">未发送</c:when>
                <c:when test="${list.sendStatus==2}">重新发送中</c:when>
                <c:otherwise>失败</c:otherwise></c:choose></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String host = request.getServerName();
if(host.indexOf(".com") > 0) {
	host = host.substring(0, host.indexOf(".com"));
	host = host.substring(host.lastIndexOf(".")+1);
}
%>
<%@ include file="version.jsp" %>
<link rel="icon" href="//cf2.<%=host %>.com/favicon.ico" mce_href="//cf2.<%=host %>.com/favicon.ico" type="image/x-icon">
<script src="//cf1.<%=host %>.com/base/jquery-1.7.2.min.js"></script>
<script src="//cf1.<%=host %>.com/util/jquery.cookie.js"></script>
<script src="//cf1.<%=host %>.com/util/jquery.md5.js"></script>
<link href="<%=request.getContextPath() %>/jslib/css/bootstrap.min.css" rel="stylesheet">
<link href="//cf1.<%=host %>.com/BJUI/themes/css/style.min.css?v=${version}" rel="stylesheet">
<link href="//cf1.<%=host %>.com/BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link href="//cf1.<%=host %>.com/ui/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/jslib/FA/css/font-awesome.min.css" rel="stylesheet">
<link href="//cf2.<%=host %>.com/css/qj.login.min.css" rel="stylesheet">
<script src="//cf1.<%=host %>.com/BJUI/js/bjui-core.js?v=${version}"></script>
<script src="//cf1.<%=host %>.com/BJUI/js/bjui-regional.zh-CN.js?v=${version}"></script>
<script src="//cf1.<%=host %>.com/BJUI/js/bjui-extends.js?v=${version}"></script>
<script src="//cf1.<%=host %>.com/BJUI/js/bjui-theme.js?v=${version}"></script>
<script src="//cf1.<%=host %>.com/BJUI/js/bjui-plugins.js?v=${version}"></script>
<script src="//cf1.<%=host %>.com/ui/niceValidator/jquery.validator.js"></script>
<script src="//cf1.<%=host %>.com/ui/niceValidator/jquery.validator.themes.js"></script>
<c:if test="${hm }">
<script src="//cf2.<%=host %>.com/js/hm.js"></script>
</c:if>
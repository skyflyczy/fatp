<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
if(typeof jQuery == 'undefined'){  //未加载框架 
	window.location='<%=request.getContextPath() %>/login.do' ; 
}else{
	$(this).alertmsg('warn', '会话超时，请重新登录',{okName:'重新登录',title:'登录提示',okCall:function(){
		window.location='<%=request.getContextPath() %>/login.do' ; 
	}});
}
</script>

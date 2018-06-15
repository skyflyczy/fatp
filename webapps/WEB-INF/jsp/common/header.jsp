<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--[if lte IE 7]>
    <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="javascript:;">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="javascript:;" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<header id="bjui-header" class="navbar navbar-default navbar-static-top">
    <div class="bjui-navbar-header clearfix">
    	<a href="javascript:;" class="navbar-brand"><b>${exchange.ptomsSysName }</b></a>
	    <nav id="bjui-navbar-collapse" class="pull-right">
	        <ul class="bjui-navbar-right nav navbar-nav pull-right">
	            <li class="datetime"><div><span id="bjui-date"></span> <span id="bjui-clock"></span></div></li>
	            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
	                <ul class="dropdown-menu" role="menu">
	                    <li><a href="<%=request.getContextPath() %>/toModifyPwd.do" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="550" data-height="300">&nbsp;<span class="fa fa-lock"></span> 修改密码</a></li>
	                    <li><a href="<%=request.getContextPath() %>/member/operator/myinfo.do" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="600" data-height="600">&nbsp;<span class="fa fa-user"></span> 我的资料</a></li>
	                    <!--li><a href="{:U('Admin/Index/cache')}" data-toggle="navtab">&nbsp;<span class="fa fa-trash"></span> 清理缓存</a></li-->
	                    <li class="divider"></li>
	                    <li><a href="<%=request.getContextPath() %>/logout.do" id="logout" class="red">&nbsp;<span class="fa fa-power-off"></span>退出系统</a></li>
	                </ul>
	            </li>
	        </ul>
	    </nav>
    </div>
    <div id="bjui-hnav" class="pull-left">
        <div id="bjui-hnav-navbar-box">
            <ul id="bjui-hnav-navbar" class="nav navbar-nav">
                <li class="active">
                    <a href="javascript:;" data-url="<%=request.getContextPath() %>/home.do" data-target="#bjui-homePage" data-toggle="slidebar" id="nav-myhome">我的主页</a>
                    <div class="items hide" data-noinit="true">
                        <div class="home-page">
                        	<%-- <%@ include file="/WEB-INF/home.jsp" %> --%>
                        </div>
                    </div>
                </li>
       			<c:forEach var="nav" items="${list }" varStatus="navstatus">
                	<c:if test="${nav.isButton==3 }">
		                <li>
		                    <a href="javascript:;" data-toggle="slidebar" data-id-key="targetid" id="nav_${nav.id }">${nav.name }</a>
		                    <div class="items hide" data-noinit="true">
		                        <c:forEach var="obj" items="${nav.getChildren() }" varStatus="status">
		                        <ul id="nav_${nav.id }_leftMenu_${status.index }" class="ztree" data-toggle="ztree" data-tit="${obj.name }"
										data-options='{
											nodes:${obj.getChildJson() },
											expandAll : true,
											checkEnable:false,
											onClick: "ZtreeClick"
										}'></ul>
		                        </c:forEach>
		                    </div>
		                </li>
                	</c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
</header>
<script type="text/javascript">
$(function(){
	//退出系统
	$(document).on('click','#logout',function(){
		$.cookie("bjui.slidebar.hnav.panels",null)
		$.cookie('curNavtabBtn',null)
		$.cookie('bjui.slidebar.collapse.in',null)
	})
});
</script>
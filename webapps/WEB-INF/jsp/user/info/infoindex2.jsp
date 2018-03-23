<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
        <div style="margin:0px auto 0; width:100%;">
                <!-- Tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">基本信息</a></li>
                    <c:if test="${user.userIdentity==1}">
                    <li><a href="<%=request.getContextPath()%>/member/operator/registeragentinfo.do?userId=${user.id}" 
	    				id="agent_link" role="tab" data-toggle="ajaxtab" data-target="#agent" data-reload="false">经办人</a></li>
			        <li><a href="<%=request.getContextPath()%>/member/operator/advisor-rz/to-advisor-admin-info.do?userId=${user.id}" 
	    				role="tab" data-toggle="ajaxtab" data-target="#operator" data-reload="false">管理员信息</a></li>
	    			</c:if>
	    			<li><a href="<%=request.getContextPath()%>/user/view/files.do?id=${userId}" role="tab" data-toggle="ajaxtab" data-target="#files" data-reload="false">注册资料</a></li>
                    <li><a href="<%=request.getContextPath()%>/user/view/usertype.do?id=${userId}" role="tab" data-toggle="ajaxtab" data-target="#usertype" data-reload="false">业务资格</a></li>
                    <li><a href="<%=request.getContextPath()%>/user/view/bankinfo.do?id=${userId}" role="tab" data-toggle="ajaxtab" data-target="#bankinfo" data-reload="false">银行账户</a></li>
                    <li><a href="#financeinfo" role="tab" data-toggle="tab">财务信息</a></li>
                   	<li><a href="<%=request.getContextPath()%>/user/view/linkinfo.do?id=${userId}" role="tab" data-toggle="ajaxtab" data-target="#linkinfo" data-reload="false">部门信息</a></li>
			        <li><a href="<%=request.getContextPath()%>/user/feed.do?userId=${user.id}" 
			                    	role="tab" data-toggle="ajaxtab" data-target="#feed" data-reload="false">动态</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="baseinfo">
                    	<%@ include file="/WEB-INF/jsp/user/info/info_base2.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="agent" ></div>
                    <div class="tab-pane fade" id="operator" ></div>
                    <div class="tab-pane fade" id="files" >
                    	<%@ include file="/WEB-INF/jsp/user/info/userfiles.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="usertype">
                    	<%@ include file="/WEB-INF/jsp/user/info/usertypes.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="bankinfo">
                    	<%@ include file="/WEB-INF/jsp/user/info/info_bank2.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="financeinfo">
                    	<%@ include file="/WEB-INF/jsp/user/info/info_finance2.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="linkinfo">
                    	<%@ include file="/WEB-INF/jsp/user/info/info_link2.jsp" %>
                    </div>
                    <div class="tab-pane fade bjui-layout" id="feed" ></div>
                </div>
        </div>
</div>

<div class="bjui-pageFooter">
 <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>
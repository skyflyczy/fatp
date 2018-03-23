<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
        <div style="margin:0px auto 0; width:100%;">
                <!-- Tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">基本信息</a></li>
                    <li><a href="#financeinfo" role="tab" data-toggle="tab">财务信息</a></li>
                   	<li><a href="#usertype" role="tab" data-toggle="tab">业务资格</a></li>
			        <li><a href="#files" role="tab" data-toggle="tab">附件</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="baseinfo">
                    	<%@ include file="/WEB-INF/jsp/user/info/info_base1.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="financeinfo">
                    	<%@ include file="/WEB-INF/jsp/user/info/info_finance1.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="usertype">
                    	<%@ include file="/WEB-INF/jsp/user/info/usertypes.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="files" >
                    	<%@ include file="/WEB-INF/jsp/user/info/userfiles.jsp" %>
                    </div>
                </div>
        </div>
</div>

<div class="bjui-pageFooter">
 	<ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><a href="<%=request.getContextPath()%>/user/check.do?id=${user.id}&type=2" data-toggle="doajax" data-confirm-msg="确定要审核不通过吗？" class="btn btn-orange">审核不通过</a></li>
        <li><a href="<%=request.getContextPath()%>/user/check.do?id=${user.id}&type=1" data-toggle="doajax" data-confirm-msg="确定要审核通过吗？" class="btn btn-blue">审核通过</a></li>
    </ul>
</div>
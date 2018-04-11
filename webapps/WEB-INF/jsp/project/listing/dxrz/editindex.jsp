<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
        <!-- Tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">挂牌信息</a></li>
            <!-- 
            <li><a id="otherfiles_link" href="<%=request.getContextPath()%>/debt/proissue/profiles/list.do?projectId=${obj.id}&projectTypeId=${obj.projectTypeId}&belongType=2" 
            	role="tab" data-toggle="ajaxtab" data-target="#otherfiles" data-reload="false">附件</a></li>
            	 -->
           	<li><a href="<%=request.getContextPath()%>/timeline/listing.do?entityId=${obj.id}" 
               	role="tab" data-toggle="ajaxtab" data-target="#feedlist" data-reload="false">动态</a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane fade active in" id="baseinfo">
            	<%@ include file="/WEB-INF/jsp/project/listing/dxrz/editpro.jsp" %>
            </div>
            <!-- 
            <div class="tab-pane fade"  id="otherfiles"></div> -->
            <div class="tab-pane fade bjui-layout" id="feedlist"></div>
        </div>
</div>
<div class="bjui-pageFooter">
	<button type="button" class="btn-blue pull-right submitButton" id="submitBtn">提交审核</button>
</div>
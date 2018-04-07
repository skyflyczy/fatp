<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
        <div style="margin:0px auto 0; width:100%;">
                <!-- Tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">产品信息</a></li>
                       <!-- 
                    <li><a href="<%=request.getContextPath()%>/income/right/profiles/view.do?projectId=${obj.id}&belongType=1&projectTypeId=${obj.projectTypeId}" 
                    	role="tab" data-toggle="ajaxtab" data-target="#otherfiles" data-reload="false">附件</a></li>-->
            		<li><a href="<%=request.getContextPath()%>/timeline/record.do?entityId=${obj.id}" 
			                    	role="tab" data-toggle="ajaxtab" data-target="#feedlist" data-reload="false">动态</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="baseinfo">
                    	<%@ include file=/WEB-INF/jsp/project/record/syq/proinfo-base.jsp" %>
                    </div>
                    <!-- 
                    <div class="tab-pane fade" id="otherfiles"></div> -->
                    <div class="tab-pane fade bjui-layout" id="feedlist"></div>
                </div>
        </div>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>
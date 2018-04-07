<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
     <!-- Tabs -->
     <ul class="nav nav-tabs" role="tablist">
         <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">备案信息</a></li>
         <!-- 
         <li><a id="otherfiles_link" href="<%=request.getContextPath()%>/income/right/profiles/recordlist.do?projectId=${obj.id}&projectTypeId=${obj.projectTypeId}&memberId=${obj.memberId}" 
         	role="tab" data-toggle="ajaxtab" data-target="#otherfiles" data-reload="true">附件</a></li>
         	 -->
        <li><a href="<%=request.getContextPath()%><%=request.getContextPath()%>/timeline/record.do?entityId=${obj.id}" 
            	role="tab" data-toggle="ajaxtab" data-target="#feedlist" data-reload="false">动态</a></li>
     </ul>
     <!-- Tab panes -->
     <div class="tab-content">
         <div class="tab-pane fade active in" id="baseinfo">
         	<%@ include file="/WEB-INF/jsp/project/record/syq/editpro.jsp" %>
         </div>
         <!-- 
         <div class="tab-pane fade" id="otherfiles"></div> -->
         <div class="tab-pane fade bjui-layout" id="feedlist"></div>
     </div>
</div>
<div class="bjui-pageFooter">
	<button type="button" class="btn-blue pull-right submitButton" id="submitBtn">提交审核</button>
</div>

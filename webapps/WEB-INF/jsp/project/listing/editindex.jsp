<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
      <!-- Tabs -->
      <ul class="nav nav-tabs" role="tablist">
          <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">挂牌信息</a></li>
         	<li><a href="<%=request.getContextPath()%>/timeline/listinginfo.do?entityId=${obj.id}" 
             	role="tab" data-toggle="ajaxtab" data-target="#feedlist" data-reload="false">动态</a></li>
      </ul>
      <!-- Tab panes -->
      <div class="tab-content">
          <div class="tab-pane fade active in" id="baseinfo">
          	<%@ include file="/WEB-INF/jsp/project/listing/editInfo.jsp" %>
          </div>
         <div class="tab-pane fade bjui-layout" id="feedlist"></div>
      </div>
</div>

<div class="bjui-pageFooter">
	<button type="button" class="btn-close pull-right" data-icon="close">关闭</button>	
	<button type="button" class="btn-blue pull-right submitButton" id="submitBtn" data-icon="save">保存</button>
</div>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
     <!-- Tabs -->
     <ul class="nav nav-tabs" role="tablist">
         <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">申请登记</a></li>
         <!-- 
 		<li><a href="<%=request.getContextPath()%>/timeline/investrecords.do?entityId=${listingInfo.id}" 
             	role="tab" data-toggle="ajaxtab" data-target="#feedlist" data-reload="false">动态</a></li>
             	 -->
     </ul>
     <!-- Tab panes -->
     <div class="tab-content">
         <div class="tab-pane fade active in" id="baseinfo">
         	<%@ include file="/WEB-INF/jsp/offsite/invest/apply-register-base.jsp" %>
         </div>
         <!-- 
         <div class="tab-pane fade bjui-layout" id="feedlist"></div>
          -->
     </div>
</div>
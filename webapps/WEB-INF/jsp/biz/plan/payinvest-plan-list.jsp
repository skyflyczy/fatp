<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function afterPayinvestCompleted(json){
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message);
		$(this).dialog("refresh");
	}else{
		$(this).alertmsg("error", json.message);
	}
}
</script>
<div class="bjui-pageHeader">
<table class="table table-condensed table-hover" width="100%">
        <tbody>
            <tr>
                <td align="right">
                <!-- 
                <c:if test="${canPayinvestCompleted }">
                 <a href="/biz/plan/payinvest_completed.do?id=${planRepay.repayPlanGuid }" 
	    					class="btn btn-blue" data-toggle="doajax" 
	    					data-callback="afterPayinvestCompleted()" data-confirm-msg="确定全部兑付完成吗？" 
	    					data-mask="true" >兑付完成</a>
	    		</c:if>
	    		 <c:if test="${!canPayinvestCompleted }">
	    		 	已兑付完成
	    		 </c:if>
	    		 &nbsp;&nbsp;
	    		  -->
                <a href="/biz/plan/export_payinvest_list.do?id=${planRepay.repayPlanGuid }" 
	    					class="btn btn-blue" class="btn btn-blue"   
	    					 data-mask="true" >导出兑付文件</a>
                </td>
            </tr>
   	</tbody>
  </table>
</div>
<div class="bjui-pageContent" id="bidlist-payoffline-cfm-list">
 <table data-toggle="tablefixed" data-width="100%" data-height="300">
    	<thead>
    	<tr>
    		<th align="center" width="8%">序号</th>
    		<th align="center" width="15%">客户名称</th>
    		<th align="center" width="20%">兑付本金</th>
    		<th align="center" width="15%">兑付利息</th>
    		<th align="center" width="15%">兑付总额</th>
    		<th align="center" width="25%">兑付账户</th>
    	</tr>
    	</thead>
    	<tbody>
    	  <c:if test="${not empty list}">
    	  	<c:forEach items="${list}" var="obj" varStatus="status">
    		<tr>
    			<td align="center">${status.count}</td>
    			<td align="center">${obj.investUserRealName}</td>
    			<td align="center"><fmt:formatNumber value="${obj.principal}" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td align="center"><fmt:formatNumber value="${obj.interest}" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td align="center"><fmt:formatNumber value="${obj.principal + obj.interest}" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td align="center">
    			账号：${obj.cardAccount}<br/>
    			支行：${obj.subBankName}
    			</td>
    		</tr>
    		</c:forEach>
    	  </c:if>
    		
    	</tbody>
    </table> 
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/channeltradedetail/searchList.do?paycenterId=${search.paycenterId }&transferMoney=${search.transferMoney }" method="post">
		 <input type="hidden" id="transferAmount" name="transferAmount" value="0" />
		 <input type="hidden" name="paycenterId" value="${search.paycenterId }" />
		 <input type="hidden" id="transferMoney" name="transferMoney" value="${search.transferMoney }" />
		 <input type="hidden" name="pageSize" value="${pageSize}">
        <input type="hidden" name="totalpage" value="${totalpage}">
        <div class="bjui-searchBar">
			 <label>账户名称：</label><input type="text" name="oppositeAccountName" value="<c:out value='${search.oppositeAccountName}'/>" class="form-control" size="10" />
			<label>交易时间：</label>
			<input type="text" name="tradeTimeBegin" value="${search.tradeTimeBegin }" class="form-control" size="10" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="date || datetime"/>
			<label>-</label>
			<input type="text" name="tradeTimeEnd" value="${search.tradeTimeEnd }" class="form-control" size="10" data-toggle="datepicker"
			 data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="date || datetime"/>
			 <label>摘要：</label><input type="text" name="summary" value="<c:out value='${search.summary}'/>" class="form-control" size="10" />
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
	        <button id="btnIds" type="button" class="btn-blue" data-toggle="lookupback" data-lookupid="ids" data-warn="请至少选择一位接收人">确定选中</button>
         </div>
    </form>
</div>
<div class="bjui-pageContent" id="div1">
    <table data-toggle="tablefixed">
    	<thead>
    	<tr>   
    		<th align="center" width="6%">选择</th>
    		<th width="22%" align="center">对方账户</th>
    		<th align="center" width="14%">交易金额(元)</th>
    		<th align="center" width="15%">交易时间</th>
    		<th align="center" width="15%">交易流水号</th>
    		<th align="center" width="20%">摘要</th> 
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list }"  varStatus="status">
	    	<tr align="center" style="height:40px;">
                <td><input type="checkbox" name="ids"  data-toggle="icheck" value="{payTradeDetailIds:'${obj.id}'}" id="${obj.tradeMoney }" onchange="javascript:alert('111');"></td>
	    		   
	    		<td>
	    			<p style="padding-left:11px;">账户名称：${obj.oppositeAccountName }</p>
	    			<p>账户号码：${obj.oppositeAccountNo }</p>
	    		</td>   
	    		<%-- <td>${obj.oppositeAccountNo }</td> --%> 
	    		<td align="center"><fmt:formatNumber value="${obj.tradeMoney }" pattern="#,##0.00"/></td>  
	    		<td><fmt:formatDate value="${obj.tradeTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td align="center">${obj.myPaymentNo }</td>
	    		<td>${obj.summary }</td>      		
	    	</tr>
    	</c:forEach>
    	<c:if test="${empty list}"><tr><td colspan=12 align="center" class="red">无数据</td></tr></c:if>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
<script>
$("#div1").on("ifChecked", ":checkbox", function(event){
	var transferAmount = $("#transferAmount").val();
	var amount =$(this).attr("id");	
	$("#transferAmount").val(Math.round(transferAmount*100+amount*100)/100);
	if($("#transferAmount").val()*1 >= $("#transferMoney").val()*1){	
		$("#btnIds").attr("disabled", false);
	}else{
		$("#btnIds").attr("disabled", true);		
	}
});
$("#div1").on("ifUnchecked", ":checkbox", function(event){
	var transferAmount = $("#transferAmount").val();
	var amount =$(this).attr("id");	
	$("#transferAmount").val(Math.round(transferAmount*100-amount*100)/100) ;
	if($("#transferAmount").val()*1>=$("#transferMoney").val()*1){	
		$("#btnIds").attr("disabled", false);
	}else{
		$("#btnIds").attr("disabled", true);		
	}
});
</script>

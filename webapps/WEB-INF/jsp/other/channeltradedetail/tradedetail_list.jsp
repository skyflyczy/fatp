
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/channeltradedetail/list.do" method="post">
        <div class="bjui-searchBar">
          <label>渠道名称：</label>
          	<select name="paycenterId" data-toggle="selectpicker" class="show-tick" style="display: none;" size="10">
				<option value="">-全部-</option>
				<c:forEach var="item" items="${payClearCenterDesc}" >
					<option value="${item.centerId}" <c:if test="${search.paycenterId==item.centerId }">selected</c:if>>${item}</option>
				</c:forEach> 
			</select>
			<label>渠道资金流向：</label>
			<select name="financeDirection" data-toggle="selectpicker" class="show-tick" style="display: none;" size="10">
				<option value="">-全部-</option>
				<option value="1"<c:if test="${search.financeDirection==1 }">selected</c:if>>入金</option>
				<option value="2"<c:if test="${search.financeDirection==2 }">selected</c:if>>出金</option>
			</select>
			<label>交易时间：</label>
			<input type="text" name="tradeTimeBegin" value="${search.tradeTimeBegin }" class="form-control" size="10" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="date || datetime"/>
			<label>-</label>
			<input type="text" name="tradeTimeEnd" value="${search.tradeTimeEnd }" class="form-control" size="10" data-toggle="datepicker"
			 data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="date || datetime"/>
			 <label>摘要：</label><input type="text" name="summary" value="<c:out value='${search.summary}'/>" class="form-control" size="10" />
			 <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom2">
            	<i class="fa fa-angle-double-down"></i></button>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <c:choose>
            	<c:when test="${search.balanceMoney != null }"><label class="pull-right" style="margin-top:5px;margin-right:5px;font-size:14px;">渠道余额：<fmt:formatNumber value="${search.balanceMoney }" pattern="#,##0.00"/> 元</label></c:when>
            	<c:when test="${search.balanceMoney == null && search.paycenterId ==null }"></c:when>
            	<c:otherwise><label class="pull-right" style="margin-top:5px;margin-right:5px;font-size:14px;">该渠道不支持查询余额</label></c:otherwise>
            </c:choose>
         </div>
         <div class="bjui-moreSearch">
			<label>我方流水：</label>
			<input type="text" name="myPaymentNo" value="<c:out value='${search.myPaymentNo}'/>" class="form-control" size="10" data-rule="integer"/>
			<label>对方流水：</label>
			<input type="text" name="oppositePaymentNo" value="<c:out value='${search.oppositePaymentNo}'/>" class="form-control" size="10" data-rule="integer"/>
        </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
    	<tr>
    		<th align="center" width="4%">编号</th>
    		<th align="center" width="10%">渠道名称</th>
    		<th align="center" width="10%">我方账户</th>
    		<th width="14%">对方账户</th>
    		<th align="right" width="10%">交易金额(元)</th>
    		<th align="right" width="10%">账户余额(元)</th>
    		<th align="center" width="14%">交易时间</th>
    		<th width="18%">交易流水号</th>
    		<th align="center" width="10%">摘要</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list }"  varStatus="status">
	    	<tr align="center" style="height:40px;">
	    		<td>${status.index + 1 }</td>
	    		<td>${obj.paycenterName }</td>    		
	    		<td>${exchangeName }
	    			<c:choose>
		    			<c:when test="${fn:length(obj.myAccountNo) > 4}"> 
					    	【${fn:substring(obj.myAccountNo,fn:length(obj.myAccountNo)-4,fn:length(obj.myAccountNo)) }】
					   </c:when> 
					   <c:otherwise></c:otherwise>
				   </c:choose>
	    		</td>
	    		<td align="left">
	    			<c:choose>
		    			<c:when test="${empty obj.oppositeAccountName}"> 
					    	${obj.companyName }
					   </c:when> 
					   <c:otherwise>${obj.oppositeAccountNo }<br/>${obj.oppositeAccountName }</c:otherwise>
					   
				   </c:choose>
				   <c:choose>
		    			<c:when test="${not empty obj.oppositeBranchName }"><br/>${obj.oppositeBranchName }</c:when>
					   <c:otherwise></c:otherwise>
				   </c:choose>
				</td>
	    		<td align="right"><fmt:formatNumber value="${obj.tradeMoney }" pattern="#,##0.00"/></td>
	    		<td align="right"><fmt:formatNumber value="${obj.balance }" pattern="#,##0.00"/></td>
	    		<td><fmt:formatDate value="${obj.tradeTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td align="left">我方：${obj.myPaymentNo }<br/>对方：${obj.oppositePaymentNo }</td>
	    		<td>${obj.summary }</td>  
	    	</tr>
    	</c:forEach>
    	<c:if test="${empty list}"><tr><td colspan=12 align="center" class="red">无数据</td></tr></c:if>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

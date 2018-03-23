<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	//重新对账
	function settleCheckCallback(json){
		if(json.statusCode == 200){
			$(this).alertmsg('info','对账请求发送成功，请稍后查看结果',{autoClose:false,okCall:function(){$(this).navtab("refresh");}});
		}else{
			$(this).bjuiajax('ajaxDone', json)
		}
	} 
</script>
<!--支付渠道对账-->
<div class="bjui-pageHeader" id="prolist-issue-search">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
        <div class="bjui-searchBar">
			<label>支付渠道：</label>
			<select name="payCenterId" id="select1" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${payCenterName}">
					<option value="${item.name}" <c:if test="${search.payCenterId==item.name}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>
			<label>对账状态：</label>
			<select name="checkStatus" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${checkStatusDesc}">
					<option value="${item.status}" <c:if test="${search.checkStatus==item.status}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>
			<label>对账日期：</label>
			<input type="text" name="checkDate" value="${search.checkDate }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
			<button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
        </div>
        
    </form>
    <br>
    <form id="pager1Form" data-toggle="validate" action="<%=request.getContextPath()%>/other/settlecheck/afresh_settlecheck.do" method="post" data-callback="settleCheckCallback">
        <div class="bjui-searchBar">
        	<label>支付渠道：</label>
			<select name="payCenterId" id="select1" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${payCenterName}">
					<option value="${item.name}" <c:if test="${search.payCenterId==item.name}">selected</c:if>>${item}</option>
				</c:forEach>
			</select>
			<label>对账日期：</label>
			<input type="text" name="checkDate" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
			<button type="submit" class="btn-default" data-clear-query="false">对账</button>
        </div>
    </form>
</div>

<div class="bjui-pageContent" id="prolist-issue-list">
 <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="7%">支付渠道</th>
	    		<th align="center" width="15%">交易中心</th>
	    		<th align="center" width="8%">对账日期</th>
	    		<th align="center" width="6%">对账状态</th>
	    		<th align="center" width="7%">渠道支付成功数(笔)</th>
	    		<th align="right" width="9%">渠道总入金(元)</th>
	    		<th align="right" width="9%">渠道总出金(元)</th>
	    		<th align="center" width="7%">平台支付成功数(笔)</th>
	    		<th align="right" width="9%">平台总入金(元)</th>
	    		<th align="right" width="9%">平台总出金(元)</th>
	    		<th align="center" width="13%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    		<c:if test="${not empty list}"><c:forEach items="${list}" var="obj">
    		<tr align="center">
    			<td>
    				<c:forEach items="${payCenterName}" var="item">
    					<c:if test="${obj.payCenterId == item.name }">${item}</c:if>
    				</c:forEach>
    			</td>
    			<td>
    				<c:forEach var="item" items="${feExchanges}">
    					<c:if test="${search.exchangeId==item.id}">${item.exchangeName}</c:if>
					</c:forEach>
    			</td>
    			<td><fmt:formatDate value="${obj.checkDate}" pattern="yyyy-MM-dd"/></td>
    			<td>
    				<c:forEach items="${checkStatusDesc}" var="item">
    					<c:if test="${obj.checkStatus == item.status }">${item}</c:if>
    				</c:forEach>
    			</td>
    			<td>${obj.payTotalNumber}</td>
    			<td align="right">
    				<fmt:formatNumber value="${obj.payTotalMoneyIn}"  pattern="#,##0.00" maxFractionDigits="2"/>
    			</td>
    			<td align="right">
    				<fmt:formatNumber value="${obj.payTotalMoneyOut}"  pattern="#,##0.00" maxFractionDigits="2"/>
    			</td>
    			<td>${obj.bizTotalNumber}</td>
    			<td align="right">
    				<fmt:formatNumber value="${obj.bizTotalMoneyIn}"  pattern="#,##0.00" maxFractionDigits="2"/>
    			</td>
    			<td align="right">
    				<fmt:formatNumber value="${obj.bizTotalMoneyOut}"  pattern="#,##0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
	    			<c:if test="${powerlist.contains('/other/settlecheck/afresh_settlecheck.do') }">
	    				<a href="<%=request.getContextPath()%>/other/settlecheck/afresh_settlecheck.do?checkDate=<fmt:formatDate value="${obj.checkDate}" pattern="yyyy-MM-dd"/>&payCenterId=${obj.payCenterId}&exchangeId=${obj.exchangeId}&refresh=true" 
						  data-toggle="doajax" data-confirm-msg="确定要重新对账吗？" data-callback="settleCheckCallback" class="btn btn-blue">重新对账</a>
	    			</c:if>
    				<c:if test="${powerlist.contains('/other/settlecheck/viewdetail.do') }">
	    				<a href="<%=request.getContextPath()%>/other/settlecheck/viewdetail.do?payCheckId=${obj.id}" 
	    				data-toggle="dialog" data-width="1000"  
	    				data-height="600" data-id="dialog-mask" data-mask="true">对账明细</a>
	    			</c:if>
    			</td>
    		</tr>
    		</c:forEach></c:if>
    		
    	</tbody>
    </table> 
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

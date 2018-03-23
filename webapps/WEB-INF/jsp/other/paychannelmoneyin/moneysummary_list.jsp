<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function summary(json){
		$(this).alertmsg("info", json.message,{autoClose:false,okCall:function(){$(this).navtab("refresh");}});
	}
	
	function afterSettle(json){
		$(this).alertmsg("info", json.message,{autoClose:false,okCall:function(){$(this).navtab("refresh");}});
	}
	
</script>
<!--支付渠道入金统计列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
        <div class="bjui-searchBar">
        	<label>支付渠道：</label>
			<select name="payCenterId"  data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">全部</option>
				<c:forEach var="item" items="${payCenterName}">
					<option value="${item.paycenterId}" <c:if test="${search.payCenterId==item.paycenterId}">selected</c:if>>${item.payCenterName}</option>
				</c:forEach>
			</select>
            <label>统计日期：</label>
			<input type="text" name="statDate" value="${search.statDate }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
           <label>结算状态：</label>
          	<select name="settleStatus" data-toggle="selectpicker" class="show-tick" style="display: none;" size="10">
				<option value="">-全部-</option>
				<c:forEach var="item" items="${settleStatusDesc}" >
					<option value="${item.status}" <c:if test="${search.settleStatus==item.status }">selected</c:if>>${item}</option>
				</c:forEach> 
			</select>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
         	<div class="pull-right">
                <div class="btn-group">
                       <a href="<%=request.getContextPath()%>/other/paychannelmoneyin/paychannel_summary.do" data-confirm-msg="确定发起渠道入金统计吗？"
                       		class="btn btn-blue" data-toggle="doajax" data-callback="summary">支付渠道入金统计</a>
                </div>
            </div>
         </div>
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">编号</th>
	    		<th align="center" width="10%">产品编号</th>
	    		<th align="center" width="14%">支付渠道</th>
	    		<th align="center" width="10%">统计日期</th>
	    		<th align="right" width="14%">统计金额(元)</th>
	    		<th align="center" width="16%">统计时间</th>
	    		<th align="center" width="10%">结算状态</th>
	    		<th align="center" width="16%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list }">
	    	<tr align="center">
	    		<td>${obj.id }</td>
	    		<td>${obj.projectId }</td>
	    		<td>
	    			<c:forEach items="${payCenterName}" var="item">
    					<c:if test="${obj.payCenterId == item.paycenterId }">${item.payCenterName}</c:if>
    				</c:forEach>
    			</td>
    			<td><fmt:formatDate value="${obj.statDate }" pattern="yyyy-MM-dd"/></td>    		
	    		<td align="right"><fmt:formatNumber value="${obj.inMoney }" pattern="#,##0.00"/></td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>${obj.getSettleStatusDesc() }</td>
	    		<td>
	    			<c:if test="${powerlist.contains('/other/paychannelmoneyin/paychannel_settle.do') && (obj.settleStatus == 0 || obj.settleStatus == 3)}">
		  				<a href="<%=request.getContextPath()%>/other/paychannelmoneyin/paychannel_settle.do?summaryId=${obj.id}" 
		  				class="btn btn-blue" data-toggle="doajax" data-callback="afterSettle" data-confirm-msg="确定发起渠道入金结算吗？">渠道入金结算</a><br/>
		  			</c:if>
	  				<a href="<%=request.getContextPath()%>/other/paychannelmoneyin/moneysettle_list.do?payMoneySummrayId=${obj.id}" 
	  				data-toggle="dialog" data-width="1000"  
	  				data-height="600" data-id="dialog-mask" data-mask="true">支付渠道入金结算明细</a>
	    		</td>  
	    	</tr>
    	</c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

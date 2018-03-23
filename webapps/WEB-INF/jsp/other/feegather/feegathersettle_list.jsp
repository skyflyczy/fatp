<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function aftersettle(json){
		$(this).alertmsg("info", json.message,{autoClose:false,okCall:function(){$(this).navtab("refresh");}});
	}
</script>
<!--费用归集结算列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>${searchAddress}" method="post">
        <div class="bjui-searchBar">
          <label>支付状态：</label>
          	<select name="payStatus" data-toggle="selectpicker" class="show-tick" style="display: none;" size="10">
				<option value="">-全部-</option>
				<c:forEach var="item" items="${payStatusDesc}" >
					<option value="${item.status}" <c:if test="${search.payStatus==item.status }">selected</c:if>>${item}</option>
				</c:forEach> 
			</select>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
         	<div class="pull-right">
                <div class="btn-group">
                       <a href="<%=request.getContextPath()%>/other/payfeegather/feegathersettle.do" data-confirm-msg="确定发起费用归集结算吗？"
                       		class="btn btn-blue" data-toggle="doajax" data-callback="aftersettle">发起费用归集结算</a>
                </div>
            </div>
         </div>
         
    </form>
</div>
<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">结算编号</th>
	    		<th align="center" width="20%">结算流水号</th>
	    		<th align="right" width="20%">结算金额(元)</th>
	    		<th align="center" width="10%">支付状态</th>
	    		<th align="center" width="20%">结算时间</th>
	    		<th align="center" width="20%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach var="obj" items="${list }">
	    	<tr align="center">
	    		<td>${obj.id }</td>
	    		<td>${obj.paymentNo }</td>    		
	    		<td align="right"><fmt:formatNumber value="${obj.moneyAmount }" pattern="#,##0.00"/></td>
	    		<td>${obj.getPayStatusDesc() }</td>
	    		<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
	  				<a href="<%=request.getContextPath()%>/other/payfeegather/feegatherdetail_list.do?feeGatherPaySettleId=${obj.id}" 
	  				data-toggle="dialog" data-width="1000"  
	  				data-height="600" data-id="dialog-mask" data-mask="true">费用归集受理明细</a>
	    		</td>   
	    	</tr>
    	</c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

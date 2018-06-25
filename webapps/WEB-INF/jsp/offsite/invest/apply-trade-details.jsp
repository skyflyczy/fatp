<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--金融资产转让线下支付凭证到款操作 -->
<div class="bjui-pageHeader">
<table class="table table-condensed table-hover" width="100%">
        <tbody>
            <tr>
                <td align="right">
                <a href="/offsite/invest/downtradedetails.do?id=${search.id}" 
	    					class="btn btn-blue" class="btn btn-blue"   
	    					 data-mask="true" >下载投资明细文件</a>
                </td>
            </tr>
   	</tbody>
  </table>
</div>
<div class="bjui-pageContent" id="bidlist-payoffline-cfm-list">
 <table data-toggle="tablefixed" data-width="100%" data-height="300">
    	<thead>
    	<tr>
    		<th align="center" width="15%">客户名称</th>
    		<th align="center" width="20%">交易时间</th>
    		<th align="center" width="15%">投资总额</th>
    		<th align="center" width="15%">加息</th>
    		<th align="center" width="25%">证件</th>
    		<th align="center" width="15%">手机号</th>
    		<th align="center" width="15%">银行卡号</th>
    	</tr>
    	</thead>
    	<tbody>
    	  <c:if test="${not empty list}">
    	  	<c:forEach items="${list}" var="obj">
    		<tr>
    			<td align="center">${obj.userRealName}</td>
    			<td align="center"><fmt:formatDate value="${obj.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    			<td align="center"><fmt:formatNumber value="${obj.tradeMoney}" pattern="0.00" maxFractionDigits="2"/> 元</td>
    			<td>
    			利息：<fmt:formatNumber value="${obj.addInvestProfit*100 }" pattern="0.000"/>%<br/>
    			<span class="text-tit pull-left">天数：</span>${obj.addInvestProfitDays} 天
    			</td>
    			<td>
    			证件类型：${obj.getIdTypeDesc()}<br/>
    			<span class="text-tit pull-left">证件号：</span>${obj.idNumber}
    			</td>
    			<td align="center">${obj.phoneNumber}</td>
    			<td align="center">${obj.cardAccount}</td>
    		</tr>
    		</c:forEach>
    	  </c:if>
    		
    	</tbody>
    </table> 
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>


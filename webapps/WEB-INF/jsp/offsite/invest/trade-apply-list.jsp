<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--金融资产转让线下支付凭证到款操作 -->
<div class="bjui-pageHeader"  id="bidlist-payoffline-cfm-header">
<table class="table table-condensed table-hover" width="100%">
        <tbody>
            <tr>
                <td>
                    <label class="control-label x120">挂牌名称：</label>
                    <span>${listingInfo.listingFullName}</span>
                </td>
                <td>
                    <label class="control-label x120">挂牌规模：</label>
                    <span><fmt:formatNumber value="${listingInfo.listingMoney}" pattern="0.00" maxFractionDigits="2"/> 元</span>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label x120">已登记总额：</label>
                    <span><fmt:formatNumber value="${listingInfo.listingMoney}" pattern="0.00" maxFractionDigits="2"/>元</span>
                </td>
                <td>
                    <label class="control-label x120">已登记数量：</label>
                    <span></span>
                </td>
            </tr>
   	</tbody>
  </table>
</div>
<div class="bjui-pageContent" id="bidlist-payoffline-cfm-list">
 <table data-toggle="tablefixed" data-width="100%" data-height="300">
    	<thead>
    	<tr>
    		<th align="center" width="15%">序号</th>
    		<th align="center" width="20%">登记总额</th>
    		<th align="center" width="20%">登记数量</th>
    		<th align="center" width="15%">登记人数</th>
    		<th align="center" width="15%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    	  <c:if test="${not empty list}">
    	  	<c:forEach items="${list}" var="obj" varStatus="status">
    		<tr>
    			<td>${obj.transferBankName}</td>
    			<td>${obj.paymentSeq}</td>
    			<td>${obj.transferAccount}</td>
    			<td>${obj.confirmMoney}元</td>
    			<td align="center">
    				<fmt:formatDate value="${obj.confirmTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
    		</tr>
    		</c:forEach>
    	  </c:if>
    		
    	</tbody>
    </table> 
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>


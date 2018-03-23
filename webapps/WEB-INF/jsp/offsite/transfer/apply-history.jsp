<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent">
	<table class="table table-condensed table-hover">
		<tr>
			<td>
				<label class="control-label x100">挂牌代码：</label> 
				<span>${project.projectCode}</span>
			</td>
			<td colspan="3">
				<label class="control-label x100">挂牌名称：</label> 
				<span>${project.projectFullName }</span>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label x100">业务类型：</label> 
				<span>${project.getProductTypeDesc()}</span>
			</td>
			<td>
				<label class="control-label x100">确权总额：</label> 
				<span>${project.cfmMoney } 元</span>
			</td>
			<td>
				<label class="control-label x100">到期日：</label> 
				<span><fmt:formatDate value="${project.expireDate}" pattern="yyyy-MM-dd"/></span>
			</td>
			<td>
				<label class="control-label x100">登记成功数：</label> 
				<span>${project.successApplyNum } 次</span>
			</td>
		</tr>
	</table>
    <table data-toggle="tablefixed">
    	<thead>
    	<tr>
    		<th align="center" width="6%">序号</th>
    		<th align="center" width="20%">转让时间段</th>
    		<th align="right" width="16%">转让总金额(元)</th>
    		<th align="center" width="10%">转让客户数</th>
    		<th align="center" width="10%">受让客户数</th>
    		<th align="center" width="18%">申请提交时间</th>
    		<th align="center" width="8%">登记状态</th>
    		<th align="center" width="12%">操作</th>
    	</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="list" items="${historyList}" varStatus="status"> 
		    	<tr data-id="${ status.index + 1}" align="center">
		    		<td>${ status.index + 1}</td>
	    			<td>
	    				<span style="display:inline-block;vertical-align:middle;"><fmt:formatDate value="${list.startDate}" pattern="yyyy-MM-dd"/><br><fmt:formatDate value="${list.startDate}" pattern="HH:mm:ss"/></span>
		    			~
		    		    <span style="display:inline-block;vertical-align:middle;"><fmt:formatDate value="${list.endDate}" pattern="yyyy-MM-dd"/><br><fmt:formatDate value="${list.endDate}" pattern="HH:mm:ss"/></span>
	    			</td>
		    		<td align="right"><fmt:formatNumber value="${list.totalMoney }" pattern="#,##0.00"/></td>
		    		<td>${list.sellerNum }</td>
		    		<td>${list.buyerNum }</td>
		    		<td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    		<td>
		    			<c:choose>
			    			<c:when test="${list.applyStatus.status == 9 }"><a href="javascript:;" title="点击查看原因" data-toggle="alertmsg"  data-title="登记失败原因" data-type="info" data-msg="${list.bizimportApply.auditRemark eq null?' ':list.bizimportApply.auditRemark}" data-ok-name="关闭" data-auto-close="false" >${list.bizimportApply.applyStatus }</a></c:when>
			    			<c:otherwise>${list.bizimportApply.applyStatus }</c:otherwise>
			    		</c:choose>
		    		</td>
		    		<td><a href="<%=request.getContextPath()%>/offsite/transfer/view_apply.do?applyId=${list.bizimportApply.id}"
    				data-toggle="dialog" data-width="1000"  data-height="600" data-id="dialog-mask"
    				data-mask="true">查看登记结果</a></td>
	    		</tr>
    		</c:forEach>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
<script>
function showUpload1(id){
	$('tr[data-id='+id+']').find('div').removeAttr('data-disabled',false);
}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent">
		 <table class="table table-condensed table-noborder table-hover">
		 	<thead>
		 	</thead>
		 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x110">业务类型：</label> 
						<span>收益权转让计划</span>
					</td>
					<td>
						<label class="control-label x110">产品类型：</label> 
						<span><c:forEach var="item" items="${systypeProjectList}" varStatus="status">
								<c:if test="${item.id==obj.projectTypeId}">${item.typeName}</c:if>
							</c:forEach></span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备案代码：</label> 
						<span>${obj.recordCode}</span>
					</td>
					<td>
						<label class="control-label x110">发行人：</label> 
						<span>${loanUserName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备案名称：</label> 
						<span>${obj.recordFullName}</span>
					</td>
					<td>
						<label class="control-label x110">备案简称：</label> 
						<span>${obj.recordName}</span>
					</td>
				</tr>
			<!-- </tbody>
			<thead>
		 		<tr><th colspan="2">产品基本情况</th></tr>
		 	</thead>
		 	<tbody> -->
				<tr>
					<td>
						<label class="control-label x110">拟融资规模：</label> 
						<span><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/> 元</span>
					</td>
					<td>
						<label class="control-label x110">产品拟定期限：</label> 
						<span>${obj.projectLimit} <c:forEach var="item" items="${projectLimitTypeList}">
							<c:if test="${item.type==obj.projectLimitTypeId}">${item}</c:if>
							</c:forEach></span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">拟定年化收益率：</label> 
						<span><fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/> %</span>
					</td>
					<td>
						<label class="control-label x110">拟定还款方式：</label> 
						<span><c:forEach var="item" items="${systypeRepayList}" varStatus="status">
								<c:if test="${item.id==obj.repayTypeId}">${item.repayTypeName}</c:if>
							</c:forEach></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">增信说明：</label>
						<span>${obj.creditNote}</span> 
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">基础资产说明：</label> 
						<span>${content.basicAssetNote}</span>
					</td>
				</tr>
			</tbody>
		 </table>
</div>

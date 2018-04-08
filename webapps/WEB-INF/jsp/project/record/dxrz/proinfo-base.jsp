<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent tableContent">
		 <table class="table table-condensed table-noborder table-hover">
		 	<thead>
		 	</thead>
		 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x110">业务类型：</label> 
						<span>定向融资计划</span>
					</td>
					<td>
						<label class="control-label x110">备案编号：</label> 
						<span>${obj.recordCode}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">发行人：</label> 
						<span>${loanUserName}</span>
					</td>
					<td>
						<label class="control-label x110">备案名称：</label> 
						<span>${obj.recordFullName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备案简称：</label> 
						<span>${obj.recordName}</span>
					</td>
					<td>
					</td>
				</tr>
			</tbody>
			<thead>
		 		<tr><th colspan="2">备案信息</th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">备案金额：</label> 
						<span><fmt:formatNumber value="${obj.projectMoney}" pattern="#,##0.00"/> 元</span>
					</td>
					<td>
						<label class="control-label x110">拟定期限：</label> 
						<span>${obj.projectLimit}<c:forEach var="item" items="${projectLimitTypeList}">
							<c:if test="${item.type==obj.projectLimitType}">${item.typeName}</c:if>
							</c:forEach></span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">拟定年化收益率：</label> 
						<span><fmt:formatNumber value="${obj.investProfit*100}" pattern="0.000"/> %</span>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">资金投向：</label> 
						<span>${obj.projectUsing}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">增信说明：</label> 
						<span>${obj.creditNote}</span>
					</td>
				</tr>
			</tbody>
			<thead>
		 		<tr><th colspan="2">发行人评级信息</th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">评级机构名称：</label> 
						<span>${obj.ratingOrgName}</span>
					</td>
					<td>
						<label class="control-label x110">主体信用级别：</label> 
						<span>${obj.creditRating}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">债项级别：</label>
						<span>${obj.debtLevel}</span> 
					</td>
					<td>
					</td>
				</tr>
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">主承销商指定联系人</th></tr>
		 	</thead>
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">姓名：</label> 
						<span>${obj.agentLinkman.realName}</span> 
					</td>
					<td>
						<label class="control-label x110">联系电话：</label> 
						<span>${obj.agentLinkman.phone}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">联系邮箱：</label> 
						<span>${obj.agentLinkman.email}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">联系地址：</label> 
						<span>${obj.agentLinkman.address}</span>
					</td>
				</tr>
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">发行人指定联系人</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">姓名：</label> 
						<span>${obj.loanLinkman.realName}</span> 
					</td>
					<td>
						<label class="control-label x110">联系电话：</label> 
						<span>${obj.loanLinkman.phone}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">联系邮箱：</label> 
						<span>${obj.loanLinkman.email}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">联系地址：</label> 
						<span>${obj.loanLinkman.address}</span>
					</td>
				</tr>
		 	</tbody>
		 </table>
</div>

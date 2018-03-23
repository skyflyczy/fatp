<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 维护管理员，只支持一个-->
<div class="bjui-pageContent tableContent">
	 <table class="table table-condensed table-hover" width="100%">
		 	<thead>
		 		<tr><th colspan="2">设置/重设管理员(一个顾问暂时只能开通一个管理员)</th></tr>
		 	</thead>
			<tr>
				<td colspan="2">
					<label class="control-label x130">管理员登录名：</label> 
					${operator.loginName}
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">是否开设业务权限：</label> 
					<c:choose><c:when test="${operator.operatorType==3}">是</c:when><c:otherwise>否</c:otherwise></c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">性别：</label> 
					<input type="radio" name="gender" value="1" <c:if test="${empty operator.gender || operator.gender==1 }">checked</c:if> data-toggle="icheck" data-label="男" disabled>
                   	<input type="radio" name="gender" value="2" <c:if test="${not empty operator.gender && operator.gender==2 }">checked</c:if> data-toggle="icheck" data-label="女" disabled>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">手机号：</label> 
					${operator.phone }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">真实姓名：</label> 
					${operator.realName }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">证件类型：</label> 
					<c:forEach var="idType" items="${idTypeList}">
                       	<c:if test="${idType.typeId==operator.idType}">${idType.typeName }</c:if>
                      	</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">证件号码：</label> 
					${operator.idNumber }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">email：</label> 
					${operator.email }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">职务：</label> 
					${operator.duty }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">所属部门：</label> 
					${operator.department }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x130">备注说明：</label> 
					${operator.remark }
				</td>
			</tr>
	 </table>
</div>

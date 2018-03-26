<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		function validPhone(element){
			if($("#phoneval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/validphone.do",
					{"phone":element.value},
					function(data){
					}
				);
		}
		
		function validIdNumber(element){
			if($("#idTypeval").val() == $("#idType").val() && $("#idNumberval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/valididnumber.do",
					{"idNumber":element.value,
					"idType" : $("#idType").val()
					},
					function(data){
					}
				);
		}
		
		function validEmail(element){
			if($("#emailval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/validemail.do",
					{"email":element.value},
					function(data){}
				);
		}
		function checkIdNumber(element){
			if($("#idNumber").val() != ""){
				$("#idNumber").trigger("validate");
			}
		}
		
		function updatecallback(json){
			if(json.statusCode == 200){
				$(this).dialog("refresh")
			}else{
				$(this).bjuiajax('ajaxDone', json)
			}
		}
</script>
<!-- 增加操作员 -->
<div class="bjui-pageContent">
	<form id="mainForm" action="<%=request.getContextPath() %>/member/operator/updateMyinfo.do" data-toggle="validate" data-confirm-msg="" data-callback="updatecallback" data-loadingmask="false">
		 <input type="hidden" name="id" value="${memberOperator.id}"/>
		 <input type="hidden" name="operatorType" value="${memberOperator.operatorType }">
		 <input type="hidden" name="memberId" value="${memberOperator.memberId }">
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">操作员Id：</label> 
						<span>${memberOperator.id}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">操作员登录名：</label> 
						<span>${memberOperator.loginName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">操作人手机号：</label> 
						<input type="hidden" id="phoneval" value="<c:out value='${memberOperator.phone}'/>">
						<input type="text" name="phone" value="<c:out value='${memberOperator.phone}'/>" data-rule="required;mobile;validPhone" data-rule-validPhone="validPhone">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">操作人email：</label> 
						<input type="hidden" id="emailval" value="<c:out value='${memberOperator.email}'/>">
						<input type="text" name="email" value="<c:out value='${memberOperator.email}'/>" data-rule="required;email;validEmail" data-rule-validEmail="validEmail">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">真实姓名：</label> 
						<input type="text" name="realName" value="<c:out value='${memberOperator.realName}'/>" data-rule="required" >
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">性别：</label> 
						<input type="radio" name="gender" value="1" <c:if test="${empty memberOperator.gender || memberOperator.gender==1 }">checked</c:if> data-toggle="icheck" data-label="男">
                    	<input type="radio" name="gender" value="2" <c:if test="${not empty memberOperator.gender && memberOperator.gender==2 }">checked</c:if> data-toggle="icheck" data-label="女">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">证件类型：</label> 
						<input type="hidden" id="idTypeval" value="<c:out value='${memberOperator.idType}'/>">
						<select name="idType" id="idType" data-val="${memberOperator.idType}" data-toggle="selectpicker" data-rule="checkIdNumber" data-rule-checkIdNumber="checkIdNumber">
                        	<c:forEach var="idType" items="${idTypeList}">
                        	<option value="${idType.idType }" <c:if test="${idType.idType==memberOperator.idType}">selected="selected"</c:if>>${idType }</option>	
                        	</c:forEach>
                        </select>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">证件号码：</label> 
						<input type="hidden" id="idNumberval" value="<c:out value='${memberOperator.idNumber}'/>">
						<input type="text" id="idNumber" name="idNumber" value="<c:out value='${memberOperator.idNumber}'/>" data-rule="required;validIdNumber" data-rule-validIdNumber="validIdNumber">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">职务：</label> 
						<input type="text" name="duty" value="<c:out value='${memberOperator.duty}'/>"  >
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">所属部门：</label> 
						<input type="text" name="department"  value="<c:out value='${memberOperator.department}'/>">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备注说明：</label> 
						<textarea cols="30" rows="4" data-toggle="autoheight" name="remark"><c:out value='${memberOperator.remark}'/></textarea>
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button class="btn btn-blue">保存</button></li>
    </ul>
</div>
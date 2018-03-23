<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		
		function validPhone(element){
			if($("#phoneval").val() == element.value)
				return true;
			return $.post( "${ctx}/member/operator/validphone.do",
					{"phone":element.value},
					function(data){
					}
				);
		}
		
		function validIdNumber(element){
			if($("#idNumberval").val() == element.value && $("#idTypeval").val() == $("#idType").val())
				return true;
			return $.post( "${ctx}/member/operator/valididnumber.do",
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
			return $.post( "${ctx}/member/operator/validemail.do",
					{"email":element.value},
					function(data){}
				);
		}
		
		function updatecallback(json){
			if(json.statusCode == 200){
				$(this).dialog("closeCurrent")
					   .navtab('refresh');
			}else{
				$(this).bjuiajax('ajaxDone', json)
			}
		}
		function preUpdate(){
			var operatorType = $("#select1 :selected").val();
			if(operatorType == $("#operatorType").val()){
				$("#mainForm").attr("data-confirm-msg","确定要提交吗？");
			}else{
				$("#mainForm").attr("data-confirm-msg","操作员类型改变，需要重新设置角色，确定要提交吗？");
			}
		}
</script>
<!-- 增加操作员 -->
<div class="bjui-pageContent">
	<form id="mainForm" action="${ctx}/member/operator/update.do" data-toggle="validate" data-confirm-msg=""  data-callback="updatecallback">
		 <input type="hidden" name="id" value="${memberOperator.id}"/>
		 <input type="hidden" name="id" value="${memberOperator.id}"/>
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作员登录名：</label> 
						<span>${memberOperator.loginName}</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作人手机号：</label> 
						<input type="hidden" id="phoneval" value="<c:out value='${memberOperator.phone}'/>">
						<input type="text" name="phone" value="<c:out value='${memberOperator.phone}'/>" data-rule="required;mobile;validPhone" data-rule-validPhone="validPhone">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作人email：</label> 
						<input type="hidden" id="emailval" value="<c:out value='${memberOperator.email}'/>">
						<input type="text" name="email" value="<c:out value='${memberOperator.email}'/>" data-rule="required;email;validEmail" data-rule-validEmail="validEmail">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">真实姓名：</label> 
						<input type="text" name="realName" value="<c:out value='${memberOperator.realName}'/>" data-rule="required" >
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">性别：</label> 
						<input type="radio" name="gender" value="1" <c:if test="${empty memberOperator.gender || memberOperator.gender==1 }">checked</c:if> data-toggle="icheck" data-label="男">
                    	<input type="radio" name="gender" value="2" <c:if test="${not empty memberOperator.gender && memberOperator.gender==2 }">checked</c:if> data-toggle="icheck" data-label="女">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">证件类型：</label> 
						<input type="hidden" id="idTypeval" value="<c:out value='${memberOperator.idType}'/>">
						<select name="idType" id="idType" data-val="${memberOperator.idType}" data-toggle="selectpicker" data-rule="validate(idNumber)">
                        	<c:forEach var="idType" items="${idTypeList}">
                        	<option value="${idType.typeId }" <c:if test="${idType.typeId==memberOperator.idType}">selected="selected"</c:if>>${idType.typeName }</option>	
                        	</c:forEach>
                        </select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">证件号码：</label> 
						<input type="hidden" id="idNumberval" value="<c:out value='${memberOperator.idNumber}'/>">
						<input type="text" name="idNumber" value="<c:out value='${memberOperator.idNumber}'/>" data-rule="required;validIdNumber" data-rule-validIdNumber="validIdNumber">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作员类型：</label> 
						<input type="hidden" id="operatorType" value="${memberOperator.operatorType}">
						<select name="operatorType" id="select1" data-toggle="selectpicker" class="show-tick" style="display: none;">
							<option value="1" <c:if test="${memberOperator.operatorType == 1 }"> selected="selected" </c:if>>业务人员</option>
							<option value="2" <c:if test="${memberOperator.operatorType == 2 }"> selected="selected" </c:if>>系统管理员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">职务：</label> 
						<input type="text" name="duty" value="<c:out value='${memberOperator.duty}'/>"  >
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">所属部门：</label> 
						<input type="text" name="department"  value="<c:out value='${memberOperator.department}'/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">备注说明：</label> 
						<textarea cols="30" rows="4" data-toggle="autoheight" name="remark"><c:out value='${memberOperator.remark}'/></textarea>
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close">关闭</button></li>
        <li><button class="btn btn-blue" onclick="preUpdate();">保存</button></li>
    </ul>
</div>
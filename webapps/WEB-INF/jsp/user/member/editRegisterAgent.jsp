<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		
		function validAgentPhone(element){
			return $.post( "<%=request.getContextPath() %>/member/operator/validphone.do",
					{"phone":element.value,"memberId":${memberId }},
					function(data){
						$("#agentloginName").val(element.value);
					}
				);
		}
		
		function validAgentIdNumber(element){
			if($("#agentIdTypeval").val() == $("#idType").val() && $("#agentIdNumberval").val() == element.value){
				return true;
			}
			return $.post( "<%=request.getContextPath() %>/member/operator/valididnumber.do",
					{"idNumber":element.value,
					 "idType" : $("#idType").val(),"memberId":${memberId }
					},
					function(data){
					}
				);
		}
		
		function validAgentEmail(element){
			if($("#agentEmailval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/validemail.do",
					{"email":element.value,"memberId":${memberId }},
					function(data){}
				);
		}
		
		function checkAgentIdNumber(element){
			if($("#idNumber").val() != ""){
				$("#idNumber").trigger("validate");
			}
		}
		
		function editAgentCallBack(json){
			if(json.statusCode == 200){
				$(this).bjuiajax('refreshLayout', {target:'#agent',url:$("#agent_link").attr('href')});
			}else{
				$(this).bjuiajax('ajaxDone', json);
			}
			
		}
		
</script>
<!-- 经办人-->
<div class="bjui-pageContent">
	<form action="<%=request.getContextPath() %>/member/operator/editregisteragent.do" data-toggle="validate" data-callback="editAgentCallBack" data-loadingmask="false">
		 <table class="table table-condensed table-hover" width="100%">
			 	<thead>
			 		<tr><th colspan="2">经办人</th></tr>
			 	</thead>
				<tr>
					<td colspan="2">
						<input type="hidden" value="1" name="isRegisterAgent">
						<input type="hidden" value="1" name="isRegisterAgent">
						<label class="control-label x130">经办人登录名：</label> 
						<c:if test="${operator.loginName != null }">${operator.loginName }
							<input type="hidden" name="id" value="${operator.id }" >
						</c:if>
						<c:if test="${operator.loginName == null }">
							<input type="text" name="loginName" id="agentloginName" value="" readonly>
						</c:if>
						<input type="hidden" name="memberId" value="${memberId }"  data-rule="required" >
						<input type="hidden" id="loginNameval" value="${operator.loginName}">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>手机号：</label> 
						<c:if test="${operator.phone == null }"><input type="text" name="phone" value="${operator.phone }" data-rule="required;mobile;validAgentPhone" data-rule-validAgentPhone="validAgentPhone" maxlength="11"></c:if>
						<c:if test="${operator.phone != null }">${operator.phone }</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>性别：</label> 
						<input type="radio" name="gender" value="1" <c:if test="${empty operator.gender || operator.gender==1 }">checked</c:if> data-toggle="icheck" data-label="男">
                    	<input type="radio" name="gender" value="2" <c:if test="${not empty operator.gender && operator.gender==2 }">checked</c:if> data-toggle="icheck" data-label="女">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>真实姓名：</label> 
						<input type="text" name="realName" value="<c:out value='${operator.realName }'/>" data-rule="required">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>证件类型：</label> 
						<input type="hidden" id="agentIdTypeval" value="<c:out value='${operator.idType}'/>">
						<select name="idType" id="idType" data-val="${operator.idType}" data-toggle="selectpicker" data-rule="checkAgentIdNumber" data-rule-checkAgentIdNumber="checkAgentIdNumber">
                        	<c:forEach var="idType" items="${idTypeList}">
                        	<option value="${idType.typeId }" <c:if test="${idType.typeId==operator.idType}">selected="selected"</c:if>>${idType.typeName }</option>	
                        	</c:forEach>
                        </select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>证件号码：</label> 
						<input type="hidden" id="agentIdNumberval" value="<c:out value='${operator.idNumber}'/>">
						<input type="text" id="idNumber" name="idNumber" value="<c:out value='${operator.idNumber }'/>"  data-rule="required;validAgentIdNumber" data-rule-validAgentIdNumber="validAgentIdNumber">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>email：</label> 
						<input type="hidden" id="agentEmailval" value="<c:out value='${operator.email}'/>">
						<input type="text" name="email" value="<c:out value='${operator.email }'/>"  data-rule="required;email;validAgentEmail" data-rule-validAgentEmail="validAgentEmail">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">职务：</label> 
						<input type="text" name="duty"  value="<c:out value='${operator.duty }'/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">所属部门：</label> 
						<input type="text" name="department"  value="<c:out value='${operator.department }'/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">备注说明：</label> 
						<textarea cols="30" rows="4" data-toggle="autoheight" name="remark" ><c:out value='${operator.remark }'/></textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
		    			<button class="btn btn-blue">保存</button>
		    			<button type="button" class="btn-close">关闭</button>
					</td>
				</tr>
		 </table>
	</form>
</div>

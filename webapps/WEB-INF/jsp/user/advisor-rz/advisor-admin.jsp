<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		var isRegisterAgent = '${operator.isRegisterAgent}' ;
		function validLoginName(element){
			if(isRegisterAgent != 'true' && $("#loginNameval").val() == element.value){
				return true;
			}
			return $.post( "<%=request.getContextPath() %>/member/operator/validloginname.do",
					{"loginName":element.value,"memberId":${memberId }},
					function(data){
					}
				);
		}
		
		function validPhone(element){
			if(isRegisterAgent != 'true' && $("#phoneval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/validphone.do",
					{"phone":element.value,"memberId":${memberId }},
					function(data){
						$("#loginName").val(element.value);
					}
				);
		}
		
		function validIdNumber(element){
			if(isRegisterAgent != 'true' && $("#idTypeval").val() == $("#idType").val() && $("#idNumberval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/valididnumber.do",
					{"idNumber":element.value,
					 "idType" : $("#idType").val(),"memberId":${memberId }
					},
					function(data){
					}
				);
		}
		
		function validEmail(element){
			if(isRegisterAgent != 'true' && $("#emailval").val() == element.value)
				return true;
			return $.post( "<%=request.getContextPath() %>/member/operator/validemail.do",
					{"email":element.value,"memberId":${memberId }},
					function(data){}
				);
		}
		
		function checkIdNumber(element){
			if($("#idNumber").val() != ""){
				$("#idNumber").trigger("validate");
			}
		}
		
		function editAdminCallBack(json){
			if(json.statusCode == 200){
				$(this).alertmsg("correct",json.message);
				$(this).bjuiajax('refreshLayout', {target:'#operator',url:$("#operator_link").attr('href')});
			}else{
				$(this).bjuiajax('ajaxDone', json);
			}
			
		}
		$(function(){
			if(isRegisterAgent == 'true') {
				$('.admin').attr("disabled","disabled");
				$('.adminGender').iCheck('disable');
			}
			$('#isRegisterAgent').on('ifChecked',function(){
				$('.admin').attr("disabled","disabled");
				$('select.admin').selectpicker('refresh');
				$('.adminGender').iCheck('disable');
			})
			$('#isRegisterAgent').on('ifUnchecked',function(){
				$('.admin').removeAttr("disabled");
				$('.adminGender').iCheck('enable');
			})
		});
</script>
<!-- 维护管理员，只支持一个-->
<div class="bjui-pageContent">
	<form action="<%=request.getContextPath() %>/member/operator/advisor-rz/edit-advisor-admin.do" data-toggle="validate" data-callback="editAdminCallBack" data-loadingmask="false">
		 <table class="table table-condensed table-hover" width="100%">
		 	<thead>
		 		<tr><th colspan="2">设置/重设管理员(一个顾问暂时只能开通一个管理员)</th></tr>
		 	</thead>
		 	<tbody>
			 	<tr>
					<td colspan="2">
						<label class="control-label x130">设置经办人为管理员：</label> 
						<input type="checkbox" id="isRegisterAgent" name="isRegisterAgent" data-toggle="icheck" data-label="" <c:if test="${operator.isRegisterAgent == true }">checked</c:if>>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>手机号：</label> 
						<input type="hidden" id="phoneval" value="${operator.phone}">
						<input class="admin" type="text" name="phone" value="${operator.phone }" data-rule="required;mobile;validPhone" data-rule-validPhone="validPhone"  maxlength="11">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>管理员登录名：</label> 
						<c:if test="${operator.loginName != null }">
						<input class="admin" type="text" id="loginName" name="loginName" value="<c:out value='${operator.loginName }'/>" data-rule="required;validLoginName" data-rule-validLoginName="validLoginName" >
						<input type="hidden" name="id" value="${operator.id }" ></c:if>
						<c:if test="${operator.loginName == null }">
						<input class="admin" type="text" name="loginName" id="loginName" value=""  data-rule="required;validLoginName" data-rule-validLoginName="validLoginName" ></c:if>
						<input type="hidden" name="memberId" value="${memberId }"  data-rule="required" >
						<input type="hidden" id="loginNameval" value="${operator.loginName}">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>性别：</label> 
						<input type="radio" class="adminGender" name="gender" value="1" <c:if test="${empty operator.gender || operator.gender==1 }">checked</c:if> data-toggle="icheck" data-label="男">
                    	<input type="radio" class="adminGender" name="gender" value="2" <c:if test="${not empty operator.gender && operator.gender==2 }">checked</c:if> data-toggle="icheck" data-label="女">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>真实姓名：</label> 
						<input class="admin" type="text" name="realName" value="<c:out value='${operator.realName }'/>" data-rule="required">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>证件类型：</label> 
						<input type="hidden" id="idTypeval" value="<c:out value='${operator.idType}'/>">
						<select class="admin" name="idType" id="idType" data-val="${operator.idType}" data-toggle="selectpicker" data-rule="checkIdNumber" data-rule-checkIdNumber="checkIdNumber">
                        	<c:forEach var="idType" items="${idTypeList}">
                        	<option value="${idType.idType }" <c:if test="${idType.idType==operator.idType}">selected="selected"</c:if>>${idType }</option>	
                        	</c:forEach>
                        </select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>证件号码：</label> 
						<input type="hidden" id="idNumberval" value="<c:out value='${operator.idNumber}'/>">
						<input class="admin" type="text" id="idNumber" name="idNumber" value="<c:out value='${operator.idNumber }'/>"  data-rule="required;validIdNumber" data-rule-validIdNumber="validIdNumber">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130"><span class="red">*</span>email：</label> 
						<input type="hidden" id="emailval" value="<c:out value='${operator.email}'/>">
						<input class="admin" type="text" name="email" value="<c:out value='${operator.email }'/>"  data-rule="required;email;validEmail" data-rule-validEmail="validEmail">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">职务：</label> 
						<input class="admin" type="text" name="duty"  value="<c:out value='${operator.duty }'/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">所属部门：</label> 
						<input class="admin" type="text" name="department"  value="<c:out value='${operator.department }'/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x130">备注说明：</label> 
						<textarea class="admin" cols="30" rows="4" data-toggle="autoheight" name="remark" ><c:out value='${operator.remark }'/></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button class="btn btn-blue">保存</button>
						<button type="button" class="btn-close">关闭</button>
					</td>
				</tr>
			</tbody>
		 </table>
	</form>
</div>

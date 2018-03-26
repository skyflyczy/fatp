<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		function validLoginName(element){
			return $.post( "${ctx}/member/operator/validloginname.do",
					{"loginName":element.value},
					function(data){
					}
				);
		}
		
		function validPhone(element){
			return $.post( "${ctx}/member/operator/validphone.do",
					{"phone":element.value},
					function(data){
					}
				);
		}
		
		function validIdNumber(element){
			return $.post( "${ctx}/member/operator/valididnumber.do",
					{"idNumber":element.value,
					 "idType" : $("#idType").val()
					},
					function(data){
					}
				);
		}
		
		function validEmail(element){
			return $.post( "${ctx}/member/operator/validemail.do",
					{"email":element.value},
					function(data){}
				);
		}
		
		function formcallback(json){
			if(json.statusCode == 200){
				$(this).dialog("closeCurrent")
					   .navtab('refresh');
			}else{
				$(this).bjuiajax('ajaxDone', json)
			}
		}
</script>
<!-- 增加操作员 -->
<div class="bjui-pageContent">
	<form id="mainForm" action="${ctx}/member/operator/save.do" data-toggle="validate" data-confirm-msg="确定要提交吗？" data-callback="formcallback">
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作员登录名：</label> 
						<input type="text" name="loginName" class="form-control" data-rule="required;validLoginName" data-rule-validLoginName="validLoginName">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作人手机号：</label> 
						<input type="text" name="phone" class="form-control" data-rule="required;mobile;validPhone" data-rule-validPhone="validPhone">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作人email：</label> 
						<input type="text" name="email" class="form-control" data-rule="required;email;validEmail" data-rule-validEmail="validEmail">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">真实姓名：</label> 
						<input type="text" name="realName" class="form-control" data-rule="required" >
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">性别：</label> 
						<input type="radio" name="gender" value="1" checked data-toggle="icheck" data-label="男">
                    	<input type="radio" name="gender" value="2" data-toggle="icheck" data-label="女">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">证件类型：</label> 
						<select name="idType" id="idType" data-val="${operator.idType}" data-toggle="selectpicker" data-rule="validate(idNumber)">
                        	<c:forEach var="idType" items="${idTypeList}">
                        	<option value="${idType.idType }" <c:if test="${idType.idType==operator.idType}">selected="selected"</c:if>>${idType }</option>	
                        	</c:forEach>
                        </select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">证件号码：</label> 
						<input type="text" name="idNumber"  class="form-control" data-rule="required;validIdNumber" data-rule-validIdNumber="validIdNumber">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">操作员类型：</label> 
						<select name="operatorType"  data-toggle="selectpicker">
							<option value="1">业务人员</option>
							<option value="2">系统管理员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">职务：</label> 
						<input type="text" name="duty"  class="form-control" >
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">所属部门：</label> 
						<input type="text" name="department" class="form-control" >
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x110">备注说明：</label> 
						<textarea cols="30" rows="4" data-toggle="autoheight" name="remark"></textarea>
					</td>
				</tr>
			</tbody>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue">保存</button></li>
    </ul>
</div>
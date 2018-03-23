<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		
		function validRoleName(element){
			return $.post( "${ctx}/sys/role/validrolename.do",
					{"roleName":element.value},
					function(data){
					}
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
<!-- 增加角色 -->
<div class="bjui-pageContent">
	<form action="${ctx}/sys/role/save.do" data-toggle="validate" data-confirm-msg="确定要提交吗？" data-callback="formcallback">
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">角色名称：</label> 
						<input type="text" name="roleName"  data-rule="required;validRoleName" data-rule-validRoleName="validRoleName">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">角色类型：</label>
						<select name="roleType"  data-toggle="selectpicker">
							<option value="1">管理角色</option>
							<option value="2">业务角色</option>
						</select>
					</td>
					
				</tr>
				
				<tr>
					<td>
						<label class="control-label x110">角色描述：</label> 
						<textarea cols="40" rows="4" name="roleDesc" data-toggle="autoheight" style="resize:none;"></textarea>
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <!-- onclick="saveRole()" -->
        <li><button type="submit" class="btn-blue" data-icon="save" >保存</button></li>
    </ul>
</div>

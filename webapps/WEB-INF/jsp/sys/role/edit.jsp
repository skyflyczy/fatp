<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		
		function validRoleName(element){
			if($("#roleNameVal").val() == element.value)
				return true;
			return $.post( "${ctx}/sys/role/validrolename.do",
					{"roleName":element.value},
					function(data){
					}
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
		
</script>
<!-- 编辑角色 -->
<div class="bjui-pageContent">
	<form action="${ctx}/sys/role/update.do" data-toggle="validate" data-confirm-msg="确定要提交吗？" data-callback="updatecallback">
		  <input type="hidden" name="id" value="${sysMemberRole.id}"/>
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">角色名称：</label> 
						<input type="hidden" id="roleNameVal" value="<c:out value='${sysMemberRole.roleName}'/>"/>
						<input type="text" name="roleName" value="<c:out value='${sysMemberRole.roleName}'/>" data-rule="required;validRoleName" data-rule-validRoleName="validRoleName" >
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">角色类型：</label> 
						<span>
							<c:if test="${sysMemberRole.roleType == 1}">管理角色</c:if>
							<c:if test="${sysMemberRole.roleType == 2}">业务角色</c:if>
						</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">角色描述：</label> 
						<textarea cols="30" rows="4" data-toggle="autoheight" name="roleDesc"><c:out value='${sysMemberRole.roleDesc}'/></textarea>
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-blue" data-icon="save">保存</button></li>
    </ul>
</div>

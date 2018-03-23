<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$('#modifypwdForm').on("valid.form",function(e, form){
	$.ajax({
		url: '<%=request.getContextPath()%>/modifyPwd.do',
		type: 'post',
		data: {k:$("#k").val(),oldpassword:$.md5($("#oldpassword").val()),password:$.md5($("#password").val())},
		cache: 'false',
		dataType: 'json',
		success: function(data) {
			if(data.statusCode == 200) {
				$(this).bjuiajax('ajaxDone', data).dialog("closeCurrent")
			}else {
				$(this).bjuiajax('ajaxDone', data)
			}
		}
	});
})
</script>
<!-- 设置密码 -->
<div class="bjui-pageContent">
	<form id="modifypwdForm" action="<%=request.getContextPath() %>/modifyPwd.do" data-confirm-msg="确定要提交吗？" >
	<input type="hidden" id="k" name="k" value="${k }">
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">当前密码：</label>
						<input type="password" class="hide"/> 
						<input type="password" name="oldpassword" id="oldpassword" data-rule="当前密码:required;"  data-timely="0" autocomplete="off" oncopy="return false" onpaste="return false">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">新密码：</label> 
						<input type="password" name="password" id="password" data-rule="新密码:required;password;match[neq, oldpassword]"  data-timely="0" autocomplete="off" oncopy="return false" onpaste="return false">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">确认密码：</label> 
						<input type="password" name="confirmpassword" data-rule="确认密码:required;match(password);password"  data-timely="0" autocomplete="off" oncopy="return false" onpaste="return false">
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue">保存</button></li>
    </ul>
</div>
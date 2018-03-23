<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<script type="text/javascript">
	//更新密码callback
	function updatePasswordCallback(json){
		if(json.statusCode == 200){
			$(this).bjuiajax('ajaxDone', json)
				   .dialog("closeCurrent")
				   .navtab('refresh');
		}else{
			$(this).bjuiajax('ajaxDone', json)
		}
	}
</script>
<!-- 设置密码 -->
<div class="bjui-pageContent">
	<form action="${ctx}/member/operator/updatepassword.do?id=${id}" data-toggle="validate" data-confirm-msg="确定要提交吗？" data-callback="updatePasswordCallback">
		 <table class="table table-condensed table-hover">
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">操作员登录名：</label> 
						<span>${loginName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">登录密码：</label>
						<input type="password" class="hide"/> 
						<input type="password" name="password" data-rule="密码:required" autocomplete="off" oncopy="return false" onpaste="return false">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">确认密码：</label> 
						<input type="password" name="confirmpassword" data-rule="确认密码:required;match(password)" autocomplete="off" oncopy="return false" onpaste="return false" >
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent"  id="pro-review">
	<form id="form" action="<%=request.getContextPath() %>/systype/saveprotocol.do" data-reload-navtab="true" data-toggle="validate" data-callback="">
		<input type="hidden" name="id" value="${systypeProject.id}">
		<table class="table-condensed" width="100%">
		 		<tr>
					<td>
						<label class="x130">协议类型：</label> 
						${systypeProject.typeName}-${productTypeName }
					</td>
				</tr>
				<tr>
					<td>
						<textarea data-toggle="kindeditor" style="width:100%" name="protocolText" maxlength="65535">${systypeProject.protocolText}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<label class="x130">文本说明：</label> 
						${systypeProject.protocolTextUsage}
					</td>
				</tr>
				<tr>
					<td align="center">
	    			<button type="submit" class="btn-blue">确定</button>
	    			<button type="button" class="btn-close" data-icon="close">取消</button>
					</td>
				</tr>
		 </table>
	</form>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function callback(json){
		if(json.statusCode == 200){
			$(this).alertmsg("correct", json.message);
			$(this).navtab("refresh");
		}else{
			$(this).alertmsg("error", json.message);
		}
	}
	function formcallback(json){
		if(json.statusCode == 200){
			$(this).alertmsg("correct", json.message).dialog('closeCurrent');
		}else{
			$(this).bjuiajax('ajaxDone', json)
		}
	}
	
	$(function(){
		$('#protocolText').height($('#protocolText').parent().outerHeight()-40)
		$(window).on('resize',function(){
			$('#protocolText').height($('#protocolText').parent().outerHeight()-40)
		})
	})
</script>
<div class="bjui-pageContent">
	<form style="height:100%" id="form" action="<%=request.getContextPath() %>/protocol/save.do"  data-reload-navtab="true" data-confirm-msg="确定要提交吗？" data-toggle="validate" data-callback="formcallback">
		<input type="hidden" name="id" value="${protocol.id}">
		<textarea data-toggle="kindeditor" style="width:100%;" data-filter-mode="false" data-autoHeightMode="true" id="protocolText" name="protocolText" maxlength="65535"  data-rule="required;">${protocol.agreementText}</textarea>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <c:if test="${protocolType==2}"><li><button type="button" class="btn-close" data-icon="close">关闭</button></li></c:if>
        <li><button type="submit" class="btn-blue">保存</button></li>
    </ul>
</div>
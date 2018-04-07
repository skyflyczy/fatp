<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent"  id="pro-review">
	<form id="form" action="<%=request.getContextPath()%>/project/record/check/check.do" data-reload-navtab="true" data-toggle="validate" data-confirm-msg="确定审核通过？" data-callback="projectupateCallback">
		<input type="hidden" name="id" value="${project.id}">
		<input type="hidden" name="todayForJudge" data-rule="当天:" value="${todayForJudge}">
		<input type="hidden" id="pass" name="pass" value="0">
		<table class="table-condensed" width="100%">
	 		<tr>
				<td>
					<label class="control-label x120">产品备案截止时间：</label> 
					<input data-rule="备案截止时间:date;match[gt, todayForJudge, date]" type="text" value="<fmt:formatDate value="${project.deadline}" pattern="yyyy-MM-dd"/>" data-min-date="%y-%M-{%d}" name="deadline" id="deadline" data-toggle="datepicker"  data-nobtn="true">
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label x120">审核意见：</label> 
					<textarea cols="45" rows="8" data-toggle="autoheight" data-rule="required;length[~600, true]" id="flowFeedOpinion" name="flowFeedOpinion" style="resize:none;"></textarea>
				</td>
			</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close">取消</button></li>
        <li><button type="submit" class="btn btn-orange" id="checkBackBtn">审核退回</button></li>
        <li><button type="submit" class="btn-blue" id="checkPassBtn">审核通过</button></li>
        <li><button type="submit" class="btn btn-orange" id="checkNotPassBtn">审核不通过</button></li>
    </ul>
</div>
<script>
$('#checkBackBtn').on('click',function(e){
	$("#form").on("valid.form", function(e, form) {
		$("#pass").val(0);
	    $(form).data("confirmMsg", "确定退回？").bjuiajax('ajaxForm', $(form).data())
	});
})
$('#checkPassBtn').on("click",function(){
	$("#form").on("valid.form", function(e, form) {
		$("#pass").val(1);
	    $(form).data("confirmMsg", "确定通过？").bjuiajax('ajaxForm', $(form).data())
	});
})
$('#checkNotPassBtn').on('click',function(e){
	$("#form").on("valid.form", function(e, form) {
		$("#pass").val(2);
	    $(form).data("confirmMsg", "确定不通过？").bjuiajax('ajaxForm', $(form).data())
	});
})
function projectupateCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message);
		$(this).dialog("closeCurrent");
		$(this).navtab("refresh");
	}else {
		$(this).alertmsg("error", json.message);
	}
}
</script>
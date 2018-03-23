<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 审核注册资料 -->
<div class="bjui-pageContent"  id="pro-review">
    <form id="form" action="<%=request.getContextPath()%>/user/member/audit_reg/submit.do" method="post" data-reload-navtab="true" data-toggle="validate" data-confirm-msg="确定审核通过？" data-callback="regAuditCallback">
        <input type="hidden" name="memberId" value="${memberId}">
        <input type="hidden" name="action" id="action" value="${action}">
        <table class="table-condensed" width="100%">
            <tr>
                 <td>
                     <label class="control-label x80">审核意见：</label>
                     <textarea cols="38" rows="8" data-toggle="autoheight" data-rule="required;length[~200, true]" id="regAuditComments" name="comments" style="resize:none;"></textarea>
                 </td>
            </tr>
		</table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button id="cancelButton" type="button" class="btn btn-close">取消</a></li>
        <li><button id="passButton" type="submit" class="btn-blue">确认</button></li>
    </ul>
</div>
<script>
$('#passButton').on("click", function(){
    $("#form").on("valid.form", function(e, form) {
        $(form).data("confirmMsg", "确定提交？").bjuiajax('ajaxForm', $(form).data())
    });
})
$('#cancelButton').on('click',function(e){
	$(this).dialog("closeCurrent");
});

function regAuditCallback(json) {
    if(json.statusCode == 200) {
        $(this).alertmsg("correct", json.message, {autoClose: true});
    }else {
        $(this).alertmsg("error", json.message, {autoClose: true});
    }
   $(this).dialog("closeCurrent");
   $(this).dialog("close", "dialog-mask-editPage");
   $(this).navtab("refresh");
    
}
</script>
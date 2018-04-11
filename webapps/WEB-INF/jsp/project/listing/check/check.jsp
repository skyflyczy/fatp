<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="bjui-pageContent"  id="pro-review">
	<form id="form" action="<%=request.getContextPath()%>/project/listing/check/check.do" data-reload-navtab="true" data-toggle="validate" data-confirm-msg="确定审核通过？" data-callback="projectupateCallback">
		<input type="hidden" name="id" value="${project.id}">
		<input type="hidden" id="pass" name="pass" value="0">
		<table class="table table-condensed table-hover">
			<tbody>
			<tr>
					<td>
						<label class="control-label x160">挂牌产品的预计到期日：</label>
						<span>
							<fmt:formatDate value="${project.expireDate }" pattern="yyyy-MM-dd"/>
						</span>
					</td>
					<td>
						<label class="control-label x160">挂牌产品的期限：</label>
						<span>${project.projectLimit }${project.getProjectLimitTypeDesc() }</span>
					</td>
				</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x160">起息日规则：</label> 
					<c:forEach var="item" items="${valueDateTypes}" varStatus="varStatus">
						<input <c:if test="${project.valueDateChangeStyle==item.type}">checked</c:if> id="valueDateChangeStyle${varStatus.count}" name="valueDateChangeStyle" type="radio" value="${item.type }" data-toggle="icheck" data-label="${item }" data-rule="checked">
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x160">到期日规则：</label> 
					<c:forEach var="item" items="${expireDateTypes}" varStatus="varStatus">
						<input <c:if test="${project.expireDateChangeStyle==item.type}">checked</c:if> id="expireDateChangeStyle0" name="expireDateChangeStyle" type="radio" value="${item.type }" data-toggle="icheck" data-label="${item }" data-rule="checked">
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x160">分次放款：</label> 
					<input id="multipleRelease0"<c:if test="${empty project.multipleRelease || project.multipleRelease==0}">checked</c:if> name="multipleRelease" type="radio" value="0" data-toggle="icheck" data-label="否" data-rule="checked" >
					<input id="multipleRelease1" <c:if test="${not empty project.multipleRelease && project.multipleRelease==1}">checked</c:if> name="multipleRelease" type="radio" value="1" data-toggle="icheck" data-label="是" data-rule="checked">
					最多可以放款 <input maxlength="10" type="text" name="maxReleaseNum" data-rule="integer[+]" <c:if test="${empty project.multipleRelease || project.multipleRelease==0}">disabled</c:if> id="maxReleaseNum" 
					<c:if test="${empty project.multipleRelease || project.multipleRelease==0}">value="0"</c:if>
					<c:if test="${not empty project.multipleRelease && project.multipleRelease==1}">value="${project.maxReleaseNum }"</c:if>   size="5" > 次 
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label x160">审核意见：</label> 
					<textarea cols="50" rows="5" data-toggle="autoheight" data-rule="required;length[~600, true]" id="flowFeedOpinion" name="auditRemark" style="resize:none;"></textarea>
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
        <li><button type="submit" class="btn-blue" id="checkNotPassBtn">审核不通过</button></li>
    </ul>
</div>
<script>
$('#checkBackBtn').on('click',function(e){
	$("#form").on("valid.form", function(e, form) {
		$("#pass").val(0);
	    $(form).data("confirmMsg", "确定退回？").bjuiajax('ajaxForm', $(form).data())
	});
})
$('#checkPassBtn').on("click", function(){
	$("#form").on("valid.form", function(e, form) {
		$("#pass").val(1);
	    $(form).data("confirmMsg", "确定审核通过？").bjuiajax('ajaxForm', $(form).data())
	});
})
$('#checkNotPassBtn').on("click", function(){
	$("#form").on("valid.form", function(e, form) {
		$("#pass").val(2);
	    $(form).data("confirmMsg", "确定审核不通过？").bjuiajax('ajaxForm', $(form).data())
	});
})
$('#multipleRelease0').on("ifChecked",function(){
	$('#maxReleaseNum').val(0);
	$('#maxReleaseNum').attr("disabled","disabled");
});
$('#multipleRelease1').on("ifChecked",function(){
	$('#maxReleaseNum').removeAttr("disabled");
});
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
</script>
<!-- 审核业务资格 -->
<div class="bjui-pageContent">
	<table>
		<tr>
			<td>
				<label class="control-label x110">申请类型：</label>
				<span>${applyType }</span>
			</td>
		</tr>
	</table>
	<table class="table table-condensed table-hover table-auto">
		<c:choose>
			<c:when test="${applyType == '新增'}">
				<thead>
					<tr>
						<th colspan="2">申请业务资格</th>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<c:forEach var="productType" items="${productTypes}">
							<input disabled type="checkbox" name="usertypes" value="${productType.id}" <c:if test="${productType.checked==1 }">checked</c:if>
								data-toggle="icheck" data-label="${productType.typeName}"
								data-rule="checked[1~]" data-autoClose="true">
                      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 		</c:forEach>
                 	</td>
				</tr>
				<c:if test="${bankCard != null }">
					<thead>
						<tr>
							<th colspan="2">银行账户</th>
						</tr>
					</thead>
					<tr>
						<td>
							<label class="control-label x110">账户名称：</label>
							<span>${bankCard.accountName }</span>
						</td>
						<td>
							<label class="control-label x110">账户号码：</label>
							<span>${bankCard.cardAccount }</span>
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">开户银行：</label>
							<span>${bankCard.channelName }</span>
						</td>
						<td>
							<label class="control-label x110">开户行所在地：</label>
							<span>${bankCard.provinceName }${bankCard.cityName }${bankCard.subBankName }</span>
						</td>
					</tr>
				</c:if>
			</c:when>
			<c:otherwise>
				<thead>
					<tr>
						<th colspan="2">已有业务资格</th>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<c:forEach var="bizType" items="${oldBizTypeList}">
							${bizType.productTypeName }
                      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 		</c:forEach>
					</td>
				</tr>
				<thead>
					<tr>
						<th colspan="2">新增业务资格</th>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<c:forEach var="bizType" items="${list}">
							<input disabled type="checkbox" checked data-label="${bizType.productTypeName}" data-toggle="icheck" data-rule="checked[1~]" data-autoClose="true">
                      		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 		</c:forEach>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div style="padding:10px;">
		<label><span class="red">*</span>审核意见</label>
		<textarea rows="5" cols="60" id="auditRemark" maxlength="100" style="resize:none;"></textarea>
	</div>
</div>
<div class="bjui-pageFooter">
 	<ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><a id="back" class="btn btn-orange">审核退回</a></li>
        <li><a id="pass" class="btn btn-blue">审核通过</a></li>
    </ul>
</div>
<script>
$("#pass").on("click", function(){
	var auditRemark = $("#auditRemark").val();
	if(auditRemark == "") {
		$(this).alertmsg("error","请填写审核意见");
		return;
	}
	var options = {url:'<%=request.getContextPath()%>/user/member/biztypeapply/audit.do',type:'post',data:{applyGuid:'${applyGuid}',memberId:'${memberId}',type:1,auditRemark:auditRemark},confirmMsg:'确定要审核通过吗？',callback:'callback'}
	$(this).bjuiajax('doAjax', options);
})
$("#back").on("click", function(){
	var auditRemark = $("#auditRemark").val();
	if(auditRemark == "") {
		$(this).alertmsg("error","请填写审核意见");
		return;
	}
	var options = {url:'<%=request.getContextPath()%>/user/member/biztypeapply/audit.do',type:'post',data:{applyGuid:'${applyGuid}',memberId:'${memberId}',type:2,auditRemark:auditRemark},confirmMsg:'确定要审核退回吗？',callback:'callback'}
	$(this).bjuiajax('doAjax', options);
})
function callback(json) {
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message, {autoClose:false,okCall:function(){$(this).dialog("closeCurrent");$(this).navtab("refresh");}});
	}else{
		$(this).alertmsg("error", json.message);
	}
}
</script>

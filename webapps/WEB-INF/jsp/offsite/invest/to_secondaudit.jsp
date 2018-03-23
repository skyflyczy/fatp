<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 初审页面 -->
<div class="bjui-pageContent">
	<form id="form" action="<%=request.getContextPath()%>/offsite/invest/audit.do" data-reload-navtab="true" data-toggle="validate" data-confirm-msg="确定审核通过？" data-callback="auditCallback">
		 <input type="hidden" name="applyStatus" value="" id="applyStatus">
		 <input type="hidden" name="applyGuid" value="${obj.applyGuid}">
		 <table class="table table-condensed table-noborder">
		 	<thead></thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x140">挂牌代码：</label> 
						<span>${obj.projectCode }</span>
					</td>
					<td>
						<label class="control-label x140">挂牌名称：</label> 
						<span>${obj.projectFullName }</span>
					</td>
					
				</tr>
		 		<tr>
		 			<td>
						<label class="control-label x140">募集总额：</label> 
						<span><fmt:formatNumber value="${obj.saleMoney }" pattern="#,##0.00#"/> 元</span>
					</td>
					<td>
						<label class="control-label x140">产品状态：</label> 
						<span>
							<c:forEach items="${projectStatusDesc }" var="item">
								<c:if test="${item.value == obj.projectStatus }">
								${item }
								</c:if>
							</c:forEach>
						</span>
					</td>
				</tr>
				
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">投资登记信息</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x140">起息日：</label> 
						<span><fmt:formatDate value="${obj.valueDate}" pattern="yyyy-MM-dd"/></span>
					</td>
					<td>
						<label class="control-label x140">上传总金额：</label> 
						<span><fmt:formatNumber value="${obj.uploadTotolMoney }" pattern="#,##0.00#"/> 元</span>
					</td>
				</tr>
		 		<tr>
		 			<td>
						<label class="control-label x140">上传条数：</label> 
						<span>${obj.uploadTotalNum } 条</span>
					</td>
					<td>
						<label class="control-label x140">上传人数：</label> 
						<span>${obj.uploadUserNum} 人</span>
					</td>
				</tr>
				<c:if test="${tradeImportResult.overBuyTime>0||tradeImportResult.overInvestLimit>0||tradeImportResult.lessBuy>0||obj.valueDate != oldValueDate}">
				<tr>
					<td colspan="2">
					<label class="control-label x40"></label><i class="fa fa-exclamation-triangle text-danger"></i>以下信息与挂牌信息不符，可能会导致登记失败，请再次确认
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2">
						<c:if test="${tradeImportResult.overBuyTime>0}">
							<label class="control-label x140">不在认购期内：</label> 
							<span>${tradeImportResult.overBuyTime } 条</span>
						</c:if>
						<%-- <c:if test="${tradeImportResult.overInvestLimit>0}">
							<label class="control-label x140">超过投资上限：</label> 
							<span>${tradeImportResult.overInvestLimit }条</span>
						</c:if> --%>
						<c:if test="${tradeImportResult.lessBuy>0}">
							<label class="control-label x140">小于起投金额：</label> 
							<span>${tradeImportResult.lessBuy } 条</span>
						</c:if>
						<c:if test="${tradeImportResult.notEqAppend>0}">
							<label class="control-label x140">追加金额不符：</label> 
							<span>${tradeImportResult.notEqAppend } 条</span>
						</c:if>
					</td>
				</tr>
				<c:if test="${obj.valueDate != oldValueDate }">
					<tr>
						<td colspan="2">
							<label class="control-label x140">已有起息日</label>：<fmt:formatDate value="${oldValueDate}" pattern="yyyy-MM-dd"/>
						</td>
					</tr
				</c:if>
				<c:if test="${tradeImportResult.getDuplicateMapSize()>0}">
					<tr style="display:none">
						<td colspan="2">
						<label class="control-label x140">重复数据：</label>
						<c:forEach items="${tradeImportResult.duplicateMap}" var="m">
						<span id="overBuyTime">第${m.value}条数据重复</span>
						</c:forEach>
						</td>
					</tr>
				</c:if>
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">相关附件</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td colspan="2">
						<table class="table other-table table-bordered">
							<thead>
								<tr>
									<th width="10%">序号</th>
									<th width="20%">文件类型</th>
									<th width="30%">文件名称</th>
									<th width="20%">创建时间</th>
									<th width="20%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${globalFileVos}" var="file" varStatus="status">
								<tr>
									<td align="center">${status.index + 1}</td>
									<td align="center">${file.fileType }</td>
									<td align="center">${file.displayName }</td>
									<td align="center"><fmt:formatDate value="${file.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td align="center">
										<c:choose>
											<c:when test="${file.contentType == 'xlsx' || file.contentType == 'xls' }"></c:when>
											<c:otherwise>
												<a target="_blank" href="<%=request.getContextPath()%>/offsite/base/view-apply-file.do?fileGuid=${file.fileGuid}&applyId=${obj.bizImportApplyId}">浏览</a>
											</c:otherwise>
										</c:choose>
							    		<a href="<%=request.getContextPath()%>/offsite/base/down-apply-file.do?fileGuid=${file.fileGuid}&applyId=${obj.bizImportApplyId}">下载</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
		 	</tbody>
		 	<thead>
		 		<tr><th colspan="2">审核</th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x140">审核状态：</label> 
						<span>${obj.getApplyStatusDesc() }</span>
					</td>
					<td>
						<label class="control-label x140">初审时间：</label> 
						<span>
							<c:choose>
								<c:when test="${obj.auditTime == null }">
									--
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${obj.auditTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</c:otherwise>
							</c:choose>
						</span>
						
					</td>
				</tr>
		 		<tr>
		 			<td colspan="2">
						<label class="control-label x140">初审意见：</label> 
						<span>${obj.auditRemark }</span>
					</td>
				</tr>
				<tr>
		 			<td colspan="2">
						<label class="control-label x140">复审意见：</label> 
						<textarea cols="50" rows="8" data-toggle="autoheight" data-rule="required;length[~200, true]" id="auditRemark" name="auditRemark"></textarea>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="submit" class="btn btn-orange"  id="checkBackBtn">复审退回</button></li>
        <li><button type="submit" class="btn-blue" id="checkPassBtn">复审通过</button></li>
    </ul>
</div>

<script>
$('#form').validator({
	fields: {
        'auditRemark': 'required;length[~200, true]'
    }
})
$('#checkBackBtn').on('click',function(e){
	$("#form").on("valid.form", function(e, form) {
		//$("#applyStatus").val(6);
        $("input[id=applyStatus]").val(6)
	    $(form).data("confirmMsg", "确定退回？").bjuiajax('ajaxForm', $(form).data())
	});
})
$('#checkPassBtn').on("click",function(){
	$("#form").on("valid.form", function(e, form) {
		//$("#applyStatus").val(7);
        $("input[id=applyStatus]").val(7)
	    $(form).data("confirmMsg", "确定通过？").bjuiajax('ajaxForm', $(form).data())
	});
})
function auditCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message);
		$(this).dialog("closeCurrent");
		$(this).navtab("refresh");
	}else {
		$(this).alertmsg("error", json.message);
	}
}
</script>
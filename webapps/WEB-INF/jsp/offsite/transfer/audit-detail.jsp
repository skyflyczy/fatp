<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 转让申请详情页--for审核 -->
<div class="bjui-pageContent">
	<form id="form" action="<%=request.getContextPath()%>/offsite/transfer/audit-apply.do" data-reload-navtab="true" data-toggle="validate" data-confirm-msg="确定审核通过？" data-callback="projectupateCallback">
		 <input type="hidden" name="applyStatus" id="applyStatus" value="${obj.applyStatus }" />
		 <input type="hidden" name="applyId" value="${obj.applyId }" />
		 <input type="hidden" name="applyGuid" value="${obj.applyGuid }" />
		 <table class="table table-condensed table-noborder">
		 	<thead>
		 	</thead>
		 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x110">挂牌名称：</label> 
						<span>${obj.projectFullName}</span>
					</td>
		 			<td>
						<label class="control-label x110">业务类型：</label> 
						<span>${obj.productTypeDesc}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">挂牌代码：</label> 
						<span>${obj.projectCode}</span>
					</td>
					<td>
						<label class="control-label x110">确权金额：</label> 
						<span><fmt:formatNumber value="${obj.saleCfmRightMoney}" pattern="#,##0.00"/> 元</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">到期日：</label> 
						<span><fmt:formatDate value="${obj.expireDate}" pattern="yyyy-MM-dd"/></span>
					</td>
					<td>
						<label class="control-label x110">登记成功数：</label> 
						<span>${obj.registerSuccessNum} 次</span>
					</td>
				</tr>
			</tbody>
			<thead>
		 		<tr><th colspan="2">转让登记信息 </th></tr>
		 	</thead>
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">转让时间段：</label> 
						<span><fmt:formatDate value="${obj.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;~&nbsp;<fmt:formatDate value="${obj.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</td>
					<td>
						<label class="control-label x110">转让总金额：</label> 
						<span><fmt:formatNumber value="${obj.totalMoney}" pattern="#,##0.00"/> 元</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">转让客户数：</label> 
						<span>${obj.sellerNum} 人</span>
					</td>
					<td>
						<label class="control-label x110">受让客户数：</label> 
						<span>${obj.buyerNum} 人</span>
					</td>
				</tr>
			</tbody>
			<c:if test="${not empty obj && obj.hasExceptionMsg == true }">
					<thead>
						<tr><th colspan="2">异常信息提示 </th></tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label class="control-label x150">转让方和受让方同一人：</label> 
								<span>${obj.sameTransferUserNum} 条</span>
							</td>
							<td>
							</td>
						</tr>
					</tbody>
				</c:if>
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
								<c:forEach var="var" items="${obj.fileList}" varStatus="status">
								<tr>
									<td align="center">${status.index + 1}</td>
									<td align="center">${var.fileType }</td>
									<td align="center">${var.displayName}</td>
									<td align="center"><fmt:formatDate value="${var.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td align="center">
										<c:choose>
											<c:when test="${var.contentType == 'xlsx' || var.contentType == 'xls' }"></c:when>
											<c:otherwise>
												<a target="_blank" href="<%=request.getContextPath()%>/offsite/base/view-apply-file.do?fileGuid=${var.fileGuid}&applyId=${obj.applyId}">浏览</a>
											</c:otherwise>
										</c:choose>
					    				<a href="<%=request.getContextPath()%>/offsite/base/down-apply-file.do?fileGuid=${var.fileGuid}&applyId=${obj.applyId}">下载</a>
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
		 		<%-- <tr><td colspan="2">数据校验成功：</td></tr>
		 		<tr><td colspan="2">本批转让记录共计：${obj.totalNum } 条。其中：转让总金额 ${obj.totalMoney } 元，转让客户数 ${obj.sellerNum } 人，受让客户数 ${obj.buyerNum } 人。</td></tr> --%>
		 		<tr>
					<td>
						<label class="control-label x110">审核状态：</label> 
						<span>${obj.getApplyStatusDesc() }</span>
					</td>
					<td>
						<label class="control-label x110">审核时间：</label> 
						<span>
							<c:choose>
								<c:when test="${empty obj.auditTime}">
									<fmt:formatDate value="${obj.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
						<label class="control-label x110">审核意见：</label> 
						<span>${obj.auditRemark }</span>
					</td>
				</tr>
		 		<tr>
		 			<td colspan="2">
		 				<label class="control-label x110"><span class="red">*</span>审核意见：</label>
						<textarea cols="60" rows="6" data-toggle="autoheight" id="auditRemark" name="auditRemark"></textarea>
					</td>
		 		</tr>
		 	</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="submit" class="btn btn-orange" id="checkBackBtn">${obj.applyStatus==5 ?"审核退回":"审核不通过" }</button></li>
        <li><button type="submit" class="btn-blue" id="checkPassBtn">审核通过</button></li>
    </ul>
</div>

<script>
$('#form').validator({
	fields: {
        'auditRemark':{
        	rule:'required;length[~200, true]',
        	msg:{
        		required:'请填写审核意见'
        	}
        } 
    }
})
$('#checkBackBtn').on('click',function(e){
	$("#form").on("valid.form", function(e, form) {
		<c:if test="${obj.applyStatus == 3 || obj.applyStatus == 6 }">
		$("input[name='applyStatus']").val(9);
	    $(form).data("confirmMsg", "确定审核不通过？").bjuiajax('ajaxForm', $(form).data());
		</c:if>
		<c:if test="${obj.applyStatus == 5 || obj.applyStatus == 10 }">
		$("input[name='applyStatus']").val(6);
	    $(form).data("confirmMsg", "确定审核退回？").bjuiajax('ajaxForm', $(form).data());
		</c:if>
	});
})
$('#checkPassBtn').on("click", function(){
	$("#form").on("valid.form", function(e, form) {
		<c:if test="${obj.applyStatus == 3 || obj.applyStatus == 6 }">
		$("input[name='applyStatus']").val(5);
		</c:if>
		<c:if test="${obj.applyStatus == 5 || obj.applyStatus == 10 }">
		$("input[name='applyStatus']").val(7);
		</c:if>
	    $(form).data("confirmMsg", "确定审核通过？").bjuiajax('ajaxForm', $(form).data())
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

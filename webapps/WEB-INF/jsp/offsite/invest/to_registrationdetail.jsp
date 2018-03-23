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
	 			<td colspan="2">
					<label class="control-label x140">募集总额：</label> 
					<span><fmt:formatNumber value="${obj.saleMoney }" pattern="#,##0.00"/> 元</span>
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
					<span><fmt:formatNumber value="${obj.uploadTotolMoney }" pattern="#,##0.00"/> 元</span>
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
	 	</tbody>
	 	<!--
	 	<thead>
	 		<tr><th colspan="2">投资明细确权结果</th></tr>
	 	</thead>
	 	<tbody>
	 		<tr>
				<td>
					<label class="control-label x140">确权状态：</label> 
					<span>
						<c:choose>
							<c:when test='${empty cfmStatus}'>
								--
							</c:when>
							<c:otherwise>
								${cfmStatus}
							</c:otherwise>
						</c:choose>
					</span>
				</td>
				<td>
					<label class="control-label x140">确权时间：</label> 
					<span> 
						<c:choose>
							<c:when test='${empty cfmTime}'>
								--
							</c:when>
							<c:otherwise>
								${cfmTime}
							</c:otherwise>
						</c:choose>
					</span>
				</td>
			</tr>
	 		<tr>
	 			<td>
					<label class="control-label x140">确权金额：</label> 
					<span>
						<c:choose>
							<c:when test='${empty cfmMoney}'>
								--
							</c:when>
							<c:otherwise>
								<fmt:formatNumber value="${cfmMoney}" pattern="#,##0.00"/> 元
							</c:otherwise>
						</c:choose>
					</span>
				</td>
				<td>
					<label class="control-label x140">确权人数：</label> 
					<span>
						<c:choose>
							<c:when test='${empty cfmUsers}'>
								--
							</c:when>
							<c:otherwise>
								${cfmUsers} 人
							</c:otherwise>
						</c:choose>
					</span>
				</td>
			</tr>
			<tr>
	 			<td>
					<label class="control-label x140">超募金额：</label> 
					<span>
						<c:choose>
							<c:when test='${empty unCfmMoney}'>
								--
							</c:when>
							<c:otherwise>
								<fmt:formatNumber value="${unCfmMoney }" pattern="#,##0.00"/> 元
							</c:otherwise>
						</c:choose>
					</span>
				</td>
				<td>
					<label class="control-label x140">超募人数：</label> 
					<span>
						<c:choose>
							<c:when test='${empty unCfmUsers}'>
								--
							</c:when>
							<c:otherwise>
								${unCfmUsers} 人
							</c:otherwise>
						</c:choose>
					</span>
				</td>
			</tr>
	 	</tbody>
	 	-->
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
	 		<tr><th colspan="2">投资明细登记结果</th></tr>
	 	</thead>
	 	
	 	<tbody>
		 		<tr>
		 			<td>
						<label class="control-label x140">登记状态：</label> 
						<span>${obj.applyStatusDesc}</span>
					</td>
				</tr>
				<tr>
		 			<td colspan="2">
						<label class="control-label x140">审核意见：</label> 
						<span>${obj.auditRemark}</span>
					</td>
				</tr>
				<tr>
		 			<td colspan="2">
						<label class="control-label x140">登记更新时间：</label> 
						<span><fmt:formatDate value="${obj.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					</td>
				</tr>
			</tbody>
	</table>
	</form>
</div>
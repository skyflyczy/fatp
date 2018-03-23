<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
	<form id="exportform" action="<%=request.getContextPath()%>/dataexport/user/member.do" method="post">
		 <input type="hidden" name="userCode" value="${search.userCode }">
		 <input type="hidden" name="userName" value="${search.userName }">
		 <input type="hidden" name="memberStatus" value="${search.memberStatus }">
		 <input type="hidden" name="companyName" value="${search.companyName }">
		 <input type="hidden" name="createTimeBegin" value="${search.createTimeBegin }">
		 <input type="hidden" name="createTimeEnd" value="${search.createTimeEnd }">
		 <input type="hidden" name="pageSize" value="${search.pageSize }">
		 <input type="hidden" name="totalpage" value="${search.totalpage }" data-rule="总页数:">
		 <table class="table table-condensed table-hover">
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">导出文件名称：</label> 
						<input type="text" data-rule="filename" data-rule-filename="[/^[ -\w\u0391-\uFFE5]+$/, '不支持特殊字符']" name="filename" size="30" maxlength="40"> 不填，则系统生成
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">选择导出数据：  </label> 
						从第<input type="text" name="frompage" size="5" data-rule="起始页:required;integer(+);match(lte, totalpage)" data-autoClose="true">页到<input type="text" name="topage" size="5" data-rule="required;integer(+);match(lte, totalpage);match(gte, frompage)" data-autoClose="true">页 每次导出最多100条数据
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue">导出</button></li>
    </ul>
</div>
<script>
$('#exportform').on('valid.form', function(e, form){
	$('#exportform')[0].submit()
});
</script>
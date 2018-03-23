<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 质押物类型 -->
<script type="text/javascript">
//修改回调
function afterEdit(json){
	if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
		$(this).alertmsg('ok',json.message)
		$("#pagerForm").submit();
	}else
		$(this).alertmsg('error',json.message);
	
	
}
//删除回调
function afterDel(json){
	if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) 
		$(this).alertmsg('ok',json.message);
	else
		$(this).alertmsg('error',json.message);
	
	$("#pagerForm").submit();
}
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/systypememberpledge/list.do" method="post">
        <div class="bjui-searchBar">
            <label>质押物类型名称：</label><input type="text" value="<c:out value='${pledgeTypeName }'/>" name="pledgeTypeName" size="10">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-default" data-toggle="reloadsearch" data-icon="undo" data-clear-query="true">重置查询</a>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <form action="" id="j_custom_form" class="pageForm" data-toggle="validate" method="post">
        <table id="tabledit1" data-reload-navtab="true" class="table table-hover table-striped" data-toggle="tabledit" data-initnum="0" data-action="<%=request.getContextPath()%>/systypememberpledge/edit.do" data-single-noindex="true" data-callback="afterEdit">
            <thead>
                <tr data-idname="customList[#index#].id">
                    <th title="质押物编码" width="20%" align="center" data-align="center" data-noedit="true"><input type="text" name="systypeMemberPledge[#index#].id" size="2" readonly></th>
                    <th title="质押物类型名称" width="60%" align="center" data-align="center"><input type="text" name="systypeMemberPledge[#index#].pledgeTypeName" data-rule="required" size="5"></th>
                    <th title="新增" data-addbtn="true" width="20%" align="center" data-align="center">
                        <a href="javascript:;" class="btn btn-blue" data-toggle="dosave">保存</a>
                        <a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删</a>
                    </th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="obj" items="${list}" varStatus="status"> 
                <tr align="center" data-id="${ status.index + 1}">
                    <td data-noedit="true">${obj.id }<input type="hidden" value="${obj.id }" name="systypeMemberPledge[#index#].id"/></td>
                    <td>${obj.pledgeTypeName }</td>
                    <td data-noedit="true">
                        <button type="button" class="btn-blue" data-toggle="doedit">编辑</button>
                       <a href="<%=request.getContextPath()%>/systypememberpledge/delete.do?id=${obj.id }" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" data-callback="afterDel">删</a>
                    </td>
                </tr>
             </c:forEach>
            </tbody>
        </table>
    </form>
</div>

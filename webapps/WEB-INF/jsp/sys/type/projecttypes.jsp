<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
//修改回调
function afterEdit(json){
	if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
		$(this).alertmsg('ok',json.message)
		$("#pagerForm").submit();
	}else{
		$(this).alertmsg('error',json.message);
	}
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
    <form id="pagerForm" action="<%=request.getContextPath()%>/systype/getprojecttypes.do" data-toggle="ajaxsearch"  method="post">
        <div class="bjui-searchBar">
            <label>产品类型名称：</label><input type="text" value="<c:out value='${search.typeName }'/>" name="typeName" size="10">&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-default" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">重置查询</a>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <form action="" id="j_custom_form" class="pageForm" data-toggle="validate" method="post" >
        <table id="tabledit1"  class="table table-hover table-striped" data-toggle="tabledit" data-initnum="0" data-action="<%=request.getContextPath() %>/systype/editprojecttype.do" data-single-noindex="true" data-callback="afterEdit">
            <thead>
                <tr>
                    <th width="10%" align="center" data-align="center" title="序号" data-noedit="true"><input type="text" name="id"  size="2" readonly	/></th>
                    <th width="30%" align="center" data-align="center" title="产品类型名称"><input type="text" name="typeName" data-rule="required" value="" size="5"></th>
                    <th width="30%" align="center" data-align="center" title="所属产品类型">
	                   <select name="productTypeId" data-toggle="selectpicker" data-rule="required">
	                    	<c:forEach var="item" items="${productTypeList}" varStatus="status">
								<option value="${item.id}">${item.typeName}</option>
							</c:forEach>                  
	                   </select>
                    </th>
                    <th width="30%" align="center" data-align="center" title="新增" data-addbtn="true">
                        <a href="javascript:;" class="btn btn-blue" data-toggle="dosave">保存</a>
                        <a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删</a>
                    </th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="obj" items="${list}" varStatus="status"> 
            <input type="hidden" value="${obj.id }" name="id"/>
                <tr align="center">
                    <td>${obj.id }</td>
                    <td>${obj.typeName }</td>
                    <td data-val="${obj.productTypeId}"></td>
                    <td data-noedit="true" data-notread="true">
                        <button type="button" class="btn-blue" data-toggle="doedit">编辑</button>
                        <a href="<%=request.getContextPath() %>/systype/delprojecttype.do?id=${obj.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" data-callback="afterDel">删</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

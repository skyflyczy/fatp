<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form id="pagerForm" action="<%=request.getContextPath()%>/protocol/list.do" data-toggle="ajaxsearch"  method="post">
        <input type ="hidden" name ="protocolType" value="2"/>
        <div class="bjui-searchBar">
            <label>所属产品类型：</label>&nbsp;
            <select name="typeId"  data-toggle="selectpicker" class="show-tick" style="display: none;">
			    <option value="">全部</option>
				<c:forEach var="item" items="${systypeProductList}">
					<option value="${item.id}" <c:if test="${typeId==item.id}">selected</c:if>>${item.typeName}</option>
				</c:forEach>       
            </select>
            <label>产品类型名称：</label>
            <input type="text" name="subTypeName" value="<c:out value='${subTypeName}'/>" size="15"/>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>&nbsp;
            <a class="btn btn-default" href="javascript:;"   data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
     <table data-toggle="tablefixed">
         <thead>
             <tr>
                 <th width="10%" align="center">序号</th>
                 <th width="30%" align="center">所属产品类型</th>
                 <th width="30%" align="center">产品类型名称</th>
                 <th width="30%" align="center">操作</th>
             </tr>
         </thead>
         <tbody>
          <c:forEach var="obj" items="${list}" varStatus="status"> 
              <tr align="center">
                  <td>${obj.agreementSubType }</td>
                  <td>${obj.typeName }</td>
                  <td>${obj.subTypeName}</td>
                  <td>
                      <a data-nodel="true" href="<%=request.getContextPath() %>/protocol/view.do?id=${obj.id }" data-width="1000" data-height="800" data-mask="true" data-toggle="dialog" class="btn btn-blue">协议管理</a>
                  </td>
              </tr>
          </c:forEach>
         </tbody>
     </table>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 银行联行号列表 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath() %>/sys/bankserinalno/list.do" method="post">
        <div class="bjui-searchBar">
            <label>银行：</label>
            <select data-autoClose="true" data-live-search="true" name="bankId" data-toggle="selectpicker" data-width="100px">
              <option value="">--请选择--</option>
              <c:forEach var="obj" items="${bankList}"> 
              	<option value="${obj.id}" <c:if test='${obj.id == search.bankId }'>selected="selected"</c:if>>${obj.bankName}</option>
              </c:forEach>                   
             </select>
             <label>省份：</label>
              <select data-autoClose="true"  name="provinceId" data-nextselect="#cityId" data-toggle="selectpicker" data-refurl="<%=request.getContextPath()%>/systype/city.do?proId={value}">
                <option value="">--省份--</option>
                <c:forEach var="obj" items="${provinceList}"> 
    				<option value="${obj.proId}" <c:if test="${obj.proId == search.provinceId}">selected="selected"</c:if>>${obj.proName}</option>
    			</c:forEach>
              </select>
               <label>城市：</label>
              <select data-autoClose="true"  name="cityId" id="cityId" data-toggle="selectpicker">
               <option value="">--城市--</option>
               <c:forEach var="obj" items="${cityList}"> 
			    <option value="${obj.cityId}" <c:if test="${obj.cityId == search.cityId}">selected="selected"</c:if>>${obj.cityName}</option>
			   </c:forEach>
              </select>
              <label>分支行：</label>
              <input type="text" name="subBankName" value="${search.subBankName}" size="20"/>
               <label>联行号：</label>
              <input type="text" name="subBankNo" value="${search.subBankNo}" size="15"/>
            <button type="submit" class="btn-default" data-icon="search" data-clear-query="false">查询</button>
            <a class="btn btn-default" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">重置查询</a>
            <c:if test="${powerlist.contains('/sys/bankserinalno/toadd.do') }">
            <div class="pull-right">
                <div class="btn-group">
                       <a href="<%=request.getContextPath() %>/sys/bankserinalno/toadd.do"
                       		class="btn btn-blue" data-toggle="dialog" data-width="600" 
                       		data-height="300" data-id="dialog-mask" data-mask="true">新增联行号</a>
                    	
                </div>
            </div>
            </c:if>
        </div>
    </form>
</div>

<div class="bjui-pageContent">
    <table data-toggle="tablefixed">
    	<thead>
	    	<tr>
	    		<th align="center" width="10%">id</th>
	    		<th width="25%">银行名称</th>
	    		<th width="25%">分(支)行名称</th>
	    		<th align="center" width="15%">分(支)行联行号</th>
	    		<th align="center" width="15%">创建时间</th>
	    		<th align="center" width="10%">操作</th>
	    	</tr>
    	</thead>
    	<tbody>
	    	<c:if test="${empty list}">
	    		<tr align="center"><td colspan="6">暂无数据</td></tr>
	    	</c:if>
	    	<c:if test="${!empty list}"><c:forEach var="obj" items="${list}" varStatus="statu">
		    	<tr align="center">
		    		<td>${obj.id}</td>
		    		<td align="left">${obj.bankName}</td>
		    		<td align="left">${obj.subBankName}</td>
		    		<td>${obj.subBankNo}</td>
		    		<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		    		<td>
		    			<a href="<%=request.getContextPath() %>/sys/bankserinalno/toedit.do?id=${obj.id}"
		                       		class="btn btn-blue" data-toggle="dialog" data-width="600" 
		                       		data-height="300" data-id="dialog-mask" data-mask="true">编辑</a>
		    		</td>
		    	</tr>
	    	</c:forEach></c:if>
    	</tbody>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>

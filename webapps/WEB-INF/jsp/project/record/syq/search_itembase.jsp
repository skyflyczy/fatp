<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label>备案代码：</label>
<input type="text" name="recordCode" value="<c:out value='${search.recordCode}'/>" class="form-control" size="10" data-rule="integer"/>
<label>备案名称：</label>
<input type="text" name="recordFullName" value="<c:out value='${search.recordFullName}'/>" class="form-control" size="15" />
<label>发行人：</label>
<input type="text" name="loanUserName" value="<c:out value='${search.loanUserName}'/>" class="form-control" size="10" />
<label>备案状态：</label>
<select name="recordStatus" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
	<option value="">全部</option>
	<c:forEach var="item" items="${recordStatusDesc}">
	<option value="${item.value}" <c:if test="${search.recordStatus==item.value}">selected</c:if>>${item}</option>
	</c:forEach>
</select>

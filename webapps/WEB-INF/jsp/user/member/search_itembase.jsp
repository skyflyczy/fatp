<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label>会员账号：</label>
<input type="text" name="userCode" value="<c:out value='${search.userCode}'/>" class="form-control" size="10" data-rule="integer"/>
<label>会员简称：</label>
<input type="text" name="userName" value="<c:out value='${search.userName}'/>" class="form-control" size="15" />
<label>会员状态：</label> 
<select name="userStatus" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
	<option value="-1" <c:if test="${search.userStatus == -1}">selected</c:if>>全部</option>
	<c:forEach var="item" items="${userStatusList}">
    	<option value="${item.value}" <c:if test="${search.userStatus == item.value}">selected</c:if>>${item}</option>
	</c:forEach>
</select>

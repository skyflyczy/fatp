<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label>会员账号：</label>
<input type="text" name="userCode" value="<c:out value='${search.userCode}'/>" class="form-control" size="10" data-rule="integer"/>
<label>会员简称：</label>
<input type="text" name="userName" value="<c:out value='${search.userName}'/>" class="form-control" size="15" />
<label>会员状态：</label> 
<select name="memberStatus" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
	<c:forEach var="item" items="${statusMenuItem}">
    	<option value="${item}" <c:if test="${search.memberStatus == item}">selected</c:if>>${item}</option>
	</c:forEach>
</select>

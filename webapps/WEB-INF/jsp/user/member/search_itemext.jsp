<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%">
<tr>
	<td>
		<label class="control-label x80">机构名称：</label> 
		<input type="text" name="companyName" value="<c:out value='${search.companyName }'/>" class="form-control" size="20" />
	</td>
	<td>
		<label class="control-label x80">注册时间：</label>
		<input type="text" name="createTimeBegin" value="${search.createTimeBegin }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
		<label>-</label>
		<input type="text" name="createTimeEnd" value="${search.createTimeEnd }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
	</td>
</tr>
</table>
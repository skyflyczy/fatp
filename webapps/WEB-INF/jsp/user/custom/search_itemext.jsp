<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%">
<tr>
	<td>
		<label class="control-label x100">所属会员编号：</label> 
		<input type="text" name="ownerUserCode" value="<c:out value='${search.ownerUserCode }'/>" class="form-control" size="20" />
	</td>
	<td>
		<label class="control-label x100">所属会员简称：</label>
		<input type="text" name="ownerUserName" value="<c:out value='${search.ownerUserName }'/>" class="form-control" size="20" />
	</td>
</tr>
<tr>
	<td>
		<label class="control-label x100">机构名称：</label> 
		<input type="text" name="companyName" value="<c:out value='${search.companyName }'/>" class="form-control" size="20" />
	</td>
	<td>
		<label class="control-label x100">注册时间：</label>
		<input type="text" name="createTimeBegin" value="${search.createTimeBegin }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
		<label>-</label>
		<input type="text" name="createTimeEnd" value="${search.createTimeEnd }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
	</td>
</tr>
<tr>
	<td>
		<label class="control-label x100">审核时间：</label>
		<input type="text" name="auditTimeBegin" value="${search.auditTimeBegin }" class="form-control" size="15" data-toggle="datepicker"
				data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="datetime"/>
			<label>-</label>
		<input type="text" name="auditTimeEnd" value="${search.auditTimeEnd }" class="form-control" size="15" data-toggle="datepicker"
				data-pattern="yyyy-MM-dd HH:mm:ss" data-nobtn="true" data-rule="datetime"/>
	</td>
</tr>
</table>
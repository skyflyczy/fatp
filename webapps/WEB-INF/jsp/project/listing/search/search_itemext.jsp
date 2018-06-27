<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table width="100%">
<tr>
	<td>
		<label >付息方式：</label> 
		<select name="payInterestType" id="select2" data-toggle="selectpicker" class="show-tick" style="display: none;">
			<option value="">-全部-</option>
			<c:forEach var="item" items="${payInterestTypeList}">
				<option value="${item.type}"  <c:if test="${item.type==search.payInterestType}">selected</c:if>>${item}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td>
		<label>&nbsp;&nbsp;&nbsp;起息日：</label>
		<input type="text" name="valueDateBegin" value="${search.valueDateBegin }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
		<label>-</label>
		<input type="text" name="valueDateEnd" value="${search.valueDateEnd }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
		<label>&nbsp;到期日：</label>
		<input type="text" name="expireDateBegin" value="${search.expireDateBegin }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
		<label>-</label>
		<input type="text" name="expireDateEnd" value="${search.expireDateEnd }" class="form-control" size="15" data-toggle="datepicker"
			data-pattern="yyyy-MM-dd" data-nobtn="true" data-rule="date"/>
	</td>
</tr>
</table>
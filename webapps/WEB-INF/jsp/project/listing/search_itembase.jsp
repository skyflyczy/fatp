<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label>挂牌代码：</label>
<input type="text" name="listingCode" value="<c:out value='${search.listingCode}'/>" class="form-control" size="10" data-rule="integer"/>
<label>挂牌名称：</label>
<input type="text" name="listingFullName" value="<c:out value='${search.listingFullName}'/>" class="form-control" size="15" />
<label>发行人：</label>
<input type="text" name="issuer" value="<c:out value='${search.issuer}'/>" class="form-control" size="10" />

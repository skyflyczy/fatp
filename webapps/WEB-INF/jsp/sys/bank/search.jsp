<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.banklist li{padding:5px;}
.banklist li,.banklist b {overflow:hidden; white-space:nowrap; text-overflow:ellipsis;line-height:28px;}
.bank-nav li a{color:#666;padding:10px 0;}
.bank-nav .active a,
.bank-nav > li > a:hover,
.bank-nav > li > a:focus{border-bottom:2px solid #428bca;color:#428bca;background:#fff;}
</style>
<script>
$(function(){
	$('#bankNav li').on('click',function(){
		$(this).addClass('active').siblings().removeClass('active');
		var text = $(this).children().text();
		if(text == '常用')
			text = '';
		$("#bank_py").val(text);
		$.CurrentDialog.find("#bankName").val("");
		$.CurrentDialog.find('#pagerForm').submit();
	})
})
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="<%=request.getContextPath()%>/sys/bank/search.do">
        <input type="hidden" id="bank_py" name="py" value="${search.py }"/>
        <div class="bjui-searchBar">
            <label>名称：</label><input type="text" value="${search.bankName }" id="bankName" name="bankName" size="10" class="form-control"/>&nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>&nbsp;
        </div>
    </form>
</div>
<div class="bjui-pageContent">
	<ul class="nav nav-justified bank-nav" id="bankNav" role="tablist" style="margin-bottom:10px;">
		<li role="presentation" <c:if test='${empty search.py }'>class="active"</c:if>><a href="javascript:;">常用</a></li>
		<li role="presentation" <c:if test='${search.py == "A" }'>class="active"</c:if>><a href="javascript:;">A</a></li>
		<li role="presentation" <c:if test='${search.py == "B" }'>class="active"</c:if>><a href="javascript:;">B</a></li>
		<li role="presentation" <c:if test='${search.py == "C" }'>class="active"</c:if>><a href="javascript:;">C</a></li>
		<li role="presentation" <c:if test='${search.py == "D" }'>class="active"</c:if>><a href="javascript:;">D</a></li>
		<li role="presentation" <c:if test='${search.py == "E" }'>class="active"</c:if>><a href="javascript:;">E</a></li>
		<li role="presentation" <c:if test='${search.py == "F" }'>class="active"</c:if>><a href="javascript:;">F</a></li>
		<li role="presentation" <c:if test='${search.py == "G" }'>class="active"</c:if>><a href="javascript:;">G</a></li>
		<li role="presentation" <c:if test='${search.py == "H" }'>class="active"</c:if>><a href="javascript:;">H</a></li>
		<li role="presentation" <c:if test='${search.py == "J" }'>class="active"</c:if>><a href="javascript:;">J</a></li>
		<li role="presentation" <c:if test='${search.py == "K" }'>class="active"</c:if>><a href="javascript:;">K</a></li>
		<li role="presentation" <c:if test='${search.py == "L" }'>class="active"</c:if>><a href="javascript:;">L</a></li>
		<li role="presentation" <c:if test='${search.py == "M" }'>class="active"</c:if>><a href="javascript:;">M</a></li>
		<li role="presentation" <c:if test='${search.py == "N" }'>class="active"</c:if>><a href="javascript:;">N</a></li>
		<li role="presentation" <c:if test='${search.py == "P" }'>class="active"</c:if>><a href="javascript:;">P</a></li>
		<li role="presentation" <c:if test='${search.py == "Q" }'>class="active"</c:if>><a href="javascript:;">Q</a></li>
		<li role="presentation" <c:if test='${search.py == "R" }'>class="active"</c:if>><a href="javascript:;">R</a></li>
		<li role="presentation" <c:if test='${search.py == "S" }'>class="active"</c:if>><a href="javascript:;">S</a></li>
		<li role="presentation" <c:if test='${search.py == "T" }'>class="active"</c:if>><a href="javascript:;">T</a></li>
		<li role="presentation" <c:if test='${search.py == "W" }'>class="active"</c:if>><a href="javascript:;">W</a></li>
		<li role="presentation" <c:if test='${search.py == "X" }'>class="active"</c:if>><a href="javascript:;">X</a></li>
		<li role="presentation" <c:if test='${search.py == "Y" }'>class="active"</c:if>><a href="javascript:;">Y</a></li>
		<li role="presentation" <c:if test='${search.py == "Z" }'>class="active"</c:if>><a href="javascript:;">Z</a></li>
	</ul>
	<div class="container-fluid">
		<ul class="row banklist">
			<c:forEach var="obj" items="${list}" varStatus="status">
			<li class="col-xs-2"><a href="javascript:;" data-toggle="lookupback" data-args="{bankId:${obj.id}, bankName:'${obj.bankName }'}" title="${obj.bankName }">${obj.bankName }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>


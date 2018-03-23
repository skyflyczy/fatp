<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.sub_memu_box > div {padding-left:205px;}
.left_menu {float:right; width:200px; margin-left:-205px; margin-right:5px; overflow:auto; border:1px solid #c3ced5;}
.right_menu {float:right; width:100%; overflow:auto;border:1px solid #c3ced5;}
.bs-example { padding:10px;}
#MenuIcon { display:inline-block; width:50px;}
.btn_box { padding:10px;} 
</style>
<script>
	$(document).bjuiajax('doLoad', {
		target: '#showProtocol',
		url: "${ctx}/protocol/list.do",
		'loadingmask':false,
		data: {"protocolType": 2}
		
	});
	
	//菜单布局
	function resizeH(){
		var menuH=$(window).height()-170;
		$('.left_menu,.right_menu').height(menuH);
	}
	resizeH();
	$(window).resize(resizeH);
</script>




<div class="bjui-pageContent">
<div class="sub_memu_box">
			<div class="clearfix">
				<div id="ztree-detail" class="right_menu">
					<div id="showProtocol" class="tab-pane bjui-layout" style="height:100%;"></div>
				</div>
				<div class="left_menu">
		            <ul class="nav nav-stacked" role="tablist">
		            	<c:forEach var="obj" items="${protocolTypeList}" varStatus="status">
		            		<li>
		            			<a href="<%=request.getContextPath()%>/protocol/list.do?protocolType=${obj.agreementType}" data-mask data-toggle="ajaxload" data-target="#showProtocol">${obj}</a>
		            		</li>
			            </c:forEach>
					</ul>
				</div>
			</div>
		</div>

</div>

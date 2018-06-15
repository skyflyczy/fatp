<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <title>${exchange.ptomsSysName }</title>
	<meta name="description" content="${exchange.description }">
    <meta name="Keywords" content="${exchange.exchangeName },${exchange.ptomsSysName }"/>
    <%@ include file="jsp/common/intoHead.jsp" %>
</head>
<script type="text/javascript">
	function ZtreeClick(event, treeId, treeNode) {
		var $target = $(event.target)
		if(treeNode.url == undefined || treeNode.url == "") {
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			treeObj.expandNode(treeNode, !treeNode.open, true, true);
			return;
		}
		var url = '<%=request.getContextPath()%>'+treeNode.url;
		var options={
				id:treeNode.id+"",
				url:url,
				title:treeNode.name,
				fresh:true
		};
		$(this).navtab(options);
		$('#bjui-accordionmenu').find('.curSelectedNode').removeClass('curSelectedNode')
		$target.parent('a').addClass('curSelectedNode');
		//add cookie
		$.cookie('curNavtabBtn',$target.parent('a').attr('id'))
		$.cookie('bjui.slidebar.collapse.in',$target.parents('.panel').index())
		event.preventDefault();
	}
</script>
<body>
<div id="bjui-window">
    <%@ include file="jsp/common/header.jsp" %>

    <div id="bjui-container" class="clearfix">
		<div id="bjui-leftside">
		    <div id="bjui-sidebar">
		        <div class="toggleCollapse">
			        <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar">
			        </div>
		    	</div>
			</div>
		</div>
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                    </ul>
                </div>
            </div>
            <div class="navtab-panel tabsPageContent">
            </div>
        </div>
        <div id="bjui-homePage">
    		
    	</div>
    </div>
    <%@ include file="jsp/common/footer.jsp" %>
</div>
</body>

</html>
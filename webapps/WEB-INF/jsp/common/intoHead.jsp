<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String host = request.getServerName();
if(host.indexOf(".com") > 0) {
	host = host.substring(0, host.indexOf(".com"));
	host = host.substring(host.lastIndexOf(".")+1);
}
%>
<%@ include file="/WEB-INF/version.jsp" %>
<link rel="icon" href="//cf2.<%=host %>.com/favicon.ico" mce_href="//cf2.<%=host %>.com/favicon.ico" type="image/x-icon">
<!-- bootstrap - css -->
<link href="<%=request.getContextPath() %>/jslib/css/bootstrap.min.css" rel="stylesheet">
<!-- core - css -->
<link href="//cf1.<%=host %>.com/BJUI/v2.0/themes/css/style.min.css?v=${version}" rel="stylesheet">
<!-- plug - css -->
<link href="//cf1.<%=host %>.com/ui/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="//cf1.<%=host %>.com/ui/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="//cf1.<%=host %>.com/ui/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/jslib/FA/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
    tr{height: 32px; line-height: 32px} /* 优化表格行高 */
    .resize-head{height:0;} /* 修正表格行距增加后表头显示问题 */
    .panel-body td{line-height:1.5;font-size: 14px;} /* kindEditor文字行距 */
    .panel-body p{line-height:1.5;font-size: 14px;} /* kindEditor文字行距 */
</style>
<!--[if lte IE 7]>
<link href="//cf1.<%=host %>.com/BJUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="//cf1.<%=host %>.com/ie/html5shiv.min.js"></script>
    <script src="//cf1.<%=host %>.com/ie/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
 <script src="//cf1.<%=host %>.com/base/jquery-1.7.2.min.js"></script>
<!-- <script src="jslib/jue.js?v=55"></script> -->
<script src="//cf1.<%=host %>.com/util/jquery.md5.js"></script>
<!--[if lte IE 9]>
<script src="//cf1.<%=host %>.com/util/jquery.iframe-transport.js"></script>    
<![endif]-->
<c:if test="${hm }">
<script src="//cf2.<%=host %>.com/js/hm.js"></script>
</c:if>
<!-- BJUI.all  分模块压缩版-->
<!-- <script src="jslib/bjui-all.js?v=55"></script> -->
<script src="//cf1.<%=host %>.com/BJUI/v2.0/js/bjui-all.js?v=${version}"></script>
<!-- plugins -->
<!-- swfupload for uploadify && kindeditor -->
<script src="//cf1.<%=host %>.com/ui/swfupload/swfupload.js"></script>
<!-- kindeditor -->
<script src="//cf1.<%=host %>.com/ui/kindeditor_4.1.10/kindeditor-min.js"></script>
<script src="//cf1.<%=host %>.com/ui/kindeditor_4.1.10/lang/zh_CN.js"></script>
<!-- ztree -->
<script src="//cf1.<%=host %>.com/ui/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="//cf1.<%=host %>.com/ui/niceValidator/jquery.validator.js"></script>
<script src="//cf1.<%=host %>.com/ui/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="//cf1.<%=host %>.com/ui/bootstrap/bootstrap.min.js"></script>
<script src="//cf1.<%=host %>.com/ui/bootstrapSelect/bootstrap-select.min.js"></script>
<!-- icheck -->
<script src="//cf1.<%=host %>.com/ui/icheck/icheck.min.js"></script>
<!-- other plugins -->
<script src="//cf1.<%=host %>.com/ui/placeholder/jquery.placeholder.min.js"></script>
<script src="//cf1.<%=host %>.com/ui/other/jquery.autosize.js"></script>
<link href="//cf1.<%=host %>.com/ui/uploadify/css/uploadify.css" rel="stylesheet">
<script src="//cf1.<%=host %>.com/ui/uploadify/scripts/jquery.uploadify.min.js"></script>
<script src="//cf1.<%=host %>.com/BJUI/others/functions.js"></script>
<script src="//cf1.<%=host %>.com/BJUI/others/holiday.js"></script>
<!-- init -->
<script type="text/javascript">
$(function() {
    BJUI.init({
        JSPATH       : '//cf1.<%=host %>.com/BJUI/',
        PLUGINPATH   : '//cf1.<%=host %>.com/ui/',
        loginInfo    : {okCall:function(){window.location='<%=request.getContextPath() %>/login.do'}}, // 会话超时后弹出登录对话框
        statusCode   : {ok:200, error:300, timeout:301}, //[可选]
        ajaxTimeout  : 50000, //[可选]全局Ajax请求超时时间(毫秒)
        alertMsg	 : {displayMode:'none', displayPosition:'middlecenter', alertTimeout:1000, mask:true},
        pageInfo     : {pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
        keys         : {statusCode:'statusCode', message:'message'}, //[可选]
        ui           : {showSlidebar:true, clientPaging:false}, //[可选]clientPaging:在客户端响应分页及排序参数
        debug        : true    // [可选]调试模式 [true|false，默认false]
    })
    //时钟
    var today = new Date();
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'));
    function navClock() {
    	today = new Date();
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'));
    }
    navClock();
    setInterval(navClock, 1000);
})
//菜单-事件
function MainMenuClick(event, treeId, treeNode) {
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        
        zTree.expandNode(treeNode);
        return;
    }
    
    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name});
    else
        $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external});
    event.preventDefault();
}
</script>

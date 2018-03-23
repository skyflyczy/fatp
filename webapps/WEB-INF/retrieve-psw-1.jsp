<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<title>安全中心-${exchange.ptomsSysName }—${exchange.simpleName }</title>
<%@ include file="header.jsp" %>
<script type="text/javascript">
$(function() {
	$('#retrievePsw_valid').validator({
		timely:false,
		stopOnError: false, //关闭此开关，以便一次性显示所有消息
		showOk:false
	});
});
function changeImg() {
    var imgSrc = $("#captcha_img");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
}
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    var index = url.indexOf("?")
    if(index >0)
    	url = url.substring(0, index);
    return url + "?s=retrieve&timestamp=" + timestamp;
}
</script>
</head>
<body>
<!--[if lte IE 7]>
<div id="errorie"><div>您还在使用老掉牙的IE，请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
	<div class="cont_wrap">
		<div class="cont_box">
			<%@ include file="head.jsp" %>
			<div class="login_container hj_wrap">
	       		<h3 class="bg-info fp-tit">找回密码</h3>
				<div class="fp-box">
					<ul class="clearfix">
						<li class="pass" id="step1">1操作员信息</li>
						<li class="" id="step2">2重置密码</li>
						<li class="" id="step3">3找回成功</li>
					</ul>
					<!--身份验证-->
					<div class="con show" id="form-cont1">
						<div class="form-cont">
							<form id="retrievePsw_valid" action="<%=request.getContextPath()%>/retrievePsw-valid.do" method="post" data-toggle="validate">
								<input type="hidden" name="s" value="retrieve">
								<p id="error_tip_row" class="error_tip_row" style="margin-left:104px;height:10px;line-height:10px	">${message }</p>
								<div class="form-group">
									<label for="hj_username1" class="control-label x100">用户名：</label>
									<input type="text" data-rule="required;" data-msg-required="请填写用户名" name="loginName" id="loginName" value="" class="form-control input-nm" autocomplete="off">
								</div>
								<div class="form-group">
									<label for="hj_captcha" class="control-label x100">手机号：</label> 
									<input type="text" data-rule="required;mobile" data-msg-required="请填写手机号" data-rule-mobile="[/^1[3-9]\d{9}$/, '手机号格式不正确']" name="phone" id="phone" value="" class="form-control input-nm" autocomplete="off" maxlength="11">
								</div>
								<div class="form-group">
									<label for="hj_captcha" class="control-label x100">验证码：</label> 
									<input data-autoClose="true" id="hj_captcha" name="code" type="text" data-rule="required;" data-msg-required="请填写验证码" data-rule-validCode="validCode" class="form-control input-nm code-inp">
				    				<img id="captcha_img" src="<%=request.getContextPath()%>/captcha/getcode.do?s=retrieve" class="code">
									<a href="javascript:changeImg();" class="change-code">换一张</a>
								</div>
								<div class="form-group space">
									<input type="submit" value="&nbsp;下&nbsp;一&nbsp;步" class="btn btn-primary btn-nm sub-btn">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--footer begin-->	
	<%@ include file="footer.jsp" %>
	<!--footer end-->
</body>
</html>

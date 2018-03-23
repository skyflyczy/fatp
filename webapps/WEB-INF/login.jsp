<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<title>系统登录-${exchange.ptomsSysName }—${exchange.simpleName }</title>
<meta name="description" content="${exchange.description }">
<meta name="Keywords" content="${exchange.ptomsSysName }，${exchange.simpleName }">
<%@ include file="header.jsp" %>
<script type="text/javascript">
$.cookie('navtaboptions', '', { expires: -1 }); 
$(function() {
	$('#login_form').validator({
		timely:false,
		stopOnError: false, //关闭此开关，以便一次性显示所有消息
		msgMaker: false,    //不要自动生成消息
		invalid: function(form, errors){
			$('#error_tip_row').text(errors[0]);
		},
		valid: function(form) {
			this.holdSubmit();
			$("#hj_password").val($.md5($("#hj_password").val()));
			form.submit();
		}
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
    return url + "?s=login&timestamp=" + timestamp;
}	
</script>
</head>
<body>
	<!--[if lte IE 7]>
	<div id="errorie"><div>您还在使用老掉牙的IE，请升级您的浏览器到 IE8以上版本 <a target="_blank" href="javascript:;">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="javascript:;" target="_blank">谷歌 Chrome</a></div></div>
	<![endif]-->
	<div class="cont_wrap">
		<div class="cont_box">
			<%@ include file="head.jsp" %>
			<div class="login_container clearfix login_bg">
				<div class="hj_wrap">
			        <div class="login_form pull-right">
			    		<form action="<%=request.getContextPath()%>/dologin.do" id="login_form" method="post" data-toggle="validate">
			    			<input type="hidden" name="exchangeId" value="1">
			    			<input type="hidden" name="s" value="login">
							<p id="error_tip_row" class="error_tip_row">${message}</p>
							<!--<span class="msg-box" for="hj_username" style="margin-left:10px; border:none;"></span>-->
							<div class="form-group">
								<label for="hj_username" class="control-label x85">用户名：</label>
								<input type="text" id="hj_username" data-rule="required" data-msg-required="请填写用户名" name="loginName" value="" placeholder="用户名" class="form-control input-nm" autocomplete="off">
							</div>
							<div class="form-group">
								<label for="hj_password" class="control-label x85">密码：</label>
								<input type="password" class="hide"/>
								<input type="password" id="hj_password" data-rule="required;password" data-msg-required="请填写密码" data-rule-password="[/^(?!\d+$)(?![A-Za-z]+$)[0-9a-zA-Z]{6,30}$/, '密码由6-30位数字、字母组成']" name="secrectCode" value="" placeholder="密码" class="form-control input-nm" autocomplete="off" oncopy="return false" onpaste="return false">
							</div>
			    			<div class="form-group">
			    				<label for="hj_captcha" class="control-label x85">验证码：</label> 
								<input id="hj_captcha" name="code" type="text" data-rule="required;" data-msg-required="请填写验证码" data-rule-validCode="validCode"  placeholder="验证码" class="form-control input-nm code-inp">
			    				<img id="captcha_img" src="<%=request.getContextPath()%>/captcha/getcode.do?s=login" class="code">
								<a href="javascript:changeImg();" class="change-code">换一张</a>
			    			</div>
			    			<div class="form-group">
			                    <label class="x85"></label>
			    				<a href="<%=request.getContextPath()%>/toRetrievePsw.do" class="pull-right forget-psw">忘记密码？</a>
							</div>
			    			<div class="form-group space">
			    				<input type="submit" id="login_ok" value="&nbsp;登&nbsp;录&nbsp;" class="btn btn-primary btn-nm sub-btn" style="margin-left: 90px;">
			    			</div>
			    		</form>
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

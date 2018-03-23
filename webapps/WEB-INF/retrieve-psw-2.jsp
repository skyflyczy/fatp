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
	$('#retrievePsw').validator({
		timely:false,
		stopOnError: false, //关闭此开关，以便一次性显示所有消息
		showOk:false
	});
});
var ti = 0;
function retrievePsw_send() {
	$.ajax({
		url : "<%=request.getContextPath()%>/retrievePsw-send.do",
		type: 'post',
		data: {k:$("#k").val()},
		dataType : "json",
		timeout : 5000,
		cache : false,
		success : function(data, textStatus) {
			if(data.statusCode == 200){
				$("#retrievePsw").validator("hideMsg", "#validCode");
				if (ti <= 0) {
			        ti = data.data * 60;
			        var self = $('#getCode');
			        self.addClass('btn-disable');
			        self.text(ti+"秒后可重新获取");
			        self.off("click");
			        var _inter = setInterval(function () {
			            if (ti <= 0) {
			                self.removeClass('btn-disable');
			                self.text('点击获取验证码');
			                self.on("click", retrievePsw_send);
			                window.clearInterval(_inter);
			            } else {
			                self.text(ti+"秒后可重新获取");
			                ti--;
			            }
			        }, 1000);
			    }
			}else{
				if(data.data != undefined) {
					$("#retrievePsw").validator("showMsg", data.data);
					$("#retrievePsw").validator("showMsg", data.data);
				}else {
					$("#error_tip_row").text(data.message);
				}
				
			}
		}
	});
}
function retrievePsw() {
	$("#retrievePsw").trigger("validate"); 
	var instance = $("#retrievePsw").data("validator");
	if(instance.isFormValid()) {
		$.ajax({
			url: '<%=request.getContextPath()%>/retrievePsw.do',
			type: 'post',
			data: {k:$("#k").val(),validCode:$("#validCode").val(),password:$.md5($("#password").val())},
			cache: 'false',
			dataType: 'json',
			success: function(data) {
				if(data.statusCode == 200) {
					$("#form-cont2").removeClass("show");
					$("#form-cont3").addClass("show");
					$("#step2").removeClass("pass");
					$("#step3").addClass("pass");
				}else {
					if(data.data != undefined) {
						instance.showMsg(data.data);
					}else {
						$("#error_tip_row").text(data.message);
					}
				}
			}
		});
	}
}
$(document).ready(function(){
	　$("#getCode").on("click", retrievePsw_send);
});
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
						<li class="" id="step1">1操作员信息</li>
						<li class="pass" id="step2">2重置密码</li>
						<li class="" id="step3">3找回成功</li>
					</ul>
					<!--重置密码-->
					<div class="con show" id="form-cont2">
						<div class="form-cont">
							<form id="retrievePsw" action="" method="post" data-toggle="validate">
								<input type="hidden" name="k" id="k" value="${k }">
								<p id="error_tip_row" class="error_tip_row" style="margin-left:104px;height:10px;line-height:10px	">${message }</p>
								<div class="form-group">
									<label for="hj_msgcode" class="control-label x100">短信验证码：</label> 
									<input data-autoClose="true" autocomplete="off" id="validCode" name="validCode" type="text" data-rule="required" data-msg-required="请填写短信验证码" class="form-control input-nm code-inp">
									<a class="btn btn-default btn-nm" id="getCode">点击获取验证码</a>
								</div>
								<div class="form-group">
									<label for="hj_psw" class="control-label x100">重置密码：</label>
									<input type="password" class="hide"/> 
									<input id="password" name="password" type="password" data-rule="required;password" data-msg-required="请填写密码" data-rule-password="[/^(?!\d+$)(?![A-Za-z]+$)[0-9a-zA-Z]{6,30}$/, '密码由6-30位数字、字母组成']" class="form-control input-nm" autocomplete="off" oncopy="return false" onpaste="return false" >
								</div>
								<div class="form-group">
									<label for="hj_psw2" class="control-label x100">确认密码：</label> 
									<input name="confirmpassword" type="password" data-rule="required;match[password];password" data-msg-required="请填写确认密码" data-msg-match="确认密码必须与密码相同" data-rule-password="[/^(?!\d+$)(?![A-Za-z]+$)[0-9a-zA-Z]{6,30}$/, '密码由6-30位数字、字母组成']" class="form-control input-nm" autocomplete="off" oncopy="return false" onpaste="return false">
								</div>
								<div class="form-group space">
									<input type="button" onclick="retrievePsw()" id="login_ok2" value="&nbsp;下&nbsp;一&nbsp;步" class="btn btn-primary btn-nm sub-btn">
								</div>
							</form>
						</div>
					</div>
					<!--找回成功-->
					<div class="con hide" id="form-cont3">
						<div class="be-sucss">
							<i class="fa fa-check-circle-o fa-5x"></i>
							<p class="succ-text">设置成功，请牢记您的登录密码。</p>
							<p>您可能需要：</p>
						</div>
						<div class="succ-btn text-center">
							<a href="<%=request.getContextPath()%>/login.do" class="btn btn-primary btn-nm">立即登录</a>
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"  %>
<script>
	var copyrightYear = ${exchange.poweredBegin };
	var currentYear=new Date().getFullYear();
	if(currentYear>copyrightYear)
	{
		copyrightYear = copyrightYear + '-' + currentYear;
	}
</script>
<div class="qj-footer">
		<div class="hj_wrap text-center" style="position:relative;">
			<!--<div class="bottom-pic"></div>-->
			<p>版权所有 © <script>document.write(copyrightYear);</script> ${exchange.poweredBy }</p>
			<p>Copyright © <script>document.write(copyrightYear);</script> ${exchange.exchangeEng }All Rights Reserved</p>
			<p><a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=${exchange.websiteRecord }" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="//cf2.<%=host%>.com/img/security_remark.png" style="float:left;"><span style="height:20px;line-height:20px;margin: 0px 0px 0px 1px;color:#737373">${exchange.recordProvince }公网安备 ${exchange.websiteRecord }号</span></a>&nbsp;&nbsp;&nbsp;&nbsp;${exchange.recordProvince }ICP备${exchange.icpRecord }号</p>
			
			<!-- <img class="qrcode" src="<%=request.getContextPath() %>/js/BJUI/themes/css/img/qrcode.png" alt="" title="" /> -->
		</div>
	</div>
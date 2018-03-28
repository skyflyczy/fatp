<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="bjui-pageContent">
        <div style="margin:0px auto 0; width:100%;">
                <!-- Tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#baseinfo" role="tab" data-toggle="tab">基本信息</a></li>
                    <li><a href="<%=request.getContextPath()%>/member/operator/toeditregisteragent.do?userId=${user.id}" 
	    				id="agent_link" role="tab" data-toggle="ajaxtab" data-target="#agent" data-reload="false">经办人</a></li>
                    <li><a href="<%=request.getContextPath()%>/member/operator/advisor-rz/to-advisor-admin.do?userId=${user.id}" 
	    				id="operator_link" role="tab" data-toggle="ajaxtab" data-target="#operator" data-reload="false">管理员设置</a></li>
	    			<!-- <li><a id="files_link" href="<%=request.getContextPath()%>/userfiles/list.do?userId=${user.id}" 
			                    	role="tab" data-toggle="ajaxtab" data-target="#files" data-reload="false">注册资料</a></li> -->
                    <li><a id="bankinfo_link" href="<%=request.getContextPath()%>/user/bankcard/list.do?userId=${user.id}" 
			                    	role="tab" data-toggle="ajaxtab" data-target="#bankinfo" data-reload="false">银行账户</a></li>
                    <li><a href="#financeinfo" role="tab" data-toggle="tab">财务信息</a></li>
                   	<li><a href="#linkinfo" role="tab" data-toggle="tab">部门信息</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="baseinfo">
                    	<%@ include file="/WEB-INF/jsp/user/member/edit_base2.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="agent" ></div>
                    <div class="tab-pane fade" id="operator" ></div>
                    <!-- <div class="tab-pane fade" id="files" ></div> -->
                    <div class="tab-pane fade" id="bankinfo" ></div>
                    <div class="tab-pane fade" id="financeinfo">
                    	<%@ include file="/WEB-INF/jsp/user/member/edit_finance2.jsp" %>
                    </div>
                    <div class="tab-pane fade" id="linkinfo">
                    	<%@ include file="/WEB-INF/jsp/user/member/edit_link2.jsp" %>
                    </div>
                </div>
        </div>
</div>

<div class="bjui-pageFooter">
<ul>
    <li>
	    <a href="<%=request.getContextPath()%>/user/member/checkAuditConditions.do?id=${user.id}"
	       data-toggle="doajax" data-callback="checkAuditConditionsCallback"
	       class="btn btn-blue">提交审核</a>
    </li>
</ul>
</div>

<script>
function checkAuditConditionsCallback(json) {
	if (json.statusCode == 200) {
		var options = {url:'<%=request.getContextPath()%>/user/member/audit_reg/submit.do?memberId=${user.id}',type:'get',confirmMsg:'确定要提交审核吗？',callback:'memberCheckCallback'};
		$(this).bjuiajax('doAjax', options);
	} else {
		// 显示错误提示
		$(this).alertmsg("error", json.message);
	}
}

function memberCheckCallback(json) {
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message, {
			autoClose:false,
			okCall:function(){
				$(this).dialog("closeCurrent");
				$(this).navtab("refresh");
			}
		});
	}else{
		$(this).alertmsg("error", json.message);
	}
}
</script>

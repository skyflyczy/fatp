<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function customCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message, {mask:true,autoClose:false,okCall:function(){$.CurrentDialog.dialog("closeCurrent");$(this).dialog({id:'dialog-mask-editPage',onClose:function(){$(this).navtab("refresh");},title:'编辑',width:1000,height:600,mask:true,url:'<%=request.getContextPath()%>/user/custom/toEditPage.do?id='+json.id});}});
	}else {
		$(this).alertmsg("error", json.message);
	}
}
function validUserName(element){
	return $.post( "<%=request.getContextPath()%>/user/custom/validusername.do",
		{"userName":element.value,"isUpdate":0,"userId":-1,"memberId":$("#ownerUserId").val()},
		function(data){
		}
	);
}
$("#ownerUserId").on("change", function(){
	var memberId = $("#ownerUserId").val();
	if(memberId == '') {
		return;
	}
	var userName = $("#userName").val();
	if(userName != '') {
		$("#userName").trigger("validate");
	}
})
$("#orgTypeId").on("change", function() {
	var orgTypeIdVal = $(this).val();
	if(orgTypeIdVal == 3) {
		$("#parentCompanyOrgCodeTR").removeClass("hide");
		$("#parentCompanyNameTR").removeClass("hide");
		$("#parentCompanyName").attr("disabled",false); 
		$("#parentCompanyOrgCode").attr("disabled",false); 
	}else {
		$("#parentCompanyOrgCodeTR").addClass("hide");
		$("#parentCompanyNameTR").addClass("hide");
		$("#parentCompanyName").attr("disabled",true); 
		$("#parentCompanyOrgCode").attr("disabled",true); 
	}
})
$("#companyName").on("blur", function(){
	if($("#companyName").val() == '')
		return;
	$.post("<%=request.getContextPath()%>/user/companySearchName.do","term="+$("#companyName").val(),function(data) {
		if(data[0] != undefined) {
			$("#companyOrgCode").val(data[0].value);
			$('#companyOrgCode').attr("readonly", "readonly");
			$('#companyName').attr("readonly", "readonly");
		}
	});
})
$("#companyOrgCode").on("blur", function(){
	if($("#companyOrgCode").val() == '')
		return;
	$.post("<%=request.getContextPath()%>/user/companySearchCode.do","term="+$("#companyOrgCode").val(),function(data) {
		if(data[0] != undefined) {
			$("#companyName").val(data[0].name);
			$('#companyOrgCode').attr("readonly", "readonly");
			$('#companyName').attr("readonly", "readonly");
		}
	});
})
$("#parentCompanyName").on("blur", function(){
	if($("#parentCompanyName").val() == '')
		return;
	$.post("<%=request.getContextPath()%>/user/companySearchName.do","term="+$("#parentCompanyName").val(),function(data) {
		if(data[0] != undefined) {
			$("#parentCompanyOrgCode").val(data[0].value);
			$('#parentCompanyOrgCode').attr("readonly", "readonly");
			$('#parentCompanyName').attr("readonly", "readonly");
		}
	});
})
$("#parentCompanyOrgCode").on("blur", function(){
	if($("#parentCompanyOrgCode").val() == '')
		return;
	$.post("<%=request.getContextPath()%>/user/companySearchCode.do","term="+$("#parentCompanyOrgCode").val(),function(data) {
		if(data[0] != undefined) {
			$("#parentCompanyName").val(data[0].name);
			$('#parentCompanyOrgCode').attr("readonly", "readonly");
			$('#parentCompanyName').attr("readonly", "readonly");
		}
	});
})
$('#companyOrgCode').on('afterchange.bjui.lookup', function(e, data) {
	$('#companyOrgCode').attr("readonly", "readonly");
	$('#companyName').attr("readonly", "readonly");
})
function reset() {
	$.CurrentDialog.find("input[type='text']").val("");
	$('#companyOrgCode').removeAttr("readonly");
	$('#companyName').removeAttr("readonly");
	$('#parentCompanyOrgCode').removeAttr("readonly");
	$('#parentCompanyName').removeAttr("readonly");
	$("#addCustomForm").validator("cleanUp");
}
$('#parentCompanyName').on('aftercreated.bjui.tags', function(e, data) {
	var item = data.item;
	$('#parentCompanyOrgCode').val(item.value);
	$('#parentCompanyName').val(item.name);
	$('#parentCompanyOrgCode').attr("readonly", "readonly");
	$('#parentCompanyName').attr("readonly", "readonly");
})
</script>
<!-- 增加个人投资者 -->
<div class="bjui-pageContent">
    <form action="<%=request.getContextPath()%>/user/custom/add.do" data-toggle="validate"
     data-reload-navtab="true" data-callback="customCallback" data-loadingmask="true" id="addCustomForm">
     <input type="hidden" name="userChildType" value="${userChildType }">
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
					<td colspan="2">
						<label class="control-label x140">所属会员：</label> 
						<select data-autoClose="true" data-live-search="true" data-rule="required" id="ownerUserId" name="ownerUserId" data-toggle="selectpicker" data-emptytxt="--请选择--">
                       	<option value="">--请选择--</option>
                       	<c:forEach var="obj" items="${memberList}"> 
           				<option value="${obj.id}">${obj.userName}</option>
           				</c:forEach>
                    </select>
					</td>
					</tr>
                	<tr>
                       <td colspan="2">
                           <label class="control-label x140">客户类型：</label>
                           <select name="orgTypeId" id="orgTypeId" data-toggle="selectpicker" data-rule="required">
                               <c:forEach var="obj" items="${SystypeUserorgs}">
                               <option value="${obj.id}">${obj.orgTypeName }</option>
                               </c:forEach>
                           </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">客户全称：</label>
                            <input maxlength="200" type="text" name="realName"  size="35"  data-rule="required;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">客户简称：</label>
                            <input maxlength="60" type="text" id="userName" name="userName"  size="20"  data-rule="required;validUserName" data-rule-validUserName="validUserName">
                        </td>
                    </tr>
                    <tr id="companyOrgCodeTR">
                        <td colspan="2">
                            <label class="control-label x140">营业执照号码：</label>
                            <c:choose>
                            <c:when test="${user == null }">
                            <input data-toggle="lookup" data-title="机构选择" data-url="<%=request.getContextPath()%>/user/companySearch.do" data-width="600" data-height="400" type="text" name="companyOrgCode" value="${user.companyOrgCode }" id="companyOrgCode" size="20" data-rule="required" data-ok="" >
                            </c:when>
                            <c:otherwise>
                            <input type="text" name="companyOrgCode" value="${user.companyOrgCode }" id="companyOrgCode" size="20" readonly>
                            </c:otherwise>
                            </c:choose>
                             <span class="">(统一社会信用代码或注册号)</span>
                        </td>
                    </tr>
                    <tr id="companyNameTR">
                        <td colspan="2">
                            <label class="control-label x140">机构全称：</label>
                           <c:choose>
                            <c:when test="${user == null }">
                            <input maxlength="100" type="text" name="companyName" value="${user.companyName }" id="companyName" size="20" data-rule="required;" data-ok="">
                            </c:when>
                            <c:otherwise>
                            <input type="text" name="companyName" value="${user.companyName }" id="companyName" size="20" readonly>
                            </c:otherwise>
                            </c:choose>
                           
                        </td>
                    </tr>
                    <tr id="parentCompanyNameTR" class="hide">
                        <td colspan="2">
                            <label class="control-label x140">所属法人机构全称：</label>
                           <c:choose>
                            <c:when test="${user == null }">
                            <input maxlength="200" data-toggle="tags" data-width="202" data-url="<%=request.getContextPath()%>/user/companySearchName.do" data-max="1" type="text" name="parentCompanyName" id="parentCompanyName" size="20" data-rule="required;" data-ok="" disabled>
                            </c:when>
                            <c:otherwise>
                            <input type="text" name="parentCompanyName" value="${user.companyName }" id="parentCompanyName" size="20" readonly disabled>
                            </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr id="parentCompanyOrgCodeTR" class="hide">
                        <td colspan="2">
                            <label class="control-label x140">所属法人营业执照号码：</label>
                            <c:choose>
                            <c:when test="${user == null }">
                            <input type="text" name="parentCompanyOrgCode" id="parentCompanyOrgCode" size="20" data-rule="required" data-ok="" disabled>
                            </c:when>
                            <c:otherwise>
                            <input type="text" name="parentCompanyOrgCode" value="${user.companyOrgCode }" id="parentCompanyOrgCode" size="20" readonly disabled>
                            </c:otherwise>
                            </c:choose>
                             <span class="">(统一社会信用代码或注册号)</span>
                        </td>
                    </tr>
                    
                </tbody>
            </table>
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-blue" data-icon="save">保存</button></li>
        <li><button type="button" class="btn-green" onclick="reset()">重填</button></li>
    </ul>
</div>

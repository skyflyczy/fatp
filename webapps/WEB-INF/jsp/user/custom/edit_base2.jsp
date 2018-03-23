<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 修改个人投资者 -->
<script type="text/javascript">
function validUserName(element){
	return $.post("<%=request.getContextPath()%>/user/custom/validusername.do",
		{"userName":element.value,"isUpdate":1,"userId":${user.id},"memberId":${user.ownerUserId}},
		function(data){
		}
	);
}
function validCompanyName(element) {
	return $.post("<%=request.getContextPath()%>/user/validCompanyName.do",
		{"companyInfoId":'${user.companyInfoId}',"companyName":element.value},
		function(data){
		}
	);
}
function validCompanyCode(element) {
	return $.post("<%=request.getContextPath()%>/user/validCompanyName.do",
		{"companyInfoId":'${user.companyInfoId}',"companyOrgCode":element.value},
		function(data){
		}
	);
}
function validParentCompanyName(element) {
	return $.post("<%=request.getContextPath()%>/user/validCompanyName.do",
		{"companyInfoId":'${user.companyParentId}',"companyName":element.value},
		function(data){
		}
	);
}
function validParentCompanyCode(element) {
	return $.post("<%=request.getContextPath()%>/user/validCompanyName.do",
		{"companyInfoId":'${user.companyParentId}',"companyOrgCode":element.value},
		function(data){
		}
	);
}
</script>
<div class="bjui-pageContent">
    <form action="<%=request.getContextPath()%>/user/custom/updateBase.do" data-toggle="validate" data-reload="false">
     <input type="hidden" value="${user.id}" name="id"/>
     <input type="hidden" value="${user.companyInfoId}" name="companyInfoId" id="companyInfoId"/>
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
                        <td>
                            <label class="control-label x140">客户简称：</label>
                            <input maxlength="60" type="text" name="userName" value="<c:out value='${user.userName}'/>" size="20"  data-rule="required;validUserName" data-rule-validUserName="validUserName">
                        </td>
                        <td>
                            <label class="control-label x140">注册时间：</label>
                            <fmt:formatDate value="${user.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                	<tr>
                		<td colspan="2">
                            <label class="control-label x140">营业执照号码：</label>
                            <input type="text" name="companyOrgCode" value="${user.companyOrgCode }" id="companyOrgCode" size="20" data-rule="required;validCompanyCode;"  data-rule-validCompanyCode="validCompanyCode"> <span class="">(统一社会信用代码或注册号)</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">机构全称：</label>
                            <input maxlength="100" type="text" name="companyName" value="<c:out value='${user.companyName }'/>" id="companyName" size="40" data-rule="required;validCompanyName" data-rule-validCompanyName="validCompanyName">
                        </td>
                    </tr>
                    <c:if test="${user.orgTypeId==3 }">
                   	<tr>
                		<td colspan="2">
                            <label class="control-label x140">所属法人营业执照号码：</label>
                            <input type="text" name="parentCompanyOrgCode" value="${userPub.companyOrgCode }" id="parentCompanyOrgCode" size="20" data-rule="required;validParentCompanyCode;" data-rule-validParentCompanyCode="validParentCompanyCode"> <span class="">(统一社会信用代码或注册号)</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">所属法人机构全称：</label>
                            <input maxlength="200" type="text" name="parentCompanyName" value="<c:out value='${userPub.companyName }'/>" id="parentCompanyName" size="40" data-rule="required;validParentCompanyName" data-rule-validParentCompanyName="validParentCompanyName">
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                    	<td>
                            <label class="control-label x140">法定代表人：</label>
                            <input maxlength="100" type="text" name="companyRepresentative" value="<c:out value='${user.companyRepresentative }'/>"  size="20" data-rule="required;" data-ok="">
                        </td>
                        <td>
                        	<!-- 
                            <label class="control-label x140">营业执照号码：</label>
                            <input maxlength="100" type="text" name="companyBusinessLicense" value="<c:out value='${user.companyBusinessLicense }'/>"  size="20" data-rule="">
                       		 -->
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">注册地：</label>
                                <select name="provinceId" id="provinceId" data-val="${user.provinceId}" data-toggle="selectpicker" data-nextselect="#cityId" data-refurl="<%=request.getContextPath()%>/systype/city.do?proId={value}" data-rule="required;integer[+]" data-msg-integer="请选择" data-autoClose="true">
                                    <option value="0">--省市--</option>
                                </select>
                                <select name="cityId" id="cityId" data-toggle="selectpicker" data-val="${user.cityId}" data-nextselect="#disId" data-refurl="<%=request.getContextPath()%>/systype/district.do?cityId={value}" data-emptytxt="--城市--" data-rule="required;integer[+]" data-msg-integer="请选择" data-autoClose="true">
                                    <option value="0">--城市--</option>
                                </select>
                                <select name="disId" id="disId" data-toggle="selectpicker" data-val="${user.disId}" data-emptytxt="--区县--" data-rule="required;integer[+]" data-msg-integer="请选择" data-autoclose="true">
                                    <option value="0">--区县--</option>
                                </select>
                               	<input maxlength="200" type="text" name="companyRegAddress" value="<c:out value='${user.companyRegAddress }'/>"  size="40" data-rule="required;" data-ok="">
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label class="control-label x140">机构类型：</label>
                            <select name="companyTypeId" id="companyTypeId" data-val="${user.companyTypeId}" data-toggle="selectpicker" data-rule="required">
                                    <option value="">-请选择-</option>
                                </select>
                        </td>
                        <td>
                            <label class="control-label x140">所属行业：</label>
                            <select name="industryId" id="industryId" data-val="${user.industryId}" data-toggle="selectpicker" data-rule="required">
                                    <option value="">-请选择行业-</option>
                                </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<label class="control-label x140">机构邮编：</label>
                            <input maxlength="32" type="text" name="postalCode" value="<c:out value='${user.postalCode }'/>" size="20" data-rule="postcode">
                        </td>
                        <td>
                            <label class="control-label x140">机构传真：</label>
                            <input maxlength="100" type="text" name="faxPhone" value="<c:out value='${user.faxPhone }'/>" size="20" data-rule="tel" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x140">机构电话：</label>
                            <input maxlength="100" type="text" name="telePhone" value="<c:out value='${user.telePhone }'/>" size="20" data-rule="tel" >
                        </td>
                        <td>
                            <label class="control-label x140">机构网址：</label>
                            <input maxlength="80" type="text" name="webUrl" value="<c:out value='${user.webUrl }'/>" size="20" data-rule="url" >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">主营业务：</label>
                            <textarea maxlength="200" name="mainBuisness" cols="40" rows="4" data-toggle="autoheight" data-rule="" ><c:out value='${user.mainBuisness }'/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x140">机构描述：</label>
                            <textarea maxlength="200" name="mainDesc" cols="40" rows="4" data-toggle="autoheight" data-rule="" ><c:out value='${user.mainDesc }'/></textarea>
                        </td>
                    </tr>      
                    <tr>
                        <td colspan="2" style="text-align:center;">
                        	<button type="submit" class="btn-blue" data-icon="save">保存</button>
                            <button type="button" class="btn-close" data-icon="close">关闭</button>
					    </td>
                    </tr>                 
                </tbody>
            </table>
    </form>
</div>
<script>
	$.ajax({
		url: '<%=request.getContextPath()%>/systype/province.do',
		type: 'get',
		cache: 'false',
		dataType: 'json',
		success: function(data) {
			var array = ['<option value="0">--省市--</option>'];
			var dbProvinceId = 0;
			<c:if test="${user.provinceId!=null}">
			dbProvinceId = ${user.provinceId};
			</c:if>
			for(var i=0; i<data.length; i++) {
				var selected = "";
				if(dbProvinceId == data[i].proId) {
					selected = "selected";
				}
				array.push('<option value="'+data[i].proId+'" '+selected+'>'+data[i].proName+'</option>');
			}
			$("#provinceId").html(array.join("")).selectpicker('refresh');
		}
	});
	$.ajax({
		url: '<%=request.getContextPath()%>/systype/industry.do',
		type: 'get',
		cache: 'false',
		dataType: 'json',
		success: function(data) {
			var array = ['<option value="">-请选择行业-</option>'];
			var dbIndustryId = 0;
			<c:if test="${user.industryId!=null}">
			dbIndustryId = ${user.industryId};
			</c:if>
			for(var i=0; i<data.length; i++) {
				var selected = "";
				if(dbIndustryId == data[i].id) {
					selected = "selected";
				}
				array.push('<option value="'+data[i].id+'" '+selected+'>'+data[i].industryName+'</option>');
			}
			$("#industryId").html(array.join("")).selectpicker('refresh');
		}
	});
	$.ajax({
		url: '<%=request.getContextPath()%>/systype/company.do',
		type: 'get',
		cache: 'false',
		dataType: 'json',
		success: function(data) {
			var array = ['<option value="">-请选择-</option>'];
			var dbCompanyTypeId = 0;
			<c:if test="${user.companyTypeId!=null}">
			dbCompanyTypeId = ${user.companyTypeId};
			</c:if>
			for(var i=0; i<data.length; i++) {
				var selected = "";
				if(dbCompanyTypeId == data[i].id) {
					selected = "selected";
				}
				array.push('<option value="'+data[i].id+'" '+selected+'>'+data[i].companyTypeName+'</option>');
			}
			$("#companyTypeId").html(array.join("")).selectpicker('refresh');
		}
	});
</script>
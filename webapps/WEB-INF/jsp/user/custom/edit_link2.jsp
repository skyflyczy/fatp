<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
    <form action="<%=request.getContextPath()%>/user/custom/updateLink.do" data-toggle="validate" data-reload="false">
     <input type="hidden" value="${userExt.id}" name="id"/>
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
                        <td colspan="2">
                            <label class="control-label x100">部门：</label>
                            <input maxlength="100" type="text" name="deparmentName" value="<c:out value='${userExt.deparmentName }'/>"  size="40" data-rule="required;" data-ok="">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">所在地：</label>
                                <select name="deparmentProvinceId" id="deparmentProvinceId" data-val="${userExt.deparmentProvinceId}" data-toggle="selectpicker" data-nextselect="#deparmentCityId" data-refurl="<%=request.getContextPath()%>/systype/city.do?proId={value}">
                                    <option value="0">--省市--</option>
                                </select>
                                <select name="deparmentCityId" id="deparmentCityId" data-toggle="selectpicker" data-val="${userExt.deparmentCityId}" data-nextselect="#deparmentDisId" data-refurl="<%=request.getContextPath()%>/systype/district.do?cityId={value}" data-emptytxt="--城市--">
                                    <option value="0">--城市--</option>
                                </select>
                                <select name="deparmentDisId" id="deparmentDisId" data-toggle="selectpicker" data-val="${userExt.deparmentDisId}" data-emptytxt="--区县--">
                                    <option value="0">--区县--</option>
                                </select>
                                <input maxlength="200" type="text" name="deparmentAddress" value="<c:out value='${userExt.deparmentAddress }'/>"  size="20">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">部门邮编：</label>
                            <input maxlength="10" type="text" name="deparmentPostCode" value="<c:out value='${userExt.deparmentPostCode }'/>"  size="20" data-rule="postcode">
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">部门电话：</label>
                            <input maxlength="100" type="text" name="deparmentPhone" value="<c:out value='${userExt.deparmentPhone }'/>"  size="20" data-rule="tel">
                        </td>
                        <td>
                            <label class="control-label x100">部门传真：</label>
                            <input maxlength="100" type="text" name="deparmentFax" value="<c:out value='${userExt.deparmentFax }'/>"  size="20" data-rule="tel">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">主要负责人：</label>
                            <input maxlength="100" type="text" name="linkMan" value="<c:out value='${userExt.linkMan }'/>"  size="20" data-rule="required;" data-ok="">
                        </td>
                        <td>
                            <label class="control-label x100">性别：</label>
                            <input type="radio" name="linkSex" value="1" <c:if test="${userExt.linkSex==1}">checked</c:if> data-toggle="icheck" data-label="男" data-rule="">
                            <input type="radio" name="linkSex" value="2" <c:if test="${userExt.linkSex==2}">checked</c:if> data-toggle="icheck" data-label="女" data-rule="">
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label class="control-label x100">证件类型：</label>
                            <select name="linkIdTypeId" id="linkIdTypeId" data-val="${userExt.linkIdTypeId}" data-toggle="selectpicker" data-rule="">
                                    <option value="2">身份证</option>
                                </select>
                        </td>
                        <td>
                            <label class="control-label x100">证件号码：</label>
                            <input maxlength="100" type="text" name="linkIdnumber" value="<c:out value='${userExt.linkIdnumber }'/>"  size="20"  data-rule="ID_card">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">职位：</label>
                            <input maxlength="40" type="text" name="linkCareer" value="<c:out value='${userExt.linkCareer }'/>"  size="40" data-rule="">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">负责人手机：</label>
                            <input maxlength="40" type="text" name="linkPhone" value="<c:out value='${userExt.linkPhone }'/>"  size="20" data-rule="required;mobile;"><span class="red">*该手机用于找回密码</span>
                        </td>
                        <td>
                            <label class="control-label x100">负责人邮箱：</label>
                            <input maxlength="100" type="text" name="linkEmail" value="<c:out value='${userExt.linkEmail }'/>"  size="20" data-rule="email">
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
			<c:if test="${userExt.deparmentProvinceId!=null}">
			dbProvinceId = ${userExt.deparmentProvinceId};
			</c:if>
			for(var i=0; i<data.length; i++) {
				var selected = "";
				if(dbProvinceId == data[i].proId) {
					selected = "selected";
				}
				array.push('<option value="'+data[i].proId+'" '+selected+'>'+data[i].proName+'</option>');
			}
			$("#deparmentProvinceId").html(array.join("")).selectpicker('refresh');
		}
	});
</script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
                        <td colspan="2">
                            <label class="control-label x100">部门：</label>
                            ${user.deparmentName }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">所在地：</label>
                            ${user.deparmentProvince }${user.deparmentCity }${user.deparmentDis }${user.deparmentAddress }
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">部门邮编：</label>
                            ${user.deparmentPostCode }
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">部门电话：</label>
                            ${user.deparmentPhone }
                        </td>
                        <td>
                            <label class="control-label x100">部门传真：</label>
                            ${user.deparmentFax }
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">主要负责人：</label>
                            ${user.linkMan }
                        </td>
                        <td>
                            <label class="control-label x100">性别：</label>
                            <c:if test="${user.linkSex==1}">男</c:if>
                            <c:if test="${user.linkSex==2}">女</c:if>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                            <label class="control-label x100">证件类型：</label>
                            <c:forEach var="idType" items="${idTypeList}">
                           	<c:if test="${idType.idType==user.linkIdTypeId}">${idType }</c:if>
                           	</c:forEach>
                        </td>
                        <td>
                            <label class="control-label x100">证件号码：</label>
                            ${user.linkIdnumber }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">职位：</label>
                            ${user.linkCareer }
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label x100">负责人手机：</label>
                            ${user.linkPhone }
                        </td>
                        <td>
                            <label class="control-label x100">负责人邮箱：</label>
                            ${user.linkEmail }
                        </td>
                    </tr>
                </tbody>
            </table>
</div>

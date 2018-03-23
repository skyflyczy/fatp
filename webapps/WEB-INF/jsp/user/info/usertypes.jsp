<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
                        <td colspan="2">
                		<c:forEach var="productType" items="${productTypes}">
                            <input type="checkbox" name="usertypes" value="${productType.id}" <c:if test="${productType.checked==1 }">checked</c:if> data-toggle="icheck" data-label="${productType.typeName }" disabled>
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                        </td>
                    </tr>
                </tbody>
            </table>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
    <form action="<%=request.getContextPath()%>/user/updateUserTypes.do" data-toggle="validate" data-reload="false">
     <input type="hidden" value="${userId}" name="userId"/>
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
                        <td colspan="2">
                		<c:forEach var="productType" items="${productTypes}">
                            <input type="checkbox" name="usertypes" value="${productType.id}" <c:if test="${productType.checked==1 }">checked</c:if> data-toggle="icheck" data-label="${productType.typeName}" data-autoClose="true">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                        </td>
                    </tr>
                    <!-- 
	                    <tr>
	                    	<td colspan="2">
	                			<span class="red">注：</span>如果保存业务资格信息，提交审核时，必须有默认银行账户信息。
	                        </td>
	                    </tr>
                     -->
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

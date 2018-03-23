<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
		 <table class="table table-condensed table-hover">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">注册资本：</label>
                            <fmt:formatNumber value="${user.financialGroupBusinessIncome }" pattern="#,##0.00"/> <c:if test="${user.moneyUnit==1}">元</c:if><c:if test="${user.moneyUnit==2}">港币</c:if><c:if test="${user.moneyUnit==3}">美元</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">净资产：</label>
                            <fmt:formatNumber value="${user.financialNetAsset }" pattern="#,##0.00"/> <c:if test="${user.moneyUnitFinance==1}">元</c:if><c:if test="${user.moneyUnitFinance==2}">港币</c:if><c:if test="${user.moneyUnitFinance==3}">美元</c:if>
                        </td>
                    </tr>
                    <tr>
                       <td colspan="2">
                            <label class="control-label x100">营业收入：</label>
                            <fmt:formatNumber value="${user.financialBusinessIncome }" pattern="#,##0.00"/> <c:if test="${user.moneyUnitFinance==1}">元</c:if><c:if test="${user.moneyUnitFinance==2}">港币</c:if><c:if test="${user.moneyUnitFinance==3}">美元</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">资产总额：</label>
                            <fmt:formatNumber value="${user.financialTotalAsset }" pattern="#,##0.00"/> <c:if test="${user.moneyUnitFinance==1}">元</c:if><c:if test="${user.moneyUnitFinance==2}">港币</c:if><c:if test="${user.moneyUnitFinance==3}">美元</c:if>
                        </td>
                    </tr>
                     <tr>
                        <td colspan="2">
                            <label class="control-label x100">净利润：</label>
                            <fmt:formatNumber value="${user.financialNetProfit }" pattern="#,##0.00"/> <c:if test="${user.moneyUnitFinance==1}">元</c:if><c:if test="${user.moneyUnitFinance==2}">港币</c:if><c:if test="${user.moneyUnitFinance==3}">美元</c:if>
                        </td>
                    </tr>
                     <tr>
                        <td colspan="2">
                            <label class="control-label x100">利润总额：</label>
                            <fmt:formatNumber value="${user.financialTotalProfit }" pattern="#,##0.00"/> <c:if test="${user.moneyUnitFinance==1}">元</c:if><c:if test="${user.moneyUnitFinance==2}">港币</c:if><c:if test="${user.moneyUnitFinance==3}">美元</c:if>
                        </td>
                    </tr>
                </tbody>
            </table>
</div>

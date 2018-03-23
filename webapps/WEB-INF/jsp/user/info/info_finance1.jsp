<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
		 <table class="table table-condensed table-hover">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">个人收入：</label>
                            ${user.financialBusinessIncome }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">个人净资产：</label>
                            ${user.financialNetAsset }
                        </td>
                    </tr>
                    <tr>
                       <td colspan="2">
                            <label class="control-label x100">家庭收入：</label>
                            ${user.financialGroupBusinessIncome }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">家庭净资产：</label>
                            ${user.financialGroupNetAsset }
                        </td>
                    </tr>
                </tbody>
            </table>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 修改个人投资者 -->
<div class="bjui-pageContent">
		 <table class="table table-condensed table-hover">
                <tbody>
                	<tr>
                        <td colspan="2">
                            <label class="control-label x100">客户编号：</label>
                            ${user.userCode }
                        </td>
                    </tr>
                	<tr>
                        <td colspan="2">
                            <label class="control-label x100">客户姓名：</label>
                            ${user.realName }
                        </td>
                    </tr>
                     <tr>
                       <td colspan="2">
                            <label class="control-label x100">证件类型：</label>
                            ${user.showIdTypeDesc() }
                        </td>
                    </tr>
                    <tr>
                       <td colspan="2">
                            <label class="control-label x100">证件号码：</label>
                            ${user.idNumber }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">手机号：</label>
                            ${user.phone }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">银行账号：</label>
                            ${bankCard.cardAccount }
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <label class="control-label x100">开户行：</label>
                            ${bankCard.channelName }
                        </td>
                    </tr>
                </tbody>
            </table>
</div>
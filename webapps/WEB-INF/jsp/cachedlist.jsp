<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 其他附件 -->
<div class="bjui-pageContent tableContent">
        <table class="table table-hover table-striped">
        	<thead>
	        	<tr>
		    		<th align="center" width="20%">缓存名称</th>
		    		<th align="center" width="30%">缓存KEY或前缀</th>
		    		<th align="center" width="30%">缓存后缀(多个,分隔)</th>
		    		<th align="center" width="20%">操作</th>
	    		</tr>
            </thead>
            <tbody>
		    	<tr align="center">
		    		<td>行业类型</td>
                    <td>SystypeIndustry_</td>
                    <td><input id="SystypeIndustry_input"></td>
                    <td><a data-key="SystypeIndustry_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>公司类型</td>
                    <td>SystypeCompany_</td>
                    <td><input id="SystypeCompany_input"></td>
                    <td><a class="btn btn-red" data-key="SystypeCompany_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>省份</td>
                    <td>SysareaProvince_</td>
                    <td><input id="SysareaProvince_input"></td>
                    <td><a class="btn btn-red" data-key="SysareaProvince_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>城市</td>
                    <td>SysareaCity_</td>
                    <td><input id="SysareaCity_input"></td>
                    <td><a class="btn btn-red" data-key="SysareaCity_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>区县</td>
                    <td>SysareaDistrict_</td>
                    <td><input id="SysareaDistrict_input"></td>
                    <td><a class="btn btn-red" data-key="SysareaDistrict_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>操作者权限</td>
                    <td>MemberOperator_Menus_</td>
                    <td><input id="MemberOperator_Menus_input"></td>
                    <td><a class="btn btn-red" data-key="MemberOperator_Menus_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>交易中心信息</td>
                    <td>FeExchange_</td>
                    <td><input id="FeExchange_input"></td>
                    <td><a class="btn btn-red" data-key="FeExchange_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>系统参数表</td>
                    <td>sysParam_</td>
                    <td><input id="sysParam_input"></td>
                    <td><a class="btn btn-red" data-key="sysParam_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>系统异常表</td>
                    <td>SysException</td>
                    <td><input id="SysExceptioninput" type="hidden"></td>
                    <td><a class="btn btn-red" data-key="SysException" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
		    	<tr align="center">
		    		<td>银行列表</td>
                    <td>sysBank_</td>
                    <td><input id="sysBank_input"></td>
                    <td><a class="btn btn-red" data-key="sysBank_" href=""
    				data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a></td>
		    	</tr>
            </tbody>
        </table>
</div>
<script type="text/javascript">
$.CurrentNavtab.find('a').on("click", function(){
	var key = $(this).attr("data-key");
	var url = "<%=request.getContextPath()%>/cached/delete.do?key="+key+"&values="+$("#"+key+"input").val();
	$(this).attr("href", url);
})
</script>

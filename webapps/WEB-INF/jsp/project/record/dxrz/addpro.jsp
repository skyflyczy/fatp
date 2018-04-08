<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
	<form id="projectadd" action="<%=request.getContextPath()%>/project/record/dxrz/add.do" data-toggle="validate" data-reload-navtab="true" data-confirm-msg="确定要保存吗？" data-callback="addprojectCallback">
		 <input type="hidden" id="projectSourceId" value="">
		 <input type="hidden" name="projectTypeId" value="1">
		 <table class="table table-condensed table-noborder table-hover">
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">业务类型：</label> 
						<span>定向融资计划</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">备案名称：</label> 
						<input type="text" name="recordFullName" id="recordFullName" size="40" maxlength="120">
					</td>
				</tr>
				
				<tr>
					<td>
						<label class="control-label x110">发行人：</label> 
						<input type="hidden" name="loanUserId" id="loanUserId">
						<input readonly type="text" name="loader" id="loader" size="15" id="loader"
							data-toggle="lookup" data-title="发行人选择" data-url="<%=request.getContextPath()%>/user/lookupload.do" data-width="800" data-height="400">
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue" id="createButton">保存并继续</button></li>
    </ul>
</div>
<script>
$("#loader").on("afterchange.bjui.lookup", function(e, data){
	$("#loader").blur();
})
function addprojectCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message, {autoClose:false,okCall:function(){$(this).dialog('closeCurrent');$(this).dialog({id:'dialog-edit',onClose:function(){$(this).navtab("refresh");},title:'编辑',width:1000,height:600,mask:true,url:'<%=request.getContextPath()%>/project/record/dxrz/edit.do?id='+json.id});}});
	}else {
		$(this).alertmsg("error", json.message);
	}
}
$('#projectadd').validator({
    fields: {
    	recordFullName: 'required;remote(<%=request.getContextPath()%>/project/record/dxrz/valid.do)',
    	loader: 'required'
    }
});
</script>
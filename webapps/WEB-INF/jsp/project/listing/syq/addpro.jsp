<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageContent">
	<form id="projectadd" action="<%=request.getContextPath()%>/project/listing/syq/add.do" data-toggle="validate" data-reload-navtab="true" data-confirm-msg="确定要保存吗？" data-callback="addprojectCallback">
		 <input type="hidden" name="projectRecordId" value="">
		 <table class="table table-condensed table-noborder table-hover">
				<tr>
					<td>
						<label class="control-label x100">业务类型：</label> 
						<span>金融产品发行</span>
					</td>
					<td>
						<label class="control-label x100">产品类型：</label> 
						<span id="projectTypeDesc"></span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x100">备案代码：</label> 
						<span><input readonly type="text" name="recordCode" id="recordCode" size="25" data-rule="required"
							data-toggle="lookup" data-title="备案选择" placeholder="请选择要挂牌的备案" data-url="<%=request.getContextPath()%>/project/listing/syq/recordlist.do" data-width="1000" data-height="600"></span>
					</td>
					<td>
						<label class="control-label x100" style="display:table-cell">备案名称：</label> 
						<span id="recordFullName" style="display:table-cell"></span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x100">发行人：</label> 
						<span id="loanUserName"></span>
					</td>
					<td>
						<label class="control-label x100">已挂牌次数：</label> 
						<span id="quotedCnt"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label class="control-label x100">挂牌名称：</label> 
						<input placeholder="建议设置挂牌名称能和备案名称对应" data-rule="required" type="text" name="projectFullName" size="25" maxlength="120"><span class="text-muted"></span>
					</td>
				</tr>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue">保存并继续</button></li>
    </ul>
</div>
<script>
$("#recordCode").on("afterchange.bjui.lookup", function(e, data){
	$("#projectTypeDesc").text(data.projectTypeDesc);
	$("#recordFullName").text(data.recordFullName);
	$("#loanUserName").text(data.loanUserName);
	$("#quotedCnt").text(data.quotedCnt);
	$("#recordCode").blur();
})
function addprojectCallback(json) {
	if(json.statusCode == 200) {
		$(this).alertmsg("correct", json.message, {autoClose:false,okCall:function(){$(this).dialog("closeCurrent");$(this).dialog({id:"dialog-edit",onClose:function(){$(this).navtab("refresh");},title:'编辑',width:1000,height:600,mask:true,url:'<%=request.getContextPath()%>/project/listing/syq/edit.do?id='+json.id});}});
	}else {
		$(this).alertmsg("error", json.message);
	}
}
</script>

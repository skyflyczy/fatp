<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		function formcallback(json){
			if(json.statusCode == 200){
				$(this).alertmsg("correct", json.message).dialog("closeCurrent");
			}else{
				$(this).alertmsg("error", json.message);
			}
		}
		
		var ids = "";
		//保存
		function savePurview(){
			var treeObj = $.fn.zTree.getZTreeObj("ztree1");
			//获取所有选中的节点
			var nodes =  treeObj.getCheckedNodes(true);
			for(var i = 0; i < nodes.length; i++){
				if(nodes[i].noticeType) {
					ids += nodes[i].noticeType + ",";
				}
			}
			$("#types").val(ids);
		}
</script>
<!-- 增加操作员 -->
<div class="bjui-pageContent">
	<form id="mainForm" action="/sys/role/setnotice.do" data-toggle="validate" data-callback="formcallback">
		 <input type="hidden" name="roleId" value="${sysMemberRole.id}">
		 <input type="hidden" name="types" id="types">
		 <table class="table table-condensed table-hover" width="100%">
		 	<tbody>
		 		<tr>
					<td>
						<label class="control-label x110">角色名称：</label> 
						<span>${sysMemberRole.roleName}</span>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">接收的业务通知：</label> 
                        <div style="width:400px; height:400px; overflow:auto;" class="form-control">
							<ul id="ztree1" class="ztree" data-toggle="ztree" style="padding:0;"
								data-options='{
									nodes:${noticeTypes },
									checkEnable :true,
									expandAll : true
								}'>
			                </ul>
                        </div>
					</td>
				</tr>
			</tbody>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue" onclick="savePurview()">保存</button></li>
    </ul>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	var ids = "";
	//保存
	function savePurview(){
		var treeObj = $.fn.zTree.getZTreeObj("ztree1");
		//获取所有选中的节点
		var nodes =  treeObj.getCheckedNodes(true);
		for(var i = 0; i < nodes.length; i++){
			ids += nodes[i].id + ",";
		}
		$("#ids").val(ids);
	}
	function setpurview(json){
		if(json.statusCode == 200){
			$(this).dialog("closeCurrent")
				   .navtab('refresh');
		}else{
			$(this).bjuiajax('ajaxDone', json)
		}
	}
	
	
	//树加载成功之后操作
	function initCheckNode(event, treeId, treeNode, msg){
		//树对象
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		treeObj.expandAll(true);
		var roleMenuIds = $("#roleMenuids").val();
		if(roleMenuIds=="")
			return;
		var idArr = roleMenuIds.split(",");
		
		//节点
		var nodes = treeObj.transformToArray(treeObj.getNodes());
		for(var i = 0 ; i<nodes.length; i++){
			/* if(idArr.indexOf(nodes[i].id) >= 0){
				treeObj.checkNode(nodes[i], true, true);
			} */
			if(contains(nodes[i].id,idArr) && nodes[i].children.length < 1){
				treeObj.checkNode(nodes[i], true, true);
			}
			/* if(nodes[i].id == idArr[0]){
				treeObj.checkNode(nodes[i], true, true);
			} */
		}
	}
	
	function contains(obj,arr){
		for(var i = 0; i<arr.length;i++){
			if(obj == arr[i])
				return true;
		}
		return false;
	}
</script>
<!-- 分配角色权限 -->
<div class="bjui-pageContent" >
	<form action="${ctx}/sys/role/setpurview.do" data-toggle="validate" data-confirm-msg="确定要提交吗？" data-callback="setpurview">
		 <input type="hidden" name="roleId" value="${sysMemberRole.id}">
		 <input type="hidden" name="purviewIds" id="ids">
		 <input type="hidden" id="roleMenuids" value="${roleMenuIds}">
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
						<label class="control-label x110">分配如下权限：</label> 
			            <div style="width:400px; height:400px; overflow:auto;" class="form-control">
			                <ul id="ztree1" class="ztree" data-toggle="ztree" 
						data-options="{
							checkEnable :true,
							expandAll : true,
							maxAddLevel:3
						}"
						data-setting="{async:{enable: true,
									   dataType:'json',
									   url: '${ctx}/sys/role/getpurviewnodes.do',
									   otherParam:{'roleType': ${sysMemberRole.roleType}},
									   type:'post'
										},
										callback:{
												onAsyncSuccess:initCheckNode
										}
									}">
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
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-blue" data-icon="save" onclick="savePurview()">保存</button></li>
    </ul>
</div>

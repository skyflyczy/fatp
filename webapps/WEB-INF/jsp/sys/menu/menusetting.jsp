<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.sub_memu_box > div {padding-left:305px;}
.left_menu {float:right; width:300px; margin-left:-305px; margin-right:5px; overflow:auto; border:1px solid #c3ced5;}
.right_menu {float:right; width:100%; overflow:auto;border:1px solid #c3ced5;}
.bs-example { padding:10px;}
#MenuIcon { display:inline-block; width:50px;}
.btn_box { padding:10px;} 
</style>
<script>
var appId = ${appId};//初始appId
//菜单布局
function resizeH(){
	var menuH=$('.left_menu').closest('.bjui-pageContent').height();
	$('.left_menu,.right_menu').height(menuH);
}
resizeH();
$(window).resize(resizeH);

function changeUpdateMenu(obj,oInp){
	oInp.prop('disabled',true);
	$('[data-toggle=selectpicker]').selectpicker('refresh');
	$(obj).html('<i class="fa fa-refresh"></i> '+'更新菜单');
	$('#choseImg').attr({'href':'javascript:;','data-toggle':null});
	$("#upflag").val("1");//用于判断变为更新还是保存按钮
}

function changeSaveMenu(obj,oInp){
	oInp.prop('disabled',false);
	$('[data-toggle=selectpicker]').selectpicker('refresh');
	$(obj).html('<i class="fa fa-save"></i> '+'保存菜单');
	$('#choseImg').attr({'href':'<%=request.getContextPath()%>/demo/list.do?vm=sys/menu/menufonts','data-toggle':'dialog'});
	$("#upflag").val("2");//用于判断变为更新还是保存按钮
}
//单击事件
function ZtreeClick(event, treeId, treeNode) {
    event.preventDefault()    
    var $detail = $('#ztree-detail');
    if ($detail.attr('tid') == treeNode.tId) return
	
	var pid = treeNode.getParentNode()!= null ?treeNode.getParentNode().id:0;//parentId
	var id =  typeof(treeNode.id) == 'undefined'?"":treeNode.id;//id
	var requrl = "${ctx}/sys/menu/menuinfo.do?id="+id+"&pid="+pid+"&appId="+appId;//请求路径 
	var options={
			'target':'#ztree-detail',
			'url':requrl,
			'type':'get',
			'loadingmask':true
	};
	$("#ztree-detail").bjuiajax('doLoad', options);
	var oInp=$('#ztree-detail input,#ztree-detail select,#ztree-detail textarea');
	changeUpdateMenu($('#refreshMenu'),oInp);
	$detail.attr('tid', treeNode.tId)
    $detail.show()
}
//保存属性
function M_Ts_Menu(obj) {
	var _this=this;
	var oInp=$('#ztree-detail input,#ztree-detail select,#ztree-detail textarea');
	var zTree  = $.fn.zTree.getZTreeObj("ztree1")
	
	var upNode = zTree.getSelectedNodes()[0]
	if (!upNode) {
		$(_this).alertmsg('error','未选中任何菜单！')
        return
	}
	if($("#upflag").val() == 1){
		//变成保存
		changeSaveMenu(obj,oInp);
	}else{
		var name   = $('#MenuName').val();//菜单名
		var url  = $('#MenuUrl').val();//url
		var relationUrl = $('#RelationUrl').val();//相关url
		var target = $('#Target').val()//target
		var menuType = $("#MenuType").val();//菜单类型
		var adminType = $("#AdminType :selected").val();//管理类型
		var isButton = $("#IsButton :selected").val();//是否为菜单
		var showIndex = $("#ShowIndex").val();//显示索引
		var remark = $("#Remark").val();//备注
		var pid = upNode.getParentNode()!= null ?upNode.getParentNode().id:0;
		var id =  typeof(upNode.id) == 'undefined'?'':upNode.id;
		if ($.trim(name).length == 0) {
			$(_this).alertmsg('error','菜单名称不能为空！')
			return;
		}
		
		//保存菜单
		 var datap = "{'menuName':'"+name+"','menuUrl':'"+url+"','relationUrl':'"+relationUrl+"',"+
			"'target':'"+target+"',"+
			"'menuType':'"+menuType+"','adminType':'"+adminType+"',"+
			"'isButton':'"+isButton+"','showIndex':'"+showIndex+"',"+
			"'remark':'"+remark+"','id':'"+id+"',"+
			"'parentId':'"+pid+"','appId':'"+appId+"'}"; 
		datap = eval('('+datap+')');
		$.post("${ctx}/sys/menu/save.do",
				datap,
				function(data){
				 if(data.retcode == 0){
					 $(_this).alertmsg('ok','添加/更新成功',{mask:true});
					 
					 //变成更新
					changeUpdateMenu(obj,oInp);
					//赋值
					upNode.id = data.id;
					upNode.name   = name;
					/*upNode.url    = url;
					upNode.target = target;
					upNode.menuType = menuType;
					upNode.adminType = adminType;
					upNode.isButton = isButton;
					upNode.showIndex = showIndex;
					upNode.remark = remark; */
					zTree.updateNode(upNode);
					$("#diyBtn_del_undefined").remove();
					$("#diyBtn_add_undefined").remove();
				 }else{
					 $(_this).alertmsg('error','添加/更新失败');
				 }
			   }	
			);
		
	}
	
}
//增加父级菜单
var newCount=1;
function add(e) {
	var zTree = $.fn.zTree.getZTreeObj("ztree1"),
	treeNode = zTree.addNodes(null, {pid:0,name:"新建菜单"+ (newCount++)});
	
	zTree.selectNode(treeNode[0]);
	$('#'+treeNode[0].tId).find('a:first').click();
	
};
$("#expand-collapseAllBtn").on("click", function(){
	var zTree = $.fn.zTree.getZTreeObj("ztree1");
	var b = $(this).attr("data-val");
	if(b == 1) {
		zTree.expandAll(true);
		$(this).attr({'data-val':0,'title':'折叠'}).html('<i class="fa fa-minus-circle"></i>'); 
	}else { 
		zTree.expandAll(false);
		$(this).attr({'data-val':1,'title':'展开'}).html('<i class="fa fa-plus-circle"></i>');
	}
})
//监听拖拽事件
function M_BeforeNodeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
    /*禁止插入层级为2的节点*/
    if (moveType == 'inner' && targetNode.level == 2) {
        return false
    }
    /*禁止插入剩余层级不足的子节点*/
    if (moveType == 'inner' && treeNodes[0].isParent) {
        var molevel = 2 - targetNode.level //剩余层级
        var maxlevel = 1
        var zTree = $.fn.zTree.getZTreeObj("ztree1")
        var nodes = zTree.transformToArray(treeNodes)
        var level = nodes[0].level
        
        for (var i = 1; i < nodes.length; i++) {
            if (nodes[i].level == (level + 1)) {
                maxlevel++
                level++
            }
        }
        if (maxlevel > molevel) {
            return false
        }
    }
    return true
}
/**
 * 删除节点
 */
function deleteNode(treeId, treeNode){
	if(typeof(treeNode.id) == 'undefined'){
		return true;
	}
	var flag = false;
	$.ajax({
		   url: "${ctx}/sys/menu/delete.do",
		   async: false,
		   type: "POST",
		   data: {id:treeNode.id},
		   success: function(data){
			   if(data.ok != 'undefined'){
				   flag =  true;
				}else{
					flag = false;
				}
		   }
		 });
	
	return flag;
}
//删除结束事件
function M_NodeRemove(event, treeId, treeNode) {
	$(this).alertmsg('ok','删除成功');
}

//树加载成功之后操作
function initCheckNode(event, treeId, treeNode, msg){
	//树对象
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	treeObj.expandAll(true);
}

/**
 * App切换
 */
function selectApp(obj){
	var val = $(obj).find(":selected").val();
	if(val == appId){
		return;
	}
	$(this).navtab({url:'${ctx}/sys/menu/setmenu.do?appId='+val,title:'菜单设置'});
}

</script>
<!--菜单设置  -->
<div class="bjui-pageContent">
    <input type="hidden" id="upflag" value="1"/>
	<div class="sub_memu_box">
			<div class="clearfix">
				<div id="ztree-detail" class="right_menu">
					<%@ include file="menusettingedit.jsp"%>
				</div>
				<div class="left_menu">
					<div class="btn_box">
						<button class="btn btn-blue" onclick="add();">新建菜单</button>
						<select data-toggle="selectpicker" id="appId" data-width="auto" onchange="selectApp(this);">
							<c:forEach items="${sysApps}" var="sysApp">
							<option value="${sysApp.id}" 
									<c:if test="${sysApp.id == appId}">
									selected = "selected"
								</c:if>>${sysApp.appName }</option>
							</c:forEach>
						</select>
						<a id="expand-collapseAllBtn" href="javascript:;" title="折叠" data-value="0" style="font-size:20px;vertical-align: middle;"><i class="fa fa-minus-circle"></i></a>
					</div>
					<ul id="ztree1" class="ztree" data-toggle="ztree" 
						data-options="{
							expandAll: true,
							onClick: 'ZtreeClick',
							addHoverDom:'edit',
							removeHoverDom:'edit',
							maxAddLevel:3,
							editEnable:true,
							beforeDrop:'M_BeforeNodeDrop',
							onDrop:'M_NodeDrop',
							onRemove:'M_NodeRemove',
							beforeRemove:'deleteNode'
						}"
						data-setting="{async:{enable: true,
									   dataType:'json',
									   url: '${ctx}/sys/menu/getmenunodes.do',
									   otherParam:{'appId': ${appId}},
									   type:'post'
										},
										callback:{
												onAsyncSuccess:initCheckNode
										}
										}"
					></ul>
				</div>
			</div>
		</div>
<!--</form>-->
</div>

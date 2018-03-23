<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function validMenuName(element){
	var parentId = 0;
	<c:if test="${sysMenu.parentId != null}">
	parentId = ${sysMenu.parentId};
	</c:if>
	if($("#menuNameVal").val() == element.value)
		return true;
	return $.post( "${ctx}/sys/menu/validmenuname.do",
			{"menuName":element.value,
			 "parentId":parentId
			},
			function(data){
			}
		);
}
</script>
	<div class="bs-example" data-content="详细信息">
	<form>
		<div class="form-group">
			<label for="MenuName" class="control-label x120">菜单名称：</label>
			<input type="hidden" id="menuNameVal" value="${sysMenu.menuName}">
			<input type="text" class="form-control validate[required] required" name="menuName"  id="MenuName" value="<c:out value='${sysMenu.menuName}'/>" placeholder="菜单名称" data-rule="required;"  data-rule-validMenuName="validMenuName" disabled />
		</div>
		<div class="form-group">
			<label for="MenuType" class="control-label x120">菜单类型：</label>
			<input type="hidden" id="MenuType" name="menuType" value="${sysMenu.menuType}">
			<c:if test="${sysMenu.menuType == 1}">产品管理运营平台</c:if>
		</div>
		<div class="form-group">
			<label for="AdminType" class="control-label x120">管理类型：</label>
			<select data-toggle="selectpicker" name="adminType" id="AdminType" data-width="auto" disabled >
				<option value="">请选择菜单类型</option>
				<c:choose>
				<c:when test="${sysMenu.adminType == null}">
					<option value="1" <c:if test="${sysMenu.adminType == 1}">selected="selected"</c:if>>系统管理</option>
					<option value="2" <c:if test="${sysMenu.adminType == 2}">selected="selected"</c:if>>普通业务</option>
				</c:when>
				<c:when test="${sysMenu.adminType == 1}">
					<option value="1" <c:if test="${sysMenu.adminType == 1}">selected="selected"</c:if>>系统管理</option>
				</c:when>
				<c:otherwise>
					<option value="2" <c:if test="${sysMenu.adminType == 2}">selected="selected"</c:if>>普通业务</option>
				</c:otherwise>
				</c:choose>
			</select>
		</div>
		<div class="form-group">
			<label for="MenuUrl" class="control-label x120">链接地址：</label>
			<input type="text" class="form-control" name="menuUrl" id="MenuUrl" value="<c:out value='${sysMenu.menuUrl}'/>" size="60" placeholder="Url" disabled />
		</div>
		<div class="form-group">
			<label for="RelationUrl" class="control-label x120">相关链接地址：</label>
			<textarea class="selectpicker show-tick" name="relationUrl" id="RelationUrl" cols="60" rows="5" style="resize:none;" disabled ><c:out value='${sysMenu.relationUrl}'/></textarea>
		</div>
		<div class="form-group">
			<label for="Target" class="control-label x120">打开方式：</label>
			<select data-toggle="selectpicker" name="target" id="Target" data-width="auto" disabled >
				<option value="">请选择打开方式</option>
				<option value="1" <c:if test="${sysMenu.target == 1}">selected="selected"</c:if>>navTab</option>
				<option value="2" <c:if test="${sysMenu.target == 2}">selected="selected"</c:if>>dialog</option>
			</select>
		</div>
		<div class="form-group">
			<label for="IsButton" class="control-label x120">是否是button：</label>
			<select data-toggle="selectpicker" name="isButton" id="IsButton" data-width="auto" disabled >
				<option value="1" <c:if test="${sysMenu.isButton == 1}">selected="selected"</c:if>>菜单</option>
				<option value="2" <c:if test="${sysMenu.isButton == 2}">selected="selected"</c:if>>按钮</option>
				<option value="3" <c:if test="${sysMenu.isButton == 3}">selected="selected"</c:if>>导航</option>
			</select>
		</div>
		<div class="form-group">
			<label for="ShowIndex" class="control-label x120">同一父id下的排序：</label>
			<input type="text" class="form-control" name="showIndex" id="ShowIndex" value="<c:out value='${sysMenu.showIndex}'/>" size="60" disabled />
		</div>
		<div class="form-group">
			<label for="Remark" class="control-label x120">备注：</label>
			<input type="text" class="form-control" name="remark" id="Remark" value="<c:out value='${sysMenu.remark}'/>" size="60" disabled />
		</div>
		</form>
		<div class="form-group" style="padding-top:8px; border-top:1px #DDD solid;">
			<label class="control-label x120"></label>
			<button class="btn btn-blue" data-icon="refresh" onclick="M_Ts_Menu(this);" id="refreshMenu">更新菜单</button>
		</div>
	</div>
<script>
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
		
function formcallback(json){
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message, {autoClose:false,mask:true,okCall:function(){$(this).dialog("closeCurrent").navtab('refresh');}});
	}else{
		$(this).bjuiajax('ajaxDone', json)
	}
}
		
</script>
<!-- 增加联行号 -->
<div class="bjui-pageContent">
	<form id="mainForm" action="<%=request.getContextPath()%>/sys/bankserinalno/update.do" data-toggle="validate" data-confirm-msg="确定要提交吗？" data-callback="formcallback">
		 <input type="hidden" name="id" value="${sysBankSerialno.id}">
		 <table class="table table-condensed table-hover">
		 	<tbody>
				<tr>
					<td>
						<label class="control-label x110">银行：</label> 
						 <select data-autoClose="true" data-live-search="true" name="bankId" data-toggle="selectpicker" data-width="100px" data-rule="required">
              				<option value="">--请选择--</option>
				             <c:forEach var="obj" items="${bankList}"> 
				             	<option value="${obj.id}" <c:if test="${obj.id == sysBankSerialno.bankId}">selected="selected"</c:if>>${obj.bankName}</option>
				             </c:forEach>                   
            		 	 </select>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">所属省份/城市：</label> 
						<select data-autoClose="true"  name="provinceId" data-nextselect="#cityId1" data-toggle="selectpicker" data-refurl="<%=request.getContextPath()%>/systype/city.do?proId={value}">
                			<option value="">--省份--</option>
			                <c:forEach var="obj" items="${provinceList}"> 
			    				<option value="${obj.proId}" <c:if test="${obj.proId == sysBankSerialno.provinceId}">selected="selected"</c:if>>${obj.proName}</option>
			    			</c:forEach>
              			</select>
		              <select data-autoClose="true"  name="cityId" id="cityId1" data-toggle="selectpicker">
               			<option value="">--城市--</option>
               			<c:forEach var="obj" items="${cityList}"> 
			    				<option value="${obj.cityId}" <c:if test="${obj.cityId == sysBankSerialno.cityId}">selected="selected"</c:if>>${obj.cityName}</option>
			    			</c:forEach>
             		  </select>
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">分(支)行名称：</label> 
						<input type="text" name="subBankName" value="${sysBankSerialno.subBankName}" class="form-control" data-rule="required">
					</td>
				</tr>
				<tr>
					<td>
						<label class="control-label x110">分(支)行联行号：</label> 
						<input type="text" name="subBankNo" value="${sysBankSerialno.subBankNo}" class="form-control" data-rule="required">
					</td>
				</tr>
			</tbody>
		 </table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="submit" class="btn btn-blue">保存</button></li>
    </ul>
</div>
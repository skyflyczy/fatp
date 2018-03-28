<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function validlength(element){
	var value = element.value.replace(" ", "");
	if(value.length>=4 && value.length<=30){
		return true;
	}else{
		return "账户名称长度必须为4-30位";
	}
}
function bankcardAddCallback(json) {
	if(json.statusCode == 200){
		$(this).alertmsg("correct", json.message);
		$(this).bjuiajax('refreshLayout', {target:'#bankinfo',url:$("#bankinfo_link").attr('href')});
		$('#bankinfo').removeClass('bjui-layout')
	}else{
		$(this).alertmsg("error", json.message);
	}
}
function refreshSubbank() {
	$("#bank_provinceId,#bank_cityId,#bank_bankSerialId").removeAttr("disabled").val("").selectpicker("refresh");
    $('.bank-list').hide();
    $('#choosebank').show();
}
$(function(){
	$('.bank-list').on('click','.bank-panel',function(){
		$("#bankName").text($(this).text());
		$("#bankId").val($(this).attr("data-id"));
		refreshSubbank();
	})
	$('#choosebank').on('click',function(){
		$('.bank-list').show();
		$(this).hide();
	})
	$('#bankId').on('afterchange.bjui.lookup', function(e, data) {
        $("#bankName").text(data.bankName);
        refreshSubbank();
    })
    $('#cancelBtn').click(function(){
		$('#bank-wrap').html('')
    })
})
</script>
<div style="padding:10px;">
	<div class="panel panel-default">
		<div class="panel-heading"><c:if test="${empty bankcard }">新增</c:if><c:if test="${not empty bankcard }">编辑</c:if>银行卡</div>
	  	<div class="panel-body">
	    	<form action="<%=request.getContextPath()%>/user/bankcard/update.do" data-toggle="validate"
	     		data-reload-navtab="true" data-callback="bankcardAddCallback" data-loadingmask="true">
			     <input type="hidden" name="userId" value="${userId }">
			     <input type="hidden" name="id" value="${bankcard.id }">
				 <div class="form-group">
				 	<label class="control-label x110">开户银行：</label>
				 	<span id="bankName" class="form-control-static" style="display:inline-block;min-width:200px;"><c:if test="${not empty bankcard }">${bankcard.channelName }</c:if></span>
				 	<a href="javascript:;" id="choosebank" <c:if test="${empty bankcard }">style="display:none"</c:if>>选择其他银行</a>
				 	<input type="hidden" id="bankId" name="bankId" value="${bankcard.bankId }"/>
				 </div>
				 <div class="container-fluid bank-list" <c:if test="${not empty bankcard }">style="display:none"</c:if>>
				 	<div class="row text-center">
				 		<c:forEach var="obj" items="${bankList}"> 
					 	<div class="col-xs-3">
					 		<div class="panel panel-default bank-panel" data-id="${obj.id }">
					 			<div class="panel-body bank-icon bank-icon-${obj.bankNo }" data-id="${obj.id }">${obj.bankSname }</div>
					 		</div>
						</div>
						</c:forEach> 
					</div>
					<a href="<%=request.getContextPath()%>/sys/bank/search.do" data-width="800" data-toggle="lookupbtn" data-title="支持的银行" class="more-bank">更多银行 <i class="fa fa-circle-plus"></i></a>
				 </div>
				 <div class="form-group">
					<label class="control-label x110">银行账户名：</label>
					<span class="form-control-static" style="display:inline-block;">${accountName}</span>
				 </div>
				 <div class="form-group">
					<label for="cardAccount" class="control-label x110">银行卡号：</label>
					<input class="input-nm bank-format" size="33" type="text" name="cardAccount" id="cardAccount" value="${bankcard.cardAccount}" data-rule="required;validlength" data-rule-validlength="validlength"/>
				 </div>
				 <div class="form-group">
					<label for="cardAccount" class="control-label x110">开户行所在地：</label>
					<select <c:if test="${empty bankcard }">disabled</c:if> data-style="btn-default btn-nm" data-autoClose="true" data-rule="required" id="bank_provinceId" name="provinceId" data-toggle="selectpicker" data-nextselect="#bank_cityId" data-refurl="<%=request.getContextPath()%>/sys/area/city.do?proId={value}">
			            <option value="">--省份--</option>
			            <c:forEach var="obj" items="${provinceList}"> 
						<option value="${obj.proId}" <c:if test="${obj.proId==bankcard.provinceId}">selected</c:if>>${obj.proName}</option>
						</c:forEach>
			       	</select>
			       	<select <c:if test="${empty bankcard }">disabled</c:if> data-style="btn-default btn-nm" data-autoClose="true" data-rule="required" id="bank_cityId" name="cityId" data-toggle="selectpicker" data-nextselect="#bank_bankSerialId" data-refurl="<%=request.getContextPath()%>/sys/bank/bankserialno.do?bankId={bankId}&cityId={cityId}" data-param='{"bankId":"bankId","cityId":"bank_cityId"}'>
			       		<option value="">--城市--</option>
			       		<c:forEach var="obj" items="${cityList}"> 
						<option value="${obj.cityId}" <c:if test="${obj.cityId==bankcard.cityId}">selected</c:if>>${obj.cityName}</option>
						</c:forEach>
			       	</select>
			       	<select <c:if test="${empty bankcard }">disabled</c:if> data-style="btn-default btn-nm" data-autoClose="true" data-live-search="true" data-rule="required" id="bank_bankSerialId" name="bankSerialId" data-emptytxt="--请选择--" data-toggle="selectpicker">
			        	<option value="">--请选择--</option>
			        	<c:forEach var="obj" items="${bankSerialList}"> 
						<option value="${obj.id}" <c:if test="${obj.id==bankcard.bankSerialId}">selected</c:if>>${obj.subBankName}</option>
						</c:forEach>
			        </select>
				 </div>
				 <p class="text-center">
		 			<button type="submit" class="btn-primary">保存</button>
		 			<button type="button" class="btn-red" id="cancelBtn">取消</button>
		 		 </p>
			</form>
		</div>
	</div>
</div>

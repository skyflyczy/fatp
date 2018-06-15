<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.my-table > thead > tr > th { background: #fff;}
.my-table td {overflow:hidden; white-space:nowrap;}
.panel-block-lg{margin-bottom:10px;}
.panel-block-lg .panel-body {height:390px;overflow:auto;}
.projectShow {margin-bottom:20px;}
.projectShow .btn{position:relative;margin:10px;padding:10px;width:100%;height:auto;background-color:#6CA6CD;color:#fff;}
.projectShow .badge{background:#fff;color:#CD3333;margin-left:5px;font-size:16px;}
.link{position:absolute;left:0;right:0;top:0;bottom:0;}
</style>
<script type="text/javascript">
function re(){
	$(this).bjuiajax('refreshDiv', 'home-pageContent');
	
}
</script>
<div class="bjui-pageHeader">
	<h4 class="pull-left">欢迎进入${exchange.ptomsSysName }</h4><h4 class="pull-right"><!-- <button type="button" class="btn btn-green pull-right" data-icon="refresh" onclick="$(this).navtab('refresh')">刷新</button> -->累计交易量：<fmt:formatNumber value="${summoney }" currencySymbol="" type="currency" maxFractionDigits="2" minFractionDigits="2"/> 元</h4>	
</div>
<div class="bjui-pageContent" id="pageCont">
	<div class="container-fluid">
		<div class="row text-center projectShow">
		</div>
		<div class="row">
			<div class="col-md-7">	
				<div class="panel panel-default panel-block-lg">
					<div class="panel-heading">
 						<h2 class="panel-title"><b><i class="fa fa-clock-o" style="font-size:22px;"></i>&nbsp;&nbsp;发行认购中产品</b></h2>
					</div>
					<div class="panel-body">
						<table class="table table-hover my-table" style="table-layout: fixed;">
							<thead>
								<tr>
									<th width="35%">产品信息</th>
									<th width="30%">融资信息</th>
									<th width="35%">认购信息</th>
								</tr>
							</thead>
							<tbody id="home_tbody1">								
							</tbody>	  
						</table>
					</div>											
				</div>
			</div>
			<div class="col-md-5">	
				<div class="panel panel-default panel-block-lg">
					<div class="panel-heading">
 						<h2 class="panel-title"><b><i class="fa fa-bullhorn" style="font-size:22px;"></i>&nbsp;&nbsp;上线公告</b></h2>
					</div>
					<div class="panel-body">
					  <p>2017年07月18日，上线内容：</p>
					  <p class="text-indent">1、备案挂牌前端页面改造</p>
					  <p class="text-indent">2、机构端新增和复制功能的调整</p>
					  <p class="text-indent">3、备案三级审批改为两级审批</p>
					  <p class="text-indent">4、挂牌申请书调整</p>
				  </div>	
				</div>
			</div>
		</div>
		
	</div>
</div>

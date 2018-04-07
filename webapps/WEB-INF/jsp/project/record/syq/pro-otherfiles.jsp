<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
#tabledit3 tbody tr:first-child td{border-top:none}
</style>
<p>温馨提示：请确保上传的文件内容清晰，不得任意涂改。支持pdf文件格式，大小控制在10MB内。</p>
<div class="bjui-pageContent tableContent">
    <form  action="" id="j_custom_form" class="pageForm" data-toggle="validate" method="post">
    	<table id="tabledit2" data-reload-navtab="true" class="table table-hover" data-toggle="tabledit" data-initnum="0" data-action="<%=request.getContextPath()%>/income/right/profiles/edit.do?projectId=${projectId}&belongType=1" data-single-noindex="true" data-callback="afterEdit3">
           	<col style="width:8%" />
			<col style="width:12%" />
			<col style="width:20%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:7%" />
			<col style="width:18%" />
           <thead>
                <tr>
                    <th align="center" data-align="center" title="附件编号" data-noedit="true"><input type="text" name="id"  size="2" readonly></th>
                	<th align="center" data-align="center" title="附件类型" data-noedit="true"></th>
                    <th title="附件名称"><input type="text" name="infoName" data-rule="required" value="" size="5" data-autoClose="true" maxlength="30"></th>
                    <th align="center" data-align="center" title="更新时间"></th>
                    <th title="上传附件"></th>
                    <th align="center" data-align="center" title="对外披露"><input type="checkbox" id="fileIsOpen_[#index#]"  name="fileIsOpen" class="j-icheck" data-toggle="icheck" value="true"></th>
                    <th align="center" data-align="center">操作&nbsp;&nbsp;<button type="button" class="btn-green" data-toggle="tableditadd" data-target="#tabledit3" data-num="1" data-icon="plus">新增</button>
                        <input type="hidden" name="fileName" class="pic-name" value="" id="projectFiles_#index#" >
                    </th>
                </tr>
            </thead>
            <tbody>
        		<c:forEach var="obj" items="${mustList}" varStatus="status"> 
        		<tr align="center">
                    <td data-noedit="true">${obj.id }<input type="hidden" value="${obj.id }" name="id"/></td>
                	<td><span class="red">*</span>${obj.typeName }<input type="hidden" value="${obj.infoType }" name="infoType"/></td>
                    <td align="left">${obj.infoName }</td>
                    <td><fmt:formatDate value="${obj.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="left" data-val="${obj.allFileNames()}">
                    	<c:if test="${not empty obj.file1}">
	            		<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file1Link()}">${obj.file1Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file2}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file2Link()}">${obj.file2Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file3}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file3Link()}">${obj.file3Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file4}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file4Link()}">${obj.file4Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file5}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file5Link()}">${obj.file5Show()}</a>
		            	</c:if>
                    </td>
                    <td <c:if test="${empty obj.fileIsOpen || obj.fileIsOpen == 1}">data-val="true"</c:if>></td>
                    <td data-noedit="true">
                    	<div class="inline-upload"
                        	data-toggle="upload"  
                            data-uploader="//<%=request.getServerName() %>/income/right/profiles/upload.do" 
                            data-file-type-exts="${fileType }"
                            data-auto="true" 
                            data-button-text="上传" 
                            data-width="50"
                            data-form-data='{"projectId":${projectId},"belongType":1,"j":"${jsessionid}"}'
                            data-on-upload-success="custom_pic_upload_success3"></div>
                    	<input type="hidden" name="fileName" class="pic-name" value="${obj.allFileNames() }" id="projectFiles_#index#"  data-rule="required" data-msg-required="请上传附件" data-autoClose="true">
                        <button type="button" class="btn-blue" data-toggle="doedit" onclick="showUpload3(${ status.index + 1})">编辑</button>
                    </td>
                </tr>
        		</c:forEach>
        	</tbody>
        </table>
        <table id="tabledit3" data-reload-navtab="true" class="table table-hover" data-toggle="tabledit" data-initnum="0" data-action="<%=request.getContextPath()%>/income/right/profiles/edit.do?projectId=${projectId}&belongType=1" data-single-noindex="true" data-callback="afterEdit3">
           	<col style="width:8%" />
			<col style="width:12%" />
			<col style="width:20%" />
			<col style="width:15%" />
			<col style="width:20%" />
			<col style="width:7%" />
			<col style="width:18%" />
           	<thead style="display:none">
                <tr>
                    <th data-noedit="true" data-align="center"><input type="text" name="id"  size="2" readonly></th>
                	<th data-noedit="true" data-align="center">
                	<select data-autoClose="true" data-rule="required" name="infoType" data-toggle="selectpicker">
                       <c:forEach var="obj" items="${filetypes}"> 
           					<option value="${obj.typeId}">${obj.typeName}</option>
           				</c:forEach>
                    </select>
                	</th>
                    <th><input type="text" name="infoName" data-rule="required" value="" size="5" data-autoClose="true" maxlength="30"></th>
                    <th data-align="center"></th>
                    <th></th>
                    <th data-align="center"><input type="checkbox" id="fileIsOpen_[#index#]"  name="fileIsOpen" class="j-icheck" data-toggle="icheck" value="true"></th>
                    <th data-addbtn="true" data-align="center">
                    	 <div class="inline-upload"
                        	data-toggle="upload"  
                            data-uploader="//<%=request.getServerName() %>/income/right/profiles/upload.do" 
                            data-file-type-exts="${fileType }"
                            data-auto="true" 
                            data-button-text="上传" 
                            data-width="50"
                            data-form-data='{"projectId":${projectId},"belongType":1,"j":"${jsessionid}"}'
                            data-on-upload-success="custom_pic_upload_success3"
                            data-upload-limit="99"></div>
                        <input type="hidden" name="fileName" class="pic-name" value="" id="projectFiles_#index#" data-rule="required" data-msg-required="请上传附件" data-autoClose="true">
                        <a href="javascript:;" class="btn btn-blue" data-toggle="dosave">保存</a>
                        <a href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" id="_#index#" data-okcallback="delFile3">删除</a>
                    </th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="obj" items="${list}" varStatus="status"> 
                <tr align="center">
                    <td data-noedit="true">${obj.id }<input type="hidden" value="${obj.id }" name="id"/></td>
                	<td data-val="${obj.infoType }" ></td>
                    <td align="left">${obj.infoName }</td>
                    <td><fmt:formatDate value="${obj.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="left" data-val="${obj.allFileNames()}">
                    	<c:if test="${not empty obj.file1}">
	            		<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file1Link()}">${obj.file1Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file2}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file2Link()}">${obj.file2Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file3}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file3Link()}">${obj.file3Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file4}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file4Link()}">${obj.file4Show()}</a>
		            	</c:if>
		            	<c:if test="${not empty obj.file5}">
		            		|<a target="_blank" href="${accessPath}p${obj.projectFileGuid},${obj.file5Link()}">${obj.file5Show()}</a>
		            	</c:if>
                    </td>
                    <td <c:if test="${obj.fileIsOpen == 1}">data-val="true"</c:if>></td>
                    <td data-noedit="true">
                    	<div class="inline-upload"
                        	data-toggle="upload"  
                            data-uploader="//<%=request.getServerName() %>/income/right/profiles/upload.do" 
                            data-file-type-exts="${fileType }"
                            data-auto="true" 
                            data-button-text="上传" 
                            data-width="50"
                            data-form-data='{"projectId":${projectId},"belongType":1,"j":"${jsessionid}"}'
                            data-on-upload-success="custom_pic_upload_success3"></div>
                    	<input type="hidden" name="fileName" class="pic-name" value="${obj.allFileNames() }" id="projectFiles_#index#" data-rule="required">
                        <button type="button" class="btn-blue" data-toggle="doedit" onclick="showUpload3(${ status.index + 1})">编辑</button>
                        <a href="<%=request.getContextPath()%>/income/right/profiles/delete.do?id=${obj.id }&belongType=1" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？" data-callback="afterDel3">删除</a>
                    </td>
                </tr>
			</c:forEach>
            </tbody>
        </table>
    </form>
</div>
<script type="text/javascript">
//如果该会员有电子签章，需要先初始化两个插件
if(${hasSeal} == 1){
	var ver=parseInt($.getExplorerInfo.version.substring(0,$.getExplorerInfo.version.indexOf(".")));
	if(($.getExplorerInfo.type=='Chrome' && $.getExplorerInfo.platform=='32' && (ver>=28 && ver<=41)) || ($.getExplorerInfo.type=='IE' && (ver>=8 && ver<=11))){
		var agent = new SignAgent();
		 if($.hasSignAgent) {
			//如果有电子签章,先选择证书
	 	 	try {
	 	 		agent.select("","","${certSn}");
	 	   	}catch (e) {
	 	   		alertSignErr(e);
	 	    }
		 }
	}else{
		$(this).alertmsg("warn", '<h5 class="text-center"><b style="font-size:16px;">浏览器版本不支持</b></h5><h5 class="text-center">请更换浏览器，<a style="font-size:14px;cursor:pointer;" data-toggle="modal" data-target="#myModal">查看可用版本的浏览器</a></h5>', {"autoClose":false});
	}
}
function custom_pic_upload_success3(file, data, $upload) {
    var json = $.parseJSON(data)
    if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
    	var arr = json.fileName.split(":");
    	var showFileName,linkFileName;
    	if(arr.length == 1) {
    		showFileName = linkFileName = json.fileName;
    	}else {
    		showFileName = arr[0];
    		linkFileName = arr[1];
    	}
        var obj =$upload.next();
    	if (!window.FileReader && $.hasFlash.v >= 9){
    		obj =  $('#'+$upload.attr('id')).parent().next();	
		}
    	var oldFiles = obj.val();
    	oldFiles = oldFiles.length == 0 ? (json.fileName) : oldFiles + "|" + (json.fileName);
    	var html = '<a href="'+(json.accessPath+linkFileName) +'" target="_blank">'+showFileName+'</a>';
    	var fileName = showFileName.substring(0, showFileName.lastIndexOf('.'));
	   	if(!json.hasSeal  || !json.sealSessionId) {
	   		obj.parent().siblings("td").eq(2).children().val(fileName);		
	    	obj.parent().siblings("td").eq(4).html(html);
	    	obj.val(json.fileName);
	   	}else {
	   		var ver=parseInt($.getExplorerInfo.version.substring(0,$.getExplorerInfo.version.indexOf(".")));
	   		if(($.getExplorerInfo.type=='Chrome' && $.getExplorerInfo.platform=='32' && (ver>=28 && ver<=41)) || ($.getExplorerInfo.type=='IE' && (ver>=8 && ver<=11))){
		   	   	if(!$.hasSignAgent) {
		   	   		alertSignErr();
		   	   		return;
		   	   	}
	   	 	 	try {
	   	 	 		agent.select("","",json.certSn);
	   	 	   	}catch (e) {
	   	 	   		alertSignErr(e);
	   	 	   		return;
	   	 	    }
		   	   	try {
	 				//进行签名
	 				 $.post(
			    		"<%=request.getContextPath()%>/income/right/profiles/dofilesign.do",
			    		{"signature":agent.sign(json.fileHash),
			    			"projectId":${projectId},
			    			"belongType":1,
			    		 "sealSessionId":json.sealSessionId,
			    		 "fileName":linkFileName
			    		},
			    		function(j){
			    			if(j.statusCode == 200){
			    				//成功
			    				$(this).alertmsg('ok', '<h5 class="text-center"><b style="font-size:16px;">签章成功，请保存</b></h5>');
			    				obj.parent().siblings("td").eq(2).children().val(fileName);		
			    		    	obj.parent().siblings("td").eq(4).html(html);
			    		    	obj.val(json.fileName);
			    			}else{
			    				//失败
			    				$(this).alertmsg('error', '<h5 class="text-center"><b style="font-size:16px;">签章失败</b></h5><h5 class="text-center">原因：'+j.message+'</h5>');
			    			}
			    		}
			    	 );
			    }catch(e) {
			    	alertSignErr(e);
			    }
	   		}else{
	   			$(this).alertmsg("warn", '<h5 class="text-center"><b style="font-size:16px;">浏览器版本不支持</b></h5><h5 class="text-center">请更换浏览器，<a style="font-size:14px;cursor:pointer;" data-toggle="modal" data-target="#myModal">查看可用版本的浏览器</a></h5>', {"autoClose":false});
	   		}
	    }
    }
}
function afterEdit3(json){
	if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
		$(this).alertmsg('ok',json.message)
		$(this).bjuiajax('refreshLayout', {target:'#otherfiles',url:$("#otherfiles_link").attr('href')});
		$('#otherfiles').removeClass('bjui-layout')
	}else
		$(this).alertmsg('error',json.message);
}
function afterDel3(json){
	if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) 
		$(this).alertmsg('ok',json.message);
	else
		$(this).alertmsg('error',json.message);
	
	//$(this).bjuiajax('refreshDiv', 'otherfiles')
	$(this).bjuiajax('refreshLayout', {target:'#otherfiles',url:$("#otherfiles_link").attr('href')});
	$('#otherfiles').removeClass('bjui-layout')
}
function showUpload3(id){
	$('tr[data-id='+id+']').find('div').removeAttr('data-disabled',false);
}
//没保存删除
function delFile3($element){
	var picNames = $("#projectFiles"+$element.attr("id")).val();
	if(picNames !='undefiend' && picNames !=""){
		var options ="{'projectId':'"+${projectId}+"','belongType':1,'picNames':'"+picNames+"'}";
		options = eval('('+options+')');
		$.post("<%=request.getContextPath()%>/income/right/profiles/delfile.do",options,function(data){
		});
	}
}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 其他附件 -->
<script type="text/javascript">
function uploadSuccess(file, data, $upload) {
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
    	var html = '|<a href="'+(json.accessPath+linkFileName) +'" target="_blank">'+showFileName+'</a>';
    	var obj = $upload.next();
    	if (!window.FileReader && $.hasFlash.v >= 9){
    		obj =  $('#'+$upload.attr('id')).parent().next();	
		}
    	var oldFiles = obj.val();
    	oldFiles = oldFiles.length == 0 ? (json.fileName) : oldFiles + "|" + (json.fileName);
    	obj.val(oldFiles).next().append(html);
    }
}
function afterEdit(json){
	if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
		$(this).alertmsg('ok',json.message)
		$(this).bjuiajax('refreshLayout', {target:'#files',url:$("#files_link").attr('href')});
		$('#files').removeClass('bjui-layout')
	}else
		$(this).alertmsg('error',json.message);
}
</script>
<div class="bjui-pageContent tableContent">
    <form action="" id="j_custom_form" class="pageForm" data-toggle="validate" method="post">
        <table id="userfiles" class="table table-hover table-striped" data-toggle="tabledit" data-initnum="0" data-action="<%=request.getContextPath()%>/userfiles/edit.do" data-single-noindex="true" data-callback="afterEdit">
        	<col style="width:6%" />
        	<col style="width:10%" />
        	<col style="width:40%" />
        	<col style="width:30%" />
        	<col style="width:14%" />
        	<thead>
                <tr data-idname="id">
                    <th title="文件id" align="center" data-align="center" data-noedit="true"><input type="hidden" name="userId" value="${userId}"><input type="hidden" name="fileType" value="0"></th>
                    <th title="文件名称" align="center" data-align="center"><input type="text" id="infoName[#index#]" name="infoName" data-rule="required" value="" size="5" maxlength="50"></th>
                    <th title="文件说明"><input type="text" id="infoDesc[#index#]" name="infoDesc" data-rule="" value="" size="5" maxlength="100"></th>
                    <th title="文件">  
                    	<div data-toggle="upload"  
                           data-uploader="//<%=request.getServerName() %>/userfiles/upload.do?userId=${userId}&jsessionid=${jsessionid}"  
                           data-file-type-exts="${fileType}" 
                           data-multi="true" 
                           data-auto="true" 
                           data-on-upload-success="uploadSuccess"></div>
                        <input type="hidden" name="allFileNames" class="pic-name" id="projectFiles_#index#" value=""><span class="pic-box"></span>
                    </th>
                    <th title="新增" data-addbtn="true" align="center" data-align="center">
                        <a href="javascript:;" class="btn btn-blue" data-toggle="dosave">保存</a>
                        <a data-action="<%=request.getContextPath()%>/userfiles/del.do?id=" href="javascript:;" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删除</a>
                    </th>
                </tr>
            </thead>
            <tbody>
            	<tr align="center" <c:if test="${not empty 入会申请书  }">data-id="${入会申请书.id}"</c:if>><input type="hidden" name="userId" value="${userId}"><input type="hidden" name="fileType" value="1">
					<td data-noedit="true"><c:if test="${not empty 入会申请书  }">${入会申请书.id }</c:if></td>
					<td data-noedit="true"><input type="hidden" value="入会申请书" name="infoName">入会申请书</td>
					<td align="left" data-noedit="true">按要求填写完整，加盖公司公章后上传。</td>
					<td align="left" data-val="${入会申请书 .allFileNames()}">
						<c:if test="${not empty 入会申请书 .file1}">
                    		<a target="_blank" href="${accessPath}u${入会申请书 .userFileGuid},${入会申请书 .file1Link()}">${入会申请书.file1Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 入会申请书 .file2}">
                    		|<a target="_blank" href="${accessPath}u${入会申请书 .userFileGuid},${入会申请书 .file2Link()}">${入会申请书.file2Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 入会申请书 .file3}">
                    		|<a target="_blank" href="${accessPath}u${入会申请书 .userFileGuid},${入会申请书 .file3Link()}">${入会申请书.file3Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 入会申请书 .file4}">
                    		|<a target="_blank" href="${accessPath}u${入会申请书 .userFileGuid},${入会申请书 .file4Link()}">${入会申请书.file4Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 入会申请书 .file5}">
                    		|<a target="_blank" href="${accessPath}u${入会申请书 .userFileGuid},${入会申请书 .file5Link()}">${入会申请书.file5Show()}</a>
                    	</c:if>
					</td>
					<td data-noedit="true">
						 <button type="button" class="btn-blue" data-toggle="doedit">编辑</button>
					</td>
				</tr>
				<tr align="center" <c:if test="${not empty 经办人身份证 }">data-id="${经办人身份证.id}"</c:if>><input type="hidden" name="userId" value="${userId}"><input type="hidden" name="fileType" value="2">
					<td data-noedit="true"><c:if test="${not empty 经办人身份证  }">${经办人身份证.id }</c:if></td>
					<td data-noedit="true"><input type="hidden" value="经办人身份证" name="infoName">经办人身份证</td>
					<td align="left" data-noedit="true">身份证件需正反复印在一张纸上，加盖公章后上传。（身份证正面+反面照）</td>
					<td align="left" data-val="${经办人身份证.allFileNames()}">
						<c:if test="${not empty 经办人身份证 .file1}">
                    		<a target="_blank" href="${accessPath}u${经办人身份证 .userFileGuid},${经办人身份证.file1Link()}">${经办人身份证.file1Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 经办人身份证 .file2}">
                    		<a target="_blank" href="${accessPath}u${经办人身份证 .userFileGuid},${经办人身份证.file2Link()}">${经办人身份证.file2Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 经办人身份证 .file3}">
                    		<a target="_blank" href="${accessPath}u${经办人身份证 .userFileGuid},${经办人身份证.file3Link()}">${经办人身份证.file3Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 经办人身份证 .file4}">
                    		<a target="_blank" href="${accessPath}u${经办人身份证 .userFileGuid},${经办人身份证.file4Link()}">${经办人身份证.file4Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 经办人身份证 .file5}">
                    		<a target="_blank" href="${accessPath}u${经办人身份证 .userFileGuid},${经办人身份证.file5Link()}">${经办人身份证.file5Show()}</a>
                    	</c:if>
					</td>
					<td data-noedit="true">
						 <button type="button" class="btn-blue" data-toggle="doedit">编辑</button>
					</td>
				</tr>
				<tr align="center" <c:if test="${not empty 机构营业执照  }">data-id="${机构营业执照.id}"</c:if>><input type="hidden" name="userId" value="${userId}"><input type="hidden" name="fileType" value="3">
					<td data-noedit="true"><c:if test="${not empty 机构营业执照  }">${机构营业执照.id }</c:if></td>
					<td data-noedit="true"><input type="hidden" value="机构营业执照" name="infoName">机构营业执照</td>
					<td align="left" data-noedit="true">证件复印件请加盖公章后上传，如三证合一，不用上传组织机构证与税务登记证。</td>
					<td align="left" data-val="${机构营业执照 .allFileNames()}">
						<c:if test="${not empty 机构营业执照 .file1}">
                    		<a target="_blank" href="${accessPath}u${机构营业执照.userFileGuid},${机构营业执照.file1Link()}">${机构营业执照.file1Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 机构营业执照 .file2}">
                    		|<a target="_blank" href="${accessPath}u${机构营业执照.userFileGuid},${机构营业执照 .file2Link()}">${机构营业执照.file2Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 机构营业执照 .file3}">
                    		|<a target="_blank" href="${accessPath}u${机构营业执照.userFileGuid},${机构营业执照 .file3Link()}">${机构营业执照.file3Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 机构营业执照 .file4}">
                    		|<a target="_blank" href="${accessPath}u${机构营业执照.userFileGuid},${机构营业执照 .file4Link()}">${机构营业执照.file4Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty 机构营业执照 .file5}">
                    		|<a target="_blank" href="${accessPath}u${机构营业执照.userFileGuid},${机构营业执照 .file5Link()}">${机构营业执照.file5Show()}</a>
                    	</c:if>
					</td>
					<td data-noedit="true">
						 <button type="button" class="btn-blue" data-toggle="doedit">编辑</button>
					</td>
				</tr>
               	<c:forEach var="obj" items="${list}" varStatus="status">
                <tr align="center" data-id="${obj.id}"><input type="hidden" name="userId" value="${userId}"><input type="hidden" name="fileType" value="0">
                    <td data-noedit="true">${obj.id}</td>
                    <td>${obj.infoName}</td>
                    <td align="left">${obj.infoDesc}</td>
                    <td align="left" data-val="${obj.allFileNames()}">
                    	<c:if test="${not empty obj.file1}">
                    		<a target="_blank" href="${accessPath}u${obj.userFileGuid},${obj.file1Link()}">${obj.file1Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty obj.file2}">
                    		|<a target="_blank" href="${accessPath}u${obj.userFileGuid},${obj.file2Link()}">${obj.file2Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty obj.file3}">
                    		|<a target="_blank" href="${accessPath}u${obj.userFileGuid},${obj.file3Link()}">${obj.file3Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty obj.file4}">
                    		|<a target="_blank" href="${accessPath}u${obj.userFileGuid},${obj.file4Link()}">${obj.file4Show()}</a>
                    	</c:if>
                    	<c:if test="${not empty obj.file5}">
                    		|<a target="_blank" href="${accessPath}u${obj.userFileGuid},${obj.file5Link()}">${obj.file5Show()}</a>
                    	</c:if>
                    </td>
                    <td data-noedit="true">
                        <button type="button" class="btn-blue" data-toggle="doedit">编辑</button>
                        <a data-action="<%=request.getContextPath()%>/userfiles/del.do?id=" href="<%=request.getContextPath()%>/userfiles/del.do?id=${obj.id}" class="btn btn-red row-del" data-confirm-msg="确定要删除该行信息吗？">删除</a>
                    </td>
                </tr>
			</c:forEach>
            </tbody>
        </table>
    </form>
</div>

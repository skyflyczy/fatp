<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 其他附件 -->
<div class="bjui-pageContent tableContent">
	<table class="table table-hover">
      	<thead>
	       	<tr>
	    		<th align="center" width="10%">文件id</th>
	    		<th align="center" width="16%">文件名称</th>
	    		<th align="center" width="20%">文件说明</th>
	    		<th align="center" width="36%">文件</th>
	    		<th align="center" width="18%">最后上传时间</th>
	   		</tr>
        </thead>
        <tbody>
        	<c:forEach var="obj" items="${list}">
    		<tr>
	    		<td align="center">${obj.id }</td>
	    		<td>${obj.infoName}</td>
	    		<td>${obj.infoDesc}</td>
	    		<td>
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
	    		<td align="center"><fmt:formatDate value="${obj.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bjui-pageFooter">
    <div class="pages">
        <span>每页</span>
        <div class="selectPagesize">
            <select data-toggle="selectpicker" data-toggle-change="changepagesize">
                <option value="5" <c:if test="${pageSize==5}">selected</c:if>>5</option>
                <option value="10" <c:if test="${pageSize==10}">selected</c:if>>10</option>
                <option value="20" <c:if test="${pageSize==20}">selected</c:if>>20</option>
                <option value="40" <c:if test="${pageSize==40}">selected</c:if>>40</option>
                <option value="60" <c:if test="${pageSize==60}">selected</c:if>>60</option>
                <option value="120" <c:if test="${pageSize==120}">selected</c:if>>120</option>
            </select>
        </div>
        <span>条，共 ${total} 条</span>
    </div>

    <div class="pagination-box" data-toggle="pagination" data-total="${total}" data-page-size="${search.pageSize}" data-page-current="${search.pageCurrent}">
</div>
</div>
<script>
    /* zxc优化开始 */

    // 解决多个列表间的字段排序冲突问题
    $(".page.unitBox.fade.in > .bjui-pageHeader > #pagerForm > [name='orderField']").val("");
    $(".page.unitBox.fade.in > .bjui-pageHeader > #pagerForm > [name='orderDirection']").val("");

    // 解决多个列表间的分页大小冲突问题
    var selectedPagesize = $(".page.unitBox.fade.in > .bjui-pageFooter > .pages > .selectPagesize > select").val();
    $(".page.unitBox.fade.in > .bjui-pageHeader > #pagerForm > [name='pageSize']").val(selectedPagesize);
    $(".page.unitBox.fade.in > .bjui-pageFooter > .pagination-box").attr('data-page-size',selectedPagesize);

    //console.log("pageSize" + $(".page.unitBox.fade.in > .bjui-pageHeader > #pagerForm > [name='pageSize']").val());
    //console.log("selectedPagesize:" + selectedPagesize);
    //console.log("data-page-size:" + $(".page.unitBox.fade.in > .bjui-pageFooter > .pagination-box").attr('data-page-size'));

    /* zxc优化结束 */
</script>

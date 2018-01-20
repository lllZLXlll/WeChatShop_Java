<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 设置序号 -->
<c:set var="count" value="${(page.pageNum - 1) * page.pageSize}"></c:set> 

<!-- 表单 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${path }/admin/homeBanner" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNum}">
        <div class="bjui-searchBar">
        
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
        </div>
    </form>
</div>


<!-- 表格 -->
<table class="table table-bordered table-hover table-striped table-top">
    <thead>
        <tr height="50">
            <th title="编号">NO</th>
            <th title="链接商品名称">链接商品名称</th>
            <th title="轮播图">轮播图</th>
        </tr>
    </thead>
    <tbody>
    	<!-- 没有数据 -->
    	<c:choose>
            <c:when test="${page.page == null or page.page == '[]' }">
	           	<tr>
            		<td align="center" colspan="6">暂无数据</td>
            	</tr>
	        </c:when>
           	<c:otherwise>
	     		<!-- 循环输出数据  -->
		    	<c:forEach items="${page.page }" var="item" varStatus="status">
			        <tr height="90">
			        	<td>${status.index + 1 + count }</td>
			            <td>${item.name }</td>
			            <td><img src="${item.image }" width="180" height="90"/></td>
			        </tr>
		        </c:forEach>
	       	</c:otherwise>
	    </c:choose>
    
    </tbody>
</table>

<!-- 底部分页组件 -->
<div class="bjui-pageFooter">
	<div class="pages">
	    <span>每页 </span>
	    <div class="selectPagesize">
	        <select data-toggle="selectpicker" data-toggle-change="changepagesize">
	            <option value="10">10</option>
	            <option value="20" selected="selected">20</option>
	            <option value="50">50</option>
	        </select>
	    </div>
	    <span>&nbsp;条，共 ${page.pageTotalCount } 条， 共 ${page.pageTotalNum } 页</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${page.pageTotalCount }" data-page-size="${page.pageSize }" data-page-current="1"></div>
</div>
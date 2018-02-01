<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 设置序号 -->
<c:set var="count" value="${(page.pageNum - 1) * page.pageSize}"></c:set> 

<!-- 表单 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${path }/admin/productClass" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize }">
        <input type="hidden" name="pageCurrent" value="${page.pageNum }">
        <input type="hidden" name="tabid" value="${tabid }">
        <input type="hidden" name="id" value="${id }">
        
        <div class="bjui-searchBar">
        	<!-- 添加 -->
        	<a class="btn btn-blue" href="${path }/admin/productClassAddInit?tabid=${tabid}&productId=${id }" data-toggle="dialog" data-width="800" data-height="600" data-mask="true" data-title="添加分类">添加分类</a>
        </div>
    </form>
</div>


<!-- 表格 -->
<div class="bjui-pageContent">
	<table class="table table-bordered table-hover table-striped">
	    <thead>
	        <tr height="35">
	            <th title="编号" align="center">NO</th>
	            <th title="类型图片" align="center">类型图片</th>
	            <th title="类型名称" align="center">类型名称</th>
	            <th title="价格" align="center">价格</th>
	            <th title="库存" align="center">库存</th>
	            <th title="操作" align="center">操作</th>
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
				        <tr height="30">
				        	<td align="center">${status.index + 1 + count }</td>
				        	<td align="center">
				            	<a href="${item.productImage }" target="_blank">
				            		<img src="${item.productImage }" width="80" height="80"/>
			            		</a>
			            	</td>
				            <td align="center">${item.className }</td>
				            <td align="center">${item.price }</td>
				            <td align="center">${item.count }</td>
				            <td align="center">
			            		<a class="btn btn-default" href="${path }/admin/productTypeEditInit?id=${item.id }&tabid=${tabid}" data-toggle="dialog" data-width="800" data-height="600" data-mask="true" data-id="homeBannerEditInit" data-title="类型编辑">编辑</a>
								&nbsp;|&nbsp;
								<a class="btn btn-red" href="${path }/admin/productTypeUpdateStatus?id=${item.id }&status=3" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
							</td>
				        </tr>
			        </c:forEach>
		       	</c:otherwise>
		    </c:choose>
	    </tbody>
	</table>
</div>

<!-- 底部分页组件 -->
<div class="bjui-pageFooter">
	<div class="pages">
	    <span>每页 </span>
	    <div class="selectPagesize">
	        <select data-toggle="selectpicker" data-toggle-change="changepagesize">
	            <option value="10" selected="selected">10</option>
	            <option value="20">20</option>
	            <option value="50">50</option>
	        </select>
	    </div>
	    <span>&nbsp;条，共 ${page.pageTotalCount } 条， 共 ${page.pageTotalNum } 页</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${page.pageTotalCount }" data-page-size="${page.pageSize }" data-page-current="1"></div>
</div>
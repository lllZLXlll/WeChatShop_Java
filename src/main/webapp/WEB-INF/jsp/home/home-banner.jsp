<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 设置序号 -->
<c:set var="count" value="${(page.pageNum - 1) * page.pageSize}"></c:set> 

<!-- 表单 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${path }/admin/homeBanner" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize }">
        <input type="hidden" name="pageCurrent" value="${page.pageNum }">
        <input type="hidden" name="_" value="${tabid }">
        
        <div class="bjui-searchBar">
        	<!-- 添加banner -->
        	<a class="btn btn-default" href="${path }/admin/homeBannerAddInit?tabid=${tabid}" data-toggle="dialog" data-width="800" data-height="600" data-mask="true" data-title="添加轮播图">添加轮播图</a>
        </div>
    </form>
</div>


<!-- 表格 -->
<div class="bjui-pageContent">
	<table class="table table-bordered table-hover table-striped">
	    <thead>
	        <tr height="35">
	            <th title="编号" align="center">NO</th>
	            <th title="链接商品名称" align="center">链接商品名称</th>
	            <th title="轮播图" align="center">轮播图</th>
	            <th title="状态" align="center">状态</th>
	            <th title="序号" align="center">序号</th>
	            <th title="修改时间" align="center">修改时间</th>
	            <th title="操作" align="center">操作</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<!-- 没有数据 -->
	    	<c:choose>
	            <c:when test="${page.page == null or page.page == '[]' }">
		           	<tr>
	            		<td align="center" colspan="7">暂无数据</td>
	            	</tr>
		        </c:when>
	           	<c:otherwise>
		     		<!-- 循环输出数据  -->
			    	<c:forEach items="${page.page }" var="item" varStatus="status">
				        <tr height="90">
				        	<td align="center">${status.index + 1 + count }</td>
				            <td align="center">${item.name }</td>
				            <td align="center">
				            	<a href="${item.image }" target="_blank">
				            		<img src="${item.image }" width="180" height="90"/>
			            		</a>
			            	</td>
				            <td align="center">
				            	<c:if test="${item.status == 1}">展示</c:if>
				            	<c:if test="${item.status == 2}">隐藏</c:if>
			            	</td>
				            <td align="center">${item.sort }</td>
				            <td align="center"><fmt:formatDate value="${item.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				            <td align="center">
			            		<a class="btn btn-default" href="${path }/admin/homeBannerEditInit?id=${item.id }&tabid=${tabid}" data-toggle="dialog" data-width="800" data-height="600" data-mask="true" data-id="homeBannerEditInit" data-title="轮播图编辑">编辑</a>
								&nbsp;|&nbsp;
								<a class="btn btn-red" href="${path }/admin/homeBannerUpdateStatus?id=${item.id }&status=3" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a>
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
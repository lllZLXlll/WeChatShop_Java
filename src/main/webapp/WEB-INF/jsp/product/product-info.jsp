<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 设置序号 -->
<c:set var="count" value="${(page.pageNum - 1) * page.pageSize}"></c:set> 

<!-- 表单 -->
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${path }/admin/productInfo" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize }">
        <input type="hidden" name="pageCurrent" value="${page.pageNum }">
        <input type="hidden" name="tabid" value="${tabid }">
        
        <div class="bjui-searchBar">
        	<!-- 添加商品信息 -->
        	<a class="btn btn-blue" href="${path }/admin/productInfoAddInit?tabid=${tabid}" data-toggle="dialog" data-width="800" data-height="600" data-mask="true" data-title="添加商品">添加商品</a>
        
        	&nbsp;
        	<!-- 商品名称 -->
        	<label for="j_custom_total" class="control-label x70">商品名称：</label>
            <input type="text" name="name" value="${name }" size="12">
            
            &nbsp;
            <!-- 商品类型 -->
            <label for="j_custom_total" class="control-label x70">商品类型：</label>
            <select data-toggle="selectpicker" name="typeId" data-rule="required">
            	<option value="-1">-请选择-</option>
            	<c:forEach items="${typeList }" var="item">
            		<option value="${item.id }" ${typeId == item.id ? 'selected' : null }>${item.type }</option>
            	</c:forEach>
            </select>
            
            &nbsp;
            <!-- 商品状态 -->
            <label for="j_custom_total" class="control-label x70">商品状态：</label>
            <select data-toggle="selectpicker" name="downShelves" data-rule="required">
            	<option value="-1">-请选择-</option>
            	<option value="0" ${downShelves == 0 ? 'selected' : null}>已上架</option>
            	<option value="1" ${downShelves == 1 ? 'selected' : null}>已下架</option>
            </select>
            
            &nbsp;
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
       	</div>
    </form>
</div>


<!-- 表格 -->
<div class="bjui-pageContent">
	<table class="table table-bordered table-hover table-striped">
	    <thead>
	        <tr height="35">
	            <th title="编号" align="center">NO</th>
	            <th title="商品名称" align="center" width="15%">商品名称</th>
	            <th title="商品主图" align="center">商品主图</th>
	            <th title="商品类型" align="center">商品类型</th>
	            <th title="商品价格" align="center">商品价格</th>
	            <th title="展示价格" align="center">展示价格</th>	
	            <th title="商品快递费" align="center">商品快递费</th>
	            <th title="限购数量" align="center">限购数量</th>
	            <th title="上/下架状态" align="center">上/下架状态</th>
	            <th title="操作" align="center">操作</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<!-- 没有数据 -->
	    	<c:choose>
	            <c:when test="${page.page == null or page.page == '[]' }">
		           	<tr>
	            		<td align="center" colspan="10">暂无数据</td>
	            	</tr>
		        </c:when>
	           	<c:otherwise>
		     		<!-- 循环输出数据  -->
			    	<c:forEach items="${page.page }" var="item" varStatus="status">
				        <tr height="80">
				        	<td align="center">${status.index + 1 + count }</td>
				            <td align="center" width="15%">${item.name }</td>
				            <td align="center">
				            	<a href="${item.productImage }" target="_blank">
				            		<img src="${item.productImage }" width="80" height="80"/>
			            		</a>
			            	</td>
				            <td align="center">${item.type }</td>
				            <td align="center">${item.price }</td>
				            <td align="center">${item.showPrice }</td>
				            <td align="center">${item.expressFee }</td>
				            <td align="center">
				            	<c:if test="${item.buyCount == -1 }">不限制</c:if>
				            	<c:if test="${item.buyCount != -1 }">${item.buyCount }</c:if>
			            	</td>
				            <td align="center">
				            	<c:if test="${item.downShelves == 1 }">已下架</c:if>
				            	<c:if test="${item.downShelves == 0 }">已上架</c:if>
				            </td>
				            <td align="center">
			            		<a class="btn btn-default" href="${path }/admin/productInfoEditInit?id=${item.id }&tabid=${tabid}" data-toggle="dialog" data-width="800" data-height="600" data-mask="true" data-title="编辑">编辑</a>
								<a class="btn ${item.classCount > 0 ? 'btn-default' : 'btn-red' }" href="${path }/admin/productClass?id=${item.id }&tabid=productClassNavtab-${item.id }" data-toggle="navtab" data-tabid="productClassNavtab-${item.id }" data-id="productClassNavtab-${item.id }" data-title="${item.name }-分类">分类</a>
								<a class="btn ${item.paramCount > 0 ? 'btn-default' : 'btn-red' }" href="${path }/admin/productParam?id=${item.id }&tabid=productParamNavtab-${item.id }" data-toggle="navtab" data-tabid="productParamNavtab-${item.id }" data-id="productParamNavtab-${item.id }" data-title="${item.name }-参数">参数</a>
								<a class="btn ${item.imageTextCount > 0 ? 'btn-default' : 'btn-red' }" href="${path }/admin/productImageText?id=${item.id }&tabid=productImageTextNavtab-${item.id }" data-toggle="navtab" data-tabid="productImageTextNavtab-${item.id }" data-id="productImageTextNavtab-${item.id }" data-title="${item.name }-图文详情">图文详情</a>
								<c:if test="${item.downShelves == 1 && item.classCount > 0 && item.paramCount > 0 && item.imageTextCount > 0 }">
									<a class="btn btn-red" href="${path }/admin/productUpDownShelves?id=${item.id }&downShelves=${item.downShelves }" data-toggle="doajax" data-confirm-msg="商品上架前请先检查信息是否有误哦！确定上架？">上架</a>
								</c:if>
								<c:if test="${item.downShelves == 0 }">
									<a class="btn btn-red" href="${path }/admin/productUpDownShelves?id=${item.id }&downShelves=${item.downShelves }" data-toggle="doajax" data-confirm-msg="确定要下架吗？">下架</a>
								</c:if>
								<%-- <a class="btn btn-red" href="${path }/admin/productInfoUpdateStatus?id=${item.id }" data-toggle="doajax" data-confirm-msg="确定要删除吗？">删除</a> --%>
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

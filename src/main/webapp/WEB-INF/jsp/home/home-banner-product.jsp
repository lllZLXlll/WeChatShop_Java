<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="layout-02" style="height: 100%;width: 100%; overflow:hidden;" class="bjui-layout">
	<div class="bjui-pageHeader">
	    <form id="pagerForm" data-toggle="ajaxsearch" action="${path }/admin/queryAdminSelectProductList" method="post" class="nice-validator n-red">
	        <input type="hidden" name="pageSize" value="${page.pageSize }">
	        <input type="hidden" name="pageCurrent" value="${page.pageNum }">
	        
        	<label style="color: red;margin-top: 6px;">请在列表中选择链接商品</label>
	        
	        <div class="pull-right">
	            <label>商品名称：</label><input type="text" value="${_productName }" name="_productName" size="10" class="form-control" style="width: 100px;">&nbsp;
	            <button type="submit" class="btn btn-default" data-icon="search"> 查询</button>&nbsp;
	            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo"> 清空查询</a>
	        </div>
	    </form>
	</div>
	<div class="bjui-pageContent tableContent bjui-resizeGrid" style="top: 29px; bottom: 28px; overflow: hidden;overflow: auto;">
	    <div class="bjui-tablefixed clearfix" style="height: 100%; width: 100%;">
	    	<table class="table table-bordered table-hover table-striped">
			    <thead>
			        <tr>
			        	<th title="编号" align="center">选择</th>
			            <th title="编号" align="center">NO</th>
			            <th title="链接商品名称" align="center">商品名称</th>
			            <th title="商品图片" align="center">商品图片</th>
			        </tr>
			    </thead>
			    <tbody>
			        <!-- 没有数据 -->
			    	<c:choose>
			            <c:when test="${page.page == null or page.page == '[]' }">
				           	<tr>
			            		<td align="center" colspan="4">暂无数据</td>
			            	</tr>
				        </c:when>
			           	<c:otherwise>
				     		<!-- 循环输出数据  -->
					    	<c:forEach items="${page.page }" var="product" varStatus="status">
						        <tr height="50">
						            <td align="center">
						            	<c:if test="${_productId == product.id}">
							            		<input type="radio" name="selectProduct" data-toggle="icheck" value="${product.id },${product.name }" checked />
							            	</c:if>
							            	<c:if test="${_productId != product.id}">
							            		<input type="radio" name="selectProduct" data-toggle="icheck" value="${product.id },${product.name }" />
						            		</c:if>
				            		</td>
						        	<td align="center">${status.index + 1 + count }</td>
						        	<td align="center">${product.name }</td>
						            <td align="center">
						            	<a href="javascript:;">
						            		<img src="${product.productImage }" width="50" height="50"/>
					            		</a>
					            	</td>
						        </tr>
					        </c:forEach>
				       	</c:otherwise>
				    </c:choose>
		        </tbody>
	        </table>
	    </div>
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
</div>

	
<script type="text/javascript">
	$(function() {
		// 列表中选择商品
		$('input').on('ifChecked', function(event){
		  var value = event.currentTarget.value;
		  var productId = value.substring(0, value.indexOf(","));
		  var productName = value.substring(value.indexOf(",") + 1, value.length);
		  $("#productId").val(productId);
		  $("#productName").val(productName);
		});
		
		
	})

</script>
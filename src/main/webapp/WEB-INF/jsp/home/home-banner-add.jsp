<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent" style="height: 45%;overflow: auto;">
    <form action="${path }/admin/homeBannerAdd" data-toggle="validate">
        <input type="hidden" name="tabid" value="${tabid }">
        <table class="table table-bordered table-hover table-striped">
            <tbody>
                <tr>
                    <td> 
                        <label class="control-label x85">轮播图：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadHomeBannerImg"
						        data-file-size-limit="1024000000"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="image" value="" id="doc_pic" data-rule="required">
					        </div>
						</div>
                        <span id="doc_span_pic"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_name" class="control-label x85">状态：</label>
                        <input type="radio" name="status" data-toggle="icheck" value="1"  data-label="显示" checked />
                        <input type="radio" name="status" data-toggle="icheck" value="2" data-label="隐藏" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">序号：</label>
                        <input type="text" data-toggle="spinner" readonly name="sort" value="${sort }" size="5" data-min="1">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">链接商品：</label>
                        <input type="text" id="productName" readonly value="" size="18" data-rule="required">
                        <input type="hidden" id="productId" name="productId" value="">
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>

<div style="height: 55%;width: 100%;bottom: 30px;position: absolute;">
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
		    <div class="bjui-tablefixed clearfix" style="height: 70%; width: 100%;">
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
							            	<input type="radio" name="selectProduct" data-toggle="icheck" value="${product.id },${product.name }" />
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
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close" data-icon="close"> 取消</button></li>
        <li><button type="submit" class="btn btn-default" data-icon="save"> 保存</button></li>
    </ul>
</div>

<script type="text/javascript">
	$(function() {
		// 列表中选择商品
		$('[name="selectProduct"]').on('ifChecked', function(event){
		  var value = event.currentTarget.value;
		  var productId = value.substring(0, value.indexOf(","));
		  var productName = value.substring(value.indexOf(",") + 1, value.length);
		  $("#productId").val(productId);
		  $("#productName").val(productName);
		});
	})

    // 上传图片
    function doc_upload_success(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic').val('${path}' + json.fileName)
            $('#doc_span_pic').html('已上传图片：<img src="../'+ json.fileName +'" width="180" height="90">')
        }
    }

</script>

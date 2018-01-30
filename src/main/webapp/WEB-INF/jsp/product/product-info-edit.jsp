<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent" style="overflow: auto;">
    <form action="${path }/admin/productInfoEdit" data-toggle="validate">
        <input type="hidden" name="tabid" value="${tabid }">
        <input type="hidden" name="id" value="${product.id }">
        <table class="table table-bordered table-hover table-striped">
            <tbody>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品名称：</label>
                        <input type="text" name="name" value="${product.name }" size="26" data-rule="required">
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">商品主图：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="10240"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="productImage" value="${product.productImage }" id="doc_pic" data-rule="required">
					        </div>
						</div>
                        <span id="doc_span_pic">
                        	<img src="${product.productImage }" width="90" height="90">
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品类型：</label>
                        <select data-toggle="selectpicker" name="typeId" data-rule="required">
                        	<option value="-1">-请选择-</option>
                        	<c:forEach items="${typeList }" var="item">
                        		<option value="${item.id }" ${product.typeId == item.id ? 'selected' : null }>${item.type }</option>
                        	</c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品价格：</label>
                        <input name="price" value="${product.price }" size="15" data-rule="required;number">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品展示价格：</label>
                        <input name="showPrice" value="${product.showPrice }" size="15" data-rule="required;number">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品快递费：</label>
                        <input name="expressFee" value="${product.expressFee }" size="15" data-rule="required;number">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">限购数量：</label>
                        <input name="buyCount" value="${product.buyCount }" size="15" data-toggle="spinner" data-min="-1">
                        <label for="j_custom_total" class="control-label x120" style="color: red;">'-1' 代表不限制数量</label>
                    </td>
                </tr>
                
                <tr>
                    <td> 
                        <label class="control-label x120">商品详情图</label>
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">图-1：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="10240"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success1"
						        data-icon="cloud-upload"
						        data-drag-drop="true" 
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="detilsImage" value="${image.image_1 }" id="doc_pic1">
					        </div>
						</div>
                        <span id="doc_span_pic1">
                        	<c:if test="${image.image_1 != ''}">
                        		<img src="${image.image_1 }" width="90" height="90">
                        	</c:if>
                        </span>
                        <c:if test="${image.image_1 != ''}">
                        	<a class="btn btn-red" href="javascript:;" onclick="cancel_select_img(1)">删除</a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">图-2：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="10240"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success2"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="detilsImage" value="${image.image_2 }" id="doc_pic2">
					        </div>
						</div>
                        <span id="doc_span_pic2">
	                        <c:if test="${image.image_2 != ''}">
	                        	<img src="${image.image_2 }" width="90" height="90">
                        	</c:if>
                        </span>
                        <c:if test="${image.image_2 != ''}">
                        	<a class="btn btn-red" href="javascript:;" onclick="cancel_select_img(2)">删除</a>
                       	</c:if>
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">图-3：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="10240"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success3"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="detilsImage" value="${image.image_3 }" id="doc_pic3">
					        </div>
						</div>
                        <span id="doc_span_pic3">
                        	<c:if test="${image.image_3 != ''}">
                        		<img src="${image.image_3 }" width="90" height="90">
                       		</c:if>
                        </span>
                        <c:if test="${image.image_3 != ''}">
                        	<a class="btn btn-red" href="javascript:;" onclick="cancel_select_img(3)">删除</a>
                       	</c:if>
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">图-4：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="10240"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success4"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="detilsImage" value="${image.image_4 }" id="doc_pic4">
					        </div>
						</div>
                        <span id="doc_span_pic4">
                        	<c:if test="${image.image_4 != ''}">
                        		<img src="${image.image_4 }" width="90" height="90">
                        	</c:if>
                        </span>
                        <c:if test="${image.image_4 != ''}">
                        	<a class="btn btn-red" href="javascript:;" onclick="cancel_select_img(4)">删除</a>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">图-5：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="10240"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success5"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="detilsImage" value="${image.image_5 }" id="doc_pic5">
					        </div>
						</div>
                        <span id="doc_span_pic5">
                        	<c:if test="${image.image_5 != '' }">
                        		<img src="${image.image_5 }" width="90" height="90">
                        	</c:if>
                        </span>
                        <c:if test="${image.image_5 != '' }">
                        	<a class="btn btn-red" href="javascript:;" onclick="cancel_select_img(5)">删除</a>
                       	</c:if>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>

<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn btn-close" data-icon="close"> 取消</button></li>
        <li><button type="submit" class="btn btn-default" data-icon="save"> 保存</button></li>
    </ul>
</div>

<script type="text/javascript">
    // 上传图片
    function doc_upload_success(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic').val('${path}' + json.fileName)
            $('#doc_span_pic').html('已上传图片：<img src="../'+ json.fileName +'" width="90" height="90">')
        }
    }
    // 上传图片
    function doc_upload_success1(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic1').val('${path}' + json.fileName)
            $('#doc_span_pic1').html('已上传图片：<img src="../'+ json.fileName +'" width="90" height="90">')
        }
    }
    // 上传图片
    function doc_upload_success2(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic2').val('${path}' + json.fileName)
            $('#doc_span_pic2').html('已上传图片：<img src="../'+ json.fileName +'" width="90" height="90">')
        }
    }
    // 上传图片
    function doc_upload_success3(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic3').val('${path}' + json.fileName)
            $('#doc_span_pic3').html('已上传图片：<img src="../'+ json.fileName +'" width="90" height="90">')
        }
    }
    // 上传图片
    function doc_upload_success4(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic4').val('${path}' + json.fileName)
            $('#doc_span_pic4').html('已上传图片：<img src="../'+ json.fileName +'" width="90" height="90">')
        }
    }
    // 上传图片
    function doc_upload_success5(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic5').val('${path}' + json.fileName)
            $('#doc_span_pic5').html('已上传图片：<img src="../'+ json.fileName +'" width="90" height="90">')
        }
    }
    
    // 取消选择图片
    function cancel_select_img(id) {
    	$(this).alertmsg('confirm', '删除后将无法恢复！确定删除吗？', {title:'确定删除？', okCall: function() {
    		$('#doc_pic' + id).val('');
    		$('#doc_span_pic' + id).html('');
    	}})
    }

</script>

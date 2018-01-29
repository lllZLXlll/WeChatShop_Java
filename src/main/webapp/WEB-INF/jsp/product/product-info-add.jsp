<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent" style="overflow: auto;">
    <form action="${path }/admin/productInfoAdd" data-toggle="validate">
        <input type="hidden" name="tabid" value="${tabid }">
        <table class="table table-bordered table-hover table-striped">
            <tbody>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品名称：</label>
                        <input type="text" name="name" value="" size="15" data-rule="required">
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x120">商品主图：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductInfoImg"
						        data-file-size-limit="1024000000"
						        data-file-type-exts="*.jpg;*.png;*.gif;*.mpg"
						        data-multi="true"
						        data-on-upload-success="doc_upload_success"
						        data-icon="cloud-upload"
						        data-drag-drop="true"
						        data-file-obj-name="imageFile"
						        data-button-text="选择图片">
						        <input type="hidden" name="productImage" value="" id="doc_pic" data-rule="required">
					        </div>
						</div>
                        <span id="doc_span_pic"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品类型：</label>
                        <select data-toggle="selectpicker" name="typeId" data-rule="required">
                        	<option value="-1">-请选择-</option>
                        	<c:forEach items="${typeList }" var="item">
                        		<option value="${item.id }">${item.type }</option>
                        	</c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品价格：</label>
                        <input name="price" value="" size="15" data-rule="required">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品展示价格：</label>
                        <input name="showPrice" value="" size="15" data-rule="required">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">商品快递费：</label>
                        <input name="expressFee" value="" size="15" data-rule="required">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x120">限购数量：</label>
                        <input name="buyCount" value="" size="15" data-rule="required">
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
            $('#doc_span_pic').html('已上传图片：<img src="../'+ json.fileName +'" width="180" height="90">')
        }
    }

</script>

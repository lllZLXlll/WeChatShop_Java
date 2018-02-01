<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent" style="overflow: auto;">
    <form action="${path }/admin/productClassAdd" data-toggle="validate">
        <input type="hidden" name="tabid" value="${tabid }">
        <input type="hidden" name="productId" value="${productId }">
        <table class="table table-bordered table-hover table-striped">
            <tbody>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">分类名称：</label>
                        <input type="text" name="className" value="" size="15" data-rule="required">
                    </td>
                </tr>
                <tr>
                    <td> 
                        <label class="control-label x85">分类图片：</label>
                        <div style="display:inline-block; vertical-align:middle;">
						    <div id="doc_pic_up" data-toggle="upload" 
						    	data-uploader="${path }/upload/uploadProductClassImg"
						        data-file-size-limit="10240"
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
                        <label for="j_custom_total" class="control-label x85">价格：</label>
				        <input type="text" name="price" size="15" data-rule="required;number">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">库存：</label>
                        <input type="text" data-toggle="spinner" name="count" value="0" size="5" data-min="0">
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
</script>

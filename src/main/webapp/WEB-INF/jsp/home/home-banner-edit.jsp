<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent">
    <form action="${path }/admin/homeBannerEdit" id="j_custom_form" data-toggle="validate" 
    data-alertmsg="false" class="nice-validator n-red" data-callback="mycallback">
        <input type="hidden" name="id" value="${item.id }">
        <input type="hidden" name="productId" value="${item.productId }">
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
					        </div>
					        <input type="hidden" name="image" value="${item.image }" id="doc_pic">
						</div>
                        <span id="doc_span_pic">
                        	<img src="${item.image }" width="180" height="90">
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_name" class="control-label x85">状态：</label>
                        <c:if test="${item.status == 1 }">
	                        <input type="radio" name="status" data-toggle="icheck" value="1"  data-label="显示" checked />
	                        <input type="radio" name="status" data-toggle="icheck" value="2" data-label="隐藏" />
                        </c:if>
                        <c:if test="${item.status == 2 }">
	                        <input type="radio" name="status" data-toggle="icheck" value="1"  data-label="显示" />
	                        <input type="radio" name="status" data-toggle="icheck" value="2" data-label="隐藏" checked />
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">序号：</label>
                        <input type="text" data-toggle="spinner" readonly name="sort" value="${item.sort }" size="15" data-min="1">
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
    function doc_upload_success(file, data) {
        var json = $.parseJSON(data)
         
        $(this).bjuiajax('ajaxDone', json)
        if (json[BJUI.keys.statusCode] == BJUI.statusCode.ok) {
            $('#doc_pic').val('${path}' + json.fileName)
            $('#doc_span_pic').html('已上传图片：<img src="../'+ json.fileName +'" width="180" height="90">')
        }
    }

</script>

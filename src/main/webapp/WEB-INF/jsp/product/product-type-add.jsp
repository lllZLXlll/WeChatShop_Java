<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent" style="overflow: auto;">
    <form action="${path }/admin/productTypeAdd" data-toggle="validate">
        <input type="hidden" name="tabid" value="${tabid }">
        <table class="table table-bordered table-hover table-striped">
            <tbody>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">类型：</label>
                        <input type="text" name="type" value="" size="15" data-rule="required">
                    </td>
                </tr>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">类型描述：</label>
				        <textarea cols="30" rows="4" name="detils" data-rule="required"></textarea>
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

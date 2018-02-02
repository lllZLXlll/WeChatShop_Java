<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent" style="overflow: auto;">
    <form action="${path }/admin/productParamEdit" data-toggle="validate">
        <input type="hidden" name="tabid" value="${tabid }">
        <input type="hidden" name="id" value="${item.id }">
        <table class="table table-bordered table-hover table-striped">
            <tbody>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">参数名称：</label>
                        <input type="text" name="key" value="${item.paramKey }" size="15" data-rule="required">
                    </td>
                </tr>
            	<tr>
                    <td>
                        <label for="j_custom_total" class="control-label x85">参数描述：</label>
                        <input type="text" name="detail" value="${item.detail }" size="15" data-rule="required">
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

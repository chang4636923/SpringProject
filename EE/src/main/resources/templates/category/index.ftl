<html>
<#include "../comm/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../comm/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
    <#--提交表单-->
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" action="/sell/seller/category/save" method="post">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}">
                        </div>
                        <div class="form-group">
                            <label>类目type</label>
                            <input name="categoryType" type="text" class="form-control" value="${(category.categoryType)!''}">
                        </div>
                        <input name="categoryId" hidden type="text" value="${(category.categoryId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
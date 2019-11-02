<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>table page</title>
    <link rel="stylesheet" href="/asserts/layui/css/layui.css">

    <style>
        i {
            margin-right: 10px;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "../static_top2.ftl">

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe770;</i>StudentManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a></dd>
                        <dd><a href="/auth/staff/interview/list"><i class="layui-icon">&#xe609;</i>interviewList</a>
                        </dd>
                        <dd><a href="/auth/staff/free/time/list"><i class="layui-icon">&#xe609;</i>freeTimeList</a></dd>
                        <dd><a href="/auth/staff/student/table/list"><i class="layui-icon">&#xe609;</i>DataOperation</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe629;</i>CompanyManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/company/table/list"><i class="layui-icon">&#xe656;</i>DataOperation</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe637;</i>FreeTimeManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff//free/time"><i class="layui-icon">&#xe60e;</i>setFreeTime</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed"
                ">
                <a href="javascript:;"><i class="layui-icon">&#xe63c;</i>TableManage</a>
                <dl class="layui-nav-child">
                    <dd class="layui-this"><a href="/auth/staff/table/import"><i class="layui-icon">&#xe62d;</i>operationTable</a>
                    </dd>
                </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>import form</legend>
            </fieldset>
            <div class="layui-col-md5">
                <div class="layui-upload-drag" id="studentUploadFile">
                    <i class="layui-icon"></i>
                    <p>Import the student form, click upload, or drag the file here</p>
                </div>
            </div>
            <div class="layui-col-md5">
                <div class="layui-upload-drag" id="companyUploadFile">
                    <i class="layui-icon"></i>
                    <p>Import the company form, click upload, or drag the file here</p>
                </div>
            </div>
        </div>
        <div style="padding: 15px;">
            <div class="layui-row layui-col-space15" style="margin-top: 150px">
                <div class="layui-col-md5" style="text-align: center">
                    <div class="layui-card" style="width: 416px;height: 133px; border: 1px dashed #eeeeee">
                        <div class="layui-card-header">Download the student excel template</div>
                        <div class="layui-card-body">
                            <a class="layui-btn" href="/auth/staff/derive/excel/studentInfo">click to download</a>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md5" style="text-align: center; padding-left: 0px">
                    <div class="layui-card" style="width: 425px;height: 133px; border: 1px dashed #eeeeee">
                        <div class="layui-card-header" >Download the company excel template</div>
                        <div class="layui-card-body">
                            <a class="layui-btn" href="/auth/staff/derive/excel/companyInfo">click to download</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>
</div>

</body>

<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/asserts/layui/layui.all.js"></script>
<script type="text/javascript" src="/asserts/js/axquery.js"></script>

<script>
    layui.use(['element', 'upload', 'layer'], function () {
        var upload = layui.upload,
            layer = layui.layer,
            element = layui.element;

        // 表格上传
        var uploadInst = upload.render({
            elem: '#studentUploadFile'
            , url: '/auth/staff/upload/file?excelType=student'
            , exts: 'xls|xlsx'
            , accept: 'file'
            , done: function (res) {
                if (res.error == 0) {
                    layer.msg('successfully upload');
                }
            }
            , error: function () {
                layer.msg('An unknown error occurred while uploading');
            }
        });

        var uploadInst = upload.render({
            elem: '#companyUploadFile'
            , url: '/auth/staff/upload/file?excelType=company'
            , exts: 'xls|xlsx'
            , accept: 'file'
            , done: function (res) {
                if (res.error == 0) {
                    layer.msg('successfully upload');
                }
            }
            , error: function () {
                layer.msg('An unknown error occurred while uploading');
            }
        });

    });

</script>

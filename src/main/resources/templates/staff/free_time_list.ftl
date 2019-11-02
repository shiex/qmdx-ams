<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>student free time list</title>
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
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;"><i class="layui-icon">&#xe770;</i>StudentManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/index"><i class="layui-icon">&#xe609;</i>ApplicationList</a></dd>
                        <dd><a href="/auth/staff/interview/list"><i class="layui-icon">&#xe609;</i>interviewList</a></dd>
                        <dd class="layui-this"><a href="/auth/staff/free/time/list"><i class="layui-icon">&#xe609;</i>freeTimeList</a></dd>
                        <dd><a href="/auth/staff/student/table/list"><i class="layui-icon">&#xe609;</i>DataOperation</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe629;</i>CompanyManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/company/table/list"><i class="layui-icon">&#xe656;</i>DataOperation</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe637;</i>FreeTimeManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff//free/time"><i class="layui-icon">&#xe60e;</i>setFreeTime</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe63c;</i>TableManage</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/auth/staff/table/import"><i class="layui-icon">&#xe62d;</i>operationTable</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>Student free time List</legend>
            </fieldset>
            <table class="layui-hide" id="studentFreeTime" lay-filter="studentFreeTime-table"></table>
        </div>
    </div>

    <div class="layui-footer">
        © The murphy --- manufacture
    </div>
</div>

</body>

<script type="text/html" id="studentTime-tmp">
    <ul>
        {{each studentTimeList as studentTime}}
            <li class="layui-bg-green layui-text" style="display: inline-block; padding: 0px 6px">{{studentTime.freeTime}}</li>
        {{/each}}
    </ul>
</script>

<script type="text/javascript" src="/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/asserts/layui/layui.all.js"></script>
<script type="text/javascript" src="/asserts/js/axquery.js"></script>
<script type="text/javascript" src="/asserts/js/template-web.js"></script>

<script>
    layui.use(['element', 'table', 'form', 'layer'], function () {
        var table = layui.table,
            form = layui.form,
            layer = layui.layer,
            element = layui.element;

            table.render({
                elem: '#studentFreeTime',
                title: 'student free time List',
                page: true,
                limit: 15,
                limits: [15, 30, 40, 50],
                url: '/auth/staff/free/time/list/data',
                cols: [[
                    {field: 'studentId', title: 'studentId', width: 130, sort: true, fixed: 'left'}
                    , {field: 'studentName', title: 'studentName', width: 200}
                    , {
                        field: 'freeTimeList', title: 'freeTimeList', width: 1000
                        , templet: function (data) {
                            var studentTimeListData = {
                                studentTimeList: data.studentTimeList
                            }
                            var studentTimeHtml = template('studentTime-tmp', studentTimeListData);
                            return studentTimeHtml;
                        }
                    }
                ]]
            });
    });

</script>
